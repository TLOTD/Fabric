package net.tlotd.compat.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.tlotd.recipe.IncubatonRecipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IncubatorDisplay extends BasicDisplay {

    public IncubatorDisplay(RecipeEntry<IncubatonRecipe> recipeEntry, RegistryWrapper.WrapperLookup registryLookup) {
        this(recipeEntry.value(), registryLookup);
    }

    private IncubatorDisplay(IncubatonRecipe recipe, RegistryWrapper.WrapperLookup registryLookup) {
        super(getInputList(recipe), List.of(EntryIngredients.of(recipe.getResult(registryLookup))));
    }

    private static List<EntryIngredient> getInputList(IncubatonRecipe recipe) {
        List<EntryIngredient> list = new ArrayList<>();
        DefaultedList<Ingredient> ingredients = recipe.getIngredients();
        for (int i = 0; i < 2; i++) {
            if (i < ingredients.size() && !ingredients.get(i).isEmpty()) {
                ItemStack[] matching = ingredients.get(i).getMatchingStacks();
                list.add(EntryIngredients.ofItemStacks(Arrays.asList(matching)));
            } else {
                list.add(EntryIngredient.empty());
            }
        }
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return IncubatorCategory.INCUBATING;
    }
}