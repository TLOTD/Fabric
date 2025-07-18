package net.tlotd.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.tlotd.block.ModBlocks;
import net.tlotd.config.ModConfigs;
import net.tlotd.item.ModItems;
import net.tlotd.util.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.tlotd.block.custom.AlienControlPanelBlock.HARVESTED;

@Mixin(AxeItem.class)
public abstract class WoodStrippingItemDrop {

    @Inject(method = "useOnBlock", at = @At("HEAD"))
    protected void injectOnUseMethod(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        if(!context.getWorld().isClient && ModConfigs.AXE_STRIPPING_DROPS_BARK && context.getWorld().getBlockState(context.getBlockPos()).isIn(ModTags.Blocks.LOGS_WITH_BARK)) {
            Block block = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
            ItemStack bark = ModItems.OAK_BARK.getDefaultStack();
            String name = block.getTranslationKey();
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
            } else if (name.contains("aether") && (name.contains("skyroot") || name.contains("golden_oak"))) { //modded logs
                bark = ModItems.SKYROOT_BARK.getDefaultStack();
            } else if (name.contains("alexscaves") && name.contains("pewen")) {
                bark = ModItems.PEWEN_BARK.getDefaultStack();
            } else if (name.contains("alexscaves") && name.contains("thornwood")) {
                bark = ModItems.THORNWOOD_BARK.getDefaultStack();
            } else if (name.contains("biomesoplenty") && name.contains("fir")) {
                bark = ModItems.FIR_BARK.getDefaultStack();
            } else if (name.contains("biomesoplenty") && name.contains("redwood")) {
                bark = ModItems.REDWOOD_BARK.getDefaultStack();
            } else if (name.contains("biomesoplenty") && name.contains("mahogany")) {
                bark = ModItems.MAHOGANY_BARK.getDefaultStack();
            } else if (name.contains("biomesoplenty") && name.contains("jacaranda")) {
                bark = ModItems.JACARANCA_BARK.getDefaultStack();
            } else if (name.contains("biomesoplenty") && name.contains("palm")) {
                bark = ModItems.PALM_BARK.getDefaultStack();
            } else if (name.contains("biomesoplenty") && name.contains("willow")) {
                bark = ModItems.WILLOW_BARK.getDefaultStack();
            } else if (name.contains("biomesoplenty") && name.contains("dead")) {
                bark = ModItems.DEAD_BARK.getDefaultStack();
            } else if (name.contains("biomesoplenty") && name.contains("magic")) {
                bark = ModItems.MAGIC_BARK.getDefaultStack();
            } else if (name.contains("biomesoplenty") && name.contains("umbran")) {
                bark = ModItems.UMBRAN_BARK.getDefaultStack();
            } else if (name.contains("biomesoplenty") && name.contains("hellbark")) {
                bark = ModItems.HELLBARK_BARK.getDefaultStack();
            } else if (name.contains("quark") && name.contains("ancient")) {
                bark = ModItems.ASHEN_BARK.getDefaultStack();
            } else if (name.contains("quark") && name.contains("azalea")) {
                bark = ModItems.AZALEA_BARK.getDefaultStack();
            } else if (name.contains("quark") && name.contains("blossom")) {
                bark = ModItems.TRUMPET_BARK.getDefaultStack();
            } else if (name.contains("thermal") && name.contains("rubberwood")) {
                bark = ModItems.RUBBERWOOD_BARK.getDefaultStack();
            } else if (name.contains("twilightforest") && name.contains("twilight_oak")) {
                bark = ModItems.TWILIGHT_OAK_BARK.getDefaultStack();
            } else if (name.contains("twilightforest") && name.contains("canopy")) {
                bark = ModItems.CANOPY_TREE_BARK.getDefaultStack();
            } else if (name.contains("twilightforest") && name.contains("mangrove")) {
                bark = ModItems.TWILIGHT_MANGROVE_BARK.getDefaultStack();
            } else if (name.contains("twilightforest") && name.contains("dark")) {
                bark = ModItems.DARKWOOD_BARK.getDefaultStack();
            } else if (name.contains("twilightforest") && name.contains("time")) {
                bark = ModItems.TIMEWOOD_BARK.getDefaultStack();
            } else if (name.contains("twilightforest") && name.contains("transformation")) {
                bark = ModItems.TRANSWOOD_BARK.getDefaultStack();
            } else if (name.contains("twilightforest") && name.contains("mining")) {
                bark = ModItems.MINEWOOD_BARK.getDefaultStack();
            } else if (name.contains("twilightforest") && name.contains("sorting")) {
                bark = ModItems.SORTINGWOOD_BARK.getDefaultStack();
            }
            Block.dropStack(context.getWorld(), context.getBlockPos(), bark);
        }
    }
}