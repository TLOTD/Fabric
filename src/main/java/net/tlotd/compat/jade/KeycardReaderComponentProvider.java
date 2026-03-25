package net.tlotd.compat.jade;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.entity.KeycardReaderBlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum KeycardReaderComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        boolean passwordSet = accessor.getServerData().getBoolean("password_set");
        Formatting color = Formatting.RED;
        if (passwordSet) { color = Formatting.GREEN;}
        tooltip.add(Text.translatable("jade.tlotd.keycard_reader.password_set").append(Text.literal(" ")).append(Text.translatable("jade.tlotd.keycard_reader.password_set." + passwordSet).formatted(color)));
    }

    @Override
    public void appendServerData(NbtCompound nbtCompound, BlockAccessor blockAccessor) {
        KeycardReaderBlockEntity keycard_reader = (KeycardReaderBlockEntity) blockAccessor.getBlockEntity();
        nbtCompound.putBoolean("password_set", !keycard_reader.hasPassword());
    }

    @Override
    public Identifier getUid() {
        return new Identifier(TLOTD.MOD_ID, "keycard_reader");
    }
}