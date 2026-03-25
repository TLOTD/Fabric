package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PaneBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.tlotd.block.enum_property.VerticalConnection;

public class VerticalGlassPaneBlock extends PaneBlock {

    public static final EnumProperty<VerticalConnection> CONNECTION = EnumProperty.of("connection", VerticalConnection.class);

    public VerticalGlassPaneBlock(Settings settings) {
        super(settings);
        this.setDefaultState(
                this.getDefaultState().with(CONNECTION, VerticalConnection.NONE)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(CONNECTION);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState baseState = super.getPlacementState(ctx);
        return updateVerticalConnection(ctx.getWorld(), ctx.getBlockPos(), baseState);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        BlockState updated = super.getStateForNeighborUpdate(
                state, direction, neighborState, world, pos, neighborPos
        );
        if (direction == Direction.UP || direction == Direction.DOWN) {
            return updateVerticalConnection(world, pos, updated);
        }
        return updated;
    }

    private BlockState updateVerticalConnection(WorldAccess world, BlockPos pos, BlockState state) {
        boolean hasAbove = world.getBlockState(pos.up()).getBlock() instanceof VerticalGlassPaneBlock;
        boolean hasBelow = world.getBlockState(pos.down()).getBlock() instanceof VerticalGlassPaneBlock;
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
        return state.with(CONNECTION, connection);
    }
}