package net.tlotd.compat.jade;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec2f;
import net.tlotd.TLOTD;
import net.tlotd.item.ModItems;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;

import static net.tlotd.block.custom.WitchingTableBlock.*;

public enum WitchingTableComponentProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        IElementHelper elements = tooltip.getElementHelper();
        IElement soul_flask = elements.item(new ItemStack(ModItems.SOUL_FLASK), 0.5f).translate(new Vec2f(0, -1));
        IElement cursed_flask = elements.item(new ItemStack(ModItems.CURSED_SOUL_FLASK), 0.5f).translate(new Vec2f(0, -1));
        IElement abyss_flask = elements.item(new ItemStack(ModItems.SOUL_FLASK_OF_THE_ABYSS), 0.5f).translate(new Vec2f(0, -1));
        int souls = accessor.getBlockState().get(SOUL_CHARGES);
        int cursed = accessor.getBlockState().get(CURSED_SOUL_CHARGES);
        int abyss = accessor.getBlockState().get(ABYSSAL_SOUL_CHARGES);
        int charges = souls + cursed + abyss;
        Formatting format = Formatting.WHITE;
        if (charges == 3) {
            format = Formatting.GREEN;
        } else if (charges > 3) {
            format = Formatting.RED;
        }
        tooltip.add(Text.translatable("jade.tlotd.witching_table.charges").append(Text.literal(" " + charges + " / 3").formatted(format)));
        tooltip.add(Text.literal(" "));
        tooltip.append(soul_flask);
        tooltip.append(Text.translatable("jade.tlotd.witching_table.soul_charges", souls));
        tooltip.add(Text.literal(" "));
        tooltip.append(cursed_flask);
        tooltip.append(Text.translatable("jade.tlotd.witching_table.cursed_soul_charges", cursed));
        tooltip.add(Text.literal(" "));
        tooltip.append(abyss_flask);
        tooltip.append(Text.translatable("jade.tlotd.witching_table.soul_charges_of_the_abyss", abyss));
    }

    @Override
    public Identifier getUid() {
        return new Identifier(TLOTD.MOD_ID, "witching_table");
    }
}