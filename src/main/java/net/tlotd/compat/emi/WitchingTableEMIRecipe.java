package net.tlotd.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.tlotd.TLOTD;
import net.tlotd.recipe.WitchingRecipe;
import net.tlotd.util.ModTags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WitchingTableEMIRecipe implements EmiRecipe {

    private final WitchingRecipe recipe;

    public WitchingTableEMIRecipe(WitchingRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return WitchingTableEMICategory.CATEGORY;
    }

    @Override
    public Identifier getId() {
        return recipe.getId();
    }

    @Override
    public List<EmiIngredient> getInputs() {
        List<EmiIngredient> list = new ArrayList<>();
        DefaultedList<Ingredient> ingredients = recipe.getIngredients();
        for (int i = 0; i < 9; i++) {
            if (i < ingredients.size() && !ingredients.get(i).isEmpty()) {
                list.add(EmiIngredient.of(Arrays.stream(ingredients.get(i).getMatchingStacks()).map(EmiStack::of).toList()));
            } else {
                list.add(EmiStack.EMPTY);
            }
        }
        List<EmiStack> bloodContainers = Registries.ITEM.stream().filter(item -> item.getDefaultStack().isIn(ModTags.Items.BLOOD_BOTTLES) || item.getDefaultStack().isIn(ModTags.Items.BLOOD_BUCKETS)).map(item -> EmiStack.of(new ItemStack(item))).toList();
        list.add(EmiIngredient.of(bloodContainers));
        list.add(EmiStack.EMPTY);
        return list;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(
                EmiStack.of(recipe.getOutput(null))
        );
    }

    @Override
    public int getDisplayWidth() {
        return 170;
    }

    @Override
    public int getDisplayHeight() {
        return 66;
    }


    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(new Identifier(TLOTD.MOD_ID, "textures/gui/rei/witching_table.png"), 0, 0, 170, 66, 4, 4);
        List<EmiIngredient> inputs = getInputs();
        int[][] slots = {
                {62,24},
                {43,5},
                {62,4},
                {81,5},
                {42,24},
                {82,24},
                {43,43},
                {62,44},
                {81,43},

                {22,4},
                {22,44}
        };
        for (int i = 0; i < inputs.size(); i++) {
            widgets.addSlot(inputs.get(i), slots[i][0], slots[i][1]);
        }
        widgets.addSlot(EmiStack.of(recipe.getOutput(null)), 148, 24).recipeContext(this);
    }
}