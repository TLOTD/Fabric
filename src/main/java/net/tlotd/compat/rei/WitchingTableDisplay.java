package net.tlotd.compat.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.util.collection.DefaultedList;
import net.tlotd.recipe.WitchingRecipe;
import net.tlotd.util.ModTags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WitchingTableDisplay extends BasicDisplay {

    public WitchingTableDisplay(WitchingRecipe recipe) {
        super(getInputList(recipe), List.of(EntryIngredients.of(recipe.getOutput(null))));
    }

    private static List<EntryIngredient> getInputList(WitchingRecipe recipe) {
        List<EntryIngredient> list = new ArrayList<>();
        DefaultedList<Ingredient> ingredients = recipe.getIngredients();
        for (int i = 0; i < 9; i++) {
            if (i < ingredients.size() && !ingredients.get(i).isEmpty()) {
                ItemStack[] matching = ingredients.get(i).getMatchingStacks();
                list.add(EntryIngredients.ofItemStacks(Arrays.asList(matching)));
            } else {
                list.add(EntryIngredient.empty());
            }
        }
        List<ItemStack> stacks = Registries.ITEM.stream().filter(item -> (item.getDefaultStack().isIn(ModTags.Items.BLOOD_BOTTLES) || item.getDefaultStack().isIn(ModTags.Items.BLOOD_BUCKETS))).map(ItemStack::new).toList();
        list.add(EntryIngredients.ofItemStacks(stacks));
        list.add(EntryIngredient.empty());
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return WitchingTableCategory.WITCHING;
    }
}
