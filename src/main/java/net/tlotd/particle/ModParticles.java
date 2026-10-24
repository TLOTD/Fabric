package net.tlotd.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModParticles {
    public static final SimpleParticleType COPPER_FIRE_FLAME = FabricParticleTypes.simple(true);
    public static final SimpleParticleType SULPHUR_FIRE_FLAME = FabricParticleTypes.simple(true);

    public static final SimpleParticleType DRIPPING_BLOOD = FabricParticleTypes.simple();
    public static final SimpleParticleType FALLING_BLOOD = FabricParticleTypes.simple();
    public static final SimpleParticleType LANDING_BLOOD = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(TLOTD.MOD_ID, "copper_fire_flame"), COPPER_FIRE_FLAME);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(TLOTD.MOD_ID, "sulphur_fire_flame"), SULPHUR_FIRE_FLAME);

        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(TLOTD.MOD_ID, "dripping_blood"), DRIPPING_BLOOD);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(TLOTD.MOD_ID, "falling_blood"), FALLING_BLOOD);
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(TLOTD.MOD_ID, "landing_blood"), LANDING_BLOOD);
    }
}