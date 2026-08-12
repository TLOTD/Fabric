package net.tlotd.fluid;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.transfer.v1.fluid.CauldronFluidContent;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;
import net.tlotd.block.custom.BoilingWaterFluidBlock;
import net.tlotd.block.custom.ChemicalWasteFluidBlock;
import net.tlotd.item.compat.create.ModBucketItem;
import net.tlotd.item.custom.ChemicalWasteBucketItem;

public class ModFluids {

    public static FlowableFluid STILL_BOILING_WATER;
    public static FlowableFluid FLOWING_BOILING_WATER;
    public static Block BOILING_WATER_BLOCK;
    public static Item BOILING_WATER_BUCKET;

    public static FlowableFluid STILL_SPEZI;
    public static FlowableFluid FLOWING_SPEZI;
    public static Block SPEZI_BLOCK;
    public static Item SPEZI_BUCKET;

    public static FlowableFluid STILL_BEER;
    public static FlowableFluid FLOWING_BEER;
    public static Block BEER_BLOCK;
    public static Item BEER_BUCKET;

    public static FlowableFluid STILL_MEAD;
    public static FlowableFluid FLOWING_MEAD;
    public static Block MEAD_BLOCK;
    public static Item MEAD_BUCKET;

    public static FlowableFluid STILL_HOT_MILK;
    public static FlowableFluid FLOWING_HOT_MILK;
    public static Block HOT_MILK_BLOCK;
    public static Item HOT_MILK_BUCKET;

    public static FlowableFluid STILL_HOT_CHOCOLATE;
    public static FlowableFluid FLOWING_HOT_CHOCOLATE;
    public static Block HOT_CHOCOLATE_BLOCK;
    public static Item HOT_CHOCOLATE_BUCKET;

    public static FlowableFluid STILL_HOT_COFFEE;
    public static FlowableFluid FLOWING_HOT_COFFEE;
    public static Block HOT_COFFEE_BLOCK;
    public static Item HOT_COFFEE_BUCKET;

    public static FlowableFluid STILL_OIL;
    public static FlowableFluid FLOWING_OIL;
    public static Block OIL_BLOCK;
    public static Item OIL_BUCKET;

    public static FlowableFluid STILL_BLOOD;
    public static FlowableFluid FLOWING_BLOOD;
    public static Block BLOOD_BLOCK;
    public static Item BLOOD_BUCKET;

    public static FlowableFluid STILL_CHEMICAL_WASTE;
    public static FlowableFluid FLOWING_CHEMICAL_WASTE;
    public static Block CHEMICAL_WASTE_BLOCK;
    public static Item CHEMICAL_WASTE_BUCKET;

    public static FlowableFluid STILL_SWEET_BERRY_JAM;
    public static FlowableFluid FLOWING_SWEET_BERRY_JAM;
    public static Block SWEET_BERRY_JAM_BLOCK;
    public static Item SWEET_BERRY_JAM_BUCKET;

    public static FlowableFluid STILL_GLOW_BERRY_JAM;
    public static FlowableFluid FLOWING_GLOW_BERRY_JAM;
    public static Block GLOW_BERRY_JAM_BLOCK;
    public static Item GLOW_BERRY_JAM_BUCKET;

    public static FlowableFluid STILL_STRAWBERRY_JAM;
    public static FlowableFluid FLOWING_STRAWBERRY_JAM;
    public static Block STRAWBERRY_JAM_BLOCK;
    public static Item STRAWBERRY_JAM_BUCKET;

    public static FlowableFluid STILL_ORANGE_MARMELADE;
    public static FlowableFluid FLOWING_ORANGE_MARMELADE;
    public static Block ORANGE_MARMELADE_BLOCK;
    public static Item ORANGE_MARMELADE_BUCKET;

    public static FlowableFluid STILL_BLUE_BERRY_JAM;
    public static FlowableFluid FLOWING_BLUE_BERRY_JAM;
    public static Block BLUE_BERRY_JAM_BLOCK;
    public static Item BLUE_BERRY_JAM_BUCKET;

    public static FlowableFluid STILL_DROOPFRUIT_JAM;
    public static FlowableFluid FLOWING_DROOPFRUIT_JAM;
    public static Block DROOPFRUIT_JAM_BLOCK;
    public static Item DROOPFRUIT_JAM_BUCKET;

    public static FlowableFluid STILL_ANCIENT_SOULBERRY_JAM;
    public static FlowableFluid FLOWING_ANCIENT_SOULBERRY_JAM;
    public static Block ANCIENT_SOULBERRY_JAM_BLOCK;
    public static Item ANCIENT_SOULBERRY_JAM_BUCKET;

    public static FlowableFluid STILL_MOLTEN_MITHRIL;
    public static FlowableFluid FLOWING_MOLTEN_MITHRIL;
    public static Block MOLTEN_MITHRIL_BLOCK;
    public static Item MOLTEN_MITHRIL_BUCKET;

    public static void registerModFluids() {

        STILL_BOILING_WATER = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "boiling_water"), new BoilingWaterFluid.Still());
        FLOWING_BOILING_WATER = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_boiling_water"), new BoilingWaterFluid.Flowing());
        BOILING_WATER_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "boiling_water_block"), new BoilingWaterFluidBlock(ModFluids.STILL_BOILING_WATER, FabricBlockSettings.copyOf(Blocks.WATER)) {
        });
        BOILING_WATER_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "boiling_water_bucket"), new CauldronBucketItem(ModFluids.STILL_BOILING_WATER, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        STILL_SPEZI = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "spezi"), new SpeziFluid.Still());
        FLOWING_SPEZI = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_spezi"), new SpeziFluid.Flowing());
        SPEZI_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "spezi_block"), new FluidBlock(ModFluids.STILL_SPEZI, FabricBlockSettings.copyOf(Blocks.WATER)) {
        });
        SPEZI_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "spezi_bucket"), new ModBucketItem(ModFluids.STILL_SPEZI, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        STILL_BEER = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "beer"), new BeerFluid.Still());
        FLOWING_BEER = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_beer"), new BeerFluid.Flowing());
        BEER_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "beer_block"), new FluidBlock(ModFluids.STILL_BEER, FabricBlockSettings.copyOf(Blocks.WATER)) {
        });
        BEER_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "beer_bucket"), new CauldronBucketItem(ModFluids.STILL_BEER, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        STILL_MEAD = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "mead"), new MeadFluid.Still());
        FLOWING_MEAD = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_mead"), new MeadFluid.Flowing());
        MEAD_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "mead_block"), new FluidBlock(ModFluids.STILL_MEAD, FabricBlockSettings.copyOf(Blocks.WATER)) {
        });
        MEAD_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "mead_bucket"), new CauldronBucketItem(ModFluids.STILL_MEAD, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        STILL_HOT_MILK = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "hot_milk"), new HotMilkFluid.Still());
        FLOWING_HOT_MILK = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_hot_milk"), new HotMilkFluid.Flowing());
        HOT_MILK_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "hot_milk_block"), new FluidBlock(ModFluids.STILL_HOT_MILK, FabricBlockSettings.copyOf(Blocks.WATER)) {
        });
        HOT_MILK_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "hot_milk_bucket"), new CauldronBucketItem(ModFluids.STILL_HOT_MILK, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        STILL_HOT_CHOCOLATE = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "hot_chocolate"), new HotChocolateFluid.Still());
        FLOWING_HOT_CHOCOLATE = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_hot_chocolate"), new HotChocolateFluid.Flowing());
        HOT_CHOCOLATE_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "hot_chocolate_block"), new FluidBlock(ModFluids.STILL_HOT_CHOCOLATE, FabricBlockSettings.copyOf(Blocks.WATER)) {
        });
        HOT_CHOCOLATE_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "hot_chocolate_bucket"), new CauldronBucketItem(ModFluids.STILL_HOT_CHOCOLATE, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        STILL_HOT_COFFEE = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "hot_coffee"), new HotCoffeeFluid.Still());
        FLOWING_HOT_COFFEE = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_hot_coffee"), new HotCoffeeFluid.Flowing());
        HOT_COFFEE_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "hot_coffee_block"), new FluidBlock(ModFluids.STILL_HOT_COFFEE, FabricBlockSettings.copyOf(Blocks.WATER)) {
        });
        HOT_COFFEE_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "hot_coffee_bucket"), new CauldronBucketItem(ModFluids.STILL_HOT_COFFEE, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        STILL_OIL = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "oil"), new OilFluid.Still());
        FLOWING_OIL = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_oil"), new OilFluid.Flowing());
        OIL_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "oil_block"), new FluidBlock(ModFluids.STILL_OIL, FabricBlockSettings.copyOf(Blocks.WATER)) {
        });
        OIL_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "oil_bucket"), new BucketItem(ModFluids.STILL_OIL, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        STILL_BLOOD = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "blood"), new BloodFluid.Still());
        FLOWING_BLOOD = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_blood"), new BloodFluid.Flowing());
        BLOOD_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "blood_block"), new FluidBlock(ModFluids.STILL_BLOOD, FabricBlockSettings.copyOf(Blocks.WATER)) {
        });
        BLOOD_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "blood_bucket"), new CauldronBucketItem(ModFluids.STILL_BLOOD, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        STILL_CHEMICAL_WASTE = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "chemical_waste"), new ChemicalWasteFluid.Still());
        FLOWING_CHEMICAL_WASTE = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_chemical_waste"), new ChemicalWasteFluid.Flowing());
        CHEMICAL_WASTE_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "chemical_waste_block"), new ChemicalWasteFluidBlock(ModFluids.STILL_CHEMICAL_WASTE, FabricBlockSettings.copyOf(Blocks.LAVA)) {
        });
        CHEMICAL_WASTE_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "chemical_waste_bucket"), new ChemicalWasteBucketItem(ModFluids.STILL_CHEMICAL_WASTE, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        STILL_SWEET_BERRY_JAM = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "sweet_berry_jam"), new SweetBerryJamFluid.Still());
        FLOWING_SWEET_BERRY_JAM = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_sweet_berry_jam"), new SweetBerryJamFluid.Flowing());
        SWEET_BERRY_JAM_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "sweet_berry_jam_block"), new FluidBlock(ModFluids.STILL_SWEET_BERRY_JAM, FabricBlockSettings.copyOf(Blocks.WATER)) {
        });
        SWEET_BERRY_JAM_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "sweet_berry_jam_bucket"), new ModBucketItem(ModFluids.STILL_SWEET_BERRY_JAM, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        STILL_GLOW_BERRY_JAM = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "glow_berry_jam"), new GlowBerryJamFluid.Still());
        FLOWING_GLOW_BERRY_JAM = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_glow_berry_jam"), new GlowBerryJamFluid.Flowing());
        GLOW_BERRY_JAM_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "glow_berry_jam_block"), new FluidBlock(ModFluids.STILL_GLOW_BERRY_JAM, FabricBlockSettings.copyOf(Blocks.WATER)) {
        });
        GLOW_BERRY_JAM_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "glow_berry_jam_bucket"), new ModBucketItem(ModFluids.STILL_GLOW_BERRY_JAM, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        STILL_STRAWBERRY_JAM = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "strawberry_jam"), new StrawberryJamFluid.Still());
        FLOWING_STRAWBERRY_JAM = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_strawberry_jam"), new StrawberryJamFluid.Flowing());
        STRAWBERRY_JAM_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "strawberry_jam_block"), new FluidBlock(ModFluids.STILL_STRAWBERRY_JAM, FabricBlockSettings.copyOf(Blocks.WATER)) {
        });
        STRAWBERRY_JAM_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "strawberry_jam_bucket"), new ModBucketItem(ModFluids.STILL_STRAWBERRY_JAM, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        STILL_ORANGE_MARMELADE = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "orange_marmelade"), new OrangeMarmeladeFluid.Still());
        FLOWING_ORANGE_MARMELADE = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_orange_marmelade"), new OrangeMarmeladeFluid.Flowing());
        ORANGE_MARMELADE_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "orange_marmelade_block"), new FluidBlock(ModFluids.STILL_ORANGE_MARMELADE, FabricBlockSettings.copyOf(Blocks.WATER)) {
        });
        ORANGE_MARMELADE_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "orange_marmelade_bucket"), new ModBucketItem(ModFluids.STILL_ORANGE_MARMELADE, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        STILL_BLUE_BERRY_JAM = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "blue_berry_jam"), new BlueBerryJamFluid.Still());
        FLOWING_BLUE_BERRY_JAM = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_blue_berry_jam"), new BlueBerryJamFluid.Flowing());
        BLUE_BERRY_JAM_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "blue_berry_jam_block"), new FluidBlock(ModFluids.STILL_BLUE_BERRY_JAM, FabricBlockSettings.copyOf(Blocks.WATER)) {
        });
        BLUE_BERRY_JAM_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "blue_berry_jam_bucket"), new ModBucketItem(ModFluids.STILL_BLUE_BERRY_JAM, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        STILL_DROOPFRUIT_JAM = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "droopfruit_jam"), new DroopfruitJamFluid.Still());
        FLOWING_DROOPFRUIT_JAM = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_droopfruit_jam"), new DroopfruitJamFluid.Flowing());
        DROOPFRUIT_JAM_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "droopfruit_jam_block"), new FluidBlock(ModFluids.STILL_DROOPFRUIT_JAM, FabricBlockSettings.copyOf(Blocks.WATER)) {
        });
        DROOPFRUIT_JAM_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "droopfruit_jam_bucket"), new ModBucketItem(ModFluids.STILL_DROOPFRUIT_JAM, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        STILL_ANCIENT_SOULBERRY_JAM = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "ancient_soulberry_jam"), new AncientSoulberryJamFluid.Still());
        FLOWING_ANCIENT_SOULBERRY_JAM = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_ancient_soulberry_jam"), new AncientSoulberryJamFluid.Flowing());
        ANCIENT_SOULBERRY_JAM_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "ancient_soulberry_jam_block"), new FluidBlock(ModFluids.STILL_ANCIENT_SOULBERRY_JAM, FabricBlockSettings.copyOf(Blocks.WATER)) {
        });
        ANCIENT_SOULBERRY_JAM_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "ancient_soulberry_jam_bucket"), new ModBucketItem(ModFluids.STILL_ANCIENT_SOULBERRY_JAM, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        STILL_MOLTEN_MITHRIL = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "molten_mithril"), new MoltenMithrilFluid.Still());
        FLOWING_MOLTEN_MITHRIL = Registry.register(Registries.FLUID, new Identifier(TLOTD.MOD_ID, "flowing_molten_mithril"), new MoltenMithrilFluid.Flowing());
        MOLTEN_MITHRIL_BLOCK = Registry.register(Registries.BLOCK, new Identifier(TLOTD.MOD_ID, "molten_mithril_block"), new FluidBlock(ModFluids.STILL_MOLTEN_MITHRIL, FabricBlockSettings.copyOf(Blocks.LAVA)) {
        });
        MOLTEN_MITHRIL_BUCKET = Registry.register(Registries.ITEM, new Identifier(TLOTD.MOD_ID, "molten_mithril_bucket"), new ModBucketItem(ModFluids.STILL_MOLTEN_MITHRIL, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        CauldronFluidContent.registerCauldron(ModBlocks.BEER_CAULDRON, ModFluids.STILL_BEER, FluidConstants.BOTTLE, LeveledCauldronBlock.LEVEL);
        CauldronFluidContent.registerCauldron(ModBlocks.MEAD_CAULDRON, ModFluids.STILL_MEAD, FluidConstants.BOTTLE, LeveledCauldronBlock.LEVEL);
        CauldronFluidContent.registerCauldron(ModBlocks.BLOOD_CAULDRON, ModFluids.STILL_BLOOD, FluidConstants.BOTTLE, LeveledCauldronBlock.LEVEL);
        CauldronFluidContent.registerCauldron(ModBlocks.BOILING_WATER_CAULDRON, ModFluids.STILL_BOILING_WATER, FluidConstants.BOTTLE, LeveledCauldronBlock.LEVEL);
        CauldronFluidContent.registerCauldron(ModBlocks.HOT_COFFEE_CAULDRON, ModFluids.STILL_HOT_COFFEE, FluidConstants.BOTTLE, LeveledCauldronBlock.LEVEL);
        CauldronFluidContent.registerCauldron(ModBlocks.HOT_MILK_CAULDRON, ModFluids.STILL_HOT_MILK, FluidConstants.BOTTLE, LeveledCauldronBlock.LEVEL);
        CauldronFluidContent.registerCauldron(ModBlocks.HOT_CHOCOLATE_CAULDRON, ModFluids.STILL_HOT_CHOCOLATE, FluidConstants.BOTTLE, LeveledCauldronBlock.LEVEL);

        TLOTD.LOGGER.info("Registering ModFluids for " + TLOTD.MOD_ID);
    }
}