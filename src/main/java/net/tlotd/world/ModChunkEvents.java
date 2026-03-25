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
import net.tlotd.item.ModItems;
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
        SignalTrackingArray tracker = SignalTrackingArray.get(targetWorld);
        if (!tracker.hasSignal(ModItems.BACKROOMS_SIGNAL)) {
            tracker.addSignal(ModItems.BACKROOMS_SIGNAL);
        }
        if (!tracker.hasSignal(ModItems.VHS_CASSETTE_PROJECT_KV31)) {
            tracker.addSignal(ModItems.VHS_CASSETTE_PROJECT_KV31);
        }
        player.teleport(targetWorld, -9.5, -1, 2.5, 0.0F, 0.5F);
    }
}