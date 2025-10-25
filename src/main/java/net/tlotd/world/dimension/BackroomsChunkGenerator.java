package net.tlotd.world.dimension;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.tlotd.block.ModBlocks;
import net.tlotd.config.ModConfigs;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class BackroomsChunkGenerator extends ChunkGenerator {

    public static final Codec<BackroomsChunkGenerator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BiomeSource.CODEC.fieldOf("biome_source").forGetter(generator -> generator.biomeSource)
    ).apply(instance, BackroomsChunkGenerator::new));

    private final BiomeSource biomeSource;
    private final PerlinNoiseSampler heightNoise;

    public BackroomsChunkGenerator(BiomeSource biomeSource) {
        super(biomeSource);
        this.biomeSource = biomeSource;
        this.heightNoise = new PerlinNoiseSampler(Random.create(ModConfigs.LUNAR_SEED));
    }

    @Override
    protected Codec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public void carve(ChunkRegion chunkRegion, long seed, NoiseConfig noiseConfig, BiomeAccess biomeAccess, StructureAccessor structureAccessor, Chunk chunk, GenerationStep.Carver carverStep) {
    }

    @Override
    public void buildSurface(ChunkRegion region, StructureAccessor structures, NoiseConfig noiseConfig, Chunk chunk) {
    }

    @Override
    public void populateEntities(ChunkRegion region) {
    }

    @Override
    public int getWorldHeight() {
        return 320;
    }

    @Override
    public CompletableFuture<Chunk> populateNoise(Executor executor, Blender blender, NoiseConfig noiseConfig, StructureAccessor structureAccessor, Chunk chunk) {
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                for (int dy = getMinimumY(); dy < 28; dy++) {
                    chunk.setBlockState(new BlockPos(dx, dy, dz), ModBlocks.YELLOW_WALLPAPERED_WALL.getDefaultState(), false);
                }
            }
        }
        return CompletableFuture.completedFuture(chunk);
    }

    @Override
    public int getSeaLevel() {
        return -128;
    }

    @Override
    public int getMinimumY() {
        return -128;
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmap, HeightLimitView world, NoiseConfig config) {
        double noise = heightNoise.sample(x * 0.05, 0, z * 0.05);
        return 48 + (int)(noise * 16);
    }

    @Override
    public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world, NoiseConfig config) {
        int height = getHeight(x, z, Heightmap.Type.WORLD_SURFACE, world, config);
        BlockState[] states = new BlockState[height];
        Arrays.fill(states, Blocks.STONE.getDefaultState());
        return new VerticalBlockSample(0, states);
    }

    @Override
    public void getDebugHudText(List<String> text, NoiseConfig noiseConfig, BlockPos pos) {
        text.add("?????");
    }
}