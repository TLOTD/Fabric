package net.tlotd.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;
import net.tlotd.util.ModTags;
import net.tlotd.world.ModGlobalState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;
import java.util.function.Supplier;

@Mixin(AxeItem.class)
public abstract class WoodStrippingItemDrop {

    private static Map<Block, Supplier<ItemStack>> BARK_BY_BLOCK = null;
    private static List<Pair<String, Supplier<ItemStack>>> MODDED_BARK_KEYWORDS = null;

    @Inject(method = "useOnBlock", at = @At("HEAD"))
    protected void injectOnUseMethod(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        if (player == null || world.isClient) return;
        ModGlobalState globalState = ModGlobalState.get(player.getServer());
        if (!globalState.strippingDropsBark()) return;
        BlockState state = world.getBlockState(context.getBlockPos());
        Block block = state.getBlock();
        if (!state.isIn(ModTags.Blocks.LOGS_WITH_BARK)) return;
        ensureMappingsInitialized();
        ItemStack bark = getBarkForBlock(block);
        if (bark == null) return;
        Block.dropStack(world, context.getBlockPos(), bark);
    }

    private static void ensureMappingsInitialized() {
        if (BARK_BY_BLOCK != null) return;

        BARK_BY_BLOCK = new HashMap<>();
        MODDED_BARK_KEYWORDS = new ArrayList<>();

        BARK_BY_BLOCK.put(Blocks.OAK_LOG, ModItems.OAK_BARK::getDefaultStack);
        BARK_BY_BLOCK.put(Blocks.OAK_WOOD, ModItems.OAK_BARK::getDefaultStack);
        BARK_BY_BLOCK.put(Blocks.SPRUCE_LOG, ModItems.SPRUCE_BARK::getDefaultStack);
        BARK_BY_BLOCK.put(Blocks.SPRUCE_WOOD, ModItems.SPRUCE_BARK::getDefaultStack);
        BARK_BY_BLOCK.put(Blocks.BIRCH_LOG, ModItems.BIRCH_BARK::getDefaultStack);
        BARK_BY_BLOCK.put(Blocks.BIRCH_WOOD, ModItems.BIRCH_BARK::getDefaultStack);
        BARK_BY_BLOCK.put(Blocks.JUNGLE_LOG, ModItems.JUNGLE_BARK::getDefaultStack);
        BARK_BY_BLOCK.put(Blocks.JUNGLE_WOOD, ModItems.JUNGLE_BARK::getDefaultStack);
        BARK_BY_BLOCK.put(Blocks.ACACIA_LOG, ModItems.ACACIA_BARK::getDefaultStack);
        BARK_BY_BLOCK.put(Blocks.ACACIA_WOOD, ModItems.ACACIA_BARK::getDefaultStack);
        BARK_BY_BLOCK.put(Blocks.DARK_OAK_LOG, ModItems.DARK_OAK_BARK::getDefaultStack);
        BARK_BY_BLOCK.put(Blocks.DARK_OAK_WOOD, ModItems.DARK_OAK_BARK::getDefaultStack);
        BARK_BY_BLOCK.put(Blocks.MANGROVE_LOG, ModItems.MANGROVE_BARK::getDefaultStack);
        BARK_BY_BLOCK.put(Blocks.MANGROVE_WOOD, ModItems.MANGROVE_BARK::getDefaultStack);
        BARK_BY_BLOCK.put(Blocks.CHERRY_LOG, ModItems.CHERRY_BARK::getDefaultStack);
        BARK_BY_BLOCK.put(Blocks.CHERRY_WOOD, ModItems.CHERRY_BARK::getDefaultStack);

        BARK_BY_BLOCK.put(ModBlocks.GINKGO_LOG, ModItems.GINKGO_BARK::getDefaultStack);
        BARK_BY_BLOCK.put(ModBlocks.GINKGO_WOOD, ModItems.GINKGO_BARK::getDefaultStack);

        BARK_BY_BLOCK.put(ModBlocks.YELLOW_WALLPAPERED_WALL_WITH_BASEBOARD, ModItems.BACKSHROOM_BASEBOARD::getDefaultStack);
        BARK_BY_BLOCK.put(ModBlocks.YELLOW_WALLPAPERED_WALL, ModItems.YELLOW_WALLPAPER::getDefaultStack);

        MODDED_BARK_KEYWORDS.add(Pair.of("aether:skyroot", ModItems.SKYROOT_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("aether:golden_oak", ModItems.SKYROOT_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("alexscaves:pewen", ModItems.PEWEN_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("alexscaves:thornwood", ModItems.THORNWOOD_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("biomesoplenty:fir", ModItems.FIR_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("biomesoplenty:pine", ModItems.PINE_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("biomesoplenty:maple", ModItems.MAPLE_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("biomesoplenty:redwood", ModItems.REDWOOD_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("biomesoplenty:mahogany", ModItems.MAHOGANY_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("biomesoplenty:jacaranda", ModItems.JACARANCA_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("biomesoplenty:palm", ModItems.PALM_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("biomesoplenty:willow", ModItems.WILLOW_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("biomesoplenty:dead", ModItems.DEAD_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("biomesoplenty:magic", ModItems.MAGIC_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("biomesoplenty:umbran", ModItems.UMBRAN_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("biomesoplenty:hellbark", ModItems.HELLBARK_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("biomesoplenty:empyreal", ModItems.EMPYREAL_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("quark:ancient", ModItems.ASHEN_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("quark:azalea", ModItems.AZALEA_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("quark:blossom", ModItems.TRUMPET_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("thermal:rubberwood", ModItems.RUBBERWOOD_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("twilightforest:twilight_oak", ModItems.TWILIGHT_OAK_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("twilightforest:canopy", ModItems.CANOPY_TREE_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("twilightforest:mangrove", ModItems.TWILIGHT_MANGROVE_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("twilightforest:dark", ModItems.DARKWOOD_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("twilightforest:time", ModItems.TIMEWOOD_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("twilightforest:transformation", ModItems.TRANSWOOD_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("twilightforest:mining", ModItems.MINEWOOD_BARK::getDefaultStack));
        MODDED_BARK_KEYWORDS.add(Pair.of("twilightforest:sorting", ModItems.SORTINGWOOD_BARK::getDefaultStack));
    }

    private static ItemStack getBarkForBlock(Block block) {
        Supplier<ItemStack> direct = BARK_BY_BLOCK.get(block);
        if (direct != null) return direct.get();
        String key = block.getTranslationKey().toLowerCase(Locale.ROOT);
        for (Pair<String, Supplier<ItemStack>> entry : MODDED_BARK_KEYWORDS) {
            if (key.contains(entry.getFirst())) return entry.getSecond().get();
        }
        return null;
    }
}