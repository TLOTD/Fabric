package net.tlotd.compat.jade;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.entity.OxygenCollectorBlockEntity;
import net.tlotd.util.AdAstraGasNbtHelper;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum OxygenCollectorComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        NbtCompound data = accessor.getServerData();
        int gas = data.getInt("gas");
        int amount = data.getInt("amount");
        String gasText = "gas.ad_astra.oxygen";
        Formatting formatting = Formatting.AQUA;
        if (gas == 2) {
            gasText = "gas.tlotd.withered_air";
            formatting = Formatting.RED;
        } else if (gas == 1) {
            gasText = "gas.tlotd.pipe_weed_smoke";
            formatting = Formatting.YELLOW;
        }
        String formattedGas;
        String formattedMaxGas;
        if (Screen.hasShiftDown()) {
            int displayAmount = (int) Math.round((double) amount * 1000 / AdAstraGasNbtHelper.MAX_AMOUNT);
            int displayMax = 1000;
            formattedGas = String.format("%,d", displayAmount);
            formattedMaxGas = String.format("%,d", displayMax);
        } else {
            formattedGas = AdAstraGasNbtHelper.getOxygenString(amount);
            formattedMaxGas = AdAstraGasNbtHelper.getOxygenString(AdAstraGasNbtHelper.MAX_AMOUNT);
        }
        tooltip.add(Text.translatable("gui.tlotd.oxygen_collector.oxygen_percentage").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("gui.tlotd.oxygen_collector.oxygen_percentage." + gas, amount).formatted(formatting));
        tooltip.add(Text.translatable("gui.tlotd.oxygen_collector.gas").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.tlotd.gas_cylinder.tooltip", formattedGas, formattedMaxGas, formattedGas.replace(',', '.'), formattedMaxGas.replace(',', '.'), Text.translatable(gasText)).formatted(formatting));
    }

    @Override
    public void appendServerData(NbtCompound nbt, BlockAccessor accessor) {
        OxygenCollectorBlockEntity oxygenCollector = (OxygenCollectorBlockEntity) accessor.getBlockEntity();
        nbt.putInt("gas", oxygenCollector.getGas());
        nbt.putInt("amount", oxygenCollector.getAmount());
    }

    @Override
    public Identifier getUid() {
        return new Identifier(TLOTD.MOD_ID, "hev_charger");
    }
}