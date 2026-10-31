package net.tlotd.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.gui.KeycardProgrammerGUIHandler;
import net.tlotd.util.ModTags;
import org.jetbrains.annotations.Nullable;

public class KeycardProgrammerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;

    private int progress = 0;
    private int maxProgress = 72;

    public KeycardProgrammerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.KEYCARD_PROGRAMMER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> KeycardProgrammerBlockEntity.this.progress;
                    case 1 -> KeycardProgrammerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> KeycardProgrammerBlockEntity.this.progress = value;
                    case 1 -> KeycardProgrammerBlockEntity.this.maxProgress = value;
                }
            }
            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public Object getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.tlotd.keycard_programmer");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("progress");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new KeycardProgrammerGUIHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }
        if (keycardIsPresent()) {
            progress++;
            markDirty(world, pos, state);
            if (progress >= maxProgress) {
                programKeycard();
                resetProgress();
                markDirty(world, pos, state);
            }
        } else {
            resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void programKeycard() {
        ItemStack keycard = getStack(0).copy();
        NbtCompound customData = keycard.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT).copyNbt();
        customData.putString("password", getStack(1).toString());
        keycard.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(customData));
        setStack(2, keycard);
        setStack(0, ItemStack.EMPTY);
    }

    private boolean keycardIsPresent() {
        return getStack(0).isIn(ModTags.Items.KEYCARDS) && !getStack(1).isEmpty() && getStack(2).isEmpty();
    }

    private void resetProgress() {
        progress = 0;
    }
}