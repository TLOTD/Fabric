package net.tlotd.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;

public class AugmentingTableEMICategory extends EmiRecipeCategory {

    public static final AugmentingTableEMICategory CATEGORY = new AugmentingTableEMICategory();

    private AugmentingTableEMICategory() {
        super(new Identifier(TLOTD.MOD_ID, "augmenting"), EmiStack.of(ModBlocks.AUGMENTATION_TABLE));
    }

    @Override
    public Text getName() {
        return Text.translatable("gui.tlotd.augmenting");
    }
}