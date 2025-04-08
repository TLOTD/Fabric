package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.tlotd.block.ModBlocks;
import net.tlotd.sound.ModSounds;

public class ComputerBlock extends Block {

    public static final IntProperty SCREEN = IntProperty.of("screen", 0, 1);

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public static final DirectionProperty FACING = FacingBlock.FACING;

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER))
                .with(SCREEN, 0);
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
        builder.add(FACING, WATERLOGGED, SCREEN);
    }

    public ComputerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false).with(SCREEN, 0));
    }

    public static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 0, 0, 16, 13, 12),
            Block.createCuboidShape(0, 0, 12.5, 16, 1, 16));
    public static final VoxelShape WEST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 0, 0, 12, 13, 16),
            Block.createCuboidShape(12.5, 0, 0, 16, 1, 16));
    public static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 0, 4, 16, 13, 16),
            Block.createCuboidShape(0, 0, 0, 16, 1, 3.5));
    public static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(4, 0, 0, 16, 13, 16),
            Block.createCuboidShape(0, 0, 0, 3.5, 1, 16));

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case EAST -> EAST_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> NORTH_SHAPE;
        };
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return ModBlocks.COMPUTER.asItem().getDefaultStack();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.isSneaking()) {
            if (state.getBlock().equals(ModBlocks.COMPUTER)) {
                world.setBlockState(pos, ModBlocks.COMPUTER_ON.getStateWithProperties(state));
            } else {
                world.setBlockState(pos, ModBlocks.COMPUTER.getStateWithProperties(state));
            }
            world.playSound(null, pos, ModSounds.BLOCK_COMPUTER_INTERACT, SoundCategory.BLOCKS, 1.0f, 1.0f);
            return ActionResult.SUCCESS;
        } else {
            if (state.getBlock().equals(ModBlocks.COMPUTER_ON)) {
                if (state.get(SCREEN) == 1) {
                    world.setBlockState(pos, ModBlocks.COMPUTER_ON.getStateWithProperties(state).with(SCREEN, 0));
                } else {
                    world.setBlockState(pos, ModBlocks.COMPUTER_ON.getStateWithProperties(state).with(SCREEN, state.get(SCREEN)+1));
                }
                world.playSound(null, pos, ModSounds.BLOCK_COMPUTER_INTERACT, SoundCategory.BLOCKS, 1.0f, 1.0f);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }
}