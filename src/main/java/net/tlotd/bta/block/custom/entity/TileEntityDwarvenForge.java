package net.tlotd.bta.block.custom.entity;

import com.mojang.nbt.tags.CompoundTag;
import com.mojang.nbt.tags.ListTag;
import java.util.List;
import java.util.Random;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicFurnaceBlast;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.motion.CarriedBlock;
import net.minecraft.core.crafting.LookupFuelFurnaceBlast;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryBlastFurnace;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.packet.Packet;
import net.minecraft.core.net.packet.PacketTileEntityData;
import net.minecraft.core.player.inventory.container.Container;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.ICarriable;
import net.minecraft.core.world.ICarrySource;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import net.tlotd.bta.block.ModBlocks;
import net.tlotd.bta.block.custom.BlockLogicDwarvenForge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TileEntityDwarvenForge extends TileEntity implements Container, ICarrySource {
	public static final int SLOT_LEFT = 0;
	public static final int SLOT_RIGHT = 1;
	public static final int SLOT_FUEL = 2;
	public static final int SLOT_RESULT = 3;
	private final Random random = new Random();
	protected ItemStack[] inventory = new ItemStack[4];
	public int maxBurnTime = 0;
	public int currentCookTime = 0;
	public int maxCookTime = 100;
	public int currentBurnTime = 0;

	public int getContainerSize() {
		return this.inventory.length;
	}

	public @Nullable ItemStack getItem(int slot) {
		return this.inventory[slot];
	}

	public @Nullable ItemStack removeItem(int slot, int takeAmount) {
		if (this.inventory[slot] != null) {
			if (this.inventory[slot].stackSize <= takeAmount) {
				ItemStack itemstack = this.inventory[slot];
				this.inventory[slot] = null;
				if (this.worldObj != null && slot == 3) {
					this.worldObj.markBlockNeedsUpdate(this.tilePos.x, this.tilePos.y, this.tilePos.z);
				}

				return itemstack;
			} else {
				ItemStack itemstack1 = this.inventory[slot].splitStack(takeAmount);
				if (this.inventory[slot].stackSize <= 0) {
					this.inventory[slot] = null;
					if (this.worldObj != null && slot == 3) {
						this.worldObj.markBlockNeedsUpdate(this.tilePos.x, this.tilePos.y, this.tilePos.z);
					}
				}

				return itemstack1;
			}
		} else {
			return null;
		}
	}

	public void setItem(int slot, @Nullable ItemStack stack) {
		this.inventory[slot] = stack;
		if (stack != null && stack.stackSize > this.getMaxStackSize()) {
			stack.stackSize = this.getMaxStackSize();
		}

		if (this.worldObj != null && slot == 3 && stack == null) {
			this.worldObj.markBlockNeedsUpdate(this.tilePos.x, this.tilePos.y, this.tilePos.z);
		}

	}

	public @NotNull String getNameTranslationKey() {
		return "container.tlotd.dwarven_forge.name";
	}

	public void readAdditionalData(@NotNull CompoundTag compoundTag) {
		ListTag itemsTag = compoundTag.getList("Items");
		this.inventory = new ItemStack[this.getContainerSize()];

		for(int i = 0; i < itemsTag.tagCount(); ++i) {
			CompoundTag itemTag = (CompoundTag)itemsTag.tagAt(i);
			byte slot = itemTag.getByte("Slot");
			if (slot >= 0 && slot < this.inventory.length) {
				this.inventory[slot] = ItemStack.readItemStackFromNbt(itemTag);
			}
		}

		this.currentBurnTime = compoundTag.getShort("BurnTime");
		this.currentCookTime = compoundTag.getShort("CookTime");
		this.maxBurnTime = compoundTag.getShort("MaxBurnTime");
	}

	public void writeAdditionalData(@NotNull CompoundTag compoundTag) {
		compoundTag.putShort("BurnTime", (short)this.currentBurnTime);
		compoundTag.putShort("CookTime", (short)this.currentCookTime);
		compoundTag.putShort("MaxBurnTime", (short)this.maxBurnTime);
		ListTag itemsTag = new ListTag();

		for(int slot = 0; slot < this.inventory.length; ++slot) {
			if (this.inventory[slot] != null) {
				CompoundTag itemTag = new CompoundTag();
				itemTag.putByte("Slot", (byte)slot);
				this.inventory[slot].writeToNBT(itemTag);
				itemsTag.addTag(itemTag);
			}
		}

		compoundTag.put("Items", itemsTag);
	}

	public int getMaxStackSize() {
		return 64;
	}

	public int getCookProgressScaled(int i) {
		return this.maxCookTime == 0 ? 0 : this.currentCookTime * i / this.maxCookTime;
	}

	public int getBurnTimeRemainingScaled(int i) {
		return this.maxBurnTime == 0 ? 0 : this.currentBurnTime * i / this.maxBurnTime;
	}

	public boolean isBurning() {
		return this.currentBurnTime > 0;
	}

	public void tick() {
		boolean isBurnTimeHigherThan0 = this.currentBurnTime > 0;
		boolean furnaceUpdated = false;
		if (this.currentBurnTime > 0) {
			--this.currentBurnTime;
		}

		if (this.worldObj == null || !this.worldObj.isClientSide) {
			if ((this.worldObj == null || this.worldObj.getBlockId(this.tilePos.x, this.tilePos.y, this.tilePos.z) == Blocks.FURNACE_BLAST_IDLE.id()) && this.currentBurnTime == 0 && this.inventory[0] == null && this.inventory[1] == null && this.inventory[2] != null && this.inventory[2].itemID == Blocks.COBBLE_NETHERRACK.id()) {
				--this.inventory[2].stackSize;
				if (this.inventory[2].stackSize <= 0) {
					this.inventory[2] = null;
				}

				this.updateFurnace(true);
				furnaceUpdated = true;
			}

			if (this.currentBurnTime == 0 && this.canSmelt()) {
				this.maxBurnTime = this.currentBurnTime = this.getBurnTimeFromItem(this.inventory[2]);
				if (this.currentBurnTime > 0) {
					furnaceUpdated = true;
					if (this.inventory[2] != null) {
						--this.inventory[2].stackSize;
						if (this.inventory[2].stackSize <= 0) {
							this.inventory[2] = null;
						}
					}
				}
			}

			if (this.isBurning() && this.canSmelt()) {
				++this.currentCookTime;
				if (this.currentCookTime == this.maxCookTime) {
					this.currentCookTime = 0;
					this.smeltItem();
					furnaceUpdated = true;
				}
			} else {
				this.currentCookTime = 0;
			}

			if (isBurnTimeHigherThan0 != this.currentBurnTime > 0) {
				furnaceUpdated = true;
				this.updateFurnace(false);
			}
		}

		if (furnaceUpdated) {
			this.setChanged();
		}

	}

	private boolean canSmelt() {
		if (this.inventory[0] == null && this.inventory[1] == null) {
			return false;
		} else {
			List<RecipeEntryBlastFurnace> list = Registries.RECIPES.getAllBlastFurnaceRecipes();
			ItemStack itemstack = null;

			for(RecipeEntryBlastFurnace recipeEntryBase : list) {
				if (recipeEntryBase != null && recipeEntryBase.matches(this.inventory[0], this.inventory[1])) {
					itemstack = (ItemStack)recipeEntryBase.getOutput();
				}
			}

			if (itemstack == null) {
				return false;
			} else if (this.inventory[3] == null) {
				return true;
			} else if (!this.inventory[3].isItemEqual(itemstack)) {
				return false;
			} else if (this.inventory[3].stackSize < this.getMaxStackSize() && this.inventory[3].stackSize < this.inventory[3].getMaxStackSize()) {
				return true;
			} else {
				return this.inventory[3].stackSize < itemstack.getMaxStackSize();
			}
		}
	}

	public void smeltItem() {
		if (this.canSmelt()) {
			List<RecipeEntryBlastFurnace> list = Registries.RECIPES.getAllBlastFurnaceRecipes();
			ItemStack itemstack = null;

			for(RecipeEntryBlastFurnace recipeEntryBase : list) {
				if (recipeEntryBase != null && recipeEntryBase.matches(this.inventory[0], this.inventory[1])) {
					itemstack = (ItemStack)recipeEntryBase.getOutput();
				}
			}

			if (this.inventory[3] == null && itemstack != null) {
				this.inventory[3] = itemstack.copy();
			} else if (this.inventory[3] != null && itemstack != null && this.inventory[3].itemID == itemstack.itemID) {
				ItemStack var10000 = this.inventory[3];
				var10000.stackSize += itemstack.stackSize;
			}

			if (this.inventory[0] != null) {
				--this.inventory[0].stackSize;
				if (this.inventory[0].stackSize <= 0) {
					this.inventory[0] = null;
				}
			}

			if (this.inventory[1] != null) {
				--this.inventory[1].stackSize;
				if (this.inventory[1].stackSize <= 0) {
					this.inventory[1] = null;
				}
			}

		}
	}

	protected void updateFurnace(boolean forceLit) {
		if (this.worldObj != null) {
			BlockLogicDwarvenForge.updateFurnaceBlockState(
				this.worldObj,
				new TilePos(
					this.tilePos.x,
					this.tilePos.y,
					this.tilePos.z
				),
				forceLit | this.currentBurnTime > 0
			);
		} else if (this.carriedBlock != null) {
			this.carriedBlock.blockId =
				forceLit | this.currentBurnTime > 0 ? ModBlocks.DWARVEN_FORGE_BURNING.id() : ModBlocks.DWARVEN_FORGE.id();
		}
	}

	private int getBurnTimeFromItem(ItemStack itemStack) {
		return itemStack == null ? 0 : LookupFuelFurnaceBlast.instance.getFuelYield(itemStack);
	}

	public boolean stillValid(@NotNull Player player) {
		if (this.worldObj != null && this.worldObj.getTileEntity(this.tilePos.x, this.tilePos.y, this.tilePos.z) == this) {
			return player.distanceToSqr((double)this.tilePos.x + (double)0.5F, (double)this.tilePos.y + (double)0.5F, (double)this.tilePos.z + (double)0.5F) <= (double)64.0F;
		} else {
			return false;
		}
	}

	public void dropContents(World world, int x, int y, int z) {
		super.dropContents(world, x, y, z);

		for(int slot = 0; slot < this.getContainerSize(); ++slot) {
			ItemStack item = this.getItem(slot);
			if (item != null) {
				float rx = this.random.nextFloat() * 0.8F + 0.1F;
				float ry = this.random.nextFloat() * 0.8F + 0.1F;
				float rz = this.random.nextFloat() * 0.8F + 0.1F;

				while(item.stackSize > 0) {
					int stackSize = this.random.nextInt(21) + 10;
					if (stackSize > item.stackSize) {
						stackSize = item.stackSize;
					}

					item.stackSize -= stackSize;
					EntityItem entityItem = new EntityItem(world, (double)((float)x + rx), (double)((float)y + ry), (double)((float)z + rz), new ItemStack(item.itemID, stackSize, item.getMetadata()));
					float velocityScale = 0.05F;
					entityItem.xd = (double)((float)this.random.nextGaussian() * 0.05F);
					entityItem.yd = (double)((float)this.random.nextGaussian() * 0.05F + 0.2F);
					entityItem.zd = (double)((float)this.random.nextGaussian() * 0.05F);
					world.entityJoinedWorld(entityItem);
				}
			}
		}

	}

	public Packet getDescriptionPacket() {
		return new PacketTileEntityData(this);
	}

	public void sort() {
	}

	public void heldTick(World world, Entity holder) {
		this.tick();
	}

	public boolean tryPlace(World world, Entity holder, int blockX, int blockY, int blockZ, Side side, double xPlaced, double yPlaced) {
		boolean success = super.tryPlace(world, holder, blockX, blockY, blockZ, side, xPlaced, yPlaced);
		if (success) {
			this.updateFurnace(false);
		}

		return success;
	}

	public @Nullable ICarriable pickup(@NotNull World world, @NotNull Entity holder, @NotNull TilePosc tilePos_) {
		return super.pickup(world, holder, tilePos_);
	}

	public CarriedBlock getCarriedEntry(World world, Entity holder, Block<?> currentBlock, int currentMeta) {
		return super.getCarriedEntry(world, holder, currentBlock, currentMeta & -8 | 2);
	}
}
