package net.tlotd.compat.jade;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.entity.HEVChargerBlockEntity;
import net.tlotd.util.EnergyNbtHelper;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum HEVChargerComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        long energy = accessor.getServerData().getLong("energy");
        String energyString = EnergyNbtHelper.getEnergyString(energy);
        tooltip.add(Text.translatable("jade.tlotd.hev_charger.energy", energyString));
    }

    @Override
    public void appendServerData(NbtCompound nbtCompound, BlockAccessor blockAccessor) {
        HEVChargerBlockEntity hevCharger = (HEVChargerBlockEntity) blockAccessor.getBlockEntity();
        long energy = hevCharger.energy.amount;
        nbtCompound.putLong("energy", energy);
    }

    @Override
    public Identifier getUid() {
        return new Identifier(TLOTD.MOD_ID, "hev_charger");
    }
}