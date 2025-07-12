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
    public static final DirectionProperty FACING = FacingBlock.FACING;
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
            } else {
                entity = entities.get(0);
            }
            player.startRiding(entity);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        boolean thisWater = world.getFluidState(pos).isOf(Fluids.WATER);
        if (world.getBlockState(pos.north()).isOf(ModBlocks.BENCH)) {
            boolean water = world.getBlockState(pos.north()).get(WATERLOGGED);
            if (world.getBlockState(pos.north()).get(FACING).equals(Direction.WEST) && state.get(FACING).equals(Direction.WEST)) {
                if (world.getBlockState(pos.north()).get(BENCH) == 0) {
                    world.setBlockState(pos.north(), ModBlocks.BENCH.getDefaultState().with(FACING, Direction.WEST).with(BENCH, 3).with(WATERLOGGED, water));
                } else if (world.getBlockState(pos.north()).get(BENCH) != 3) {
                    world.setBlockState(pos.north(), ModBlocks.BENCH.getDefaultState().with(FACING, Direction.WEST).with(BENCH, 2).with(WATERLOGGED, water));
                }
                world.setBlockState(pos, ModBlocks.BENCH.getDefaultState().with(FACING, Direction.WEST).with(BENCH, 1).with(WATERLOGGED, thisWater));
            }
            if (world.getBlockState(pos.north()).get(FACING).equals(Direction.EAST) && state.get(FACING).equals(Direction.EAST)) {
                if (world.getBlockState(pos.north()).get(BENCH) == 0) {
                    world.setBlockState(pos.north(), ModBlocks.BENCH.getDefaultState().with(FACING, Direction.EAST).with(BENCH, 1).with(WATERLOGGED, water));
                } else if (world.getBlockState(pos.north()).get(BENCH) != 1) {
                    world.setBlockState(pos.north(), ModBlocks.BENCH.getDefaultState().with(FACING, Direction.EAST).with(BENCH, 2).with(WATERLOGGED, water));
                }
                world.setBlockState(pos, ModBlocks.BENCH.getDefaultState().with(FACING, Direction.EAST).with(BENCH, 3).with(WATERLOGGED, thisWater));
            }
        }
        if (world.getBlockState(pos.south()).isOf(ModBlocks.BENCH)) {
            boolean water = world.getBlockState(pos.south()).get(WATERLOGGED);
            if (world.getBlockState(pos.south()).get(FACING).equals(Direction.WEST) && state.get(FACING).equals(Direction.WEST)) {
                if (world.getBlockState(pos.south()).get(BENCH) == 0) {
                    world.setBlockState(pos.south(), ModBlocks.BENCH.getDefaultState().with(FACING, Direction.WEST).with(BENCH, 1).with(WATERLOGGED, water));
                } else if (world.getBlockState(pos.south()).get(BENCH) != 1) {
                    world.setBlockState(pos.south(), ModBlocks.BENCH.getDefaultState().with(FACING, Direction.WEST).with(BENCH, 2).with(WATERLOGGED, water));
                }
                world.setBlockState(pos, ModBlocks.BENCH.getDefaultState().with(FACING, Direction.WEST).with(BENCH, 3).with(WATERLOGGED, thisWater));
            }
            if (world.getBlockState(pos.south()).get(FACING).equals(Direction.EAST) && state.get(FACING).equals(Direction.EAST)) {
                if (world.getBlockState(pos.south()).get(BENCH) == 0) {
                    world.setBlockState(pos.south(), ModBlocks.BENCH.getDefaultState().with(FACING, Direction.EAST).with(BENCH, 3).with(WATERLOGGED, water));
                } else if (world.getBlockState(pos.south()).get(BENCH) != 3) {
                    world.setBlockState(pos.south(), ModBlocks.BENCH.getDefaultState().with(FACING, Direction.EAST).with(BENCH, 2).with(WATERLOGGED, water));
                }
                world.setBlockState(pos, ModBlocks.BENCH.getDefaultState().with(FACING, Direction.EAST).with(BENCH, 1).with(WATERLOGGED, thisWater));
            }
        }
        if (world.getBlockState(pos.west()).isOf(ModBlocks.BENCH)) {
            boolean water = world.getBlockState(pos.west()).get(WATERLOGGED);
            if (world.getBlockState(pos.west()).get(FACING).equals(Direction.NORTH) && state.get(FACING).equals(Direction.NORTH)) {
                if (world.getBlockState(pos.west()).get(BENCH) == 0) {
                    world.setBlockState(pos.west(), ModBlocks.BENCH.getDefaultState().with(FACING, Direction.NORTH).with(BENCH, 1).with(WATERLOGGED, water));
                } else if (world.getBlockState(pos.west()).get(BENCH) != 1) {
                    world.setBlockState(pos.west(), ModBlocks.BENCH.getDefaultState().with(FACING, Direction.NORTH).with(BENCH, 2).with(WATERLOGGED, water));
                }
                world.setBlockState(pos, ModBlocks.BENCH.getDefaultState().with(FACING, Direction.NORTH).with(BENCH, 3).with(WATERLOGGED, thisWater));
            }
            if (world.getBlockState(pos.west()).get(FACING).equals(Direction.SOUTH) && state.get(FACING).equals(Direction.SOUTH)) {
                if (world.getBlockState(pos.west()).get(BENCH) == 0) {
                    world.setBlockState(pos.west(), ModBlocks.BENCH.getDefaultState().with(FACING, Direction.SOUTH).with(BENCH, 3).with(WATERLOGGED, water));
                } else if (world.getBlockState(pos.west()).get(BENCH) != 3) {
                    world.setBlockState(pos.west(), ModBlocks.BENCH.getDefaultState().with(FACING, Direction.SOUTH).with(BENCH, 2).with(WATERLOGGED, water));
                }
                world.setBlockState(pos, ModBlocks.BENCH.getDefaultState().with(FACING, Direction.SOUTH).with(BENCH, 1).with(WATERLOGGED, thisWater));
            }
        }
        if (world.getBlockState(pos.east()).isOf(ModBlocks.BENCH)) {
            boolean water = world.getBlockState(pos.east()).get(WATERLOGGED);
            if (world.getBlockState(pos.east()).get(FACING).equals(Direction.NORTH) && state.get(FACING).equals(Direction.NORTH)) {
                if (world.getBlockState(pos.east()).get(BENCH) == 0) {
                    world.setBlockState(pos.east(), ModBlocks.BENCH.getDefaultState().with(FACING, Direction.NORTH).with(BENCH, 3).with(WATERLOGGED, water));
                } else if (world.getBlockState(pos.east()).get(BENCH) != 3) {
                    world.setBlockState(pos.east(), ModBlocks.BENCH.getDefaultState().with(FACING, Direction.NORTH).with(BENCH, 2).with(WATERLOGGED, water));
                }
                world.setBlockState(pos, ModBlocks.BENCH.getDefaultState().with(FACING, Direction.NORTH).with(BENCH, 1).with(WATERLOGGED, thisWater));
            }
            if (world.getBlockState(pos.east()).get(FACING).equals(Direction.SOUTH) && state.get(FACING).equals(Direction.SOUTH)) {
                if (world.getBlockState(pos.east()).get(BENCH) == 0) {
                    world.setBlockState(pos.east(), ModBlocks.BENCH.getDefaultState().with(FACING, Direction.SOUTH).with(BENCH, 1).with(WATERLOGGED, water));
                } else if (world.getBlockState(pos.east()).get(BENCH) != 1) {
                    world.setBlockState(pos.east(), ModBlocks.BENCH.getDefaultState().with(FACING, Direction.SOUTH).with(BENCH, 2).with(WATERLOGGED, water));
                }
                world.setBlockState(pos, ModBlocks.BENCH.getDefaultState().with(FACING, Direction.SOUTH).with(BENCH, 3).with(WATERLOGGED, thisWater));
            }
        } //Middle Part
        if (world.getBlockState(pos.north()).isOf(ModBlocks.BENCH) && world.getBlockState(pos.south()).isOf(ModBlocks.BENCH)) {
            if (world.getBlockState(pos.north()).get(FACING).equals(Direction.WEST) && world.getBlockState(pos.south()).get(FACING).equals(Direction.WEST) && state.get(FACING).equals(Direction.WEST)) {
                world.setBlockState(pos, ModBlocks.BENCH.getDefaultState().with(FACING, Direction.WEST).with(BENCH, 2).with(WATERLOGGED, thisWater));
            }
            if (world.getBlockState(pos.north()).get(FACING).equals(Direction.EAST) && world.getBlockState(pos.south()).get(FACING).equals(Direction.EAST) && state.get(FACING).equals(Direction.EAST)) {
                world.setBlockState(pos, ModBlocks.BENCH.getDefaultState().with(FACING, Direction.EAST).with(BENCH, 2).with(WATERLOGGED, thisWater));
            }
        }
        if (world.getBlockState(pos.west()).isOf(ModBlocks.BENCH) && world.getBlockState(pos.east()).isOf(ModBlocks.BENCH)) {
            if (world.getBlockState(pos.west()).get(FACING).equals(Direction.NORTH) && world.getBlockState(pos.east()).get(FACING).equals(Direction.NORTH) && state.get(FACING).equals(Direction.NORTH)) {
                world.setBlockState(pos, ModBlocks.BENCH.getDefaultState().with(FACING, Direction.NORTH).with(BENCH, 2).with(WATERLOGGED, thisWater));
            }
            if (world.getBlockState(pos.west()).get(FACING).equals(Direction.SOUTH) && world.getBlockState(pos.east()).get(FACING).equals(Direction.SOUTH) && state.get(FACING).equals(Direction.SOUTH)) {
                world.setBlockState(pos, ModBlocks.BENCH.getDefaultState().with(FACING, Direction.SOUTH).with(BENCH, 2).with(WATERLOGGED, thisWater));
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }
}
