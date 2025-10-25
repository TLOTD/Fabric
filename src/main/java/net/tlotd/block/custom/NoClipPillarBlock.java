package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkStatus;
import net.tlotd.config.ModConfigs;
import net.tlotd.world.ModChunkEvents;
import net.tlotd.world.dimension.ModDimensions;

public class NoClipPillarBlock extends PillarBlock {
    public NoClipPillarBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0F, 0F, 0F, 0F, 0F, 0F);
    }

    @Override
    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.fullCube();
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient()) {
            if (entity instanceof ServerPlayerEntity serverPlayer) {
                if (!serverPlayer.getWorld().getRegistryKey().equals(ModDimensions.BACKROOMS_LEVEL_KEY)) {
                    ModChunkEvents.spawnBackroomsStructures(serverPlayer);
                } else {
                    ServerWorld targetWorld = serverPlayer.getServer().getWorld(World.OVERWORLD);
                    if (targetWorld != null) {
                        targetWorld.getChunk(0 >> 4, 0 >> 4, ChunkStatus.FULL, true);
                        serverPlayer.teleport(targetWorld, 0.5, ModConfigs.TERRA_WARP_DESTINATION_HEIGHT, 0.5, 0.0F, 0.5F);
                        if (ModConfigs.TERRA_FALL_DISTANCE_RESISTANCE != 0) {
                            serverPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,
                                    ModConfigs.TERRA_FALL_DISTANCE_RESISTANCE, 4, false, false, true));
                        }
                    }
                }
            }
        }
    }
}
