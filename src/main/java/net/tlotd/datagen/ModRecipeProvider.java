package net.tlotd.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.tlotd.block.ModBlocks;
import net.tlotd.fluid.ModFluids;
import net.tlotd.item.ModItems;
import net.tlotd.util.ModTags;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {

    private static final List<ItemConvertible> URANIUM_BLASTABLES = List.of(ModItems.URANIUM, ModBlocks.DEEPSLATE_URANIUM_ORE, ModBlocks.RED_DEEPSLATE_URANIUM_ORE);

    private static final List<ItemConvertible> ENDURIUM_BLASTABLES = List.of(ModItems.ENDURIUM_CRYSTAL, ModBlocks.END_ENDURIUM_ORE);
    private static final List<ItemConvertible> PALLADIUM_BLASTABLES = List.of(ModItems.RAW_PALLADIUM, ModBlocks.DEEPSLATE_PALLADIUM_ORE, ModBlocks.RED_DEEPSLATE_PALLADIUM_ORE);
    private static final List<ItemConvertible> JURASSOLINE_BLASTABLES = List.of(ModItems.JURASSOLINE_CRYSTAL, ModBlocks.DEEPSLATE_JURASSOLINE_ORE, ModBlocks.RED_DEEPSLATE_JURASSOLINE_ORE);
    private static final List<ItemConvertible> CINNABAR_BLASTABLES = List.of(ModItems.CINNABAR_CRYSTAL, ModBlocks.DEEPSLATE_CINNABAR_ORE, ModBlocks. RED_DEEPSLATE_CINNABAR_ORE);
    private static final List<ItemConvertible> NEBULAR_BLASTABLES = List.of(ModItems.NEBULAR_CRYSTAL, ModBlocks.DEEPSLATE_NEBULAR_ORE, ModBlocks. RED_DEEPSLATE_NEBULAR_ORE);
    private static final List<ItemConvertible> MITHRIL_BLASTABLES = List.of(ModItems.RAW_MITHRIL, ModBlocks.BEDROCK_MITHRIL_ORE);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    public static void offerReversibleNuggetCompactingRecipes(Consumer<RecipeJsonProvider> exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem) {
        offerReversibleCompactingRecipes(exporter, reverseCategory, baseItem, compactingCategory, compactItem, getRecipeName(compactItem) + "_from_nugget", (String)null, getRecipeName(baseItem), (String)null);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SULFUR_TORCH,4)
                .pattern("#")
                .pattern("I")
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "sulfur_dusts")))
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .criterion(hasItem(ModItems.SULFUR), conditionsFromItem(ModItems.SULFUR))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SULFUR_TORCH)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SULFUR_LANTERN)
                .pattern("###")
                .pattern("#I#")
                .pattern("###")
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "iron_nuggets")))
                .input('I', ModItems.SULFUR_TORCH)
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .criterion(hasItem(ModItems.SULFUR_TORCH), conditionsFromItem(ModItems.SULFUR_TORCH))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SULFUR_LANTERN)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SULFUR_CAMPFIRE)
                .pattern(" I ")
                .pattern("ISI")
                .pattern("###")
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .input('S', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "sulfur_dusts")))
                .input('#', ItemTags.LOGS_THAT_BURN)
                .criterion(hasItem(Items.OAK_LOG), conditionsFromItem(Items.OAK_LOG))
                .criterion(hasItem(ModItems.SULFUR), conditionsFromItem(ModItems.SULFUR))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SULFUR_CAMPFIRE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.WHITE_PUMPKIN)
                .pattern(" W ")
                .pattern("W#W")
                .pattern(" W ")
                .input('#', Items.PUMPKIN)
                .input('W', Items.WHITE_DYE)
                .criterion(hasItem(Items.PUMPKIN), conditionsFromItem(Items.PUMPKIN))
                .criterion(hasItem(Items.WHITE_DYE), conditionsFromItem(Items.WHITE_DYE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WHITE_PUMPKIN)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.PUMPKIN_SEEDS, 4)
                .input(ModBlocks.WHITE_PUMPKIN)
                .criterion(hasItem(ModBlocks.WHITE_PUMPKIN), conditionsFromItem(ModBlocks.WHITE_PUMPKIN))
                .offerTo(exporter, new Identifier(getRecipeName(Items.PUMPKIN_SEEDS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.WHITE_JACK_O_LANTERN)
                .pattern("#")
                .pattern("I")
                .input('#', ModBlocks.CARVED_WHITE_PUMPKIN)
                .input('I', ModItems.SULFUR_TORCH)
                .criterion(hasItem(ModBlocks.CARVED_WHITE_PUMPKIN), conditionsFromItem(ModBlocks.CARVED_WHITE_PUMPKIN))
                .criterion(hasItem(ModItems.SULFUR_TORCH), conditionsFromItem(ModItems.SULFUR_TORCH))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WHITE_JACK_O_LANTERN)));

        offerCarpetRecipe(exporter, ModBlocks.CURSED_CARPET, ModBlocks.CURSED_WOOL);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.PAPER,3)
                .pattern("###")
                .input('#', ModTags.Items.WOODEN_BARK)
                .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
                .offerTo(exporter, new Identifier(getRecipeName(Items.PAPER)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.OAK_LOG)
                .input(Items.STRIPPED_OAK_LOG)
                .input(ModItems.OAK_BARK)
                .criterion(hasItem(Items.STRIPPED_OAK_LOG), conditionsFromItem(Items.STRIPPED_OAK_LOG))
                .criterion(hasItem(ModItems.OAK_BARK), conditionsFromItem(ModItems.OAK_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(Items.OAK_LOG)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.OAK_WOOD)
                .input(Items.STRIPPED_OAK_WOOD)
                .input(ModItems.OAK_BARK)
                .criterion(hasItem(Items.STRIPPED_OAK_WOOD), conditionsFromItem(Items.STRIPPED_OAK_WOOD))
                .criterion(hasItem(ModItems.OAK_BARK), conditionsFromItem(ModItems.OAK_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(Items.OAK_WOOD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.SPRUCE_LOG)
                .input(Items.STRIPPED_SPRUCE_LOG)
                .input(ModItems.SPRUCE_BARK)
                .criterion(hasItem(Items.STRIPPED_SPRUCE_LOG), conditionsFromItem(Items.STRIPPED_SPRUCE_LOG))
                .criterion(hasItem(ModItems.SPRUCE_BARK), conditionsFromItem(ModItems.SPRUCE_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(Items.SPRUCE_LOG)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.SPRUCE_WOOD)
                .input(Items.STRIPPED_SPRUCE_WOOD)
                .input(ModItems.SPRUCE_BARK)
                .criterion(hasItem(Items.STRIPPED_SPRUCE_WOOD), conditionsFromItem(Items.STRIPPED_SPRUCE_WOOD))
                .criterion(hasItem(ModItems.SPRUCE_BARK), conditionsFromItem(ModItems.SPRUCE_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(Items.SPRUCE_WOOD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BIRCH_LOG)
                .input(Items.STRIPPED_BIRCH_LOG)
                .input(ModItems.BIRCH_BARK)
                .criterion(hasItem(Items.STRIPPED_BIRCH_LOG), conditionsFromItem(Items.STRIPPED_BIRCH_LOG))
                .criterion(hasItem(ModItems.BIRCH_BARK), conditionsFromItem(ModItems.BIRCH_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(Items.BIRCH_LOG)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BIRCH_WOOD)
                .input(Items.STRIPPED_BIRCH_WOOD)
                .input(ModItems.BIRCH_BARK)
                .criterion(hasItem(Items.STRIPPED_BIRCH_WOOD), conditionsFromItem(Items.STRIPPED_BIRCH_WOOD))
                .criterion(hasItem(ModItems.BIRCH_BARK), conditionsFromItem(ModItems.BIRCH_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(Items.BIRCH_WOOD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.JUNGLE_LOG)
                .input(Items.STRIPPED_JUNGLE_LOG)
                .input(ModItems.JUNGLE_BARK)
                .criterion(hasItem(Items.STRIPPED_JUNGLE_LOG), conditionsFromItem(Items.STRIPPED_JUNGLE_LOG))
                .criterion(hasItem(ModItems.JUNGLE_BARK), conditionsFromItem(ModItems.JUNGLE_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(Items.JUNGLE_LOG)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.JUNGLE_WOOD)
                .input(Items.STRIPPED_JUNGLE_WOOD)
                .input(ModItems.JUNGLE_BARK)
                .criterion(hasItem(Items.STRIPPED_JUNGLE_WOOD), conditionsFromItem(Items.STRIPPED_JUNGLE_WOOD))
                .criterion(hasItem(ModItems.JUNGLE_BARK), conditionsFromItem(ModItems.JUNGLE_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(Items.JUNGLE_WOOD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.ACACIA_LOG)
                .input(Items.STRIPPED_ACACIA_LOG)
                .input(ModItems.ACACIA_BARK)
                .criterion(hasItem(Items.STRIPPED_ACACIA_LOG), conditionsFromItem(Items.STRIPPED_ACACIA_LOG))
                .criterion(hasItem(ModItems.ACACIA_BARK), conditionsFromItem(ModItems.ACACIA_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(Items.ACACIA_LOG)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.ACACIA_WOOD)
                .input(Items.STRIPPED_ACACIA_WOOD)
                .input(ModItems.ACACIA_BARK)
                .criterion(hasItem(Items.STRIPPED_ACACIA_WOOD), conditionsFromItem(Items.STRIPPED_ACACIA_WOOD))
                .criterion(hasItem(ModItems.ACACIA_BARK), conditionsFromItem(ModItems.ACACIA_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(Items.ACACIA_WOOD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.DARK_OAK_LOG)
                .input(Items.STRIPPED_DARK_OAK_LOG)
                .input(ModItems.DARK_OAK_BARK)
                .criterion(hasItem(Items.STRIPPED_DARK_OAK_LOG), conditionsFromItem(Items.STRIPPED_DARK_OAK_LOG))
                .criterion(hasItem(ModItems.DARK_OAK_BARK), conditionsFromItem(ModItems.DARK_OAK_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(Items.DARK_OAK_LOG)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.DARK_OAK_WOOD)
                .input(Items.STRIPPED_DARK_OAK_WOOD)
                .input(ModItems.DARK_OAK_BARK)
                .criterion(hasItem(Items.STRIPPED_DARK_OAK_WOOD), conditionsFromItem(Items.STRIPPED_DARK_OAK_WOOD))
                .criterion(hasItem(ModItems.DARK_OAK_BARK), conditionsFromItem(ModItems.DARK_OAK_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(Items.DARK_OAK_WOOD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.MANGROVE_LOG)
                .input(Items.STRIPPED_MANGROVE_LOG)
                .input(ModItems.MANGROVE_BARK)
                .criterion(hasItem(Items.STRIPPED_MANGROVE_LOG), conditionsFromItem(Items.STRIPPED_MANGROVE_LOG))
                .criterion(hasItem(ModItems.MANGROVE_BARK), conditionsFromItem(ModItems.MANGROVE_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(Items.MANGROVE_LOG)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.MANGROVE_WOOD)
                .input(Items.STRIPPED_MANGROVE_WOOD)
                .input(ModItems.MANGROVE_BARK)
                .criterion(hasItem(Items.STRIPPED_MANGROVE_WOOD), conditionsFromItem(Items.STRIPPED_MANGROVE_WOOD))
                .criterion(hasItem(ModItems.MANGROVE_BARK), conditionsFromItem(ModItems.MANGROVE_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(Items.MANGROVE_WOOD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.CHERRY_LOG)
                .input(Items.STRIPPED_CHERRY_LOG)
                .input(ModItems.CHERRY_BARK)
                .criterion(hasItem(Items.STRIPPED_CHERRY_LOG), conditionsFromItem(Items.STRIPPED_CHERRY_LOG))
                .criterion(hasItem(ModItems.CHERRY_BARK), conditionsFromItem(ModItems.CHERRY_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(Items.CHERRY_LOG)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.CHERRY_WOOD)
                .input(Items.STRIPPED_CHERRY_WOOD)
                .input(ModItems.CHERRY_BARK)
                .criterion(hasItem(Items.STRIPPED_CHERRY_WOOD), conditionsFromItem(Items.STRIPPED_CHERRY_WOOD))
                .criterion(hasItem(ModItems.CHERRY_BARK), conditionsFromItem(ModItems.CHERRY_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(Items.CHERRY_WOOD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.GINKGO_LOG)
                .input(ModBlocks.STRIPPED_GINKGO_LOG)
                .input(ModItems.GINKGO_BARK)
                .criterion(hasItem(ModBlocks.STRIPPED_GINKGO_LOG), conditionsFromItem(ModBlocks.STRIPPED_GINKGO_LOG))
                .criterion(hasItem(ModItems.GINKGO_BARK), conditionsFromItem(ModItems.GINKGO_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GINKGO_LOG)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.GINKGO_WOOD)
                .input(ModBlocks.STRIPPED_GINKGO_WOOD)
                .input(ModItems.GINKGO_BARK)
                .criterion(hasItem(ModBlocks.STRIPPED_GINKGO_WOOD), conditionsFromItem(ModBlocks.STRIPPED_GINKGO_WOOD))
                .criterion(hasItem(ModItems.GINKGO_BARK), conditionsFromItem(ModItems.GINKGO_BARK))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GINKGO_WOOD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DRAGON_BANNER_PATTERN)
                .input(Items.PAPER)
                .input(Items.DRAGON_HEAD)
                .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
                .criterion(hasItem(Items.DRAGON_HEAD), conditionsFromItem(Items.DRAGON_HEAD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.DRAGON_BANNER_PATTERN)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LOTR_BANNER_PATTERN)
                .input(Items.PAPER)
                .input(Items.GOLD_NUGGET)
                .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
                .criterion(hasItem(Items.GOLD_NUGGET), conditionsFromItem(Items.GOLD_NUGGET))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.LOTR_BANNER_PATTERN)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.APPARATUS)
                .input(ModItems.URANIUM)
                .input(Items.GLOWSTONE)
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_ingots")))
                .criterion(hasItem(ModItems.URANIUM), conditionsFromItem(ModItems.URANIUM))
                .criterion(hasItem(Items.GLOWSTONE), conditionsFromItem(Items.GLOWSTONE))
                .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.APPARATUS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.GARBAGE_CAN)
                .pattern("# #")
                .pattern("# #")
                .pattern("###")
                .input('#', Items.IRON_BARS)
                .criterion(hasItem(Items.IRON_BARS), conditionsFromItem(Items.IRON_BARS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GARBAGE_CAN)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.BENCH)
                .pattern("I-I")
                .pattern("---")
                .pattern("I-I")
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .input('-', ItemTags.WOODEN_SLABS)
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .criterion(hasItem(Items.OAK_SLAB), conditionsFromItem(Items.OAK_SLAB))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.BENCH)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PIPE)
                .pattern("#-")
                .input('#', ItemTags.PLANKS)
                .input('-', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .criterion(hasItem(Items.OAK_PLANKS), conditionsFromItem(Items.OAK_PLANKS))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PIPE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PIPE_WEED_PIPE)
                .input(ModItems.PIPE)
                .input(ModItems.PIPE_WEED)
                .criterion(hasItem(ModItems.PIPE), conditionsFromItem(ModItems.PIPE))
                .criterion(hasItem(ModItems.PIPE_WEED), conditionsFromItem(ModItems.PIPE_WEED))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PIPE_WEED_PIPE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HEMP_COOKIE, 8)
                .pattern("#C#")
                .input('#', Items.WHEAT)
                .input('C', ModItems.PIPE_WEED)
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .criterion(hasItem(ModItems.PIPE_WEED), conditionsFromItem(ModItems.PIPE_WEED))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HEMP_COOKIE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PRESERVES_JAR, 3)
                .pattern("# #")
                .pattern("###")
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "colorless_glass")))
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PRESERVES_JAR)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TOAST, 3)
                .input(Items.BREAD)
                .criterion(hasItem(Items.BREAD), conditionsFromItem(Items.BREAD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TOAST)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SWEET_BERRY_JAM_TOAST, 3)
                .group("toasts")
                .input(ModBlocks.SWEET_BERRY_JAM_JAR)
                .input(ModItems.TOAST)
                .input(ModItems.TOAST)
                .input(ModItems.TOAST)
                .criterion(hasItem(ModBlocks.SWEET_BERRY_JAM_JAR), conditionsFromItem(ModBlocks.SWEET_BERRY_JAM_JAR))
                .criterion(hasItem(ModItems.TOAST), conditionsFromItem(ModItems.TOAST))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SWEET_BERRY_JAM_TOAST)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GLOW_BERRY_JAM_TOAST, 3)
                .group("toasts")
                .input(ModBlocks.GLOW_BERRY_JAM_JAR)
                .input(ModItems.TOAST)
                .input(ModItems.TOAST)
                .input(ModItems.TOAST)
                .criterion(hasItem(ModBlocks.GLOW_BERRY_JAM_JAR), conditionsFromItem(ModBlocks.GLOW_BERRY_JAM_JAR))
                .criterion(hasItem(ModItems.TOAST), conditionsFromItem(ModItems.TOAST))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GLOW_BERRY_JAM_TOAST)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STRAWBERRY_JAM_TOAST, 3)
                .group("toasts")
                .input(ModBlocks.STRAWBERRY_JAM_JAR)
                .input(ModItems.TOAST)
                .input(ModItems.TOAST)
                .input(ModItems.TOAST)
                .criterion(hasItem(ModBlocks.STRAWBERRY_JAM_JAR), conditionsFromItem(ModBlocks.STRAWBERRY_JAM_JAR))
                .criterion(hasItem(ModItems.TOAST), conditionsFromItem(ModItems.TOAST))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.STRAWBERRY_JAM_TOAST)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ORANGE_MARMELADE_TOAST, 3)
                .group("toasts")
                .input(ModBlocks.ORANGE_MARMELADE_JAR)
                .input(ModItems.TOAST)
                .input(ModItems.TOAST)
                .input(ModItems.TOAST)
                .criterion(hasItem(ModBlocks.ORANGE_MARMELADE_JAR), conditionsFromItem(ModBlocks.ORANGE_MARMELADE_JAR))
                .criterion(hasItem(ModItems.TOAST), conditionsFromItem(ModItems.TOAST))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ORANGE_MARMELADE_TOAST)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BLUE_BERRY_JAM_TOAST, 3)
                .group("toasts")
                .input(ModBlocks.BLUE_BERRY_JAM_JAR)
                .input(ModItems.TOAST)
                .input(ModItems.TOAST)
                .input(ModItems.TOAST)
                .criterion(hasItem(ModBlocks.BLUE_BERRY_JAM_JAR), conditionsFromItem(ModBlocks.BLUE_BERRY_JAM_JAR))
                .criterion(hasItem(ModItems.TOAST), conditionsFromItem(ModItems.TOAST))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BLUE_BERRY_JAM_TOAST)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ANCIENT_SOULBERRY_JAM_TOAST, 3)
                .group("toasts")
                .input(ModBlocks.ANCIENT_SOULBERRY_JAM_JAR)
                .input(ModItems.TOAST)
                .input(ModItems.TOAST)
                .input(ModItems.TOAST)
                .criterion(hasItem(ModBlocks.ANCIENT_SOULBERRY_JAM_JAR), conditionsFromItem(ModBlocks.ANCIENT_SOULBERRY_JAM_JAR))
                .criterion(hasItem(ModItems.TOAST), conditionsFromItem(ModItems.TOAST))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ANCIENT_SOULBERRY_JAM_TOAST)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STRAWBERRY_SEEDS)
                .input(ModItems.STRAWBERRY)
                .criterion(hasItem(ModItems.STRAWBERRY), conditionsFromItem(ModItems.STRAWBERRY))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.STRAWBERRY_SEEDS)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CHOCOLATE_STRAWBERRY)
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "strawberries")))
                .input(Items.COCOA_BEANS)
                .criterion(hasItem(ModItems.STRAWBERRY), conditionsFromItem(ModItems.STRAWBERRY))
                .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CHOCOLATE_STRAWBERRY)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.STRAWBERRY_CAKE)
                .pattern("BMB")
                .pattern("SWE")
                .input('B', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "strawberries")))
                .input('M', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "milk")))
                .input('S', Items.SUGAR)
                .input('W', Items.WHEAT)
                .input('E', Items.EGG)
                .criterion(hasItem(ModItems.STRAWBERRY), conditionsFromItem(ModItems.STRAWBERRY))
                .criterion(hasItem(Items.MILK_BUCKET), conditionsFromItem(Items.MILK_BUCKET))
                .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .criterion(hasItem(Items.EGG), conditionsFromItem(Items.EGG))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.STRAWBERRY_CAKE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ORANGE_SEEDS)
                .input(ModItems.ORANGE)
                .criterion(hasItem(ModItems.ORANGE), conditionsFromItem(ModItems.ORANGE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ORANGE_SEEDS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ORANGE_CAKE)
                .pattern("OMO")
                .pattern("SWE")
                .input('O', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "oranges")))
                .input('M', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "milk")))
                .input('S', Items.SUGAR)
                .input('W', Items.WHEAT)
                .input('E', Items.EGG)
                .criterion(hasItem(ModItems.ORANGE), conditionsFromItem(ModItems.ORANGE))
                .criterion(hasItem(Items.MILK_BUCKET), conditionsFromItem(Items.MILK_BUCKET))
                .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .criterion(hasItem(Items.EGG), conditionsFromItem(Items.EGG))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.ORANGE_CAKE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PIPE_WEED_SEEDS)
                .input(ModItems.PIPE_WEED)
                .criterion(hasItem(ModItems.PIPE_WEED), conditionsFromItem(ModItems.PIPE_WEED))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PIPE_WEED_SEEDS)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.BW_STICKER)
                .input(Items.PAPER)
                .input(Items.SLIME_BALL)
                .input(Items.GOLD_NUGGET)
                .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
                .criterion(hasItem(Items.SLIME_BALL), conditionsFromItem(Items.SLIME_BALL))
                .criterion(hasItem(Items.GOLD_NUGGET), conditionsFromItem(Items.GOLD_NUGGET))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.BW_STICKER)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.TLOTD_STICKER)
                .input(Items.PAPER)
                .input(Items.SLIME_BALL)
                .input(ModItems.MITHRIL_NUGGET)
                .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
                .criterion(hasItem(Items.SLIME_BALL), conditionsFromItem(Items.SLIME_BALL))
                .criterion(hasItem(ModItems.MITHRIL_NUGGET), conditionsFromItem(ModItems.MITHRIL_NUGGET))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.TLOTD_STICKER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FESTIVE_LIGHTS, 8)
                .pattern("CCC")
                .pattern("#R#")
                .pattern("###")
                .input('C', ModItems.COPPER_WIRE)
                .input('R', Items.REDSTONE_LAMP)
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "glass_blocks")))
                .criterion(hasItem(ModItems.COPPER_WIRE), conditionsFromItem(ModItems.COPPER_WIRE))
                .criterion(hasItem(Items.REDSTONE_LAMP), conditionsFromItem(Items.REDSTONE_LAMP))
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FESTIVE_LIGHTS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PRESENT)
                .pattern("PYP")
                .pattern("RER")
                .pattern("PYP")
                .input('P', Items.PAPER)
                .input('Y', Items.YELLOW_DYE)
                .input('R', Items.RED_DYE)
                .input('E', Items.ECHO_SHARD)
                .criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
                .criterion(hasItem(Items.YELLOW_DYE), conditionsFromItem(Items.YELLOW_DYE))
                .criterion(hasItem(Items.RED_DYE), conditionsFromItem(Items.RED_DYE))
                .criterion(hasItem(Items.ECHO_SHARD), conditionsFromItem(Items.ECHO_SHARD))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PRESENT)));

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GRAVESTONE, Blocks.COBBLESTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_GRAVESTONE, Blocks.MOSSY_COBBLESTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GRAVESTONE_CROSS, Blocks.COBBLESTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_GRAVESTONE_CROSS, Blocks.MOSSY_COBBLESTONE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMALL_GRAVESTONE, Blocks.COBBLESTONE);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SKELETON)
                .input(Items.BONE).input(Items.SKELETON_SKULL).input(Items.BONE)
                .input(Items.BONE).input(Items.BONE).input(Items.BONE)
                .input(Items.BONE).input(Items.BONE).input(Items.BONE)
                .criterion(hasItem(Items.BONE), conditionsFromItem(Items.BONE))
                .criterion(hasItem(Items.SKELETON_SKULL), conditionsFromItem(Items.SKELETON_SKULL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SKELETON)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SKELETON)
                .input(ModBlocks.EMERGING_SKELETON)
                .criterion(hasItem(ModBlocks.EMERGING_SKELETON), conditionsFromItem(ModBlocks.EMERGING_SKELETON))
                .offerTo(exporter, new Identifier("skeleton_from_emerging_skeleton"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.EMERGING_SKELETON)
                .input(ModBlocks.SKELETON)
                .criterion(hasItem(ModBlocks.SKELETON), conditionsFromItem(ModBlocks.SKELETON))
                .offerTo(exporter, new Identifier("skeleton_to_emerging_skeleton"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.EFFIGIES)
                .pattern("SSS")
                .pattern("III")
                .pattern("III")
                .input('S', Items.STRING)
                .input('I', ModTags.Items.STICK_EFFIGIES)
                .criterion(hasItem(Items.STRING), conditionsFromItem(Items.STRING))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.EFFIGIES)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.STICK_CROSS)
                .pattern(" I ")
                .pattern("III")
                .pattern(" I ")
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.STICK_CROSS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STICK_EFFIGY)
                .pattern("III")
                .pattern("III")
                .pattern("I I")
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.STICK_EFFIGY)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STICK_FIGURE)
                .pattern("III")
                .pattern(" I ")
                .pattern("I I")
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.STICK_FIGURE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PLANCHETTE)
                .pattern(" ##")
                .pattern("#G#")
                .pattern(" # ")
                .input('#', ItemTags.PLANKS)
                .input('G', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "glass_panes")))
                .criterion(hasItem(Items.OAK_PLANKS), conditionsFromItem(Items.OAK_PLANKS))
                .criterion(hasItem(Items.GLASS_PANE), conditionsFromItem(Items.GLASS_PANE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PLANCHETTE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GUIDEBOOK)
                .input(Items.BOOK)
                .input(Items.COPPER_INGOT)
                .criterion(hasItem(Items.BOOK), conditionsFromItem(Items.BOOK))
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GUIDEBOOK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FLASH_DRIVE)
                .pattern(" CB")
                .pattern("WB ")
                .pattern("I  ")
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "iron_ingots")))
                .input('W', ModItems.COPPER_WIRE)
                .input('C', ModItems.INTEGRATED_CIRCUIT)
                .input('B', ModTags.Items.CIRCUIT_BOARDS)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(ModItems.COPPER_WIRE), conditionsFromItem(ModItems.COPPER_WIRE))
                .criterion(hasItem(ModItems.INTEGRATED_CIRCUIT), conditionsFromItem(ModItems.INTEGRATED_CIRCUIT))
                .criterion(hasItem(ModItems.CIRCUIT_BOARD), conditionsFromItem(ModItems.CIRCUIT_BOARD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.FLASH_DRIVE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CREEPER_PLUSHIE)
                .pattern("I")
                .pattern("#")
                .input('I', Items.GUNPOWDER)
                .input('#', ItemTags.WOOL)
                .criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(Items.GUNPOWDER))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.CREEPER_PLUSHIE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ZOMBIE_PLUSHIE)
                .pattern("I")
                .pattern("#")
                .input('I', Items.ROTTEN_FLESH)
                .input('#', ItemTags.WOOL)
                .criterion(hasItem(Items.ROTTEN_FLESH), conditionsFromItem(Items.ROTTEN_FLESH))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.ZOMBIE_PLUSHIE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PLAYER_PLUSHIE)
                .pattern("I")
                .pattern("#")
                .input('I', Items.CRAFTING_TABLE)
                .input('#', ItemTags.WOOL)
                .criterion(hasItem(Items.CRAFTING_TABLE), conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PLAYER_PLUSHIE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COPPER_SICKLE)
                .pattern(" #")
                .pattern("I ")
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "copper_ingots")))
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COPPER_SICKLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GOLDEN_SICKLE)
                .pattern(" #")
                .pattern("I ")
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "gold_ingots")))
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GOLDEN_SICKLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NETHERITE_SICKLE)
                .pattern(" #")
                .pattern("I ")
                .input('#', Items.NETHERITE_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NETHERITE_SICKLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NETHERITE_FORGING_HAMMER)
                .pattern("#")
                .pattern("I")
                .input('#', Items.NETHERITE_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NETHERITE_FORGING_HAMMER)));

        offerFoodCookingRecipe(exporter, "campfire", RecipeSerializer.CAMPFIRE_COOKING, 600, ModItems.CURED_MEAT, ModItems.COOKED_MEAT, 0.5f);
        offerFoodCookingRecipe(exporter, "smoker", RecipeSerializer.SMOKING, 100, ModItems.CURED_MEAT, ModItems.COOKED_MEAT, 0.5f);
        offerFoodCookingRecipe(exporter, "furnace", RecipeSerializer.SMELTING, 200, ModItems.CURED_MEAT, ModItems.COOKED_MEAT, 0.5f);

        offerFoodCookingRecipe(exporter, "campfire", RecipeSerializer.CAMPFIRE_COOKING, 600, ModItems.DINOSAUR_MEAT, ModItems.COOKED_DINOSAUR_MEAT, 0.5f);
        offerFoodCookingRecipe(exporter, "smoker", RecipeSerializer.SMOKING, 100, ModItems.DINOSAUR_MEAT, ModItems.COOKED_DINOSAUR_MEAT, 0.5f);
        offerFoodCookingRecipe(exporter, "furnace", RecipeSerializer.SMELTING, 200, ModItems.DINOSAUR_MEAT, ModItems.COOKED_DINOSAUR_MEAT, 0.5f);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.LEATHER, 4)
                .input(ModItems.DINOSAUR_HIDE)
                .criterion(hasItem(ModItems.DINOSAUR_HIDE), conditionsFromItem(ModItems.DINOSAUR_HIDE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.DINOSAUR_HIDE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FLOUR,3)
                .input(Items.WHEAT)
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.FLOUR)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BREADCRUMBS,3)
                .input(ModItems.TOAST)
                .criterion(hasItem(ModItems.TOAST), conditionsFromItem(ModItems.TOAST))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BREADCRUMBS)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_SCHNITZEL,1)
                .input(ModTags.Items.RAW_MEAT)
                .input(ModItems.BREADCRUMBS)
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "flour")))
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "eggs")))
                .criterion(hasItem(ModItems.BREADCRUMBS), conditionsFromItem(ModItems.BREADCRUMBS))
                .criterion(hasItem(ModItems.FLOUR), conditionsFromItem(ModItems.FLOUR))
                .criterion(hasItem(Items.EGG), conditionsFromItem(Items.EGG))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.RAW_SCHNITZEL)));

        offerFoodCookingRecipe(exporter, "campfire", RecipeSerializer.CAMPFIRE_COOKING, 600, ModItems.RAW_SCHNITZEL, ModItems.SCHNITZEL, 0.5f);
        offerFoodCookingRecipe(exporter, "smoker", RecipeSerializer.SMOKING, 100, ModItems.RAW_SCHNITZEL, ModItems.SCHNITZEL, 0.5f);
        offerFoodCookingRecipe(exporter, "furnace", RecipeSerializer.SMELTING, 200, ModItems.RAW_SCHNITZEL, ModItems.SCHNITZEL, 0.5f);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CALAMARI,3)
                .input(ModItems.SQUID)
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "flour")))
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "eggs")))
                .criterion(hasItem(ModItems.SQUID), conditionsFromItem(ModItems.SQUID))
                .criterion(hasItem(ModItems.FLOUR), conditionsFromItem(ModItems.FLOUR))
                .criterion(hasItem(Items.EGG), conditionsFromItem(Items.EGG))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CALAMARI)));

        offerFoodCookingRecipe(exporter, "campfire", RecipeSerializer.CAMPFIRE_COOKING, 600, ModItems.CALAMARI, ModItems.FRIED_CALAMARI, 0.5f);
        offerFoodCookingRecipe(exporter, "smoker", RecipeSerializer.SMOKING, 100, ModItems.CALAMARI, ModItems.FRIED_CALAMARI, 0.5f);
        offerFoodCookingRecipe(exporter, "furnace", RecipeSerializer.SMELTING, 200, ModItems.CALAMARI, ModItems.FRIED_CALAMARI, 0.5f);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MAULTASCHE,2)
                .input(ModTags.Items.RAW_MEAT)
                .input(Items.WHEAT)
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MAULTASCHE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TINTED_GLASS_FLASK)
                .pattern("# #")
                .pattern(" # ")
                .input('#', Items.TINTED_GLASS)
                .criterion(hasItem(Items.TINTED_GLASS), conditionsFromItem(Items.TINTED_GLASS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TINTED_GLASS_FLASK)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SOUL_FLASK)
                .input(ModItems.TINTED_GLASS_FLASK)
                .input(ItemTags.SOUL_FIRE_BASE_BLOCKS)
                .criterion(hasItem(ModItems.TINTED_GLASS_FLASK), conditionsFromItem(ModItems.TINTED_GLASS_FLASK))
                .criterion(hasItem(Items.SOUL_SAND), conditionsFromItem(Items.SOUL_SAND))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SOUL_FLASK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.WITCHING_TABLE)
                .pattern("C#C")
                .pattern("GGG")
                .pattern("###")
                .input('C', ModTags.Items.CINNABAR_OR_NEBULAR)
                .input('G', Items.GLASS_BOTTLE)
                .input('#', ModBlocks.FANCY_CHARRED_PLANKS)
                .criterion(hasItem(ModItems.CINNABAR_CRYSTAL), conditionsFromItem(ModItems.CINNABAR_CRYSTAL))
                .criterion(hasItem(Items.GLASS_BOTTLE), conditionsFromItem(Items.GLASS_BOTTLE))
                .criterion(hasItem(ModBlocks.FANCY_CHARRED_PLANKS), conditionsFromItem(ModBlocks.FANCY_CHARRED_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WITCHING_TABLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CATHODE_RAY_TUBE)
                .pattern("IGI")
                .pattern("GCR")
                .pattern("IGI")
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "iron_ingots")))
                .input('G', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "colorless_glass_panes")))
                .input('C', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "copper_ingots")))
                .input('R', Items.REDSTONE_LAMP)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.GLASS_PANE), conditionsFromItem(Items.GLASS_PANE))
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .criterion(hasItem(Items.REDSTONE_LAMP), conditionsFromItem(Items.REDSTONE_LAMP))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CATHODE_RAY_TUBE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LIQUID_CRYSTAL_DISPLAY_PANEL)
                .pattern("GGG")
                .pattern("GAR")
                .pattern("CCC")
                .input('G', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "colorless_glass_panes")))
                .input('A', Items.AMETHYST_SHARD)
                .input('R', Items.REDSTONE_LAMP)
                .input('C', ModTags.Items.CIRCUIT_BOARDS)
                .criterion(hasItem(Items.GLASS_PANE), conditionsFromItem(Items.GLASS_PANE))
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .criterion(hasItem(Items.REDSTONE_LAMP), conditionsFromItem(Items.REDSTONE_LAMP))
                .criterion(hasItem(ModItems.CIRCUIT_BOARD), conditionsFromItem(ModItems.CIRCUIT_BOARD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.LIQUID_CRYSTAL_DISPLAY_PANEL)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.VHS_CASSETTE)
                .input(ModTags.Items.VHS_CASSETTES)
                .criterion(hasItem(ModItems.VHS_CASSETTE), conditionsFromItem(ModItems.VHS_CASSETTE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.VHS_CASSETTE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.INTERDIMENSIONAL_RECEIVER)
                .pattern("M M")
                .pattern("IRI")
                .pattern("TXT")
                .input('M', ModItems.MITHRIL_WIRE)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "iron_ingots")))
                .input('T', ModTags.Items.TRANSCENDENT_CIRCUIT_BOARDS)
                .input('X', ModItems.XEN_CRYSTAL)
                .input('R', Items.REDSTONE_LAMP)
                .criterion(hasItem(ModItems.MITHRIL_WIRE), conditionsFromItem(ModItems.MITHRIL_WIRE))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(ModItems.TRANSCENDENT_CIRCUIT_BOARD), conditionsFromItem(ModItems.TRANSCENDENT_CIRCUIT_BOARD))
                .criterion(hasItem(ModItems.XEN_CRYSTAL), conditionsFromItem(ModItems.XEN_CRYSTAL))
                .criterion(hasItem(Items.REDSTONE_LAMP), conditionsFromItem(Items.REDSTONE_LAMP))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.INTERDIMENSIONAL_RECEIVER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModItems.HOLOGRAPHIC_PROJECTOR)
                .pattern(" B ")
                .pattern("SFS")
                .input('B', Items.BEACON)
                .input('S', ModItems.SUPERCONDUCTING_WIRE)
                .input('F', ModItems.FUTURISTIC_CIRCUIT_BOARD)
                .criterion(hasItem(Items.BEACON), conditionsFromItem(Items.BEACON))
                .criterion(hasItem(ModItems.SUPERCONDUCTING_WIRE), conditionsFromItem(ModItems.SUPERCONDUCTING_WIRE))
                .criterion(hasItem(ModItems.FUTURISTIC_CIRCUIT_BOARD), conditionsFromItem(ModItems.FUTURISTIC_CIRCUIT_BOARD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HOLOGRAPHIC_PROJECTOR)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TELEPORTER)
                .pattern("HHH")
                .pattern("AXA")
                .pattern("SFS")
                .input('H', ModItems.HOLOGRAPHIC_PROJECTOR)
                .input('A', ModItems.ALIEN_METAL)
                .input('X', ModItems.XEN_CRYSTAL)
                .input('S', ModItems.SUPERCONDUCTING_WIRE)
                .input('F', ModItems.FUTURISTIC_CIRCUIT_BOARD)
                .criterion(hasItem(ModItems.HOLOGRAPHIC_PROJECTOR), conditionsFromItem(ModItems.HOLOGRAPHIC_PROJECTOR))
                .criterion(hasItem(ModItems.ALIEN_METAL), conditionsFromItem(ModItems.ALIEN_METAL))
                .criterion(hasItem(ModItems.XEN_CRYSTAL), conditionsFromItem(ModItems.XEN_CRYSTAL))
                .criterion(hasItem(ModItems.SUPERCONDUCTING_WIRE), conditionsFromItem(ModItems.SUPERCONDUCTING_WIRE))
                .criterion(hasItem(ModItems.FUTURISTIC_CIRCUIT_BOARD), conditionsFromItem(ModItems.FUTURISTIC_CIRCUIT_BOARD))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.TELEPORTER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SIGNAL_TRANSMITTER_ANTENNA)
                .pattern(" C ")
                .pattern("CWC")
                .pattern("CWC")
                .input('C', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "copper_ingots")))
                .input('W', ModItems.COPPER_WIRE)
                .criterion(hasItem(Items.COPPER_INGOT), conditionsFromItem(Items.COPPER_INGOT))
                .criterion(hasItem(ModItems.COPPER_WIRE), conditionsFromItem(ModItems.COPPER_WIRE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SIGNAL_TRANSMITTER_ANTENNA)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SUPERCONDUCTING_WIRE, 3)
                .input(ModItems.FRAGMENTED_FUTURISTIC_CIRCUIT_BOARD)
                .criterion(hasItem(ModItems.FRAGMENTED_FUTURISTIC_CIRCUIT_BOARD), conditionsFromItem(ModItems.FRAGMENTED_FUTURISTIC_CIRCUIT_BOARD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SUPERCONDUCTING_WIRE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.HEV_CHARGER)
                .pattern("SXS")
                .pattern("AAD")
                .pattern("SXS")
                .input('X', ModItems.XEN_CRYSTAL)
                .input('D', ModItems.LIQUID_CRYSTAL_DISPLAY_PANEL)
                .input('A', ModTags.Items.ADVANCED_CIRCUIT_BOARDS)
                .input('S', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_ingots")))
                .criterion(hasItem(ModItems.XEN_CRYSTAL), conditionsFromItem(ModItems.XEN_CRYSTAL))
                .criterion(hasItem(ModItems.LIQUID_CRYSTAL_DISPLAY_PANEL), conditionsFromItem(ModItems.LIQUID_CRYSTAL_DISPLAY_PANEL))
                .criterion(hasItem(ModItems.ADVANCED_CIRCUIT_BOARD), conditionsFromItem(ModItems.ADVANCED_CIRCUIT_BOARD))
                .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.HEV_CHARGER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.INCUBATOR)
                .pattern("IRI")
                .pattern("IHI")
                .pattern("SSS")
                .input('R', Items.REDSTONE_LAMP)
                .input('H', Items.HAY_BLOCK)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .input('S', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_ingots")))
                .criterion(hasItem(Items.REDSTONE_LAMP), conditionsFromItem(Items.REDSTONE_LAMP))
                .criterion(hasItem(Items.HAY_BLOCK), conditionsFromItem(Items.HAY_BLOCK))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.INCUBATOR)));

        offerBlasting(exporter, List.of(ModItems.SUPERCONDUCTING_WIRE), RecipeCategory.MISC, ModItems.ALIEN_METAL, 0.7f, 400, "alien_metal");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MINING_ELEVATOR_CONTROLLER)
                .group("elevator_controller")
                .pattern("###")
                .pattern("I I")
                .pattern("IBC")
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_ingots")))
                .input('I', Items.IRON_BARS)
                .input('B', ItemTags.BUTTONS)
                .input('C', ModItems.COPPER_WIRE)
                .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                .criterion(hasItem(Items.IRON_BARS), conditionsFromItem(Items.IRON_BARS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.MINING_ELEVATOR_CONTROLLER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MINING_ELEVATOR_BASE)
                .group("elevator_base")
                .pattern("I I")
                .pattern("IEI")
                .pattern("###")
                .input('I', Items.IRON_BARS)
                .input('#', ItemTags.PLANKS)
                .input('E', Items.ENDER_PEARL)
                .criterion(hasItem(Items.IRON_BARS), conditionsFromItem(Items.IRON_BARS))
                .criterion(hasItem(Items.ENDER_PEARL), conditionsFromItem(Items.ENDER_PEARL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.MINING_ELEVATOR_BASE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WOODEN_ELEVATOR_CONTROLLER)
                .group("elevator_controller")
                .pattern("###")
                .pattern("#P#")
                .pattern("#BC")
                .input('#', ItemTags.PLANKS)
                .input('P', Items.PAINTING)
                .input('B', ItemTags.BUTTONS)
                .input('C', ModItems.COPPER_WIRE)
                .criterion(hasItem(Items.PAINTING), conditionsFromItem(Items.PAINTING))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WOODEN_ELEVATOR_CONTROLLER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WOODEN_ELEVATOR_BASE)
                .group("elevator_base")
                .pattern("# #")
                .pattern("#E#")
                .pattern("###")
                .input('#', ItemTags.PLANKS)
                .input('E', Items.ENDER_PEARL)
                .criterion(hasItem(Items.ENDER_PEARL), conditionsFromItem(Items.ENDER_PEARL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WOODEN_ELEVATOR_BASE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_ELEVATOR_CONTROLLER)
                .group("elevator_controller")
                .pattern("###")
                .pattern("QPQ")
                .pattern("QBC")
                .input('#', ItemTags.PLANKS)
                .input('Q', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "quartz_blocks")))
                .input('P', Items.PAINTING)
                .input('B', ItemTags.BUTTONS)
                .input('C', ModItems.COPPER_WIRE)
                .criterion(hasItem(Items.SMOOTH_QUARTZ), conditionsFromItem(Items.SMOOTH_QUARTZ))
                .criterion(hasItem(Items.PAINTING), conditionsFromItem(Items.PAINTING))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.QUARTZ_ELEVATOR_CONTROLLER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUARTZ_ELEVATOR_BASE)
                .group("elevator_base")
                .pattern("Q Q")
                .pattern("QEQ")
                .pattern("###")
                .input('Q', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "quartz_blocks")))
                .input('#', ItemTags.PLANKS)
                .input('E', Items.ENDER_PEARL)
                .criterion(hasItem(Items.SMOOTH_QUARTZ), conditionsFromItem(Items.SMOOTH_QUARTZ))
                .criterion(hasItem(Items.ENDER_PEARL), conditionsFromItem(Items.ENDER_PEARL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.QUARTZ_ELEVATOR_BASE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLASS_ELEVATOR_CONTROLLER)
                .group("elevator_controller")
                .pattern("###")
                .pattern("G G")
                .pattern("GBC")
                .input('#', ItemTags.PLANKS)
                .input('G', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "colorless_glass")))
                .input('B', ItemTags.BUTTONS)
                .input('C', ModItems.COPPER_WIRE)
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GLASS_ELEVATOR_CONTROLLER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLASS_ELEVATOR_BASE)
                .group("elevator_base")
                .pattern("G G")
                .pattern("GEG")
                .pattern("###")
                .input('G', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "colorless_glass")))
                .input('#', ItemTags.PLANKS)
                .input('E', Items.ENDER_PEARL)
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .criterion(hasItem(Items.ENDER_PEARL), conditionsFromItem(Items.ENDER_PEARL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GLASS_ELEVATOR_BASE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NARSIL_HANDLE)
                .pattern("#")
                .pattern("I")
                .input('#', Items.NETHERITE_SCRAP)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(Items.NETHERITE_SCRAP), conditionsFromItem(Items.NETHERITE_SCRAP))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NARSIL_HANDLE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GONDORIAN_SHIELD)
                .input(Items.SHIELD)
                .input(Items.BLACK_DYE)
                .input(ItemTags.SAPLINGS)
                .input(ModItems.MITHRIL_NUGGET)
                .criterion(hasItem(Items.SHIELD), conditionsFromItem(Items.SHIELD))
                .criterion(hasItem(Items.BLACK_DYE), conditionsFromItem(Items.BLACK_DYE))
                .criterion(hasItem(ModItems.MITHRIL_NUGGET), conditionsFromItem(ModItems.MITHRIL_NUGGET))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GONDORIAN_SHIELD))+"_from_shield");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GONDORIAN_SHIELD)
                .input(ModItems.GONDORIAN_KINGS_GUARD_TOWER_SHIELD)
                .criterion(hasItem(ModItems.GONDORIAN_KINGS_GUARD_TOWER_SHIELD), conditionsFromItem(ModItems.GONDORIAN_KINGS_GUARD_TOWER_SHIELD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GONDORIAN_SHIELD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GONDORIAN_TOWER_SHIELD)
                .input(ModItems.GONDORIAN_SHIELD)
                .criterion(hasItem(ModItems.GONDORIAN_SHIELD), conditionsFromItem(ModItems.GONDORIAN_SHIELD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GONDORIAN_TOWER_SHIELD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GONDORIAN_KINGS_GUARD_TOWER_SHIELD)
                .input(ModItems.GONDORIAN_TOWER_SHIELD)
                .criterion(hasItem(ModItems.GONDORIAN_TOWER_SHIELD), conditionsFromItem(ModItems.GONDORIAN_TOWER_SHIELD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GONDORIAN_KINGS_GUARD_TOWER_SHIELD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModItems.GONDORIAN_KNIGHT_SHIELD)
                .pattern("GGG")
                .pattern("G#G")
                .pattern("GGG")
                .input('#', ModItems.GONDORIAN_TOWER_SHIELD)
                .input('G', Items.GOLD_NUGGET)
                .criterion(hasItem(ModItems.GONDORIAN_TOWER_SHIELD), conditionsFromItem(ModItems.GONDORIAN_TOWER_SHIELD))
                .criterion(hasItem(Items.GOLD_NUGGET), conditionsFromItem(Items.GOLD_NUGGET))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GONDORIAN_KNIGHT_SHIELD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModItems.GONDORIAN_ORNAMENTED_KNIGHT_SHIELD)
                .pattern("MGM")
                .pattern("G#G")
                .pattern("MGM")
                .input('#', ModItems.GONDORIAN_KNIGHT_SHIELD)
                .input('G', Items.GREEN_DYE)
                .input('M', ModItems.MITHRIL_NUGGET)
                .criterion(hasItem(ModItems.GONDORIAN_KNIGHT_SHIELD), conditionsFromItem(ModItems.GONDORIAN_KNIGHT_SHIELD))
                .criterion(hasItem(Items.GREEN_DYE), conditionsFromItem(Items.GREEN_DYE))
                .criterion(hasItem(ModItems.MITHRIL_NUGGET), conditionsFromItem(ModItems.MITHRIL_NUGGET))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GONDORIAN_ORNAMENTED_KNIGHT_SHIELD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ARCHAEOLOGY_TABLE)
                .pattern("II")
                .pattern("##")
                .pattern("##")
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "fossils")))
                .input('#', ItemTags.PLANKS)
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.ARCHAEOLOGY_TABLE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.RED_DYE)
                .input(ModBlocks.ROSE)
                .group("red_dye")
                .criterion(hasItem(ModBlocks.ROSE), conditionsFromItem(ModBlocks.ROSE))
                .offerTo(exporter, new Identifier("red_dye_from_rose"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.PURPLE_DYE)
                .input(ModBlocks.IRIS)
                .group("purple_dye")
                .criterion(hasItem(ModBlocks.IRIS), conditionsFromItem(ModBlocks.IRIS))
                .offerTo(exporter, new Identifier("purple_dye_from_iris"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.EDELWEISS_PETALS)
                .input(ModBlocks.EDELWEISS)
                .criterion(hasItem(ModBlocks.EDELWEISS), conditionsFromItem(ModBlocks.EDELWEISS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.EDELWEISS_PETALS)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.WHITE_DYE)
                .input(ModItems.EDELWEISS_PETALS)
                .criterion(hasItem(ModItems.EDELWEISS_PETALS), conditionsFromItem(ModItems.EDELWEISS_PETALS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.EDELWEISS_PETALS) + "_to_dye"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.WHITE_DYE)
                .input(ModBlocks.ATHELAS)
                .group("white_dye")
                .criterion(hasItem(ModBlocks.ATHELAS), conditionsFromItem(ModBlocks.ATHELAS))
                .offerTo(exporter, new Identifier("white_dye_from_athelas"));

        createStairsRecipe(ModBlocks.MARBLE_STAIRS, Ingredient.ofItems(ModBlocks.MARBLE))
                .criterion(hasItem(ModBlocks.MARBLE), conditionsFromItem(ModBlocks.MARBLE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.MARBLE_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MARBLE_SLAB, Ingredient.ofItems(ModBlocks.MARBLE))
                .criterion(hasItem(ModBlocks.MARBLE), conditionsFromItem(ModBlocks.MARBLE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.MARBLE_SLAB)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MARBLE_WALL, 6)
                .pattern("###")
                .pattern("###")
                .input('#', ModBlocks.MARBLE)
                .criterion(hasItem(ModBlocks.MARBLE), conditionsFromItem(ModBlocks.MARBLE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.MARBLE_WALL)));
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MARBLE_STAIRS, ModBlocks.MARBLE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MARBLE_SLAB, ModBlocks.MARBLE,2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MARBLE_WALL, ModBlocks.MARBLE);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MARBLE_BUTTON)
                .input(ModBlocks.MARBLE)
                .criterion(hasItem(ModBlocks.MARBLE), conditionsFromItem(ModBlocks.MARBLE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.MARBLE_BUTTON)));
        createPressurePlateRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MARBLE_PRESSURE_PLATE, Ingredient.ofItems(ModBlocks.MARBLE))
                .criterion(hasItem(ModBlocks.MARBLE), conditionsFromItem(ModBlocks.MARBLE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.MARBLE_PRESSURE_PLATE)));

        createStairsRecipe(ModBlocks.LIMESTONE_STAIRS, Ingredient.ofItems(ModBlocks.LIMESTONE))
                .criterion(hasItem(ModBlocks.LIMESTONE), conditionsFromItem(ModBlocks.LIMESTONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.LIMESTONE_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_SLAB, Ingredient.ofItems(ModBlocks.LIMESTONE))
                .criterion(hasItem(ModBlocks.LIMESTONE), conditionsFromItem(ModBlocks.LIMESTONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.LIMESTONE_SLAB)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_WALL, 6)
                .pattern("###")
                .pattern("###")
                .input('#', ModBlocks.LIMESTONE)
                .criterion(hasItem(ModBlocks.LIMESTONE), conditionsFromItem(ModBlocks.LIMESTONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.LIMESTONE_WALL)));
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_STAIRS, ModBlocks.LIMESTONE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_SLAB, ModBlocks.LIMESTONE,2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_WALL, ModBlocks.LIMESTONE);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_BUTTON)
                .input(ModBlocks.LIMESTONE)
                .criterion(hasItem(ModBlocks.LIMESTONE), conditionsFromItem(ModBlocks.LIMESTONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.LIMESTONE_BUTTON)));
        createPressurePlateRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIMESTONE_PRESSURE_PLATE, Ingredient.ofItems(ModBlocks.LIMESTONE))
                .criterion(hasItem(ModBlocks.LIMESTONE), conditionsFromItem(ModBlocks.LIMESTONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.LIMESTONE_PRESSURE_PLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_DEEPSLATE, 2)
                .pattern("#C")
                .pattern("C#")
                .input('#', Items.DEEPSLATE)
                .input('C', Items.RAW_COPPER)
                .criterion(hasItem(Items.DEEPSLATE), conditionsFromItem(Items.DEEPSLATE))
                .criterion(hasItem(Items.RAW_COPPER), conditionsFromItem(Items.RAW_COPPER))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.RED_DEEPSLATE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_RED_DEEPSLATE, 2)
                .pattern("#C")
                .pattern("C#")
                .input('#', Items.COBBLED_DEEPSLATE)
                .input('C', Items.RAW_COPPER)
                .criterion(hasItem(Items.COBBLED_DEEPSLATE), conditionsFromItem(Items.COBBLED_DEEPSLATE))
                .criterion(hasItem(Items.RAW_COPPER), conditionsFromItem(Items.RAW_COPPER))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.COBBLED_RED_DEEPSLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_SANDY_DEEPSLATE, 2)
                .pattern("#R")
                .pattern("R#")
                .input('#', ModBlocks.RED_DEEPSLATE)
                .input('R', Items.RED_SAND)
                .criterion(hasItem(ModBlocks.RED_DEEPSLATE), conditionsFromItem(ModBlocks.RED_DEEPSLATE))
                .criterion(hasItem(Items.RED_SAND), conditionsFromItem(Items.RED_SAND))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.RED_SANDY_DEEPSLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_GRAVEL, 2)
                .pattern("#C")
                .pattern("C#")
                .input('#', Items.GRAVEL)
                .input('C', Items.RAW_COPPER)
                .criterion(hasItem(Items.GRAVEL), conditionsFromItem(Items.GRAVEL))
                .criterion(hasItem(Items.RAW_COPPER), conditionsFromItem(Items.RAW_COPPER))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.RED_GRAVEL)));

        createStairsRecipe(ModBlocks.COBBLED_RED_DEEPSLATE_STAIRS, Ingredient.ofItems(ModBlocks.COBBLED_RED_DEEPSLATE))
                .criterion(hasItem(ModBlocks.COBBLED_RED_DEEPSLATE), conditionsFromItem(ModBlocks.COBBLED_RED_DEEPSLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.COBBLED_RED_DEEPSLATE_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_RED_DEEPSLATE_SLAB, Ingredient.ofItems(ModBlocks.COBBLED_RED_DEEPSLATE))
                .criterion(hasItem(ModBlocks.COBBLED_RED_DEEPSLATE), conditionsFromItem(ModBlocks.COBBLED_RED_DEEPSLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.COBBLED_RED_DEEPSLATE_SLAB)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_RED_DEEPSLATE_WALL, 6)
                .pattern("###")
                .pattern("###")
                .input('#', ModBlocks.COBBLED_RED_DEEPSLATE)
                .criterion(hasItem(ModBlocks.COBBLED_RED_DEEPSLATE), conditionsFromItem(ModBlocks.COBBLED_RED_DEEPSLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.COBBLED_RED_DEEPSLATE_WALL)));
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_RED_DEEPSLATE_STAIRS, ModBlocks.COBBLED_RED_DEEPSLATE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_RED_DEEPSLATE_SLAB, ModBlocks.COBBLED_RED_DEEPSLATE,2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_RED_DEEPSLATE_WALL, ModBlocks.COBBLED_RED_DEEPSLATE);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_DEEPSLATE_BUTTON)
                .input(ModBlocks.RED_DEEPSLATE)
                .criterion(hasItem(ModBlocks.RED_DEEPSLATE), conditionsFromItem(ModBlocks.RED_DEEPSLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.RED_DEEPSLATE_BUTTON)));
        createPressurePlateRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_DEEPSLATE_PRESSURE_PLATE, Ingredient.ofItems(ModBlocks.RED_DEEPSLATE))
                .criterion(hasItem(ModBlocks.RED_DEEPSLATE), conditionsFromItem(ModBlocks.RED_DEEPSLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.RED_DEEPSLATE_PRESSURE_PLATE)));

        offerSmelting(exporter, List.of(ModBlocks.COBBLED_RED_DEEPSLATE), RecipeCategory.MISC, ModBlocks.RED_DEEPSLATE, 0.1f, 200, "red_deepslate");

        createStairsRecipe(ModBlocks.RED_DEEPSLATE_STAIRS, Ingredient.ofItems(ModBlocks.RED_DEEPSLATE))
                .criterion(hasItem(ModBlocks.RED_DEEPSLATE), conditionsFromItem(ModBlocks.RED_DEEPSLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.RED_DEEPSLATE_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_DEEPSLATE_SLAB, Ingredient.ofItems(ModBlocks.RED_DEEPSLATE))
                .criterion(hasItem(ModBlocks.RED_DEEPSLATE), conditionsFromItem(ModBlocks.RED_DEEPSLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.RED_DEEPSLATE_SLAB)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_DEEPSLATE_WALL, 6)
                .pattern("###")
                .pattern("###")
                .input('#', ModBlocks.RED_DEEPSLATE)
                .criterion(hasItem(ModBlocks.RED_DEEPSLATE), conditionsFromItem(ModBlocks.RED_DEEPSLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.RED_DEEPSLATE_WALL)));
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_DEEPSLATE_STAIRS, ModBlocks.RED_DEEPSLATE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_DEEPSLATE_SLAB, ModBlocks.RED_DEEPSLATE,2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_DEEPSLATE_WALL, ModBlocks.RED_DEEPSLATE);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_RED_DEEPSLATE, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.RED_DEEPSLATE)
                .criterion(hasItem(ModBlocks.RED_DEEPSLATE), conditionsFromItem(ModBlocks.RED_DEEPSLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.POLISHED_RED_DEEPSLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_DEEPSLATE_BRICKS, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.POLISHED_RED_DEEPSLATE)
                .criterion(hasItem(ModBlocks.POLISHED_RED_DEEPSLATE), conditionsFromItem(ModBlocks.POLISHED_RED_DEEPSLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.RED_DEEPSLATE_BRICKS)));

        createStairsRecipe(ModBlocks.RED_DEEPSLATE_BRICK_STAIRS, Ingredient.ofItems(ModBlocks.RED_DEEPSLATE_BRICKS))
                .criterion(hasItem(ModBlocks.RED_DEEPSLATE_BRICKS), conditionsFromItem(ModBlocks.RED_DEEPSLATE_BRICKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.RED_DEEPSLATE_BRICK_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_DEEPSLATE_BRICK_SLAB, Ingredient.ofItems(ModBlocks.RED_DEEPSLATE_BRICKS))
                .criterion(hasItem(ModBlocks.RED_DEEPSLATE_BRICKS), conditionsFromItem(ModBlocks.RED_DEEPSLATE_BRICKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.RED_DEEPSLATE_BRICK_SLAB)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_DEEPSLATE_BRICK_WALL, 6)
                .pattern("###")
                .pattern("###")
                .input('#', ModBlocks.RED_DEEPSLATE_BRICKS)
                .criterion(hasItem(ModBlocks.RED_DEEPSLATE_BRICKS), conditionsFromItem(ModBlocks.RED_DEEPSLATE_BRICKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.RED_DEEPSLATE_BRICK_WALL)));
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_DEEPSLATE_BRICK_STAIRS, ModBlocks.RED_DEEPSLATE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_DEEPSLATE_BRICK_SLAB, ModBlocks.RED_DEEPSLATE_BRICKS,2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_DEEPSLATE_BRICK_WALL, ModBlocks.RED_DEEPSLATE_BRICKS);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_RED_DEEPSLATE_BRICKS)
                .input(ModBlocks.RED_DEEPSLATE_BRICKS)
                .input(Items.VINE)
                .group(ModBlocks.MOSSY_RED_DEEPSLATE_BRICKS.toString())
                .criterion(hasItem(ModBlocks.RED_DEEPSLATE_BRICKS), conditionsFromItem(ModBlocks.RED_DEEPSLATE_BRICKS))
                .criterion(hasItem(Items.VINE), conditionsFromItem(Items.VINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.MOSSY_RED_DEEPSLATE_BRICKS)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_RED_DEEPSLATE_BRICKS)
                .input(ModBlocks.RED_DEEPSLATE_BRICKS)
                .input(Items.MOSS_BLOCK)
                .group(ModBlocks.MOSSY_RED_DEEPSLATE_BRICKS.toString())
                .criterion(hasItem(ModBlocks.RED_DEEPSLATE_BRICKS), conditionsFromItem(ModBlocks.RED_DEEPSLATE_BRICKS))
                .criterion(hasItem(Items.MOSS_BLOCK), conditionsFromItem(Items.MOSS_BLOCK))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.MOSSY_RED_DEEPSLATE_BRICKS) + "2"));

        offerSmelting(exporter, List.of(ModBlocks.RED_DEEPSLATE_BRICKS), RecipeCategory.MISC, ModBlocks.CRACKED_RED_DEEPSLATE_BRICKS, 0.1f, 200, "cracked_red_deepslate_bricks");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.POLISHED_COBBLED_RED_DEEPSLATE, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.COBBLED_RED_DEEPSLATE)
                .criterion(hasItem(ModBlocks.COBBLED_RED_DEEPSLATE), conditionsFromItem(ModBlocks.COBBLED_RED_DEEPSLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.POLISHED_COBBLED_RED_DEEPSLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.POLISHED_COBBLED_RED_DEEPSLATE)
                .criterion(hasItem(ModBlocks.POLISHED_COBBLED_RED_DEEPSLATE), conditionsFromItem(ModBlocks.POLISHED_COBBLED_RED_DEEPSLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS)));

        createStairsRecipe(ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_STAIRS, Ingredient.ofItems(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS))
                .criterion(hasItem(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS), conditionsFromItem(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_SLAB, Ingredient.ofItems(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS))
                .criterion(hasItem(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS), conditionsFromItem(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_SLAB)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_WALL, 6)
                .pattern("###")
                .pattern("###")
                .input('#', ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS)
                .criterion(hasItem(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS), conditionsFromItem(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_WALL)));
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_STAIRS, ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_SLAB, ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS,2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_RED_DEEPSLATE_BRICK_WALL, ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_COBBLED_RED_DEEPSLATE_BRICKS)
                .input(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS)
                .input(Items.VINE)
                .group(ModBlocks.MOSSY_COBBLED_RED_DEEPSLATE_BRICKS.toString())
                .criterion(hasItem(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS), conditionsFromItem(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS))
                .criterion(hasItem(Items.VINE), conditionsFromItem(Items.VINE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.MOSSY_COBBLED_RED_DEEPSLATE_BRICKS)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_COBBLED_RED_DEEPSLATE_BRICKS)
                .input(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS)
                .input(Items.MOSS_BLOCK)
                .group(ModBlocks.MOSSY_COBBLED_RED_DEEPSLATE_BRICKS.toString())
                .criterion(hasItem(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS), conditionsFromItem(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS))
                .criterion(hasItem(Items.MOSS_BLOCK), conditionsFromItem(Items.MOSS_BLOCK))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.MOSSY_COBBLED_RED_DEEPSLATE_BRICKS) + "2"));

        offerSmelting(exporter, List.of(ModBlocks.COBBLED_RED_DEEPSLATE_BRICKS), RecipeCategory.MISC, ModBlocks.CRACKED_COBBLED_RED_DEEPSLATE_BRICKS, 0.0f, 200, "cracked_cobbled_red_deepslate_bricks");

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.REINFORCED_RED_DEEPSLATE)
                .input(ModBlocks.RED_DEEPSLATE)
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "fossils")))
                .criterion(hasItem(ModBlocks.RED_DEEPSLATE), conditionsFromItem(ModBlocks.RED_DEEPSLATE))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.REINFORCED_RED_DEEPSLATE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TREX_HEAD)
                .input(ModTags.Items.TREX_HEADS)
                .input(Items.BROWN_DYE)
                .criterion(hasItem(ModBlocks.TREX_HEAD), conditionsFromItem(ModBlocks.TREX_HEAD))
                .criterion(hasItem(Items.BROWN_DYE), conditionsFromItem(Items.BROWN_DYE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.TREX_HEAD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GREEN_TREX_HEAD)
                .input(ModTags.Items.TREX_HEADS)
                .input(Items.GREEN_DYE)
                .criterion(hasItem(ModBlocks.TREX_HEAD), conditionsFromItem(ModBlocks.TREX_HEAD))
                .criterion(hasItem(Items.GREEN_DYE), conditionsFromItem(Items.GREEN_DYE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GREEN_TREX_HEAD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GRAY_TREX_HEAD)
                .input(ModTags.Items.TREX_HEADS)
                .input(Items.GRAY_DYE)
                .criterion(hasItem(ModBlocks.TREX_HEAD), conditionsFromItem(ModBlocks.TREX_HEAD))
                .criterion(hasItem(Items.GRAY_DYE), conditionsFromItem(Items.GRAY_DYE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GRAY_TREX_HEAD)));
        
        createDoorRecipe(ModBlocks.GLASS_DOOR, Ingredient.ofItems(Blocks.GLASS))
                .criterion(hasItem(Blocks.GLASS), conditionsFromItem(Blocks.GLASS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GLASS_DOOR)));

        createTrapdoorRecipe(ModBlocks.GLASS_TRAPDOOR, Ingredient.ofItems(Blocks.GLASS))
                .criterion(hasItem(Blocks.GLASS), conditionsFromItem(Blocks.GLASS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GLASS_TRAPDOOR)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GINKGO_PLANKS, 4)
                .input(ModTags.Items.GINKGO_LOGS)
                .criterion(hasItem(ModBlocks.GINKGO_LOG), conditionsFromItem(ModBlocks.GINKGO_LOG))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GINKGO_PLANKS)));

        createStairsRecipe(ModBlocks.GINKGO_STAIRS, Ingredient.ofItems(ModBlocks.GINKGO_PLANKS))
                .criterion(hasItem(ModBlocks.GINKGO_PLANKS), conditionsFromItem(ModBlocks.GINKGO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GINKGO_STAIRS)));

        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GINKGO_SLAB, Ingredient.ofItems(ModBlocks.GINKGO_PLANKS))
                .criterion(hasItem(ModBlocks.GINKGO_PLANKS), conditionsFromItem(ModBlocks.GINKGO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GINKGO_SLAB)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GINKGO_BUTTON)
                .input(ModBlocks.GINKGO_PLANKS)
                .criterion(hasItem(ModBlocks.GINKGO_PLANKS), conditionsFromItem(ModBlocks.GINKGO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GINKGO_BUTTON)));

        createPressurePlateRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GINKGO_PRESSURE_PLATE, Ingredient.ofItems(ModBlocks.GINKGO_PLANKS))
                .criterion(hasItem(ModBlocks.GINKGO_PLANKS), conditionsFromItem(ModBlocks.GINKGO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GINKGO_PRESSURE_PLATE)));

        createFenceRecipe(ModBlocks.GINKGO_FENCE, Ingredient.ofItems(ModBlocks.GINKGO_PLANKS))
                .criterion(hasItem(ModBlocks.GINKGO_PLANKS), conditionsFromItem(ModBlocks.GINKGO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GINKGO_FENCE)));

        createFenceGateRecipe(ModBlocks.GINKGO_FENCE_GATE, Ingredient.ofItems(ModBlocks.GINKGO_PLANKS))
                .criterion(hasItem(ModBlocks.GINKGO_PLANKS), conditionsFromItem(ModBlocks.GINKGO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GINKGO_FENCE_GATE)));

        createDoorRecipe(ModBlocks.GINKGO_DOOR, Ingredient.ofItems(ModBlocks.GINKGO_PLANKS))
                .criterion(hasItem(ModBlocks.GINKGO_PLANKS), conditionsFromItem(ModBlocks.GINKGO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GINKGO_DOOR)));

        createTrapdoorRecipe(ModBlocks.GINKGO_TRAPDOOR, Ingredient.ofItems(ModBlocks.GINKGO_PLANKS))
                .criterion(hasItem(ModBlocks.GINKGO_PLANKS), conditionsFromItem(ModBlocks.GINKGO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GINKGO_TRAPDOOR)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GINKGO_WOOD, 3)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.GINKGO_LOG)
                .criterion(hasItem(ModBlocks.GINKGO_LOG), conditionsFromItem(ModBlocks.GINKGO_LOG))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GINKGO_WOOD)+"_from_logs"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_GINKGO_WOOD, 3)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.STRIPPED_GINKGO_LOG)
                .criterion(hasItem(ModBlocks.STRIPPED_GINKGO_LOG), conditionsFromItem(ModBlocks.STRIPPED_GINKGO_LOG))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.STRIPPED_GINKGO_WOOD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GINKGO_SIGN, 3)
                .pattern("###")
                .pattern("###")
                .pattern(" I ")
                .input('#', ModBlocks.GINKGO_PLANKS)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .group("wooden_sign")
                .criterion(hasItem(ModBlocks.GINKGO_PLANKS), conditionsFromItem(ModBlocks.GINKGO_PLANKS))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GINKGO_SIGN)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HANGING_GINKGO_SIGN, 3)
                .pattern("I I")
                .pattern("###")
                .pattern("###")
                .input('I', Items.CHAIN)
                .input('#', ModBlocks.STRIPPED_GINKGO_LOG)
                .group("hanging_sign")
                .criterion(hasItem(ModBlocks.STRIPPED_GINKGO_LOG), conditionsFromItem(ModBlocks.STRIPPED_GINKGO_LOG))
                .criterion(hasItem(Items.CHAIN), conditionsFromItem(Items.CHAIN))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HANGING_GINKGO_SIGN)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GINKGO_BOAT)
                .pattern("# #")
                .pattern("###")
                .input('#', ModBlocks.GINKGO_PLANKS)
                .group("boat")
                .criterion(hasItem(ModBlocks.GINKGO_PLANKS), conditionsFromItem(ModBlocks.GINKGO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GINKGO_BOAT)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GINKGO_CHEST_BOAT)
                .input(Items.CHEST)
                .input(ModItems.GINKGO_BOAT)
                .group("chest_boat")
                .criterion(hasItem(Items.CHEST), conditionsFromItem(Items.CHEST))
                .criterion(hasItem(ModItems.GINKGO_BOAT), conditionsFromItem(ModItems.GINKGO_BOAT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GINKGO_CHEST_BOAT)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FRAMED_GLASS, 8)
                .pattern("###")
                .pattern("#I#")
                .pattern("###")
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "colorless_glass")))
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FRAMED_GLASS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.FRAMED_GLASS_PANE, 8)
                .pattern("###")
                .pattern("#I#")
                .pattern("###")
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "colorless_glass_panes")))
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(Items.GLASS_PANE), conditionsFromItem(Items.GLASS_PANE))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FRAMED_GLASS_PANE)));

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_OAK_PLANKS, Blocks.OAK_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_SPRUCE_PLANKS, Blocks.SPRUCE_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_BIRCH_PLANKS, Blocks.BIRCH_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_JUNGLE_PLANKS, Blocks.JUNGLE_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_ACACIA_PLANKS, Blocks.ACACIA_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_DARK_OAK_PLANKS, Blocks.DARK_OAK_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_MANGROVE_PLANKS, Blocks.MANGROVE_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_CHERRY_PLANKS, Blocks.CHERRY_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_BAMBOO_PLANKS, Blocks.BAMBOO_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_CRIMSON_PLANKS, Blocks.CRIMSON_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_WARPED_PLANKS, Blocks.WARPED_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_GINKGO_PLANKS, ModBlocks.GINKGO_PLANKS);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.OAK_PLANKS, ModBlocks.FANCY_OAK_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.SPRUCE_PLANKS, ModBlocks.FANCY_SPRUCE_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.BIRCH_PLANKS, ModBlocks.FANCY_BIRCH_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.JUNGLE_PLANKS, ModBlocks.FANCY_JUNGLE_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.ACACIA_PLANKS, ModBlocks.FANCY_ACACIA_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.DARK_OAK_PLANKS, ModBlocks.FANCY_DARK_OAK_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.MANGROVE_PLANKS, ModBlocks.FANCY_MANGROVE_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.CHERRY_PLANKS, ModBlocks.FANCY_CHERRY_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.BAMBOO_PLANKS, ModBlocks.FANCY_BAMBOO_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.CRIMSON_PLANKS, ModBlocks.FANCY_CRIMSON_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_PLANKS, ModBlocks.FANCY_WARPED_PLANKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GINKGO_PLANKS, ModBlocks.FANCY_GINKGO_PLANKS);

        createStairsRecipe(ModBlocks.FANCY_OAK_STAIRS, Ingredient.ofItems(ModBlocks.FANCY_OAK_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_OAK_PLANKS), conditionsFromItem(ModBlocks.FANCY_OAK_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_OAK_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_OAK_SLAB, Ingredient.ofItems(ModBlocks.FANCY_OAK_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_OAK_PLANKS), conditionsFromItem(ModBlocks.FANCY_OAK_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_OAK_SLAB)));
        createTrapdoorRecipe(ModBlocks.FANCY_OAK_TRAPDOOR, Ingredient.ofItems(ModBlocks.FANCY_OAK_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_OAK_PLANKS), conditionsFromItem(ModBlocks.FANCY_OAK_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_OAK_TRAPDOOR)));

        createStairsRecipe(ModBlocks.FANCY_SPRUCE_STAIRS, Ingredient.ofItems(ModBlocks.FANCY_SPRUCE_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_SPRUCE_PLANKS), conditionsFromItem(ModBlocks.FANCY_SPRUCE_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_SPRUCE_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_SPRUCE_SLAB, Ingredient.ofItems(ModBlocks.FANCY_SPRUCE_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_SPRUCE_PLANKS), conditionsFromItem(ModBlocks.FANCY_SPRUCE_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_SPRUCE_SLAB)));
        createTrapdoorRecipe(ModBlocks.FANCY_SPRUCE_TRAPDOOR, Ingredient.ofItems(ModBlocks.FANCY_SPRUCE_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_SPRUCE_PLANKS), conditionsFromItem(ModBlocks.FANCY_SPRUCE_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_SPRUCE_TRAPDOOR)));

        createStairsRecipe(ModBlocks.FANCY_BIRCH_STAIRS, Ingredient.ofItems(ModBlocks.FANCY_BIRCH_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_BIRCH_PLANKS), conditionsFromItem(ModBlocks.FANCY_BIRCH_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_BIRCH_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_BIRCH_SLAB, Ingredient.ofItems(ModBlocks.FANCY_BIRCH_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_BIRCH_PLANKS), conditionsFromItem(ModBlocks.FANCY_BIRCH_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_BIRCH_SLAB)));
        createTrapdoorRecipe(ModBlocks.FANCY_BIRCH_TRAPDOOR, Ingredient.ofItems(ModBlocks.FANCY_BIRCH_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_BIRCH_PLANKS), conditionsFromItem(ModBlocks.FANCY_BIRCH_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_BIRCH_TRAPDOOR)));

        createStairsRecipe(ModBlocks.FANCY_JUNGLE_STAIRS, Ingredient.ofItems(ModBlocks.FANCY_JUNGLE_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_JUNGLE_PLANKS), conditionsFromItem(ModBlocks.FANCY_JUNGLE_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_JUNGLE_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_JUNGLE_SLAB, Ingredient.ofItems(ModBlocks.FANCY_JUNGLE_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_JUNGLE_PLANKS), conditionsFromItem(ModBlocks.FANCY_JUNGLE_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_JUNGLE_SLAB)));
        createTrapdoorRecipe(ModBlocks.FANCY_JUNGLE_TRAPDOOR, Ingredient.ofItems(ModBlocks.FANCY_JUNGLE_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_JUNGLE_PLANKS), conditionsFromItem(ModBlocks.FANCY_JUNGLE_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_JUNGLE_TRAPDOOR)));

        createStairsRecipe(ModBlocks.FANCY_ACACIA_STAIRS, Ingredient.ofItems(ModBlocks.FANCY_ACACIA_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_ACACIA_PLANKS), conditionsFromItem(ModBlocks.FANCY_ACACIA_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_ACACIA_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_ACACIA_SLAB, Ingredient.ofItems(ModBlocks.FANCY_ACACIA_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_ACACIA_PLANKS), conditionsFromItem(ModBlocks.FANCY_ACACIA_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_ACACIA_SLAB)));
        createTrapdoorRecipe(ModBlocks.FANCY_ACACIA_TRAPDOOR, Ingredient.ofItems(ModBlocks.FANCY_ACACIA_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_ACACIA_PLANKS), conditionsFromItem(ModBlocks.FANCY_ACACIA_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_ACACIA_TRAPDOOR)));

        createStairsRecipe(ModBlocks.FANCY_DARK_OAK_STAIRS, Ingredient.ofItems(ModBlocks.FANCY_DARK_OAK_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_DARK_OAK_PLANKS), conditionsFromItem(ModBlocks.FANCY_DARK_OAK_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_DARK_OAK_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_DARK_OAK_SLAB, Ingredient.ofItems(ModBlocks.FANCY_DARK_OAK_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_DARK_OAK_PLANKS), conditionsFromItem(ModBlocks.FANCY_DARK_OAK_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_DARK_OAK_SLAB)));
        createTrapdoorRecipe(ModBlocks.FANCY_DARK_OAK_TRAPDOOR, Ingredient.ofItems(ModBlocks.FANCY_DARK_OAK_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_DARK_OAK_PLANKS), conditionsFromItem(ModBlocks.FANCY_DARK_OAK_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_DARK_OAK_TRAPDOOR)));

        createStairsRecipe(ModBlocks.FANCY_MANGROVE_STAIRS, Ingredient.ofItems(ModBlocks.FANCY_MANGROVE_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_MANGROVE_PLANKS), conditionsFromItem(ModBlocks.FANCY_MANGROVE_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_MANGROVE_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_MANGROVE_SLAB, Ingredient.ofItems(ModBlocks.FANCY_MANGROVE_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_MANGROVE_PLANKS), conditionsFromItem(ModBlocks.FANCY_MANGROVE_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_MANGROVE_SLAB)));
        createTrapdoorRecipe(ModBlocks.FANCY_MANGROVE_TRAPDOOR, Ingredient.ofItems(ModBlocks.FANCY_MANGROVE_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_MANGROVE_PLANKS), conditionsFromItem(ModBlocks.FANCY_MANGROVE_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_MANGROVE_TRAPDOOR)));

        createStairsRecipe(ModBlocks.FANCY_CHERRY_STAIRS, Ingredient.ofItems(ModBlocks.FANCY_CHERRY_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_CHERRY_PLANKS), conditionsFromItem(ModBlocks.FANCY_CHERRY_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_CHERRY_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_CHERRY_SLAB, Ingredient.ofItems(ModBlocks.FANCY_CHERRY_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_CHERRY_PLANKS), conditionsFromItem(ModBlocks.FANCY_CHERRY_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_CHERRY_SLAB)));
        createTrapdoorRecipe(ModBlocks.FANCY_CHERRY_TRAPDOOR, Ingredient.ofItems(ModBlocks.FANCY_CHERRY_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_CHERRY_PLANKS), conditionsFromItem(ModBlocks.FANCY_CHERRY_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_CHERRY_TRAPDOOR)));

        createStairsRecipe(ModBlocks.FANCY_PALE_OAK_STAIRS, Ingredient.ofItems(ModBlocks.FANCY_PALE_OAK_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_PALE_OAK_PLANKS), conditionsFromItem(ModBlocks.FANCY_PALE_OAK_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_PALE_OAK_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_PALE_OAK_SLAB, Ingredient.ofItems(ModBlocks.FANCY_PALE_OAK_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_PALE_OAK_PLANKS), conditionsFromItem(ModBlocks.FANCY_PALE_OAK_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_PALE_OAK_SLAB)));
        createTrapdoorRecipe(ModBlocks.FANCY_PALE_OAK_TRAPDOOR, Ingredient.ofItems(ModBlocks.FANCY_PALE_OAK_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_PALE_OAK_PLANKS), conditionsFromItem(ModBlocks.FANCY_PALE_OAK_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_PALE_OAK_TRAPDOOR)));

        createStairsRecipe(ModBlocks.FANCY_BAMBOO_STAIRS, Ingredient.ofItems(ModBlocks.FANCY_BAMBOO_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_BAMBOO_PLANKS), conditionsFromItem(ModBlocks.FANCY_BAMBOO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_BAMBOO_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_BAMBOO_SLAB, Ingredient.ofItems(ModBlocks.FANCY_BAMBOO_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_BAMBOO_PLANKS), conditionsFromItem(ModBlocks.FANCY_BAMBOO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_BAMBOO_SLAB)));
        createTrapdoorRecipe(ModBlocks.FANCY_BAMBOO_TRAPDOOR, Ingredient.ofItems(ModBlocks.FANCY_BAMBOO_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_BAMBOO_PLANKS), conditionsFromItem(ModBlocks.FANCY_BAMBOO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_BAMBOO_TRAPDOOR)));

        createStairsRecipe(ModBlocks.FANCY_CRIMSON_STAIRS, Ingredient.ofItems(ModBlocks.FANCY_CRIMSON_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_CRIMSON_PLANKS), conditionsFromItem(ModBlocks.FANCY_CRIMSON_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_CRIMSON_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_CRIMSON_SLAB, Ingredient.ofItems(ModBlocks.FANCY_CRIMSON_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_CRIMSON_PLANKS), conditionsFromItem(ModBlocks.FANCY_CRIMSON_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_CRIMSON_SLAB)));
        createTrapdoorRecipe(ModBlocks.FANCY_CRIMSON_TRAPDOOR, Ingredient.ofItems(ModBlocks.FANCY_CRIMSON_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_CRIMSON_PLANKS), conditionsFromItem(ModBlocks.FANCY_CRIMSON_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_CRIMSON_TRAPDOOR)));

        createStairsRecipe(ModBlocks.FANCY_WARPED_STAIRS, Ingredient.ofItems(ModBlocks.FANCY_WARPED_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_WARPED_PLANKS), conditionsFromItem(ModBlocks.FANCY_WARPED_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_WARPED_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_WARPED_SLAB, Ingredient.ofItems(ModBlocks.FANCY_WARPED_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_WARPED_PLANKS), conditionsFromItem(ModBlocks.FANCY_WARPED_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_WARPED_SLAB)));
        createTrapdoorRecipe(ModBlocks.FANCY_WARPED_TRAPDOOR, Ingredient.ofItems(ModBlocks.FANCY_WARPED_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_WARPED_PLANKS), conditionsFromItem(ModBlocks.FANCY_WARPED_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_WARPED_TRAPDOOR)));

        createStairsRecipe(ModBlocks.FANCY_GINKGO_STAIRS, Ingredient.ofItems(ModBlocks.FANCY_GINKGO_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_GINKGO_PLANKS), conditionsFromItem(ModBlocks.FANCY_GINKGO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_GINKGO_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_GINKGO_SLAB, Ingredient.ofItems(ModBlocks.FANCY_GINKGO_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_GINKGO_PLANKS), conditionsFromItem(ModBlocks.FANCY_GINKGO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_GINKGO_SLAB)));
        createTrapdoorRecipe(ModBlocks.FANCY_GINKGO_TRAPDOOR, Ingredient.ofItems(ModBlocks.FANCY_GINKGO_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_GINKGO_PLANKS), conditionsFromItem(ModBlocks.FANCY_GINKGO_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_GINKGO_TRAPDOOR)));

        createStairsRecipe(ModBlocks.FANCY_CHARRED_STAIRS, Ingredient.ofItems(ModBlocks.FANCY_CHARRED_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_CHARRED_PLANKS), conditionsFromItem(ModBlocks.FANCY_CHARRED_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_CHARRED_STAIRS)));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FANCY_CHARRED_SLAB, Ingredient.ofItems(ModBlocks.FANCY_CHARRED_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_CHARRED_PLANKS), conditionsFromItem(ModBlocks.FANCY_CHARRED_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_CHARRED_SLAB)));
        createTrapdoorRecipe(ModBlocks.FANCY_CHARRED_TRAPDOOR, Ingredient.ofItems(ModBlocks.FANCY_CHARRED_PLANKS))
                .criterion(hasItem(ModBlocks.FANCY_CHARRED_PLANKS), conditionsFromItem(ModBlocks.FANCY_CHARRED_PLANKS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.FANCY_CHARRED_TRAPDOOR)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MEGAREGOLITH, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.LUNAR_REGOLITH)
                .criterion(hasItem(ModBlocks.LUNAR_REGOLITH), conditionsFromItem(ModBlocks.LUNAR_REGOLITH))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.MEGAREGOLITH)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModFluids.CHEMICAL_WASTE_BUCKET)
                .input(Items.LAVA_BUCKET)
                .input(ModItems.URANIUM)
                .input(ModItems.ENDURIUM_CRYSTAL)
                .input(Items.BUCKET)
                .group(ModFluids.CHEMICAL_WASTE_BUCKET.toString())
                .criterion(hasItem(Items.LAVA_BUCKET), conditionsFromItem(Items.LAVA_BUCKET))
                .criterion(hasItem(ModItems.URANIUM), conditionsFromItem(ModItems.URANIUM))
                .criterion(hasItem(ModItems.ENDURIUM_CRYSTAL), conditionsFromItem(ModItems.ENDURIUM_CRYSTAL))
                .criterion(hasItem(Items.BUCKET), conditionsFromItem(Items.BUCKET))
                .offerTo(exporter, new Identifier(getRecipeName(ModFluids.CHEMICAL_WASTE_BUCKET)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModFluids.BEER_BUCKET)
                .input(Items.WATER_BUCKET)
                .input(Items.WHEAT)
                .input(Items.BUCKET)
                .group(ModFluids.BEER_BUCKET.toString())
                .criterion(hasItem(Items.WATER_BUCKET), conditionsFromItem(Items.WATER_BUCKET))
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .criterion(hasItem(Items.BUCKET), conditionsFromItem(Items.BUCKET))
                .offerTo(exporter, new Identifier(getRecipeName(ModFluids.BEER_BUCKET)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModFluids.BEER_BUCKET)
                .input(Items.BUCKET)
                .input(ModTags.Items.BEER_CONTAINER)
                .group(ModFluids.BEER_BUCKET.toString())
                .criterion(hasItem(Items.BUCKET), conditionsFromItem(Items.BUCKET))
                .offerTo(exporter, new Identifier(getRecipeName(ModFluids.BEER_BUCKET)+"2"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModFluids.BEER_BUCKET)
                .input(Items.BUCKET)
                .input(ModItems.BEER_BOTTLE)
                .input(ModItems.BEER_BOTTLE)
                .input(ModItems.BEER_BOTTLE)
                .group(ModFluids.BEER_BUCKET.toString())
                .criterion(hasItem(Items.BUCKET), conditionsFromItem(Items.BUCKET))
                .criterion(hasItem(ModItems.BEER_BOTTLE), conditionsFromItem(ModItems.BEER_BOTTLE))
                .offerTo(exporter, new Identifier(getRecipeName(ModFluids.BEER_BUCKET)+"_from_bottles"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModFluids.BEER_BUCKET)
                .input(Items.POTION)
                .input(Items.POTION)
                .input(Items.POTION)
                .input(Items.WHEAT)
                .input(Items.BUCKET)
                .group(ModFluids.BEER_BUCKET.toString())
                .criterion(hasItem(Items.BUCKET), conditionsFromItem(Items.BUCKET))
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .criterion(hasItem(Items.POTION), conditionsFromItem(Items.POTION))
                .offerTo(exporter, new Identifier(getRecipeName(ModFluids.BEER_BUCKET)+"3"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.WOODEN_STEIN,4)
                .pattern("# #")
                .pattern("I I")
                .pattern("###")
                .input('#', ItemTags.LOGS)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "iron_ingots")))
                .group(ModBlocks.WOODEN_STEIN.toString())
                .criterion(hasItem(Items.OAK_LOG), conditionsFromItem(Items.OAK_LOG))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WOODEN_STEIN)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.WOODEN_WATER_STEIN)
                .input(ModBlocks.WOODEN_STEIN)
                .input(Items.WATER_BUCKET)
                .group(ModBlocks.WOODEN_STEIN.toString())
                .criterion(hasItem(ModBlocks.WOODEN_STEIN), conditionsFromItem(ModBlocks.WOODEN_STEIN))
                .criterion(hasItem(Items.WATER_BUCKET), conditionsFromItem(Items.WATER_BUCKET))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WOODEN_WATER_STEIN)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.WOODEN_APPLE_JUICE_STEIN)
                .input(ModBlocks.WOODEN_STEIN)
                .input(Items.APPLE)
                .input(Items.APPLE)
                .input(Items.APPLE)
                .group(ModBlocks.WOODEN_STEIN.toString())
                .criterion(hasItem(ModBlocks.WOODEN_STEIN), conditionsFromItem(ModBlocks.WOODEN_STEIN))
                .criterion(hasItem(Items.APPLE), conditionsFromItem(Items.APPLE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WOODEN_APPLE_JUICE_STEIN)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.WOODEN_ORANGE_JUICE_STEIN)
                .input(ModBlocks.WOODEN_STEIN)
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "oranges")))
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "oranges")))
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "oranges")))
                .group(ModBlocks.WOODEN_STEIN.toString())
                .criterion(hasItem(ModBlocks.WOODEN_STEIN), conditionsFromItem(ModBlocks.WOODEN_STEIN))
                .criterion(hasItem(ModItems.ORANGE), conditionsFromItem(ModItems.ORANGE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WOODEN_ORANGE_JUICE_STEIN)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.WOODEN_BEER_STEIN)
                .input(ModBlocks.WOODEN_STEIN)
                .input(ModTags.Items.BEER_CONTAINER)
                .group(ModBlocks.WOODEN_STEIN.toString())
                .criterion(hasItem(ModBlocks.WOODEN_STEIN), conditionsFromItem(ModBlocks.WOODEN_STEIN))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WOODEN_BEER_STEIN)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.WOODEN_BEER_STEIN)
                .input(ModBlocks.WOODEN_STEIN)
                .input(ModItems.BEER_BOTTLE)
                .input(ModItems.BEER_BOTTLE)
                .input(ModItems.BEER_BOTTLE)
                .group(ModBlocks.WOODEN_STEIN.toString())
                .criterion(hasItem(ModBlocks.WOODEN_STEIN), conditionsFromItem(ModBlocks.WOODEN_STEIN))
                .criterion(hasItem(ModItems.BEER_BOTTLE), conditionsFromItem(ModItems.BEER_BOTTLE))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WOODEN_BEER_STEIN)+"_from_bottles"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.WOODEN_BEER_STEIN)
                .input(ModBlocks.WOODEN_WATER_STEIN)
                .input(Items.WHEAT)
                .group(ModBlocks.WOODEN_STEIN.toString())
                .criterion(hasItem(ModBlocks.WOODEN_WATER_STEIN), conditionsFromItem(ModBlocks.WOODEN_WATER_STEIN))
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WOODEN_BEER_STEIN)+"2"));


        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.WOODEN_MILK_STEIN)
                .input(ModBlocks.WOODEN_STEIN)
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "milk")))
                .group(ModBlocks.WOODEN_STEIN.toString())
                .criterion(hasItem(ModBlocks.WOODEN_STEIN), conditionsFromItem(ModBlocks.WOODEN_STEIN))
                .criterion(hasItem(Items.MILK_BUCKET), conditionsFromItem(Items.MILK_BUCKET))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WOODEN_MILK_STEIN)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.HOT_WOODEN_MILK_STEIN)
                .input(ModBlocks.WOODEN_STEIN)
                .input(ModFluids.HOT_MILK_BUCKET)
                .group(ModBlocks.WOODEN_STEIN.toString())
                .criterion(hasItem(ModBlocks.WOODEN_STEIN), conditionsFromItem(ModBlocks.WOODEN_STEIN))
                .criterion(hasItem(ModFluids.HOT_MILK_BUCKET), conditionsFromItem(ModFluids.HOT_MILK_BUCKET))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.HOT_WOODEN_MILK_STEIN)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN)
                .input(ModBlocks.WOODEN_STEIN)
                .input(ModFluids.HOT_CHOCOLATE_BUCKET)
                .group(ModBlocks.WOODEN_STEIN.toString())
                .criterion(hasItem(ModBlocks.WOODEN_STEIN), conditionsFromItem(ModBlocks.WOODEN_STEIN))
                .criterion(hasItem(ModFluids.HOT_CHOCOLATE_BUCKET), conditionsFromItem(ModFluids.HOT_CHOCOLATE_BUCKET))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.WOODEN_STRAWBERRY_MILKSHAKE_STEIN)
                .input(ModBlocks.WOODEN_MILK_STEIN)
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "strawberries")))
                .input(Items.SNOWBALL)
                .group(ModBlocks.WOODEN_STEIN.toString())
                .criterion(hasItem(ModBlocks.WOODEN_MILK_STEIN), conditionsFromItem(ModBlocks.WOODEN_MILK_STEIN))
                .criterion(hasItem(ModItems.STRAWBERRY), conditionsFromItem(ModItems.STRAWBERRY))
                .criterion(hasItem(Items.SNOWBALL), conditionsFromItem(Items.SNOWBALL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WOODEN_STRAWBERRY_MILKSHAKE_STEIN)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.WOODEN_ORANGE_MILKSHAKE_STEIN)
                .input(ModBlocks.WOODEN_MILK_STEIN)
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "oranges")))
                .input(Items.SNOWBALL)
                .group(ModBlocks.WOODEN_STEIN.toString())
                .criterion(hasItem(ModBlocks.WOODEN_MILK_STEIN), conditionsFromItem(ModBlocks.WOODEN_MILK_STEIN))
                .criterion(hasItem(ModItems.ORANGE), conditionsFromItem(ModItems.ORANGE))
                .criterion(hasItem(Items.SNOWBALL), conditionsFromItem(Items.SNOWBALL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WOODEN_ORANGE_MILKSHAKE_STEIN)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.WOODEN_CHOCOLATE_MILKSHAKE_STEIN)
                .input(ModBlocks.WOODEN_MILK_STEIN)
                .input(Items.COCOA_BEANS)
                .input(Items.SNOWBALL)
                .group(ModBlocks.WOODEN_STEIN.toString())
                .criterion(hasItem(ModBlocks.WOODEN_MILK_STEIN), conditionsFromItem(ModBlocks.WOODEN_MILK_STEIN))
                .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS))
                .criterion(hasItem(Items.SNOWBALL), conditionsFromItem(Items.SNOWBALL))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WOODEN_CHOCOLATE_MILKSHAKE_STEIN)));

        offerFoodCookingRecipe(exporter, "campfire", RecipeSerializer.CAMPFIRE_COOKING, 600, ModBlocks.WOODEN_MILK_STEIN, ModBlocks.HOT_WOODEN_MILK_STEIN, 0.5f);
        offerFoodCookingRecipe(exporter, "smoker", RecipeSerializer.SMOKING, 100, ModBlocks.WOODEN_MILK_STEIN, ModBlocks.HOT_WOODEN_MILK_STEIN, 0.5f);
        offerFoodCookingRecipe(exporter, "furnace", RecipeSerializer.SMELTING, 200, ModBlocks.WOODEN_MILK_STEIN, ModBlocks.HOT_WOODEN_MILK_STEIN, 0.5f);

        offerFoodCookingRecipe(exporter, "campfire", RecipeSerializer.CAMPFIRE_COOKING, 600, Items.MILK_BUCKET, ModFluids.HOT_MILK_BUCKET, 0.5f);
        offerFoodCookingRecipe(exporter, "smoker", RecipeSerializer.SMOKING, 100, Items.MILK_BUCKET, ModFluids.HOT_MILK_BUCKET, 0.5f);
        offerFoodCookingRecipe(exporter, "furnace", RecipeSerializer.SMELTING, 200, Items.MILK_BUCKET, ModFluids.HOT_MILK_BUCKET, 0.5f);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN)
                .input(ModTags.Items.HOT_MILK_CONTAINER)
                .input(Items.COCOA_BEANS)
                .input(ModBlocks.WOODEN_STEIN)
                .group(ModBlocks.WOODEN_STEIN.toString())
                .criterion(hasItem(ModBlocks.HOT_WOODEN_MILK_STEIN), conditionsFromItem(ModBlocks.HOT_WOODEN_MILK_STEIN))
                .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN)+"_2"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModFluids.HOT_CHOCOLATE_BUCKET)
                .input(ModTags.Items.HOT_MILK_CONTAINER)
                .input(Items.COCOA_BEANS)
                .input(Items.BUCKET)
                .group(ModFluids.HOT_CHOCOLATE_BUCKET.toString())
                .criterion(hasItem(ModBlocks.HOT_WOODEN_MILK_STEIN), conditionsFromItem(ModBlocks.HOT_WOODEN_MILK_STEIN))
                .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS))
                .criterion(hasItem(Items.BUCKET), conditionsFromItem(Items.BUCKET))
                .offerTo(exporter, new Identifier(getRecipeName(ModFluids.HOT_CHOCOLATE_BUCKET)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModFluids.HOT_CHOCOLATE_BUCKET)
                .input(ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN)
                .input(Items.BUCKET)
                .group(ModFluids.HOT_CHOCOLATE_BUCKET.toString())
                .criterion(hasItem(ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN), conditionsFromItem(ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN))
                .criterion(hasItem(Items.BUCKET), conditionsFromItem(Items.BUCKET))
                .offerTo(exporter, new Identifier(getRecipeName(ModFluids.HOT_CHOCOLATE_BUCKET)+"_2"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BEER_GOAT_HORN)
                .input(Items.GOAT_HORN)
                .input(ModTags.Items.BEER_CONTAINER)
                .group(ModItems.BEER_GOAT_HORN.toString())
                .criterion(hasItem(Items.GOAT_HORN), conditionsFromItem(Items.GOAT_HORN))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BEER_GOAT_HORN)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BEER_GOAT_HORN)
                .input(Items.GOAT_HORN)
                .input(ModItems.BEER_BOTTLE)
                .input(ModItems.BEER_BOTTLE)
                .input(ModItems.BEER_BOTTLE)
                .group(ModItems.BEER_GOAT_HORN.toString())
                .criterion(hasItem(Items.GOAT_HORN), conditionsFromItem(Items.GOAT_HORN))
                .criterion(hasItem(ModItems.BEER_BOTTLE), conditionsFromItem(ModItems.BEER_BOTTLE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BEER_GOAT_HORN)+"_from_bottles"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BEER_BOTTLE,3)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .input(ModTags.Items.BEER_CONTAINER)
                .criterion(hasItem(Items.GLASS_BOTTLE), conditionsFromItem(Items.GLASS_BOTTLE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BEER_BOTTLE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.APPLE_JUICE_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .input(Items.APPLE)
                .criterion(hasItem(Items.GLASS_BOTTLE), conditionsFromItem(Items.GLASS_BOTTLE))
                .criterion(hasItem(Items.APPLE), conditionsFromItem(Items.APPLE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.APPLE_JUICE_BOTTLE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ORANGE_JUICE_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "oranges")))
                .criterion(hasItem(Items.GLASS_BOTTLE), conditionsFromItem(Items.GLASS_BOTTLE))
                .criterion(hasItem(ModItems.ORANGE), conditionsFromItem(ModItems.ORANGE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ORANGE_JUICE_BOTTLE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SPEZI_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "oranges")))
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "sugar")))
                .criterion(hasItem(Items.GLASS_BOTTLE), conditionsFromItem(Items.GLASS_BOTTLE))
                .criterion(hasItem(ModItems.ORANGE), conditionsFromItem(ModItems.ORANGE))
                .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SPEZI_BOTTLE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.GLOBUS_CRUCIGER)
                .input(Items.ENCHANTED_GOLDEN_APPLE)
                .input(ModBlocks.STICK_CROSS)
                .criterion(hasItem(Items.ENCHANTED_GOLDEN_APPLE), conditionsFromItem(Items.ENCHANTED_GOLDEN_APPLE))
                .criterion(hasItem(ModBlocks.STICK_CROSS), conditionsFromItem(ModBlocks.STICK_CROSS))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.GLOBUS_CRUCIGER)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.EMPERORS_CROWN)
                .input(Items.GOLDEN_HELMET)
                .input(ModBlocks.GLOBUS_CRUCIGER)
                .criterion(hasItem(Items.GOLDEN_HELMET), conditionsFromItem(Items.GOLDEN_HELMET))
                .criterion(hasItem(ModBlocks.GLOBUS_CRUCIGER), conditionsFromItem(ModBlocks.GLOBUS_CRUCIGER))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.EMPERORS_CROWN)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.KEYCARD)
                .input(ModItems.KEYCARD)
                .criterion(hasItem(ModItems.KEYCARD), conditionsFromItem(ModItems.KEYCARD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.KEYCARD) + "_cleared"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.FLINT_AND_STEEL)
                .input(ModTags.Items.IRON_OR_STEEL)
                .input(Items.FLINT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.FLINT), conditionsFromItem(Items.FLINT))
                .offerTo(exporter, new Identifier(getRecipeName(Items.FLINT_AND_STEEL)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FOSSIL_AND_STEEL)
                .input(ModTags.Items.IRON_OR_STEEL)
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "fossils")))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.FOSSIL_AND_STEEL)));

        offerReversibleNuggetCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.COPPER_NUGGET, RecipeCategory.MISC, Items.COPPER_INGOT);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.RAW_LEAD, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_LEAD_BLOCK);
        offerSmelting(exporter, List.of(ModItems.RAW_LEAD), RecipeCategory.MISC, ModItems.LEAD_INGOT, 0.25f,200, "lead_ingot");
        offerReversibleNuggetCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.LEAD_NUGGET, RecipeCategory.MISC, ModItems.LEAD_INGOT);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.LEAD_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LEAD_BLOCK);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.URANIUM, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_URANIUM_BLOCK);
        offerSmelting(exporter, URANIUM_BLASTABLES, RecipeCategory.MISC, ModItems.URANIUM_INGOT, 1f,200, "uranium");
        offerReversibleNuggetCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.URANIUM_NUGGET, RecipeCategory.MISC, ModItems.URANIUM_INGOT);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.URANIUM_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.URANIUM_BLOCK);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STEEL_INGREDIENTS)
                .input(Items.IRON_INGOT)
                .input(Items.CHARCOAL)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.CHARCOAL), conditionsFromItem(Items.CHARCOAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.STEEL_INGREDIENTS)));

        offerBlasting(exporter, List.of(ModItems.STEEL_INGREDIENTS), RecipeCategory.MISC, ModItems.STEEL_INGOT, 0.7f, 400, "steel_ingot");

        offerReversibleNuggetCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.STEEL_NUGGET, RecipeCategory.MISC, ModItems.STEEL_INGOT);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.STEEL_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.STEEL_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STEEL_ROD, 4)
                .pattern("#")
                .pattern("#")
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_ingots")))
                .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.STEEL_ROD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STEEL_SICKLE)
                .pattern(" #")
                .pattern("I ")
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_ingots")))
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.STEEL_SICKLE)));

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.SULFUR, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SULFUR_BLOCK);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MONOCLE)
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "colorless_glass_panes")))
                .input(Items.GOLD_NUGGET)
                .criterion(hasItem(Items.GLASS_PANE), conditionsFromItem(Items.GLASS_PANE))
                .criterion(hasItem(Items.GOLD_NUGGET), conditionsFromItem(Items.GOLD_NUGGET))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MONOCLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.VICTORIAN_SUIT)
                .pattern("#B#")
                .pattern("###")
                .pattern("###")
                .input('#', Items.LEATHER)
                .input('B', Items.BLACK_DYE)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(Items.BLACK_DYE), conditionsFromItem(Items.BLACK_DYE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.VICTORIAN_SUIT)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.VICTORIAN_PANTS)
                .pattern("###")
                .pattern("#L#")
                .pattern("# #")
                .input('#', Items.LEATHER)
                .input('L', Items.LIGHT_GRAY_DYE)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(Items.LIGHT_GRAY_DYE), conditionsFromItem(Items.LIGHT_GRAY_DYE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.VICTORIAN_PANTS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.VICTORIAN_BOOTS)
                .pattern("# #")
                .pattern("#G#")
                .input('#', Items.LEATHER)
                .input('G', Items.GRAY_DYE)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(Items.GRAY_DYE), conditionsFromItem(Items.GRAY_DYE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.VICTORIAN_BOOTS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PICKELHAUBE)
                .pattern(" I ")
                .pattern("###")
                .pattern("# #")
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "gold_ingots")))
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_ingots")))
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PICKELHAUBE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.IMPERIAL_GERMAN_UNIFORM_MANTLE)
                .pattern("#B#")
                .pattern("###")
                .pattern("###")
                .input('#', Items.LEATHER)
                .input('B', Items.BLUE_DYE)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(Items.BLUE_DYE), conditionsFromItem(Items.BLUE_DYE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.IMPERIAL_GERMAN_UNIFORM_MANTLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.IMPERIAL_GERMAN_UNIFORM_PANTS)
                .pattern("###")
                .pattern("#G#")
                .pattern("# #")
                .input('#', Items.LEATHER)
                .input('G', Items.GRAY_DYE)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(Items.GRAY_DYE), conditionsFromItem(Items.GRAY_DYE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.IMPERIAL_GERMAN_UNIFORM_PANTS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.IMPERIAL_GERMAN_UNIFORM_BOOTS)
                .pattern("# #")
                .pattern("#B#")
                .input('#', Items.LEATHER)
                .input('B', Items.BLACK_DYE)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(Items.BLACK_DYE), conditionsFromItem(Items.BLACK_DYE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.IMPERIAL_GERMAN_UNIFORM_BOOTS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SCIENTIST_GLASSES)
                .pattern("I I")
                .pattern("#I#")
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "iron_nuggets")))
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "colorless_glass_panes")))
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .criterion(hasItem(Items.GLASS_PANE), conditionsFromItem(Items.GLASS_PANE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SCIENTIST_GLASSES)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CTHONAUT_HELMET)
                .pattern("###")
                .pattern("# #")
                .pattern("SSS")
                .input('#', Items.TINTED_GLASS)
                .input('S', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_ingots")))
                .criterion(hasItem(Items.TINTED_GLASS), conditionsFromItem(Items.TINTED_GLASS))
                .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CTHONAUT_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DEPTH_SUIT_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_ingots")))
                .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.DEPTH_SUIT_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DEPTH_SUIT_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_ingots")))
                .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.DEPTH_SUIT_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DEPTH_SUIT_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_ingots")))
                .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.DEPTH_SUIT_BOOTS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OXYGEN_TANK)
                .pattern(" I ")
                .pattern("IGI")
                .pattern(" I ")
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "iron_ingots")))
                .input('G', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "glass_panes")))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.GLASS_PANE), conditionsFromItem(Items.GLASS_PANE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.OXYGEN_TANK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ASTRONAUT_HELMET)
                .pattern("###")
                .pattern("# #")
                .pattern("LLL")
                .input('#', Items.TINTED_GLASS)
                .input('L', Items.LEATHER)
                .criterion(hasItem(Items.TINTED_GLASS), conditionsFromItem(Items.TINTED_GLASS))
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ASTRONAUT_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SPACE_SUIT_CHESTPLATE)
                .pattern("#W#")
                .pattern("O#O")
                .pattern("###")
                .input('#', Items.LEATHER)
                .input('W', Items.WHITE_DYE)
                .input('O', ModItems.OXYGEN_TANK)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(Items.WHITE_DYE), conditionsFromItem(Items.WHITE_DYE))
                .criterion(hasItem(ModItems.OXYGEN_TANK), conditionsFromItem(ModItems.OXYGEN_TANK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SPACE_SUIT_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SPACE_SUIT_LEGGINGS)
                .pattern("###")
                .pattern("#W#")
                .pattern("# #")
                .input('#', Items.LEATHER)
                .input('W', Items.WHITE_DYE)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(Items.WHITE_DYE), conditionsFromItem(Items.WHITE_DYE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SPACE_SUIT_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SPACE_SUIT_BOOTS)
                .pattern("# #")
                .pattern("#L#")
                .input('#', Items.LEATHER)
                .input('L', Items.LIGHT_GRAY_DYE)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(Items.LIGHT_GRAY_DYE), conditionsFromItem(Items.LIGHT_GRAY_DYE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SPACE_SUIT_BOOTS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HEV_SUIT_CHESTPLATE)
                .pattern("# #")
                .pattern("#X#")
                .pattern("X#X")
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "lead_ingots")))
                .input('X', ModItems.XEN_CRYSTAL)
                .criterion(hasItem(ModItems.LEAD_INGOT), conditionsFromItem(ModItems.LEAD_INGOT))
                .criterion(hasItem(ModItems.XEN_CRYSTAL), conditionsFromItem(ModItems.XEN_CRYSTAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HEV_SUIT_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HEV_SUIT_LEGGINGS)
                .pattern("###")
                .pattern("X X")
                .pattern("# #")
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "lead_ingots")))
                .input('X', ModItems.XEN_CRYSTAL)
                .criterion(hasItem(ModItems.LEAD_INGOT), conditionsFromItem(ModItems.LEAD_INGOT))
                .criterion(hasItem(ModItems.XEN_CRYSTAL), conditionsFromItem(ModItems.XEN_CRYSTAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HEV_SUIT_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HEV_SUIT_BOOTS)
                .pattern("X X")
                .pattern("# #")
                .input('#', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "lead_ingots")))
                .input('X', ModItems.XEN_CRYSTAL)
                .criterion(hasItem(ModItems.LEAD_INGOT), conditionsFromItem(ModItems.LEAD_INGOT))
                .criterion(hasItem(ModItems.XEN_CRYSTAL), conditionsFromItem(ModItems.XEN_CRYSTAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HEV_SUIT_BOOTS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BAMBOO_SWORD)
                .pattern("#")
                .pattern("#")
                .pattern("I")
                .input('#', Items.BAMBOO)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .criterion(hasItem(Items.BAMBOO), conditionsFromItem(Items.BAMBOO))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BAMBOO_SWORD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BAMBOO_PICKAXE)
                .pattern("###")
                .pattern(" I ")
                .pattern(" I ")
                .input('#', Items.BAMBOO)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .criterion(hasItem(Items.BAMBOO), conditionsFromItem(Items.BAMBOO))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BAMBOO_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BAMBOO_SHOVEL)
                .pattern("#")
                .pattern("I")
                .pattern("I")
                .input('#', Items.BAMBOO)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .criterion(hasItem(Items.BAMBOO), conditionsFromItem(Items.BAMBOO))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BAMBOO_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BAMBOO_AXE)
                .pattern("##")
                .pattern("#I")
                .pattern(" I")
                .input('#', Items.BAMBOO)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .criterion(hasItem(Items.BAMBOO), conditionsFromItem(Items.BAMBOO))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .group(ModItems.BAMBOO_AXE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BAMBOO_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BAMBOO_AXE)
                .pattern("##")
                .pattern("I#")
                .pattern("I ")
                .input('#', Items.BAMBOO)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .criterion(hasItem(Items.BAMBOO), conditionsFromItem(Items.BAMBOO))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .group(ModItems.BAMBOO_AXE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BAMBOO_AXE) + "2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BAMBOO_HOE)
                .pattern("##")
                .pattern(" I")
                .pattern(" I")
                .input('#', Items.BAMBOO)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .criterion(hasItem(Items.BAMBOO), conditionsFromItem(Items.BAMBOO))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .group(ModItems.BAMBOO_HOE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BAMBOO_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BAMBOO_HOE)
                .pattern("##")
                .pattern("I ")
                .pattern("I ")
                .input('#', Items.BAMBOO)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "wooden_rods")))
                .criterion(hasItem(Items.BAMBOO), conditionsFromItem(Items.BAMBOO))
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .group(ModItems.BAMBOO_HOE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BAMBOO_HOE) + "2"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELIORITE_INGOT)
                .input(ModItems.HELIORITE_COMB)
                .input(Items.OBSIDIAN)
                .criterion(hasItem(ModItems.HELIORITE_COMB), conditionsFromItem(ModItems.HELIORITE_COMB))
                .criterion(hasItem(Items.OBSIDIAN), conditionsFromItem(Items.OBSIDIAN))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELIORITE_INGOT) + "s"));

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.HELIORITE_COMB, RecipeCategory.BUILDING_BLOCKS, ModBlocks.HELIORITE_COMB_BLOCK);
        offerReversibleNuggetCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.HELIORITE_NUGGET, RecipeCategory.MISC, ModItems.HELIORITE_INGOT);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.HELIORITE_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.HELIORITE_BLOCK);

        offerBlasting(exporter, List.of(ModItems.HELIORITE_SWORD, ModItems.HELIORITE_PICKAXE, ModItems.HELIORITE_AXE, ModItems.HELIORITE_SHOVEL, ModItems.HELIORITE_HOE, ModItems.HELIORITE_SICKLE, ModItems.HELIORITE_FORGING_HAMMER, ModItems.HELIORITE_HELMET, ModItems.HELIORITE_CHESTPLATE, ModItems.HELIORITE_LEGGINGS, ModItems.HELIORITE_BOOTS), RecipeCategory.MISC, ModItems.HELIORITE_NUGGET, 0.7f, 400, "heliorite_nugget");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELIORITE_UPGRADE_SMITHING_TEMPLATE)
                .pattern("#S#")
                .pattern("#R#")
                .pattern("###")
                .input('#', ModItems.HELIORITE_INGOT)
                .input('S', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_ingots")))
                .input('R', ModBlocks.COBBLED_RED_DEEPSLATE)
                .criterion(hasItem(ModItems.HELIORITE_INGOT), conditionsFromItem(ModItems.HELIORITE_INGOT))
                .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                .criterion(hasItem(ModBlocks.COBBLED_RED_DEEPSLATE), conditionsFromItem(ModBlocks.COBBLED_RED_DEEPSLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELIORITE_UPGRADE_SMITHING_TEMPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELIORITE_PICKAXE_HEAD)
                .pattern("###")
                .input('#', ModItems.HELIORITE_INGOT)
                .criterion(hasItem(ModItems.HELIORITE_INGOT), conditionsFromItem(ModItems.HELIORITE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELIORITE_PICKAXE_HEAD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELIORITE_SWORD)
                .pattern("#")
                .pattern("#")
                .pattern("I")
                .input('#', ModItems.HELIORITE_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(ModItems.HELIORITE_INGOT), conditionsFromItem(ModItems.HELIORITE_INGOT))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELIORITE_SWORD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELIORITE_SHOVEL)
                .pattern("#")
                .pattern("I")
                .pattern("I")
                .input('#', ModItems.HELIORITE_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(ModItems.HELIORITE_INGOT), conditionsFromItem(ModItems.HELIORITE_INGOT))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELIORITE_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELIORITE_AXE)
                .pattern("##")
                .pattern("#I")
                .pattern(" I")
                .input('#', ModItems.HELIORITE_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(ModItems.HELIORITE_INGOT), conditionsFromItem(ModItems.HELIORITE_INGOT))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .group(ModItems.HELIORITE_AXE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELIORITE_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELIORITE_AXE)
                .pattern("##")
                .pattern("I#")
                .pattern("I ")
                .input('#', ModItems.HELIORITE_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(ModItems.HELIORITE_INGOT), conditionsFromItem(ModItems.HELIORITE_INGOT))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .group(ModItems.HELIORITE_AXE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELIORITE_AXE) + "2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELIORITE_HOE)
                .pattern("##")
                .pattern(" I")
                .pattern(" I")
                .input('#', ModItems.HELIORITE_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(ModItems.HELIORITE_INGOT), conditionsFromItem(ModItems.HELIORITE_INGOT))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .group(ModItems.HELIORITE_HOE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELIORITE_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELIORITE_HOE)
                .pattern("##")
                .pattern("I ")
                .pattern("I ")
                .input('#', ModItems.HELIORITE_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(ModItems.HELIORITE_INGOT), conditionsFromItem(ModItems.HELIORITE_INGOT))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .group(ModItems.HELIORITE_HOE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELIORITE_HOE) + "2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELIORITE_SICKLE)
                .pattern(" #")
                .pattern("I ")
                .input('#', ModItems.HELIORITE_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(ModItems.HELIORITE_INGOT), conditionsFromItem(ModItems.HELIORITE_INGOT))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELIORITE_SICKLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELIORITE_FORGING_HAMMER)
                .pattern("#")
                .pattern("I")
                .input('#', ModItems.HELIORITE_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(ModItems.HELIORITE_INGOT), conditionsFromItem(ModItems.HELIORITE_INGOT))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELIORITE_FORGING_HAMMER)));


        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELIORITE_HELMET)
                .pattern("###")
                .pattern("# #")
                .input('#', ModItems.HELIORITE_INGOT)
                .criterion(hasItem(ModItems.HELIORITE_INGOT), conditionsFromItem(ModItems.HELIORITE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELIORITE_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELIORITE_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.HELIORITE_INGOT)
                .criterion(hasItem(ModItems.HELIORITE_INGOT), conditionsFromItem(ModItems.HELIORITE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELIORITE_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELIORITE_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.HELIORITE_INGOT)
                .criterion(hasItem(ModItems.HELIORITE_INGOT), conditionsFromItem(ModItems.HELIORITE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELIORITE_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HELIORITE_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.HELIORITE_INGOT)
                .criterion(hasItem(ModItems.HELIORITE_INGOT), conditionsFromItem(ModItems.HELIORITE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.HELIORITE_BOOTS)));

        offerBlasting(exporter, ENDURIUM_BLASTABLES, RecipeCategory.MISC, ModItems.ENDURIUM_INGOT, 0.7f, 200, "endurium_ingot");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.ENDURIUM_CRYSTAL, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_ENDURIUM_BLOCK);
        offerReversibleNuggetCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.ENDURIUM_NUGGET, RecipeCategory.MISC, ModItems.ENDURIUM_INGOT);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.ENDURIUM_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDURIUM_BLOCK);

        offerBlasting(exporter, List.of(ModItems.ENDURIUM_SWORD, ModItems.ENDURIUM_PICKAXE, ModItems.ENDURIUM_AXE, ModItems.ENDURIUM_SHOVEL, ModItems.ENDURIUM_HOE, ModItems.ENDURIUM_SICKLE, ModItems.ENDURIUM_FORGING_HAMMER, ModItems.ENDURIUM_HELMET, ModItems.ENDURIUM_CHESTPLATE, ModItems.ENDURIUM_LEGGINGS, ModItems.ENDURIUM_BOOTS), RecipeCategory.MISC, ModItems.ENDURIUM_NUGGET, 0.7f, 400, "endurium_nugget");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDURIUM_UPGRADE_SMITHING_TEMPLATE)
                .pattern("#S#")
                .pattern("#R#")
                .pattern("###")
                .input('#', ModItems.ENDURIUM_INGOT)
                .input('S', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_ingots")))
                .input('R', ModBlocks.COBBLED_RED_DEEPSLATE)
                .criterion(hasItem(ModItems.ENDURIUM_INGOT), conditionsFromItem(ModItems.ENDURIUM_INGOT))
                .criterion(hasItem(ModItems.STEEL_INGOT), conditionsFromItem(ModItems.STEEL_INGOT))
                .criterion(hasItem(ModBlocks.COBBLED_RED_DEEPSLATE), conditionsFromItem(ModBlocks.COBBLED_RED_DEEPSLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ENDURIUM_UPGRADE_SMITHING_TEMPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDURIUM_PICKAXE_HEAD)
                .pattern("###")
                .input('#', ModItems.ENDURIUM_INGOT)
                .criterion(hasItem(ModItems.ENDURIUM_INGOT), conditionsFromItem(ModItems.ENDURIUM_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ENDURIUM_PICKAXE_HEAD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDURIUM_SWORD)
                .pattern("#")
                .pattern("#")
                .pattern("I")
                .input('#', ModItems.ENDURIUM_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(ModItems.ENDURIUM_INGOT), conditionsFromItem(ModItems.ENDURIUM_INGOT))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ENDURIUM_SWORD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDURIUM_SHOVEL)
                .pattern("#")
                .pattern("I")
                .pattern("I")
                .input('#', ModItems.ENDURIUM_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(ModItems.ENDURIUM_INGOT), conditionsFromItem(ModItems.ENDURIUM_INGOT))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ENDURIUM_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDURIUM_AXE)
                .pattern("##")
                .pattern("#I")
                .pattern(" I")
                .input('#', ModItems.ENDURIUM_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(ModItems.ENDURIUM_INGOT), conditionsFromItem(ModItems.ENDURIUM_INGOT))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .group(ModItems.ENDURIUM_AXE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ENDURIUM_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDURIUM_AXE)
                .pattern("##")
                .pattern("I#")
                .pattern("I ")
                .input('#', ModItems.ENDURIUM_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(ModItems.ENDURIUM_INGOT), conditionsFromItem(ModItems.ENDURIUM_INGOT))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .group(ModItems.ENDURIUM_AXE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ENDURIUM_AXE) + "2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDURIUM_HOE)
                .pattern("##")
                .pattern(" I")
                .pattern(" I")
                .input('#', ModItems.ENDURIUM_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(ModItems.ENDURIUM_INGOT), conditionsFromItem(ModItems.ENDURIUM_INGOT))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .group(ModItems.ENDURIUM_HOE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ENDURIUM_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDURIUM_HOE)
                .pattern("##")
                .pattern("I ")
                .pattern("I ")
                .input('#', ModItems.ENDURIUM_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(ModItems.ENDURIUM_INGOT), conditionsFromItem(ModItems.ENDURIUM_INGOT))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .group(ModItems.ENDURIUM_HOE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ENDURIUM_HOE) + "2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDURIUM_SICKLE)
                .pattern(" #")
                .pattern("I ")
                .input('#', ModItems.ENDURIUM_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(ModItems.ENDURIUM_INGOT), conditionsFromItem(ModItems.ENDURIUM_INGOT))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ENDURIUM_SICKLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDURIUM_FORGING_HAMMER)
                .pattern("#")
                .pattern("I")
                .input('#', ModItems.ENDURIUM_INGOT)
                .input('I', TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .criterion(hasItem(ModItems.ENDURIUM_INGOT), conditionsFromItem(ModItems.ENDURIUM_INGOT))
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ENDURIUM_FORGING_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDURIUM_HELMET)
                .pattern("###")
                .pattern("# #")
                .input('#', ModItems.ENDURIUM_INGOT)
                .criterion(hasItem(ModItems.ENDURIUM_INGOT), conditionsFromItem(ModItems.ENDURIUM_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ENDURIUM_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDURIUM_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.ENDURIUM_INGOT)
                .criterion(hasItem(ModItems.ENDURIUM_INGOT), conditionsFromItem(ModItems.ENDURIUM_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ENDURIUM_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDURIUM_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.ENDURIUM_INGOT)
                .criterion(hasItem(ModItems.ENDURIUM_INGOT), conditionsFromItem(ModItems.ENDURIUM_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ENDURIUM_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ENDURIUM_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.ENDURIUM_INGOT)
                .criterion(hasItem(ModItems.ENDURIUM_INGOT), conditionsFromItem(ModItems.ENDURIUM_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ENDURIUM_BOOTS)));

        offerBlasting(exporter, PALLADIUM_BLASTABLES, RecipeCategory.MISC, ModItems.PALLADIUM_INGOT, 0.7f, 200, "palladium_ingot");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.RAW_PALLADIUM, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_PALLADIUM_BLOCK);
        offerReversibleNuggetCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.PALLADIUM_NUGGET, RecipeCategory.MISC, ModItems.PALLADIUM_INGOT);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.PALLADIUM_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALLADIUM_BLOCK);

        offerBlasting(exporter, List.of(ModItems.PALLADIUM_SWORD, ModItems.PALLADIUM_PICKAXE, ModItems.PALLADIUM_AXE, ModItems.PALLADIUM_SHOVEL, ModItems.PALLADIUM_HOE, ModItems.PALLADIUM_SICKLE, ModItems.PALLADIUM_FORGING_HAMMER, ModItems.PALLADIUM_HELMET, ModItems.PALLADIUM_CHESTPLATE, ModItems.PALLADIUM_LEGGINGS, ModItems.PALLADIUM_BOOTS), RecipeCategory.MISC, ModItems.PALLADIUM_NUGGET, 0.7f, 400, "palladium_nugget");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PALLADIUM_SWORD)
                .pattern("#")
                .pattern("#")
                .pattern("I")
                .input('#', ModItems.PALLADIUM_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.PALLADIUM_INGOT), conditionsFromItem(ModItems.PALLADIUM_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PALLADIUM_SWORD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PALLADIUM_PICKAXE)
                .pattern("###")
                .pattern(" I ")
                .pattern(" I ")
                .input('#', ModItems.PALLADIUM_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.PALLADIUM_INGOT), conditionsFromItem(ModItems.PALLADIUM_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PALLADIUM_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PALLADIUM_SHOVEL)
                .pattern("#")
                .pattern("I")
                .pattern("I")
                .input('#', ModItems.PALLADIUM_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.PALLADIUM_INGOT), conditionsFromItem(ModItems.PALLADIUM_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PALLADIUM_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PALLADIUM_AXE)
                .pattern("##")
                .pattern("#I")
                .pattern(" I")
                .input('#', ModItems.PALLADIUM_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.PALLADIUM_INGOT), conditionsFromItem(ModItems.PALLADIUM_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .group(ModItems.PALLADIUM_AXE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PALLADIUM_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PALLADIUM_AXE)
                .pattern("##")
                .pattern("I#")
                .pattern("I ")
                .input('#', ModItems.PALLADIUM_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.PALLADIUM_INGOT), conditionsFromItem(ModItems.PALLADIUM_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .group(ModItems.PALLADIUM_AXE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PALLADIUM_AXE) + "2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PALLADIUM_HOE)
                .pattern("##")
                .pattern(" I")
                .pattern(" I")
                .input('#', ModItems.PALLADIUM_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.PALLADIUM_INGOT), conditionsFromItem(ModItems.PALLADIUM_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .group(ModItems.PALLADIUM_HOE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PALLADIUM_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PALLADIUM_HOE)
                .pattern("##")
                .pattern("I ")
                .pattern("I ")
                .input('#', ModItems.PALLADIUM_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.PALLADIUM_INGOT), conditionsFromItem(ModItems.PALLADIUM_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .group(ModItems.PALLADIUM_HOE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PALLADIUM_HOE) + "2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PALLADIUM_SICKLE)
                .pattern(" #")
                .pattern("I ")
                .input('#', ModItems.PALLADIUM_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.PALLADIUM_INGOT), conditionsFromItem(ModItems.PALLADIUM_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PALLADIUM_SICKLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PALLADIUM_FORGING_HAMMER)
                .pattern("#")
                .pattern("I")
                .input('#', ModItems.PALLADIUM_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.PALLADIUM_INGOT), conditionsFromItem(ModItems.PALLADIUM_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PALLADIUM_FORGING_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PALLADIUM_HELMET)
                .pattern("###")
                .pattern("# #")
                .input('#', ModItems.PALLADIUM_INGOT)
                .criterion(hasItem(ModItems.PALLADIUM_INGOT), conditionsFromItem(ModItems.PALLADIUM_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PALLADIUM_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PALLADIUM_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.PALLADIUM_INGOT)
                .criterion(hasItem(ModItems.PALLADIUM_INGOT), conditionsFromItem(ModItems.PALLADIUM_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PALLADIUM_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PALLADIUM_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.PALLADIUM_INGOT)
                .criterion(hasItem(ModItems.PALLADIUM_INGOT), conditionsFromItem(ModItems.PALLADIUM_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PALLADIUM_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PALLADIUM_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.PALLADIUM_INGOT)
                .criterion(hasItem(ModItems.PALLADIUM_INGOT), conditionsFromItem(ModItems.PALLADIUM_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PALLADIUM_BOOTS)));

        offerBlasting(exporter, JURASSOLINE_BLASTABLES, RecipeCategory.MISC, ModItems.JURASSOLINE_INGOT, 0.7f, 200, "jurassoline_ingot");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.JURASSOLINE_CRYSTAL, RecipeCategory.BUILDING_BLOCKS, ModBlocks.JURASSOLINE_CRYSTAL_BLOCK);
        offerReversibleNuggetCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.JURASSOLINE_NUGGET, RecipeCategory.MISC, ModItems.JURASSOLINE_INGOT);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.JURASSOLINE_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.JURASSOLINE_BLOCK);

        offerBlasting(exporter, List.of(ModItems.JURASSOLINE_SWORD, ModItems.JURASSOLINE_PICKAXE, ModItems.JURASSOLINE_AXE, ModItems.JURASSOLINE_SHOVEL, ModItems.JURASSOLINE_HOE, ModItems.JURASSOLINE_SICKLE, ModItems.JURASSOLINE_FORGING_HAMMER, ModItems.JURASSOLINE_HELMET, ModItems.JURASSOLINE_CHESTPLATE, ModItems.JURASSOLINE_LEGGINGS, ModItems.JURASSOLINE_BOOTS), RecipeCategory.MISC, ModItems.JURASSOLINE_NUGGET, 0.7f, 400, "jurassoline_nugget");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JURASSOLINE_SWORD)
                .pattern("#")
                .pattern("#")
                .pattern("I")
                .input('#', ModItems.JURASSOLINE_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.JURASSOLINE_INGOT), conditionsFromItem(ModItems.JURASSOLINE_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.JURASSOLINE_SWORD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JURASSOLINE_PICKAXE)
                .pattern("###")
                .pattern(" I ")
                .pattern(" I ")
                .input('#', ModItems.JURASSOLINE_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.JURASSOLINE_INGOT), conditionsFromItem(ModItems.JURASSOLINE_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.JURASSOLINE_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JURASSOLINE_SHOVEL)
                .pattern("#")
                .pattern("I")
                .pattern("I")
                .input('#', ModItems.JURASSOLINE_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.JURASSOLINE_INGOT), conditionsFromItem(ModItems.JURASSOLINE_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.JURASSOLINE_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JURASSOLINE_AXE)
                .pattern("##")
                .pattern("#I")
                .pattern(" I")
                .input('#', ModItems.JURASSOLINE_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.JURASSOLINE_INGOT), conditionsFromItem(ModItems.JURASSOLINE_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .group(ModItems.JURASSOLINE_AXE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.JURASSOLINE_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JURASSOLINE_AXE)
                .pattern("##")
                .pattern("I#")
                .pattern("I ")
                .input('#', ModItems.JURASSOLINE_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.JURASSOLINE_INGOT), conditionsFromItem(ModItems.JURASSOLINE_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .group(ModItems.JURASSOLINE_AXE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.JURASSOLINE_AXE) + "2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JURASSOLINE_HOE)
                .pattern("##")
                .pattern(" I")
                .pattern(" I")
                .input('#', ModItems.JURASSOLINE_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.JURASSOLINE_INGOT), conditionsFromItem(ModItems.JURASSOLINE_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .group(ModItems.JURASSOLINE_HOE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.JURASSOLINE_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JURASSOLINE_HOE)
                .pattern("##")
                .pattern("I ")
                .pattern("I ")
                .input('#', ModItems.JURASSOLINE_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.JURASSOLINE_INGOT), conditionsFromItem(ModItems.JURASSOLINE_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .group(ModItems.JURASSOLINE_HOE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.JURASSOLINE_HOE) + "2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JURASSOLINE_SICKLE)
                .pattern(" #")
                .pattern("I ")
                .input('#', ModItems.JURASSOLINE_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.JURASSOLINE_INGOT), conditionsFromItem(ModItems.JURASSOLINE_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.JURASSOLINE_SICKLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JURASSOLINE_FORGING_HAMMER)
                .pattern("#")
                .pattern("I")
                .input('#', ModItems.JURASSOLINE_INGOT)
                .input('I', ModItems.FOSSILIZED_BONE)
                .criterion(hasItem(ModItems.JURASSOLINE_INGOT), conditionsFromItem(ModItems.JURASSOLINE_INGOT))
                .criterion(hasItem(ModItems.FOSSILIZED_BONE), conditionsFromItem(ModItems.FOSSILIZED_BONE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.JURASSOLINE_FORGING_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JURASSOLINE_HELMET)
                .pattern("###")
                .pattern("# #")
                .input('#', ModItems.JURASSOLINE_INGOT)
                .criterion(hasItem(ModItems.JURASSOLINE_INGOT), conditionsFromItem(ModItems.JURASSOLINE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.JURASSOLINE_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JURASSOLINE_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.JURASSOLINE_INGOT)
                .criterion(hasItem(ModItems.JURASSOLINE_INGOT), conditionsFromItem(ModItems.JURASSOLINE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.JURASSOLINE_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JURASSOLINE_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.JURASSOLINE_INGOT)
                .criterion(hasItem(ModItems.JURASSOLINE_INGOT), conditionsFromItem(ModItems.JURASSOLINE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.JURASSOLINE_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JURASSOLINE_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.JURASSOLINE_INGOT)
                .criterion(hasItem(ModItems.JURASSOLINE_INGOT), conditionsFromItem(ModItems.JURASSOLINE_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.JURASSOLINE_BOOTS)));

        offerBlasting(exporter, CINNABAR_BLASTABLES, RecipeCategory.MISC, ModItems.CINNABAR_INGOT, 0.7f, 200, "cinnabar_ingot");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.CINNABAR_CRYSTAL, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CINNABAR_CRYSTAL_BLOCK);
        offerReversibleNuggetCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.CINNABAR_NUGGET, RecipeCategory.MISC, ModItems.CINNABAR_INGOT);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.CINNABAR_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CINNABAR_BLOCK);

        offerBlasting(exporter, List.of(ModItems.CINNABAR_SWORD, ModItems.CINNABAR_PICKAXE, ModItems.CINNABAR_AXE, ModItems.CINNABAR_SHOVEL, ModItems.CINNABAR_HOE, ModItems.CINNABAR_SICKLE, ModItems.CINNABAR_FORGING_HAMMER, ModItems.CINNABAR_HELMET, ModItems.CINNABAR_CHESTPLATE, ModItems.CINNABAR_LEGGINGS, ModItems.CINNABAR_BOOTS), RecipeCategory.MISC, ModItems.CINNABAR_NUGGET, 0.7f, 400, "cinnabar_nugget");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CINNABAR_SWORD)
                .pattern("#")
                .pattern("#")
                .pattern("I")
                .input('#', ModItems.CINNABAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.CINNABAR_INGOT), conditionsFromItem(ModItems.CINNABAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CINNABAR_SWORD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CINNABAR_PICKAXE)
                .pattern("###")
                .pattern(" I ")
                .pattern(" I ")
                .input('#', ModItems.CINNABAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.CINNABAR_INGOT), conditionsFromItem(ModItems.CINNABAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CINNABAR_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CINNABAR_SHOVEL)
                .pattern("#")
                .pattern("I")
                .pattern("I")
                .input('#', ModItems.CINNABAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.CINNABAR_INGOT), conditionsFromItem(ModItems.CINNABAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CINNABAR_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CINNABAR_AXE)
                .pattern("##")
                .pattern("#I")
                .pattern(" I")
                .input('#', ModItems.CINNABAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.CINNABAR_INGOT), conditionsFromItem(ModItems.CINNABAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .group(ModItems.CINNABAR_AXE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CINNABAR_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CINNABAR_AXE)
                .pattern("##")
                .pattern("I#")
                .pattern("I ")
                .input('#', ModItems.CINNABAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.CINNABAR_INGOT), conditionsFromItem(ModItems.CINNABAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .group(ModItems.CINNABAR_AXE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CINNABAR_AXE) + "2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CINNABAR_HOE)
                .pattern("##")
                .pattern(" I")
                .pattern(" I")
                .input('#', ModItems.CINNABAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.CINNABAR_INGOT), conditionsFromItem(ModItems.CINNABAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .group(ModItems.CINNABAR_HOE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CINNABAR_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CINNABAR_HOE)
                .pattern("##")
                .pattern("I ")
                .pattern("I ")
                .input('#', ModItems.CINNABAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.CINNABAR_INGOT), conditionsFromItem(ModItems.CINNABAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .group(ModItems.CINNABAR_HOE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CINNABAR_HOE) + "2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CINNABAR_SICKLE)
                .pattern(" #")
                .pattern("I ")
                .input('#', ModItems.CINNABAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.CINNABAR_INGOT), conditionsFromItem(ModItems.CINNABAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CINNABAR_SICKLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CINNABAR_FORGING_HAMMER)
                .pattern("#")
                .pattern("I")
                .input('#', ModItems.CINNABAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.CINNABAR_INGOT), conditionsFromItem(ModItems.CINNABAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CINNABAR_FORGING_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CINNABAR_HELMET)
                .pattern("###")
                .pattern("# #")
                .input('#', ModItems.CINNABAR_INGOT)
                .criterion(hasItem(ModItems.CINNABAR_INGOT), conditionsFromItem(ModItems.CINNABAR_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CINNABAR_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CINNABAR_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.CINNABAR_INGOT)
                .criterion(hasItem(ModItems.CINNABAR_INGOT), conditionsFromItem(ModItems.CINNABAR_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CINNABAR_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CINNABAR_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.CINNABAR_INGOT)
                .criterion(hasItem(ModItems.CINNABAR_INGOT), conditionsFromItem(ModItems.CINNABAR_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CINNABAR_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CINNABAR_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.CINNABAR_INGOT)
                .criterion(hasItem(ModItems.CINNABAR_INGOT), conditionsFromItem(ModItems.CINNABAR_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CINNABAR_BOOTS)));

        offerBlasting(exporter, NEBULAR_BLASTABLES, RecipeCategory.MISC, ModItems.NEBULAR_INGOT, 0.7f, 200, "nebular_ingot");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.NEBULAR_CRYSTAL, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_NEBULAR_BLOCK);
        offerReversibleNuggetCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.NEBULAR_NUGGET, RecipeCategory.MISC, ModItems.NEBULAR_INGOT);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.NEBULAR_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.NEBULAR_BLOCK);

        offerBlasting(exporter, List.of(ModItems.NEBULAR_SWORD, ModItems.NEBULAR_PICKAXE, ModItems.NEBULAR_AXE, ModItems.NEBULAR_SHOVEL, ModItems.NEBULAR_HOE, ModItems.NEBULAR_SICKLE, ModItems.NEBULAR_FORGING_HAMMER, ModItems.NEBULAR_HELMET, ModItems.NEBULAR_CHESTPLATE, ModItems.NEBULAR_LEGGINGS, ModItems.NEBULAR_BOOTS), RecipeCategory.MISC, ModItems.NEBULAR_NUGGET, 0.7f, 400, "nebular_nugget");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NEBULAR_SWORD)
                .pattern("#")
                .pattern("#")
                .pattern("I")
                .input('#', ModItems.NEBULAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.NEBULAR_INGOT), conditionsFromItem(ModItems.NEBULAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NEBULAR_SWORD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NEBULAR_PICKAXE)
                .pattern("###")
                .pattern(" I ")
                .pattern(" I ")
                .input('#', ModItems.NEBULAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.NEBULAR_INGOT), conditionsFromItem(ModItems.NEBULAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NEBULAR_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NEBULAR_SHOVEL)
                .pattern("#")
                .pattern("I")
                .pattern("I")
                .input('#', ModItems.NEBULAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.NEBULAR_INGOT), conditionsFromItem(ModItems.NEBULAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NEBULAR_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NEBULAR_AXE)
                .pattern("##")
                .pattern("#I")
                .pattern(" I")
                .input('#', ModItems.NEBULAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.NEBULAR_INGOT), conditionsFromItem(ModItems.NEBULAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .group(ModItems.NEBULAR_AXE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NEBULAR_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NEBULAR_AXE)
                .pattern("##")
                .pattern("I#")
                .pattern("I ")
                .input('#', ModItems.NEBULAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.NEBULAR_INGOT), conditionsFromItem(ModItems.NEBULAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .group(ModItems.NEBULAR_AXE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NEBULAR_AXE) + "2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NEBULAR_HOE)
                .pattern("##")
                .pattern(" I")
                .pattern(" I")
                .input('#', ModItems.NEBULAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.NEBULAR_INGOT), conditionsFromItem(ModItems.NEBULAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .group(ModItems.NEBULAR_HOE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NEBULAR_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NEBULAR_HOE)
                .pattern("##")
                .pattern("I ")
                .pattern("I ")
                .input('#', ModItems.NEBULAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.NEBULAR_INGOT), conditionsFromItem(ModItems.NEBULAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .group(ModItems.NEBULAR_HOE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NEBULAR_HOE) + "2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NEBULAR_SICKLE)
                .pattern(" #")
                .pattern("I ")
                .input('#', ModItems.NEBULAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.NEBULAR_INGOT), conditionsFromItem(ModItems.NEBULAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NEBULAR_SICKLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NEBULAR_FORGING_HAMMER)
                .pattern("#")
                .pattern("I")
                .input('#', ModItems.NEBULAR_INGOT)
                .input('I', ModItems.REINFORCED_TOOL_ROD)
                .criterion(hasItem(ModItems.NEBULAR_INGOT), conditionsFromItem(ModItems.NEBULAR_INGOT))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NEBULAR_FORGING_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NEBULAR_HELMET)
                .pattern("###")
                .pattern("# #")
                .input('#', ModItems.NEBULAR_INGOT)
                .criterion(hasItem(ModItems.NEBULAR_INGOT), conditionsFromItem(ModItems.NEBULAR_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NEBULAR_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NEBULAR_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.NEBULAR_INGOT)
                .criterion(hasItem(ModItems.NEBULAR_INGOT), conditionsFromItem(ModItems.NEBULAR_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NEBULAR_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NEBULAR_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.NEBULAR_INGOT)
                .criterion(hasItem(ModItems.NEBULAR_INGOT), conditionsFromItem(ModItems.NEBULAR_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NEBULAR_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NEBULAR_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.NEBULAR_INGOT)
                .criterion(hasItem(ModItems.NEBULAR_INGOT), conditionsFromItem(ModItems.NEBULAR_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NEBULAR_BOOTS)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.REINFORCED_TOOL_ROD)
                .input(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "steel_rods")))
                .input(Items.NETHERITE_SCRAP)
                .criterion(hasItem(ModItems.STEEL_ROD), conditionsFromItem(ModItems.STEEL_ROD))
                .criterion(hasItem(Items.NETHERITE_SCRAP), conditionsFromItem(Items.NETHERITE_SCRAP))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.REINFORCED_TOOL_ROD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FANCY_TOOL_ROD)
                .input(Items.DIAMOND)
                .input(ModItems.REINFORCED_TOOL_ROD)
                .input(Items.GOLD_INGOT)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .criterion(hasItem(ModItems.REINFORCED_TOOL_ROD), conditionsFromItem(ModItems.REINFORCED_TOOL_ROD))
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.FANCY_TOOL_ROD)));

        offerBlasting(exporter, MITHRIL_BLASTABLES, RecipeCategory.MISC, ModItems.MITHRIL_INGOT, 1.0f, 400, "mithril_ingot");
        offerBlasting(exporter, List.of(ModBlocks.RAW_MITHRIL_BLOCK), RecipeCategory.MISC, ModBlocks.MITHRIL_BLOCK, 1.0f, 400, "mithril_block");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.RAW_MITHRIL, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_MITHRIL_BLOCK);
        offerReversibleNuggetCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.MITHRIL_NUGGET, RecipeCategory.MISC, ModItems.MITHRIL_INGOT);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.MITHRIL_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MITHRIL_BLOCK);

        offerBlasting(exporter, List.of(ModItems.MITHRIL_SWORD, ModItems.MITHRIL_PICKAXE, ModItems.MITHRIL_AXE, ModItems.MITHRIL_SHOVEL, ModItems.MITHRIL_HOE, ModItems.MITHRIL_SICKLE, ModItems.MITHRIL_FORGING_HAMMER, ModItems.MITHRIL_HELMET, ModItems.MITHRIL_CHESTPLATE, ModItems.MITHRIL_LEGGINGS, ModItems.MITHRIL_BOOTS), RecipeCategory.MISC, ModItems.MITHRIL_NUGGET, 0.7f, 400, "mithril_nugget");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_SWORD)
                .pattern("#")
                .pattern("#")
                .pattern("I")
                .input('#', ModItems.MITHRIL_INGOT)
                .input('I', ModItems.FANCY_TOOL_ROD)
                .criterion(hasItem(ModItems.MITHRIL_INGOT), conditionsFromItem(ModItems.MITHRIL_INGOT))
                .criterion(hasItem(ModItems.FANCY_TOOL_ROD), conditionsFromItem(ModItems.FANCY_TOOL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MITHRIL_SWORD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_PICKAXE)
                .pattern("###")
                .pattern(" I ")
                .pattern(" I ")
                .input('#', ModItems.MITHRIL_INGOT)
                .input('I', ModItems.FANCY_TOOL_ROD)
                .criterion(hasItem(ModItems.MITHRIL_INGOT), conditionsFromItem(ModItems.MITHRIL_INGOT))
                .criterion(hasItem(ModItems.FANCY_TOOL_ROD), conditionsFromItem(ModItems.FANCY_TOOL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MITHRIL_PICKAXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_SHOVEL)
                .pattern("#")
                .pattern("I")
                .pattern("I")
                .input('#', ModItems.MITHRIL_INGOT)
                .input('I', ModItems.FANCY_TOOL_ROD)
                .criterion(hasItem(ModItems.MITHRIL_INGOT), conditionsFromItem(ModItems.MITHRIL_INGOT))
                .criterion(hasItem(ModItems.FANCY_TOOL_ROD), conditionsFromItem(ModItems.FANCY_TOOL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MITHRIL_SHOVEL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_AXE)
                .pattern("##")
                .pattern("#I")
                .pattern(" I")
                .input('#', ModItems.MITHRIL_INGOT)
                .input('I', ModItems.FANCY_TOOL_ROD)
                .criterion(hasItem(ModItems.MITHRIL_INGOT), conditionsFromItem(ModItems.MITHRIL_INGOT))
                .criterion(hasItem(ModItems.FANCY_TOOL_ROD), conditionsFromItem(ModItems.FANCY_TOOL_ROD))
                .group(ModItems.MITHRIL_AXE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MITHRIL_AXE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_AXE)
                .pattern("##")
                .pattern("I#")
                .pattern("I ")
                .input('#', ModItems.MITHRIL_INGOT)
                .input('I', ModItems.FANCY_TOOL_ROD)
                .criterion(hasItem(ModItems.MITHRIL_INGOT), conditionsFromItem(ModItems.MITHRIL_INGOT))
                .criterion(hasItem(ModItems.FANCY_TOOL_ROD), conditionsFromItem(ModItems.FANCY_TOOL_ROD))
                .group(ModItems.MITHRIL_AXE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MITHRIL_AXE) + "2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_HOE)
                .pattern("##")
                .pattern(" I")
                .pattern(" I")
                .input('#', ModItems.MITHRIL_INGOT)
                .input('I', ModItems.FANCY_TOOL_ROD)
                .criterion(hasItem(ModItems.MITHRIL_INGOT), conditionsFromItem(ModItems.MITHRIL_INGOT))
                .criterion(hasItem(ModItems.FANCY_TOOL_ROD), conditionsFromItem(ModItems.FANCY_TOOL_ROD))
                .group(ModItems.MITHRIL_HOE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MITHRIL_HOE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_HOE)
                .pattern("##")
                .pattern("I ")
                .pattern("I ")
                .input('#', ModItems.MITHRIL_INGOT)
                .input('I', ModItems.FANCY_TOOL_ROD)
                .criterion(hasItem(ModItems.MITHRIL_INGOT), conditionsFromItem(ModItems.MITHRIL_INGOT))
                .criterion(hasItem(ModItems.FANCY_TOOL_ROD), conditionsFromItem(ModItems.FANCY_TOOL_ROD))
                .group(ModItems.MITHRIL_HOE.toString())
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MITHRIL_HOE) + "2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_FORGING_HAMMER)
                .pattern("#")
                .pattern("I")
                .input('#', ModItems.MITHRIL_INGOT)
                .input('I', ModItems.FANCY_TOOL_ROD)
                .criterion(hasItem(ModItems.MITHRIL_INGOT), conditionsFromItem(ModItems.MITHRIL_INGOT))
                .criterion(hasItem(ModItems.FANCY_TOOL_ROD), conditionsFromItem(ModItems.FANCY_TOOL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MITHRIL_FORGING_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_STAR_CATCHER)
                .pattern("  #")
                .pattern(" I ")
                .pattern("I  ")
                .input('#', ModItems.MITHRIL_INGOT)
                .input('I', ModItems.FANCY_TOOL_ROD)
                .criterion(hasItem(ModItems.MITHRIL_INGOT), conditionsFromItem(ModItems.MITHRIL_INGOT))
                .criterion(hasItem(ModItems.FANCY_TOOL_ROD), conditionsFromItem(ModItems.FANCY_TOOL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MITHRIL_STAR_CATCHER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_SICKLE)
                .pattern(" #")
                .pattern("I ")
                .input('#', ModItems.MITHRIL_INGOT)
                .input('I', ModItems.FANCY_TOOL_ROD)
                .criterion(hasItem(ModItems.MITHRIL_INGOT), conditionsFromItem(ModItems.MITHRIL_INGOT))
                .criterion(hasItem(ModItems.FANCY_TOOL_ROD), conditionsFromItem(ModItems.FANCY_TOOL_ROD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MITHRIL_SICKLE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_HELMET)
                .pattern("###")
                .pattern("# #")
                .input('#', ModItems.MITHRIL_INGOT)
                .criterion(hasItem(ModItems.MITHRIL_INGOT), conditionsFromItem(ModItems.MITHRIL_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MITHRIL_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.MITHRIL_INGOT)
                .criterion(hasItem(ModItems.MITHRIL_INGOT), conditionsFromItem(ModItems.MITHRIL_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MITHRIL_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.MITHRIL_INGOT)
                .criterion(hasItem(ModItems.MITHRIL_INGOT), conditionsFromItem(ModItems.MITHRIL_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MITHRIL_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_BOOTS)
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.MITHRIL_INGOT)
                .criterion(hasItem(ModItems.MITHRIL_INGOT), conditionsFromItem(ModItems.MITHRIL_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MITHRIL_BOOTS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_HORSE_ARMOR)
                .pattern("  #")
                .pattern("#L#")
                .pattern("###")
                .input('#', ModItems.MITHRIL_INGOT)
                .input('L', Items.LEATHER)
                .criterion(hasItem(ModItems.MITHRIL_INGOT), conditionsFromItem(ModItems.MITHRIL_INGOT))
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MITHRIL_HORSE_ARMOR)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MITHRIL_MIRROR)
                .input(ModItems.FOGGY_MITHRIL_MIRROR)
                .input(ModItems.SOUL_FLASK_OF_THE_ABYSS)
                .criterion(hasItem(ModItems.FOGGY_MITHRIL_MIRROR), conditionsFromItem(ModItems.FOGGY_MITHRIL_MIRROR))
                .criterion(hasItem(ModItems.SOUL_FLASK_OF_THE_ABYSS), conditionsFromItem(ModItems.SOUL_FLASK_OF_THE_ABYSS))
                .offerTo(exporter, new Identifier("charge_" + getRecipeName(ModItems.MITHRIL_MIRROR)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.MITHRIL_ANVIL)
                .pattern("###")
                .pattern(" I ")
                .pattern("III")
                .input('#', ModBlocks.MITHRIL_BLOCK)
                .input('I', ModItems.MITHRIL_INGOT)
                .criterion(hasItem(ModBlocks.MITHRIL_BLOCK), conditionsFromItem(ModBlocks.MITHRIL_BLOCK))
                .criterion(hasItem(ModItems.MITHRIL_INGOT), conditionsFromItem(ModItems.MITHRIL_INGOT))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.MITHRIL_ANVIL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.MITHRIL_BARS,16)
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.MITHRIL_INGOT)
                .criterion(hasItem(ModBlocks.MITHRIL_BLOCK), conditionsFromItem(ModBlocks.MITHRIL_BLOCK))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.MITHRIL_BARS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModItems.GAME_CARTRIDGE_1)
                .pattern("N")
                .pattern("C")
                .input('N', Items.NETHERRACK)
                .input('C', ModItems.GAME_CARTRIDGE)
                .criterion(hasItem(Items.NETHERRACK), conditionsFromItem(Items.NETHERRACK))
                .criterion(hasItem(ModItems.GAME_CARTRIDGE), conditionsFromItem(ModItems.GAME_CARTRIDGE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GAME_CARTRIDGE_1)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModItems.GAME_CARTRIDGE_2)
                .pattern("X")
                .pattern("C")
                .input('X', ModItems.XEN_CRYSTAL)
                .input('C', ModItems.GAME_CARTRIDGE)
                .criterion(hasItem(ModItems.XEN_CRYSTAL), conditionsFromItem(ModItems.XEN_CRYSTAL))
                .criterion(hasItem(ModItems.GAME_CARTRIDGE), conditionsFromItem(ModItems.GAME_CARTRIDGE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GAME_CARTRIDGE_2)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModItems.GAME_CARTRIDGE_3)
                .pattern("S")
                .pattern("C")
                .input('S', ItemTags.SAPLINGS)
                .input('C', ModItems.GAME_CARTRIDGE)
                .criterion(hasItem(Items.OAK_SAPLING), conditionsFromItem(Items.OAK_SAPLING))
                .criterion(hasItem(ModItems.GAME_CARTRIDGE), conditionsFromItem(ModItems.GAME_CARTRIDGE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GAME_CARTRIDGE_3)));

        offerReversibleNuggetCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.ASTRAL_NUGGET, RecipeCategory.MISC, ModItems.ASTRAL_INGOT);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.XEN_CRYSTAL, RecipeCategory.BUILDING_BLOCKS, ModBlocks.XEN_CRYSTAL_BLOCK);
    }
}
