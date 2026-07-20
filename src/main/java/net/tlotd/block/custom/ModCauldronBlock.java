package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.Map;

public class ModCauldronBlock extends LeveledCauldronBlock {

    public ModCauldronBlock(AbstractBlock.Settings settings, Map<Item, CauldronBehavior> behaviorMap) {
        super(settings, precipitation -> false, behaviorMap);
    }

    @Override
    protected double getFluidHeight(BlockState state) {
        return (6.0D + state.get(LEVEL) * 3.0D) / 16.0D;
    }

    @Override
    protected boolean canBeFilledByDripstone(Fluid fluid) {
        return false;
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return Items.CAULDRON.getDefaultStack();
    }
}