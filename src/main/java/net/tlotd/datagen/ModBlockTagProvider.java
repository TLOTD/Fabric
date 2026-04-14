package net.tlotd.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.tlotd.block.ModBlocks;
import net.tlotd.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Blocks.MITHRIL_REACTING)
                .add(ModBlocks.BEDROCK_MITHRIL_ORE)
                .add(ModBlocks.RAW_MITHRIL_BLOCK)
                .add(ModBlocks.MITHRIL_BLOCK)
                .add(ModBlocks.MITHRIL_ANVIL)
                .add(ModBlocks.MITHRIL_BARS)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.EXTRACTABLE_BLOCKS)
                .addTag(ModTags.Blocks.STONE_EXTRACTABLE_BLOCKS)
                .addTag(ModTags.Blocks.ANDESITE_EXTRACTABLE_BLOCKS)
                .addTag(ModTags.Blocks.DIORITE_EXTRACTABLE_BLOCKS)
                .addTag(ModTags.Blocks.GRANITE_EXTRACTABLE_BLOCKS)
                .addTag(ModTags.Blocks.TUFF_EXTRACTABLE_BLOCKS)
                .addTag(ModTags.Blocks.CALCITE_EXTRACTABLE_BLOCKS)
                .addTag(ModTags.Blocks.DEEPSLATE_EXTRACTABLE_BLOCKS)
                .addTag(ModTags.Blocks.RED_DEEPSLATE_EXTRACTABLE_BLOCKS)
                .addTag(ModTags.Blocks.BEDROCK_EXTRACTABLE_BLOCKS)
                .addTag(ModTags.Blocks.END_STONE_EXTRACTABLE_BLOCKS)
                .addTag(ModTags.Blocks.MOON_ROCK_EXTRACTABLE_BLOCKS)
                .addTag(ModTags.Blocks.NETHERRACK_EXTRACTABLE_BLOCKS)
                .addTag(ModTags.Blocks.BASALT_EXTRACTABLE_BLOCKS)
                .addTag(ModTags.Blocks.BLACKSTONE_EXTRACTABLE_BLOCKS)
                .add(Blocks.RAW_COPPER_BLOCK)
                .add(Blocks.RAW_IRON_BLOCK)
                .add(Blocks.RAW_GOLD_BLOCK)
                .add(Blocks.ANCIENT_DEBRIS)
                .add(ModBlocks.PREHISTORIC_DEBRIS)
                .add(ModBlocks.RAW_LEAD_BLOCK)
                .add(ModBlocks.RAW_ALUMINIUM_BLOCK)
                .add(ModBlocks.RAW_URANIUM_BLOCK)
                .add(ModBlocks.RAW_ENDURIUM_BLOCK)
                .add(ModBlocks.RAW_NEBULAR_BLOCK)
                .add(ModBlocks.RAW_PALLADIUM_BLOCK)
                .add(ModBlocks.RAW_MITHRIL_BLOCK)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.STONE_EXTRACTABLE_BLOCKS)
                .add(Blocks.COAL_ORE)
                .add(Blocks.COPPER_ORE)
                .add(Blocks.LAPIS_ORE)
                .add(Blocks.IRON_ORE)
                .add(Blocks.REDSTONE_ORE)
                .add(Blocks.GOLD_ORE)
                .add(Blocks.DIAMOND_ORE)
                .add(Blocks.EMERALD_ORE)
                .add(ModBlocks.ALUMINIUM_ORE)
                .add(ModBlocks.LEAD_ORE)
                .add(ModBlocks.URANIUM_ORE)
                .add(ModBlocks.FOSSIL)
                .add(ModBlocks.HELIORITE_ORE)
                .add(ModBlocks.PALLADIUM_ORE)
                .add(ModBlocks.JURASSOLINE_ORE)
                .add(ModBlocks.CINNABAR_ORE)
                .add(ModBlocks.NEBULAR_ORE)
                .addOptional(Identifier.tryParse("create:zinc_ore"))
                .addOptional(Identifier.tryParse("create_new_age:thorium_ore"))

                .addOptional(Identifier.tryParse("alltheores:aluminum_ore"))
                .addOptional(Identifier.tryParse("alltheores:lead_ore"))
                .addOptional(Identifier.tryParse("alltheores:nickel_ore"))
                .addOptional(Identifier.tryParse("alltheores:osmium_ore"))
                .addOptional(Identifier.tryParse("alltheores:platinum_ore"))
                .addOptional(Identifier.tryParse("alltheores:silver_ore"))
                .addOptional(Identifier.tryParse("alltheores:tin_ore"))
                .addOptional(Identifier.tryParse("alltheores:uranium_ore"))
                .addOptional(Identifier.tryParse("alltheores:zinc_ore"))
                .addOptional(Identifier.tryParse("alltheores:iridium_ore"))
                .addOptional(Identifier.tryParse("alltheores:peridot_ore"))
                .addOptional(Identifier.tryParse("alltheores:ruby_ore"))
                .addOptional(Identifier.tryParse("alltheores:sapphire_ore"))

                .addOptional(Identifier.tryParse("allthemodium:allthemodium_ore"))

                .addOptional(Identifier.tryParse("thermal:apatite_ore"))
                .addOptional(Identifier.tryParse("thermal:cinnabar_ore"))
                .addOptional(Identifier.tryParse("thermal:niter_ore"))
                .addOptional(Identifier.tryParse("thermal:sulfur_ore"))
                .addOptional(Identifier.tryParse("thermal:tin_ore"))
                .addOptional(Identifier.tryParse("thermal:lead_ore"))
                .addOptional(Identifier.tryParse("thermal:silver_ore"))
                .addOptional(Identifier.tryParse("thermal:nickel_ore"))
                .addOptional(Identifier.tryParse("thermal:ruby_ore"))
                .addOptional(Identifier.tryParse("thermal:sapphire_ore"))

                .addOptional(Identifier.tryParse("terramity:sapphire_ore"))
                .addOptional(Identifier.tryParse("terramity:topaz_ore"))

                .addOptional(Identifier.tryParse("iceandfire:silver_ore"))
                .addOptional(Identifier.tryParse("iceandfire:sapphire_ore"))
        ;
        getOrCreateTagBuilder(ModTags.Blocks.ANDESITE_EXTRACTABLE_BLOCKS)
                .addOptional(Identifier.tryParse("universal_ores:andesite_coal_ore"))
                .addOptional(Identifier.tryParse("universal_ores:andesite_iron_ore"))
                .addOptional(Identifier.tryParse("universal_ores:andesite_gold_ore"))
                .addOptional(Identifier.tryParse("universal_ores:andesite_copper_ore"))
                .addOptional(Identifier.tryParse("universal_ores:andesite_redstone_ore"))
                .addOptional(Identifier.tryParse("universal_ores:andesite_lapis_ore"))
                .addOptional(Identifier.tryParse("universal_ores:andesite_emerald_ore"))
                .addOptional(Identifier.tryParse("universal_ores:andesite_diamond_ore"))
        ;
        getOrCreateTagBuilder(ModTags.Blocks.DIORITE_EXTRACTABLE_BLOCKS)
                .addOptional(Identifier.tryParse("universal_ores:diorite_coal_ore"))
                .addOptional(Identifier.tryParse("universal_ores:diorite_iron_ore"))
                .addOptional(Identifier.tryParse("universal_ores:diorite_gold_ore"))
                .addOptional(Identifier.tryParse("universal_ores:diorite_copper_ore"))
                .addOptional(Identifier.tryParse("universal_ores:diorite_redstone_ore"))
                .addOptional(Identifier.tryParse("universal_ores:diorite_lapis_ore"))
                .addOptional(Identifier.tryParse("universal_ores:diorite_emerald_ore"))
                .addOptional(Identifier.tryParse("universal_ores:diorite_diamond_ore"))
        ;
        getOrCreateTagBuilder(ModTags.Blocks.GRANITE_EXTRACTABLE_BLOCKS)
                .addOptional(Identifier.tryParse("universal_ores:granite_coal_ore"))
                .addOptional(Identifier.tryParse("universal_ores:granite_iron_ore"))
                .addOptional(Identifier.tryParse("universal_ores:granite_gold_ore"))
                .addOptional(Identifier.tryParse("universal_ores:granite_copper_ore"))
                .addOptional(Identifier.tryParse("universal_ores:granite_redstone_ore"))
                .addOptional(Identifier.tryParse("universal_ores:granite_lapis_ore"))
                .addOptional(Identifier.tryParse("universal_ores:granite_emerald_ore"))
                .addOptional(Identifier.tryParse("universal_ores:granite_diamond_ore"))
        ;
        getOrCreateTagBuilder(ModTags.Blocks.TUFF_EXTRACTABLE_BLOCKS)
                .addOptional(Identifier.tryParse("universal_ores:tuff_coal_ore"))
                .addOptional(Identifier.tryParse("universal_ores:tuff_iron_ore"))
                .addOptional(Identifier.tryParse("universal_ores:tuff_gold_ore"))
                .addOptional(Identifier.tryParse("universal_ores:tuff_copper_ore"))
                .addOptional(Identifier.tryParse("universal_ores:tuff_redstone_ore"))
                .addOptional(Identifier.tryParse("universal_ores:tuff_lapis_ore"))
                .addOptional(Identifier.tryParse("universal_ores:tuff_emerald_ore"))
                .addOptional(Identifier.tryParse("universal_ores:tuff_diamond_ore"))
        ;
        getOrCreateTagBuilder(ModTags.Blocks.CALCITE_EXTRACTABLE_BLOCKS)
                .addOptional(Identifier.tryParse("universal_ores:calcite_coal_ore"))
                .addOptional(Identifier.tryParse("universal_ores:calcite_iron_ore"))
                .addOptional(Identifier.tryParse("universal_ores:calcite_gold_ore"))
                .addOptional(Identifier.tryParse("universal_ores:calcite_copper_ore"))
                .addOptional(Identifier.tryParse("universal_ores:calcite_redstone_ore"))
                .addOptional(Identifier.tryParse("universal_ores:calcite_lapis_ore"))
                .addOptional(Identifier.tryParse("universal_ores:calcite_emerald_ore"))
                .addOptional(Identifier.tryParse("universal_ores:calcite_diamond_ore"))
        ;
        getOrCreateTagBuilder(ModTags.Blocks.DEEPSLATE_EXTRACTABLE_BLOCKS)
                .add(Blocks.DEEPSLATE_COAL_ORE)
                .add(Blocks.DEEPSLATE_COPPER_ORE)
                .add(Blocks.DEEPSLATE_LAPIS_ORE)
                .add(Blocks.DEEPSLATE_IRON_ORE)
                .add(Blocks.DEEPSLATE_REDSTONE_ORE)
                .add(Blocks.DEEPSLATE_GOLD_ORE)
                .add(Blocks.DEEPSLATE_DIAMOND_ORE)
                .add(Blocks.DEEPSLATE_EMERALD_ORE)
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE)
                .add(ModBlocks.DEEPSLATE_HELIORITE_ORE)
                .add(ModBlocks.DEEPSLATE_PALLADIUM_ORE)
                .add(ModBlocks.DEEPSLATE_JURASSOLINE_ORE)
                .add(ModBlocks.DEEPSLATE_CINNABAR_ORE)
                .add(ModBlocks.DEEPSLATE_NEBULAR_ORE)
                .add(ModBlocks.DEEPSLATE_FOSSIL)
                .addOptional(Identifier.tryParse("create:deepslate_zinc_ore"))
                .addOptional(Identifier.tryParse("vs_clockwork:wanderlite_deepslate_ore"))

                .addOptional(Identifier.tryParse("alltheores:deepslate_aluminum_ore"))
                .addOptional(Identifier.tryParse("alltheores:deepslate_lead_ore"))
                .addOptional(Identifier.tryParse("alltheores:deepslate_nickel_ore"))
                .addOptional(Identifier.tryParse("alltheores:deepslate_osmium_ore"))
                .addOptional(Identifier.tryParse("alltheores:deepslate_platinum_ore"))
                .addOptional(Identifier.tryParse("alltheores:deepslate_silver_ore"))
                .addOptional(Identifier.tryParse("alltheores:deepslate_tin_ore"))
                .addOptional(Identifier.tryParse("alltheores:deepslate_uranium_ore"))
                .addOptional(Identifier.tryParse("alltheores:deepslate_zinc_ore"))
                .addOptional(Identifier.tryParse("alltheores:deepslate_iridium_ore"))
                .addOptional(Identifier.tryParse("alltheores:deepslate_peridot_ore"))
                .addOptional(Identifier.tryParse("alltheores:deepslate_ruby_ore"))
                .addOptional(Identifier.tryParse("alltheores:deepslate_sapphire_ore"))

                .addOptional(Identifier.tryParse("allthemodium:allthemodium_slate_ore"))

                .addOptional(Identifier.tryParse("thermal:deepslate_apatite_ore"))
                .addOptional(Identifier.tryParse("thermal:deepslate_cinnabar_ore"))
                .addOptional(Identifier.tryParse("thermal:deepslate_niter_ore"))
                .addOptional(Identifier.tryParse("thermal:deepslate_sulfur_ore"))
                .addOptional(Identifier.tryParse("thermal:deepslate_tin_ore"))
                .addOptional(Identifier.tryParse("thermal:deepslate_lead_ore"))
                .addOptional(Identifier.tryParse("thermal:deepslate_silver_ore"))
                .addOptional(Identifier.tryParse("thermal:deepslate_nickel_ore"))
                .addOptional(Identifier.tryParse("thermal:deepslate_ruby_ore"))
                .addOptional(Identifier.tryParse("thermal:deepslate_sapphire_ore"))

                .addOptional(Identifier.tryParse("terramity:deepslate_sapphire_ore"))
                .addOptional(Identifier.tryParse("terramity:deepslate_topaz_ore"))
                .addOptional(Identifier.tryParse("terramity:deepslate_dimlite_ore"))
                .addOptional(Identifier.tryParse("terramity:deepslate_iridescent_ore"))
                .addOptional(Identifier.tryParse("terramity:gaianite_cluster_ore"))
                .addOptional(Identifier.tryParse("terramity:profaned_ore"))

                .addOptional(Identifier.tryParse("iceandfire:deepslate_silver_ore"))
        ;
        getOrCreateTagBuilder(ModTags.Blocks.RED_DEEPSLATE_EXTRACTABLE_BLOCKS)
                .add(ModBlocks.RED_DEEPSLATE_FOSSIL)

                .add(ModBlocks.RED_DEEPSLATE_IRON_ORE)
                .add(ModBlocks.RED_DEEPSLATE_COPPER_ORE)
                .add(ModBlocks.RED_DEEPSLATE_GOLD_ORE)
                .add(ModBlocks.RED_DEEPSLATE_REDSTONE_ORE)
                .add(ModBlocks.RED_DEEPSLATE_EMERALD_ORE)
                .add(ModBlocks.RED_DEEPSLATE_LAPIS_ORE)
                .add(ModBlocks.RED_DEEPSLATE_DIAMOND_ORE)

                .add(ModBlocks.RED_DEEPSLATE_SULFUR_ORE)
                .add(ModBlocks.RED_DEEPSLATE_QUARTZ_ORE)
                .add(ModBlocks.RED_DEEPSLATE_ALUMINIUM_ORE)
                .add(ModBlocks.RED_DEEPSLATE_LEAD_ORE)
                .add(ModBlocks.RED_DEEPSLATE_URANIUM_ORE)
                .add(ModBlocks.RED_DEEPSLATE_HELIORITE_ORE)
                .add(ModBlocks.RED_DEEPSLATE_PALLADIUM_ORE)
                .add(ModBlocks.RED_DEEPSLATE_JURASSOLINE_ORE)
                .add(ModBlocks.RED_DEEPSLATE_CINNABAR_ORE)
                .add(ModBlocks.RED_DEEPSLATE_NEBULAR_ORE)

                .add(ModBlocks.RED_DEEPSLATE_ZINC_ORE)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.BEDROCK_EXTRACTABLE_BLOCKS)
                .add(ModBlocks.BEDROCK_MITHRIL_ORE)
                .addOptional(Identifier.tryParse("terramity:bedrock_black_matter_ore"))
        ;
        getOrCreateTagBuilder(ModTags.Blocks.END_STONE_EXTRACTABLE_BLOCKS)
                .add(ModBlocks.END_ENDURIUM_ORE)
                .addOptional(Identifier.tryParse("vs_clockwork:wanderlite_end_ore"))

                .addOptional(Identifier.tryParse("alltheores:end_aluminum_ore"))
                .addOptional(Identifier.tryParse("alltheores:end_lead_ore"))
                .addOptional(Identifier.tryParse("alltheores:end_nickel_ore"))
                .addOptional(Identifier.tryParse("alltheores:end_osmium_ore"))
                .addOptional(Identifier.tryParse("alltheores:end_platinum_ore"))
                .addOptional(Identifier.tryParse("alltheores:end_silver_ore"))
                .addOptional(Identifier.tryParse("alltheores:end_tin_ore"))
                .addOptional(Identifier.tryParse("alltheores:end_uranium_ore"))
                .addOptional(Identifier.tryParse("alltheores:end_zinc_ore"))
                .addOptional(Identifier.tryParse("alltheores:end_iridium_ore"))

                .addOptional(Identifier.tryParse("allthemodium:unobtainium_ore"))

                .addOptional(Identifier.tryParse("terramity:end_iridium_ore"))
                .addOptional(Identifier.tryParse("terramity:end_onyx_ore"))
        ;
        getOrCreateTagBuilder(ModTags.Blocks.MOON_ROCK_EXTRACTABLE_BLOCKS)
                .add(ModBlocks.MEGAREGOLITH_COAL_ORE)
                .add(ModBlocks.MEGAREGOLITH_IRON_ORE)
                .add(ModBlocks.MEGAREGOLITH_LUNAR_CALLAINUS_ORE)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.NETHERRACK_EXTRACTABLE_BLOCKS)
                .add(ModBlocks.NETHER_SULFUR_ORE)
                .add(Blocks.NETHER_QUARTZ_ORE)
                .add(Blocks.NETHER_GOLD_ORE)

                .addOptional(Identifier.tryParse("tconstruct:cobalt_ore"))

                .addOptional(Identifier.tryParse("alltheores:nether_aluminum_ore"))
                .addOptional(Identifier.tryParse("alltheores:nether_lead_ore"))
                .addOptional(Identifier.tryParse("alltheores:nether_nickel_ore"))
                .addOptional(Identifier.tryParse("alltheores:nether_osmium_ore"))
                .addOptional(Identifier.tryParse("alltheores:nether_platinum_ore"))
                .addOptional(Identifier.tryParse("alltheores:nether_silver_ore"))
                .addOptional(Identifier.tryParse("alltheores:nether_tin_ore"))
                .addOptional(Identifier.tryParse("alltheores:nether_uranium_ore"))
                .addOptional(Identifier.tryParse("alltheores:nether_zinc_ore"))
                .addOptional(Identifier.tryParse("alltheores:nether_iridium_ore"))

                .addOptional(Identifier.tryParse("allthemodium:vibranium_ore"))

                .addOptional(Identifier.tryParse("terramity:nether_ruby_ore"))
                .addOptional(Identifier.tryParse("terramity:daemonium_ore"))
        ;
        getOrCreateTagBuilder(ModTags.Blocks.BASALT_EXTRACTABLE_BLOCKS)
                .addOptional(Identifier.tryParse("universal_ores:basalt_gold_ore"))
                .addOptional(Identifier.tryParse("universal_ores:basalt_quartz_ore"))
        ;
        getOrCreateTagBuilder(ModTags.Blocks.BLACKSTONE_EXTRACTABLE_BLOCKS)
                .addOptional(Identifier.tryParse("universal_ores:blackstone_gold_ore"))
                .addOptional(Identifier.tryParse("universal_ores:blackstone_quartz_ore"))
        ;

        getOrCreateTagBuilder(ModTags.Blocks.WITCHING_TABLE_BASE_BLOCKS)
                .add(ModBlocks.RITUALISTIC_FANCY_CHARRED_PLANKS)
                .add(ModBlocks.FANCY_CHARRED_PLANKS)
        ;

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.GINKGO_LEAVES)
        ;

        getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(ModBlocks.GINKGO_SAPLING)
        ;

        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
                .add(ModBlocks.STANDING_GINKGO_SIGN)
        ;

        getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
                .add(ModBlocks.WALL_GINKGO_SIGN)
        ;

        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
                .add(ModBlocks.HANGING_GINKGO_SIGN)
        ;

        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
                .add(ModBlocks.WALL_HANGING_GINKGO_SIGN)
        ;

        getOrCreateTagBuilder(BlockTags.BUTTONS)
                .add(ModBlocks.GINKGO_BUTTON)
        ;

        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.GINKGO_DOOR)
        ;

        getOrCreateTagBuilder(BlockTags.DOORS)
                .add(ModBlocks.HEAVY_METAL_DOOR)
                .add(ModBlocks.METAL_PUSH_DOOR)
                .add(ModBlocks.GLASS_DOOR)
        ;

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.GINKGO_FENCE)
        ;

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.GINKGO_FENCE_GATE)
        ;

        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.GINKGO_PRESSURE_PLATE)
        ;

        getOrCreateTagBuilder(BlockTags.STONE_PRESSURE_PLATES)
                .add(ModBlocks.RED_DEEPSLATE_PRESSURE_PLATE)
                .add(ModBlocks.LIMESTONE_PRESSURE_PLATE)
                .add(ModBlocks.MARBLE_PRESSURE_PLATE)
        ;

        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.GINKGO_SLAB)
                .add(ModBlocks.FANCY_OAK_SLAB)
                .add(ModBlocks.FANCY_SPRUCE_SLAB)
                .add(ModBlocks.FANCY_BIRCH_SLAB)
                .add(ModBlocks.FANCY_JUNGLE_SLAB)
                .add(ModBlocks.FANCY_ACACIA_SLAB)
                .add(ModBlocks.FANCY_DARK_OAK_SLAB)
                .add(ModBlocks.FANCY_MANGROVE_SLAB)
                .add(ModBlocks.FANCY_CHERRY_SLAB)
                .add(ModBlocks.FANCY_PALE_OAK_SLAB)
                .add(ModBlocks.FANCY_BAMBOO_SLAB)
                .add(ModBlocks.FANCY_CRIMSON_SLAB)
                .add(ModBlocks.FANCY_WARPED_SLAB)
                .add(ModBlocks.FANCY_GINKGO_SLAB)
        ;

        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.GINKGO_STAIRS)
                .add(ModBlocks.FANCY_OAK_STAIRS)
                .add(ModBlocks.FANCY_SPRUCE_STAIRS)
                .add(ModBlocks.FANCY_BIRCH_STAIRS)
                .add(ModBlocks.FANCY_JUNGLE_STAIRS)
                .add(ModBlocks.FANCY_ACACIA_STAIRS)
                .add(ModBlocks.FANCY_DARK_OAK_STAIRS)
                .add(ModBlocks.FANCY_MANGROVE_STAIRS)
                .add(ModBlocks.FANCY_CHERRY_STAIRS)
                .add(ModBlocks.FANCY_PALE_OAK_STAIRS)
                .add(ModBlocks.FANCY_BAMBOO_STAIRS)
                .add(ModBlocks.FANCY_CRIMSON_STAIRS)
                .add(ModBlocks.FANCY_WARPED_STAIRS)
                .add(ModBlocks.FANCY_GINKGO_STAIRS)
        ;

        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.GINKGO_TRAPDOOR)
                .add(ModBlocks.FANCY_OAK_TRAPDOOR)
                .add(ModBlocks.FANCY_SPRUCE_TRAPDOOR)
                .add(ModBlocks.FANCY_BIRCH_TRAPDOOR)
                .add(ModBlocks.FANCY_JUNGLE_TRAPDOOR)
                .add(ModBlocks.FANCY_ACACIA_TRAPDOOR)
                .add(ModBlocks.FANCY_DARK_OAK_TRAPDOOR)
                .add(ModBlocks.FANCY_MANGROVE_TRAPDOOR)
                .add(ModBlocks.FANCY_CHERRY_TRAPDOOR)
                .add(ModBlocks.FANCY_PALE_OAK_TRAPDOOR)
                .add(ModBlocks.FANCY_BAMBOO_TRAPDOOR)
                .add(ModBlocks.FANCY_CRIMSON_TRAPDOOR)
                .add(ModBlocks.FANCY_WARPED_TRAPDOOR)
                .add(ModBlocks.FANCY_GINKGO_TRAPDOOR)
        ;

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .addTag(ModTags.Blocks.GINKGO_LOGS)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.GINKGO_LOGS)
                .add(ModBlocks.GINKGO_LOG)
                .add(ModBlocks.GINKGO_WOOD)
                .add(ModBlocks.STRIPPED_GINKGO_LOG)
                .add(ModBlocks.STRIPPED_GINKGO_WOOD)
        ;

        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(ModBlocks.GINKGO_PLANKS)
                .add(ModBlocks.FANCY_OAK_PLANKS)
                .add(ModBlocks.FANCY_SPRUCE_PLANKS)
                .add(ModBlocks.FANCY_BIRCH_PLANKS)
                .add(ModBlocks.FANCY_JUNGLE_PLANKS)
                .add(ModBlocks.FANCY_ACACIA_PLANKS)
                .add(ModBlocks.FANCY_DARK_OAK_PLANKS)
                .add(ModBlocks.FANCY_MANGROVE_PLANKS)
                .add(ModBlocks.FANCY_CHERRY_PLANKS)
                .add(ModBlocks.FANCY_PALE_OAK_PLANKS)
                .add(ModBlocks.FANCY_BAMBOO_PLANKS)
                .add(ModBlocks.FANCY_CRIMSON_PLANKS)
                .add(ModBlocks.FANCY_WARPED_PLANKS)
                .add(ModBlocks.FANCY_GINKGO_PLANKS)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.COMPUTER_ACCESSORIES)
                .add(ModBlocks.KEYCARD_PROGRAMMER)
                .add(ModBlocks.INCUBATOR)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.OXYGEN_PROVIDERS)
                .addTag(BlockTags.SAPLINGS)
                .addTag(BlockTags.SMALL_FLOWERS)
                .addTag(BlockTags.FLOWER_POTS)
                .add(Blocks.FERN)
                .add(Blocks.GRASS)
                .add(Blocks.PINK_PETALS)

                .add(Blocks.WHEAT)
                .add(Blocks.CARROTS)
                .add(Blocks.POTATOES)
                .add(Blocks.BEETROOTS)
                .add(Blocks.SWEET_BERRY_BUSH)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.WOODEN_STEINS)
                .add(ModBlocks.WOODEN_STEIN)
                .add(ModBlocks.WOODEN_WATER_STEIN)
                .add(ModBlocks.WOODEN_MILK_STEIN)
                .add(ModBlocks.WOODEN_BEER_STEIN)
                .add(ModBlocks.WOODEN_MEAD_STEIN)
                .add(ModBlocks.WOODEN_APPLE_JUICE_STEIN)
                .add(ModBlocks.WOODEN_ORANGE_JUICE_STEIN)
                .add(ModBlocks.WOODEN_STRAWBERRY_MILKSHAKE_STEIN)
                .add(ModBlocks.WOODEN_ORANGE_MILKSHAKE_STEIN)
                .add(ModBlocks.WOODEN_BLUE_BERRY_MILKSHAKE_STEIN)
                .add(ModBlocks.WOODEN_TORCHBERRY_MILKSHAKE_STEIN)
                .add(ModBlocks.WOODEN_CHOCOLATE_MILKSHAKE_STEIN)
                .add(ModBlocks.WOODEN_CARAMEL_MILKSHAKE_STEIN)
                .add(ModBlocks.HOT_WOODEN_MILK_STEIN)
                .add(ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.LOGS_WITH_BARK)
                .add(Blocks.OAK_LOG)
                .add(Blocks.OAK_WOOD)
                .add(Blocks.SPRUCE_LOG)
                .add(Blocks.SPRUCE_WOOD)
                .add(Blocks.BIRCH_LOG)
                .add(Blocks.BIRCH_WOOD)
                .add(Blocks.JUNGLE_LOG)
                .add(Blocks.JUNGLE_WOOD)
                .add(Blocks.ACACIA_LOG)
                .add(Blocks.ACACIA_WOOD)
                .add(Blocks.DARK_OAK_LOG)
                .add(Blocks.DARK_OAK_WOOD)
                .add(Blocks.MANGROVE_LOG)
                .add(Blocks.MANGROVE_WOOD)
                .add(Blocks.CHERRY_LOG)
                .add(Blocks.CHERRY_WOOD)
                .add(ModBlocks.GINKGO_LOG)
                .add(ModBlocks.GINKGO_WOOD)

                .addOptional(Identifier.tryParse("aether:skyroot_log"))
                .addOptional(Identifier.tryParse("aether:skyroot_wood"))
                .addOptional(Identifier.tryParse("aether:golden_oak_log"))
                .addOptional(Identifier.tryParse("aether:golden_oak_wood"))

                .addOptional(Identifier.tryParse("alexscaves:pewen_log"))
                .addOptional(Identifier.tryParse("alexscaves:pewen_wood"))
                .addOptional(Identifier.tryParse("alexscaves:thornwood_log"))
                .addOptional(Identifier.tryParse("alexscaves:thornwood_wood"))

                .addOptional(Identifier.tryParse("biomesoplenty:fir_log"))
                .addOptional(Identifier.tryParse("biomesoplenty:fir_wood"))
                .addOptional(Identifier.tryParse("biomesoplenty:pine_log"))
                .addOptional(Identifier.tryParse("biomesoplenty:pine_wood"))
                .addOptional(Identifier.tryParse("biomesoplenty:maple_log"))
                .addOptional(Identifier.tryParse("biomesoplenty:maple_wood"))
                .addOptional(Identifier.tryParse("biomesoplenty:redwood_log"))
                .addOptional(Identifier.tryParse("biomesoplenty:redwood_wood"))
                .addOptional(Identifier.tryParse("biomesoplenty:mahogany_log"))
                .addOptional(Identifier.tryParse("biomesoplenty:mahogany_wood"))
                .addOptional(Identifier.tryParse("biomesoplenty:jacaranda_log"))
                .addOptional(Identifier.tryParse("biomesoplenty:jacaranda_wood"))
                .addOptional(Identifier.tryParse("biomesoplenty:palm_log"))
                .addOptional(Identifier.tryParse("biomesoplenty:palm_wood"))
                .addOptional(Identifier.tryParse("biomesoplenty:willow_log"))
                .addOptional(Identifier.tryParse("biomesoplenty:willow_wood"))
                .addOptional(Identifier.tryParse("biomesoplenty:dead_log"))
                .addOptional(Identifier.tryParse("biomesoplenty:dead_wood"))
                .addOptional(Identifier.tryParse("biomesoplenty:magic_log"))
                .addOptional(Identifier.tryParse("biomesoplenty:magic_wood"))
                .addOptional(Identifier.tryParse("biomesoplenty:umbran_log"))
                .addOptional(Identifier.tryParse("biomesoplenty:umbran_wood"))
                .addOptional(Identifier.tryParse("biomesoplenty:hellbark_log"))
                .addOptional(Identifier.tryParse("biomesoplenty:hellbark_wood"))
                .addOptional(Identifier.tryParse("biomesoplenty:empyreal_log"))
                .addOptional(Identifier.tryParse("biomesoplenty:empyreal_wood"))

                .addOptional(Identifier.tryParse("quark:ancient_log"))
                .addOptional(Identifier.tryParse("quark:ancient_wood"))
                .addOptional(Identifier.tryParse("quark:azalea_log"))
                .addOptional(Identifier.tryParse("quark:azalea_wood"))
                .addOptional(Identifier.tryParse("quark:blossom_log"))
                .addOptional(Identifier.tryParse("quark:blossom_wood"))

                .addOptional(Identifier.tryParse("thermal:rubberwood_log"))
                .addOptional(Identifier.tryParse("thermal:rubberwood_wood"))

                .addOptional(Identifier.tryParse("twilightforest:twilight_oak_log"))
                .addOptional(Identifier.tryParse("twilightforest:twilight_oak_wood"))
                .addOptional(Identifier.tryParse("twilightforest:canopy_log"))
                .addOptional(Identifier.tryParse("twilightforest:canopy_wood"))
                .addOptional(Identifier.tryParse("twilightforest:mangrove_log"))
                .addOptional(Identifier.tryParse("twilightforest:mangrove_wood"))
                .addOptional(Identifier.tryParse("twilightforest:dark_log"))
                .addOptional(Identifier.tryParse("twilightforest:dark_wood"))
                .addOptional(Identifier.tryParse("twilightforest:time_log"))
                .addOptional(Identifier.tryParse("twilightforest:time_wood"))
                .addOptional(Identifier.tryParse("twilightforest:transformation_log"))
                .addOptional(Identifier.tryParse("twilightforest:transformation_wood"))
                .addOptional(Identifier.tryParse("twilightforest:mining_log"))
                .addOptional(Identifier.tryParse("twilightforest:mining_wood"))
                .addOptional(Identifier.tryParse("twilightforest:sorting_log"))
                .addOptional(Identifier.tryParse("twilightforest:sorting_wood"))

                .add(ModBlocks.YELLOW_WALLPAPERED_WALL)
        ;

        getOrCreateTagBuilder(BlockTags.CROPS)
                .add(ModBlocks.STRAWBERRY_BUSH)
                .add(ModBlocks.ORANGE_TREE)
                .add(ModBlocks.PIPE_WEED_PLANT)
        ;

        getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND)
                .add(ModBlocks.STRAWBERRY_BUSH)
                .add(ModBlocks.ORANGE_TREE)
                .add(ModBlocks.PIPE_WEED_PLANT)
        ;

        getOrCreateTagBuilder(BlockTags.CAMPFIRES)
                .add(ModBlocks.SULFUR_CAMPFIRE)
        ;

        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.WHITE_PUMPKIN)
                .add(ModBlocks.CARVED_WHITE_PUMPKIN)
                .add(ModBlocks.WHITE_JACK_O_LANTERN)
        ;

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.LUNAR_REGOLITH)
                .add(ModBlocks.SUSPICIOUS_LUNAR_REGOLITH)

                .add(ModBlocks.RICH_GRASS_BLOCK)
                .add(ModBlocks.RICH_DIRT)
                .add(ModBlocks.RICH_DIRT_PATH)
                .add(ModBlocks.RICH_FARMLAND)

                .add(ModBlocks.RED_SANDY_DEEPSLATE)
                .add(ModBlocks.RED_GRAVEL)
        ;

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .addTag(ModTags.Blocks.WOODEN_STEINS)
                .add(ModBlocks.STRAWBERRY_CRATE)
                .add(ModBlocks.ORANGE_CRATE)
                .add(ModBlocks.PIPE_WEED_CRATE)
                .add(ModBlocks.WHITE_JACK_O_LANTERN)
                .add(ModBlocks.SULFUR_CAMPFIRE)
                .add(ModBlocks.STICK_CROSS)
                .add(ModBlocks.EFFIGIES)
                .add(ModBlocks.WITCHING_TABLE)
                .add(ModBlocks.BENCH)
                .add(ModBlocks.RADIO)
                .add(ModBlocks.ARCHAEOLOGY_TABLE)
                .add(ModBlocks.FANCY_CHARRED_PLANKS)
                .add(ModBlocks.FANCY_CHARRED_STAIRS)
                .add(ModBlocks.FANCY_CHARRED_SLAB)
                .add(ModBlocks.FANCY_CHARRED_TRAPDOOR)
                .add(ModBlocks.RITUALISTIC_FANCY_CHARRED_PLANKS)
                .add(ModBlocks.VIDEOCASSETTE_RECORDER_BOOKSHELF)
                .add(ModBlocks.GAME_CONSOLE_BOOKSHELF)
                .add(ModBlocks.MEDIA_SYSTEM_BOOKSHELF)
                .add(ModBlocks.WHITE_PUMPKIN)
                .add(ModBlocks.CARVED_WHITE_PUMPKIN)
                .add(ModBlocks.WHITE_JACK_O_LANTERN)

                .add(ModBlocks.YELLOW_WALLPAPERED_WOOD)
        ;

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)

                .add(ModBlocks.SULFUR_LANTERN)

                .add(ModBlocks.APPARATUS)

                .add(ModBlocks.GLOBUS_CRUCIGER)

                .add(ModBlocks.GARBAGE_CAN)
                .add(ModBlocks.BENCH)

                .add(ModBlocks.AUGMENTATION_TABLE)

                .add(ModBlocks.GRAVESTONE)
                .add(ModBlocks.MOSSY_GRAVESTONE)
                .add(ModBlocks.GRAVESTONE_CROSS)
                .add(ModBlocks.MOSSY_GRAVESTONE_CROSS)

                .add(ModBlocks.SMALL_GRAVESTONE)

                .add(ModBlocks.SKELETON)
                .add(ModBlocks.EMERGING_SKELETON)

                .add(ModBlocks.BLOOD_CAULDRON)

                .add(ModBlocks.RADIO)
                .add(ModBlocks.TELEVISION)
                .add(ModBlocks.TELEVISION_ON)
                .add(ModBlocks.TELEVISION_GAME)
                .add(ModBlocks.COMPUTER)
                .add(ModBlocks.COMPUTER_ON)
                .add(ModBlocks.INTERDIMENSIONAL_RECEIVER)
                .add(ModBlocks.VIDEOCASSETTE_RECORDER)
                .add(ModBlocks.GAME_CONSOLE)
                .add(ModBlocks.OXYGEN_COLLECTOR)
                .add(ModBlocks.INCUBATOR)

                .add(ModBlocks.KEYCARD_PROGRAMMER)
                .add(ModBlocks.KEYCARD_READER)
                .add(ModBlocks.HEV_CHARGER)

                .add(ModBlocks.SIGNAL_TRANSMITTER_ANTENNA)
                .add(ModBlocks.SIGNAL_TRANSMITTER)

                .add(ModBlocks.TELEPORTER)
                .add(ModBlocks.ALIEN_CONTROL_PANEL)

                .add(ModBlocks.ELEVATOR_DIAL)
                .add(ModBlocks.MINING_ELEVATOR_CONTROLLER)
                .add(ModBlocks.MINING_ELEVATOR_BASE)
                .add(ModBlocks.WOODEN_ELEVATOR_CONTROLLER)
                .add(ModBlocks.WOODEN_ELEVATOR_BASE)
                .add(ModBlocks.QUARTZ_ELEVATOR_CONTROLLER)
                .add(ModBlocks.QUARTZ_ELEVATOR_BASE)
                .add(ModBlocks.GLASS_ELEVATOR_CONTROLLER)
                .add(ModBlocks.GLASS_ELEVATOR_BASE)
                .add(ModBlocks.EXIT_SIGN)

                .add(ModBlocks.MARBLE)
                .add(ModBlocks.MARBLE_STAIRS)
                .add(ModBlocks.MARBLE_SLAB)
                .add(ModBlocks.MARBLE_WALL)
                .add(ModBlocks.MARBLE_PRESSURE_PLATE)
                .add(ModBlocks.MARBLE_BUTTON)
                .add(ModBlocks.CHISELED_MARBLE)
                .add(ModBlocks.CHISELED_MARBLE_2)
                .add(ModBlocks.MARBLE_PILLAR)

                .add(ModBlocks.LIMESTONE)
                .add(ModBlocks.LIMESTONE_STAIRS)
                .add(ModBlocks.LIMESTONE_SLAB)
                .add(ModBlocks.LIMESTONE_WALL)
                .add(ModBlocks.LIMESTONE_PRESSURE_PLATE)
                .add(ModBlocks.LIMESTONE_BUTTON)

                .add(ModBlocks.RED_SANDY_DEEPSLATE)

                .add(ModBlocks.RED_DEEPSLATE)
                .add(ModBlocks.RED_DEEPSLATE_STAIRS)
                .add(ModBlocks.RED_DEEPSLATE_SLAB)
                .add(ModBlocks.RED_DEEPSLATE_WALL)
                .add(ModBlocks.POLISHED_RED_DEEPSLATE)
                .add(ModBlocks.RED_DEEPSLATE_BRICKS)
                .add(ModBlocks.RED_DEEPSLATE_BRICK_STAIRS)
                .add(ModBlocks.RED_DEEPSLATE_BRICK_SLAB)
                .add(ModBlocks.RED_DEEPSLATE_BRICK_WALL)
                .add(ModBlocks.MOSSY_RED_DEEPSLATE_BRICKS)
                .add(ModBlocks.CRACKED_RED_DEEPSLATE_BRICKS)
                .add(ModBlocks.RED_DEEPSLATE_PRESSURE_PLATE)

                .add(ModBlocks.COBBLED_RED_DEEPSLATE)
                .add(ModBlocks.COBBLED_RED_DEEPSLATE_STAIRS)
                .add(ModBlocks.COBBLED_RED_DEEPSLATE_SLAB)
                .add(ModBlocks.COBBLED_RED_DEEPSLATE_WALL)
                .add(ModBlocks.POLISHED_COBBLED_RED_DEEPSLATE)
                .add(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS)
                .add(ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_STAIRS)
                .add(ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_SLAB)
                .add(ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_WALL)
                .add(ModBlocks.MOSSY_COBBLED_RED_DEEPSLATE_BRICKS)
                .add(ModBlocks.CRACKED_COBBLED_RED_DEEPSLATE_BRICKS)

                .add(ModBlocks.REINFORCED_RED_DEEPSLATE)

                .add(ModBlocks.RED_DEEPSLATE_FOSSIL)

                .add(ModBlocks.RED_DEEPSLATE_IRON_ORE)
                .add(ModBlocks.RED_DEEPSLATE_COPPER_ORE)
                .add(ModBlocks.RED_DEEPSLATE_GOLD_ORE)
                .add(ModBlocks.RED_DEEPSLATE_REDSTONE_ORE)
                .add(ModBlocks.RED_DEEPSLATE_EMERALD_ORE)
                .add(ModBlocks.RED_DEEPSLATE_LAPIS_ORE)
                .add(ModBlocks.RED_DEEPSLATE_DIAMOND_ORE)

                .add(ModBlocks.RED_DEEPSLATE_SULFUR_ORE)
                .add(ModBlocks.RED_DEEPSLATE_QUARTZ_ORE)
                .add(ModBlocks.RED_DEEPSLATE_ALUMINIUM_ORE)
                .add(ModBlocks.RED_DEEPSLATE_LEAD_ORE)
                .add(ModBlocks.RED_DEEPSLATE_URANIUM_ORE)
                .add(ModBlocks.RED_DEEPSLATE_HELIORITE_ORE)
                .add(ModBlocks.RED_DEEPSLATE_PALLADIUM_ORE)
                .add(ModBlocks.RED_DEEPSLATE_JURASSOLINE_ORE)
                .add(ModBlocks.RED_DEEPSLATE_CINNABAR_ORE)
                .add(ModBlocks.RED_DEEPSLATE_NEBULAR_ORE)

                .add(ModBlocks.RED_DEEPSLATE_ZINC_ORE)

                .add(ModBlocks.XEN_CRYSTAL_CLUSTER)
                .add(ModBlocks.XEN_CRYSTAL_BLOCK)

                .add(ModBlocks.MEGAREGOLITH)
                .add(ModBlocks.MEGAREGOLITH_COAL_ORE)
                .add(ModBlocks.MEGAREGOLITH_IRON_ORE)
                .add(ModBlocks.MEGAREGOLITH_LUNAR_CALLAINUS_ORE)
                .add(ModBlocks.MEGAREGOLITH_BRICKS)
                .add(ModBlocks.MEGAREGOLITH_BRICK_STAIRS)
                .add(ModBlocks.MEGAREGOLITH_BRICK_SLAB)
                .add(ModBlocks.MEGAREGOLITH_BRICK_WALL)
                .add(ModBlocks.MEGAREGOLITH_TILES)
                .add(ModBlocks.MEGAREGOLITH_TILE)
                .add(ModBlocks.TALL_MEGAREGOLITH_TILE)
                .add(ModBlocks.LARGE_MEGAREGOLITH_TILE)

                .add(ModBlocks.DARK_METAL_TILES)
                .add(ModBlocks.DARK_METAL_TILE)
                .add(ModBlocks.TALL_DARK_METAL_TILE)
                .add(ModBlocks.LARGE_DARK_METAL_TILE)

                .add(ModBlocks.DARK_METAL_LIGHTS)

                .add(ModBlocks.HEAVY_METAL_DOOR)
                .add(ModBlocks.METAL_PUSH_DOOR)
                .add(ModBlocks.GLASS_DOOR)

                .add(ModBlocks.STEEL_BLOCK)

                .add(ModBlocks.FRAMED_GLASS)
                .add(ModBlocks.VERTICAL_FRAMED_GLASS)
                .add(ModBlocks.ROUND_FRAMED_GLASS)
                .add(ModBlocks.SPLIT_FRAMED_GLASS)
                .add(ModBlocks.TILED_FRAMED_GLASS)

                .add(ModBlocks.FRAMED_GLASS_PANE)
                .add(ModBlocks.VERTICAL_FRAMED_GLASS_PANE)
                .add(ModBlocks.ROUND_FRAMED_GLASS_PANE)
                .add(ModBlocks.SPLIT_FRAMED_GLASS_PANE)
                .add(ModBlocks.TILED_FRAMED_GLASS_PANE)
                
                .add(ModBlocks.NETHER_SULFUR_ORE)
                .add(ModBlocks.SULFUR_BLOCK)

                .add(ModBlocks.ALUMINIUM_ORE)
                .add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE)
                .add(ModBlocks.RAW_ALUMINIUM_BLOCK)
                .add(ModBlocks.ALUMINIUM_BLOCK)

                .add(ModBlocks.LEAD_ORE)
                .add(ModBlocks.DEEPSLATE_LEAD_ORE)
                .add(ModBlocks.RAW_LEAD_BLOCK)
                .add(ModBlocks.LEAD_BLOCK)

                .add(ModBlocks.URANIUM_ORE)
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE)
                .add(ModBlocks.RAW_URANIUM_BLOCK)
                .add(ModBlocks.URANIUM_BLOCK)

                .add(ModBlocks.FOSSIL)
                .add(ModBlocks.DEEPSLATE_FOSSIL)

                .add(ModBlocks.HELIORITE_ORE)
                .add(ModBlocks.DEEPSLATE_HELIORITE_ORE)
                .add(ModBlocks.HELIORITE_COMB_BLOCK)
                .add(ModBlocks.HELIORITE_BLOCK)

                .add(ModBlocks.END_ENDURIUM_ORE)
                .add(ModBlocks.RAW_ENDURIUM_BLOCK)
                .add(ModBlocks.ENDURIUM_BLOCK)

                .add(ModBlocks.PALLADIUM_ORE)
                .add(ModBlocks.DEEPSLATE_PALLADIUM_ORE)
                .add(ModBlocks.RAW_PALLADIUM_BLOCK)
                .add(ModBlocks.PALLADIUM_BLOCK)

                .add(ModBlocks.JURASSOLINE_ORE)
                .add(ModBlocks.DEEPSLATE_JURASSOLINE_ORE)
                .add(ModBlocks.JURASSOLINE_CRYSTAL_BLOCK)
                .add(ModBlocks.JURASSOLINE_BLOCK)

                .add(ModBlocks.CINNABAR_ORE)
                .add(ModBlocks.DEEPSLATE_CINNABAR_ORE)
                .add(ModBlocks.CINNABAR_CRYSTAL_BLOCK)
                .add(ModBlocks.CINNABAR_BLOCK)

                .add(ModBlocks.NEBULAR_ORE)
                .add(ModBlocks.DEEPSLATE_NEBULAR_ORE)
                .add(ModBlocks.RAW_NEBULAR_BLOCK)
                .add(ModBlocks.NEBULAR_BLOCK)

                .add(ModBlocks.BEDROCK_MITHRIL_ORE)
                .add(ModBlocks.RAW_MITHRIL_BLOCK)
                .add(ModBlocks.MITHRIL_BLOCK)

                .add(ModBlocks.MITHRIL_ANVIL)
                .add(ModBlocks.MITHRIL_BARS)
        ;

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(ModBlocks.GINKGO_LEAVES)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.PAXEL_MINEABLE)
                .addTag(BlockTags.PICKAXE_MINEABLE)
                .addTag(BlockTags.AXE_MINEABLE)
                .addTag(BlockTags.SHOVEL_MINEABLE)
                .addTag(BlockTags.HOE_MINEABLE)
        ;

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.MARBLE_WALL)
                .add(ModBlocks.LIMESTONE_WALL)
                .add(ModBlocks.RED_DEEPSLATE_WALL)
                .add(ModBlocks.RED_DEEPSLATE_BRICK_WALL)
                .add(ModBlocks.COBBLED_RED_DEEPSLATE_WALL)
                .add(ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_WALL)
                .add(ModBlocks.MEGAREGOLITH_BRICK_WALL)
                .add(ModBlocks.ALIEN_BRICK_WALL)
        ;

        getOrCreateTagBuilder(BlockTags.STONE_BUTTONS)
                .add(ModBlocks.MARBLE_BUTTON)
                .add(ModBlocks.LIMESTONE_BUTTON)
                .add(ModBlocks.RED_DEEPSLATE_BUTTON)
        ;

        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.GINKGO_BUTTON)
        ;

        getOrCreateTagBuilder(BlockTags.SAND)
                .add(ModBlocks.LUNAR_REGOLITH)
                .add(ModBlocks.SUSPICIOUS_LUNAR_REGOLITH)
        ;

        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS)
                .add(ModBlocks.ROSE)
                .add(ModBlocks.IRIS)
                .add(ModBlocks.EDELWEISS)
                .add(ModBlocks.ATHELAS)
        ;

        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_ROSE)
                .add(ModBlocks.POTTED_IRIS)
                .add(ModBlocks.POTTED_EDELWEISS)
                .add(ModBlocks.POTTED_ATHELAS)
                .add(ModBlocks.POTTED_GINKGO_SAPLING)
        ;

        getOrCreateTagBuilder(BlockTags.ANIMALS_SPAWNABLE_ON)
                .add(ModBlocks.RICH_GRASS_BLOCK)
        ;
        getOrCreateTagBuilder(BlockTags.RABBITS_SPAWNABLE_ON)
                .add(ModBlocks.RICH_GRASS_BLOCK)
        ;
        getOrCreateTagBuilder(BlockTags.SNIFFER_DIGGABLE_BLOCK)
                .add(ModBlocks.RICH_GRASS_BLOCK)
                .add(ModBlocks.RICH_DIRT)
        ;
        getOrCreateTagBuilder(BlockTags.WOLVES_SPAWNABLE_ON)
                .add(ModBlocks.RICH_GRASS_BLOCK)
        ;
        getOrCreateTagBuilder(BlockTags.PARROTS_SPAWNABLE_ON)
                .add(ModBlocks.RICH_GRASS_BLOCK)
        ;
        getOrCreateTagBuilder(BlockTags.GOATS_SPAWNABLE_ON)
                .add(ModBlocks.RICH_GRASS_BLOCK)
        ;
        getOrCreateTagBuilder(BlockTags.FROGS_SPAWNABLE_ON)
                .add(ModBlocks.RICH_GRASS_BLOCK)
        ;
        getOrCreateTagBuilder(BlockTags.FOXES_SPAWNABLE_ON)
                .add(ModBlocks.RICH_GRASS_BLOCK)
        ;
        getOrCreateTagBuilder(BlockTags.VALID_SPAWN)
                .add(ModBlocks.RICH_GRASS_BLOCK)
        ;
        getOrCreateTagBuilder(BlockTags.DIRT)
                .add(ModBlocks.RICH_GRASS_BLOCK)
                .add(ModBlocks.RICH_DIRT)
        ;

        getOrCreateTagBuilder(BlockTags.VIBRATION_RESONATORS)
                .add(ModBlocks.XEN_CRYSTAL_BLOCK)
        ;

        getOrCreateTagBuilder(BlockTags.CRYSTAL_SOUND_BLOCKS)
                .add(ModBlocks.XEN_CRYSTAL_BLOCK)
        ;

        getOrCreateTagBuilder(BlockTags.CAULDRONS)
                .add(ModBlocks.BLOOD_CAULDRON)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "bookshelves")))
                .add(ModBlocks.VIDEOCASSETTE_RECORDER_BOOKSHELF)
                .add(ModBlocks.GAME_CONSOLE_BOOKSHELF)
                .add(ModBlocks.MEDIA_SYSTEM_BOOKSHELF)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "clusters")))
                .add(ModBlocks.XEN_CRYSTAL_CLUSTER)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "golem_heads")))
                .add(Blocks.CARVED_PUMPKIN)
                .add(Blocks.JACK_O_LANTERN)
                .add(ModBlocks.CARVED_WHITE_PUMPKIN)
                .add(ModBlocks.WHITE_JACK_O_LANTERN)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "framed_colorless_glass")))
                .add(ModBlocks.FRAMED_GLASS)
                .add(ModBlocks.VERTICAL_FRAMED_GLASS)
                .add(ModBlocks.ROUND_FRAMED_GLASS)
                .add(ModBlocks.SPLIT_FRAMED_GLASS)
                .add(ModBlocks.TILED_FRAMED_GLASS)
        ;

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "framed_colorless_glass_panes")))
                .add(ModBlocks.FRAMED_GLASS_PANE)
                .add(ModBlocks.VERTICAL_FRAMED_GLASS_PANE)
                .add(ModBlocks.ROUND_FRAMED_GLASS_PANE)
                .add(ModBlocks.SPLIT_FRAMED_GLASS_PANE)
                .add(ModBlocks.TILED_FRAMED_GLASS_PANE)
        ;

        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.ALUMINIUM_BLOCK)
                .add(ModBlocks.HELIORITE_BLOCK)
                .add(ModBlocks.ENDURIUM_BLOCK)
                .add(ModBlocks.PALLADIUM_BLOCK)
                .add(ModBlocks.JURASSOLINE_BLOCK)
                .add(ModBlocks.CINNABAR_BLOCK)
                .add(ModBlocks.NEBULAR_BLOCK)
                .add(ModBlocks.MITHRIL_BLOCK)
        ;

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.ALUMINIUM_ORE)
                .add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE)
                .add(ModBlocks.RAW_ALUMINIUM_BLOCK)
                .add(ModBlocks.ALUMINIUM_BLOCK)

                .add(ModBlocks.LEAD_ORE)
                .add(ModBlocks.RAW_LEAD_BLOCK)
                .add(ModBlocks.LEAD_BLOCK)

                .add(ModBlocks.MEGAREGOLITH_IRON_ORE)

                .add(ModBlocks.RED_DEEPSLATE_ALUMINIUM_ORE)
                .add(ModBlocks.RED_DEEPSLATE_LEAD_ORE)
                .add(ModBlocks.RED_DEEPSLATE_IRON_ORE)
                .add(ModBlocks.RED_DEEPSLATE_COPPER_ORE)
                .add(ModBlocks.RED_DEEPSLATE_LAPIS_ORE)

                .add(ModBlocks.STEEL_BLOCK)
        ;

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)

                .add(ModBlocks.DEEPSLATE_URANIUM_ORE)
                .add(ModBlocks.RAW_URANIUM_BLOCK)
                .add(ModBlocks.URANIUM_BLOCK)

                .add(ModBlocks.RED_DEEPSLATE_URANIUM_ORE)
                .add(ModBlocks.RED_DEEPSLATE_GOLD_ORE)
                .add(ModBlocks.RED_DEEPSLATE_REDSTONE_ORE)
                .add(ModBlocks.RED_DEEPSLATE_EMERALD_ORE)
                .add(ModBlocks.RED_DEEPSLATE_DIAMOND_ORE)

                .add(ModBlocks.RED_DEEPSLATE_ZINC_ORE)
        ;

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.PREHISTORIC_DEBRIS)
        ;

        getOrCreateTagBuilder(BlockTags.WOOL)
                .add(ModBlocks.CURSED_WOOL)
        ;

        getOrCreateTagBuilder(BlockTags.WOOL_CARPETS)
                .add(ModBlocks.CURSED_CARPET)
        ;

        getOrCreateTagBuilder(BlockTags.WALL_POST_OVERRIDE)
                .add(ModBlocks.SULFUR_TORCH)
                .add(ModBlocks.EXTINGUISHED_TORCH)
                .add(ModBlocks.EXTINGUISHED_SOUL_TORCH)
                .add(ModBlocks.EXTINGUISHED_SULFUR_TORCH)
        ;

        getOrCreateTagBuilder(BlockTags.DRAGON_IMMUNE)
                .add(ModBlocks.LUNAR_BEDROCK)
                .add(ModBlocks.BEDROCK_MITHRIL_ORE)
                .add(ModBlocks.RAW_MITHRIL_BLOCK)
                .add(ModBlocks.MITHRIL_BLOCK)
                .add(ModBlocks.MITHRIL_ANVIL)
                .add(ModBlocks.MITHRIL_BARS)
                .add(ModBlocks.END_ENDURIUM_ORE)
                .add(ModBlocks.RAW_ENDURIUM_BLOCK)
                .add(ModBlocks.ENDURIUM_BLOCK)
                .add(ModBlocks.REINFORCED_RED_DEEPSLATE)
                .add(ModBlocks.ALIEN_GATE)
                .add(ModBlocks.ALIEN_BRICKS)
                .add(ModBlocks.ALIEN_BRICK_STAIRS)
                .add(ModBlocks.ALIEN_BRICK_SLAB)
                .add(ModBlocks.ALIEN_BRICK_WALL)
                .add(ModBlocks.ALIEN_PILLAR)

                .add(ModBlocks.YELLOW_WALLPAPERED_WALL)
                .add(ModBlocks.STRIPPED_YELLOW_WALLPAPERED_WALL)
                .add(ModBlocks.CEILING_LIGHT)
                .add(ModBlocks.CEILING_TILE)
                .add(ModBlocks.CEILING_TILE_STAIRS)
                .add(ModBlocks.HORIZONTAL_CEILING_TILE_STAIRS)
                .add(ModBlocks.MOIST_CARPET)
                .add(ModBlocks.MOIST_CARPET_STAIRS)
                .add(ModBlocks.HORIZONTAL_MOIST_CARPET_STAIRS)
                .add(ModBlocks.FLOOR_TILE)
                .add(ModBlocks.FRAGILE_LIGHT_SWITCH)
                .add(ModBlocks.LIGHT_SWITCH)
                .add(ModBlocks.BACKROOMS_ELEVATOR_CONTROLLER)
                .add(ModBlocks.BACKROOMS_ELEVATOR_BASE)
                .add(ModBlocks.VOID)
        ;

        getOrCreateTagBuilder(BlockTags.WITHER_IMMUNE)
                .add(ModBlocks.LUNAR_BEDROCK)
                .add(ModBlocks.BEDROCK_MITHRIL_ORE)
                .add(ModBlocks.RAW_MITHRIL_BLOCK)
                .add(ModBlocks.MITHRIL_BLOCK)
                .add(ModBlocks.MITHRIL_ANVIL)
                .add(ModBlocks.MITHRIL_BARS)
                .add(ModBlocks.REINFORCED_RED_DEEPSLATE)
                .add(ModBlocks.ALIEN_GATE)
                .add(ModBlocks.ALIEN_BRICKS)
                .add(ModBlocks.ALIEN_BRICK_STAIRS)
                .add(ModBlocks.ALIEN_BRICK_SLAB)
                .add(ModBlocks.ALIEN_BRICK_WALL)
                .add(ModBlocks.ALIEN_PILLAR)

                .add(ModBlocks.YELLOW_WALLPAPERED_WALL)
                .add(ModBlocks.STRIPPED_YELLOW_WALLPAPERED_WALL)
                .add(ModBlocks.CEILING_LIGHT)
                .add(ModBlocks.CEILING_TILE)
                .add(ModBlocks.CEILING_TILE_STAIRS)
                .add(ModBlocks.HORIZONTAL_CEILING_TILE_STAIRS)
                .add(ModBlocks.MOIST_CARPET)
                .add(ModBlocks.MOIST_CARPET_STAIRS)
                .add(ModBlocks.HORIZONTAL_MOIST_CARPET_STAIRS)
                .add(ModBlocks.FLOOR_TILE)
                .add(ModBlocks.FRAGILE_LIGHT_SWITCH)
                .add(ModBlocks.LIGHT_SWITCH)
                .add(ModBlocks.BACKROOMS_ELEVATOR_CONTROLLER)
                .add(ModBlocks.BACKROOMS_ELEVATOR_BASE)
                .add(ModBlocks.VOID)
        ;

        getOrCreateTagBuilder(BlockTags.FEATURES_CANNOT_REPLACE)
                .add(ModBlocks.LUNAR_BEDROCK)
                .add(ModBlocks.BEDROCK_MITHRIL_ORE)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.TOOL_LEVEL_4)
                .add(ModBlocks.HELIORITE_ORE)
                .add(ModBlocks.DEEPSLATE_HELIORITE_ORE)
                .add(ModBlocks.HELIORITE_COMB_BLOCK)
                .add(ModBlocks.HELIORITE_BLOCK)
                .add(ModBlocks.END_ENDURIUM_ORE)
                .add(ModBlocks.RAW_ENDURIUM_BLOCK)
                .add(ModBlocks.ENDURIUM_BLOCK)

                .add(ModBlocks.RED_DEEPSLATE_HELIORITE_ORE)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.TOOL_LEVEL_5)
                .add(ModBlocks.PALLADIUM_ORE)
                .add(ModBlocks.DEEPSLATE_PALLADIUM_ORE)
                .add(ModBlocks.RAW_PALLADIUM_BLOCK)
                .add(ModBlocks.PALLADIUM_BLOCK)
                .add(ModBlocks.RED_DEEPSLATE_PALLADIUM_ORE)
                .add(ModBlocks.JURASSOLINE_ORE)
                .add(ModBlocks.DEEPSLATE_JURASSOLINE_ORE)
                .add(ModBlocks.JURASSOLINE_CRYSTAL_BLOCK)
                .add(ModBlocks.JURASSOLINE_BLOCK)
                .add(ModBlocks.RED_DEEPSLATE_JURASSOLINE_ORE)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.TOOL_LEVEL_6)
                .add(ModBlocks.CINNABAR_ORE)
                .add(ModBlocks.DEEPSLATE_CINNABAR_ORE)
                .add(ModBlocks.CINNABAR_CRYSTAL_BLOCK)
                .add(ModBlocks.CINNABAR_BLOCK)
                .add(ModBlocks.RED_DEEPSLATE_CINNABAR_ORE)
                .add(ModBlocks.NEBULAR_ORE)
                .add(ModBlocks.DEEPSLATE_NEBULAR_ORE)
                .add(ModBlocks.RAW_NEBULAR_BLOCK)
                .add(ModBlocks.NEBULAR_BLOCK)
                .add(ModBlocks.RED_DEEPSLATE_NEBULAR_ORE)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.TOOL_LEVEL_7)
                .add(ModBlocks.BEDROCK_MITHRIL_ORE)
                .add(ModBlocks.RAW_MITHRIL_BLOCK)
                .add(ModBlocks.MITHRIL_BLOCK)
                .add(ModBlocks.MITHRIL_ANVIL)
                .add(ModBlocks.MITHRIL_BARS)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.TOOL_LEVEL_8)
                .add(ModBlocks.MEGAREGOLITH_LUNAR_CALLAINUS_ORE)
                .add(ModBlocks.TELEPORTER)
                .add(ModBlocks.ALIEN_CONTROL_PANEL)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.TOOL_LEVEL_9)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.TOOL_LEVEL_10)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.ELEVATOR_CONTROLLERS)
                .add(ModBlocks.MINING_ELEVATOR_CONTROLLER)
                .add(ModBlocks.WOODEN_ELEVATOR_CONTROLLER)
                .add(ModBlocks.QUARTZ_ELEVATOR_CONTROLLER)
                .add(ModBlocks.GLASS_ELEVATOR_CONTROLLER)
                .add(ModBlocks.BACKROOMS_ELEVATOR_CONTROLLER)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.ELEVATOR_BASES)
                .add(ModBlocks.MINING_ELEVATOR_BASE)
                .add(ModBlocks.WOODEN_ELEVATOR_BASE)
                .add(ModBlocks.QUARTZ_ELEVATOR_BASE)
                .add(ModBlocks.GLASS_ELEVATOR_BASE)
                .add(ModBlocks.BACKROOMS_ELEVATOR_BASE)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.TELEVISIONS)
                .addTag(ModTags.Blocks.TELEVISIONS_OFF)
                .addTag(ModTags.Blocks.TELEVISIONS_ON)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.TELEVISIONS_OFF)
                .add(ModBlocks.TELEVISION)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.TELEVISIONS_ON)
                .add(ModBlocks.TELEVISION_ON)
                .add(ModBlocks.TELEVISION_GAME)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.COMPUTERS)
                .addTag(ModTags.Blocks.COMPUTERS_OFF)
                .addTag(ModTags.Blocks.COMPUTERS_ON)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.COMPUTERS_OFF)
                .add(ModBlocks.COMPUTER)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.COMPUTERS_ON)
                .add(ModBlocks.COMPUTER_ON)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.INTERDIMENSIONAL_RECEIVERS)
                .add(ModBlocks.INTERDIMENSIONAL_RECEIVER)
        ;

        getOrCreateTagBuilder(ModTags.Blocks.INFINIBURN_PREHISTORIC)
                .addOptionalTag(Identifier.tryParse("minecraft:infiniburn_overworld"))
        ;

        getOrCreateTagBuilder(ModTags.Blocks.INFINIBURN_LUNA)
        ;
    }
}
