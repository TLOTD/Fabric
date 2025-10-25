package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
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
import net.tlotd.sound.ModSounds;
import net.tlotd.util.ModTags;
import net.tlotd.world.ModGlobalState;

public class ElevatorControllerBlock extends Block {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty BROKEN = BooleanProperty.of("broken");

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER))
                .with(BROKEN, false);
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
        builder.add(FACING, WATERLOGGED, BROKEN);
    }

    public ElevatorControllerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false).with(BROKEN, false));
    }

    private static final VoxelShape BASE_SHAPE = Block.createCuboidShape(0,15,0,16,16,16);

    public static final VoxelShape WEST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0.0, 0.0, 0.0, 1.0, 15.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 15.0, 1.0),
            Block.createCuboidShape(0.0, 0.0, 15.0, 16.0, 15.0, 16.0),
            BASE_SHAPE
    );
    public static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0.0, 0.0, 0.0, 1.0, 15.0, 16.0),
            Block.createCuboidShape(15.0, 0.0, 0.0, 16.0, 15.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 15.0, 1.0),
            BASE_SHAPE
    );
    public static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(15.0, 0.0, 0.0, 16.0, 15.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 15.0, 1.0),
            Block.createCuboidShape(0.0, 0.0, 15.0, 16.0, 15.0, 16.0),
            BASE_SHAPE
    );
    public static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0.0, 0.0, 0.0, 1.0, 15.0, 16.0),
            Block.createCuboidShape(15.0, 0.0, 0.0, 16.0, 15.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 15.0, 16.0, 15.0, 16.0),
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
        ItemStack stack = player.getStackInHand(hand);
        if (!state.get(BROKEN)) {
            if (!world.isClient) {
                int max_distance = 0;
                if (player != null && player.getServer() != null) {
                    ModGlobalState globalState = ModGlobalState.get(player.getServer());
                    max_distance = globalState.elevatorMaxDistance();
                }
                if (player.getPos().add(0,1,0).isInRange(pos.toCenterPos(), 0.5)) {
                    int search_y;
                    if (player.isSneaking()) {
                        for(search_y = -2; search_y>= -max_distance-1; search_y--){
                            if((world.getBlockState(pos.add(0, search_y,0)).isIn(ModTags.Blocks.ELEVATOR_BASES)) && (world.getBlockState(pos.add(0,1+search_y,0)).isIn(ModTags.Blocks.ELEVATOR_CONTROLLERS))) {
                                player.teleport(player.getPos().getX(), player.getPos().getY()+search_y+1, player.getPos().getZ());
                                world.playSound(null, pos.add(0, search_y,0), ModSounds.BLOCK_ELEVATOR_PLING, SoundCategory.BLOCKS, 1.0f, 1.0f);
                                break;
                            }
                        }
                    } else {
                        for(search_y = 0; search_y<=max_distance-1; search_y++){
                            if((world.getBlockState(pos.add(0, search_y,0)).isIn(ModTags.Blocks.ELEVATOR_BASES)) && (world.getBlockState(pos.add(0,1+search_y,0)).isIn(ModTags.Blocks.ELEVATOR_CONTROLLERS))) {
                                player.teleport(player.getPos().getX(), player.getPos().getY()+search_y+1, player.getPos().getZ());
                                world.playSound(null, pos.add(0, search_y,0), ModSounds.BLOCK_ELEVATOR_PLING, SoundCategory.BLOCKS, 1.0f, 1.0f);
                                break;
                            }
                        }
                    }
                }
            }
            return ActionResult.SUCCESS;
        } else if (stack.isIn(ModTags.Items.CIRCUIT_BOARDS)) {
            if (!world.isClient) {
                stack.decrement(1);
                world.setBlockState(pos, state.with(BROKEN, false));
                world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 0.5f, 1.0f);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}