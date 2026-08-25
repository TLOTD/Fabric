package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.tlotd.block.ModBlocks;
import net.tlotd.block.entity.SignalTransmitterBlockEntity;
import net.tlotd.block.enum_property.AntennaMaterial;
import net.tlotd.sound.ModSounds;
import net.tlotd.util.ModTags;
import net.tlotd.world.RadioStation;
import net.tlotd.world.SignalTrackingArray;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SignalTransmitterBlock extends Block implements BlockEntityProvider {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final EnumProperty<AntennaMaterial> ANTENNA_MATERIAL = EnumProperty.of("antenna_material", AntennaMaterial.class);

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing()).with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, ANTENNA_MATERIAL);
    }

    public SignalTransmitterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false).with(ANTENNA_MATERIAL, AntennaMaterial.COPPER));
    }

    public static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(1, 0, 1, 15, 11.5, 15), Block.createCuboidShape(2, 11.5, 2, 14, 15, 14), Block.createCuboidShape(3, 15, 3, 13, 16, 13));

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("block.tlotd.signal_transmitter.tooltip").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.signal_transmitter.tooltip_2")).formatted(Formatting.BLUE));
        tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.signal_transmitter.tooltip_3")).formatted(Formatting.BLUE));
        tooltip.add(Text.translatable("block.tlotd.signal_transmitter.tooltip_4").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(" 4x ").append(Text.translatable("block.tlotd.any_signal_transmitter_antenna")).formatted(Formatting.BLUE));
        super.appendTooltip(stack, world, tooltip, options);
    }

    private void buildSignalTower(ItemStack stack, World world, BlockPos pos, BlockState state, PlayerEntity player) {
        world.setBlockState(pos, state);
        world.playSound(null, pos, SoundEvents.BLOCK_COPPER_PLACE, SoundCategory.BLOCKS, 1.0f, 1.0f);
        if (!player.isCreative()) {
            stack.decrement(1);
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (world.isClient()) return;
        ServerWorld serverWorld = (ServerWorld) world;
        SignalTrackingArray tracker = SignalTrackingArray.get(serverWorld);
        tracker.addStation(pos, 0, 0);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
        if (world.isClient()) return;
        ServerWorld serverWorld = (ServerWorld) world;
        SignalTrackingArray tracker = SignalTrackingArray.get(serverWorld);
        tracker.removeStation(pos);
    }

    public static void updateStationActive(World world, BlockPos pos) {
        if (world.isClient()) return;
        SignalTrackingArray tracker = SignalTrackingArray.get((ServerWorld) world);
        RadioStation station = tracker.getStation(pos);
        if (station == null) return;
        station.setActive(antenna(world, pos) && station.hasSignals());
        station.setStrength(strength(world, pos));
        station.setRange(range(world, pos));
        tracker.markDirty();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;
        ServerWorld serverWorld = (ServerWorld) world;
        SignalTrackingArray tracker = SignalTrackingArray.get(serverWorld);
        RadioStation station = tracker.getStation(pos);
        ItemStack stack = player.getMainHandStack();
        if (!antenna(world, pos)) {
            if (world.getBlockState(pos.up()).getBlock() instanceof AirBlock && stack.isOf(ModBlocks.ALUMINIUM_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
                buildSignalTower(stack, world, pos.up(), ModBlocks.ALUMINIUM_SIGNAL_TRANSMITTER_ANTENNA.getDefaultState(), player);
                world.setBlockState(pos, state.with(ANTENNA_MATERIAL, AntennaMaterial.ALUMINIUM));
            } else if (world.getBlockState(pos.up(2)).getBlock() instanceof AirBlock && stack.isOf(ModBlocks.ALUMINIUM_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
                buildSignalTower(stack, world, pos.up(2), ModBlocks.ALUMINIUM_SIGNAL_TRANSMITTER_ANTENNA.getDefaultState().with(SignalTransmitterAntennaBlock.PART, 1), player);
                world.setBlockState(pos, state.with(ANTENNA_MATERIAL, AntennaMaterial.ALUMINIUM));
            } else if (world.getBlockState(pos.up(3)).getBlock() instanceof AirBlock && stack.isOf(ModBlocks.ALUMINIUM_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
                buildSignalTower(stack, world, pos.up(3), ModBlocks.ALUMINIUM_SIGNAL_TRANSMITTER_ANTENNA.getDefaultState().with(SignalTransmitterAntennaBlock.PART, 2), player);
                world.setBlockState(pos, state.with(ANTENNA_MATERIAL, AntennaMaterial.ALUMINIUM));
            } else if (world.getBlockState(pos.up(4)).getBlock() instanceof AirBlock && stack.isOf(ModBlocks.ALUMINIUM_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
                buildSignalTower(stack, world, pos.up(4), ModBlocks.ALUMINIUM_SIGNAL_TRANSMITTER_ANTENNA.getDefaultState().with(SignalTransmitterAntennaBlock.PART, 3), player);
                world.setBlockState(pos, state.with(ANTENNA_MATERIAL, AntennaMaterial.ALUMINIUM));
            } else if (world.getBlockState(pos.up()).getBlock() instanceof AirBlock && stack.isOf(ModBlocks.COPPER_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
                buildSignalTower(stack, world, pos.up(), ModBlocks.COPPER_SIGNAL_TRANSMITTER_ANTENNA.getDefaultState(), player);
                world.setBlockState(pos, state.with(ANTENNA_MATERIAL, AntennaMaterial.COPPER));
            } else if (world.getBlockState(pos.up(2)).getBlock() instanceof AirBlock && stack.isOf(ModBlocks.COPPER_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
                buildSignalTower(stack, world, pos.up(2), ModBlocks.COPPER_SIGNAL_TRANSMITTER_ANTENNA.getDefaultState().with(SignalTransmitterAntennaBlock.PART, 1), player);
                world.setBlockState(pos, state.with(ANTENNA_MATERIAL, AntennaMaterial.COPPER));
            } else if (world.getBlockState(pos.up(3)).getBlock() instanceof AirBlock && stack.isOf(ModBlocks.COPPER_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
                buildSignalTower(stack, world, pos.up(3), ModBlocks.COPPER_SIGNAL_TRANSMITTER_ANTENNA.getDefaultState().with(SignalTransmitterAntennaBlock.PART, 2), player);
                world.setBlockState(pos, state.with(ANTENNA_MATERIAL, AntennaMaterial.COPPER));
            } else if (world.getBlockState(pos.up(4)).getBlock() instanceof AirBlock && stack.isOf(ModBlocks.COPPER_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
                buildSignalTower(stack, world, pos.up(4), ModBlocks.COPPER_SIGNAL_TRANSMITTER_ANTENNA.getDefaultState().with(SignalTransmitterAntennaBlock.PART, 3), player);
                world.setBlockState(pos, state.with(ANTENNA_MATERIAL, AntennaMaterial.COPPER));
            } else if (world.getBlockState(pos.up()).getBlock() instanceof AirBlock && stack.isOf(ModBlocks.GOLD_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
                buildSignalTower(stack, world, pos.up(), ModBlocks.GOLD_SIGNAL_TRANSMITTER_ANTENNA.getDefaultState(), player);
                world.setBlockState(pos, state.with(ANTENNA_MATERIAL, AntennaMaterial.GOLD));
            } else if (world.getBlockState(pos.up(2)).getBlock() instanceof AirBlock && stack.isOf(ModBlocks.GOLD_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
                buildSignalTower(stack, world, pos.up(2), ModBlocks.GOLD_SIGNAL_TRANSMITTER_ANTENNA.getDefaultState().with(SignalTransmitterAntennaBlock.PART, 1), player);
                world.setBlockState(pos, state.with(ANTENNA_MATERIAL, AntennaMaterial.GOLD));
            } else if (world.getBlockState(pos.up(3)).getBlock() instanceof AirBlock && stack.isOf(ModBlocks.GOLD_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
                buildSignalTower(stack, world, pos.up(3), ModBlocks.GOLD_SIGNAL_TRANSMITTER_ANTENNA.getDefaultState().with(SignalTransmitterAntennaBlock.PART, 2), player);
                world.setBlockState(pos, state.with(ANTENNA_MATERIAL, AntennaMaterial.GOLD));
            } else if (world.getBlockState(pos.up(4)).getBlock() instanceof AirBlock && stack.isOf(ModBlocks.GOLD_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
                buildSignalTower(stack, world, pos.up(4), ModBlocks.GOLD_SIGNAL_TRANSMITTER_ANTENNA.getDefaultState().with(SignalTransmitterAntennaBlock.PART, 3), player);
                world.setBlockState(pos, state.with(ANTENNA_MATERIAL, AntennaMaterial.GOLD));
            } else if (world.getBlockState(pos.up()).getBlock() instanceof AirBlock && stack.isOf(ModBlocks.MITHRIL_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
                buildSignalTower(stack, world, pos.up(), ModBlocks.MITHRIL_SIGNAL_TRANSMITTER_ANTENNA.getDefaultState(), player);
                world.setBlockState(pos, state.with(ANTENNA_MATERIAL, AntennaMaterial.MITHRIL));
            } else if (world.getBlockState(pos.up(2)).getBlock() instanceof AirBlock && stack.isOf(ModBlocks.MITHRIL_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
                buildSignalTower(stack, world, pos.up(2), ModBlocks.MITHRIL_SIGNAL_TRANSMITTER_ANTENNA.getDefaultState().with(SignalTransmitterAntennaBlock.PART, 1), player);
                world.setBlockState(pos, state.with(ANTENNA_MATERIAL, AntennaMaterial.MITHRIL));
            } else if (world.getBlockState(pos.up(3)).getBlock() instanceof AirBlock && stack.isOf(ModBlocks.MITHRIL_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
                buildSignalTower(stack, world, pos.up(3), ModBlocks.MITHRIL_SIGNAL_TRANSMITTER_ANTENNA.getDefaultState().with(SignalTransmitterAntennaBlock.PART, 2), player);
                world.setBlockState(pos, state.with(ANTENNA_MATERIAL, AntennaMaterial.MITHRIL));
            } else if (world.getBlockState(pos.up(4)).getBlock() instanceof AirBlock && stack.isOf(ModBlocks.MITHRIL_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
                buildSignalTower(stack, world, pos.up(4), ModBlocks.MITHRIL_SIGNAL_TRANSMITTER_ANTENNA.getDefaultState().with(SignalTransmitterAntennaBlock.PART, 3), player);
                world.setBlockState(pos, state.with(ANTENNA_MATERIAL, AntennaMaterial.MITHRIL));
            } else {
                player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.incomplete"), false);
                world.playSound(null, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
            updateStationActive(world, pos);
            return ActionResult.SUCCESS;
        }
        if (!stack.isEmpty() && stack.isIn(ModTags.Items.TRANSMITTABLE_SIGNALS)) {
            if (station == null) {
                tracker.addStation(pos, 1, 1);
                station = tracker.getStation(pos);
            }
            Identifier id = Registries.ITEM.getId(stack.getItem());
            if (station.hasSignal(id)) {
                tracker.removeSignal(pos, id);
                updateStationActive(world, pos);
                player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.removed"), true);
            } else {
                tracker.addSignal(pos, id);
                updateStationActive(world, pos);
                player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.added"), true);
            }
            world.playSound(null, pos, ModSounds.BLOCK_VIDEOCASSETTE_RECORDER, SoundCategory.BLOCKS, 1.0f, 1.0f);
        } else if (station != null && stack.isOf(Items.NAME_TAG) && stack.hasCustomName()) {
            Text.translatable("block.tlotd.signal_transmitter.name_set").formatted(Formatting.GRAY);
            station.setName(stack.getName().getString());
            world.playSound(null, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 1.0f, 1.0f);
        } else {
            if (station == null || !station.hasSignals()) {
                if (player.isSneaking()) {
                    player.sendMessage(station == null || station.getName().isEmpty() ? Text.translatable("block.tlotd.signal_transmitter.unnamed").formatted(Formatting.GRAY) : Text.literal(station.getName()).formatted(Formatting.GRAY));
                    player.sendMessage(SignalTrackingArray.getStats(station != null && station.isActive(), station == null ? 0 : station.getStrength(), station == null ? 0 : station.getRange(), station == null ? 0 : station.getSignals().size(), ".1"), false);
                    player.sendMessage(SignalTrackingArray.getStats(station != null && station.isActive(), station == null ? 0 : station.getStrength(), station == null ? 0 : station.getRange(), station == null ? 0 : station.getSignals().size(), ".2"), false);
                } else {
                    player.sendMessage(SignalTrackingArray.getStats(station != null && station.isActive(), station == null ? 0 : station.getStrength(), station == null ? 0 : station.getRange(), station == null ? 0 : station.getSignals().size(), ""), true);
                }
            } else {
                if (player.isSneaking()) {
                    player.sendMessage(station.getName().isEmpty() ? Text.translatable("block.tlotd.signal_transmitter.unnamed").formatted(Formatting.GRAY) : Text.literal(station.getName()).formatted(Formatting.GRAY));
                    player.sendMessage(SignalTrackingArray.getStats(station.isActive(), station.getStrength(), station.getRange(), station.getSignals().size(), ".1"), false);
                    player.sendMessage(SignalTrackingArray.getStats(station.isActive(), station.getStrength(), station.getRange(), station.getSignals().size(), ".2"), false);
                    for (Identifier sig : station.getSignals()) {
                        String keyName = "item." + sig.getNamespace() + "." + sig.getPath();
                        String descKey = keyName + ".desc";
                        Text nameText;
                        Text descText;
                        if (Language.getInstance().hasTranslation(keyName)) {
                            nameText = Text.translatable(keyName);
                        } else {
                            String prettyName = sig.getPath().replace('_', ' ');
                            prettyName = Character.toUpperCase(prettyName.charAt(0)) + prettyName.substring(1);
                            nameText = Text.literal(prettyName);
                        }
                        if (Language.getInstance().hasTranslation(descKey)) {
                            descText = Text.translatable(descKey);
                        } else {
                            descText = Text.literal("");
                        }
                        player.sendMessage(Text.literal(" ").formatted(Formatting.GRAY).append(nameText).append(Text.literal(": ")).append(descText), false);
                    }
                } else {
                    player.sendMessage(SignalTrackingArray.getStats(station.isActive(), station.getStrength(), station.getRange(), station.getSignals().size(), ""), true);
                }
            }
            world.playSound(null, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }
        return ActionResult.SUCCESS;
    }

    private static boolean antenna(World world, BlockPos pos) {
        return world.getBlockState(pos.up()).getBlock() instanceof SignalTransmitterAntennaBlock && world.getBlockState(pos.up()).get(SignalTransmitterAntennaBlock.PART) == 0 && world.getBlockState(pos.up(2)).getBlock() instanceof SignalTransmitterAntennaBlock && world.getBlockState(pos.up(2)).get(SignalTransmitterAntennaBlock.PART) == 1 && world.getBlockState(pos.up(3)).getBlock() instanceof SignalTransmitterAntennaBlock && world.getBlockState(pos.up(3)).get(SignalTransmitterAntennaBlock.PART) == 2 && world.getBlockState(pos.up(4)).getBlock() instanceof SignalTransmitterAntennaBlock && world.getBlockState(pos.up(4)).get(SignalTransmitterAntennaBlock.PART) == 3;
    }

    private static int strength(World world, BlockPos pos) {
        int weakest = Integer.MAX_VALUE;
        for (int i = 1; i <= 4; i++) {
            BlockState state = world.getBlockState(pos.up(i));
            if (state.isOf(ModBlocks.ALUMINIUM_SIGNAL_TRANSMITTER_ANTENNA)) {
                weakest = Math.min(weakest, 1);
            } else if (state.isOf(ModBlocks.COPPER_SIGNAL_TRANSMITTER_ANTENNA)) {
                weakest = Math.min(weakest, 2);
            } else if (state.isOf(ModBlocks.GOLD_SIGNAL_TRANSMITTER_ANTENNA)) {
                weakest = Math.min(weakest, 3);
            } else if (state.isOf(ModBlocks.MITHRIL_SIGNAL_TRANSMITTER_ANTENNA)) {
                weakest = Math.min(weakest, 4);
            } else {
                return 0;
            }
        }
        return weakest;
    }

    private static int range(World world, BlockPos pos) {
        int weakest = Integer.MAX_VALUE;
        for (int i = 1; i <= 4; i++) {
            BlockState state = world.getBlockState(pos.up(i));
            if (state.isOf(ModBlocks.ALUMINIUM_SIGNAL_TRANSMITTER_ANTENNA)) {
                weakest = Math.min(weakest, 1);
            } else if (state.isOf(ModBlocks.COPPER_SIGNAL_TRANSMITTER_ANTENNA)) {
                weakest = Math.min(weakest, 2);
            } else if (state.isOf(ModBlocks.GOLD_SIGNAL_TRANSMITTER_ANTENNA)) {
                weakest = Math.min(weakest, 3);
            } else if (state.isOf(ModBlocks.MITHRIL_SIGNAL_TRANSMITTER_ANTENNA)) {
                weakest = Math.min(weakest, 4);
            } else {
                return 0;
            }
        }
        return weakest;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SignalTransmitterBlockEntity(pos, state);
    }
}