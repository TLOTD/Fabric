package net.tlotd.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;

public class DwarvenForgingEMICategory extends EmiRecipeCategory {

    public static final DwarvenForgingEMICategory CATEGORY = new DwarvenForgingEMICategory();

    private DwarvenForgingEMICategory() {
        super(new Identifier(TLOTD.MOD_ID, "dwarven_forging"), EmiStack.of(ModBlocks.DWARVEN_FORGE));
    }

    @Override
    public Text getName() {
        return Text.translatable("gui.tlotd.dwarven_forging");
    }
}