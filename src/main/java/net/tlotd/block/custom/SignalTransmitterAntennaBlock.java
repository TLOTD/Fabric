package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class SignalTransmitterAntennaBlock extends Block {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if (ctx.getWorld().getBlockState(ctx.getBlockPos().down()).isOf(this)) {
            return this.getDefaultState()
                    .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER))
                    .with(HALF, DoubleBlockHalf.UPPER);
        } else {
            return this.getDefaultState()
                    .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER))
                    .with(HALF, DoubleBlockHalf.LOWER);
        }
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
        builder.add(WATERLOGGED, HALF);
    }

    public SignalTransmitterAntennaBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(WATERLOGGED, false).with(HALF, DoubleBlockHalf.LOWER));
    }

    public static final VoxelShape UPPER_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 5.0, 11.0),
            Block.createCuboidShape(6.0, 5.0, 6.0, 10.0, 16.0, 10.0)
    );

    public static final VoxelShape LOWER_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 5.0, 13.0),
            Block.createCuboidShape(4.0, 5.0, 4.0, 12.0, 12.0, 12.0),
            Block.createCuboidShape(5.0, 12.0, 5.0, 11.0, 16.0, 11.0)
    );

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(HALF).equals(DoubleBlockHalf.LOWER)) {
            return LOWER_SHAPE;
        } else return UPPER_SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
