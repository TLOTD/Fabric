package net.tlotd.world.tree;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.tlotd.world.ModConfiguredFeatures;

public class GinkgoSaplingGenerator extends SaplingGenerator {
    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return ModConfiguredFeatures.GINKGO_KEY;
    }

    @Override
    public boolean generate(ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random random) {
        for (int dx = 0; dx >= -1; --dx) {
            for (int dz = 0; dz >= -1; --dz) {
                if (this.isTwoByTwoSapling(state, world, pos, dx, dz)) {
                    RegistryKey<ConfiguredFeature<?, ?>> megaTree = ModConfiguredFeatures.MEGA_GINKGO_KEY;
                    ConfiguredFeature<?, ?> configuredFeature = world.getRegistryManager()
                            .get(RegistryKeys.CONFIGURED_FEATURE)
                            .get(megaTree.getValue());
                    if (configuredFeature != null) {
                        clearSaplings(world, pos, dx, dz);
                        configuredFeature.generate(world, chunkGenerator, random, pos.add(dx, 0, dz));
                        return true;
                    }
                }
            }
        }
        return super.generate(world, chunkGenerator, pos, state, random);
    }

    private boolean isTwoByTwoSapling(BlockState state, ServerWorld world, BlockPos pos, int dx, int dz) {
        Block block = state.getBlock();
        return world.getBlockState(pos.add(dx, 0, dz)).isOf(block)
                && world.getBlockState(pos.add(dx + 1, 0, dz)).isOf(block)
                && world.getBlockState(pos.add(dx, 0, dz + 1)).isOf(block)
                && world.getBlockState(pos.add(dx + 1, 0, dz + 1)).isOf(block);
    }

    private void clearSaplings(ServerWorld world, BlockPos pos, int dx, int dz) {
        for (int x = 0; x < 2; x++) {
            for (int z = 0; z < 2; z++) {
                world.removeBlock(pos.add(dx + x, 0, dz + z), false);
            }
        }
    }
}