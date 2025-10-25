package net.tlotd.world.biome;

import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.tlotd.TLOTD;
import net.tlotd.entity.ModEntities;
import net.tlotd.world.ModPlacedFeatures;

public class ModBiomes {
    public static final RegistryKey<Biome> PREHISTORIC_JUNGLE = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(TLOTD.MOD_ID,"prehistoric_jungle")
    );
    public static final RegistryKey<Biome> LUNAR_HIGHLANDS = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(TLOTD.MOD_ID,"lunar_highlands")
    );
    public static final RegistryKey<Biome> LEVEL_1 = RegistryKey.of(RegistryKeys.BIOME,
            new Identifier(TLOTD.MOD_ID,"level_1")
    );

    public static void bootstrap(Registerable<Biome> context) {
        context.register(PREHISTORIC_JUNGLE, prehistoricJungle(context));
        context.register(LUNAR_HIGHLANDS, lunarHighlands(context));
        context.register(LEVEL_1, level1(context));
    }

    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);
    }

    public static Biome prehistoricJungle(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.TREX, 2, 1, 3));

        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addMossyRocks(biomeBuilder);

        biomeBuilder.feature(
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.MEGA_GINKGO_TREE_PLACED_KEY
        );
        biomeBuilder.feature(
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.GINKGO_TREE_PLACED_KEY
        );
        DefaultBiomeFeatures.addJungleTrees(biomeBuilder);
        DefaultBiomeFeatures.addDefaultDisks(biomeBuilder);
        DefaultBiomeFeatures.addDefaultGrass(biomeBuilder);
        DefaultBiomeFeatures.addJungleGrass(biomeBuilder);
        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(1.2f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x00BA95)
                        .waterFogColor(0x00BA95)
                        .grassColor(0x558A2B)
                        .foliageColor(0x558A2B)
                        .fogColor(0xCCCCFF)
                        .skyColor(0xCCCCFF)
                        .moodSound(BiomeMoodSound.CAVE)
                        .music(MusicType.GAME)
                        .build()
                ).build();
    }

    public static Biome lunarHighlands(Registerable<Biome> context) {

        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        DefaultBiomeFeatures.addLandCarvers(biomeBuilder);
        DefaultBiomeFeatures.addMineables(biomeBuilder);

        return new Biome.Builder()
                .precipitation(false)
                .downfall(0.0f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x526675)
                        .waterFogColor(0x526675)
                        .grassColor(0x577552)
                        .foliageColor(0x577552)
                        .fogColor(0x000000)
                        .skyColor(0x000000)
                        .moodSound(BiomeMoodSound.CAVE)
                        .music(MusicType.GAME)
                        .build()
                ).build();
    }

    public static Biome level1(Registerable<Biome> context) {

        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        return new Biome.Builder()
                .precipitation(false)
                .downfall(0.0f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0x526675)
                        .waterFogColor(0x526675)
                        .grassColor(0x577552)
                        .foliageColor(0x577552)
                        .fogColor(0x000000)
                        .skyColor(0x000000)
                        .moodSound(BiomeMoodSound.CAVE)
                        .music(MusicType.GAME)
                        .build()
                ).build();
    }
}
