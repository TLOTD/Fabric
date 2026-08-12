package net.tlotd.world.dimension;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
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
import net.tlotd.block.enum_property.NoClipable;
import net.tlotd.config.ModConfigs;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static net.minecraft.block.RedstoneLampBlock.LIT;
import static net.tlotd.block.custom.NoClipBlock.NOCLIPABLE;
import static net.tlotd.block.custom.NoClipMoistCarpetBlock.MOISTURE;

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

    private boolean hasPillar(long seed, int segmentX, int segmentZ, int layerIndex) {
        long s = seed;
        s += segmentX * 341873128712L;
        s += segmentZ * 132897987541L;
        s += layerIndex * 918273645231L;  // unique per layer
        s ^= (s >> 33);
        s *= 0xff51afd7ed558ccdL;
        s ^= (s >> 33);
        s *= 0xc4ceb9fe1a85ec53L;
        s ^= (s >> 33);
        return (s & 1L) == 0L;
    }

    @Override
    public CompletableFuture<Chunk> populateNoise(Executor executor, Blender blender, NoiseConfig noiseConfig, StructureAccessor structureAccessor, Chunk chunk) {
        long seed = ModConfigs.LUNAR_SEED;
        BlockState air            = Blocks.AIR.getDefaultState();
        BlockState wall           = ModBlocks.YELLOW_WALLPAPERED_WALL.getDefaultState();
        BlockState baseboardWall  = ModBlocks.YELLOW_WALLPAPERED_WALL_WITH_BASEBOARD.getDefaultState();
        BlockState floorCorridor  = ModBlocks.MOIST_CARPET.getDefaultState();
        BlockState floorUnderWall = ModBlocks.FLOOR_TILE.getDefaultState();
        BlockState ceiling        = ModBlocks.CEILING_TILE.getDefaultState();
        BlockState lamp           = ModBlocks.CEILING_LIGHT.getDefaultState().with(LIT, true);
        BlockState lampPower      = Blocks.REDSTONE_BLOCK.getDefaultState();
        int logicalSize = 4;
        int renderSize  = 5;
        int wallHeight  = 3;
        int floorHeight = 1;
        int ceilingHeight = 1;
        int lampLayerHeight = 1;
        int totalLayerHeight = floorHeight + wallHeight + ceilingHeight + lampLayerHeight;
        ChunkPos chunkPos = chunk.getPos();
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                int worldX = chunkPos.getStartX() + dx;
                int worldZ = chunkPos.getStartZ() + dz;
                int segmentX = Math.floorDiv(worldX, logicalSize);
                int segmentZ = Math.floorDiv(worldZ, logicalSize);
                int localX = Math.floorMod(worldX, logicalSize);
                int localZ = Math.floorMod(worldZ, logicalSize);
                int renderX = (localX == logicalSize ? renderSize - 1 : localX);
                int renderZ = (localZ == logicalSize ? renderSize - 1 : localZ);
                for (int baseY = getMinimumY(); baseY < getWorldHeight(); baseY += totalLayerHeight) {
                    int layerIndex = (baseY - getMinimumY()) / totalLayerHeight;
                    boolean current = hasPillar(seed, segmentX, segmentZ, layerIndex);
                    boolean north   = hasPillar(seed, segmentX, segmentZ - 1, layerIndex);
                    boolean south   = hasPillar(seed, segmentX, segmentZ + 1, layerIndex);
                    boolean west    = hasPillar(seed, segmentX - 1, segmentZ, layerIndex);
                    boolean east    = hasPillar(seed, segmentX + 1, segmentZ, layerIndex);
                    boolean isNorthWall = current && !north && renderZ == 0;
                    boolean isSouthWall = current && !south && renderZ == renderSize - 1;
                    boolean isWestWall  = current && !west && renderX == 0;
                    boolean isEastWall  = current && !east && renderX == renderSize - 1;
                    boolean isPillar    = current && renderX == 0 && renderZ == 0;
                    boolean isWall = isNorthWall || isSouthWall || isWestWall || isEastWall || isPillar;
                    int wallBaseY  = baseY + floorHeight;
                    int ceilingY   = wallBaseY + wallHeight;
                    BlockState floorBlock = isWall ? floorUnderWall : floorCorridor;
                    if (!isWall) {
                        long floorSeed = seed
                                + layerIndex * 918273645231L
                                + worldX * 7342871L
                                + worldZ * 9127831L
                                + baseY * 15485863L;
                        net.minecraft.util.math.random.Random floorRand = net.minecraft.util.math.random.Random.create(floorSeed);
                        if (floorRand.nextDouble() < 0.001) {
                            floorBlock = ModBlocks.MOIST_CARPET.getDefaultState().with(MOISTURE, 1);
                        }
                    }
                    chunk.setBlockState(new BlockPos(dx, baseY, dz), floorBlock, false);
                    for (int y = wallBaseY; y < wallBaseY + wallHeight; y++) {
                        BlockState chosenWall = wall;
                        BlockState bottomWall = baseboardWall;
                        if (isWall) {
                            long segmentSeed = seed + layerIndex * 918273645231L
                                    + segmentX * 341873128712L
                                    + segmentZ * 132897987541L;
                            Random segmentRand = Random.create(segmentSeed);
                            boolean fullSegmentVariant = segmentRand.nextDouble() < 0.0005;
                            if (fullSegmentVariant) {
                                chosenWall = ModBlocks.YELLOW_WALLPAPERED_WALL.getDefaultState().with(NOCLIPABLE, NoClipable.PORTALING);
                                bottomWall = ModBlocks.YELLOW_WALLPAPERED_WALL_WITH_BASEBOARD.getDefaultState().with(NOCLIPABLE, NoClipable.PORTALING);
                            } else {
                                long blockSeed = seed
                                        + layerIndex * 918273645231L
                                        + worldX * 7342871L
                                        + worldZ * 9127831L
                                        + y * 15485863L;
                                Random blockRand = Random.create(blockSeed);
                                if (blockRand.nextDouble() < 0.001) {
                                    chosenWall = ModBlocks.STRIPPED_YELLOW_WALLPAPERED_WALL.getDefaultState();
                                    bottomWall = ModBlocks.YELLOW_WALLPAPERED_WALL_WITH_BASEBOARD_AND_OUTLET.getDefaultState();
                                }
                            }
                            if (y == wallBaseY) {
                                chosenWall = bottomWall;
                            }
                        } else {
                            chosenWall = air;
                        }
                        chunk.setBlockState(new BlockPos(dx, y, dz), chosenWall, false);
                    }
                    chunk.setBlockState(new BlockPos(dx, ceilingY, dz), ceiling, false);
                    chunk.setBlockState(new BlockPos(dx, ceilingY+1, dz), ceiling, false);
                    if (localX == 2 && localZ == 2 && current) {
                        chunk.setBlockState(new BlockPos(dx, ceilingY, dz), lamp, false);
                        chunk.setBlockState(new BlockPos(dx, ceilingY+1, dz), lampPower, false);
                    }
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
        text.add("- .-.. --- - -..");
        text.add("Nullspace: kv31");
    }
}