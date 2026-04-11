package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.tlotd.block.ModBlocks;
import net.tlotd.block.enum_property.TallBlock;
import net.tlotd.block.enum_property.VerticalConnection;
import org.jetbrains.annotations.Nullable;

public class TallTileBlock extends Block {
    public static final EnumProperty<TallBlock> HALF = EnumProperty.of("type", TallBlock.class);

    public TallTileBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HALF, TallBlock.FULL));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF);
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
        boolean hasBelow = world.getBlockState(pos.down()).getBlock() instanceof TallTileBlock;
        TallBlock half = TallBlock.FULL;
        if (hasBelow) {
            if (!world.getBlockState(pos.down()).get(HALF).equals(TallBlock.UPPER)) {
                half = TallBlock.UPPER;
                world.setBlockState(pos.down(), world.getBlockState(pos.down()).with(HALF, TallBlock.LOWER), Block.NOTIFY_ALL);
            }
        }
        return this.getDefaultState().with(HALF, half);
    }
}