package net.tlotd.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;

public class MithrilSmithingEMICategory extends EmiRecipeCategory {

    public static final MithrilSmithingEMICategory CATEGORY = new MithrilSmithingEMICategory();

    private MithrilSmithingEMICategory() {
        super(new Identifier(TLOTD.MOD_ID, "mithril_metalworking"), EmiStack.of(ModBlocks.MITHRIL_ANVIL));
    }

    @Override
    public Text getName() {
        return Text.translatable("gui.tlotd.mithril_metalworking");
    }
}