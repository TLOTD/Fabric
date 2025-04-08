package net.tlotd.gui;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.tlotd.block.entity.WitchingTableBlockEntity;
import net.tlotd.util.FluidStack;

public class WitchingTableGUIHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final WitchingTableBlockEntity blockEntity;
    public FluidStack fluidStack;

    public WitchingTableGUIHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(2));
    }

    public WitchingTableGUIHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {
        super(ModGUIHandlers.WITCHING_TABLE_GUI_HANDLER, syncId);
        checkSize(((Inventory) blockEntity),11);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = ((WitchingTableBlockEntity) blockEntity);
        this.fluidStack = new FluidStack(((WitchingTableBlockEntity) blockEntity).fluidStorage.variant, ((WitchingTableBlockEntity) blockEntity).fluidStorage.amount);

        this.addSlot(new Slot(inventory, 0, 47, 16));
        this.addSlot(new Slot(inventory, 1, 66, 15));
        this.addSlot(new Slot(inventory, 2, 85, 16));

        this.addSlot(new Slot(inventory, 3, 46, 35));
        this.addSlot(new Slot(inventory, 4, 66, 35));
        this.addSlot(new Slot(inventory, 5, 86, 35));

        this.addSlot(new Slot(inventory, 6, 47, 54));
        this.addSlot(new Slot(inventory, 7, 66, 55));
        this.addSlot(new Slot(inventory, 8, 85, 54));

        this.addSlot(new Slot(inventory, 9, 26, 15));
        this.addSlot(new Slot(inventory, 10, 26, 55));

        this.addSlot(new Slot(inventory, 11, 152, 35));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(arrayPropertyDelegate);
    }

    public void setFluid(FluidStack stack) {
        fluidStack = stack;
    }

    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int progressArrowSize = 42;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
