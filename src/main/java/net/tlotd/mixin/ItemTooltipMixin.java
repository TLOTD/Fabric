package net.tlotd.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tlotd.util.AdAstraOxygenNbtHelper;
import net.tlotd.util.ModTags;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;

@Mixin(Item.class)
public abstract class ItemTooltipMixin {
    @Inject(
            method = "appendTooltip",
            at = @At("TAIL")
    )
    private void addTestTooltip(
            ItemStack stack,
            World world,
            List<Text> tooltip,
            TooltipContext context,
            CallbackInfo ci
    ) {
        if (getAugmentLevel(stack, "tlotd:oxygen_tank") > 0) {
            int maxOxygen = getAugmentLevel(stack, "tlotd:oxygen_tank");
            String oxygen = "0 \uD83E\uDEA3 / " + maxOxygen + "K \uD83E\uDEA3";
            if (Screen.hasShiftDown()) {
                oxygen = "0 \uD83E\uDEA3 / " + maxOxygen + ",000 \uD83E\uDEA3";
                if (stack.hasNbt()) {
                    long oxygenAmount = AdAstraOxygenNbtHelper.getOxygen(stack);
                    int displayAmount = (int) Math.round((double) oxygenAmount * 1000 / AdAstraOxygenNbtHelper.MAX_AMOUNT);
                    String formattedOxygen = String.format("%,d", displayAmount);
                    oxygen = formattedOxygen + " \uD83E\uDEA3 / " + maxOxygen + ",000 \uD83E\uDEA3";
                }
            } else {
                if (stack.hasNbt()) {
                    long oxygenAmount = AdAstraOxygenNbtHelper.getOxygen(stack);
                    String formattedOxygen = getString((double) oxygenAmount);
                    oxygen = formattedOxygen + " \uD83E\uDEA3 / " + maxOxygen + "K \uD83E\uDEA3";
                }
            }
            tooltip.add(Text.translatable("item.tlotd.oxygen_level.tooltip", oxygen).formatted(Formatting.GOLD));
        }
        int slots = 0;
        if (stack.isIn(ModTags.Items.THREE_AUGMENT_SLOTS)) {
            slots = 3;
        } else if (stack.isIn(ModTags.Items.TWO_AUGMENT_SLOTS)) {
            slots = 2;
        } else if (stack.isIn(ModTags.Items.ONE_AUGMENT_SLOT)) {
            slots = 1;
        }
        if (getAugmentLevel(stack,"tlotd:slot_expansion") > 0) {
            slots += 1+getAugmentLevel(stack,"tlotd:slot_expansion");
        }
        if (slots <= 0) return;
        final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
        final Identifier AUGMENTS_FONT_ID = new Identifier("tlotd", "augments");
        NbtList augments = new NbtList();
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("augments.tlotd.title").formatted(Formatting.GRAY));
        if (stack.hasNbt() && stack.getNbt().contains("Augments", NbtElement.LIST_TYPE)) {
            augments = stack.getNbt().getList("Augments", NbtElement.COMPOUND_TYPE);
        }
        for (int slot = 0; slot < slots; slot++) {
            if (slot < augments.size()) {
                NbtCompound augment = augments.getCompound(slot);
                String id = augment.getString("id");
                Formatting rarity = Formatting.GRAY;
                if (id.equals("tlotd:slot_expansion")) {
                    rarity = Formatting.GOLD;
                }
                if (id.equals("tlotd:mithril_chainmail")) {
                    rarity = Formatting.YELLOW;
                }
                int level = augment.getInt("lvl");
                MutableText line = Text.literal(" ").append(Text.translatable("augment." + id.replace(':', '.') + ".icon").setStyle(Style.EMPTY.withFont(AUGMENTS_FONT_ID)).append(Text.literal(" ")).append(Text.translatable("augment." + id.replace(':', '.')).setStyle(Style.EMPTY.withFont(DEFAULT_FONT_ID)).formatted(rarity)));
                if (level > 1) {
                    line.append(" ").append(Text.translatable("enchantment.level." + level).formatted(rarity));
                }
                tooltip.add(line);
                if (Screen.hasShiftDown()) {
                    tooltip.add(Text.literal(" ").append(Text.translatable("augment." + id.replace(':', '.') + ".desc").formatted(Formatting.DARK_GRAY)));
                    tooltip.add(Text.literal(" ").append(Text.translatable("augment." + id.replace(':', '.') + ".desc2").formatted(Formatting.DARK_GRAY)));
                }
            } else {
                tooltip.add(Text.literal(" ").append(Text.translatable("augment.tlotd.empty.icon").setStyle(Style.EMPTY.withFont(AUGMENTS_FONT_ID)).append(Text.literal(" ")).append(Text.translatable("augment.tlotd.empty").setStyle(Style.EMPTY.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.DARK_GRAY)))));
                if (Screen.hasShiftDown()) {
                    tooltip.add(Text.literal(" ").append(Text.translatable("augment.tlotd.empty.desc").formatted(Formatting.DARK_GRAY)));
                }
            }
        }
    }

    private static @NotNull String getString(double oxygenAmount) {
        int displayAmount = (int) Math.round(oxygenAmount * 1000 / AdAstraOxygenNbtHelper.MAX_AMOUNT);
        String formattedOxygen;
        if (displayAmount >= 1000) {
            int thousands = displayAmount / 1000;
            int hundreds = (displayAmount % 1000) / 100;
            if (hundreds == 0) {
                formattedOxygen = thousands + "K";
            } else {
                formattedOxygen = thousands + "." + hundreds + "K";
            }
        } else {
            formattedOxygen = String.valueOf(displayAmount);
        }
        return formattedOxygen;
    }
}