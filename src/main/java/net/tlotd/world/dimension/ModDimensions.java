package net.tlotd.world.dimension;

import net.tlotd.TLOTD;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> PREHISTORIC_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            new Identifier(TLOTD.MOD_ID, "prehistoric"));
    public static final RegistryKey<World> PREHISTORIC_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(TLOTD.MOD_ID, "prehistoric"));
    public static final RegistryKey<DimensionType> PREHISTORIC_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(TLOTD.MOD_ID, "prehistoric"));

    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(PREHISTORIC_TYPE, new DimensionType(
                OptionalLong.of(12000), // fixedTime
                true, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                true, // natural
                1.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                384, // height
                320, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                DimensionTypes.OVERWORLD_ID, // effectsLocation
                1.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));
    }
}