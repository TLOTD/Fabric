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

public class WitchingRecipe implements Recipe<RecipeInput> {
    private static final int INPUT_SIZE = 9;
    private static final int[] SLOT_MAPPING = {4, 0, 1, 2, 3, 5, 6, 7, 8};
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;

    public WitchingRecipe(ItemStack output, List<Ingredient> recipeItems) {
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
        if (world.isClient() || input.getSize() != INPUT_SIZE) {
            return false;
        }
        for (int i = 0; i < INPUT_SIZE; i++) {
            Ingredient ingredient = recipeItems.get(i);
            ItemStack stack = input.getStackInSlot(SLOT_MAPPING[i]);
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

    public static class Type implements RecipeType<WitchingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "witching";

        private Type() {
        }
    }

    public static class Serializer implements RecipeSerializer<WitchingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "witching";

        private static final MapCodec<WitchingRecipe> CODEC =
                RecordCodecBuilder.mapCodec(instance -> instance.group(
                        Ingredient.DISALLOW_EMPTY_CODEC.listOf()
                                .fieldOf("ingredients")
                                .forGetter(recipe ->
                                        new ArrayList<>(recipe.recipeItems)),
                        ItemStack.CODEC
                                .fieldOf("output")
                                .forGetter(recipe -> recipe.output)
                ).apply(instance, (ingredients, output) ->
                        new WitchingRecipe(output, ingredients)));

        private static final PacketCodec<RegistryByteBuf, WitchingRecipe> PACKET_CODEC =
                PacketCodec.of(
                        (recipe, buf) -> {
                            buf.writeVarInt(recipe.recipeItems.size());
                            for (Ingredient ingredient : recipe.recipeItems) {
                                Ingredient.PACKET_CODEC.encode(buf, ingredient);
                            }
                            ItemStack.PACKET_CODEC.encode(buf, recipe.output);
                        },
                        buf -> {
                            int size = buf.readVarInt();
                            if (size != INPUT_SIZE) {
                                throw new IllegalStateException(
                                        "Witching recipe must have exactly 9 ingredients, got " + size
                                );
                            }
                            List<Ingredient> ingredients = new ArrayList<>(size);
                            for (int i = 0; i < size; i++) {
                                ingredients.add(Ingredient.PACKET_CODEC.decode(buf));
                            }
                            ItemStack output = ItemStack.PACKET_CODEC.decode(buf);
                            return new WitchingRecipe(output, ingredients);
                        }
                );

        @Override
        public MapCodec<WitchingRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, WitchingRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }
}