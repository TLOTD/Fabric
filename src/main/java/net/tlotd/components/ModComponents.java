package net.tlotd.components;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModComponents {
    public static final ComponentType<Integer> MITHRIL_MIRROR_CHARGES_USED = Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(TLOTD.MOD_ID, "mithril_mirror_charges_used"), ComponentType.<Integer>builder().codec(Codec.INT).packetCodec(PacketCodecs.VAR_INT).build());
    public static final ComponentType<NbtComponent> AUGMENTS = Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(TLOTD.MOD_ID, "augments"), ComponentType.<NbtComponent>builder().codec(NbtComponent.CODEC).build());
    public static final ComponentType<NbtComponent> ITEM_STORAGE = Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(TLOTD.MOD_ID, "item_storage"), ComponentType.<NbtComponent>builder().codec(NbtComponent.CODEC).build());
    public static final ComponentType<Integer> TEMPERATURE = Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(TLOTD.MOD_ID, "temperature"), ComponentType.<Integer>builder().codec(Codec.INT).packetCodec(PacketCodecs.VAR_INT).build());
    public static void register() {
    }
}