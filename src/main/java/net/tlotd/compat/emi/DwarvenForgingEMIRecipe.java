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
import net.tlotd.recipe.DwarvenForgingRecipe;
import net.tlotd.util.ItemHeatHelper;
import net.tlotd.util.ModTags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DwarvenForgingEMIRecipe implements EmiRecipe {

    public static final Identifier TEXTURE = new Identifier(TLOTD.MOD_ID, "textures/gui/rei/dwarven_forge.png");

    private final DwarvenForgingRecipe recipe;

    public DwarvenForgingEMIRecipe(DwarvenForgingRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return DwarvenForgingEMICategory.CATEGORY;
    }

    @Override
    public Identifier getId() {
        return recipe.getId();
    }

    @Override
    public List<EmiIngredient> getInputs() {
        List<EmiIngredient> list = new ArrayList<>();
        DefaultedList<Ingredient> ingredients = recipe.getIngredients();
        AtomicInteger minTemp = new AtomicInteger();
        for (int i = 0; i < 3; i++) {
            if (i < ingredients.size() && !ingredients.get(i).isEmpty()) {
                List<EmiStack> stacks = Arrays.stream(ingredients.get(i).getMatchingStacks())
                        .map(stack -> {
                            ItemStack copy = stack.copy();
                            int forgingTemp = ItemHeatHelper.getForgingTemperature(copy);
                            if (forgingTemp > 0) {
                                minTemp.set(Math.max(minTemp.get(), forgingTemp));
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
        List<EmiStack> fuel;
        List<EmiStack> base;
        if (minTemp.get() > 3000) {
            fuel = Registries.ITEM.stream().filter(item -> item.getDefaultStack().isIn(ModTags.Items.BURNS_6000)).map(item -> EmiStack.of(new ItemStack(item))).toList();
            base = Registries.ITEM.stream().filter(item -> item.getDefaultStack().isIn(ModTags.Items.FIRE_BASE_6000)).map(item -> EmiStack.of(new ItemStack(item))).toList();
        } else if (minTemp.get() > 1400) {
            fuel = Registries.ITEM.stream().filter(item -> item.getDefaultStack().isIn(ModTags.Items.BURNS_3000) || item.getDefaultStack().isIn(ModTags.Items.BURNS_6000)).map(item -> EmiStack.of(new ItemStack(item))).toList();
            base = Registries.ITEM.stream().filter(item -> item.getDefaultStack().isIn(ModTags.Items.FIRE_BASE_3000) || item.getDefaultStack().isIn(ModTags.Items.FIRE_BASE_6000)).map(item -> EmiStack.of(new ItemStack(item))).toList();
        } else {
            fuel = Registries.ITEM.stream().filter(item -> item.getDefaultStack().isIn(ModTags.Items.BURNS_IN_FORGE)).map(item -> EmiStack.of(new ItemStack(item))).toList();
            base = Registries.ITEM.stream().filter(item -> item.getDefaultStack().isIn(ModTags.Items.FIRE_BASE_FORGE)).map(item -> EmiStack.of(new ItemStack(item))).toList();
        }
        list.add(EmiIngredient.of(fuel));
        list.add(EmiStack.EMPTY);
        list.add(EmiStack.EMPTY);
        list.add(EmiStack.EMPTY);
        list.add(EmiIngredient.of(base));
        list.add(EmiIngredient.of(base));
        list.add(EmiIngredient.of(base));
        return list;
    }

    @Override
    public List<EmiStack> getOutputs() {
        ItemStack output = recipe.getOutput(null).copy();
        int maxTemp = getMaxIngredientTemperature(recipe);
        if (maxTemp > 0) {
            ItemHeatHelper.setTemperature(output, maxTemp);
        }
        return List.of(EmiStack.of(output));
    }

    @Override
    public int getDisplayWidth() {
        return 133;
    }

    @Override
    public int getDisplayHeight() {
        return 80;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(TEXTURE, 0, 0, 133, 80, 4, 4);

        EmiStack output = getOutputs().get(0);
        if (output.getItemStack() != null && !output.getItemStack().isEmpty()) {
            ItemStack stack = output.getItemStack();
            int temperature = ItemHeatHelper.getTemperature(stack);
            int y;
            if (temperature <= 1500) {
                y = temperature * 36 / 1500;
            } else if (temperature <= 3000) {
                y = 36 + (temperature - 1500) * 12 / 1500;
            } else {
                y = 48 + (temperature - 3000) * 24 / 3000;
            }
            int v = 0;
            if (temperature > 3000) {
                v = 28;
            } else if (temperature > 1500) {
                v = 14;
            }
            widgets.addTexture(TEXTURE, 27, 4 + (72 - y), 7, y, 141, 72 - y);
            widgets.addTexture(TEXTURE, 59, 33, 14, 14, 148, v);
        }

        List<EmiIngredient> inputs = getInputs();
        int[][] slots = {
                {39,14},
                {57,13},
                {75,14},

                {4,58},
                {4,40},
                {4,22},
                {4,4},

                {39,48},
                {57,49},
                {75,48}
        };

        for (int i = 0; i < inputs.size(); i++) {
            widgets.addSlot(inputs.get(i), slots[i][0], slots[i][1]);
        }

        widgets.addSlot(getOutputs().get(0), 111, 31).recipeContext(this);
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
}