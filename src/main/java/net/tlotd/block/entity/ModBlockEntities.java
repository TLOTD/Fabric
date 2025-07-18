package net.tlotd.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;

public class ModBlockEntities {
    public static final BlockEntityType<MithrilAnvilBlockEntity> MITHRIL_ANVIL_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "mithril_anvil_block_entity"),
                    FabricBlockEntityTypeBuilder.create(MithrilAnvilBlockEntity::new,
                            ModBlocks.MITHRIL_ANVIL).build());

    public static final BlockEntityType<WitchingTableBlockEntity> WITCHING_TABLE_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "witching_table_block_entity"),
                    FabricBlockEntityTypeBuilder.create(WitchingTableBlockEntity::new,
                            ModBlocks.WITCHING_TABLE).build());

    public static final BlockEntityType<OxygenCollectorBlockEntity> OXYGEN_COLLECTOR_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "oxygen_collector_block_entity"),
                    FabricBlockEntityTypeBuilder.create(OxygenCollectorBlockEntity::new,
                            ModBlocks.OXYGEN_COLLECTOR).build());

    public static final BlockEntityType<KeycardReaderBlockEntity> KEYCARD_READER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "keycard_reader_block_entity"),
                    FabricBlockEntityTypeBuilder.create(KeycardReaderBlockEntity::new,
                            ModBlocks.KEYCARD_READER).build());

    public static final BlockEntityType<KeycardProgrammerBlockEntity> KEYCARD_PROGRAMMER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "keycard_programmer_block_entity"),
                    FabricBlockEntityTypeBuilder.create(KeycardProgrammerBlockEntity::new,
                            ModBlocks.KEYCARD_PROGRAMMER).build());

    public static final BlockEntityType<IncubatorBlockEntity> INCUBATOR_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "incubator_block_entity"),
                    FabricBlockEntityTypeBuilder.create(IncubatorBlockEntity::new,
                            ModBlocks.INCUBATOR).build());

    public static final BlockEntityType<GarbageCanBlockEntity> GARBAGE_CAN_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "garbage_can_block_entity"),
                    FabricBlockEntityTypeBuilder.create(GarbageCanBlockEntity::new,
                            ModBlocks.GARBAGE_CAN).build());

    public static final BlockEntityType<TeleporterBlockEntity> TELEPORTER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "teleporter_block_entity"),
                    FabricBlockEntityTypeBuilder.create(TeleporterBlockEntity::new,
                            ModBlocks.TELEPORTER).build());

    public static void registerBlockEntities() {
        FluidStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.fluidStorage, WITCHING_TABLE_BLOCK_ENTITY);

        TLOTD.LOGGER.info("Registering Block Entities for " + TLOTD.MOD_ID);
    }
}