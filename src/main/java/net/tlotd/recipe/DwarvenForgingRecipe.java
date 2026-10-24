package net.tlotd.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DwarvenForgingRecipe implements Recipe<RecipeInput> {

    private final ItemStack output;
    private final List<Ingredient> recipeItems;

    public DwarvenForgingRecipe(ItemStack output, List<Ingredient> recipeItems) {
        this.output = output.copy();
        this.recipeItems = List.copyOf(recipeItems);
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> ingredients = DefaultedList.ofSize(recipeItems.size(), Ingredient.EMPTY);

        for (int i = 0; i < recipeItems.size(); i++) {
            ingredients.set(i, recipeItems.get(i));
        }
        return ingredients;
    }

    @Override
    public boolean matches(RecipeInput input, World world) {
        if (world.isClient()) {
            return false;
        }
        List<ItemStack> remainingStacks = new ArrayList<>();
        for (int i = 0; i < input.getSize(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            if (!stack.isEmpty()) {
                remainingStacks.add(stack);
            }
        }
        List<Ingredient> remainingIngredients = recipeItems.stream().filter(ingredient -> !ingredient.isEmpty()).toList();
        if (remainingStacks.size() != remainingIngredients.size()) {
            return false;
        }
        for (Ingredient ingredient : remainingIngredients) {
            boolean matched = false;
            Iterator<ItemStack> iterator = remainingStacks.iterator();
            while (iterator.hasNext()) {
                ItemStack stack = iterator.next();
                if (ingredient.test(stack)) {
                    iterator.remove();
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack craft(RecipeInput inventory, RegistryWrapper.WrapperLookup registries) {
        return output.copy();
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registries) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<DwarvenForgingRecipe> {
        public static final Type INSTANCE = new Type();
        private Type() {}
        public static final String ID = "dwarven_forging";
    }

    public static class Serializer implements RecipeSerializer<DwarvenForgingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "dwarven_forging";

        private static final MapCodec<DwarvenForgingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients").forGetter(recipe -> recipe.recipeItems),
                ItemStack.CODEC.fieldOf("result").forGetter(recipe -> recipe.output)).apply(instance, (ingredients, output) -> new DwarvenForgingRecipe(output, ingredients)));

        private static final PacketCodec<RegistryByteBuf, DwarvenForgingRecipe> PACKET_CODEC = PacketCodec.of((recipe, buf) -> {
            buf.writeVarInt(recipe.recipeItems.size());
            for (Ingredient ingredient : recipe.recipeItems) {
                Ingredient.PACKET_CODEC.encode(buf, ingredient);
            }
            ItemStack.PACKET_CODEC.encode(buf, recipe.output);
        }, buf -> {
            int size = buf.readVarInt();
            List<Ingredient> ingredients = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                ingredients.add(Ingredient.PACKET_CODEC.decode(buf));
            }
            ItemStack output = ItemStack.PACKET_CODEC.decode(buf);
            return new DwarvenForgingRecipe(output, ingredients);
        });

        private Serializer() {
        }

        @Override
        public MapCodec<DwarvenForgingRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, DwarvenForgingRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }
}