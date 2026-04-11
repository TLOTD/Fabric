package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.tlotd.block.enum_property.TallBlock;

public class DarkMetalLightBlock extends TallTileBlock {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

    public DarkMetalLightBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HALF, TallBlock.FULL).with(FACING, Direction.NORTH).with(LIT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF, FACING, LIT);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();
        BlockState state = this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());
        return updateConnection(state, world, pos);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        boolean powered;
        if (state.get(HALF) == TallBlock.UPPER) {
            powered = world.isReceivingRedstonePower(pos.down());
        } else {
            powered = world.isReceivingRedstonePower(pos);
        }
        state = state.with(LIT, powered);
        if (direction == Direction.UP || direction == Direction.DOWN) {
            state = updateConnection(state, world, pos);
        }
        return state;
    }

    public BlockState updateConnection(BlockState state, WorldAccess world, BlockPos pos) {
        boolean hasBelow = isSameLamp(world, pos.down(), state);
        boolean hasAbove = isSameLamp(world, pos.up(), state);
        TallBlock half = TallBlock.FULL;
        if (hasAbove) {
            if (!world.getBlockState(pos.up()).get(HALF).equals(TallBlock.LOWER)) {
                half = TallBlock.LOWER;
            }
        }
        if (hasBelow) {
            if (!world.getBlockState(pos.down()).get(HALF).equals(TallBlock.UPPER)) {
                half = TallBlock.UPPER;
            }
        }
        return state.with(HALF, half);
    }

    private boolean isSameLamp(WorldAccess world, BlockPos pos, BlockState self) {
        BlockState other = world.getBlockState(pos);
        return other.getBlock() instanceof DarkMetalLightBlock && other.get(FACING) == self.get(FACING);
    }
}