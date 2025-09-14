package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.tlotd.block.ModBlocks;
import net.tlotd.config.ModConfigs;
import net.tlotd.item.ModItems;
import net.tlotd.sound.ModSounds;
import net.tlotd.util.ModTags;
import net.tlotd.world.SignalTrackingArray;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class RadioBlock extends Block {

    public static final IntProperty FREQUENCY = IntProperty.of("frequency", 0, 4);
    public static final IntProperty WOOD_TYPE = IntProperty.of("wood_type", 1, 28);
    public static final BooleanProperty MODDED = BooleanProperty.of("modded");
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = FacingBlock.FACING;

    private static final VoxelShape Z_SHAPE = Block.createCuboidShape(4.0, 0.0, 0.0, 12.0, 11.0, 16.0);
    private static final VoxelShape X_SHAPE = Block.createCuboidShape(0.0, 0.0, 4.0, 16.0, 11.0, 12.0);

    private static final Text[] FREQUENCY_MESSAGES = new Text[]{
            Text.translatable("messages.tlotd.radio.frequency.0"),
            Text.translatable("messages.tlotd.radio.frequency.1"),
            Text.translatable("messages.tlotd.radio.frequency.2"),
            Text.translatable("messages.tlotd.radio.frequency.3"),
            Text.translatable("messages.tlotd.radio.frequency.4")
    };
    private static final SoundEvent[] FREQUENCY_SOUNDS = new SoundEvent[]{
            null,
            ModSounds.RADIO_FREQUENCY_1,
            ModSounds.RADIO_FREQUENCY_2,
            ModSounds.RADIO_FREQUENCY_3,
            ModSounds.RADIO_FREQUENCY_4
    };

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

    private static String[] FREQUENCY_DISC_KEYS = null;

    private static String[] getFrequencyDiscKeys() {
        if (FREQUENCY_DISC_KEYS == null) {
            FREQUENCY_DISC_KEYS = new String[]{
                    ModItems.MUSIC_DISC_1.getTranslationKey(),
                    ModItems.MUSIC_DISC_2.getTranslationKey(),
                    ModItems.MUSIC_DISC_3.getTranslationKey(),
                    ModItems.MUSIC_DISC_4.getTranslationKey()
            };
        }
        return FREQUENCY_DISC_KEYS;
    }

    public RadioBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(WATERLOGGED, false)
                .with(FREQUENCY, 0)
                .with(WOOD_TYPE, 1)
                .with(MODDED, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
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
        builder.add(FACING, WATERLOGGED, FREQUENCY, WOOD_TYPE, MODDED);
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
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext opts) {
        if (!ModConfigs.ALL_SIGNALS_UNLOCKED) {
            tooltip.add(Text.literal(""));
            tooltip.add(Text.translatable("block.tlotd.radio.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.radio.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.signal_transmitter").formatted(Formatting.BLUE)));
        }
        super.appendTooltip(stack, world, tooltip, opts);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return ModBlocks.RADIO.asItem().getDefaultStack();
    }

    private void sendFrequencyMessageAndSound(PlayerEntity player, World world, BlockPos pos, int freq) {
        player.sendMessage(FREQUENCY_MESSAGES[freq], true);
        if (freq > 0) {
            world.playSound(null, pos, FREQUENCY_SOUNDS[freq], SoundCategory.RECORDS, 1.0f, 1.0f);
        }
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
        int freq = state.get(FREQUENCY);
        if (player.isSneaking()) {
            MinecraftClient.getInstance().getSoundManager().stopAll();
            if (!world.isClient) {
                ServerWorld serverWorld = (ServerWorld) world;
                SignalTrackingArray tracker = SignalTrackingArray.get(serverWorld);
                boolean radioOff = state.getBlock().equals(ModBlocks.RADIO);
                if (radioOff) {
                    if (ModConfigs.ALL_SIGNALS_UNLOCKED) {
                        world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state));
                        sendFrequencyMessageAndSound(player, world, pos, freq);
                    } else if (tracker.hasAnySignals()) {
                        for (int i = 1; i <= 4; i++) {
                            if (tracker.hasSignal(getFrequencyDiscKeys()[i - 1]) && freq <= i) {
                                world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state).with(FREQUENCY, i));
                                sendFrequencyMessageAndSound(player, world, pos, i);
                                return ActionResult.SUCCESS;
                            }
                        }
                        world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state).with(FREQUENCY, 0));
                        sendFrequencyMessageAndSound(player, world, pos, 0);
                    } else {
                        world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state).with(FREQUENCY, 0));
                        sendFrequencyMessageAndSound(player, world, pos, 0);
                    }
                } else {
                    world.setBlockState(pos, ModBlocks.RADIO.getStateWithProperties(state));
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
        if (state.getBlock().equals(ModBlocks.RADIO_ON)) {
            MinecraftClient.getInstance().getSoundManager().stopAll();
            if (!world.isClient) {
                ServerWorld serverWorld = (ServerWorld) world;
                SignalTrackingArray tracker = SignalTrackingArray.get(serverWorld);
                if (ModConfigs.ALL_SIGNALS_UNLOCKED) {
                    int newFreq = freq < 4 ? freq + 1 : 1;
                    world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state.with(FREQUENCY, newFreq)));
                    sendFrequencyMessageAndSound(player, world, pos, newFreq);
                } else if (tracker.hasAnySignals()) {
                    boolean updated = false;
                    for (int i = 1; i <= 4; i++) {
                        if (tracker.hasSignal(getFrequencyDiscKeys()[i - 1]) && freq < i) {
                            world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state).with(FREQUENCY, i));
                            sendFrequencyMessageAndSound(player, world, pos, i);
                            updated = true;
                            break;
                        }
                    }
                    if (!updated) {
                        world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state).with(FREQUENCY, 0));
                        sendFrequencyMessageAndSound(player, world, pos, 0);
                    }
                } else {
                    sendFrequencyMessageAndSound(player, world, pos, 0);
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
}