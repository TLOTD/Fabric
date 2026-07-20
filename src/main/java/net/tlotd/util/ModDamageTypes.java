package net.tlotd.util;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.tlotd.TLOTD;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> BOILING = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(TLOTD.MOD_ID, "boiling"));

    public static final RegistryKey<DamageType> SUBSPACE_SICKNESS = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(TLOTD.MOD_ID, "subspace_sickness"));

    public static final RegistryKey<DamageType> RADIATION = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(TLOTD.MOD_ID, "radiation"));
    public static final RegistryKey<DamageType> HYPOXIA = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(TLOTD.MOD_ID, "hypoxia"));

    public static final RegistryKey<DamageType> BULLET = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(TLOTD.MOD_ID, "bullet"));

    public static final RegistryKey<DamageType> SILVERTHORN_ARROW = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(TLOTD.MOD_ID, "silverthorn_arrow"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
