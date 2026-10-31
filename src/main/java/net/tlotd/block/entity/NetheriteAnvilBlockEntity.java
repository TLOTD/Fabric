package net.tlotd.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.tlotd.block.custom.NetheriteAnvilBlock;
import net.tlotd.gui.NetheriteAnvilGUIHandler;
import net.tlotd.recipe.NetheriteSmithingRecipe;
import net.tlotd.util.ItemHeatHelper;
import net.tlotd.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NetheriteAnvilBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);

    private static final int OUTPUT_SLOT = 6;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public NetheriteAnvilBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.NETHERITE_ANVIL_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> NetheriteAnvilBlockEntity.this.progress;
                    case 1 -> NetheriteAnvilBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> NetheriteAnvilBlockEntity.this.progress = value;
                    case 1 -> NetheriteAnvilBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    public Direction facing() {
        return getCachedState().get(NetheriteAnvilBlock.FACING);
    }

    public ItemStack getRenderStack() {
        if(!this.getStack(OUTPUT_SLOT).isEmpty()) {
            return this.getStack(OUTPUT_SLOT);
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
        return Text.translatable("block.tlotd.netherite_anvil");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("netherite_anvil.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("netherite_anvil.progress");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new NetheriteAnvilGUIHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }
        if (isOutputSlotEmptyOrReceivable()) {
            if (this.hasRecipe()) {
                progress++;
                markDirty(world, pos, state);
                if (progress >= maxProgress) {
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
                tryCoolingItems();
            }
        } else {
            this.resetProgress();
            tryCoolingItems();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void tryCoolingItems() {
        for (int i = 1; i <= 5; i++) {
            ItemStack stack = getStack(i);
            if (!stack.isEmpty()) {
                ItemHeatHelper.editTemperature(stack, -1);
            }
        }
    }

    private void craftItem() {
        if (world == null) {
            return;
        }
        Optional<NetheriteSmithingRecipe> currentRecipe = getCurrentRecipe();
        if (currentRecipe.isEmpty() || !hasRecipe()) {
            resetProgress();
            return;
        }
        ItemStack result = currentRecipe.get().getResult(world.getRegistryManager());
        for (int i = 1; i <= 5; i++) {
            removeStack(i, 1);
        }
        ItemStack output = getStack(OUTPUT_SLOT);
        if (output.isEmpty()) {
            setStack(OUTPUT_SLOT, result.copy());
        } else {
            output.increment(result.getCount());
        }
        world.playSound(null, getPos(), SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    private boolean hasRecipe() {
        if (world == null) {
            return false;
        }
        return getCurrentRecipe().map(recipe -> {
            ItemStack result = recipe.getResult(world.getRegistryManager());
            return canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem()) && hasRequiredHeat(recipe);
        }).orElse(false);
    }

    private boolean hasRequiredHeat(NetheriteSmithingRecipe recipe) {
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            Ingredient ingredient = recipe.getIngredients().get(i);
            if (ingredient.isEmpty()) {
                continue;
            }
            ItemStack stack = getStack(i);
            if (!ingredient.test(stack)) {
                continue;
            }
            if (stack.isIn(ModTags.Items.SMITHING_HEAT_5800)) {
                int temp = ItemHeatHelper.getTemperature(stack);
                if (temp < 5800) {
                    return false;
                }
            } else if (stack.isIn(ModTags.Items.SMITHING_HEAT_4200)) {
                int temp = ItemHeatHelper.getTemperature(stack);
                if (temp < 4200) {
                    return false;
                }
            } else if (stack.isIn(ModTags.Items.SMITHING_HEAT_2600)) {
                int temp = ItemHeatHelper.getTemperature(stack);
                if (temp < 2600) {
                    return false;
                }
            } else if (stack.isIn(ModTags.Items.SMITHING_HEAT_1800)) {
                int temp = ItemHeatHelper.getTemperature(stack);
                if (temp < 1800) {
                    return false;
                }
            }
        }
        return true;
    }

    private record NetheriteAnvilRecipeInput(List<ItemStack> stacks) implements RecipeInput {
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

    private Optional<NetheriteSmithingRecipe> getCurrentRecipe() {
        if (world == null) {
            return Optional.empty();
        }
        List<ItemStack> inputs = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            inputs.add(getStack(i));
        }
        NetheriteAnvilRecipeInput input = new NetheriteAnvilRecipeInput(inputs);
        return world.getRecipeManager().getFirstMatch(NetheriteSmithingRecipe.Type.INSTANCE, input, world).map(RecipeEntry::value);
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
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}