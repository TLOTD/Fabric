package net.tlotd.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;
import net.tlotd.item.custom.SpaceSuitArmorItem;
import net.tlotd.util.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;

@Mixin(Item.class)
public abstract class ItemTooltipMixin {
    @Inject(
            method = "appendTooltip",
            at = @At("TAIL")
    )
    private void addItemTooltip(
            ItemStack stack,
            World world,
            List<Text> tooltip,
            TooltipContext context,
            CallbackInfo ci
    ) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;
        TemperatureUnit unit = ItemHeatHelper.getTemperatureUnit(player);
        if (stack.isIn(ModTags.Items.BURNS_IN_FORGE)){
            int minTemp = ItemHeatHelper.getMinBurningTemperature(stack);
            int maxtemp = ItemHeatHelper.getMaxBurningTemperature(stack);
            if (minTemp!=0){
                tooltip.add(Text.translatable("temperature.tlotd.starts_burning_at", ItemHeatHelper.getTemperatureText(minTemp, unit)).formatted(Formatting.GRAY));
            }
            tooltip.add(Text.translatable("temperature.tlotd.burns_at", ItemHeatHelper.getTemperatureText(maxtemp, unit), ItemHeatHelper.getBurningTime(maxtemp)).formatted(Formatting.GRAY));
        }
        if (stack.isIn(ModTags.Items.FIRE_BASE_FORGE)){
            int minTemp = ItemHeatHelper.getMinBurningBaseTemperature(stack);
            int maxTemp = ItemHeatHelper.getMaxBurningBaseTemperature(stack);
            if (minTemp!=0){
                tooltip.add(Text.translatable("temperature.tlotd.starts_burning_at", ItemHeatHelper.getTemperatureText(minTemp, unit)).formatted(Formatting.GRAY));
            }
            tooltip.add(Text.translatable("temperature.tlotd.allows_burning_to", ItemHeatHelper.getTemperatureText(maxTemp, unit)).formatted(Formatting.GRAY));
        }
        if (ItemHeatHelper.getTemperature(stack) >= 500) {
            tooltip.add(Text.translatable("temperature.tlotd.too_hot").formatted(Formatting.RED));
        }
        if (ItemHeatHelper.hasTemperature(stack)) {
            tooltip.add(ItemHeatHelper.getTemperatureText(ItemHeatHelper.getTemperature(stack), unit));
        }
        int forgingTemp = ItemHeatHelper.getForgingTemperature(stack);
        int smithingTemp = ItemHeatHelper.getSmithingTemperature(stack);
        if (forgingTemp > 0) {
            tooltip.add(Text.translatable("temperature.tlotd.forge_at", ItemHeatHelper.getTemperatureText(forgingTemp, unit), ModBlocks.DWARVEN_FORGE.getName().formatted(Formatting.YELLOW)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("temperature.tlotd.forge", ModBlocks.DWARVEN_FORGE.getName().formatted(Formatting.YELLOW), ItemHeatHelper.getTemperatureText(forgingTemp, unit)).formatted(Formatting.GRAY));
        }
        if (smithingTemp > 0) {
            if (stack.isOf(ModItems.MITHRIL_INGOT) || stack.isOf(ModItems.MITHRIL_PLATE)) {
                tooltip.add(Text.translatable("temperature.tlotd.anvil_at", ItemHeatHelper.getTemperatureText(smithingTemp, unit), ModBlocks.MITHRIL_ANVIL.getName().formatted(Formatting.YELLOW)).formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("temperature.tlotd.anvil", ModBlocks.MITHRIL_ANVIL.getName().formatted(Formatting.YELLOW), ItemHeatHelper.getTemperatureText(smithingTemp, unit)).formatted(Formatting.GRAY));
            } else {
                tooltip.add(Text.translatable("temperature.tlotd.anvil_at", ItemHeatHelper.getTemperatureText(smithingTemp, unit), ModBlocks.NETHERITE_ANVIL.getName().formatted(Formatting.WHITE)).formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("temperature.tlotd.anvil", ModBlocks.NETHERITE_ANVIL.getName().formatted(Formatting.WHITE), ItemHeatHelper.getTemperatureText(smithingTemp, unit)).formatted(Formatting.GRAY));
            }
        }
        if (stack.isOf(ModItems.HEV_SUIT_CHESTPLATE) || stack.isOf(ModItems.HEV_SUIT_LEGGINGS) || stack.isOf(ModItems.HEV_SUIT_BOOTS) || getAugmentLevel(stack, "tlotd:battery_pack") > 0) {
            String formattedPower = "0";
            String formattedMaxPower = "0";
            if (Screen.hasShiftDown()) {
                if (stack.hasNbt()) {
                    long powerAmount = EnergyNbtHelper.getEnergy(stack);
                    formattedPower = String.format("%,d", powerAmount);
                    formattedMaxPower = String.format("%,d", EnergyNbtHelper.getMaxEnergyItem(stack));
                }
            } else {
                if (stack.hasNbt()) {
                    long powerAmount = EnergyNbtHelper.getEnergy(stack);
                    formattedPower = EnergyNbtHelper.getEnergyString((int) powerAmount);
                    formattedMaxPower = EnergyNbtHelper.getEnergyString((int) EnergyNbtHelper.getMaxEnergyItem(stack));
                }
            }
            String formattedPower2 = formattedPower.replace(',', '.');
            String formattedMaxPower2 = formattedMaxPower.replace(',', '.');
            tooltip.add(Text.translatable("item.tlotd.power_level.tooltip", formattedPower, formattedMaxPower, formattedPower2, formattedMaxPower2).formatted(Formatting.YELLOW));
        }
        if (stack.getItem() instanceof SpaceSuitArmorItem) {
            Map<String, AdAstraGasNbtHelper.GasInfo> gases = AdAstraGasNbtHelper.getSuitGasContents(stack);
            if (gases.isEmpty()) {
                tooltip.add(Text.translatable("item.tlotd.gas_cylinder.tooltip", "0", "0", "0", "0", AdAstraGasNbtHelper.gasName("empty")).formatted(Formatting.GOLD));
                return;
            }
            for (Map.Entry<String, AdAstraGasNbtHelper.GasInfo> entry : gases.entrySet()) {
                String gas = entry.getKey();
                AdAstraGasNbtHelper.GasInfo info = entry.getValue();
                String formattedGas;
                String formattedMaxGas;
                if (Screen.hasShiftDown()) {
                    int displayAmount = (int)Math.round((double)info.amount * 1000 / AdAstraGasNbtHelper.MAX_AMOUNT);
                    int displayMax = (int)Math.round((double)info.max * 1000 / AdAstraGasNbtHelper.MAX_AMOUNT);
                    formattedGas = String.format("%,d", displayAmount);
                    formattedMaxGas = String.format("%,d", displayMax);
                } else {
                    formattedGas = AdAstraGasNbtHelper.getOxygenString(info.amount);
                    formattedMaxGas = AdAstraGasNbtHelper.getOxygenString(info.max);
                }
                tooltip.add(Text.translatable("item.tlotd.gas_cylinder.tooltip", formattedGas, formattedMaxGas, formattedGas.replace(',', '.'), formattedMaxGas.replace(',', '.'), AdAstraGasNbtHelper.gasName(gas)).formatted(Formatting.GOLD));
            }
            return;
        }
        if (stack.isOf(ModItems.GAS_CYLINDER) || getAugmentLevel(stack, "tlotd:oxygen_tank") > 0) {
            String gas = AdAstraGasNbtHelper.getGas(stack);
            long gasRaw = stack.hasNbt() ? AdAstraGasNbtHelper.getGasAmount(stack, gas) : 0;
            long maxGasRaw = AdAstraGasNbtHelper.getMaxGasItem(stack);
            long maxOxygen = maxGasRaw / AdAstraGasNbtHelper.MAX_AMOUNT;
            String formattedOxygen;
            String formattedMaxOxygen;
            if (Screen.hasShiftDown()) {
                int displayAmount = (int) Math.round((double) gasRaw * 1000 / AdAstraGasNbtHelper.MAX_AMOUNT);
                formattedOxygen = String.format("%,d", displayAmount);
                formattedMaxOxygen = maxOxygen > 0 ? maxOxygen + ",000" : "0";
            } else {
                formattedOxygen = AdAstraGasNbtHelper.getOxygenString(gasRaw);
                formattedMaxOxygen = maxOxygen > 0 ? maxOxygen + "K" : "0";
            }
            String formattedOxygen2 = formattedOxygen.replace(',', '.');
            String formattedMaxOxygen2 = formattedMaxOxygen.replace(',', '.');
            if (gasRaw == 0) {
                gas = "empty";
            }
            tooltip.add(Text.translatable("item.tlotd.gas_cylinder.tooltip", formattedOxygen, formattedMaxOxygen, formattedOxygen2, formattedMaxOxygen2, AdAstraGasNbtHelper.gasName(gas)).formatted(Formatting.GOLD));
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
        final Identifier TENGWAR_FONT_ID = new Identifier("tlotd", "tengwar");
        NbtList augments = new NbtList();
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("augments.tlotd.title").formatted(Formatting.GRAY));
        if (stack.hasNbt() && stack.getNbt().contains("Augments", NbtElement.LIST_TYPE)) {
            augments = stack.getNbt().getList("Augments", NbtElement.COMPOUND_TYPE);
        }
        MutableText iconRow = Text.literal(" ");
        if (Screen.hasShiftDown()) {
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
                    if (id.equals("tlotd:elder_days_elven_forged")) {
                        rarity = Formatting.AQUA;
                        int level = augment.getInt("lvl");
                        if (Screen.hasControlDown()) {
                            MutableText line = Text.literal(" ").append(Text.translatable("augment." + id.replace(':', '.') + ".icon").setStyle(Style.EMPTY.withFont(AUGMENTS_FONT_ID)).append(Text.literal(" ")).append(Text.translatable("augment." + id.replace(':', '.')).setStyle(Style.EMPTY.withFont(DEFAULT_FONT_ID)).formatted(rarity)));
                            if (level > 1) {
                                line.append(" ").append(Text.translatable("enchantment.level." + level).formatted(rarity));
                            }
                            tooltip.add(line);
                            String type = "sword";
                            if (stack.getItem() instanceof PickaxeItem) {type = "pickaxe";}
                            tooltip.add(Text.literal(" ").append(Text.translatable("augment." + id.replace(':', '.') + '.' + type + ".desc").formatted(Formatting.DARK_GRAY)));
                            tooltip.add(Text.literal(" ").append(Text.translatable("augment." + id.replace(':', '.') + '.' + type + ".desc2").formatted(Formatting.DARK_GRAY)));
                        } else {
                            MutableText line = Text.literal(" ").append(Text.translatable("augment." + id.replace(':', '.') + ".icon").setStyle(Style.EMPTY.withFont(AUGMENTS_FONT_ID)).append(Text.literal(" ")).append(Text.translatable("augment." + id.replace(':', '.') + ".tengwar").setStyle(Style.EMPTY.withFont(TENGWAR_FONT_ID)).formatted(rarity)));
                            if (level > 1) {
                                line.append(" ").append(Text.translatable("enchantment.level." + level).formatted(rarity));
                            }
                            tooltip.add(line);
                        }
                    } else {
                        int level = augment.getInt("lvl");
                        MutableText line = Text.literal(" ").append(Text.translatable("augment." + id.replace(':', '.') + ".icon").setStyle(Style.EMPTY.withFont(AUGMENTS_FONT_ID)).append(Text.literal(" ")).append(Text.translatable("augment." + id.replace(':', '.')).setStyle(Style.EMPTY.withFont(DEFAULT_FONT_ID)).formatted(rarity)));
                        if (level > 1) {
                            line.append(" ").append(Text.translatable("enchantment.level." + level).formatted(rarity));
                        }
                        tooltip.add(line);
                        if (Screen.hasControlDown()) {
                            tooltip.add(Text.literal(" ").append(Text.translatable("augment." + id.replace(':', '.') + ".desc").formatted(Formatting.DARK_GRAY)));
                            tooltip.add(Text.literal(" ").append(Text.translatable("augment." + id.replace(':', '.') + ".desc2").formatted(Formatting.DARK_GRAY)));
                        }
                    }
                } else {
                    tooltip.add(Text.literal(" ").append(Text.translatable("augment.tlotd.empty.icon").setStyle(Style.EMPTY.withFont(AUGMENTS_FONT_ID)).append(Text.literal(" ")).append(Text.translatable("augment.tlotd.empty").setStyle(Style.EMPTY.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.DARK_GRAY)))));
                    if (Screen.hasControlDown()) {
                        tooltip.add(Text.literal(" ").append(Text.translatable("augment.tlotd.empty.desc").formatted(Formatting.DARK_GRAY)));
                    }
                }
            }
        } else {
            for (int slot = 0; slot < slots; slot++) {
                if (slot < augments.size()) {
                    String id = augments.getCompound(slot).getString("id");
                    iconRow.append(Text.translatable("augment." + id.replace(':', '.') + ".icon").setStyle(Style.EMPTY.withFont(AUGMENTS_FONT_ID)));
                } else {
                    iconRow.append(Text.translatable("augment.tlotd.empty.icon").setStyle(Style.EMPTY.withFont(AUGMENTS_FONT_ID)));
                }
                iconRow.append(" ");
            }
            tooltip.add(iconRow);
        }
    }
}