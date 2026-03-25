package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.tlotd.block.ModBlocks;
import net.tlotd.block.entity.RadioBlockEntity;
import net.tlotd.config.ModConfigs;
import net.tlotd.sound.ModSounds;
import net.tlotd.util.ModAdvancementTriggers;
import net.tlotd.util.ModTags;
import net.tlotd.world.SignalTrackingArray;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class RadioBlock extends Block implements BlockEntityProvider {
    public static final BooleanProperty ON = BooleanProperty.of("on");
    public static final IntProperty FREQUENCY = IntProperty.of("frequency", 0, 4);
    public static final IntProperty WOOD_TYPE = IntProperty.of("wood_type", 1, 28);
    public static final BooleanProperty MODDED = BooleanProperty.of("modded");
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    private static final VoxelShape Z_SHAPE = Block.createCuboidShape(4.0, 0.0, 0.0, 12.0, 11.0, 16.0);
    private static final VoxelShape X_SHAPE = Block.createCuboidShape(0.0, 0.0, 4.0, 16.0, 11.0, 12.0);

    private static final Map<TagKey<Item>, Integer> WOOD_TYPE_MAP = Map.ofEntries(
            Map.entry(ItemTags.OAK_LOGS, 1),
            Map.entry(ItemTags.SPRUCE_LOGS, 2),
            Map.entry(ItemTags.BIRCH_LOGS, 3),
            Map.entry(ItemTags.JUNGLE_LOGS, 4),
            Map.entry(ItemTags.ACACIA_LOGS, 5),
            Map.entry(ItemTags.DARK_OAK_LOGS, 6),
            Map.entry(ItemTags.MANGROVE_LOGS, 7),
            Map.entry(ItemTags.CHERRY_LOGS, 8),
            Map.entry(ModTags.Items.GINKGO_LOGS, 10),
            Map.entry(ItemTags.BAMBOO_BLOCKS, 11),
            Map.entry(ItemTags.CRIMSON_STEMS, 12),
            Map.entry(ItemTags.WARPED_STEMS, 13)
    );

    private record ModdedWoodRule(String modId, String namePart, int type) {}
    private static final List<ModdedWoodRule> MODDED_WOOD_RULES = List.of(
            new ModdedWoodRule("aether", "skyroot", 1),
            new ModdedWoodRule("aether", "golden_oak", 1),
            new ModdedWoodRule("twilightforest", "twilight_oak", 2),
            new ModdedWoodRule("twilightforest", "canopy", 3),
            new ModdedWoodRule("twilightforest", "mangrove", 4),
            new ModdedWoodRule("twilightforest", "dark", 5),
            new ModdedWoodRule("twilightforest", "time", 6),
            new ModdedWoodRule("twilightforest", "transformation", 7),
            new ModdedWoodRule("twilightforest", "mining", 8),
            new ModdedWoodRule("twilightforest", "sorting", 9),
            new ModdedWoodRule("thermal", "rubberwood", 10),
            new ModdedWoodRule("quark", "ancient", 11),
            new ModdedWoodRule("quark", "azalea", 12),
            new ModdedWoodRule("quark", "blossom", 13),
            new ModdedWoodRule("alexscaves", "pewen", 14),
            new ModdedWoodRule("alexscaves", "thornwood", 15),
            new ModdedWoodRule("biomesoplenty", "fir", 16),
            new ModdedWoodRule("biomesoplenty", "pine", 17),
            new ModdedWoodRule("biomesoplenty", "maple", 18),
            new ModdedWoodRule("biomesoplenty", "redwood", 19),
            new ModdedWoodRule("biomesoplenty", "mahogany", 20),
            new ModdedWoodRule("biomesoplenty", "jacaranda", 21),
            new ModdedWoodRule("biomesoplenty", "palm", 22),
            new ModdedWoodRule("biomesoplenty", "willow", 23),
            new ModdedWoodRule("biomesoplenty", "dead", 24),
            new ModdedWoodRule("biomesoplenty", "magic", 25),
            new ModdedWoodRule("biomesoplenty", "umbran", 26),
            new ModdedWoodRule("biomesoplenty", "hellbark", 27),
            new ModdedWoodRule("biomesoplenty", "empyreal", 28)
    );

    private Identifier findNextDisc(SignalTrackingArray tracker, @Nullable Identifier current) {
        List<Identifier> sorted = tracker.getAllSignals().stream().sorted().toList();
        if (sorted.isEmpty()) return null;
        int startIndex = 0;
        if (current != null) {
            int idx = sorted.indexOf(current);
            if (idx >= 0) startIndex = (idx + 1) % sorted.size();
        }
        for (int i = 0; i < sorted.size(); i++) {
            Identifier id = sorted.get((startIndex + i) % sorted.size());
            Item item = Registries.ITEM.get(id);
            if (item instanceof MusicDiscItem) {
                return id;
            }
        }
        return null;
    }

    private void playDisc(World world, BlockPos pos, Identifier id) {
        Item item = Registries.ITEM.get(id);
        if (item instanceof MusicDiscItem disc) {
            world.playSound(
                    null,
                    pos,
                    disc.getSound(),
                    SoundCategory.RECORDS,
                    1.0f,
                    1.0f
            );
        }
    }

    public RadioBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(ON, false)
                .with(FACING, Direction.NORTH)
                .with(WATERLOGGED, false)
                .with(FREQUENCY, 0)
                .with(WOOD_TYPE, 1)
                .with(MODDED, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(ON, false)
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER))
                .with(FREQUENCY, 0)
                .with(WOOD_TYPE, 1)
                .with(MODDED, false);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction dir, BlockState neighbor, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, dir, neighbor, world, pos, neighborPos);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ON, FACING, WATERLOGGED, FREQUENCY, WOOD_TYPE, MODDED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        return switch (state.get(FACING)) {
            case EAST, WEST -> Z_SHAPE;
            default -> X_SHAPE;
        };
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public MutableText getName() {
        return Text.translatable("block.tlotd.radio");
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return ModBlocks.RADIO.asItem().getDefaultStack();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext opts) {
        if (!ModConfigs.ALL_SIGNALS_UNLOCKED) {
            tooltip.add(Text.empty());
            tooltip.add(Text.translatable("block.tlotd.radio.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.radio.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.signal_transmitter").formatted(Formatting.BLUE)));
        }
        super.appendTooltip(stack, world, tooltip, opts);
    }

    private void tryUpdateWood(World world, BlockPos pos, PlayerEntity player) {
        ItemStack stack = player.getMainHandStack();
        for (var entry : WOOD_TYPE_MAP.entrySet()) {
            if (stack.isIn(entry.getKey())) {
                updateWood(world, pos, entry.getValue(), false);
                return;
            }
        }
        String name = stack.getTranslationKey();
        for (ModdedWoodRule rule : MODDED_WOOD_RULES) {
            if (name.contains(rule.modId()) && name.contains(rule.namePart())) {
                updateWood(world, pos, rule.type(), true);
                return;
            }
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.isSneaking()) {
            MinecraftClient.getInstance().getSoundManager().stopAll();
            if (!world.isClient) {
                ServerWorld serverWorld = (ServerWorld) world;
                SignalTrackingArray tracker = SignalTrackingArray.get(serverWorld);
                if (!state.get(ON)) {
                    world.setBlockState(pos, ModBlocks.RADIO.getStateWithProperties(state).with(ON, true));
                    RadioBlockEntity be = (RadioBlockEntity) world.getBlockEntity(pos);
                    if (be == null) return ActionResult.SUCCESS;
                    if (!tracker.hasAnySignals()) {
                        player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.list_empty"), true);
                    } else {
                        Identifier current = be.getCurrentTrack();
                        Identifier toPlay = null;
                        if (current != null && tracker.hasSignal(current)) {
                            Item item = Registries.ITEM.get(current);
                            if (item instanceof MusicDiscItem) {
                                toPlay = current;
                            }
                        }
                        if (toPlay == null) {
                            toPlay = findNextDisc(tracker, current);
                        }
                        if (toPlay != null) {
                            be.setCurrentTrack(toPlay);
                            playDisc(world, pos, toPlay);
                            ModAdvancementTriggers.PLAY_RADIO.trigger(
                                    (ServerPlayerEntity) player,
                                    (ServerWorld) world,
                                    toPlay
                            );
                            int newFreq = state.get(FREQUENCY) == 0 ? 1 : state.get(FREQUENCY);
                            world.setBlockState(pos, ModBlocks.RADIO.getStateWithProperties(state).with(ON, true).with(FREQUENCY, newFreq));
                            player.sendMessage(Text.translatable(Registries.ITEM.get(toPlay).getTranslationKey() + ".desc"), true);
                        } else {
                            be.setCurrentTrack(null);
                            world.setBlockState(pos, ModBlocks.RADIO.getStateWithProperties(state).with(ON, true).with(FREQUENCY, 0));
                            player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.list_empty"), true);
                        }
                    }
                } else {
                    world.setBlockState(pos, ModBlocks.RADIO.getStateWithProperties(state).with(ON, false));
                }
            }
            world.playSound(null, pos, ModSounds.BLOCK_RADIO_SWITCH_FREQUENCY, SoundCategory.BLOCKS, 1.0f, 1.0f);
            return ActionResult.SUCCESS;
        }
        ItemStack held = player.getMainHandStack();
        if (held.isIn(ItemTags.LOGS) || held.isIn(ItemTags.BAMBOO_BLOCKS) || held.isIn(ModTags.Items.GINKGO_LOGS)) {
            if (!world.isClient) {
                tryUpdateWood(world, pos, player);
            }
            world.playSound(null, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            return ActionResult.SUCCESS;
        }
        if (state.get(ON)) {
            MinecraftClient.getInstance().getSoundManager().stopAll();
            if (!world.isClient) {
                ServerWorld serverWorld = (ServerWorld) world;
                SignalTrackingArray tracker = SignalTrackingArray.get(serverWorld);
                if (!tracker.hasAnySignals()) {
                    player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.list_empty"), true);
                    return ActionResult.SUCCESS;
                }
                RadioBlockEntity be = (RadioBlockEntity) world.getBlockEntity(pos);
                if (be == null) return ActionResult.SUCCESS;
                Identifier next = findNextDisc(tracker, be.getCurrentTrack());
                if (next != null) {
                    be.setCurrentTrack(next);
                    playDisc(world, pos, next);
                    ModAdvancementTriggers.PLAY_RADIO.trigger(
                            (ServerPlayerEntity) player,
                            (ServerWorld) world,
                            next
                    );
                    int newFreq = state.get(FREQUENCY) < 4 ? state.get(FREQUENCY) + 1 : 1;
                    world.setBlockState(pos, state.with(FREQUENCY, newFreq));
                    player.sendMessage(Text.translatable(Registries.ITEM.get(next).getTranslationKey() + ".desc"), true);
                } else {
                    player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.list_empty"), true);
                }
            }
            world.playSound(null, pos, ModSounds.BLOCK_RADIO_SWITCH_FREQUENCY, SoundCategory.BLOCKS, 1.0f, 1.0f);
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }

    public void updateWood(World world, BlockPos pos, int type, boolean modded) {
        world.setBlockState(pos, world.getBlockState(pos).with(WOOD_TYPE, type).with(MODDED, modded));
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        MinecraftClient.getInstance().getSoundManager().stopAll();
        world.playSound(null, pos, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
        super.onBreak(world, pos, state, player);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RadioBlockEntity(pos, state);
    }
}