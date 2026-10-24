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
import java.util.List;

public class AugmentationRecipe implements Recipe<RecipeInput> {

    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;

    public AugmentationRecipe(ItemStack output, List<Ingredient> recipeItems) {
        this.output = output.copy();
        this.recipeItems = DefaultedList.ofSize(9, Ingredient.EMPTY);
        for (int i = 0; i < Math.min(recipeItems.size(), 9); i++) {
            this.recipeItems.set(i, recipeItems.get(i));
        }
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public boolean matches(RecipeInput input, World world) {
        if (world.isClient() || input.getSize() != 9) {
            return false;
        }
        for (int i = 0; i < 9; i++) {
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
        return true;
    }

    @Override
    public ItemStack craft(RecipeInput input, RegistryWrapper.WrapperLookup registries) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 9;
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

    public static class Type implements RecipeType<AugmentationRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "augmenting";
        private Type() {}
    }

    public static class Serializer implements RecipeSerializer<AugmentationRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "augmenting";
        private static final MapCodec<AugmentationRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients").forGetter(recipe -> new ArrayList<>(recipe.recipeItems)),
                ItemStack.CODEC.fieldOf("augment").forGetter(recipe -> recipe.output)).apply(instance, (ingredients, output) -> new AugmentationRecipe(output, ingredients)));

        private static final PacketCodec<RegistryByteBuf, AugmentationRecipe> PACKET_CODEC = PacketCodec.of((recipe, buf) -> {
            buf.writeVarInt(recipe.recipeItems.size());
            for (Ingredient ingredient : recipe.recipeItems) {
                Ingredient.PACKET_CODEC.encode(buf, ingredient);
            }
            ItemStack.PACKET_CODEC.encode(buf, recipe.output);
        }, buf -> {
            int size = buf.readVarInt();
            if (size != 9) {
                throw new IllegalStateException("Augmentation recipe must have exactly 9 ingredients");
            }
            List<Ingredient> ingredients = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                ingredients.add(Ingredient.PACKET_CODEC.decode(buf));
            }
            ItemStack output = ItemStack.PACKET_CODEC.decode(buf);
            return new AugmentationRecipe(output, ingredients);
        });

        private Serializer() {
        }

        @Override
        public MapCodec<AugmentationRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, AugmentationRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }
}