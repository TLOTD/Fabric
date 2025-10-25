package net.tlotd.world;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.chunk.ChunkStatus;
import net.tlotd.TLOTD;
import net.tlotd.compat.CompatModsCheck;
import net.tlotd.world.dimension.ModDimensions;

import java.util.Optional;


public class ModChunkEvents {

    public static void generateModWorldGen() {
        ServerChunkEvents.CHUNK_LOAD.register((world, chunk) -> {
            ChunkPos chunkPos = chunk.getPos();

            if (world.getRegistryKey().equals(ModDimensions.LUNA_LEVEL_KEY)) {
                if (chunkPos.x == -6 && chunkPos.z == -6) {
                    world.getServer().execute(() -> {
                        StructurePlacedPersistentState state = StructurePlacedPersistentState.get(world);
                        if (!state.isPlaced()) {
                            placeStructure(world, new Identifier(TLOTD.MOD_ID, "alien_gate"), new BlockPos(-6, -66, -6));
                            state.setPlaced(true);
                            state.markDirty();
                        }
                    });
                }
            }
        });
    }

    public static void placeStructure(ServerWorld world, Identifier id, BlockPos origin) {
        StructureTemplateManager manager = world.getStructureTemplateManager();
        Optional<StructureTemplate> opt = manager.getTemplate(id);
        if (opt.isEmpty()) {
            System.out.println("[DEBUG] Structure " + id + " not found!");
            return;
        }
        StructureTemplate template = opt.get();
        Vec3i size = template.getSize();
        System.out.println("[DEBUG] Placing structure " + id + " at " + origin + " size=" + size);
        StructurePlacementData data = new StructurePlacementData()
                .setMirror(BlockMirror.NONE)
                .setRotation(BlockRotation.NONE)
                .setIgnoreEntities(false);
        if (!world.isChunkLoaded(origin.getX() >> 4, origin.getZ() >> 4)) {
            System.out.println("[WARN] Chunk not loaded for " + id + ", delaying placement");
            return;
        }
        template.place(world, origin, origin, data, world.getRandom(), 2);
    }

    public static void spawnBackroomsStructures(ServerPlayerEntity player) {
        ServerWorld targetWorld = player.getServer().getWorld(ModDimensions.BACKROOMS_LEVEL_KEY);
        if (targetWorld == null) return;
        StructurePlacedPersistentState state = StructurePlacedPersistentState.get(targetWorld);
        if (!state.isPlaced()) {
            player.teleport(targetWorld, 0.5, 28.1, 0.5, 0.0F, 0.5F);
            for (int cx = -1; cx <= 0; cx++) {
                for (int cz = -1; cz <= 0; cz++) {
                    BlockPos lowerPos = new BlockPos(cx * 16, 0, cz * 16);
                    BlockPos upperPos = new BlockPos(cx * 16, 10, cz * 16);
                    targetWorld.getChunk(lowerPos.getX() >> 4, lowerPos.getZ() >> 4, ChunkStatus.FULL, true);
                    targetWorld.getChunk(upperPos.getX() >> 4, upperPos.getZ() >> 4, ChunkStatus.FULL, true);
                    String compat = "";
                    if (cx == -1 && CompatModsCheck.CREATE) {
                        compat = "_create";
                    }
                    Identifier lowerId = new Identifier(TLOTD.MOD_ID, "backrooms_lower_" + cx + "_" + cz);
                    Identifier upperId = new Identifier(TLOTD.MOD_ID, "backrooms_upper_" + cx + "_" + cz + compat);
                    placeStructure(targetWorld, lowerId, lowerPos);
                    placeStructure(targetWorld, upperId, upperPos);
                }
            }
            state.setPlaced(true);
            state.markDirty();
        } else {
            player.teleport(targetWorld, 0.5, 28.1, 0.5, 0.0F, 0.5F);
        }
    }
}