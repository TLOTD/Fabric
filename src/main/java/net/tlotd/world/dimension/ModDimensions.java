package net.tlotd.world.dimension;

import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.tlotd.TLOTD;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import net.tlotd.util.ModTags;
import net.tlotd.world.biome.ModBiomes;

import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> PREHISTORIC_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            new Identifier(TLOTD.MOD_ID, "prehistoric"));
    public static final RegistryKey<World> PREHISTORIC_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(TLOTD.MOD_ID, "prehistoric"));
    public static final RegistryKey<DimensionType> PREHISTORIC_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(TLOTD.MOD_ID, "prehistoric"));

    public static final RegistryKey<DimensionOptions> LUNA_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            new Identifier(TLOTD.MOD_ID, "luna"));
    public static final RegistryKey<World> LUNA_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(TLOTD.MOD_ID, "luna"));
    public static final RegistryKey<DimensionType> LUNA_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(TLOTD.MOD_ID, "luna"));

    public static final RegistryKey<DimensionOptions> BACKROOMS_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            new Identifier(TLOTD.MOD_ID, "backrooms"));
    public static final RegistryKey<World> BACKROOMS_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(TLOTD.MOD_ID, "backrooms"));
    public static final RegistryKey<DimensionType> BACKROOMS_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(TLOTD.MOD_ID, "backrooms"));

    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(PREHISTORIC_TYPE, new DimensionType(
                OptionalLong.of(12000), // fixedTime
                true, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                1.0, // coordinateScale
                true, // bedWorks
                true, // respawnAnchorWorks
                -64, // minY
                384, // height
                320, // logicalHeight
                ModTags.Blocks.INFINIBURN_PREHISTORIC, // infiniburn
                DimensionTypes.OVERWORLD_ID, // effectsLocation
                0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));

        context.register(LUNA_TYPE, new DimensionType(
                OptionalLong.of(0),
                true,
                false,
                false,
                false,
                1.0,
                false,
                true,
                -128,
                448,
                320,
                ModTags.Blocks.INFINIBURN_LUNA,
                DimensionTypes.THE_END_ID,
                0f,
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));

        context.register(BACKROOMS_TYPE, new DimensionType(
                OptionalLong.of(0),
                true,
                false,
                false,
                false,
                1.0,
                false,
                false,
                -128,
                448,
                320,
                ModTags.Blocks.INFINIBURN_BACKROOMS,
                DimensionTypes.THE_END_ID,
                0f,
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));
    }

    public static void bootstrapDimension(Registerable<DimensionOptions> context) {
        RegistryEntryLookup<DimensionType> dimensionTypes = context.getRegistryLookup(RegistryKeys.DIMENSION_TYPE);
        RegistryEntryLookup<Biome> biomes = context.getRegistryLookup(RegistryKeys.BIOME);

        RegistryEntry<DimensionType> prehistoricDimensionType = dimensionTypes.getOrThrow(PREHISTORIC_TYPE);
        RegistryEntry<DimensionType> lunaDimensionType = dimensionTypes.getOrThrow(LUNA_TYPE);
        RegistryEntry<DimensionType> backroomsDimensionType = dimensionTypes.getOrThrow(BACKROOMS_TYPE);

        RegistryEntry<Biome> prehistoricJungle = biomes.getOrThrow(ModBiomes.PREHISTORIC_JUNGLE);
        RegistryEntry<Biome> lunarHighlands = biomes.getOrThrow(ModBiomes.LUNAR_HIGHLANDS);
        RegistryEntry<Biome> level1 = biomes.getOrThrow(ModBiomes.LEVEL_1);

        FixedBiomeSource prehistoricSource = new FixedBiomeSource(prehistoricJungle);
        FixedBiomeSource lunarSource = new FixedBiomeSource(lunarHighlands);
        FixedBiomeSource backroomsSource = new FixedBiomeSource(level1);

        PrehistoricChunkGenerator prehistoricGenerator = new PrehistoricChunkGenerator(prehistoricSource);
        LunarChunkGenerator lunarGenerator = new LunarChunkGenerator(lunarSource);
        BackroomsChunkGenerator backroomsGenerator = new BackroomsChunkGenerator(backroomsSource);

        context.register(PREHISTORIC_KEY, new DimensionOptions(prehistoricDimensionType, prehistoricGenerator));
        context.register(LUNA_KEY, new DimensionOptions(lunaDimensionType, lunarGenerator));
        context.register(BACKROOMS_KEY, new DimensionOptions(backroomsDimensionType, backroomsGenerator));
    }
}