package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class ShelfMushroomBlock extends Block implements Fertilizable {
    public static final IntProperty AGE = IntProperty.of("age",0,1);
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public ShelfMushroomBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(AGE, 0));
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos blockPos = pos.offset(direction.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isSideSolidFullSquare(world, blockPos, direction);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        WorldView worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        Direction[] directions = ctx.getPlacementDirections();
        for(Direction direction : directions) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction2 = direction.getOpposite();
                blockState = blockState.with(FACING, direction2);
                if (blockState.canPlaceAt(worldView, blockPos)) {
                    return blockState;
                }
            }
        }
        return null;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : state;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, AGE);
    }

    public static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0, 7, 3, 7, 10, 13);
    public static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(3, 7, 0, 13, 10, 7);
    public static final VoxelShape WEST_SHAPE = Block.createCuboidShape(9, 7, 3, 16, 10, 13);
    public static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(3, 7, 9, 13, 10, 16);

    public static final VoxelShape EAST_SHAPE_BIG = Block.createCuboidShape(0, 6, 1, 10, 11, 15);
    public static final VoxelShape SOUTH_SHAPE_BIG = Block.createCuboidShape(1, 6, 0, 15, 11, 10);
    public static final VoxelShape WEST_SHAPE_BIG = Block.createCuboidShape(6, 6, 1, 16, 11, 15);
    public static final VoxelShape NORTH_SHAPE_BIG = Block.createCuboidShape(1, 6, 6, 15, 11, 16);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(AGE) == 0) {
            return switch (state.get(FACING)) {
                case EAST -> EAST_SHAPE;
                case SOUTH -> SOUTH_SHAPE;
                case WEST -> WEST_SHAPE;
                default -> NORTH_SHAPE;
            };
        } else {
            return switch (state.get(FACING)) {
                case EAST -> EAST_SHAPE_BIG;
                case SOUTH -> SOUTH_SHAPE_BIG;
                case WEST -> WEST_SHAPE_BIG;
                default -> NORTH_SHAPE_BIG;
            };
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return state.get(AGE).equals(0);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return (double)world.random.nextFloat() < 0.45;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        growUp(state, world, pos);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        growUp(state, world, pos);
    }

    private void growUp(BlockState state, ServerWorld world, BlockPos pos) {
        if (state.get(AGE).equals(0)) {
            world.setBlockState(pos, state.with(AGE, 1));
        }
    }

    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        super.onLandedUpon(world, state, pos, entity, fallDistance * 0.5F);
    }

    public void onEntityLand(BlockView world, Entity entity) {
        if (entity.bypassesLandingEffects()) {
            super.onEntityLand(world, entity);
        } else {
            this.bounceEntity(entity);
        }
    }

    private void bounceEntity(Entity entity) {
        Vec3d vec3d = entity.getVelocity();
        if (vec3d.y < (double)0.0F) {
            double d = entity instanceof LivingEntity ? (double)1.0F : 0.8;
            entity.setVelocity(vec3d.x, -vec3d.y * (double)0.66F * d, vec3d.z);
        }
    }
}
