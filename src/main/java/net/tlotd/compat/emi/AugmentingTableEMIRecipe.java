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
import net.tlotd.TLOTD;
import net.tlotd.recipe.AugmentationRecipe;
import net.tlotd.util.AugmentNbtHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

import static net.tlotd.block.entity.AugmentationTableBlockEntity.getAugment;

public class AugmentingTableEMIRecipe implements EmiRecipe {

    private final RecipeEntry<AugmentationRecipe> recipeEntry;
    private final AugmentationRecipe recipe;
    private final RegistryWrapper.WrapperLookup registryLookup;

    public AugmentingTableEMIRecipe(RecipeEntry<AugmentationRecipe> recipeEntry, RegistryWrapper.WrapperLookup registryLookup) {
        this.recipeEntry = recipeEntry;
        this.recipe = recipeEntry.value();
        this.registryLookup = registryLookup;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return AugmentingTableEMICategory.CATEGORY;
    }

    @Override
    public @Nullable Identifier getId() {
        return recipeEntry.id();
    }

    @Override
    public List<EmiIngredient> getInputs() {

        return recipe.getIngredients().stream().filter(i -> !i.isEmpty()).map(i -> EmiIngredient.of(Arrays.stream(i.getMatchingStacks()).map(EmiStack::of).toList())).toList();
    }

    @Override
    public List<EmiStack> getOutputs() {
        Ingredient baseIngredient = recipe.getIngredients().getFirst();
        String augmentId = getAugment(recipe.getResult(registryLookup));
        int maxLevel = recipe.getResult(registryLookup).getCount();
        return Arrays.stream(baseIngredient.getMatchingStacks()).map(ItemStack::copy).map(stack -> {
            AugmentNbtHelper.addOrUpdateAugment(stack, augmentId, 1, maxLevel);
            return EmiStack.of(stack);
        }).toList();
    }

    @Override
    public int getDisplayWidth() {
        return 110;
    }

    @Override
    public int getDisplayHeight() {
        return 66;
    }


    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(Identifier.of(TLOTD.MOD_ID, "textures/gui/rei/augmentation_table.png"), 0, 0, 110, 66, 4, 4);
        List<EmiIngredient> inputs = getInputs();
        int[][] slots = {{24, 24}, {5, 5}, {24, 4}, {43, 5}, {4, 24}, {44, 24}, {5, 43}, {24, 44}, {43, 43}};
        for (int i = 0; i < inputs.size(); i++) {
            widgets.addSlot(inputs.get(i), slots[i][0], slots[i][1]);
        }
        widgets.addSlot(EmiIngredient.of(getOutputs()), 88, 24).recipeContext(this);
    }
}