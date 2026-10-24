package net.tlotd.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;

public class ExtinguishedWallTorchBlock extends WallTorchBlock {

    public ExtinguishedWallTorchBlock(SimpleParticleType simpleParticleType, Settings settings) {
        super(simpleParticleType, settings);
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        if (state.isOf(ModBlocks.EXTINGUISHED_SOUL_WALL_TORCH)) {
            return Items.SOUL_TORCH.getDefaultStack();
        } else if (state.isOf(ModBlocks.EXTINGUISHED_COPPER_WALL_TORCH)) {
            return ModItems.COPPER_TORCH.getDefaultStack();
        } else if (state.isOf(ModBlocks.EXTINGUISHED_SULFUR_WALL_TORCH)) {
            return ModItems.SULFUR_TORCH.getDefaultStack();
        } else return Items.TORCH.getDefaultStack();
    }
}