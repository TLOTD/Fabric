package net.tlotd.recipe;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModRecipies {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(TLOTD.MOD_ID, MithrilSmithingRecipe.Serializer.ID),
                MithrilSmithingRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(TLOTD.MOD_ID, MithrilSmithingRecipe.Type.ID),
                MithrilSmithingRecipe.Type.INSTANCE);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(TLOTD.MOD_ID, WitchingRecipe.Serializer.ID),
                WitchingRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(TLOTD.MOD_ID, WitchingRecipe.Type.ID),
                WitchingRecipe.Type.INSTANCE);
    }
}
