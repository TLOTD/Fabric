package net.tlotd.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;

public class ExtinguishedWallTorchBlock extends WallTorchBlock {
    public ExtinguishedWallTorchBlock(Settings settings, ParticleEffect particleEffect) {
        super(settings, particleEffect);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        if (state.isOf(ModBlocks.EXTINGUISHED_SOUL_WALL_TORCH)) {
            return Items.SOUL_TORCH.getDefaultStack();
        } else if (state.isOf(ModBlocks.EXTINGUISHED_COPPER_WALL_TORCH)) {
            return ModItems.COPPER_TORCH.getDefaultStack();
        } else if (state.isOf(ModBlocks.EXTINGUISHED_SULFUR_WALL_TORCH)) {
            return ModItems.SULFUR_TORCH.getDefaultStack();
        } else return Items.TORCH.getDefaultStack();
    }
}