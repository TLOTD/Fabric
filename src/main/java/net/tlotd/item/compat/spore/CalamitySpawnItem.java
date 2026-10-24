package net.tlotd.item.compat.spore;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CalamitySpawnItem extends SpawnEggItem {
    public CalamitySpawnItem(EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor, Settings settings) {
        super(type, primaryColor, secondaryColor, settings);
    }

    public static final Identifier DEFAULT_FONT_ID = Identifier.of("minecraft", "default");
    public static final Identifier MODS_FONT_ID = Identifier.of("tlotd", "mods");

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        /*if (context.isCreative()) {
            Style style = this.getName().getStyle();
            tooltip.add(Text.literal("\uE010 ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.spore.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            super.appendTooltip(stack, context, tooltip, type);
        }*/
        super.appendTooltip(stack, context, tooltip, type);
    }
}
