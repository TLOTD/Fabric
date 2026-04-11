package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
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
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.tlotd.block.ModBlocks;
import net.tlotd.entity.ModEntities;
import net.tlotd.entity.custom.SeatEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BenchBlock extends Block {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final IntProperty BENCH = IntProperty.of("bench", 0,3);

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER))
                .with(BENCH, 0);

    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
                                                WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        if (!world.isClient()) {
            BlockState newState = updateBenchState(world, pos, state);
            if (newState != state) {
                world.setBlockState(pos, newState, Block.NOTIFY_ALL);
            }
        }

        return state;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, BENCH);
    }

    public BenchBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false).with(BENCH, 0));
    }

    private static final VoxelShape BASE_SHAPE = Block.createCuboidShape(0,0,0,16,8,16);

    public static final VoxelShape WEST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 8, 0, 1, 21, 16),
            BASE_SHAPE
    );
    public static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 8, 0, 16, 21, 1),
            BASE_SHAPE
    );
    public static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(15, 8, 0, 16, 21, 16),
            BASE_SHAPE
    );
    public static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 8, 15, 16, 21, 16),
            BASE_SHAPE
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
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient){
            Entity entity = null;
            List<SeatEntity> entities = world.getEntitiesByType(ModEntities.SEAT, new Box(pos), chair -> true);
            if(entities.isEmpty()) {
                entity = ModEntities.SEAT.spawn((ServerWorld) world, pos, SpawnReason.TRIGGERED);
                if (entity != null) {
                    entity.setPosition(
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5
                    );
                }
            } else {
                entity = entities.get(0);
            }
            player.startRiding(entity);
        }
        return ActionResult.SUCCESS;
    }

    private BlockState updateBenchState(WorldAccess world, BlockPos pos, BlockState state) {
        Direction facing = state.get(FACING);
        boolean leftConnected = false;
        boolean rightConnected = false;
        Direction leftDir = facing.rotateYCounterclockwise();
        Direction rightDir = facing.rotateYClockwise();
        BlockState leftState = world.getBlockState(pos.offset(leftDir));
        BlockState rightState = world.getBlockState(pos.offset(rightDir));
        if (leftState.isOf(ModBlocks.BENCH) && leftState.get(FACING) == facing) {
            leftConnected = true;
        }
        if (rightState.isOf(ModBlocks.BENCH) && rightState.get(FACING) == facing) {
            rightConnected = true;
        }
        int benchType;
        if (leftConnected && rightConnected) {
            benchType = 2;
        } else if (leftConnected) {
            benchType = 3;
        } else if (rightConnected) {
            benchType = 1;
        } else {
            benchType = 0;
        }
        return state.with(BENCH, benchType);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient) {
            BlockState newState = updateBenchState(world, pos, state);
            world.setBlockState(pos, newState, Block.NOTIFY_ALL);
            for (Direction dir : Direction.Type.HORIZONTAL) {
                BlockPos neighborPos = pos.offset(dir);
                BlockState neighbor = world.getBlockState(neighborPos);
                if (neighbor.isOf(ModBlocks.BENCH)) {
                    world.setBlockState(neighborPos, updateBenchState(world, neighborPos, neighbor), Block.NOTIFY_ALL);
                }
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }
}
