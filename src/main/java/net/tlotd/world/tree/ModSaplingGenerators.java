package net.tlotd.world.tree;

import net.minecraft.block.SaplingGenerator;
import net.tlotd.world.ModConfiguredFeatures;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator GINKGO = new SaplingGenerator(
            "ginkgo",
            Optional.of(ModConfiguredFeatures.MEGA_GINKGO_KEY),
            Optional.of(ModConfiguredFeatures.GINKGO_KEY),
            Optional.empty()
    );
}