package net.tlotd.util;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;
import net.tlotd.block.ModBlocks;
import net.tlotd.effect.ModPotions;
import net.tlotd.item.ModItems;

public class BrewingRecipeBuilder {
    public static void registerBrewingRecipes() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.WATER, Ingredient.ofItems(Items.WHEAT), ModPotions.DRUNKENNESS_POTION);
            builder.registerPotionRecipe(Potions.WATER, Ingredient.ofItems(ModItems.URANIUM, ModItems.URANIUM_INGOT, ModBlocks.URANIUM_ORE, ModBlocks.DEEPSLATE_URANIUM_ORE, ModBlocks.RED_DEEPSLATE_URANIUM_ORE), ModPotions.IRRADIATED_POTION);
        });
    }
}