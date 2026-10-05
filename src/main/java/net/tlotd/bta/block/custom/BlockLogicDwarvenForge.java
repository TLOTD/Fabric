package net.tlotd.bta.block.custom;

import com.mojang.logging.LogUtils;
import java.util.Random;
import net.minecraft.core.Global;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import net.tlotd.bta.TLOTD;
import net.tlotd.bta.block.ModBlocks;
import net.tlotd.bta.block.custom.entity.TileEntityDwarvenForge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import sunsetsatellite.catalyst.Catalyst;

public class BlockLogicDwarvenForge extends BlockLogicRotatable {
	private static final Logger LOGGER = LogUtils.getLogger();
	protected final boolean isActive;

	public BlockLogicDwarvenForge(Block<?> block, boolean active) {
		super(block, Materials.METAL);
		this.isActive = active;
		block.withEntity(TileEntityDwarvenForge::new);
	}

	public ItemStack[] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
		ItemStack[] var10000;
		switch (dropCause) {
			case PICK_BLOCK:
			case EXPLOSION:
			case PROPER_TOOL:
			case SILK_TOUCH:
				var10000 = new ItemStack[]{new ItemStack(Blocks.FURNACE_BLAST_IDLE)};
				break;
			default:
				var10000 = null;
		}
		return var10000;
	}

	public void animationTick(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Random rand) {
		if (this.isActive) {
			double posX = (double)tilePos.x() + (double)0.5F;
			double posY = (double)tilePos.y() + (double)(rand.nextFloat() * 6.0F) / (double)16.0F;
			double posZ = (double)tilePos.z() + (double)0.5F;
			double f3 = 0.52;
			float f4 = rand.nextFloat() * 0.6F - 0.3F;
			switch (BlockLogicRotatable.getDirectionFromMeta(world.getBlockData(tilePos))) {
				case WEST:
					world.spawnParticle("smoke", posX - f3, posY, posZ + (double)f4, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
					world.spawnParticle("largeSmoke", posX - f3, posY, posZ + (double)f4, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
					break;
				case EAST:
					world.spawnParticle("smoke", posX + f3, posY, posZ + (double)f4, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
					world.spawnParticle("largeSmoke", posX + f3, posY, posZ + (double)f4, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
					break;
				case NORTH:
					world.spawnParticle("smoke", posX + (double)f4, posY, posZ - f3, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
					world.spawnParticle("largeSmoke", posX + (double)f4, posY, posZ - f3, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
					break;
				case SOUTH:
					world.spawnParticle("smoke", posX + (double)f4, posY, posZ + f3, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
					world.spawnParticle("largeSmoke", posX + (double)f4, posY, posZ + f3, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
			}

		}
	}

	@Override
	public boolean onInteracted(
		@NotNull World world,
		@NotNull TilePosc tilePos,
		@NotNull Player player,
		@Nullable Side side,
		double xHit,
		double yHit
	) {
		if (world.isClientSide) {
			return true;
		}
		TileEntityDwarvenForge tile = (TileEntityDwarvenForge) world.getTileEntity(tilePos);
		if (tile != null) {
			Catalyst.displayGui(
				player,
				tile,
				TLOTD.MOD_ID + ":gui/dwarven_forge"
			);
		}
		return true;
	}

	public static void updateFurnaceBlockState(@NotNull World world, @NotNull TilePos tilePos, boolean lit) {
		if (!(world.getTileEntity(tilePos) instanceof TileEntityDwarvenForge)) {
			String msg = "Blast Furnace is missing Tile Entity at " + String.valueOf(tilePos) + ", block will be removed!";
			if (Global.BUILD_CHANNEL.isUnstableBuild()) {
				throw new RuntimeException(msg);
			} else {
				world.setBlockTypeNotify(tilePos, Blocks.AIR);
				LOGGER.warn(msg);
			}
		} else {
			int meta = world.getBlockData(tilePos);
			Block<? extends BlockLogic> block = lit ? ModBlocks.DWARVEN_FORGE_BURNING : ModBlocks.DWARVEN_FORGE;
			world.setBlockTypeDataRaw(tilePos, block, meta);
			world.notifyBlockChange(tilePos, block);
		}
	}
}
