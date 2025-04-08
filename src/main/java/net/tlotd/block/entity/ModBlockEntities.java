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

    public static void registerBlockEntities() {
        FluidStorage.SIDED.registerForBlockEntity((blockEntity, direction) -> blockEntity.fluidStorage, WITCHING_TABLE_BLOCK_ENTITY);

        TLOTD.LOGGER.info("Registering Block Entities for " + TLOTD.MOD_ID);
    }

}
