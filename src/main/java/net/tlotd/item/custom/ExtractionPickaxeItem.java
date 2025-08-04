package net.tlotd.item.custom;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.ItemToolPickaxe;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;

public class ExtractionPickaxeItem extends ItemToolPickaxe {
	public ExtractionPickaxeItem(String name, String namespaceId, int id, ToolMaterial enumtoolmaterial) {
		super(name, namespaceId, id, enumtoolmaterial);
	}

	@Override
	public boolean onUseItemOnBlock(ItemStack itemstack, Player entityplayer, World world, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		Block<?> block = world.getBlock(blockX, blockY, blockZ);
		if (!world.isClientSide){
			if (block == Blocks.ORE_COAL_STONE) {
				world.dropItem(blockX,blockY,blockZ, Items.COAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.STONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_COAL_BASALT) {
				world.dropItem(blockX,blockY,blockZ, Items.COAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.BASALT.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_COAL_LIMESTONE) {
				world.dropItem(blockX,blockY,blockZ, Items.COAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.LIMESTONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_COAL_GRANITE) {
				world.dropItem(blockX,blockY,blockZ, Items.COAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.GRANITE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_COAL_PERMAFROST) {
				world.dropItem(blockX,blockY,blockZ, Items.COAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.PERMAFROST.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_IRON_STONE) {
				world.dropItem(blockX,blockY,blockZ, Items.ORE_RAW_IRON.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.STONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_IRON_BASALT) {
				world.dropItem(blockX,blockY,blockZ, Items.ORE_RAW_IRON.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.BASALT.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_IRON_LIMESTONE) {
				world.dropItem(blockX,blockY,blockZ, Items.ORE_RAW_IRON.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.LIMESTONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_IRON_GRANITE) {
				world.dropItem(blockX,blockY,blockZ, Items.ORE_RAW_IRON.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.GRANITE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_IRON_PERMAFROST) {
				world.dropItem(blockX,blockY,blockZ, Items.ORE_RAW_IRON.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.PERMAFROST.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_GOLD_STONE) {
				world.dropItem(blockX,blockY,blockZ, Items.ORE_RAW_GOLD.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.STONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_GOLD_BASALT) {
				world.dropItem(blockX,blockY,blockZ, Items.ORE_RAW_GOLD.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.BASALT.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_GOLD_LIMESTONE) {
				world.dropItem(blockX,blockY,blockZ, Items.ORE_RAW_GOLD.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.LIMESTONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_GOLD_GRANITE) {
				world.dropItem(blockX,blockY,blockZ, Items.ORE_RAW_GOLD.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.GRANITE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_GOLD_PERMAFROST) {
				world.dropItem(blockX,blockY,blockZ, Items.ORE_RAW_GOLD.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.PERMAFROST.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_LAPIS_STONE) {
				world.dropItem(blockX,blockY,blockZ, new ItemStack(Items.DYE.id, 1, 4));
				world.setBlock(blockX,blockY,blockZ,Blocks.STONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_LAPIS_BASALT) {
				world.dropItem(blockX,blockY,blockZ, new ItemStack(Items.DYE.id, 1, 4));
				world.setBlock(blockX,blockY,blockZ,Blocks.BASALT.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_LAPIS_LIMESTONE) {
				world.dropItem(blockX,blockY,blockZ, new ItemStack(Items.DYE.id, 1, 4));
				world.setBlock(blockX,blockY,blockZ,Blocks.LIMESTONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_LAPIS_GRANITE) {
				world.dropItem(blockX,blockY,blockZ, new ItemStack(Items.DYE.id, 1, 4));
				world.setBlock(blockX,blockY,blockZ,Blocks.GRANITE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_LAPIS_PERMAFROST) {
				world.dropItem(blockX,blockY,blockZ, new ItemStack(Items.DYE.id, 1, 4));
				world.setBlock(blockX,blockY,blockZ,Blocks.PERMAFROST.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_REDSTONE_STONE) {
				world.dropItem(blockX,blockY,blockZ, Items.DUST_REDSTONE.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.STONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_REDSTONE_BASALT) {
				world.dropItem(blockX,blockY,blockZ, Items.DUST_REDSTONE.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.BASALT.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_REDSTONE_LIMESTONE) {
				world.dropItem(blockX,blockY,blockZ, Items.DUST_REDSTONE.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.LIMESTONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_REDSTONE_GRANITE) {
				world.dropItem(blockX,blockY,blockZ, Items.DUST_REDSTONE.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.GRANITE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_REDSTONE_PERMAFROST) {
				world.dropItem(blockX,blockY,blockZ, Items.DUST_REDSTONE.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.PERMAFROST.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_DIAMOND_STONE) {
				world.dropItem(blockX,blockY,blockZ, Items.DIAMOND.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.STONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_DIAMOND_BASALT) {
				world.dropItem(blockX,blockY,blockZ, Items.DIAMOND.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.BASALT.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_DIAMOND_LIMESTONE) {
				world.dropItem(blockX,blockY,blockZ, Items.DIAMOND.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.LIMESTONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_DIAMOND_GRANITE) {
				world.dropItem(blockX,blockY,blockZ, Items.DIAMOND.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.GRANITE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.ORE_DIAMOND_PERMAFROST) {
				world.dropItem(blockX,blockY,blockZ, Items.DIAMOND.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.PERMAFROST.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == Blocks.BLOCK_NETHER_COAL) {
				world.dropItem(blockX,blockY,blockZ, Items.NETHERCOAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.NETHERRACK.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.STONE_FOSSIL) { //Modded
				world.dropItem(blockX,blockY,blockZ, ModItems.FOSSILIZED_BONE.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.STONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.BASALT_FOSSIL) {
				world.dropItem(blockX,blockY,blockZ, ModItems.FOSSILIZED_BONE.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.BASALT.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.LIMESTONE_FOSSIL) {
				world.dropItem(blockX,blockY,blockZ, ModItems.FOSSILIZED_BONE.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.LIMESTONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.GRANITE_FOSSIL) {
				world.dropItem(blockX,blockY,blockZ, ModItems.FOSSILIZED_BONE.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.GRANITE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.PERMAFROST_FOSSIL) {
				world.dropItem(blockX,blockY,blockZ, ModItems.FOSSILIZED_BONE.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.PERMAFROST.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.RED_DEEPSLATE_FOSSIL) {
				world.dropItem(blockX,blockY,blockZ, ModItems.FOSSILIZED_BONE.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,ModBlocks.RED_DEEPSLATE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.HELIORITE_STONE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.HELIORITE_COMB.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.STONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.HELIORITE_BASALT_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.HELIORITE_COMB.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.BASALT.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.HELIORITE_LIMESTONE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.HELIORITE_COMB.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.LIMESTONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.HELIORITE_GRANITE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.HELIORITE_COMB.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.GRANITE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.HELIORITE_PERMAFROST_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.HELIORITE_COMB.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.PERMAFROST.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.HELIORITE_RED_DEEPSLATE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.HELIORITE_COMB.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,ModBlocks.RED_DEEPSLATE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.ENDURIUM_STONE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.ENDURIUM_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.STONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.ENDURIUM_BASALT_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.ENDURIUM_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.BASALT.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.ENDURIUM_LIMESTONE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.ENDURIUM_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.LIMESTONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.ENDURIUM_GRANITE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.ENDURIUM_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.GRANITE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.ENDURIUM_PERMAFROST_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.ENDURIUM_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.PERMAFROST.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.ENDURIUM_RED_DEEPSLATE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.ENDURIUM_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,ModBlocks.RED_DEEPSLATE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.PALLADIUM_STONE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.RAW_PALLADIUM.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.STONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.PALLADIUM_BASALT_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.RAW_PALLADIUM.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.BASALT.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.PALLADIUM_LIMESTONE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.RAW_PALLADIUM.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.LIMESTONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.PALLADIUM_GRANITE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.RAW_PALLADIUM.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.GRANITE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.PALLADIUM_PERMAFROST_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.RAW_PALLADIUM.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.PERMAFROST.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.PALLADIUM_RED_DEEPSLATE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.RAW_PALLADIUM.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,ModBlocks.RED_DEEPSLATE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.JURASSOLINE_STONE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.JURASSOLINE_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.STONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.JURASSOLINE_BASALT_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.JURASSOLINE_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.BASALT.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.JURASSOLINE_LIMESTONE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.JURASSOLINE_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.LIMESTONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.JURASSOLINE_GRANITE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.JURASSOLINE_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.GRANITE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.JURASSOLINE_PERMAFROST_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.JURASSOLINE_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.PERMAFROST.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.JURASSOLINE_RED_DEEPSLATE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.JURASSOLINE_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,ModBlocks.RED_DEEPSLATE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.CINNABAR_STONE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.CINNABAR_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.STONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.CINNABAR_BASALT_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.CINNABAR_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.BASALT.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.CINNABAR_LIMESTONE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.CINNABAR_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.LIMESTONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.CINNABAR_GRANITE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.CINNABAR_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.GRANITE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.CINNABAR_PERMAFROST_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.CINNABAR_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.PERMAFROST.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.CINNABAR_RED_DEEPSLATE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.CINNABAR_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,ModBlocks.RED_DEEPSLATE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.NEBULAR_STONE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.NEBULAR_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.STONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.NEBULAR_BASALT_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.NEBULAR_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.BASALT.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.NEBULAR_LIMESTONE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.NEBULAR_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.LIMESTONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.NEBULAR_GRANITE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.NEBULAR_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.GRANITE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.NEBULAR_PERMAFROST_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.NEBULAR_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.PERMAFROST.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.NEBULAR_RED_DEEPSLATE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.NEBULAR_CRYSTAL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,ModBlocks.RED_DEEPSLATE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.MITHRIL_STONE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.RAW_MITHRIL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.STONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.MITHRIL_BASALT_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.RAW_MITHRIL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.BASALT.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.MITHRIL_LIMESTONE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.RAW_MITHRIL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.LIMESTONE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.MITHRIL_GRANITE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.RAW_MITHRIL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.GRANITE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.MITHRIL_PERMAFROST_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.RAW_MITHRIL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,Blocks.PERMAFROST.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.MITHRIL_RED_DEEPSLATE_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.RAW_MITHRIL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,ModBlocks.RED_DEEPSLATE.id());
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			} else if (block == ModBlocks.MITHRIL_BEDROCK_ORE) {
				world.dropItem(blockX,blockY,blockZ, ModItems.RAW_MITHRIL.getDefaultStack());
				world.setBlock(blockX,blockY,blockZ,260);
				world.markBlockDirty(blockX,blockY,blockZ);
				itemstack.damageItem(1,entityplayer);
				return true;
			}
		}
		return false;
	}
}
