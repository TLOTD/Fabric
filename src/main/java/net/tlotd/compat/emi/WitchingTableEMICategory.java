package net.tlotd.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;

public class WitchingTableEMICategory extends EmiRecipeCategory {

    public static final WitchingTableEMICategory CATEGORY = new WitchingTableEMICategory();

    private WitchingTableEMICategory() {
        super(new Identifier(TLOTD.MOD_ID, "witching"), EmiStack.of(ModBlocks.WITCHING_TABLE));
    }

    @Override
    public Text getName() {
        return Text.translatable("gui.tlotd.witching");
    }
}