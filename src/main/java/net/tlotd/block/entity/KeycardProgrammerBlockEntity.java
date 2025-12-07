package net.tlotd.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
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
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
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
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("progress");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new KeycardProgrammerGUIHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }
        if(KeycardIsPresent()) {
            progress++;
            markDirty(world, pos, state);
            if(progress >= maxProgress) {
                NbtCompound nbt = new NbtCompound();
                if (this.getStack(0).getNbt() != null) {
                    nbt = this.getStack(0).getNbt();
                }
                nbt.putString("password", this.getStack(1).toString());
                this.setStack(2, this.getStack(0));
                this.setStack(0, ItemStack.EMPTY);
                this.getStack(2).setNbt(nbt);
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }


    private boolean KeycardIsPresent() {
        return this.getStack(0).isIn(ModTags.Items.KEYCARDS) && !this.getStack(1).isEmpty() && this.getStack(2).isEmpty();
    }

    private void resetProgress() {
        this.progress = 0;
    }
}
