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
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.gui.NetheriteAnvilGUIHandler;
import net.tlotd.recipe.NetheriteSmithingRecipe;
import net.tlotd.util.ItemHeatHelper;
import net.tlotd.util.ModTags;
import net.tlotd.world.ModGlobalState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static net.tlotd.world.dimension.ModDimensions.LUNA_LEVEL_KEY;

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

    public ItemStack getRenderStack() {
        if(!this.getStack(OUTPUT_SLOT).isEmpty()) {
            return this.getStack(OUTPUT_SLOT);
        } else if (!this.getStack(2).isEmpty()) {
            return this.getStack(2);
        } else if (!this.getStack(3).isEmpty()) {
            return this.getStack(3);
        } else if (!this.getStack(4).isEmpty()) {
            return this.getStack(4);
        } else if (!this.getStack(5).isEmpty()) {
            return this.getStack(5);
        } else if (!this.getStack(1).isEmpty()) {
            return this.getStack(1);
        } else {
            return this.getStack(0);
        }
    }

    @Override
    public void markDirty() {
        world.updateListeners(pos,getCachedState(),getCachedState(),3);
        super.markDirty();
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);
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
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("netherite_anvil.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
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
        if (!this.getStack(1).isEmpty()) {
            ItemHeatHelper.editTemperature(this.getStack(1), -1);
        }
        if (!this.getStack(2).isEmpty()) {
            ItemHeatHelper.editTemperature(this.getStack(2), -1);
        }
        if (!this.getStack(3).isEmpty()) {
            ItemHeatHelper.editTemperature(this.getStack(3), -1);
        }
        if (!this.getStack(4).isEmpty()) {
            ItemHeatHelper.editTemperature(this.getStack(3), -1);
        }
        if (!this.getStack(5).isEmpty()) {
            ItemHeatHelper.editTemperature(this.getStack(3), -1);
        }
    }

    private void craftItem() {
        Optional<NetheriteSmithingRecipe> recipe = getCurrentRecipe();

        this.removeStack(1, 1);
        this.removeStack(2, 1);
        this.removeStack(3, 1);
        this.removeStack(4, 1);
        this.removeStack(5, 1);

        this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().getOutput(null).getItem(), getStack(OUTPUT_SLOT).getCount() + recipe.get().getOutput(null).getCount()));

        world.playSound(null, getPos(), SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    private boolean hasRecipe() {
        Optional<NetheriteSmithingRecipe> recipe = getCurrentRecipe();
        return recipe.isPresent()
                && canInsertAmountIntoOutputSlot(recipe.get().getOutput(null))
                && canInsertItemIntoOutputSlot(recipe.get().getOutput(null).getItem())
                && hasRequiredHeat(recipe.get());
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

    private Optional<NetheriteSmithingRecipe> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for(int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }
        return getWorld().getRecipeManager().getFirstMatch(NetheriteSmithingRecipe.Type.INSTANCE, inv, getWorld());
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
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}