package net.tlotd.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.gui.IncubatorGUIHandler;
import net.tlotd.recipe.IncubatonRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class IncubatorBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int OUTPUT_SLOT = 2;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public IncubatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INCUBATOR_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> IncubatorBlockEntity.this.progress;
                    case 1 -> IncubatorBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> IncubatorBlockEntity.this.progress = value;
                    case 1 -> IncubatorBlockEntity.this.maxProgress = value;
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
        return Text.translatable("block.tlotd.incubator");
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
        return new IncubatorGUIHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }
        if (isOutputSlotEmptyOrReceivable()) {
            if (hasRecipe()) {
                progress++;
                markDirty(world, pos, state);

                if (progress >= maxProgress) {
                    craftItem();
                    resetProgress();
                    markDirty(world, pos, state);
                }
            } else {
                resetProgress();
            }
        } else {
            resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        Optional<IncubatonRecipe> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return;
        }
        ItemStack result = recipe.get().getResult(getWorld().getRegistryManager());
        if (result.isEmpty() || !canInsertItemIntoOutputSlot(result) || !canInsertAmountIntoOutputSlot(result)) {
            return;
        }
        removeStack(0, 1);
        removeStack(1, 1);
        ItemStack output = getStack(OUTPUT_SLOT);
        if (output.isEmpty()) {
            setStack(OUTPUT_SLOT, result.copy());
        } else {
            output.increment(result.getCount());
            markDirty();
        }
    }

    private boolean hasRecipe() {
        Optional<IncubatonRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResult(getWorld().getRegistryManager());
        return !result.isEmpty() && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result);
    }

    private Optional<IncubatonRecipe> getCurrentRecipe() {
        World world = getWorld();
        if (world == null) {
            return Optional.empty();
        }
        RecipeInput input = new IncubatorRecipeInput(getStack(0), getStack(1));
        return world.getRecipeManager().getFirstMatch(IncubatonRecipe.Type.INSTANCE, input, world).map(RecipeEntry::value);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack result) {
        ItemStack output = getStack(OUTPUT_SLOT);

        return output.isEmpty() || ItemStack.areItemsAndComponentsEqual(output, result);
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        ItemStack output = getStack(OUTPUT_SLOT);
        int currentCount = output.isEmpty() ? 0 : output.getCount();
        return currentCount + result.getCount() <= result.getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        ItemStack output = getStack(OUTPUT_SLOT);

        return output.isEmpty() || output.getCount() < output.getMaxCount();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    private record IncubatorRecipeInput(ItemStack first, ItemStack second) implements RecipeInput {
        @Override
        public ItemStack getStackInSlot(int slot) {
            return switch (slot) {
                case 0 -> first;
                case 1 -> second;
                default -> ItemStack.EMPTY;
            };
        }
        @Override
        public int getSize() {
            return 2;
        }
    }
}