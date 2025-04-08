package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.tlotd.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StickerBlock extends Block {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public static final DirectionProperty FACING = FacingBlock.FACING;

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getPlayerLookDirection())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
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
        builder.add(FACING, WATERLOGGED);
    }

    public StickerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    public static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(1.0, 4.0, 0.0, 15.0, 12.0, 0.1);
    public static final VoxelShape EAST_SHAPE = Block.createCuboidShape(15.9, 4.0, 1.0, 16.0, 12.0, 15.0);
    public static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(1.0, 4.0, 15.9, 15.0, 12.0, 16.0);
    public static final VoxelShape WEST_SHAPE = Block.createCuboidShape(0.0, 4.0, 1.0, 0.1, 12.0, 15.0);
    public static final VoxelShape DOWN_SHAPE = Block.createCuboidShape(1.0, 0.0, 4.0, 15.0, 0.1, 12.0);
    public static final VoxelShape UP_SHAPE = Block.createCuboidShape(1.0, 15.9, 4.0, 15.0, 16.0, 12.0);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case EAST -> EAST_SHAPE;
            case WEST -> WEST_SHAPE;
            case UP -> UP_SHAPE;
            case DOWN -> DOWN_SHAPE;
        };
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        if (Screen.hasShiftDown()) {
            if (stack.getItem() == ModBlocks.BW_STICKER.asItem()) {
                tooltip.add(Text.translatable("block.tlotd.bw_sticker.tooltip").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.bw_sticker.tooltip_2").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.bw_sticker.tooltip_3").formatted(Formatting.GRAY));
            } else {
                tooltip.add(Text.translatable("block.tlotd.tlotd_sticker.tooltip").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.tlotd_sticker.tooltip_2").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.tlotd_sticker.tooltip_3").formatted(Formatting.GRAY));
            }
        }
        super.appendTooltip(stack, world, tooltip, options);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
