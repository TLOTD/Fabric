package net.tlotd.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModParticles {
    public static final DefaultParticleType COPPER_FIRE_FLAME = FabricParticleTypes.simple(true);
    public static final DefaultParticleType SULPHUR_FIRE_FLAME = FabricParticleTypes.simple(true);

    public static final DefaultParticleType DRIPPING_BLOOD = FabricParticleTypes.simple();
    public static final DefaultParticleType FALLING_BLOOD = FabricParticleTypes.simple();
    public static final DefaultParticleType LANDING_BLOOD = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(TLOTD.MOD_ID, "copper_fire_flame"), COPPER_FIRE_FLAME);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(TLOTD.MOD_ID, "sulphur_fire_flame"), SULPHUR_FIRE_FLAME);

        Registry.register(Registries.PARTICLE_TYPE, new Identifier(TLOTD.MOD_ID, "dripping_blood"), DRIPPING_BLOOD);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(TLOTD.MOD_ID, "falling_blood"), FALLING_BLOOD);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(TLOTD.MOD_ID, "landing_blood"), LANDING_BLOOD);
    }
}