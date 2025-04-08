package net.tlotd.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.tlotd.block.ModBlocks;
import net.tlotd.config.ModConfigs;
import net.tlotd.item.ModItems;
import net.tlotd.util.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AxeItem.class)
public abstract class WoodStrippingItemDrop {

    @Inject(method = "useOnBlock", at = @At("HEAD"))
    protected void injectOnUseMethod(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        if(!context.getWorld().isClient && ModConfigs.AXE_STRIPPING_DROPS_BARK && context.getWorld().getBlockState(context.getBlockPos()).isIn(ModTags.Blocks.LOGS_WITH_BARK)) {
            Block block = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
            ItemStack bark = ModItems.OAK_BARK.getDefaultStack();
            if (block == Blocks.SPRUCE_LOG || block == Blocks.SPRUCE_WOOD) {
                bark = ModItems.SPRUCE_BARK.getDefaultStack();
            } else if (block == Blocks.BIRCH_LOG || block == Blocks.BIRCH_WOOD) {
                bark = ModItems.BIRCH_BARK.getDefaultStack();
            } else if (block == Blocks.JUNGLE_LOG || block == Blocks.JUNGLE_WOOD) {
                bark = ModItems.JUNGLE_BARK.getDefaultStack();
            } else if (block == Blocks.ACACIA_LOG || block == Blocks.ACACIA_WOOD) {
                bark = ModItems.ACACIA_BARK.getDefaultStack();
            } else if (block == Blocks.DARK_OAK_LOG || block == Blocks.DARK_OAK_WOOD) {
                bark = ModItems.DARK_OAK_BARK.getDefaultStack();
            } else if (block == Blocks.MANGROVE_LOG || block == Blocks.MANGROVE_WOOD) {
                bark = ModItems.MANGROVE_BARK.getDefaultStack();
            } else if (block == Blocks.CHERRY_LOG || block == Blocks.CHERRY_WOOD) {
                bark = ModItems.CHERRY_BARK.getDefaultStack();
            } else if (block == ModBlocks.GINKGO_LOG || block == ModBlocks.GINKGO_WOOD) {
                bark = ModItems.GINKGO_BARK.getDefaultStack();
            }
            Block.dropStack(context.getWorld(), context.getBlockPos(), bark);
        }
    }
}