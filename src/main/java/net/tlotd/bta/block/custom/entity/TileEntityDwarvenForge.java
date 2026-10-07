package net.tlotd.bta.block.custom.entity;

import com.mojang.nbt.tags.CompoundTag;
import com.mojang.nbt.tags.ListTag;

import java.util.List;
import java.util.Random;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.motion.CarriedBlock;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.crafting.LookupFuelFurnaceBlast;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryBlastFurnace;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryFurnace;
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
import net.tlotd.bta.block.custom.BlockLogicDwarvenForge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TileEntityDwarvenForge extends TileEntity implements Container, ICarrySource {

	private static final int FUEL_START = 0;
	private static final int FUEL_END = 3;

	private static final int BASE_START = 4;
	private static final int BASE_END = 6;

	private static final int RECIPE_START = 7;
	private static final int RECIPE_END = 8;

	private static final int RECIPE_RESULT = 10;

	public enum ForgeMode {
		NONE, FURNACE, BLAST_FURNACE
	}

	private ForgeMode currentMode = ForgeMode.NONE;

	private final Random random = new Random();

	public ItemStack[] inventory = new ItemStack[11];

	public int maxBurnTime = 0;
	public int currentCookTime = 0;
	public int maxCookTime = 100;
	public int currentBurnTime = 0;

	public ForgeMode getCurrentMode() {
		return currentMode;
	}

	public int getContainerSize() {
		return this.inventory.length;
	}

	public @Nullable ItemStack getItem(int slot) {
		return this.inventory[slot];
	}

	private ForgeMode findRecipeMode() {
		if (this.inventory[RECIPE_START] == null) {
			return ForgeMode.NONE;
		}
		if (this.hasHellfireBase()) {
			List<RecipeEntryBlastFurnace> blastRecipes = Registries.RECIPES.getAllBlastFurnaceRecipes();
			for (RecipeEntryBlastFurnace recipe : blastRecipes) {
				if (recipe != null && recipe.matches(this.inventory[RECIPE_START], this.inventory[RECIPE_END])) {
					return ForgeMode.BLAST_FURNACE;
				}
			}
		}
		List<RecipeEntryFurnace> furnaceRecipes = Registries.RECIPES.getAllFurnaceRecipes();
		for (RecipeEntryFurnace recipe : furnaceRecipes) {
			if (recipe != null && recipe.matches(this.inventory[RECIPE_START])) {
				return ForgeMode.FURNACE;
			}
		}
		return ForgeMode.NONE;
	}

	public @Nullable ItemStack removeItem(int slot, int takeAmount) {
		if (this.inventory[slot] != null) {
			if (this.inventory[slot].stackSize <= takeAmount) {
				ItemStack itemstack = this.inventory[slot];
				this.inventory[slot] = null;
				if (this.worldObj != null && slot == RECIPE_RESULT) {
					this.worldObj.markBlockNeedsUpdate(this.tilePos.x, this.tilePos.y, this.tilePos.z);
				}
				return itemstack;
			} else {
				ItemStack itemstack1 = this.inventory[slot].splitStack(takeAmount);
				if (this.inventory[slot].stackSize <= 0) {
					this.inventory[slot] = null;
					if (this.worldObj != null && slot == RECIPE_RESULT) {
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
		if (this.worldObj != null && slot == RECIPE_RESULT && stack == null) {
			this.worldObj.markBlockNeedsUpdate(this.tilePos.x, this.tilePos.y, this.tilePos.z);
		}
	}

	public @NotNull String getNameTranslationKey() {
		return "container.tlotd.dwarven_forge.name";
	}

	public void readAdditionalData(@NotNull CompoundTag compoundTag) {
		ListTag itemsTag = compoundTag.getList("Items");
		this.inventory = new ItemStack[this.getContainerSize()];
		for (int i = 0; i < itemsTag.tagCount(); ++i) {
			CompoundTag itemTag = (CompoundTag) itemsTag.tagAt(i);
			byte slot = itemTag.getByte("Slot");
			if (slot >= 0 && slot < this.inventory.length) {
				this.inventory[slot] = ItemStack.readItemStackFromNbt(itemTag);
			}
		}
		this.currentBurnTime = compoundTag.getShort("BurnTime");
		this.currentCookTime = compoundTag.getShort("CookTime");
		this.maxBurnTime = compoundTag.getShort("MaxBurnTime");
		this.currentMode = ForgeMode.valueOf(compoundTag.getString("CurrentMode"));
	}

	public void writeAdditionalData(@NotNull CompoundTag compoundTag) {
		compoundTag.putShort("BurnTime", (short) this.currentBurnTime);
		compoundTag.putShort("CookTime", (short) this.currentCookTime);
		compoundTag.putShort("MaxBurnTime", (short) this.maxBurnTime);
		compoundTag.putString("CurrentMode", String.valueOf(this.currentMode));
		ListTag itemsTag = new ListTag();
		for (int slot = 0; slot < this.inventory.length; ++slot) {
			if (this.inventory[slot] != null) {
				CompoundTag itemTag = new CompoundTag();
				itemTag.putByte("Slot", (byte) slot);
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
			if ((this.worldObj == null || this.worldObj.getBlockId(this.tilePos.x, this.tilePos.y, this.tilePos.z) == Blocks.FURNACE_BLAST_IDLE.id()) && this.currentBurnTime == 0 && this.inventory[RECIPE_START] == null && this.inventory[RECIPE_END] == null && this.inventory[FUEL_START] != null && this.inventory[FUEL_START].itemID == Blocks.COBBLE_NETHERRACK.id()) {
				--this.inventory[FUEL_START].stackSize;
				if (this.inventory[FUEL_START].stackSize <= 0) {
					this.inventory[FUEL_START] = null;
				}
				this.updateFurnace();
				furnaceUpdated = true;
			}
			if (this.currentBurnTime == 0) {
				this.currentMode = this.findRecipeMode();
				if (this.currentMode != ForgeMode.NONE && this.canSmelt()) {
					this.maxBurnTime = this.currentBurnTime = this.getBurnTimeFromItem(this.inventory[FUEL_START]);
					if (this.currentBurnTime > 0) {
						furnaceUpdated = true;
						if (this.inventory[FUEL_START] != null) {
							--this.inventory[FUEL_START].stackSize;
							if (this.inventory[FUEL_START].stackSize <= 0) {
								this.inventory[FUEL_START] = null;
							}
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
				this.updateFurnace();
			}
		}
		if (furnaceUpdated) {
			this.setChanged();
		}
	}

	private boolean hasHellfireBase() {
		for (int slot = BASE_START; slot <= BASE_END; ++slot) {
			if (this.inventory[slot] == null || !(this.inventory[slot].itemID == Blocks.MAGMA.id() || this.inventory[slot].itemID == Blocks.SOULSAND.id() || this.inventory[slot].itemID == Blocks.SOULSCHIST.id())) {
				return false;
			}
		}
		return true;
	}

	private boolean canSmelt() {
		if (this.inventory[RECIPE_START] == null) {
			return false;
		}
		ItemStack itemstack = this.getRecipeOutput();
		if (itemstack == null) {
			return false;
		}
		if (this.inventory[RECIPE_RESULT] == null) {
			return true;
		}
		if (!this.inventory[RECIPE_RESULT].isItemEqual(itemstack)) {
			return false;
		}
		if (this.inventory[RECIPE_RESULT].stackSize < this.getMaxStackSize() && this.inventory[RECIPE_RESULT].stackSize < this.inventory[RECIPE_RESULT].getMaxStackSize()) {
			return true;
		}
		return this.inventory[RECIPE_RESULT].stackSize < itemstack.getMaxStackSize();
	}

	public void smeltItem() {
		RecipeType recipeType = this.getRecipeType();
		if (recipeType == RecipeType.NONE) {
			return;
		}
		ItemStack itemstack = this.getRecipeOutput();
		if (itemstack == null) {
			return;
		}
		if (this.inventory[RECIPE_RESULT] == null) {
			this.inventory[RECIPE_RESULT] = itemstack.copy();
		} else if (this.inventory[RECIPE_RESULT].itemID == itemstack.itemID) {
			this.inventory[RECIPE_RESULT].stackSize += itemstack.stackSize;
		}
		if (this.inventory[RECIPE_START] != null) {
			--this.inventory[RECIPE_START].stackSize;

			if (this.inventory[RECIPE_START].stackSize <= 0) {
				this.inventory[RECIPE_START] = null;
			}
		}
		if (recipeType == RecipeType.BLAST_FURNACE && this.inventory[RECIPE_END] != null) {
			--this.inventory[RECIPE_END].stackSize;

			if (this.inventory[RECIPE_END].stackSize <= 0) {
				this.inventory[RECIPE_END] = null;
			}
		}
	}

	private enum RecipeType {
		NONE, BLAST_FURNACE, FURNACE
	}

	private RecipeType getRecipeType() {
		if (this.inventory[RECIPE_START] == null) {
			return RecipeType.NONE;
		}
		if (this.hasHellfireBase()) {
			List<RecipeEntryBlastFurnace> blastRecipes = Registries.RECIPES.getAllBlastFurnaceRecipes();
			for (RecipeEntryBlastFurnace recipe : blastRecipes) {
				if (recipe != null && recipe.matches(this.inventory[RECIPE_START], this.inventory[RECIPE_END])) {
					return RecipeType.BLAST_FURNACE;
				}
			}
		}
		List<RecipeEntryFurnace> furnaceRecipes = Registries.RECIPES.getAllFurnaceRecipes();
		for (RecipeEntryFurnace recipe : furnaceRecipes) {
			if (recipe != null && recipe.matches(this.inventory[RECIPE_START])) {
				return RecipeType.FURNACE;
			}
		}
		return RecipeType.NONE;
	}

	private @Nullable ItemStack getRecipeOutput() {
		RecipeType type = this.getRecipeType();
		if (type == RecipeType.BLAST_FURNACE) {
			List<RecipeEntryBlastFurnace> recipes = Registries.RECIPES.getAllBlastFurnaceRecipes();
			for (RecipeEntryBlastFurnace recipe : recipes) {
				if (recipe != null && recipe.matches(this.inventory[RECIPE_START], this.inventory[RECIPE_END])) {
					return recipe.getOutput();
				}
			}
		} else if (type == RecipeType.FURNACE) {
			List<RecipeEntryFurnace> recipes = Registries.RECIPES.getAllFurnaceRecipes();
			for (RecipeEntryFurnace recipe : recipes) {
				if (recipe != null && recipe.matches(this.inventory[RECIPE_START])) {
					return recipe.getOutput();
				}
			}
		}
		return null;
	}

	protected void updateFurnace() {
		if (this.worldObj != null) {
			BlockLogicDwarvenForge.updateFurnaceBlockState(this.worldObj, new TilePos(this.tilePos.x, this.tilePos.y, this.tilePos.z), this.currentMode);
		}
	}

	private int getBurnTimeFromItem(ItemStack itemStack) {
		if (itemStack == null) {
			return 0;
		}
		if (this.currentMode == ForgeMode.BLAST_FURNACE) {
			return LookupFuelFurnaceBlast.instance.getFuelYield(itemStack);
		}
		if (this.currentMode == ForgeMode.FURNACE) {
			return LookupFuelFurnace.instance.getFuelYield(itemStack);
		}
		return 0;
	}

	public boolean stillValid(@NotNull Player player) {
		if (this.worldObj != null && this.worldObj.getTileEntity(this.tilePos.x, this.tilePos.y, this.tilePos.z) == this) {
			return player.distanceToSqr((double) this.tilePos.x + (double) 0.5F, (double) this.tilePos.y + (double) 0.5F, (double) this.tilePos.z + (double) 0.5F) <= (double) 64.0F;
		} else {
			return false;
		}
	}

	public void dropContents(World world, int x, int y, int z) {
		super.dropContents(world, x, y, z);
		for (int slot = 0; slot < this.getContainerSize(); ++slot) {
			ItemStack item = this.getItem(slot);
			if (item != null) {
				float rx = this.random.nextFloat() * 0.8F + 0.1F;
				float ry = this.random.nextFloat() * 0.8F + 0.1F;
				float rz = this.random.nextFloat() * 0.8F + 0.1F;
				while (item.stackSize > 0) {
					int stackSize = this.random.nextInt(21) + 10;
					if (stackSize > item.stackSize) {
						stackSize = item.stackSize;
					}
					item.stackSize -= stackSize;
					EntityItem entityItem = new EntityItem(world, (float) x + rx, (float) y + ry, (float) z + rz, new ItemStack(item.itemID, stackSize, item.getMetadata()));
					float velocityScale = 0.05F;
					entityItem.xd = (float) this.random.nextGaussian() * 0.05F;
					entityItem.yd = (float) this.random.nextGaussian() * 0.05F + 0.2F;
					entityItem.zd = (float) this.random.nextGaussian() * 0.05F;
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
			this.updateFurnace();
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
