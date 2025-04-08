package net.tlotd.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.entity.custom.InfectedTRexEntity;
import net.tlotd.entity.custom.TRexEntity;

public class ModEntities {
    public static final EntityType<TRexEntity> TREX = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TLOTD.MOD_ID, "t-rex"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TRexEntity::new)
                    .dimensions(EntityDimensions.fixed(3f,5f)).build());

    public static final EntityType<InfectedTRexEntity> INFECTED_TREX = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TLOTD.MOD_ID, "infected_t-rex"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, InfectedTRexEntity::new)
                    .dimensions(EntityDimensions.fixed(3f,5f)).build());

    public static void registerModEntities() {
        TLOTD.LOGGER.info("Registering Entities for " + TLOTD.MOD_ID);
    }

}
