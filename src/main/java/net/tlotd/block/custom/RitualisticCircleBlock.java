package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.tlotd.block.ModBlocks;

public class RitualisticCircleBlock extends Block {

    public static final IntProperty STATE = IntProperty.of("state", 0, 8);

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STATE);
    }

    public RitualisticCircleBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(STATE, 0));
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return ModBlocks.FANCY_CHARRED_PLANKS.asItem().getDefaultStack();
    }
}