package net.tlotd.compat;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.tlotd.block.ModBlocks;
import net.tlotd.recipe.MithrilSmithingRecipe;

public class TLOTDREIClientPlugin implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new MithrilSmithingCategory());
        registry.addWorkstations(MithrilSmithingCategory.MITHRIL_SMITHING, EntryStacks.of(ModBlocks.MITHRIL_ANVIL));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(MithrilSmithingRecipe.class, MithrilSmithingRecipe.Type.INSTANCE, MithrilSmithingDisplay::new);
    }
}
