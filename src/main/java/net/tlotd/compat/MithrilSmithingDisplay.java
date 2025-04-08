package net.tlotd.compat;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.tlotd.recipe.MithrilSmithingRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MithrilSmithingDisplay extends BasicDisplay {

    public MithrilSmithingDisplay(MithrilSmithingRecipe recipe) {
        super(getInputList(recipe), List.of(EntryIngredients.of(recipe.getOutput(null))));
    }

    private static List<EntryIngredient> getInputList(MithrilSmithingRecipe recipe) {
        if (recipe == null) return Collections.emptyList();
        List<EntryIngredient> input = new ArrayList<>(EntryIngredients.ofIngredients(recipe.getIngredients()));
        return input;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return MithrilSmithingCategory.MITHRIL_SMITHING;
    }
}
