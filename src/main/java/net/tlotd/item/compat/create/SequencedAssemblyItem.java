package net.tlotd.item.compat.create;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tlotd.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SequencedAssemblyItem extends Item {

    public SequencedAssemblyItem(Settings settings) {
        super(settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        if (stack.isOf(ModItems.INCOMPLETE_BIOLOGICAL_CIRCUIT_BOARD)) {
            return Text.translatable(this.getTranslationKey()).styled(style -> style.withColor(0xA58369));
        } else {
            return Text.translatable(this.getTranslationKey());
        }
    }

    public float getProgress(ItemStack stack) {
        if (!stack.hasNbt()) {
            return 0;
        }
        NbtCompound tag = stack.getNbt();
        if (!tag.contains("SequencedAssembly")) {
            return 0;
        } else {
            return tag.getCompound("SequencedAssembly").getFloat("Progress");
        }
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round(getProgress(stack) * 13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0xffc074;
    }

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier MODS_FONT_ID = new Identifier("tlotd", "mods");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (context.isCreative()){
            Style style = this.getName().getStyle();
            if (stack.isOf(ModItems.INCOMPLETE_BIOLOGICAL_CIRCUIT_BOARD)) {
                tooltip.add(Text.literal("\uE013 ").setStyle(Style.EMPTY.withFont(MODS_FONT_ID)).append(Text.translatable("mod.biomancy.name").setStyle(Style.EMPTY.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                tooltip.add(Text.literal("\uE010 ").setStyle(Style.EMPTY.withFont(MODS_FONT_ID)).append(Text.translatable("mod.spore.name").setStyle(Style.EMPTY.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
            tooltip.add(Text.literal("\uE00A ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.create.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}