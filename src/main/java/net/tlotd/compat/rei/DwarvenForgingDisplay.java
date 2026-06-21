package net.tlotd.compat.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.util.collection.DefaultedList;
import net.tlotd.recipe.DwarvenForgingRecipe;
import net.tlotd.util.ItemHeatHelper;
import net.tlotd.util.ModTags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DwarvenForgingDisplay extends BasicDisplay {

    public DwarvenForgingDisplay(DwarvenForgingRecipe recipe) {
        super(getInputList(recipe), getOutputList(recipe));
    }

    private static List<EntryIngredient> getOutputList(DwarvenForgingRecipe recipe) {
        ItemStack output = recipe.getOutput(null).copy();
        int maxTemp = getMaxIngredientTemperature(recipe);
        if (maxTemp > 0) {
            ItemHeatHelper.setTemperature(output, maxTemp);
        }
        return List.of(EntryIngredients.of(output));
    }

    private static List<EntryIngredient> getInputList(DwarvenForgingRecipe recipe) {
        List<EntryIngredient> list = new ArrayList<>();
        DefaultedList<Ingredient> ingredients = recipe.getIngredients();
        AtomicInteger min_temp = new AtomicInteger();
        for (int i = 0; i < 3; i++) {
            if (i < ingredients.size() && !ingredients.get(i).isEmpty()) {
                ItemStack[] matching = ingredients.get(i).getMatchingStacks();
                List<ItemStack> converted = Arrays.stream(matching)
                        .map(stack -> {
                            if (ItemHeatHelper.getForgingTemperature(stack) > 0) {
                                ItemStack heatedStack = new ItemStack(stack.copy().getItem(), stack.getCount());
                                int heated = ItemHeatHelper.getForgingTemperature(heatedStack);
                                min_temp.set(Math.max(min_temp.get(), heated));
                                ItemHeatHelper.setTemperature(heatedStack, heated);
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
        List<ItemStack> fuel;
        List<ItemStack> base;
        if (min_temp.get() > 3000) {
            fuel = Registries.ITEM.stream().filter(item -> (item.getDefaultStack().isIn(ModTags.Items.BURNS_6000))).map(ItemStack::new).toList();
            base = Registries.ITEM.stream().filter(item -> (item.getDefaultStack().isIn(ModTags.Items.FIRE_BASE_6000))).map(ItemStack::new).toList();
        } else if (min_temp.get() > 1400) {
            fuel = Registries.ITEM.stream().filter(item -> (item.getDefaultStack().isIn(ModTags.Items.BURNS_3000) || item.getDefaultStack().isIn(ModTags.Items.BURNS_6000))).map(ItemStack::new).toList();
            base = Registries.ITEM.stream().filter(item -> (item.getDefaultStack().isIn(ModTags.Items.FIRE_BASE_3000) || item.getDefaultStack().isIn(ModTags.Items.FIRE_BASE_6000))).map(ItemStack::new).toList();
        } else {
            fuel = Registries.ITEM.stream().filter(item -> (item.getDefaultStack().isIn(ModTags.Items.BURNS_IN_FORGE))).map(ItemStack::new).toList();
            base = Registries.ITEM.stream().filter(item -> (item.getDefaultStack().isIn(ModTags.Items.FIRE_BASE_FORGE))).map(ItemStack::new).toList();
        }
        list.add(EntryIngredients.ofItemStacks(fuel));
        list.add(EntryIngredient.empty());
        list.add(EntryIngredient.empty());
        list.add(EntryIngredient.empty());
        list.add(EntryIngredients.ofItemStacks(base));
        list.add(EntryIngredients.ofItemStacks(base));
        list.add(EntryIngredients.ofItemStacks(base));
        return list;
    }

    private static int getMaxIngredientTemperature(DwarvenForgingRecipe recipe) {
        int maxTemp = 0;
        for (Ingredient ingredient : recipe.getIngredients()) {
            for (ItemStack stack : ingredient.getMatchingStacks()) {
                maxTemp = Math.max(maxTemp, ItemHeatHelper.getForgingTemperature(stack));
            }
        }
        return maxTemp;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return DwarvenForgingCategory.DWARVEN_FORGING;
    }
}
