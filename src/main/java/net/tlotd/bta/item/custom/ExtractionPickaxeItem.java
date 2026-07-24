package net.tlotd.bta.item.custom;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.ItemToolPickaxe;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import net.tlotd.bta.block.ModBlocks;
import net.tlotd.bta.item.ModItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExtractionPickaxeItem extends ItemToolPickaxe {
	public ExtractionPickaxeItem(String name, String namespaceId, int id, ToolMaterial enumtoolmaterial) {
		super(name, namespaceId, id, enumtoolmaterial);
	}

	@Override
	public boolean onUseOnBlock(@NotNull ItemStack itemstack, @NotNull World world, @Nullable Player player, @NotNull TilePosc blockPos, @NotNull Side side, double xHit, double yHit) {
		Block<?> block = world.getBlockType(blockPos);
		if (!world.isClientSide){
			if (block == Blocks.ORE_COAL_STONE) {
				world.dropItem(blockPos, Items.COAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.STONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_COAL_BASALT) {
				world.dropItem(blockPos, Items.COAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.BASALT);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_COAL_LIMESTONE) {
				world.dropItem(blockPos, Items.COAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.LIMESTONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_COAL_GRANITE) {
				world.dropItem(blockPos, Items.COAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.GRANITE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_COAL_PERMAFROST) {
				world.dropItem(blockPos, Items.COAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.PERMAFROST);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_IRON_STONE) {
				world.dropItem(blockPos, Items.ORE_RAW_IRON.getDefaultStack());
				world.setBlockType(blockPos,Blocks.STONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_IRON_BASALT) {
				world.dropItem(blockPos, Items.ORE_RAW_IRON.getDefaultStack());
				world.setBlockType(blockPos,Blocks.BASALT);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_IRON_LIMESTONE) {
				world.dropItem(blockPos, Items.ORE_RAW_IRON.getDefaultStack());
				world.setBlockType(blockPos,Blocks.LIMESTONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_IRON_GRANITE) {
				world.dropItem(blockPos, Items.ORE_RAW_IRON.getDefaultStack());
				world.setBlockType(blockPos,Blocks.GRANITE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_IRON_PERMAFROST) {
				world.dropItem(blockPos, Items.ORE_RAW_IRON.getDefaultStack());
				world.setBlockType(blockPos,Blocks.PERMAFROST);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_GOLD_STONE) {
				world.dropItem(blockPos, Items.ORE_RAW_GOLD.getDefaultStack());
				world.setBlockType(blockPos,Blocks.STONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_GOLD_BASALT) {
				world.dropItem(blockPos, Items.ORE_RAW_GOLD.getDefaultStack());
				world.setBlockType(blockPos,Blocks.BASALT);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_GOLD_LIMESTONE) {
				world.dropItem(blockPos, Items.ORE_RAW_GOLD.getDefaultStack());
				world.setBlockType(blockPos,Blocks.LIMESTONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_GOLD_GRANITE) {
				world.dropItem(blockPos, Items.ORE_RAW_GOLD.getDefaultStack());
				world.setBlockType(blockPos,Blocks.GRANITE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_GOLD_PERMAFROST) {
				world.dropItem(blockPos, Items.ORE_RAW_GOLD.getDefaultStack());
				world.setBlockType(blockPos,Blocks.PERMAFROST);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_LAPIS_STONE) {
				world.dropItem(blockPos, new ItemStack(Items.DYE.id, 1, 4));
				world.setBlockType(blockPos,Blocks.STONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_LAPIS_BASALT) {
				world.dropItem(blockPos, new ItemStack(Items.DYE.id, 1, 4));
				world.setBlockType(blockPos,Blocks.BASALT);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_LAPIS_LIMESTONE) {
				world.dropItem(blockPos, new ItemStack(Items.DYE.id, 1, 4));
				world.setBlockType(blockPos,Blocks.LIMESTONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_LAPIS_GRANITE) {
				world.dropItem(blockPos, new ItemStack(Items.DYE.id, 1, 4));
				world.setBlockType(blockPos,Blocks.GRANITE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_LAPIS_PERMAFROST) {
				world.dropItem(blockPos, new ItemStack(Items.DYE.id, 1, 4));
				world.setBlockType(blockPos,Blocks.PERMAFROST);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_REDSTONE_STONE) {
				world.dropItem(blockPos, Items.DUST_REDSTONE.getDefaultStack());
				world.setBlockType(blockPos,Blocks.STONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_REDSTONE_BASALT) {
				world.dropItem(blockPos, Items.DUST_REDSTONE.getDefaultStack());
				world.setBlockType(blockPos,Blocks.BASALT);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_REDSTONE_LIMESTONE) {
				world.dropItem(blockPos, Items.DUST_REDSTONE.getDefaultStack());
				world.setBlockType(blockPos,Blocks.LIMESTONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_REDSTONE_GRANITE) {
				world.dropItem(blockPos, Items.DUST_REDSTONE.getDefaultStack());
				world.setBlockType(blockPos,Blocks.GRANITE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_REDSTONE_PERMAFROST) {
				world.dropItem(blockPos, Items.DUST_REDSTONE.getDefaultStack());
				world.setBlockType(blockPos,Blocks.PERMAFROST);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_DIAMOND_STONE) {
				world.dropItem(blockPos, Items.DIAMOND.getDefaultStack());
				world.setBlockType(blockPos,Blocks.STONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_DIAMOND_BASALT) {
				world.dropItem(blockPos, Items.DIAMOND.getDefaultStack());
				world.setBlockType(blockPos,Blocks.BASALT);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_DIAMOND_LIMESTONE) {
				world.dropItem(blockPos, Items.DIAMOND.getDefaultStack());
				world.setBlockType(blockPos,Blocks.LIMESTONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_DIAMOND_GRANITE) {
				world.dropItem(blockPos, Items.DIAMOND.getDefaultStack());
				world.setBlockType(blockPos,Blocks.GRANITE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_DIAMOND_PERMAFROST) {
				world.dropItem(blockPos, Items.DIAMOND.getDefaultStack());
				world.setBlockType(blockPos,Blocks.PERMAFROST);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == Blocks.ORE_NETHERCOAL_NETHERRACK) {
				world.dropItem(blockPos, Items.NETHERCOAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.COBBLE_NETHERRACK);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.STONE_FOSSIL) { //Modded
				world.dropItem(blockPos, ModItems.FOSSILIZED_BONE.getDefaultStack());
				world.setBlockType(blockPos,Blocks.STONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.BASALT_FOSSIL) {
				world.dropItem(blockPos, ModItems.FOSSILIZED_BONE.getDefaultStack());
				world.setBlockType(blockPos,Blocks.BASALT);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.LIMESTONE_FOSSIL) {
				world.dropItem(blockPos, ModItems.FOSSILIZED_BONE.getDefaultStack());
				world.setBlockType(blockPos,Blocks.LIMESTONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.GRANITE_FOSSIL) {
				world.dropItem(blockPos, ModItems.FOSSILIZED_BONE.getDefaultStack());
				world.setBlockType(blockPos,Blocks.GRANITE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.PERMAFROST_FOSSIL) {
				world.dropItem(blockPos, ModItems.FOSSILIZED_BONE.getDefaultStack());
				world.setBlockType(blockPos,Blocks.PERMAFROST);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.RED_DEEPSLATE_FOSSIL) {
				world.dropItem(blockPos, ModItems.FOSSILIZED_BONE.getDefaultStack());
				world.setBlockType(blockPos,ModBlocks.RED_DEEPSLATE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.HELIORITE_STONE_ORE) {
				world.dropItem(blockPos, ModItems.HELIORITE_COMB.getDefaultStack());
				world.setBlockType(blockPos,Blocks.STONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.HELIORITE_BASALT_ORE) {
				world.dropItem(blockPos, ModItems.HELIORITE_COMB.getDefaultStack());
				world.setBlockType(blockPos,Blocks.BASALT);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.HELIORITE_LIMESTONE_ORE) {
				world.dropItem(blockPos, ModItems.HELIORITE_COMB.getDefaultStack());
				world.setBlockType(blockPos,Blocks.LIMESTONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.HELIORITE_GRANITE_ORE) {
				world.dropItem(blockPos, ModItems.HELIORITE_COMB.getDefaultStack());
				world.setBlockType(blockPos,Blocks.GRANITE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.HELIORITE_PERMAFROST_ORE) {
				world.dropItem(blockPos, ModItems.HELIORITE_COMB.getDefaultStack());
				world.setBlockType(blockPos,Blocks.PERMAFROST);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.HELIORITE_RED_DEEPSLATE_ORE) {
				world.dropItem(blockPos, ModItems.HELIORITE_COMB.getDefaultStack());
				world.setBlockType(blockPos,ModBlocks.RED_DEEPSLATE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.ENDURIUM_STONE_ORE) {
				world.dropItem(blockPos, ModItems.ENDURIUM_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.STONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.ENDURIUM_BASALT_ORE) {
				world.dropItem(blockPos, ModItems.ENDURIUM_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.BASALT);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.ENDURIUM_LIMESTONE_ORE) {
				world.dropItem(blockPos, ModItems.ENDURIUM_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.LIMESTONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.ENDURIUM_GRANITE_ORE) {
				world.dropItem(blockPos, ModItems.ENDURIUM_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.GRANITE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.ENDURIUM_PERMAFROST_ORE) {
				world.dropItem(blockPos, ModItems.ENDURIUM_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.PERMAFROST);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.ENDURIUM_RED_DEEPSLATE_ORE) {
				world.dropItem(blockPos, ModItems.ENDURIUM_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,ModBlocks.RED_DEEPSLATE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.PALLADIUM_STONE_ORE) {
				world.dropItem(blockPos, ModItems.RAW_PALLADIUM.getDefaultStack());
				world.setBlockType(blockPos,Blocks.STONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.PALLADIUM_BASALT_ORE) {
				world.dropItem(blockPos, ModItems.RAW_PALLADIUM.getDefaultStack());
				world.setBlockType(blockPos,Blocks.BASALT);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.PALLADIUM_LIMESTONE_ORE) {
				world.dropItem(blockPos, ModItems.RAW_PALLADIUM.getDefaultStack());
				world.setBlockType(blockPos,Blocks.LIMESTONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.PALLADIUM_GRANITE_ORE) {
				world.dropItem(blockPos, ModItems.RAW_PALLADIUM.getDefaultStack());
				world.setBlockType(blockPos,Blocks.GRANITE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.PALLADIUM_PERMAFROST_ORE) {
				world.dropItem(blockPos, ModItems.RAW_PALLADIUM.getDefaultStack());
				world.setBlockType(blockPos,Blocks.PERMAFROST);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.PALLADIUM_RED_DEEPSLATE_ORE) {
				world.dropItem(blockPos, ModItems.RAW_PALLADIUM.getDefaultStack());
				world.setBlockType(blockPos,ModBlocks.RED_DEEPSLATE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.JURASSOLINE_STONE_ORE) {
				world.dropItem(blockPos, ModItems.JURASSOLINE_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.STONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.JURASSOLINE_BASALT_ORE) {
				world.dropItem(blockPos, ModItems.JURASSOLINE_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.BASALT);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.JURASSOLINE_LIMESTONE_ORE) {
				world.dropItem(blockPos, ModItems.JURASSOLINE_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.LIMESTONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.JURASSOLINE_GRANITE_ORE) {
				world.dropItem(blockPos, ModItems.JURASSOLINE_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.GRANITE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.JURASSOLINE_PERMAFROST_ORE) {
				world.dropItem(blockPos, ModItems.JURASSOLINE_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.PERMAFROST);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.JURASSOLINE_RED_DEEPSLATE_ORE) {
				world.dropItem(blockPos, ModItems.JURASSOLINE_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,ModBlocks.RED_DEEPSLATE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.CINNABAR_STONE_ORE) {
				world.dropItem(blockPos, ModItems.CINNABAR_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.STONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.CINNABAR_BASALT_ORE) {
				world.dropItem(blockPos, ModItems.CINNABAR_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.BASALT);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.CINNABAR_LIMESTONE_ORE) {
				world.dropItem(blockPos, ModItems.CINNABAR_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.LIMESTONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.CINNABAR_GRANITE_ORE) {
				world.dropItem(blockPos, ModItems.CINNABAR_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.GRANITE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.CINNABAR_PERMAFROST_ORE) {
				world.dropItem(blockPos, ModItems.CINNABAR_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.PERMAFROST);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.CINNABAR_RED_DEEPSLATE_ORE) {
				world.dropItem(blockPos, ModItems.CINNABAR_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,ModBlocks.RED_DEEPSLATE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.NEBULAR_STONE_ORE) {
				world.dropItem(blockPos, ModItems.NEBULAR_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.STONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.NEBULAR_BASALT_ORE) {
				world.dropItem(blockPos, ModItems.NEBULAR_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.BASALT);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.NEBULAR_LIMESTONE_ORE) {
				world.dropItem(blockPos, ModItems.NEBULAR_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.LIMESTONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.NEBULAR_GRANITE_ORE) {
				world.dropItem(blockPos, ModItems.NEBULAR_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.GRANITE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.NEBULAR_PERMAFROST_ORE) {
				world.dropItem(blockPos, ModItems.NEBULAR_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.PERMAFROST);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.NEBULAR_RED_DEEPSLATE_ORE) {
				world.dropItem(blockPos, ModItems.NEBULAR_CRYSTAL.getDefaultStack());
				world.setBlockType(blockPos,ModBlocks.RED_DEEPSLATE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.MITHRIL_STONE_ORE) {
				world.dropItem(blockPos, ModItems.RAW_MITHRIL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.STONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.MITHRIL_BASALT_ORE) {
				world.dropItem(blockPos, ModItems.RAW_MITHRIL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.BASALT);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.MITHRIL_LIMESTONE_ORE) {
				world.dropItem(blockPos, ModItems.RAW_MITHRIL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.LIMESTONE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.MITHRIL_GRANITE_ORE) {
				world.dropItem(blockPos, ModItems.RAW_MITHRIL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.GRANITE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.MITHRIL_PERMAFROST_ORE) {
				world.dropItem(blockPos, ModItems.RAW_MITHRIL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.PERMAFROST);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.MITHRIL_RED_DEEPSLATE_ORE) {
				world.dropItem(blockPos, ModItems.RAW_MITHRIL.getDefaultStack());
				world.setBlockType(blockPos,ModBlocks.RED_DEEPSLATE);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			} else if (block == ModBlocks.MITHRIL_BEDROCK_ORE) {
				world.dropItem(blockPos, ModItems.RAW_MITHRIL.getDefaultStack());
				world.setBlockType(blockPos,Blocks.BEDROCK);
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			}
		}
		return false;
	}
}
