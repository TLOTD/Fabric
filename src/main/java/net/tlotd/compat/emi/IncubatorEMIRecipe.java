package net.tlotd.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.recipe.IncubatonRecipe;

import java.util.Arrays;
import java.util.List;

public class IncubatorEMIRecipe implements EmiRecipe {

    private final IncubatonRecipe recipe;

    public IncubatorEMIRecipe(IncubatonRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return IncubatorEMICategory.CATEGORY;
    }

    @Override
    public Identifier getId() {
        return recipe.getId();
    }

    @Override
    public List<EmiIngredient> getInputs() {

        return recipe.getIngredients()
                .stream()
                .filter(i -> !i.isEmpty())
                .map(i -> EmiIngredient.of(
                        Arrays.stream(i.getMatchingStacks())
                                .map(EmiStack::of)
                                .toList()
                ))
                .toList();
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(
                EmiStack.of(recipe.getOutput(null))
        );
    }

    @Override
    public int getDisplayWidth() {
        return 98;
    }

    @Override
    public int getDisplayHeight() {
        return 26;
    }


    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(new Identifier(TLOTD.MOD_ID, "textures/gui/rei/incubator.png"), 0, 0, 98, 26, 4, 4);
        List<EmiIngredient> inputs = getInputs();
        int[][] slots = {
                {4,4},
                {40,4}
        };
        for (int i = 0; i < inputs.size(); i++) {
            widgets.addSlot(inputs.get(i), slots[i][0], slots[i][1]);
        }
        widgets.addSlot(EmiStack.of(recipe.getOutput(null)), 76, 4).recipeContext(this);
    }
}