package net.tlotd.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.entity.custom.ArmorPiercingArrowEntity;
import net.tlotd.entity.custom.InfectedTRexEntity;
import net.tlotd.entity.custom.SeatEntity;
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

    public static final EntityType<SeatEntity> SEAT = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(TLOTD.MOD_ID, "seat"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, SeatEntity::new)
                    .dimensions(EntityDimensions.fixed(0f,0f)).build());

    public static final EntityType<ArmorPiercingArrowEntity> ARMOR_PIERCING_ARROW =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    new Identifier(TLOTD.MOD_ID, "silverthorn_arrow"),
                    FabricEntityTypeBuilder.<ArmorPiercingArrowEntity>create(SpawnGroup.MISC, ArmorPiercingArrowEntity::new)
                            .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                            .trackRangeBlocks(4)
                            .trackedUpdateRate(20)
                            .build()
            );
    public static void registerModEntities() {
        FabricDefaultAttributeRegistry.register(ModEntities.TREX, TRexEntity.createTRexAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.INFECTED_TREX, InfectedTRexEntity.createInfectedTRexAttributes());
        TLOTD.LOGGER.info("Registering Entities for " + TLOTD.MOD_ID);
    }

}
