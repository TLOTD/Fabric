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
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.tlotd.block.custom.MithrilAnvilBlock;
import net.tlotd.gui.MithrilAnvilGUIHandler;
import net.tlotd.recipe.MithrilSmithingRecipe;
import net.tlotd.recipe.NetheriteSmithingRecipe;
import net.tlotd.util.ItemHeatHelper;
import net.tlotd.util.ModTags;
import net.tlotd.world.ModGlobalState;
import org.jetbrains.annotations.Nullable;


import java.util.Optional;

import static net.tlotd.world.dimension.ModDimensionsDataGenerator.LUNA_LEVEL_KEY;

public class MithrilAnvilBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);

    private static final int OUTPUT_SLOT = 6;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;
    private int starlight = 0;

    public MithrilAnvilBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MITHRIL_ANVIL_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> MithrilAnvilBlockEntity.this.progress;
                    case 1 -> MithrilAnvilBlockEntity.this.maxProgress;
                    case 2 -> MithrilAnvilBlockEntity.this.starlight;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> MithrilAnvilBlockEntity.this.progress = value;
                    case 1 -> MithrilAnvilBlockEntity.this.maxProgress = value;
                    case 2 -> MithrilAnvilBlockEntity.this.starlight = value;
                }
            }

            @Override
            public int size() {
                return 3;
            }
        };
    }

    public Direction facing() {
        return getCachedState().get(MithrilAnvilBlock.FACING);
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
        world.updateListeners(pos,getCachedState(),getCachedState(),3);
        super.markDirty();
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.tlotd.mithril_anvil");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("mithril_anvil.progress", progress);
        nbt.putInt("mithril_anvil.starlight", starlight);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("mithril_anvil.progress");
        starlight = nbt.getInt("mithril_anvil.starlight");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new MithrilAnvilGUIHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }
        boolean starlightAnvil = true;
        if (world.getServer() != null) {
            ModGlobalState globalState = ModGlobalState.get(world.getServer());
            starlightAnvil = globalState.starlightAnvil();
        }
        if (starlight(starlightAnvil)) {
            starlight = 1;
        } else {
            starlight = 0;
        }
        markDirty(world, pos, state);
        if (isOutputSlotEmptyOrReceivable()) {
            if (hasRecipe() && canCraft(starlightAnvil)) {
                progress++;
                markDirty(world, pos, state);
                if (progress >= maxProgress) {
                    craftItem();
                    resetProgress();
                }
            } else {
                resetProgress();
                tryCoolingItems();
            }
        } else {
            resetProgress();
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
        Optional<MithrilSmithingRecipe> mithrilRecipe = getCurrentMithrilRecipe();
        Optional<NetheriteSmithingRecipe> recipe = getCurrentRecipe();
        this.removeStack(1, 1);
        this.removeStack(2, 1);
        this.removeStack(3, 1);
        this.removeStack(4, 1);
        this.removeStack(5, 1);
        if (mithrilRecipe.isPresent()) {
            this.setStack(OUTPUT_SLOT, new ItemStack(mithrilRecipe.get().getOutput(null).getItem(), getStack(OUTPUT_SLOT).getCount() + mithrilRecipe.get().getOutput(null).getCount()));
        } else {
            this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().getOutput(null).getItem(), getStack(OUTPUT_SLOT).getCount() + recipe.get().getOutput(null).getCount()));
        }
        world.playSound(null, getPos(), SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    private boolean hasRecipe() {
        Optional<NetheriteSmithingRecipe> recipe = getCurrentRecipe();
        Optional<MithrilSmithingRecipe> mithrilRecipe = getCurrentMithrilRecipe();
        return (recipe.isPresent() && canInsertAmountIntoOutputSlot(recipe.get().getOutput(null)) && canInsertItemIntoOutputSlot(recipe.get().getOutput(null).getItem()) && hasRequiredHeat(recipe.get()))
                || (mithrilRecipe.isPresent() && canInsertAmountIntoOutputSlot(mithrilRecipe.get().getOutput(null)) && canInsertItemIntoOutputSlot(mithrilRecipe.get().getOutput(null).getItem()) && hasRequiredHeat(mithrilRecipe.get()));
    }

    private boolean starlight(boolean starlightAnvil) {
        return world.getRegistryKey().equals(LUNA_LEVEL_KEY) || !starlightAnvil || (world.isNight() && world.isSkyVisibleAllowingSea(pos));
    }

    private boolean canCraft(boolean starlightAnvil) {
        Optional<MithrilSmithingRecipe> mithrilRecipe = getCurrentMithrilRecipe();
        if (mithrilRecipe.isPresent()) {
            return starlight(starlightAnvil);
        } else return true;
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

    private boolean hasRequiredHeat(MithrilSmithingRecipe recipe) {
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

    private Optional<MithrilSmithingRecipe> getCurrentMithrilRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for(int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }
        return getWorld().getRecipeManager().getFirstMatch(MithrilSmithingRecipe.Type.INSTANCE, inv, getWorld());
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