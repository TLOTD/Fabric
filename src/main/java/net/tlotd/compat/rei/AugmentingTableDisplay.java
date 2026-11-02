package net.tlotd.compat.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.tlotd.recipe.AugmentationRecipe;
import net.tlotd.util.AugmentNbtHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.tlotd.block.entity.AugmentationTableBlockEntity.getAugment;

public class AugmentingTableDisplay extends BasicDisplay {

    public AugmentingTableDisplay(AugmentationRecipe recipe, RegistryWrapper.WrapperLookup registryLookup) {
        super(getInputList(recipe), List.of(getOutputEntries(recipe, registryLookup)));
    }

    private static List<EntryIngredient> getInputList(AugmentationRecipe recipe) {
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
        return list;
    }

    private static EntryIngredient getOutputEntries(AugmentationRecipe recipe, RegistryWrapper.WrapperLookup registryLookup) {
        Ingredient baseIngredient = recipe.getIngredients().get(0);
        ItemStack augment = recipe.getResult(registryLookup);
        String augmentId = getAugment(augment);
        int maxLevel = augment.getCount();
        List<ItemStack> outputs = Arrays.stream(baseIngredient.getMatchingStacks()).map(ItemStack::copy).map(stack -> {
            AugmentNbtHelper.addOrUpdateAugment(stack, augmentId, 1, maxLevel);
            return stack;
        }).toList();
        return EntryIngredients.ofItemStacks(outputs);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return AugmentingTableCategory.AUGMENTING;
    }
}