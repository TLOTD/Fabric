package net.tlotd.world;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.tlotd.TLOTD;
import net.tlotd.world.dimension.ModDimensions;

import java.util.Optional;

public class ModChunkEvents {
    public static final Identifier ORIGIN_STRUCTURE_ID = new Identifier(TLOTD.MOD_ID, "alien_gate");
    public static void generateModWorldGen() {
        ServerChunkEvents.CHUNK_LOAD.register((world, chunk) -> {
            if (!world.getRegistryKey().equals(ModDimensions.LUNA_LEVEL_KEY)) return;

            ChunkPos chunkPos = chunk.getPos();
            if (chunkPos.x == -6 && chunkPos.z == -6) {
                world.getServer().execute(() -> {
                    OriginPlacedPersistentState state = OriginPlacedPersistentState.get(world);
                    if (!state.isPlaced()) {
                        BlockPos origin = new BlockPos(-6, -66, -6);
                        StructureTemplateManager manager = world.getStructureTemplateManager();
                        Optional<StructureTemplate> templateOpt =
                                manager.getTemplate(ORIGIN_STRUCTURE_ID);

                        templateOpt.ifPresent(template -> {
                            StructurePlacementData placementData = new StructurePlacementData()
                                    .setMirror(BlockMirror.NONE)
                                    .setRotation(BlockRotation.NONE)
                                    .setIgnoreEntities(false);

                            template.place(world, origin, origin, placementData, world.getRandom(), 2);
                            state.setPlaced(true);
                        });
                    }
                });
            }
        });
    }
}
