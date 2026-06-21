package net.tlotd.compat.jade;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.entity.DwarvenForgeBlockEntity;
import net.tlotd.util.ItemHeatHelper;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum DwarvenForgeComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        int temperatureTarget = accessor.getServerData().getInt("temperatureTarget");
        int temperature = accessor.getServerData().getInt("temperature");
        int burnTime = accessor.getServerData().getInt("burnTime");
        if ((temperatureTarget != 0) && (burnTime != 0)) {
            tooltip.add(Text.translatable("temperature.tlotd.dwarven_forge.max", ItemHeatHelper.getTemperatureText(temperatureTarget), ItemHeatHelper.getBurningTime(burnTime)));
        }
        if (temperature != 0) {
            tooltip.add(Text.translatable("temperature.tlotd.dwarven_forge", ItemHeatHelper.getTemperatureText(temperature)));
        }
    }

    @Override
    public void appendServerData(NbtCompound nbtCompound, BlockAccessor blockAccessor) {
        DwarvenForgeBlockEntity forge = (DwarvenForgeBlockEntity) blockAccessor.getBlockEntity();
        int temperature = forge.getCurrentTemperature();
        int temperatureTarget = forge.getTemperatureTarget();
        int burnTime = forge.getRemainingBurnTime();
        nbtCompound.putInt("temperature", temperature);
        nbtCompound.putInt("temperatureTarget", temperatureTarget);
        nbtCompound.putInt("burnTime", burnTime);
    }

    @Override
    public Identifier getUid() {
        return new Identifier(TLOTD.MOD_ID, "dwarven_forge");
    }
}