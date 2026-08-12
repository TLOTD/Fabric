package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.tlotd.block.ModBlocks;
import net.tlotd.world.SignalTrackingArray;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SignalTransmitterAntennaBlock extends Block {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final IntProperty PART = IntProperty.of("part", 0, 3);

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        int part = 0;
        BlockState below = world.getBlockState(pos.down());
        if (below.isOf(this)) {
            int belowPart = below.get(PART);
            if (belowPart < 3) {
                part = belowPart + 1;
            }
        }

        return getDefaultState().with(PART, part).with(WATERLOGGED, world.getFluidState(pos).isOf(Fluids.WATER));
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
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, PART);
    }

    public SignalTransmitterAntennaBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(WATERLOGGED, false).with(PART, 0));
    }

    public static final VoxelShape SHAPE_2 = VoxelShapes.union(Block.createCuboidShape(6, 0, 6, 10, 16, 10));

    public static final VoxelShape SHAPE_1 = VoxelShapes.union(Block.createCuboidShape(5, 0, 5, 11, 5, 11), Block.createCuboidShape(6, 5, 6, 10, 16, 10));

    public static final VoxelShape SHAPE_0 = VoxelShapes.union(Block.createCuboidShape(3, 0, 3, 13, 5, 13), Block.createCuboidShape(4, 5, 4, 12, 12, 12), Block.createCuboidShape(5, 12, 5, 11, 16, 11));

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(PART)) {
            case 0 -> SHAPE_0;
            case 1 -> SHAPE_1;
            case 2, 3 -> SHAPE_2;
            default -> VoxelShapes.fullCube();
        };
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (world.isClient()) return;
        for (int i = 1; i <= 4; i++) {
            BlockPos transmitterPos = pos.down(i);
            if (world.getBlockState(transmitterPos).isOf(ModBlocks.SIGNAL_TRANSMITTER)) {
                SignalTransmitterBlock.updateStationActive(world, transmitterPos);
                break;
            }
        }
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
        if (world.isClient()) return;
        for (int i = 1; i <= 4; i++) {
            BlockPos transmitterPos = pos.down(i);
            if (world.getBlockState(transmitterPos).isOf(ModBlocks.SIGNAL_TRANSMITTER)) {
                SignalTransmitterBlock.updateStationActive((World) world, transmitterPos);
                break;
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        int signalStrength = 0;
        if (stack.isOf(ModBlocks.ALUMINIUM_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
            signalStrength = 1;
        } else if (stack.isOf(ModBlocks.COPPER_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
            signalStrength = 2;
        } else if (stack.isOf(ModBlocks.GOLD_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
            signalStrength = 3;
        } else if (stack.isOf(ModBlocks.MITHRIL_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
            signalStrength = 4;
        }
        int signalRange = 0;
        if (stack.isOf(ModBlocks.ALUMINIUM_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
            signalRange = 1;
        } else if (stack.isOf(ModBlocks.COPPER_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
            signalRange = 2;
        } else if (stack.isOf(ModBlocks.GOLD_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
            signalRange = 3;
        } else if (stack.isOf(ModBlocks.MITHRIL_SIGNAL_TRANSMITTER_ANTENNA.asItem())) {
            signalRange = 4;
        }
        super.appendTooltip(stack, world, tooltip, options);
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("block.tlotd.signal_transmitter.signal_strength").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(" ").append(SignalTrackingArray.getStrenthText(signalStrength)));
        tooltip.add(Text.translatable("block.tlotd.signal_transmitter.signal_range").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(" ").append(SignalTrackingArray.getRangeText(signalRange)));
    }
}