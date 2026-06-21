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
import net.tlotd.block.entity.DwarvenForgeBlockEntity;
import net.tlotd.util.ModTags;

public class DwarvenForgeGUIHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final DwarvenForgeBlockEntity blockEntity;

    public DwarvenForgeGUIHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(4));
    }

    public DwarvenForgeGUIHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModGUIHandlers.DWARVEN_FORGE_GUI_HANDLER, syncId);
        checkSize(((Inventory) blockEntity),10);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = propertyDelegate;
        this.blockEntity = ((DwarvenForgeBlockEntity) blockEntity);

        this.addSlot(new TagSlot(inventory, 0, 44, 18, ModTags.Items.BURNS_IN_FORGE));
        this.addSlot(new TagSlot(inventory, 1, 44, 36, ModTags.Items.BURNS_IN_FORGE));
        this.addSlot(new TagSlot(inventory, 2, 44, 54, ModTags.Items.BURNS_IN_FORGE));
        this.addSlot(new TagSlot(inventory, 3, 44, 72, ModTags.Items.BURNS_IN_FORGE));

        this.addSlot(new TagSlot(inventory, 4, 79, 62, ModTags.Items.FIRE_BASE_FORGE));
        this.addSlot(new TagSlot(inventory, 5, 97, 63, ModTags.Items.FIRE_BASE_FORGE));
        this.addSlot(new TagSlot(inventory, 6, 115, 62, ModTags.Items.FIRE_BASE_FORGE));

        this.addSlot(new TagSlot(inventory, 7, 79, 28, ModTags.Items.HEATABLE_ITEM));
        this.addSlot(new TagSlot(inventory, 8, 97, 27, ModTags.Items.HEATABLE_ITEM));
        this.addSlot(new TagSlot(inventory, 9, 115, 28, ModTags.Items.HEATABLE_ITEM));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(propertyDelegate);
    }

    public boolean isHeating() {
        return propertyDelegate.get(0) > 0;
    }

    public boolean hasMaxTemp() {
        return propertyDelegate.get(1) > 0;
    }

    public int getFireTexturePos() {
        if(propertyDelegate.get(1)>3000) {
            return 28;
        } else if (propertyDelegate.get(1)>1500) {
            return 14;
        } else return 0;
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        if (progress <= 0) {
            return 0;
        }
        if (progress <= 1500) {
            return progress * 36 / 1500;
        }
        if (progress <= 3000) {
            return 36 + (progress - 1500) * 12 / 1500;
        }
        return 48 + (progress - 3000) * 24 / 3000;
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
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 103 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 161));
        }
    }
}