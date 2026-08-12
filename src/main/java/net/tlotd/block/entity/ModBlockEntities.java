package net.tlotd.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;
import net.tlotd.block.custom.HEVChargerBlock;
import team.reborn.energy.api.EnergyStorage;

public class ModBlockEntities {
    public static final BlockEntityType<OutletBlockEntity> OUTLET_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "outlet_block_entity"), FabricBlockEntityTypeBuilder.create(OutletBlockEntity::new, ModBlocks.YELLOW_WALLPAPERED_WALL_WITH_BASEBOARD_AND_OUTLET).build());

    public static final BlockEntityType<HEVChargerBlockEntity> HEV_CHARGER_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "hev_charger_block_entity"), FabricBlockEntityTypeBuilder.create(HEVChargerBlockEntity::new, ModBlocks.HEV_CHARGER).build());

    public static final BlockEntityType<SignalTransmitterBlockEntity> SIGNAL_TRANSMITTER_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "signal_transmitter_block_entity"), FabricBlockEntityTypeBuilder.create(SignalTransmitterBlockEntity::new, ModBlocks.SIGNAL_TRANSMITTER).build());

    public static final BlockEntityType<RadioBlockEntity> RADIO_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "radio_block_entity"), FabricBlockEntityTypeBuilder.create(RadioBlockEntity::new, ModBlocks.RADIO).build());

    public static final BlockEntityType<DwarvenForgeBlockEntity> DWARVEN_FORGE_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "dwarven_forge_block_entity"), FabricBlockEntityTypeBuilder.create(DwarvenForgeBlockEntity::new, ModBlocks.DWARVEN_FORGE).build());

    public static final BlockEntityType<NetheriteAnvilBlockEntity> NETHERITE_ANVIL_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "netherite_anvil_block_entity"), FabricBlockEntityTypeBuilder.create(NetheriteAnvilBlockEntity::new, ModBlocks.NETHERITE_ANVIL).build());

    public static final BlockEntityType<MithrilAnvilBlockEntity> MITHRIL_ANVIL_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "mithril_anvil_block_entity"), FabricBlockEntityTypeBuilder.create(MithrilAnvilBlockEntity::new, ModBlocks.MITHRIL_ANVIL).build());

    public static final BlockEntityType<AugmentationTableBlockEntity> AUGMENTATION_TABLE_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "augmentation_table_block_entity"), FabricBlockEntityTypeBuilder.create(AugmentationTableBlockEntity::new, ModBlocks.AUGMENTATION_TABLE).build());

    public static final BlockEntityType<WitchingTableBlockEntity> WITCHING_TABLE_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "witching_table_block_entity"), FabricBlockEntityTypeBuilder.create(WitchingTableBlockEntity::new, ModBlocks.WITCHING_TABLE).build());

    public static final BlockEntityType<OxygenCollectorBlockEntity> OXYGEN_COLLECTOR_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "oxygen_collector_block_entity"), FabricBlockEntityTypeBuilder.create(OxygenCollectorBlockEntity::new, ModBlocks.OXYGEN_COLLECTOR).build());

    public static final BlockEntityType<KeycardReaderBlockEntity> KEYCARD_READER_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "keycard_reader_block_entity"), FabricBlockEntityTypeBuilder.create(KeycardReaderBlockEntity::new, ModBlocks.KEYCARD_READER).build());

    public static final BlockEntityType<KeycardProgrammerBlockEntity> KEYCARD_PROGRAMMER_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "keycard_programmer_block_entity"), FabricBlockEntityTypeBuilder.create(KeycardProgrammerBlockEntity::new, ModBlocks.KEYCARD_PROGRAMMER).build());

    public static final BlockEntityType<IncubatorBlockEntity> INCUBATOR_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "incubator_block_entity"), FabricBlockEntityTypeBuilder.create(IncubatorBlockEntity::new, ModBlocks.INCUBATOR).build());

    public static final BlockEntityType<GarbageCanBlockEntity> GARBAGE_CAN_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "garbage_can_block_entity"), FabricBlockEntityTypeBuilder.create(GarbageCanBlockEntity::new, ModBlocks.GARBAGE_CAN).build());

    public static final BlockEntityType<TeleporterBlockEntity> TELEPORTER_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TLOTD.MOD_ID, "teleporter_block_entity"), FabricBlockEntityTypeBuilder.create(TeleporterBlockEntity::new, ModBlocks.TELEPORTER).build());

    public static void registerBlockEntities() {
        FluidStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> {
            if (direction == Direction.UP) {
                return null;
            } else return blockEntity.fluidStorage;
        }, WITCHING_TABLE_BLOCK_ENTITY);
        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> {
            BlockState state = blockEntity.getCachedState();
            WallMountLocation face = state.get(HEVChargerBlock.FACE);
            Direction facing = state.get(HEVChargerBlock.FACING);
            Direction allowedSide = switch (face) {
                case CEILING -> Direction.UP;
                case FLOOR -> Direction.DOWN;
                case WALL -> facing;
            };
            return direction == allowedSide ? blockEntity.inputOnlyEnergy : null;
        }, HEV_CHARGER_BLOCK_ENTITY);
        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> {
            if (direction.getAxis() == Direction.Axis.Y) {
                return null;
            }
            return blockEntity.energy;
        }, ModBlockEntities.OUTLET_BLOCK_ENTITY);
        TLOTD.LOGGER.info("Registering Block Entities for " + TLOTD.MOD_ID);
    }
}