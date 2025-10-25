package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AlienControlPanelBlock extends Block {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty HARVESTED = BooleanProperty.of("harvested");

    public AlienControlPanelBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false).with(HARVESTED, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER))
                .with(HARVESTED, false);

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
        builder.add(FACING, WATERLOGGED, HARVESTED);
    }

    public static final VoxelShape BASE_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 0, 0, 16, 2, 16),
            Block.createCuboidShape(4, 2, 4, 12, 11, 12)
    );

    public static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            BASE_SHAPE,
            Block.createCuboidShape(0, 12, 0, 16, 16, 4),
            Block.createCuboidShape(0, 10, 4, 16, 14, 8),
            Block.createCuboidShape(0, 8, 8, 16, 12, 12),
            Block.createCuboidShape(0, 6, 12, 16, 10, 16)
    );

    public static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
            BASE_SHAPE,
            Block.createCuboidShape(0, 6, 0, 16, 10, 4),
            Block.createCuboidShape(0, 8, 4, 16, 12, 8),
            Block.createCuboidShape(0, 10, 8, 16, 14, 12),
            Block.createCuboidShape(0, 12, 12, 16, 16, 16)
    );

    public static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            BASE_SHAPE,
            Block.createCuboidShape(0, 6, 0, 4, 10, 16),
            Block.createCuboidShape(4, 8, 0, 8, 12, 16),
            Block.createCuboidShape(8, 10, 0, 12, 14, 16),
            Block.createCuboidShape(12, 12, 0, 16, 16, 16)
    );

    public static final VoxelShape WEST_SHAPE = VoxelShapes.union(
            BASE_SHAPE,
            Block.createCuboidShape(0, 12, 0, 4, 16, 16),
            Block.createCuboidShape(4, 10, 0, 8, 14, 16),
            Block.createCuboidShape(8, 8, 0, 12, 12, 16),
            Block.createCuboidShape(12, 6, 0, 16, 10, 16)
    );

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case EAST -> EAST_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> BASE_SHAPE;
        };
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("info.tlotd.not_yet_implemented").formatted(Formatting.RED));
        super.appendTooltip(stack, world, tooltip, options);
    }
}