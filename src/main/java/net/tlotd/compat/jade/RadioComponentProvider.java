package net.tlotd.compat.jade;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec2f;
import net.tlotd.TLOTD;
import net.tlotd.block.custom.RadioBlock;
import net.tlotd.block.entity.RadioBlockEntity;
import net.tlotd.world.RadioStation;
import net.tlotd.world.SignalTrackingArray;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;

public enum RadioComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (accessor.getServerData().getBoolean("noStation")) {
            tooltip.add(Text.translatable("block.tlotd.signal_transmitter.not_found"));
        } else {
            String stationName = accessor.getServerData().getString("stationName");
            tooltip.add(stationName.isEmpty() ? Text.translatable("block.tlotd.signal_transmitter.unnamed") : Text.literal(stationName));
        }
        if (!accessor.getBlockState().get(RadioBlock.ON)) {
            tooltip.add(Text.translatable("block.tlotd.radio.off"));
        } else {
            String currentTrack = accessor.getServerData().getString("currentTrack");
            if (currentTrack.isEmpty()) {
                tooltip.add(Text.translatable("block.tlotd.radio.list_empty"));
            } else {
                Item item = Registries.ITEM.get(Identifier.tryParse(currentTrack));
                IElementHelper elements = tooltip.getElementHelper();
                IElement game_element = elements.item(new ItemStack(item), 0.5f).translate(new Vec2f(0, -1));
                tooltip.add(game_element);
                tooltip.append(Text.literal(" ").append(Text.translatable(item.getTranslationKey() + ".desc")));
            }
        }
    }

    @Override
    public void appendServerData(NbtCompound nbt, BlockAccessor accessor) {
        ServerWorld world = (ServerWorld) accessor.getBlockEntity().getWorld();
        SignalTrackingArray tracker = SignalTrackingArray.get(world);
        RadioStation station = tracker.getBestStation(accessor.getPosition());
        if (station == null) {
            nbt.putBoolean("noStation", true);
        } else {
            nbt.putString("stationName", station.getName());
        }
        RadioBlockEntity radio = (RadioBlockEntity) accessor.getBlockEntity();
        Identifier currentTrack = radio.getCurrentTrack();
        if (currentTrack != null) {
            nbt.putString("currentTrack", currentTrack.toString());
        }
    }

    @Override
    public Identifier getUid() {
        return new Identifier(TLOTD.MOD_ID, "keycard_reader");
    }
}