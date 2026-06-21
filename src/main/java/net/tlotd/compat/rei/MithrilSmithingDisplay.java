package net.tlotd.compat.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.collection.DefaultedList;
import net.tlotd.recipe.MithrilSmithingRecipe;
import net.tlotd.util.ItemHeatHelper;
import net.tlotd.util.ModTags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MithrilSmithingDisplay extends BasicDisplay {

    public MithrilSmithingDisplay(MithrilSmithingRecipe recipe) {
        super(getInputList(recipe), List.of(EntryIngredients.of(recipe.getOutput(null))));
    }

    private static List<EntryIngredient> getInputList(MithrilSmithingRecipe recipe) {
        List<EntryIngredient> list = new ArrayList<>();
        DefaultedList<Ingredient> ingredients = recipe.getIngredients();
        for (int i = 0; i < 6; i++) {
            if (i < ingredients.size() && !ingredients.get(i).isEmpty()) {
                ItemStack[] matching = ingredients.get(i).getMatchingStacks();
                List<ItemStack> converted = Arrays.stream(matching)
                        .map(stack -> {
                            if (ItemHeatHelper.getSmithingTemperature(stack) > 0) {
                                ItemStack heatedStack = new ItemStack(stack.copy().getItem(), stack.getCount());
                                ItemHeatHelper.setTemperature(heatedStack, ItemHeatHelper.getSmithingTemperature(heatedStack));
                                return heatedStack;
                            }
                            return stack.copy();
                        })
                        .toList();
                list.add(EntryIngredients.ofItemStacks(converted));
            } else {
                list.add(EntryIngredient.empty());
            }
        }
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return MithrilSmithingCategory.MITHRIL_METALWORKING;
    }
}
