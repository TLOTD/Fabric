package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import org.jetbrains.annotations.Nullable;

public class TallTileBlock extends Block {
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    public TallTileBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        DoubleBlockHalf half = DoubleBlockHalf.LOWER;
        BlockState below = ctx.getWorld().getBlockState(ctx.getBlockPos().down());
        if (below.getBlock() instanceof TallTileBlock) {
            if (below.get(HALF).equals(DoubleBlockHalf.LOWER)) {
                half = DoubleBlockHalf.UPPER;
            }
        }
        return this.getDefaultState().with(HALF, half);
    }
}