package net.tlotd.bta.item.custom;

import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.item.material.ToolMaterial;
import net.minecraft.core.item.tool.ItemToolPickaxe;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import net.tlotd.bta.block.custom.*;
import net.tlotd.bta.item.ModItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExtractionPickaxeItem extends ItemToolPickaxe {
	public ExtractionPickaxeItem(String name, String namespaceId, int id, ToolMaterial enumtoolmaterial) {
		super(name, namespaceId, id, enumtoolmaterial);
	}

	@Override
	public boolean onUseOnBlock(@NotNull ItemStack itemstack, @NotNull World world, @Nullable Player player, @NotNull TilePosc blockPos, @NotNull Side side, double xHit, double yHit) {
		return oreExtraction(itemstack, world, player, blockPos);
	}

	static boolean oreExtraction(@NotNull ItemStack itemstack, @NotNull World world, @Nullable Player player, @NotNull TilePosc blockPos) {
		Block<?> block = world.getBlockType(blockPos);
		if (!world.isClientSide){
			if (block.getLogic() instanceof BlockLogicOreCoal || block.getLogic() instanceof BlockLogicOreIron || block.getLogic() instanceof BlockLogicOreGold || block.getLogic() instanceof BlockLogicOreLapis || block.getLogic() instanceof BlockLogicOreRedstone || block.getLogic() instanceof BlockLogicOreDiamond || block.getLogic() instanceof BlockLogicOreNetherCoal
				|| block.getLogic() instanceof BlockLogicFossil || block.getLogic() instanceof BlockLogicHelioriteOre || block.getLogic() instanceof BlockLogicEnduriumOre || block.getLogic() instanceof BlockLogicPalladiumOre || block.getLogic() instanceof BlockLogicJurassolineOre || block.getLogic() instanceof BlockLogicCinnabarOre || block.getLogic() instanceof BlockLogicNebularOre || block.getLogic() instanceof BlockLogicMithrilOre) {
				if (block.getLogic() instanceof BlockLogicOreCoal) {
					world.dropItem(blockPos, Items.COAL.getDefaultStack());
				} else if (block.getLogic() instanceof BlockLogicOreIron) {
					world.dropItem(blockPos, Items.ORE_RAW_IRON.getDefaultStack());
				} else if (block.getLogic() instanceof BlockLogicOreGold) {
					world.dropItem(blockPos, Items.ORE_RAW_GOLD.getDefaultStack());
				} else if (block.getLogic() instanceof BlockLogicOreLapis) {
					world.dropItem(blockPos, new ItemStack(Items.DYE, 4 + world.rand.nextInt(5), DyeColor.BLUE.itemMeta));
				} else if (block.getLogic() instanceof BlockLogicOreRedstone) {
					world.dropItem(blockPos, new ItemStack(Items.DUST_REDSTONE, 4 + world.rand.nextInt(2)));
				} else if (block.getLogic() instanceof BlockLogicOreDiamond) {
					world.dropItem(blockPos, Items.DIAMOND.getDefaultStack());
				} else if (block.getLogic() instanceof BlockLogicOreNetherCoal) {
					world.dropItem(blockPos, Items.NETHERCOAL.getDefaultStack());
				} else if (block.getLogic() instanceof BlockLogicFossil) {
					world.dropItem(blockPos, ModItems.FOSSILIZED_BONE.getDefaultStack());
				} else if (block.getLogic() instanceof BlockLogicHelioriteOre) {
					world.dropItem(blockPos, ModItems.HELIORITE_COMB.getDefaultStack());
				} else if (block.getLogic() instanceof BlockLogicEnduriumOre) {
					world.dropItem(blockPos, ModItems.ENDURIUM_CRYSTAL.getDefaultStack());
				} else if (block.getLogic() instanceof BlockLogicPalladiumOre) {
					world.dropItem(blockPos, ModItems.RAW_PALLADIUM.getDefaultStack());
				} else if (block.getLogic() instanceof BlockLogicJurassolineOre) {
					world.dropItem(blockPos, ModItems.JURASSOLINE_CRYSTAL.getDefaultStack());
				} else if (block.getLogic() instanceof BlockLogicCinnabarOre) {
					world.dropItem(blockPos, ModItems.CINNABAR_CRYSTAL.getDefaultStack());
				} else if (block.getLogic() instanceof BlockLogicNebularOre) {
					world.dropItem(blockPos, ModItems.NEBULAR_CRYSTAL.getDefaultStack());
				} else {
					world.dropItem(blockPos, ModItems.RAW_MITHRIL.getDefaultStack());
				}
				if (block.getMaterial() == Materials.STONE) {
					world.setBlockType(blockPos,Blocks.STONE);
				} else if (block.getMaterial() == Materials.BASALT) {
					world.setBlockType(blockPos,Blocks.BASALT);
				} else if (block.getMaterial() == Materials.LIMESTONE) {
					world.setBlockType(blockPos,Blocks.LIMESTONE);
				} else if (block.getMaterial() == Materials.GRANITE) {
					world.setBlockType(blockPos,Blocks.GRANITE);
				} else if (block.getMaterial() == Materials.PERMAFROST) {
					world.setBlockType(blockPos,Blocks.PERMAFROST);
				} else if (block.getMaterial() == Materials.MARBLE) {
					world.setBlockType(blockPos,Blocks.MARBLE);
				} else if (block.getMaterial() == Materials.SLATE) {
					world.setBlockType(blockPos,Blocks.SLATE);
				} else if (block.getMaterial() == Materials.NETHERRACK) {
					world.setBlockType(blockPos,Blocks.COBBLE_NETHERRACK);
				} else if (block.getMaterial() == Materials.GLOOMSTONE) {
					world.setBlockType(blockPos,Blocks.COBBLE_GLOOMSTONE);
				}
				world.markBlockDirty(blockPos);
				itemstack.damageItem(1,player);
				return true;
			}
		}
		return false;
	}
}
