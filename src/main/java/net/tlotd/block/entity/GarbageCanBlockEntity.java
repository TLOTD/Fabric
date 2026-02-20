package net.tlotd.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class GarbageCanBlockEntity extends BlockEntity implements ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    public GarbageCanBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GARBAGE_CAN_BLOCK_ENTITY, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (slot == 0 && !stack.isEmpty()) {
            inventory.set(3, inventory.get(2));
            inventory.set(2, inventory.get(1));
            inventory.set(1, stack.copy());
            inventory.set(0, ItemStack.EMPTY);

        } else {
            inventory.set(slot, stack);
        }
        markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }
}