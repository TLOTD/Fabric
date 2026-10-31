package net.tlotd.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.recipe.IncubatonRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class IncubatorEMIRecipe implements EmiRecipe {

    private final RecipeEntry<IncubatonRecipe> recipeEntry;
    private final IncubatonRecipe recipe;
    private final RegistryWrapper.WrapperLookup registryLookup;

    public IncubatorEMIRecipe(RecipeEntry<IncubatonRecipe> recipeEntry, RegistryWrapper.WrapperLookup registryLookup) {
        this.recipeEntry = recipeEntry;
        this.recipe = recipeEntry.value();
        this.registryLookup = registryLookup;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return IncubatorEMICategory.CATEGORY;
    }

    @Override
    public @Nullable Identifier getId() {
        return recipeEntry.id();
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
                EmiStack.of(recipe.getResult(registryLookup))
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
        widgets.addTexture(Identifier.of(TLOTD.MOD_ID, "textures/gui/rei/incubator.png"), 0, 0, 98, 26, 4, 4);
        List<EmiIngredient> inputs = getInputs();
        int[][] slots = {
                {4,4},
                {40,4}
        };
        for (int i = 0; i < inputs.size(); i++) {
            widgets.addSlot(inputs.get(i), slots[i][0], slots[i][1]);
        }
        widgets.addSlot(EmiStack.of(recipe.getResult(registryLookup)), 76, 4).recipeContext(this);
    }
}