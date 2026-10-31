package net.tlotd.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.tlotd.TLOTD;
import net.tlotd.recipe.NetheriteSmithingRecipe;
import net.tlotd.util.ItemHeatHelper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetheriteSmithingEMIRecipe implements EmiRecipe {

    private final RecipeEntry<NetheriteSmithingRecipe> recipeEntry;
    private final NetheriteSmithingRecipe recipe;
    private final RegistryWrapper.WrapperLookup registryLookup;

    public NetheriteSmithingEMIRecipe(RecipeEntry<NetheriteSmithingRecipe> recipeEntry, RegistryWrapper.WrapperLookup registryLookup) {
        this.recipeEntry = recipeEntry;
        this.recipe = recipeEntry.value();
        this.registryLookup = registryLookup;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return NetheriteSmithingEMICategory.CATEGORY;
    }

    @Override
    public @Nullable Identifier getId() {
        return recipeEntry.id();
    }

    @Override
    public List<EmiIngredient> getInputs() {
        List<EmiIngredient> list = new ArrayList<>();
        DefaultedList<Ingredient> ingredients = recipe.getIngredients();
        for (int i = 0; i < 6; i++) {
            if (i < ingredients.size() && !ingredients.get(i).isEmpty()) {
                List<EmiStack> stacks = Arrays.stream(ingredients.get(i).getMatchingStacks())
                        .map(stack -> {
                            ItemStack copy = stack.copy();
                            int forgingTemp = ItemHeatHelper.getSmithingTemperature(copy);
                            if (forgingTemp > 0) {
                                ItemHeatHelper.setTemperature(copy, forgingTemp);
                            }
                            return EmiStack.of(copy);
                        })
                        .toList();
                list.add(EmiIngredient.of(stacks));
            } else {
                list.add(EmiStack.EMPTY);
            }
        }
        return list;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(
                EmiStack.of(recipe.getResult(registryLookup))
        );
    }

    @Override
    public int getDisplayWidth() {
        return 134;
    }

    @Override
    public int getDisplayHeight() {
        return 44;
    }


    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(Identifier.of(TLOTD.MOD_ID, "textures/gui/rei/netherite_anvil.png"), 0, 0, 134, 44, 4, 4);
        List<EmiIngredient> inputs = getInputs();
        int[][] slots = {
                {58,4},
                {76,13},
                {4,13},
                {22,4},
                {22,22},
                {40,13}
        };
        for (int i = 0; i < inputs.size(); i++) {
            widgets.addSlot(inputs.get(i), slots[i][0], slots[i][1]);
        }
        widgets.addSlot(EmiStack.of(recipe.getResult(registryLookup)), 112, 13).recipeContext(this);
    }
}