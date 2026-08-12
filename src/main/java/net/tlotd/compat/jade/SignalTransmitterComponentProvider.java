package net.tlotd.compat.jade;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec2f;
import net.tlotd.TLOTD;
import net.tlotd.world.RadioStation;
import net.tlotd.world.SignalTrackingArray;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElementHelper;

public enum SignalTransmitterComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        NbtList tracks = accessor.getServerData().getList("tracks", NbtElement.STRING_TYPE);
        boolean active = accessor.getServerData().getBoolean("active");
        int strength = accessor.getServerData().getInt("strength");
        int range = accessor.getServerData().getInt("range");
        int trackCount = tracks.size();
        tooltip.add(SignalTrackingArray.getStats(active, strength, range, trackCount, ".1"));
        tooltip.add(SignalTrackingArray.getStats(active, strength, range, trackCount, ".2"));
        if (tracks.isEmpty()) {
            return;
        }
        IElementHelper helper = tooltip.getElementHelper();
        for (NbtElement element : tracks) {
            Identifier id = Identifier.tryParse(element.asString());
            if (id == null) continue;
            Item item = Registries.ITEM.get(id);
            tooltip.add(helper.item(new ItemStack(item), 0.5f).translate(new Vec2f(0, -1)));
            tooltip.append(Text.literal(" ").append(Text.translatable(item.getTranslationKey())).append(Text.literal(": ")).append(Text.translatable(item.getTranslationKey() + ".desc")).formatted(Formatting.GRAY));
        }
    }

    @Override
    public void appendServerData(NbtCompound nbt, BlockAccessor accessor) {
        ServerWorld world = (ServerWorld) accessor.getBlockEntity().getWorld();
        SignalTrackingArray tracker = SignalTrackingArray.get(world);
        RadioStation station = tracker.getStation(accessor.getPosition());
        if (station == null) {
            return;
        }
        nbt.putBoolean("active", station.isActive());
        nbt.putInt("strength", station.getStrength());
        nbt.putInt("range", station.getRange());
        NbtList tracks = new NbtList();
        for (Identifier id : station.getSignals()) {
            tracks.add(NbtString.of(id.toString()));
        }
        nbt.put("tracks", tracks);
    }

    @Override
    public Identifier getUid() {
        return new Identifier(TLOTD.MOD_ID, "keycard_reader");
    }
}