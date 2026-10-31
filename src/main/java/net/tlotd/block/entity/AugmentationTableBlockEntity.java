package net.tlotd.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.gui.AugmentationTableGUIHandler;
import net.tlotd.recipe.AugmentationRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.tlotd.util.AugmentNbtHelper.*;

public class AugmentationTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(11, ItemStack.EMPTY);

    private static final int OUTPUT_SLOT = 10;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int progress2 = 0;
    private int maxProgress = 72;

    public AugmentationTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.AUGMENTATION_TABLE_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> AugmentationTableBlockEntity.this.progress;
                    case 1 -> AugmentationTableBlockEntity.this.progress2;
                    case 2 -> AugmentationTableBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> AugmentationTableBlockEntity.this.progress = value;
                    case 1 -> AugmentationTableBlockEntity.this.progress2 = value;
                    case 2 -> AugmentationTableBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 3;
            }
        };
    }

    public ItemStack getRenderStack() {
        if(!this.getStack(OUTPUT_SLOT).isEmpty()) {
            return this.getStack(OUTPUT_SLOT);
        } else if (!this.getStack(9).isEmpty()) {
            return this.getStack(9);
        } else if (!this.getStack(1).isEmpty()) {
            return this.getStack(1);
        } else if (!this.getStack(2).isEmpty()) {
            return this.getStack(2);
        } else if (!this.getStack(3).isEmpty()) {
            return this.getStack(3);
        } else if (!this.getStack(4).isEmpty()) {
            return this.getStack(4);
        } else if (!this.getStack(5).isEmpty()) {
            return this.getStack(5);
        } else if (!this.getStack(6).isEmpty()) {
            return this.getStack(6);
        } else if (!this.getStack(7).isEmpty()) {
            return this.getStack(7);
        } else if (!this.getStack(8).isEmpty()) {
            return this.getStack(8);
        } else if (!this.getStack(9).isEmpty()) {
            return this.getStack(9);
        } else {
            return this.getStack(0);
        }
    }

    @Override
    public void markDirty() {
        if (world != null) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
        super.markDirty();
    }

    @Override
    public Object getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.tlotd.augmentation_table");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("augmentation_table.augmentation_progress", progress);
        nbt.putInt("augmentation_table.deaugmentation_progress", progress2);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("augmentation_table.augmentation_progress");
        progress2 = nbt.getInt("augmentation_table.deaugmentation_progress");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AugmentationTableGUIHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }
        if (isOutputSlotEmptyOrReceivable()) {
            if (this.hasRecipe() && this.canApply()) {
                progress++;
                markDirty(world, pos, state);
                if (progress >= maxProgress) {
                    this.augmentItem();
                    this.resetProgress();
                }
            } else if (hasAugments(this.getStack(9))) {
                progress2++;
                markDirty(world, pos, state);
                if (progress2 >= maxProgress) {
                    this.cleanseItem();
                }
            } else {
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.progress2 = 0;
    }

    private boolean canApply() {
        if (world == null) {
            return false;
        }
        return getCurrentRecipe().map(recipe -> {
            ItemStack recipeOutput = recipe.getResult(world.getRegistryManager());
            return canApplyAugment(getStack(0), getAugment(recipeOutput), recipeOutput.getCount());
        }).orElse(false);
    }

    private void augmentItem() {
        if (world == null) {
            return;
        }
        Optional<AugmentationRecipe> currentRecipe = getCurrentRecipe();
        if (currentRecipe.isEmpty()) {
            return;
        }
        ItemStack recipeOutput = currentRecipe.get().getResult(world.getRegistryManager());
        ItemStack augmentedItem = getStack(0).copy();
        for (int i = 0; i < 9; i++) {
            removeStack(i, 1);
        }
        addOrUpdateAugment(augmentedItem, getAugment(recipeOutput), 1, recipeOutput.getCount());
        setStack(OUTPUT_SLOT, augmentedItem);
        world.playSound(null, getPos(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    private void cleanseItem() {
        ItemStack augmentItem = this.getStack(9).copy();
        this.removeStack(9, 1);
        removeAugments(augmentItem);
        this.setStack(OUTPUT_SLOT, augmentItem);
        world.playSound(null, getPos(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    public static String getAugment(ItemStack stack) {
        Identifier id = Registries.ITEM.getId(stack.getItem());
        String namespace = id.getNamespace();
        String path = id.getPath();
        if (path.startsWith("augment-")) {
            path = path.substring("augment-".length());
        }
        return namespace + ":" + path;
    }

    private boolean hasRecipe() {
        if (world == null) {
            return false;
        }
        return getCurrentRecipe().map(recipe -> {
            ItemStack result = recipe.getResult(world.getRegistryManager());
            return canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
        }).orElse(false);
    }

    private Optional<AugmentationRecipe> getCurrentRecipe() {
        if (world == null) {
            return Optional.empty();
        }
        List<ItemStack> inputs = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            inputs.add(getStack(i));
        }
        AugmentationRecipeInput input = new AugmentationRecipeInput(inputs);
        return world.getRecipeManager().getFirstMatch(AugmentationRecipe.Type.INSTANCE, input, world).map(RecipeEntry::value);
    }

    private record AugmentationRecipeInput(List<ItemStack> stacks) implements RecipeInput {
        @Override
        public ItemStack getStackInSlot(int slot) {
            return stacks.get(slot);
        }
        @Override
        public int getSize() {
            return stacks.size();
        }
        @Override
        public boolean isEmpty() {
            return stacks.stream().allMatch(ItemStack::isEmpty);
        }
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).isOf(item) || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }

    @Override
    @Nullable
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}