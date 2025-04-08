package net.tlotd.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class MithrilSmithingRecipe implements Recipe<SimpleInventory> {

    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;

    public MithrilSmithingRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if(world.isClient()) {
            return false;
        }
        int recipeNum = 0;
        int itemNum = 0;
        for (int i = 0; i < recipeItems.size(); i++) {
            if (inventory.getStack(i).isEmpty()) {
                itemNum++;
            }
            if (recipeItems.get(i).isEmpty()) {
                recipeNum++;
            }
        }
        return (itemNum == recipeNum) && (recipeItems.get(0).test((inventory.getStack(0)))) &&
                (recipeItems.get(1).test((inventory.getStack(1)))) &&
                ((recipeItems.get(2).test((inventory.getStack(2)))) || (recipeItems.get(2).test((inventory.getStack(3)))) || (recipeItems.get(2).test((inventory.getStack(4)))) || (recipeItems.get(2).test((inventory.getStack(5))))) &&
                ((recipeItems.get(3).test((inventory.getStack(2)))) || (recipeItems.get(3).test((inventory.getStack(3)))) || (recipeItems.get(3).test((inventory.getStack(4)))) || (recipeItems.get(3).test((inventory.getStack(5))))) &&
                ((recipeItems.get(4).test((inventory.getStack(2)))) || (recipeItems.get(4).test((inventory.getStack(3)))) || (recipeItems.get(4).test((inventory.getStack(4)))) || (recipeItems.get(4).test((inventory.getStack(5))))) &&
                ((recipeItems.get(5).test((inventory.getStack(2)))) || (recipeItems.get(5).test((inventory.getStack(3)))) || (recipeItems.get(5).test((inventory.getStack(4)))) || (recipeItems.get(5).test((inventory.getStack(5)))));
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<MithrilSmithingRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "mithril_smithing";
    }

    public static class Serializer implements RecipeSerializer<MithrilSmithingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "mithril_smithing";

        @Override
        public MithrilSmithingRecipe read(Identifier id, JsonObject json) {
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));

            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(6, Ingredient.EMPTY);

            for (int i = 0; i < ingredients.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new MithrilSmithingRecipe(id, output, inputs);
        }

        @Override
        public MithrilSmithingRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            inputs.replaceAll(ignored -> Ingredient.fromPacket(buf));

            ItemStack output = buf.readItemStack();
            return new MithrilSmithingRecipe(id, output, inputs);
        }

        @Override
        public void write(PacketByteBuf buf, MithrilSmithingRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }
            buf.writeItemStack(recipe.getOutput(null));
        }
    }
}
