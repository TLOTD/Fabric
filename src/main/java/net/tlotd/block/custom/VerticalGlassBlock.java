package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.tlotd.block.enum_property.VerticalConnection;

public class VerticalGlassBlock extends GlassBlock {

    public static final EnumProperty<VerticalConnection> CONNECTION = EnumProperty.of("connection", VerticalConnection.class);

    public VerticalGlassBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(CONNECTION, VerticalConnection.NONE));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CONNECTION);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();
        return updateConnection(world, pos);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP || direction == Direction.DOWN) {
            return updateConnection(world, pos);
        }
        return state;
    }

    private BlockState updateConnection(WorldAccess world, BlockPos pos) {
        boolean hasAbove = world.getBlockState(pos.up()).getBlock() instanceof VerticalGlassBlock;
        boolean hasBelow = world.getBlockState(pos.down()).getBlock() instanceof VerticalGlassBlock;
        VerticalConnection connection;
        if (hasAbove && hasBelow) {
            connection = VerticalConnection.MIDDLE;
        } else if (hasAbove) {
            connection = VerticalConnection.LOWER;
        } else if (hasBelow) {
            connection = VerticalConnection.UPPER;
        } else {
            connection = VerticalConnection.NONE;
        }
        return this.getDefaultState().with(CONNECTION, connection);
    }
}