package net.tlotd.datagen;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class ModRecipeProvider implements RecipeEntrypoint {
	@Override
	public void initNamespaces() {
		RecipeBuilder.initNameSpace(TLOTD.MOD_ID);
		RecipeBuilder.getRecipeNamespace(TLOTD.MOD_ID);
	}

	@Override
	public void onRecipesReady() {
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("#", "#")
			.addInput('#', Items.INGOT_STEEL)
			.create("steel_rod", new ItemStack(ModItems.STEEL_ROD));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.STEEL_ROD)
			.addInput(Items.NETHERCOAL)
			.create("reinforced_rod", new ItemStack(ModItems.REINFORCED_TOOL_ROD));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(Items.DIAMOND)
			.addInput(ModItems.REINFORCED_TOOL_ROD)
			.addInput(Items.INGOT_GOLD)
			.create("fancy_tool_rod", new ItemStack(ModItems.FANCY_TOOL_ROD));

		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModBlocks.HELIORITE_COMB_BLOCK)
			.create("heliorite_block_to_comb", new ItemStack(ModItems.HELIORITE_COMB,9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.HELIORITE_COMB)
			.addInput(ModItems.HELIORITE_COMB)
			.addInput(ModItems.HELIORITE_COMB)
			.addInput(ModItems.HELIORITE_COMB)
			.addInput(ModItems.HELIORITE_COMB)
			.addInput(ModItems.HELIORITE_COMB)
			.addInput(ModItems.HELIORITE_COMB)
			.addInput(ModItems.HELIORITE_COMB)
			.addInput(ModItems.HELIORITE_COMB)
			.create("heliorite_comb_block", new ItemStack(ModBlocks.HELIORITE_COMB_BLOCK));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.HELIORITE_COMB)
			.addInput(Blocks.OBSIDIAN)
			.create("heliorite_ingot_obsidian", new ItemStack(ModItems.HELIORITE_INGOT));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.HELIORITE_INGOT)
			.create("heliorite_nugget", new ItemStack(ModItems.HELIORITE_NUGGET, 9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.HELIORITE_NUGGET)
			.addInput(ModItems.HELIORITE_NUGGET)
			.addInput(ModItems.HELIORITE_NUGGET)
			.addInput(ModItems.HELIORITE_NUGGET)
			.addInput(ModItems.HELIORITE_NUGGET)
			.addInput(ModItems.HELIORITE_NUGGET)
			.addInput(ModItems.HELIORITE_NUGGET)
			.addInput(ModItems.HELIORITE_NUGGET)
			.addInput(ModItems.HELIORITE_NUGGET)
			.create("heliorite_ingot", new ItemStack(ModItems.HELIORITE_INGOT));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModBlocks.HELIORITE_BLOCK)
			.create("heliorite_block_to_ingot", new ItemStack(ModItems.HELIORITE_INGOT,9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.HELIORITE_INGOT)
			.addInput(ModItems.HELIORITE_INGOT)
			.addInput(ModItems.HELIORITE_INGOT)
			.addInput(ModItems.HELIORITE_INGOT)
			.addInput(ModItems.HELIORITE_INGOT)
			.addInput(ModItems.HELIORITE_INGOT)
			.addInput(ModItems.HELIORITE_INGOT)
			.addInput(ModItems.HELIORITE_INGOT)
			.addInput(ModItems.HELIORITE_INGOT)
			.create("heliorite_block", new ItemStack(ModBlocks.HELIORITE_BLOCK));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("#", "#", "I")
			.addInput('#', ModItems.HELIORITE_INGOT)
			.addInput('I', ModItems.STEEL_ROD)
			.create("heliorite_sword", new ItemStack(ModItems.HELIORITE_SWORD));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", " I ", " I ")
			.addInput('#', ModItems.HELIORITE_INGOT)
			.addInput('I', ModItems.STEEL_ROD)
			.create("heliorite_pickaxe", new ItemStack(ModItems.HELIORITE_PICKAXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("## ", "#I ", " I ")
			.addInput('#', ModItems.HELIORITE_INGOT)
			.addInput('I', ModItems.STEEL_ROD)
			.create("heliorite_axe", new ItemStack(ModItems.HELIORITE_AXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape(" ##", " I#", " I ")
			.addInput('#', ModItems.HELIORITE_INGOT)
			.addInput('I', ModItems.STEEL_ROD)
			.create("heliorite_axe_alt", new ItemStack(ModItems.HELIORITE_AXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("#", "I", "I")
			.addInput('#', ModItems.HELIORITE_INGOT)
			.addInput('I', ModItems.STEEL_ROD)
			.create("heliorite_shovel", new ItemStack(ModItems.HELIORITE_SHOVEL));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("## ", " I ", " I ")
			.addInput('#', ModItems.HELIORITE_INGOT)
			.addInput('I', ModItems.STEEL_ROD)
			.create("heliorite_hoe", new ItemStack(ModItems.HELIORITE_HOE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape(" ##", " I ", " I ")
			.addInput('#', ModItems.HELIORITE_INGOT)
			.addInput('I', ModItems.STEEL_ROD)
			.create("heliorite__hoe_alt", new ItemStack(ModItems.HELIORITE_HOE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", "# #")
			.addInput('#', ModItems.HELIORITE_INGOT)
			.create("heliorite_helmet", new ItemStack(ModItems.HELIORITE_HELMET));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("# #", "###", "###")
			.addInput('#', ModItems.HELIORITE_INGOT)
			.create("heliorite_chestplate", new ItemStack(ModItems.HELIORITE_CHESTPLATE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", "# #", "# #")
			.addInput('#', ModItems.HELIORITE_INGOT)
			.create("heliorite_leggings", new ItemStack(ModItems.HELIORITE_LEGGINGS));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("# #", "# #")
			.addInput('#', ModItems.HELIORITE_INGOT)
			.create("heliorite_boots", new ItemStack(ModItems.HELIORITE_BOOTS));

		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModBlocks.RAW_ENDURIUM_BLOCK)
			.create("raw_endurium_to_crystal", new ItemStack(ModItems.ENDURIUM_CRYSTAL,9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.ENDURIUM_CRYSTAL)
			.addInput(ModItems.ENDURIUM_CRYSTAL)
			.addInput(ModItems.ENDURIUM_CRYSTAL)
			.addInput(ModItems.ENDURIUM_CRYSTAL)
			.addInput(ModItems.ENDURIUM_CRYSTAL)
			.addInput(ModItems.ENDURIUM_CRYSTAL)
			.addInput(ModItems.ENDURIUM_CRYSTAL)
			.addInput(ModItems.ENDURIUM_CRYSTAL)
			.addInput(ModItems.ENDURIUM_CRYSTAL)
			.create("raw_endurium_block", new ItemStack(ModBlocks.RAW_ENDURIUM_BLOCK));
		RecipeBuilder.BlastFurnace(TLOTD.MOD_ID)
			.setInput(ModItems.ENDURIUM_CRYSTAL)
			.create("endurium_crystal_blast_furnace", new ItemStack(ModItems.ENDURIUM_INGOT));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.ENDURIUM_INGOT)
			.create("endurium_nugget", new ItemStack(ModItems.ENDURIUM_NUGGET, 9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.ENDURIUM_NUGGET)
			.addInput(ModItems.ENDURIUM_NUGGET)
			.addInput(ModItems.ENDURIUM_NUGGET)
			.addInput(ModItems.ENDURIUM_NUGGET)
			.addInput(ModItems.ENDURIUM_NUGGET)
			.addInput(ModItems.ENDURIUM_NUGGET)
			.addInput(ModItems.ENDURIUM_NUGGET)
			.addInput(ModItems.ENDURIUM_NUGGET)
			.addInput(ModItems.ENDURIUM_NUGGET)
			.create("endurium_ingot", new ItemStack(ModItems.ENDURIUM_INGOT));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModBlocks.ENDURIUM_BLOCK)
			.create("endurium_block_to_ingot", new ItemStack(ModItems.HELIORITE_INGOT,9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.ENDURIUM_INGOT)
			.addInput(ModItems.ENDURIUM_INGOT)
			.addInput(ModItems.ENDURIUM_INGOT)
			.addInput(ModItems.ENDURIUM_INGOT)
			.addInput(ModItems.ENDURIUM_INGOT)
			.addInput(ModItems.ENDURIUM_INGOT)
			.addInput(ModItems.ENDURIUM_INGOT)
			.addInput(ModItems.ENDURIUM_INGOT)
			.addInput(ModItems.ENDURIUM_INGOT)
			.create("endurium_block", new ItemStack(ModBlocks.ENDURIUM_BLOCK));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("#", "#", "I")
			.addInput('#', ModItems.ENDURIUM_INGOT)
			.addInput('I', ModItems.STEEL_ROD)
			.create("endurium_sword", new ItemStack(ModItems.ENDURIUM_SWORD));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", " I ", " I ")
			.addInput('#', ModItems.ENDURIUM_INGOT)
			.addInput('I', ModItems.STEEL_ROD)
			.create("endurium_pickaxe", new ItemStack(ModItems.ENDURIUM_PICKAXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("## ", "#I ", " I ")
			.addInput('#', ModItems.ENDURIUM_INGOT)
			.addInput('I', ModItems.STEEL_ROD)
			.create("endurium_axe", new ItemStack(ModItems.ENDURIUM_AXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape(" ##", " I#", " I ")
			.addInput('#', ModItems.ENDURIUM_INGOT)
			.addInput('I', ModItems.STEEL_ROD)
			.create("endurium_axe_alt", new ItemStack(ModItems.ENDURIUM_AXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("#", "I", "I")
			.addInput('#', ModItems.ENDURIUM_INGOT)
			.addInput('I', ModItems.STEEL_ROD)
			.create("endurium_shovel", new ItemStack(ModItems.ENDURIUM_SHOVEL));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("## ", " I ", " I ")
			.addInput('#', ModItems.ENDURIUM_INGOT)
			.addInput('I', ModItems.STEEL_ROD)
			.create("endurium_hoe", new ItemStack(ModItems.ENDURIUM_HOE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape(" ##", " I ", " I ")
			.addInput('#', ModItems.ENDURIUM_INGOT)
			.addInput('I', ModItems.STEEL_ROD)
			.create("endurium__hoe_alt", new ItemStack(ModItems.ENDURIUM_HOE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", "# #")
			.addInput('#', ModItems.ENDURIUM_INGOT)
			.create("endurium_helmet", new ItemStack(ModItems.ENDURIUM_HELMET));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("# #", "###", "###")
			.addInput('#', ModItems.ENDURIUM_INGOT)
			.create("endurium_chestplate", new ItemStack(ModItems.ENDURIUM_CHESTPLATE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", "# #", "# #")
			.addInput('#', ModItems.ENDURIUM_INGOT)
			.create("endurium_leggings", new ItemStack(ModItems.ENDURIUM_LEGGINGS));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("# #", "# #")
			.addInput('#', ModItems.ENDURIUM_INGOT)
			.create("endurium_boots", new ItemStack(ModItems.ENDURIUM_BOOTS));

		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModBlocks.RAW_PALLADIUM_BLOCK)
			.create("raw_palladium_to_chunk", new ItemStack(ModItems.RAW_PALLADIUM,9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.RAW_PALLADIUM)
			.addInput(ModItems.RAW_PALLADIUM)
			.addInput(ModItems.RAW_PALLADIUM)
			.addInput(ModItems.RAW_PALLADIUM)
			.addInput(ModItems.RAW_PALLADIUM)
			.addInput(ModItems.RAW_PALLADIUM)
			.addInput(ModItems.RAW_PALLADIUM)
			.addInput(ModItems.RAW_PALLADIUM)
			.addInput(ModItems.RAW_PALLADIUM)
			.create("raw_palladium_block", new ItemStack(ModBlocks.RAW_PALLADIUM_BLOCK));
		RecipeBuilder.BlastFurnace(TLOTD.MOD_ID)
			.setInput(ModItems.RAW_PALLADIUM)
			.create("raw_palladium_blast_furnace", new ItemStack(ModItems.PALLADIUM_INGOT));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.PALLADIUM_INGOT)
			.create("palladium_nugget", new ItemStack(ModItems.PALLADIUM_NUGGET, 9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.PALLADIUM_NUGGET)
			.addInput(ModItems.PALLADIUM_NUGGET)
			.addInput(ModItems.PALLADIUM_NUGGET)
			.addInput(ModItems.PALLADIUM_NUGGET)
			.addInput(ModItems.PALLADIUM_NUGGET)
			.addInput(ModItems.PALLADIUM_NUGGET)
			.addInput(ModItems.PALLADIUM_NUGGET)
			.addInput(ModItems.PALLADIUM_NUGGET)
			.addInput(ModItems.PALLADIUM_NUGGET)
			.create("palladium_ingot", new ItemStack(ModItems.PALLADIUM_INGOT));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModBlocks.PALLADIUM_BLOCK)
			.create("palladium_block_to_ingot", new ItemStack(ModItems.PALLADIUM_INGOT,9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.PALLADIUM_INGOT)
			.addInput(ModItems.PALLADIUM_INGOT)
			.addInput(ModItems.PALLADIUM_INGOT)
			.addInput(ModItems.PALLADIUM_INGOT)
			.addInput(ModItems.PALLADIUM_INGOT)
			.addInput(ModItems.PALLADIUM_INGOT)
			.addInput(ModItems.PALLADIUM_INGOT)
			.addInput(ModItems.PALLADIUM_INGOT)
			.addInput(ModItems.PALLADIUM_INGOT)
			.create("palladium_block", new ItemStack(ModBlocks.PALLADIUM_BLOCK));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("#", "#", "I")
			.addInput('#', ModItems.PALLADIUM_INGOT)
			.addInput('I', ModItems.FOSSILIZED_BONE)
			.create("palladium_sword", new ItemStack(ModItems.PALLADIUM_SWORD));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", " I ", " I ")
			.addInput('#', ModItems.PALLADIUM_INGOT)
			.addInput('I', ModItems.FOSSILIZED_BONE)
			.create("palladium_pickaxe", new ItemStack(ModItems.PALLADIUM_PICKAXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("## ", "#I ", " I ")
			.addInput('#', ModItems.PALLADIUM_INGOT)
			.addInput('I', ModItems.FOSSILIZED_BONE)
			.create("palladium_axe", new ItemStack(ModItems.PALLADIUM_AXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape(" ##", " I#", " I ")
			.addInput('#', ModItems.PALLADIUM_INGOT)
			.addInput('I', ModItems.FOSSILIZED_BONE)
			.create("palladium_axe_alt", new ItemStack(ModItems.PALLADIUM_AXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("#", "I", "I")
			.addInput('#', ModItems.PALLADIUM_INGOT)
			.addInput('I', ModItems.FOSSILIZED_BONE)
			.create("palladium_shovel", new ItemStack(ModItems.PALLADIUM_SHOVEL));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("## ", " I ", " I ")
			.addInput('#', ModItems.PALLADIUM_INGOT)
			.addInput('I', ModItems.FOSSILIZED_BONE)
			.create("palladium_hoe", new ItemStack(ModItems.PALLADIUM_HOE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape(" ##", " I ", " I ")
			.addInput('#', ModItems.PALLADIUM_INGOT)
			.addInput('I', ModItems.FOSSILIZED_BONE)
			.create("palladium__hoe_alt", new ItemStack(ModItems.PALLADIUM_HOE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", "# #")
			.addInput('#', ModItems.PALLADIUM_INGOT)
			.create("palladium_helmet", new ItemStack(ModItems.PALLADIUM_HELMET));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("# #", "###", "###")
			.addInput('#', ModItems.PALLADIUM_INGOT)
			.create("palladium_chestplate", new ItemStack(ModItems.PALLADIUM_CHESTPLATE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", "# #", "# #")
			.addInput('#', ModItems.PALLADIUM_INGOT)
			.create("palladium_leggings", new ItemStack(ModItems.PALLADIUM_LEGGINGS));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("# #", "# #")
			.addInput('#', ModItems.PALLADIUM_INGOT)
			.create("palladium_boots", new ItemStack(ModItems.PALLADIUM_BOOTS));

		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModBlocks.JURASSOLINE_CRYSTAL_BLOCK)
			.create("jurassoline_crystal_block_to_crystal", new ItemStack(ModItems.JURASSOLINE_CRYSTAL,9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.JURASSOLINE_CRYSTAL)
			.addInput(ModItems.JURASSOLINE_CRYSTAL)
			.addInput(ModItems.JURASSOLINE_CRYSTAL)
			.addInput(ModItems.JURASSOLINE_CRYSTAL)
			.addInput(ModItems.JURASSOLINE_CRYSTAL)
			.addInput(ModItems.JURASSOLINE_CRYSTAL)
			.addInput(ModItems.JURASSOLINE_CRYSTAL)
			.addInput(ModItems.JURASSOLINE_CRYSTAL)
			.addInput(ModItems.JURASSOLINE_CRYSTAL)
			.create("jurassoline_crystal_block", new ItemStack(ModBlocks.JURASSOLINE_CRYSTAL_BLOCK));
		RecipeBuilder.BlastFurnace(TLOTD.MOD_ID)
			.setInput(ModItems.JURASSOLINE_CRYSTAL)
			.create("jurassoline_crystal_blast_furnace", new ItemStack(ModItems.JURASSOLINE_INGOT));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.JURASSOLINE_INGOT)
			.create("jurassoline_nugget", new ItemStack(ModItems.JURASSOLINE_NUGGET, 9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.JURASSOLINE_NUGGET)
			.addInput(ModItems.JURASSOLINE_NUGGET)
			.addInput(ModItems.JURASSOLINE_NUGGET)
			.addInput(ModItems.JURASSOLINE_NUGGET)
			.addInput(ModItems.JURASSOLINE_NUGGET)
			.addInput(ModItems.JURASSOLINE_NUGGET)
			.addInput(ModItems.JURASSOLINE_NUGGET)
			.addInput(ModItems.JURASSOLINE_NUGGET)
			.addInput(ModItems.JURASSOLINE_NUGGET)
			.create("jurassoline_ingot", new ItemStack(ModItems.JURASSOLINE_INGOT));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModBlocks.JURASSOLINE_BLOCK)
			.create("jurassoline_block_to_ingot", new ItemStack(ModItems.JURASSOLINE_INGOT,9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.JURASSOLINE_INGOT)
			.addInput(ModItems.JURASSOLINE_INGOT)
			.addInput(ModItems.JURASSOLINE_INGOT)
			.addInput(ModItems.JURASSOLINE_INGOT)
			.addInput(ModItems.JURASSOLINE_INGOT)
			.addInput(ModItems.JURASSOLINE_INGOT)
			.addInput(ModItems.JURASSOLINE_INGOT)
			.addInput(ModItems.JURASSOLINE_INGOT)
			.addInput(ModItems.JURASSOLINE_INGOT)
			.create("jurassoline_block", new ItemStack(ModBlocks.JURASSOLINE_BLOCK));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("#", "#", "I")
			.addInput('#', ModItems.JURASSOLINE_INGOT)
			.addInput('I', ModItems.FOSSILIZED_BONE)
			.create("jurassoline_sword", new ItemStack(ModItems.JURASSOLINE_SWORD));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", " I ", " I ")
			.addInput('#', ModItems.JURASSOLINE_INGOT)
			.addInput('I', ModItems.FOSSILIZED_BONE)
			.create("jurassoline_pickaxe", new ItemStack(ModItems.JURASSOLINE_PICKAXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("## ", "#I ", " I ")
			.addInput('#', ModItems.JURASSOLINE_INGOT)
			.addInput('I', ModItems.FOSSILIZED_BONE)
			.create("jurassoline_axe", new ItemStack(ModItems.JURASSOLINE_AXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape(" ##", " I#", " I ")
			.addInput('#', ModItems.JURASSOLINE_INGOT)
			.addInput('I', ModItems.FOSSILIZED_BONE)
			.create("jurassoline_axe_alt", new ItemStack(ModItems.JURASSOLINE_AXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("#", "I", "I")
			.addInput('#', ModItems.JURASSOLINE_INGOT)
			.addInput('I', ModItems.FOSSILIZED_BONE)
			.create("jurassoline_shovel", new ItemStack(ModItems.JURASSOLINE_SHOVEL));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("## ", " I ", " I ")
			.addInput('#', ModItems.JURASSOLINE_INGOT)
			.addInput('I', ModItems.FOSSILIZED_BONE)
			.create("jurassoline_hoe", new ItemStack(ModItems.JURASSOLINE_HOE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape(" ##", " I ", " I ")
			.addInput('#', ModItems.JURASSOLINE_INGOT)
			.addInput('I', ModItems.FOSSILIZED_BONE)
			.create("jurassoline__hoe_alt", new ItemStack(ModItems.JURASSOLINE_HOE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", "# #")
			.addInput('#', ModItems.JURASSOLINE_INGOT)
			.create("jurassoline_helmet", new ItemStack(ModItems.JURASSOLINE_HELMET));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("# #", "###", "###")
			.addInput('#', ModItems.JURASSOLINE_INGOT)
			.create("jurassoline_chestplate", new ItemStack(ModItems.JURASSOLINE_CHESTPLATE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", "# #", "# #")
			.addInput('#', ModItems.JURASSOLINE_INGOT)
			.create("jurassoline_leggings", new ItemStack(ModItems.JURASSOLINE_LEGGINGS));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("# #", "# #")
			.addInput('#', ModItems.JURASSOLINE_INGOT)
			.create("jurassoline_boots", new ItemStack(ModItems.JURASSOLINE_BOOTS));

		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModBlocks.CINNABAR_CRYSTAL_BLOCK)
			.create("cinnabar_crystal_block_to_crystal", new ItemStack(ModItems.CINNABAR_CRYSTAL,9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.CINNABAR_CRYSTAL)
			.addInput(ModItems.CINNABAR_CRYSTAL)
			.addInput(ModItems.CINNABAR_CRYSTAL)
			.addInput(ModItems.CINNABAR_CRYSTAL)
			.addInput(ModItems.CINNABAR_CRYSTAL)
			.addInput(ModItems.CINNABAR_CRYSTAL)
			.addInput(ModItems.CINNABAR_CRYSTAL)
			.addInput(ModItems.CINNABAR_CRYSTAL)
			.addInput(ModItems.CINNABAR_CRYSTAL)
			.create("cinnabar_crystal_block", new ItemStack(ModBlocks.CINNABAR_CRYSTAL_BLOCK));
		RecipeBuilder.BlastFurnace(TLOTD.MOD_ID)
			.setInput(ModItems.CINNABAR_CRYSTAL)
			.create("cinnabar_crystal_blast_furnace", new ItemStack(ModItems.CINNABAR_INGOT));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.CINNABAR_INGOT)
			.create("cinnabar_nugget", new ItemStack(ModItems.CINNABAR_NUGGET, 9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.CINNABAR_NUGGET)
			.addInput(ModItems.CINNABAR_NUGGET)
			.addInput(ModItems.CINNABAR_NUGGET)
			.addInput(ModItems.CINNABAR_NUGGET)
			.addInput(ModItems.CINNABAR_NUGGET)
			.addInput(ModItems.CINNABAR_NUGGET)
			.addInput(ModItems.CINNABAR_NUGGET)
			.addInput(ModItems.CINNABAR_NUGGET)
			.addInput(ModItems.CINNABAR_NUGGET)
			.create("cinnabar_ingot", new ItemStack(ModItems.CINNABAR_INGOT));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModBlocks.CINNABAR_BLOCK)
			.create("cinnabar_block_to_ingot", new ItemStack(ModItems.CINNABAR_INGOT,9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.CINNABAR_INGOT)
			.addInput(ModItems.CINNABAR_INGOT)
			.addInput(ModItems.CINNABAR_INGOT)
			.addInput(ModItems.CINNABAR_INGOT)
			.addInput(ModItems.CINNABAR_INGOT)
			.addInput(ModItems.CINNABAR_INGOT)
			.addInput(ModItems.CINNABAR_INGOT)
			.addInput(ModItems.CINNABAR_INGOT)
			.addInput(ModItems.CINNABAR_INGOT)
			.create("cinnabar_block", new ItemStack(ModBlocks.CINNABAR_BLOCK));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("#", "#", "I")
			.addInput('#', ModItems.CINNABAR_INGOT)
			.addInput('I', ModItems.REINFORCED_TOOL_ROD)
			.create("cinnabar_sword", new ItemStack(ModItems.CINNABAR_SWORD));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", " I ", " I ")
			.addInput('#', ModItems.CINNABAR_INGOT)
			.addInput('I', ModItems.REINFORCED_TOOL_ROD)
			.create("cinnabar_pickaxe", new ItemStack(ModItems.CINNABAR_PICKAXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("## ", "#I ", " I ")
			.addInput('#', ModItems.CINNABAR_INGOT)
			.addInput('I', ModItems.REINFORCED_TOOL_ROD)
			.create("cinnabar_axe", new ItemStack(ModItems.CINNABAR_AXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape(" ##", " I#", " I ")
			.addInput('#', ModItems.CINNABAR_INGOT)
			.addInput('I', ModItems.REINFORCED_TOOL_ROD)
			.create("cinnabar_axe_alt", new ItemStack(ModItems.CINNABAR_AXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("#", "I", "I")
			.addInput('#', ModItems.CINNABAR_INGOT)
			.addInput('I', ModItems.REINFORCED_TOOL_ROD)
			.create("cinnabar_shovel", new ItemStack(ModItems.CINNABAR_SHOVEL));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("## ", " I ", " I ")
			.addInput('#', ModItems.CINNABAR_INGOT)
			.addInput('I', ModItems.REINFORCED_TOOL_ROD)
			.create("cinnabar_hoe", new ItemStack(ModItems.CINNABAR_HOE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape(" ##", " I ", " I ")
			.addInput('#', ModItems.CINNABAR_INGOT)
			.addInput('I', ModItems.REINFORCED_TOOL_ROD)
			.create("cinnabar__hoe_alt", new ItemStack(ModItems.CINNABAR_HOE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", "# #")
			.addInput('#', ModItems.CINNABAR_INGOT)
			.create("cinnabar_helmet", new ItemStack(ModItems.CINNABAR_HELMET));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("# #", "###", "###")
			.addInput('#', ModItems.CINNABAR_INGOT)
			.create("cinnabar_chestplate", new ItemStack(ModItems.CINNABAR_CHESTPLATE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", "# #", "# #")
			.addInput('#', ModItems.CINNABAR_INGOT)
			.create("cinnabar_leggings", new ItemStack(ModItems.CINNABAR_LEGGINGS));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("# #", "# #")
			.addInput('#', ModItems.CINNABAR_INGOT)
			.create("cinnabar_boots", new ItemStack(ModItems.CINNABAR_BOOTS));

		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModBlocks.RAW_NEBULAR_BLOCK)
			.create("raw_nebular_block_to_crystal", new ItemStack(ModItems.NEBULAR_CRYSTAL,9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.NEBULAR_CRYSTAL)
			.addInput(ModItems.NEBULAR_CRYSTAL)
			.addInput(ModItems.NEBULAR_CRYSTAL)
			.addInput(ModItems.NEBULAR_CRYSTAL)
			.addInput(ModItems.NEBULAR_CRYSTAL)
			.addInput(ModItems.NEBULAR_CRYSTAL)
			.addInput(ModItems.NEBULAR_CRYSTAL)
			.addInput(ModItems.NEBULAR_CRYSTAL)
			.addInput(ModItems.NEBULAR_CRYSTAL)
			.create("raw_nebular_block", new ItemStack(ModBlocks.RAW_NEBULAR_BLOCK));
		RecipeBuilder.BlastFurnace(TLOTD.MOD_ID)
			.setInput(ModItems.NEBULAR_CRYSTAL)
			.create("nebular_crystal_blast_furnace", new ItemStack(ModItems.NEBULAR_INGOT));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.NEBULAR_INGOT)
			.create("nebular_nugget", new ItemStack(ModItems.NEBULAR_NUGGET, 9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.NEBULAR_NUGGET)
			.addInput(ModItems.NEBULAR_NUGGET)
			.addInput(ModItems.NEBULAR_NUGGET)
			.addInput(ModItems.NEBULAR_NUGGET)
			.addInput(ModItems.NEBULAR_NUGGET)
			.addInput(ModItems.NEBULAR_NUGGET)
			.addInput(ModItems.NEBULAR_NUGGET)
			.addInput(ModItems.NEBULAR_NUGGET)
			.addInput(ModItems.NEBULAR_NUGGET)
			.create("nebular_ingot", new ItemStack(ModItems.NEBULAR_INGOT));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModBlocks.NEBULAR_BLOCK)
			.create("nebular_block_to_ingot", new ItemStack(ModItems.NEBULAR_INGOT,9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.NEBULAR_INGOT)
			.addInput(ModItems.NEBULAR_INGOT)
			.addInput(ModItems.NEBULAR_INGOT)
			.addInput(ModItems.NEBULAR_INGOT)
			.addInput(ModItems.NEBULAR_INGOT)
			.addInput(ModItems.NEBULAR_INGOT)
			.addInput(ModItems.NEBULAR_INGOT)
			.addInput(ModItems.NEBULAR_INGOT)
			.addInput(ModItems.NEBULAR_INGOT)
			.create("nebular_block", new ItemStack(ModBlocks.NEBULAR_BLOCK));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("#", "#", "I")
			.addInput('#', ModItems.NEBULAR_INGOT)
			.addInput('I', ModItems.REINFORCED_TOOL_ROD)
			.create("nebular_sword", new ItemStack(ModItems.NEBULAR_SWORD));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", " I ", " I ")
			.addInput('#', ModItems.NEBULAR_INGOT)
			.addInput('I', ModItems.REINFORCED_TOOL_ROD)
			.create("nebular_pickaxe", new ItemStack(ModItems.NEBULAR_PICKAXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("## ", "#I ", " I ")
			.addInput('#', ModItems.NEBULAR_INGOT)
			.addInput('I', ModItems.REINFORCED_TOOL_ROD)
			.create("nebular_axe", new ItemStack(ModItems.NEBULAR_AXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape(" ##", " I#", " I ")
			.addInput('#', ModItems.NEBULAR_INGOT)
			.addInput('I', ModItems.REINFORCED_TOOL_ROD)
			.create("nebular_axe_alt", new ItemStack(ModItems.NEBULAR_AXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("#", "I", "I")
			.addInput('#', ModItems.NEBULAR_INGOT)
			.addInput('I', ModItems.REINFORCED_TOOL_ROD)
			.create("nebular_shovel", new ItemStack(ModItems.NEBULAR_SHOVEL));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("## ", " I ", " I ")
			.addInput('#', ModItems.NEBULAR_INGOT)
			.addInput('I', ModItems.REINFORCED_TOOL_ROD)
			.create("nebular_hoe", new ItemStack(ModItems.NEBULAR_HOE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape(" ##", " I ", " I ")
			.addInput('#', ModItems.NEBULAR_INGOT)
			.addInput('I', ModItems.REINFORCED_TOOL_ROD)
			.create("nebular__hoe_alt", new ItemStack(ModItems.NEBULAR_HOE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", "# #")
			.addInput('#', ModItems.NEBULAR_INGOT)
			.create("nebular_helmet", new ItemStack(ModItems.NEBULAR_HELMET));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("# #", "###", "###")
			.addInput('#', ModItems.NEBULAR_INGOT)
			.create("nebular_chestplate", new ItemStack(ModItems.NEBULAR_CHESTPLATE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", "# #", "# #")
			.addInput('#', ModItems.NEBULAR_INGOT)
			.create("nebular_leggings", new ItemStack(ModItems.NEBULAR_LEGGINGS));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("# #", "# #")
			.addInput('#', ModItems.NEBULAR_INGOT)
			.create("nebular_boots", new ItemStack(ModItems.NEBULAR_BOOTS));

		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModBlocks.RAW_MITHRIL_BLOCK)
			.create("raw_mithril_to_chunk", new ItemStack(ModItems.RAW_MITHRIL,9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.RAW_MITHRIL)
			.addInput(ModItems.RAW_MITHRIL)
			.addInput(ModItems.RAW_MITHRIL)
			.addInput(ModItems.RAW_MITHRIL)
			.addInput(ModItems.RAW_MITHRIL)
			.addInput(ModItems.RAW_MITHRIL)
			.addInput(ModItems.RAW_MITHRIL)
			.addInput(ModItems.RAW_MITHRIL)
			.addInput(ModItems.RAW_MITHRIL)
			.create("raw_mithril_block", new ItemStack(ModBlocks.RAW_MITHRIL_BLOCK));
		RecipeBuilder.BlastFurnace(TLOTD.MOD_ID)
			.setInput(ModItems.RAW_MITHRIL)
			.create("raw_mithril_blast_furnace", new ItemStack(ModItems.MITHRIL_INGOT));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.MITHRIL_INGOT)
			.create("mithril_nugget", new ItemStack(ModItems.MITHRIL_NUGGET, 9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.MITHRIL_NUGGET)
			.addInput(ModItems.MITHRIL_NUGGET)
			.addInput(ModItems.MITHRIL_NUGGET)
			.addInput(ModItems.MITHRIL_NUGGET)
			.addInput(ModItems.MITHRIL_NUGGET)
			.addInput(ModItems.MITHRIL_NUGGET)
			.addInput(ModItems.MITHRIL_NUGGET)
			.addInput(ModItems.MITHRIL_NUGGET)
			.addInput(ModItems.MITHRIL_NUGGET)
			.create("mithril_ingot", new ItemStack(ModItems.MITHRIL_INGOT));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModBlocks.MITHRIL_BLOCK)
			.create("mithril_block_to_ingot", new ItemStack(ModItems.MITHRIL_INGOT,9));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.MITHRIL_INGOT)
			.addInput(ModItems.MITHRIL_INGOT)
			.addInput(ModItems.MITHRIL_INGOT)
			.addInput(ModItems.MITHRIL_INGOT)
			.addInput(ModItems.MITHRIL_INGOT)
			.addInput(ModItems.MITHRIL_INGOT)
			.addInput(ModItems.MITHRIL_INGOT)
			.addInput(ModItems.MITHRIL_INGOT)
			.addInput(ModItems.MITHRIL_INGOT)
			.create("mithril_block", new ItemStack(ModBlocks.MITHRIL_BLOCK));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("#", "#", "I")
			.addInput('#', ModItems.MITHRIL_INGOT)
			.addInput('I', ModItems.FANCY_TOOL_ROD)
			.create("mithril_sword", new ItemStack(ModItems.MITHRIL_SWORD));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", " I ", " I ")
			.addInput('#', ModItems.MITHRIL_INGOT)
			.addInput('I', ModItems.FANCY_TOOL_ROD)
			.create("mithril_pickaxe", new ItemStack(ModItems.MITHRIL_PICKAXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("## ", "#I ", " I ")
			.addInput('#', ModItems.MITHRIL_INGOT)
			.addInput('I', ModItems.FANCY_TOOL_ROD)
			.create("mithril_axe", new ItemStack(ModItems.MITHRIL_AXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape(" ##", " I#", " I ")
			.addInput('#', ModItems.MITHRIL_INGOT)
			.addInput('I', ModItems.FANCY_TOOL_ROD)
			.create("mithril_axe_alt", new ItemStack(ModItems.MITHRIL_AXE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("#", "I", "I")
			.addInput('#', ModItems.MITHRIL_INGOT)
			.addInput('I', ModItems.FANCY_TOOL_ROD)
			.create("mithril_shovel", new ItemStack(ModItems.MITHRIL_SHOVEL));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("## ", " I ", " I ")
			.addInput('#', ModItems.MITHRIL_INGOT)
			.addInput('I', ModItems.FANCY_TOOL_ROD)
			.create("mithril_hoe", new ItemStack(ModItems.MITHRIL_HOE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape(" ##", " I ", " I ")
			.addInput('#', ModItems.MITHRIL_INGOT)
			.addInput('I', ModItems.FANCY_TOOL_ROD)
			.create("mithril__hoe_alt", new ItemStack(ModItems.MITHRIL_HOE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", "# #")
			.addInput('#', ModItems.MITHRIL_INGOT)
			.create("mithril_helmet", new ItemStack(ModItems.MITHRIL_HELMET));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("# #", "###", "###")
			.addInput('#', ModItems.MITHRIL_INGOT)
			.create("mithril_chestplate", new ItemStack(ModItems.MITHRIL_CHESTPLATE));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("###", "# #", "# #")
			.addInput('#', ModItems.MITHRIL_INGOT)
			.create("mithril_leggings", new ItemStack(ModItems.MITHRIL_LEGGINGS));
		RecipeBuilder.Shaped(TLOTD.MOD_ID)
			.setShape("# #", "# #")
			.addInput('#', ModItems.MITHRIL_INGOT)
			.create("mithril_boots", new ItemStack(ModItems.MITHRIL_BOOTS));

		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(Items.WHEAT)
			.create("flour", new ItemStack(ModItems.FLOUR, 3));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(Items.FOOD_BREAD)
			.create("toast", new ItemStack(ModItems.TOAST, 3));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.TOAST)
			.create("breadcrumbs", new ItemStack(ModItems.BREADCRUMBS, 3));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(Items.FOOD_PORKCHOP_RAW)
			.addInput(ModItems.BREADCRUMBS)
			.addInput(ModItems.FLOUR)
			.addInput(Items.EGG_CHICKEN)
			.create("raw_schnitzel", new ItemStack(ModItems.RAW_SCHNITZEL));

		RecipeBuilder.Furnace(TLOTD.MOD_ID)
			.setInput(ModItems.RAW_SCHNITZEL)
			.create("schnitzel", new ItemStack(ModItems.SCHNITZEL));

		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(Items.FOOD_PORKCHOP_RAW)
			.addInput(Items.WHEAT)
			.create("maultasche", new ItemStack(ModItems.MAULTASCHE));
		RecipeBuilder.Shapeless(TLOTD.MOD_ID)
			.addInput(ModItems.MAULTASCHE)
			.addInput(ModItems.MAULTASCHE)
			.addInput(ModItems.MAULTASCHE)
			.addInput(Items.BOWL)
			.create("maultaschen_broth", new ItemStack(ModItems.MAULTASCHEN_BROTH));
	}
}
