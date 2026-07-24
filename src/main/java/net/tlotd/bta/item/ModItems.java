package net.tlotd.bta.item;

import net.minecraft.core.enums.HumanArmorShape;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemSoup;
import net.minecraft.core.item.material.ArmorMaterial;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.*;
import net.tlotd.bta.TLOTD;
import net.tlotd.bta.item.custom.ExtractionPickaxeItem;
import net.tlotd.bta.item.custom.ItemToolPaxel;
import turniplabs.halplibe.helper.ArmorHelper;
import turniplabs.halplibe.helper.ItemBuilder;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryCategory;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;

public class ModItems {

	public static int itemId = TLOTD.CFG.getInt("IDs.starting_item_id");

	public static Item STEEL_ROD;
	public static Item FOSSILIZED_BONE;
	public static Item REINFORCED_TOOL_ROD;
	public static Item FANCY_TOOL_ROD;

	public static Item HELIORITE_COMB;
	public static Item HELIORITE_NUGGET;
	public static Item HELIORITE_INGOT;
	public static Item HELIORITE_SWORD;
	public static Item HELIORITE_PICKAXE;
	public static Item HELIORITE_AXE;
	public static Item HELIORITE_SHOVEL;
	public static Item HELIORITE_HOE;
	public static Item HELIORITE_PAXEL;
	public static Item HELIORITE_HELMET;
	public static Item HELIORITE_CHESTPLATE;
	public static Item HELIORITE_LEGGINGS;
	public static Item HELIORITE_BOOTS;

	public static Item ENDURIUM_CRYSTAL;
	public static Item ENDURIUM_NUGGET;
	public static Item ENDURIUM_INGOT;
	public static Item ENDURIUM_SWORD;
	public static Item ENDURIUM_PICKAXE;
	public static Item ENDURIUM_AXE;
	public static Item ENDURIUM_SHOVEL;
	public static Item ENDURIUM_HOE;
	public static Item ENDURIUM_PAXEL;
	public static Item ENDURIUM_HELMET;
	public static Item ENDURIUM_CHESTPLATE;
	public static Item ENDURIUM_LEGGINGS;
	public static Item ENDURIUM_BOOTS;

	public static Item RAW_PALLADIUM;
	public static Item PALLADIUM_NUGGET;
	public static Item PALLADIUM_INGOT;
	public static Item PALLADIUM_SWORD;
	public static Item PALLADIUM_PICKAXE;
	public static Item PALLADIUM_AXE;
	public static Item PALLADIUM_SHOVEL;
	public static Item PALLADIUM_HOE;
	public static Item PALLADIUM_PAXEL;
	public static Item PALLADIUM_HELMET;
	public static Item PALLADIUM_CHESTPLATE;
	public static Item PALLADIUM_LEGGINGS;
	public static Item PALLADIUM_BOOTS;

	public static Item JURASSOLINE_CRYSTAL;
	public static Item JURASSOLINE_NUGGET;
	public static Item JURASSOLINE_INGOT;
	public static Item JURASSOLINE_SWORD;
	public static Item JURASSOLINE_PICKAXE;
	public static Item JURASSOLINE_AXE;
	public static Item JURASSOLINE_SHOVEL;
	public static Item JURASSOLINE_HOE;
	public static Item JURASSOLINE_PAXEL;
	public static Item JURASSOLINE_HELMET;
	public static Item JURASSOLINE_CHESTPLATE;
	public static Item JURASSOLINE_LEGGINGS;
	public static Item JURASSOLINE_BOOTS;

	public static Item CINNABAR_CRYSTAL;
	public static Item CINNABAR_NUGGET;
	public static Item CINNABAR_INGOT;
	public static Item CINNABAR_SWORD;
	public static Item CINNABAR_PICKAXE;
	public static Item CINNABAR_AXE;
	public static Item CINNABAR_SHOVEL;
	public static Item CINNABAR_HOE;
	public static Item CINNABAR_PAXEL;
	public static Item CINNABAR_HELMET;
	public static Item CINNABAR_CHESTPLATE;
	public static Item CINNABAR_LEGGINGS;
	public static Item CINNABAR_BOOTS;

	public static Item NEBULAR_CRYSTAL;
	public static Item NEBULAR_NUGGET;
	public static Item NEBULAR_INGOT;
	public static Item NEBULAR_SWORD;
	public static Item NEBULAR_PICKAXE;
	public static Item NEBULAR_AXE;
	public static Item NEBULAR_SHOVEL;
	public static Item NEBULAR_HOE;
	public static Item NEBULAR_PAXEL;
	public static Item NEBULAR_HELMET;
	public static Item NEBULAR_CHESTPLATE;
	public static Item NEBULAR_LEGGINGS;
	public static Item NEBULAR_BOOTS;

	public static Item RAW_MITHRIL;
	public static Item MITHRIL_NUGGET;
	public static Item MITHRIL_INGOT;
	public static Item MITHRIL_SWORD;
	public static Item MITHRIL_PICKAXE;
	public static Item MITHRIL_AXE;
	public static Item MITHRIL_SHOVEL;
	public static Item MITHRIL_HOE;
	public static Item MITHRIL_PAXEL;
	public static Item MITHRIL_HELMET;
	public static Item MITHRIL_CHESTPLATE;
	public static Item MITHRIL_LEGGINGS;
	public static Item MITHRIL_BOOTS;

	public static Item NARSIL_HANDLE;
	public static Item ANDURIL;

	public static Item FLOUR;
	public static Item BREADCRUMBS;
	public static Item RAW_SCHNITZEL;
	public static Item SCHNITZEL;
	public static Item MAULTASCHE;
	public static Item MAULTASCHEN_BROTH;

	public static Item PRESERVES_JAR;
	public static Item CHERRY_JAM_JAR;

	public static Item TOAST;
	public static Item CHERRY_JAM_TOAST;

	public static ToolMaterial HELIORITE = (new ToolMaterial()).setDurability(2324).setEfficiency(16.0F, 64.0F).setMiningLevel(4).setDamage(6).setBlockHitDelay(4);
	public static ArmorMaterial HELIORITE_ARMOR = ArmorHelper.createArmorMaterial(TLOTD.MOD_ID, "heliorite_layer", 2324, 90f, 50f, 55f, 60f);
	public static ToolMaterial ENDURIUM = (new ToolMaterial()).setDurability(2580).setEfficiency(16.0F, 64.0F).setMiningLevel(4).setDamage(6).setBlockHitDelay(4);
	public static ArmorMaterial ENDURIUM_ARMOR = ArmorHelper.createArmorMaterial(TLOTD.MOD_ID, "endurium_layer", 2580, 90f, 50f, 55f, 60f);
	public static ToolMaterial PALLADIUM = (new ToolMaterial()).setDurability(2484).setEfficiency(16.0F, 64.0F).setMiningLevel(5).setDamage(7).setBlockHitDelay(4);
	public static ArmorMaterial PALLADIUM_ARMOR = ArmorHelper.createArmorMaterial(TLOTD.MOD_ID, "palladium_layer", 2484, 100f, 60f, 65f, 70f);
	public static ToolMaterial JURASSOLINE = (new ToolMaterial()).setDurability(2612).setEfficiency(16.0F, 64.0F).setMiningLevel(5).setDamage(7).setBlockHitDelay(4);
	public static ArmorMaterial JURASSOLINE_ARMOR = ArmorHelper.createArmorMaterial(TLOTD.MOD_ID, "jurassoline_layer", 2612, 100f, 60f, 65f, 70f);
	public static ToolMaterial CINNABAR = (new ToolMaterial()).setDurability(3162).setEfficiency(16.0F, 64.0F).setMiningLevel(6).setDamage(8).setBlockHitDelay(4);
	public static ArmorMaterial CINNABAR_ARMOR = ArmorHelper.createArmorMaterial(TLOTD.MOD_ID, "cinnabar_layer", 3162, 110f, 70f, 75f, 80f);
	public static ToolMaterial NEBULAR = (new ToolMaterial()).setDurability(3418).setEfficiency(16.0F, 64.0F).setMiningLevel(6).setDamage(8).setBlockHitDelay(4);
	public static ArmorMaterial NEBULAR_ARMOR = ArmorHelper.createArmorMaterial(TLOTD.MOD_ID, "nebular_layer", 3418, 110f, 70f, 75f, 80f);
	public static ToolMaterial MITHRIL = (new ToolMaterial()).setDurability(4096).setEfficiency(16.0F, 64.0F).setMiningLevel(7).setDamage(9).setBlockHitDelay(4);
	public static ArmorMaterial MITHRIL_ARMOR = ArmorHelper.createArmorMaterial(TLOTD.MOD_ID, "mithril_layer", 4096, 125f, 80f, 100f, 90f);

	public void registerItems(){

		STEEL_ROD = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("steel_rod", "tlotd:item/steel_rod", itemId++));
		FOSSILIZED_BONE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("fossilized_bone", "tlotd:item/fossilized_bone", itemId++));
		REINFORCED_TOOL_ROD = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("reinforced_tool_rod", "tlotd:item/reinforced_tool_rod", itemId++));
		FANCY_TOOL_ROD = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("fancy_tool_rod", "tlotd:item/fancy_tool_rod", itemId++));

		HELIORITE_COMB = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("heliorite_comb", "tlotd:item/heliorite_comb", itemId++));
		HELIORITE_NUGGET = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("heliorite_nugget", "tlotd:item/heliorite_nugget", itemId++));
		HELIORITE_INGOT = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("heliorite_ingot", "tlotd:item/heliorite_ingot", itemId++));
		HELIORITE_SWORD = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolSword("heliorite_sword", "tlotd:item/heliorite_sword", itemId++, HELIORITE));
		HELIORITE_PICKAXE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolPickaxe("heliorite_pickaxe", "tlotd:item/heliorite_pickaxe", itemId++, HELIORITE));
		HELIORITE_AXE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolAxe("heliorite_axe", "tlotd:item/heliorite_axe", itemId++, HELIORITE));
		HELIORITE_SHOVEL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolShovel("heliorite_shovel", "tlotd:item/heliorite_shovel", itemId++, HELIORITE));
		HELIORITE_HOE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolHoe("heliorite_hoe", "tlotd:item/heliorite_hoe", itemId++, HELIORITE));
		HELIORITE_PAXEL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolPaxel("heliorite_paxel", "tlotd:item/heliorite_paxel", itemId++, HELIORITE));
		HELIORITE_HELMET = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("heliorite_helmet", "tlotd:item/heliorite_helmet", itemId++, HELIORITE_ARMOR, HumanArmorShape.HEAD));
		HELIORITE_CHESTPLATE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("heliorite_chestplate", "tlotd:item/heliorite_chestplate", itemId++, HELIORITE_ARMOR, HumanArmorShape.CHEST));
		HELIORITE_LEGGINGS = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("heliorite_leggings", "tlotd:item/heliorite_leggings", itemId++, HELIORITE_ARMOR, HumanArmorShape.LEGS));
		HELIORITE_BOOTS = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("heliorite_boots", "tlotd:item/heliorite_boots", itemId++, HELIORITE_ARMOR, HumanArmorShape.BOOTS));

		ENDURIUM_CRYSTAL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("endurium_crystal", "tlotd:item/endurium_crystal", itemId++));
		ENDURIUM_NUGGET = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("endurium_nugget", "tlotd:item/endurium_nugget", itemId++));
		ENDURIUM_INGOT = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("endurium_ingot", "tlotd:item/endurium_ingot", itemId++));
		ENDURIUM_SWORD = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolSword("endurium_sword", "tlotd:item/endurium_sword", itemId++, ENDURIUM));
		ENDURIUM_PICKAXE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolPickaxe("endurium_pickaxe", "tlotd:item/endurium_pickaxe", itemId++, ENDURIUM));
		ENDURIUM_AXE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolAxe("endurium_axe", "tlotd:item/endurium_axe", itemId++, ENDURIUM));
		ENDURIUM_SHOVEL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolShovel("endurium_shovel", "tlotd:item/endurium_shovel", itemId++, ENDURIUM));
		ENDURIUM_HOE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolHoe("endurium_hoe", "tlotd:item/endurium_hoe", itemId++, ENDURIUM));
		ENDURIUM_PAXEL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolPaxel("endurium_paxel", "tlotd:item/endurium_paxel", itemId++, ENDURIUM));
		ENDURIUM_HELMET = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("endurium_helmet", "tlotd:item/endurium_helmet", itemId++, ENDURIUM_ARMOR, HumanArmorShape.HEAD));
		ENDURIUM_CHESTPLATE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("endurium_chestplate", "tlotd:item/endurium_chestplate", itemId++, ENDURIUM_ARMOR, HumanArmorShape.CHEST));
		ENDURIUM_LEGGINGS = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("endurium_leggings", "tlotd:item/endurium_leggings", itemId++, ENDURIUM_ARMOR, HumanArmorShape.LEGS));
		ENDURIUM_BOOTS = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("endurium_boots", "tlotd:item/endurium_boots", itemId++, ENDURIUM_ARMOR, HumanArmorShape.BOOTS));

		RAW_PALLADIUM = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("raw_palladium", "tlotd:item/raw_palladium", itemId++));
		PALLADIUM_NUGGET = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("palladium_nugget", "tlotd:item/palladium_nugget", itemId++));
		PALLADIUM_INGOT = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("palladium_ingot", "tlotd:item/palladium_ingot", itemId++));
		PALLADIUM_SWORD = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolSword("palladium_sword", "tlotd:item/palladium_sword", itemId++, PALLADIUM));
		PALLADIUM_PICKAXE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolPickaxe("palladium_pickaxe", "tlotd:item/palladium_pickaxe", itemId++, PALLADIUM));
		PALLADIUM_AXE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolAxe("palladium_axe", "tlotd:item/palladium_axe", itemId++, PALLADIUM));
		PALLADIUM_SHOVEL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolShovel("palladium_shovel", "tlotd:item/palladium_shovel", itemId++, PALLADIUM));
		PALLADIUM_HOE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolHoe("palladium_hoe", "tlotd:item/palladium_hoe", itemId++, PALLADIUM));
		PALLADIUM_PAXEL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolPaxel("palladium_paxel", "tlotd:item/palladium_paxel", itemId++, PALLADIUM));
		PALLADIUM_HELMET = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("palladium_helmet", "tlotd:item/palladium_helmet", itemId++, PALLADIUM_ARMOR, HumanArmorShape.HEAD));
		PALLADIUM_CHESTPLATE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("palladium_chestplate", "tlotd:item/palladium_chestplate", itemId++, PALLADIUM_ARMOR, HumanArmorShape.CHEST));
		PALLADIUM_LEGGINGS = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("palladium_leggings", "tlotd:item/palladium_leggings", itemId++, PALLADIUM_ARMOR, HumanArmorShape.LEGS));
		PALLADIUM_BOOTS = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("palladium_boots", "tlotd:item/palladium_boots", itemId++, PALLADIUM_ARMOR, HumanArmorShape.BOOTS));

		JURASSOLINE_CRYSTAL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("jurassoline_crystal", "tlotd:item/jurassoline_crystal", itemId++));
		JURASSOLINE_NUGGET = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("jurassoline_nugget", "tlotd:item/jurassoline_nugget", itemId++));
		JURASSOLINE_INGOT = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("jurassoline_ingot", "tlotd:item/jurassoline_ingot", itemId++));
		JURASSOLINE_SWORD = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolSword("jurassoline_sword", "tlotd:item/jurassoline_sword", itemId++, JURASSOLINE));
		JURASSOLINE_PICKAXE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolPickaxe("jurassoline_pickaxe", "tlotd:item/jurassoline_pickaxe", itemId++, JURASSOLINE));
		JURASSOLINE_AXE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolAxe("jurassoline_axe", "tlotd:item/jurassoline_axe", itemId++, JURASSOLINE));
		JURASSOLINE_SHOVEL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolShovel("jurassoline_shovel", "tlotd:item/jurassoline_shovel", itemId++, JURASSOLINE));
		JURASSOLINE_HOE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolHoe("jurassoline_hoe", "tlotd:item/jurassoline_hoe", itemId++, JURASSOLINE));
		JURASSOLINE_PAXEL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolPaxel("jurassoline_paxel", "tlotd:item/jurassoline_paxel", itemId++, JURASSOLINE));
		JURASSOLINE_HELMET = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("jurassoline_helmet", "tlotd:item/jurassoline_helmet", itemId++, JURASSOLINE_ARMOR, HumanArmorShape.HEAD));
		JURASSOLINE_CHESTPLATE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("jurassoline_chestplate", "tlotd:item/jurassoline_chestplate", itemId++, JURASSOLINE_ARMOR, HumanArmorShape.CHEST));
		JURASSOLINE_LEGGINGS = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("jurassoline_leggings", "tlotd:item/jurassoline_leggings", itemId++, JURASSOLINE_ARMOR, HumanArmorShape.LEGS));
		JURASSOLINE_BOOTS = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("jurassoline_boots", "tlotd:item/jurassoline_boots", itemId++, JURASSOLINE_ARMOR, HumanArmorShape.BOOTS));

		CINNABAR_CRYSTAL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("cinnabar_crystal", "tlotd:item/cinnabar_crystal", itemId++));
		CINNABAR_NUGGET = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("cinnabar_nugget", "tlotd:item/cinnabar_nugget", itemId++));
		CINNABAR_INGOT = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("cinnabar_ingot", "tlotd:item/cinnabar_ingot", itemId++));
		CINNABAR_SWORD = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolSword("cinnabar_sword", "tlotd:item/cinnabar_sword", itemId++, CINNABAR));
		CINNABAR_PICKAXE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ExtractionPickaxeItem("cinnabar_pickaxe", "tlotd:item/cinnabar_pickaxe", itemId++, CINNABAR));
		CINNABAR_AXE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolAxe("cinnabar_axe", "tlotd:item/cinnabar_axe", itemId++, CINNABAR));
		CINNABAR_SHOVEL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolShovel("cinnabar_shovel", "tlotd:item/cinnabar_shovel", itemId++, CINNABAR));
		CINNABAR_HOE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolHoe("cinnabar_hoe", "tlotd:item/cinnabar_hoe", itemId++, CINNABAR));
		CINNABAR_PAXEL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolPaxel("cinnabar_paxel", "tlotd:item/cinnabar_paxel", itemId++, CINNABAR));
		CINNABAR_HELMET = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("cinnabar_helmet", "tlotd:item/cinnabar_helmet", itemId++, CINNABAR_ARMOR, HumanArmorShape.HEAD));
		CINNABAR_CHESTPLATE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("cinnabar_chestplate", "tlotd:item/cinnabar_chestplate", itemId++, CINNABAR_ARMOR, HumanArmorShape.CHEST));
		CINNABAR_LEGGINGS = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("cinnabar_leggings", "tlotd:item/cinnabar_leggings", itemId++, CINNABAR_ARMOR, HumanArmorShape.LEGS));
		CINNABAR_BOOTS = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("cinnabar_boots", "tlotd:item/cinnabar_boots", itemId++, CINNABAR_ARMOR, HumanArmorShape.BOOTS));

		NEBULAR_CRYSTAL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("nebular_crystal", "tlotd:item/nebular_crystal", itemId++));
		NEBULAR_NUGGET = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("nebular_nugget", "tlotd:item/nebular_nugget", itemId++));
		NEBULAR_INGOT = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("nebular_ingot", "tlotd:item/nebular_ingot", itemId++));
		NEBULAR_SWORD = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolSword("nebular_sword", "tlotd:item/nebular_sword", itemId++, NEBULAR));
		NEBULAR_PICKAXE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ExtractionPickaxeItem("nebular_pickaxe", "tlotd:item/nebular_pickaxe", itemId++, NEBULAR));
		NEBULAR_AXE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolAxe("nebular_axe", "tlotd:item/nebular_axe", itemId++, NEBULAR));
		NEBULAR_SHOVEL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolShovel("nebular_shovel", "tlotd:item/nebular_shovel", itemId++, NEBULAR));
		NEBULAR_HOE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolHoe("nebular_hoe", "tlotd:item/nebular_hoe", itemId++, NEBULAR));
		NEBULAR_PAXEL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolPaxel("nebular_paxel", "tlotd:item/nebular_paxel", itemId++, NEBULAR));
		NEBULAR_HELMET = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("nebular_helmet", "tlotd:item/nebular_helmet", itemId++, NEBULAR_ARMOR, HumanArmorShape.HEAD));
		NEBULAR_CHESTPLATE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("nebular_chestplate", "tlotd:item/nebular_chestplate", itemId++, NEBULAR_ARMOR, HumanArmorShape.CHEST));
		NEBULAR_LEGGINGS = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("nebular_leggings", "tlotd:item/nebular_leggings", itemId++, NEBULAR_ARMOR, HumanArmorShape.LEGS));
		NEBULAR_BOOTS = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("nebular_boots", "tlotd:item/nebular_boots", itemId++, NEBULAR_ARMOR, HumanArmorShape.BOOTS));

		RAW_MITHRIL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("raw_mithril", "tlotd:item/raw_mithril", itemId++));
		MITHRIL_NUGGET = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("mithril_nugget", "tlotd:item/mithril_nugget", itemId++));
		MITHRIL_INGOT = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("mithril_ingot", "tlotd:item/mithril_ingot", itemId++));
		MITHRIL_SWORD = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolSword("mithril_sword", "tlotd:item/mithril_sword", itemId++, MITHRIL));
		MITHRIL_PICKAXE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ExtractionPickaxeItem("mithril_pickaxe", "tlotd:item/mithril_pickaxe", itemId++, MITHRIL));
		MITHRIL_AXE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolAxe("mithril_axe", "tlotd:item/mithril_axe", itemId++, MITHRIL));
		MITHRIL_SHOVEL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolShovel("mithril_shovel", "tlotd:item/mithril_shovel", itemId++, MITHRIL));
		MITHRIL_HOE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolHoe("mithril_hoe", "tlotd:item/mithril_hoe", itemId++, MITHRIL));
		MITHRIL_PAXEL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolPaxel("mithril_paxel", "tlotd:item/mithril_paxel", itemId++, MITHRIL));
		MITHRIL_HELMET = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("mithril_helmet", "tlotd:item/mithril_helmet", itemId++, MITHRIL_ARMOR, HumanArmorShape.HEAD));
		MITHRIL_CHESTPLATE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("mithril_chestplate", "tlotd:item/mithril_chestplate", itemId++, MITHRIL_ARMOR, HumanArmorShape.CHEST));
		MITHRIL_LEGGINGS = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("mithril_leggings", "tlotd:item/mithril_leggings", itemId++, MITHRIL_ARMOR, HumanArmorShape.LEGS));
		MITHRIL_BOOTS = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemArmor("mithril_boots", "tlotd:item/mithril_boots", itemId++, MITHRIL_ARMOR, HumanArmorShape.BOOTS));

		NARSIL_HANDLE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolSword("narsil_handle", "tlotd:item/narsil_handle", itemId++, MITHRIL));
		ANDURIL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemToolSword("anduril", "tlotd:item/anduril", itemId++, MITHRIL));

		FLOUR = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("flour", "tlotd:item/flour", itemId++));
		BREADCRUMBS = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("breadcrumbs", "tlotd:item/breadcrumbs", itemId++));
		RAW_SCHNITZEL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemFood("raw_schnitzel", "tlotd:item/raw_schnitzel", itemId++,4, 16, true, 8));
		SCHNITZEL = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemFood("schnitzel", "tlotd:item/schnitzel", itemId++, 10, 16, true, 8));
		MAULTASCHE = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemFood("maultasche", "tlotd:item/maultasche", itemId++, 4, 16, true, 9));
		MAULTASCHEN_BROTH = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemSoup("maultaschen_broth", "tlotd:item/maultaschen_broth", itemId++,16,16));

		PRESERVES_JAR = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("preserves_jar", "tlotd:item/preserves_jar", itemId++));
		CHERRY_JAM_JAR = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new Item("cherry_jam_jar", "tlotd:item/cherry_jam_jar", itemId++).setMaxStackSize(1).setContainerItem(PRESERVES_JAR));

		TOAST = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemFood("toast", "tlotd:item/toast", itemId++, 2, 12, false, 16));
		CHERRY_JAM_TOAST = new ItemBuilder(TLOTD.MOD_ID).setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.MISCELLANEOUS)).build(new ItemFood("cherry_jam_toast", "tlotd:item/cherry_jam_toast", itemId++, 4, 12, false, 16));
	}
}
