package net.tlotd.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
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
import net.tlotd.gui.OxygenCollectorGUIHandler;
import net.tlotd.item.custom.SpaceSuitArmorItem;
import net.tlotd.util.AdAstraOxygenNbtHelper;
import net.tlotd.util.ModTags;
import org.jetbrains.annotations.Nullable;

import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;

public class OxygenCollectorBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int quality = 1 ;

    public OxygenCollectorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.OXYGEN_COLLECTOR_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return OxygenCollectorBlockEntity.this.quality;
            }

            @Override
            public void set(int index, int value) {
                OxygenCollectorBlockEntity.this.quality = value;
            }

            @Override
            public int size() {
                return 1;
            }
        };
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.tlotd.oxygen_collector");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("quality", quality);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        quality = nbt.getInt("quality");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new OxygenCollectorGUIHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }
        if(oxygenChargable()) {
            if(canBeFilled()) {
                this.fillOxygen();
            }
        }
    }

    private boolean oxygenChargable() {
        return this.getStack(0).isIn(ModTags.Items.OXYGEN_STORING) || (getAugmentLevel(this.getStack(0), "tlotd:oxygen_tank") > 0);
    }

    private boolean canBeFilled() {
        return (this.getStack(0).getItem() instanceof SpaceSuitArmorItem || (this.getStack(0).hasNbt() && this.getStack(0).getNbt().getInt("tlotd:oxygen") < AdAstraOxygenNbtHelper.getMaxOxygenItem(this.getStack(0)) || !this.getStack(0).hasNbt()));
    }

    private void fillOxygen() {
        ItemStack stack = this.getStack(0);
        if (stack.isEmpty()) return;
        if (stack.getItem() instanceof SpaceSuitArmorItem) {
            AdAstraOxygenNbtHelper.modifyOxygenInSuit(stack, quality * 81L);
        } else {
            long current = AdAstraOxygenNbtHelper.getOxygen(stack);
            long next = Math.min(current + (quality * 81L), AdAstraOxygenNbtHelper.getMaxOxygenItem(stack));
            AdAstraOxygenNbtHelper.setOxygen(stack, next);
        }
    }
}
