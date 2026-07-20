package net.tlotd.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;

public class NetheriteSmithingEMICategory extends EmiRecipeCategory {

    public static final NetheriteSmithingEMICategory CATEGORY = new NetheriteSmithingEMICategory();

    private NetheriteSmithingEMICategory() {
        super(new Identifier(TLOTD.MOD_ID, "metalworking"), EmiStack.of(ModBlocks.NETHERITE_ANVIL));
    }

    @Override
    public Text getName() {
        return Text.translatable("gui.tlotd.metalworking");
    }
}