package net.tlotd.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;

public class IncubatorEMICategory extends EmiRecipeCategory {

    public static final IncubatorEMICategory CATEGORY = new IncubatorEMICategory();

    private IncubatorEMICategory() {
        super(new Identifier(TLOTD.MOD_ID, "incubating"), EmiStack.of(ModBlocks.INCUBATOR));
    }

    @Override
    public Text getName() {
        return Text.translatable("gui.tlotd.incubating");
    }
}