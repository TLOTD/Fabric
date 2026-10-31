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

public class NetheriteSmithingRecipe implements Recipe<RecipeInput> {

    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;

    public NetheriteSmithingRecipe(ItemStack output, List<Ingredient> recipeItems) {
        this.output = output.copy();
        this.recipeItems = DefaultedList.ofSize(6, Ingredient.EMPTY);
        for (int i = 0; i < Math.min(recipeItems.size(), 6); i++) {
            this.recipeItems.set(i, recipeItems.get(i));
        }
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public boolean matches(RecipeInput input, World world) {
        if (world.isClient() || input.getSize() != 6) {
            return false;
        }
        for (int i = 0; i < 2; i++) {
            Ingredient ingredient = recipeItems.get(i);
            ItemStack stack = input.getStackInSlot(i);
            if (ingredient.isEmpty()) {
                if (!stack.isEmpty()) {
                    return false;
                }
            } else if (!ingredient.test(stack)) {
                return false;
            }
        }
        List<ItemStack> remainingStacks = new ArrayList<>();
        for (int i = 2; i < 6; i++) {
            ItemStack stack = input.getStackInSlot(i);
            if (!stack.isEmpty()) {
                remainingStacks.add(stack);
            }
        }
        List<Ingredient> remainingIngredients = new ArrayList<>();
        for (int i = 2; i < 6; i++) {
            Ingredient ingredient = recipeItems.get(i);
            if (!ingredient.isEmpty()) {
                remainingIngredients.add(ingredient);
            }
        }
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
    public ItemStack craft(RecipeInput input, RegistryWrapper.WrapperLookup registries) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
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

    public static class Type implements RecipeType<NetheriteSmithingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "metalworking";

        private Type() {
        }
    }

    public static class Serializer implements RecipeSerializer<NetheriteSmithingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "metalworking";
        private static final MapCodec<NetheriteSmithingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients").forGetter(recipe -> new ArrayList<>(recipe.recipeItems)),

                ItemStack.CODEC.fieldOf("output").forGetter(recipe -> recipe.output)).apply(instance, (ingredients, output) -> new NetheriteSmithingRecipe(output, ingredients)));

        private static final PacketCodec<RegistryByteBuf, NetheriteSmithingRecipe> PACKET_CODEC = PacketCodec.of((recipe, buf) -> {
            buf.writeVarInt(recipe.recipeItems.size());
            for (Ingredient ingredient : recipe.recipeItems) {
                Ingredient.PACKET_CODEC.encode(buf, ingredient);
            }
            ItemStack.PACKET_CODEC.encode(buf, recipe.output);
        }, buf -> {
            int size = buf.readVarInt();
            if (size != 6) {
                throw new IllegalStateException("Netherite smithing recipe must have exactly 6 ingredients");
            }
            List<Ingredient> ingredients = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                ingredients.add(Ingredient.PACKET_CODEC.decode(buf));
            }
            ItemStack output = ItemStack.PACKET_CODEC.decode(buf);
            return new NetheriteSmithingRecipe(output, ingredients);
        });

        private Serializer() {
        }

        @Override
        public MapCodec<NetheriteSmithingRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, NetheriteSmithingRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }
}