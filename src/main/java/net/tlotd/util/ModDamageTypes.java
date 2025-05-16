package net.tlotd.util;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tlotd.TLOTD;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> RADIATION = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(TLOTD.MOD_ID, "radiation"));
    public static final RegistryKey<DamageType> HYPOXIA = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(TLOTD.MOD_ID, "hypoxia"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
