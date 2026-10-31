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

public class LavaCleansingRecipe implements Recipe<RecipeInput> {
    private static final int INPUT_SIZE = 1;

    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;

    public LavaCleansingRecipe(ItemStack output, List<Ingredient> recipeItems) {
        this.output = output.copy();
        this.recipeItems = DefaultedList.ofSize(INPUT_SIZE, Ingredient.EMPTY);
        for (int i = 0; i < Math.min(recipeItems.size(), INPUT_SIZE); i++) {
            this.recipeItems.set(i, recipeItems.get(i));
        }
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public boolean matches(RecipeInput input, World world) {
        if (world.isClient() || input.getSize() < INPUT_SIZE) {
            return false;
        }
        return recipeItems.get(0).test(input.getStackInSlot(0));
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

    public static class Type implements RecipeType<LavaCleansingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "lava_cleansing";

        private Type() {
        }
    }

    public static class Serializer implements RecipeSerializer<LavaCleansingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "lava_cleansing";

        private static final MapCodec<LavaCleansingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("ingredients").forGetter(recipe -> new ArrayList<>(recipe.recipeItems)), ItemStack.CODEC.fieldOf("output").forGetter(recipe -> recipe.output)).apply(instance, (ingredients, output) -> new LavaCleansingRecipe(output, ingredients)));

        private static final PacketCodec<RegistryByteBuf, LavaCleansingRecipe> PACKET_CODEC = PacketCodec.of((recipe, buf) -> {
            buf.writeVarInt(recipe.recipeItems.size());
            for (Ingredient ingredient : recipe.recipeItems) {
                Ingredient.PACKET_CODEC.encode(buf, ingredient);
            }
            ItemStack.PACKET_CODEC.encode(buf, recipe.output);
        }, buf -> {
            int size = buf.readVarInt();
            if (size != INPUT_SIZE) {
                throw new IllegalStateException("Lava cleansing recipe must have exactly 1 ingredient, got " + size);
            }
            List<Ingredient> ingredients = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                ingredients.add(Ingredient.PACKET_CODEC.decode(buf));
            }
            ItemStack output = ItemStack.PACKET_CODEC.decode(buf);
            return new LavaCleansingRecipe(output, ingredients);
        });

        @Override
        public MapCodec<LavaCleansingRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, LavaCleansingRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }
}