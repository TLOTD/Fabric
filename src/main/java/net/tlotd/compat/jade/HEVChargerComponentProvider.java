package net.tlotd.compat.jade;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.tlotd.TLOTD;
import net.tlotd.block.entity.HEVChargerBlockEntity;
import net.tlotd.item.custom.HEVArmorItem;
import net.tlotd.util.EnergyHelper;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;
import snownee.jade.api.ui.IProgressStyle;
import team.reborn.energy.api.base.SimpleEnergyItem;

public enum HEVChargerComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        NbtCompound data = accessor.getServerData();

        long energy = data.getLong("energy");
        long capacity = 1_000_000L;
        IElementHelper elements = tooltip.getElementHelper();
        float progress = MathHelper.clamp((float) energy / (float) capacity, 0.0f, 1.0f);
        String energyText = EnergyHelper.getEnergyString(energy);
        String capacityText = EnergyHelper.getEnergyString(capacity);
        String energyText2 = energyText.replace(',', '.');
        String capacityText2 = capacityText.replace(',', '.');
        Text chargerText = Text.translatable("item.tlotd.energy_level.tooltip", energyText, capacityText, energyText2, capacityText2);
        IProgressStyle progressStyle = elements.progressStyle().color(0xFF8B0000, 0xFF5A0000).textColor(0xFFAAAAAA);
        tooltip.add(elements.progress(progress, chargerText, progressStyle, EnergyBoxStyle.INSTANCE, false));
        if (data.contains("item", NbtElement.COMPOUND_TYPE)) {
            ItemStack item = ItemStack.fromNbt(data.getCompound("item"));
            if (!item.isEmpty()) {
                IElement icon = elements.item(item, 0.5f).translate(new Vec2f(0, -1));
                tooltip.add(icon);
                tooltip.append(Text.literal(" "));
                tooltip.append(item.getName());
                if (item.getItem() instanceof HEVArmorItem energyItem) {
                    long storedItem = HEVArmorItem.getStoredEnergy(item);
                    long capacityItem = HEVArmorItem.getEnergyCapacity(item);
                    ItemTooltip(tooltip, storedItem, capacityItem);
                } else if (item.getItem() instanceof SimpleEnergyItem energyItem) {
                    long storedItem = energyItem.getStoredEnergy(item);
                    long capacityItem = energyItem.getEnergyCapacity(item);
                    ItemTooltip(tooltip, storedItem, capacityItem);
                }
            }
        }
    }

    private void ItemTooltip(ITooltip tooltip, long stored, long capacityItem) {
        String energyItemText = EnergyHelper.getEnergyString(stored);
        String capacityItemText = EnergyHelper.getEnergyString(capacityItem);
        String energyItemText2 = energyItemText.replace(',', '.');
        String capacityItemText2 = capacityItemText.replace(',', '.');
        tooltip.add(Text.literal(" "));
        tooltip.append(Text.translatable("item.tlotd.energy_level.tooltip", energyItemText, capacityItemText, energyItemText2, capacityItemText2));
    }

    @Override
    public void appendServerData(NbtCompound nbt, BlockAccessor accessor) {
        HEVChargerBlockEntity hevCharger = (HEVChargerBlockEntity) accessor.getBlockEntity();
        nbt.putLong("energy", hevCharger.energy.amount);
        if (!hevCharger.getItem().isEmpty()) {
            nbt.put("item", hevCharger.getItem().writeNbt(new NbtCompound()));
        }
    }

    @Override
    public Identifier getUid() {
        return new Identifier(TLOTD.MOD_ID, "hev_charger");
    }
}