package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
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
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.tlotd.block.entity.GarbageCanBlockEntity;
import net.tlotd.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

public class GarbageCanBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
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
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    public GarbageCanBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(WATERLOGGED, false));
    }

    private static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2,0,2,14,12,14),

            Block.createCuboidShape(2, 12, 2, 3, 13, 14),
            Block.createCuboidShape(2, 12, 2, 14, 13, 3),
            Block.createCuboidShape(2, 12, 13, 14, 13, 14),
            Block.createCuboidShape(13, 12, 2, 14, 13, 14),

            Block.createCuboidShape(1, 13, 1, 3, 16, 15),
            Block.createCuboidShape(1, 13, 1, 15, 16, 3),
            Block.createCuboidShape(1, 13, 13, 15, 16, 15),
            Block.createCuboidShape(13, 13, 1, 15, 16, 15)
    );

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GarbageCanBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!world.isClient && blockEntity instanceof GarbageCanBlockEntity garbageCanBlockEntity) {
            if (player.isSneaking()) {
                if (player.getInventory().getEmptySlot() == -1) {
                    Block.dropStack(world, pos.up(), garbageCanBlockEntity.getStack(1));
                } else {
                    player.setStackInHand(hand, garbageCanBlockEntity.getStack(1));
                }
                garbageCanBlockEntity.setStack(1, garbageCanBlockEntity.getStack(2));
                garbageCanBlockEntity.setStack(2, garbageCanBlockEntity.getStack(3));
                garbageCanBlockEntity.setStack(3, ItemStack.EMPTY);
                world.playSound(null,pos, ModSounds.BLOCK_GARBAGE_CAN_USED, SoundCategory.BLOCKS, 1.0f, 1.0f);
            } else if (!stack.isEmpty()) {
                garbageCanBlockEntity.setStack(0, stack);
                player.setStackInHand(hand, ItemStack.EMPTY);
                world.playSound(null,pos, ModSounds.BLOCK_GARBAGE_CAN_USED, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof GarbageCanBlockEntity) {
                ItemScatterer.spawn(world, pos, (GarbageCanBlockEntity)blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }
}