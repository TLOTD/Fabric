package net.tlotd.world;

import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;
import net.tlotd.block.ModBlocks;

import java.util.List;

public class ModTreePlacement {
    public static List<PlacementModifier> modifiersWithCountAndExtra(int count, float extraChance, int extraCount) {
        return VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                PlacedFeatures.createCountExtraModifier(count, extraChance, extraCount),
                ModBlocks.GINKGO_SAPLING
        );
    }
}