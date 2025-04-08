package net.tlotd.block.entity;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.config.ModConfigs;
import net.tlotd.fluid.ModFluids;
import net.tlotd.gui.WitchingTableGUIHandler;
import net.tlotd.item.ModItems;
import net.tlotd.networking.ModMessages;
import net.tlotd.recipe.WitchingRecipe;
import net.tlotd.util.FluidStack;
import net.tlotd.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static net.minecraft.block.CandleBlock.LIT;
import static net.tlotd.block.custom.RitualisticCircleBlock.STATE;
import static net.tlotd.block.custom.WitchingTableBlock.CHARGES;

public class WitchingTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(12, ItemStack.EMPTY);

    private static final int OUTPUT_SLOT = 11;

    public final SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<FluidVariant>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        public long getCapacity(FluidVariant fluidVariant) {
            return FluidConstants.BUCKET * 8;
        }

        @Override
        protected void onFinalCommit() {
            markDirty();
            if(!world.isClient()) {
                sendFluidPacket();
            }
        }
    };

    private void sendFluidPacket() {
        PacketByteBuf data = PacketByteBufs.create();
        fluidStorage.variant.toPacket(data);
        data.writeLong(fluidStorage.amount);
        data.writeBlockPos(getPos());

        for (ServerPlayerEntity player : PlayerLookup.tracking((ServerWorld) world, getPos())) {
            ServerPlayNetworking.send(player, ModMessages.FLUID_SYNC, data);
        }
    }

    public void setFluidLevel(FluidVariant fluidVariant, long fluidLevel) {
        this.fluidStorage.variant = fluidVariant;
        this.fluidStorage.amount = fluidLevel;
    }

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 126;

    public WitchingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WITCHING_TABLE_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> WitchingTableBlockEntity.this.progress;
                    case 1 -> WitchingTableBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> WitchingTableBlockEntity.this.progress = value;
                    case 1 -> WitchingTableBlockEntity.this.maxProgress = value;
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
        } else if (!this.getStack(8).isEmpty()) {
            return this.getStack(8);
        } else if (!this.getStack(7).isEmpty()) {
            return this.getStack(7);
        } else if (!this.getStack(6).isEmpty()) {
            return this.getStack(6);
        } else if (!this.getStack(5).isEmpty()) {
            return this.getStack(5);
        } else if (!this.getStack(4).isEmpty()) {
            return this.getStack(4);
        } else if (!this.getStack(3).isEmpty()) {
            return this.getStack(3);
        } else if (!this.getStack(2).isEmpty()) {
            return this.getStack(2);
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
        return Text.translatable("block.tlotd.witching_table");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("witching_table.progress", progress);
        nbt.put("witching_table.fluid", fluidStorage.variant.toNbt());
        nbt.putLong("witching_table.fluid_amount", fluidStorage.amount);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("witching_table.progress");
        fluidStorage.variant = FluidVariant.fromNbt((NbtCompound) nbt.get("witching_table.fluid"));
        fluidStorage.amount = nbt.getLong("witching_table.fluid_amount");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        sendFluidPacket();
        return new WitchingTableGUIHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }

        if (isOutputSlotEmptyOrReceivable()) {
            if (hasRecipe() && (!ModConfigs.WITCHING_TABLE_NEEDS_BLOOD || hasEnoughFluid()) && (!ModConfigs.WITCHING_TABLE_NEEDS_SOULS || state.get(CHARGES) > 0)) {
                increaseCraftProgress();
                markDirty(world, pos, state);
                if (hasCraftingFinished()) {
                    craftItem();
                    if (ModConfigs.WITCHING_TABLE_NEEDS_BLOOD) {
                        extractFluid();
                    }
                    if (ModConfigs.WITCHING_TABLE_NEEDS_SOULS) {
                        world.setBlockState(pos,state.with(CHARGES, state.get(CHARGES)-1));
                    }
                    if (WitchingTableBase()){
                        world.setBlockState(pos.add(-1,-1,-1), ModBlocks.RITUALISTIC_FANCY_CHARRED_PLANKS.getDefaultState().with(STATE,0));
                        world.setBlockState(pos.add(0,-1,-1), ModBlocks.RITUALISTIC_FANCY_CHARRED_PLANKS.getDefaultState().with(STATE,1));
                        world.setBlockState(pos.add(1,-1,-1), ModBlocks.RITUALISTIC_FANCY_CHARRED_PLANKS.getDefaultState().with(STATE,2));
                        world.setBlockState(pos.add(-1,-1,0), ModBlocks.RITUALISTIC_FANCY_CHARRED_PLANKS.getDefaultState().with(STATE,3));
                        world.setBlockState(pos.add(0,-1,0), ModBlocks.RITUALISTIC_FANCY_CHARRED_PLANKS.getDefaultState().with(STATE,4));
                        world.setBlockState(pos.add(1,-1,0), ModBlocks.RITUALISTIC_FANCY_CHARRED_PLANKS.getDefaultState().with(STATE,5));
                        world.setBlockState(pos.add(-1,-1,1), ModBlocks.RITUALISTIC_FANCY_CHARRED_PLANKS.getDefaultState().with(STATE,6));
                        world.setBlockState(pos.add(0,-1,1), ModBlocks.RITUALISTIC_FANCY_CHARRED_PLANKS.getDefaultState().with(STATE,7));
                        world.setBlockState(pos.add(1,-1,1), ModBlocks.RITUALISTIC_FANCY_CHARRED_PLANKS.getDefaultState().with(STATE,8));
                        if (Candles()) {
                            world.setBlockState(pos.add(-1,0,-1), world.getBlockState(pos.add(-1,0,-1)).with(LIT,false));
                            world.setBlockState(pos.add(1,0,-1), world.getBlockState(pos.add(1,0,-1)).with(LIT,false));
                            world.setBlockState(pos.add(-1,0,1), world.getBlockState(pos.add(-1,0,1)).with(LIT,false));
                            world.setBlockState(pos.add(1,0,1), world.getBlockState(pos.add(1,0,1)).with(LIT,false));
                        }
                    }
                    resetProgress();
                    sendFluidPacket();
                }
            } else {
                resetProgress();
            }
        } else {
            resetProgress();
            markDirty(world, pos, state);
        }

        if(SpaceForFluid()) {
            if(hasFluidBottleInSlot()) {
                transferFluidBottleToStorage();
                sendFluidPacket();
            } else if(hasFluidBucketInSlot()) {
                transferFluidBucketToStorage();
                sendFluidPacket();
            }
        }

        if(CanBottleOrBucketBeFilled()) {
            if(CanBucketBeFilled()) {
                fillBucket();
                sendFluidPacket();
            } else if (CanBottleBeFilled()) {
                fillBottle();
                sendFluidPacket();
            }
        }
    }

    private boolean WitchingTableBase() {
        return (world.getBlockState(pos.add(-1,-1,-1)).isIn(ModTags.Blocks.WITCHING_TABLE_BASE_BLOCKS) && world.getBlockState(pos.add(0,-1,-1)).isIn(ModTags.Blocks.WITCHING_TABLE_BASE_BLOCKS) && world.getBlockState(pos.add(1,-1,-1)).isIn(ModTags.Blocks.WITCHING_TABLE_BASE_BLOCKS)
        && world.getBlockState(pos.add(-1,-1,0)).isIn(ModTags.Blocks.WITCHING_TABLE_BASE_BLOCKS) && world.getBlockState(pos.add(0,-1,0)).isIn(ModTags.Blocks.WITCHING_TABLE_BASE_BLOCKS) && world.getBlockState(pos.add(1,-1,0)).isIn(ModTags.Blocks.WITCHING_TABLE_BASE_BLOCKS)
        && world.getBlockState(pos.add(-1,-1,1)).isIn(ModTags.Blocks.WITCHING_TABLE_BASE_BLOCKS) && world.getBlockState(pos.add(0,-1,1)).isIn(ModTags.Blocks.WITCHING_TABLE_BASE_BLOCKS) && world.getBlockState(pos.add(1,-1,1)).isIn(ModTags.Blocks.WITCHING_TABLE_BASE_BLOCKS));
    }

    private boolean Candles() {
        return (world.getBlockState(pos.add(-1,0,-1)).isIn(BlockTags.CANDLES) && world.getBlockState(pos.add(1,0,-1)).isIn(BlockTags.CANDLES) && world.getBlockState(pos.add(-1,0,1)).isIn(BlockTags.CANDLES) && world.getBlockState(pos.add(1,0,1)).isIn(BlockTags.CANDLES) &&
        world.getBlockState(pos.add(-1,0,-1)).get(LIT) && world.getBlockState(pos.add(1,0,-1)).get(LIT) && world.getBlockState(pos.add(-1,0,1)).get(LIT) && world.getBlockState(pos.add(1,0,1)).get(LIT));
    }

    private boolean SpaceForFluid() {
        return this.fluidStorage.amount <= FluidConstants.BUCKET * 7;
    }

    private boolean CanBottleOrBucketBeFilled() {
        return (this.fluidStorage.amount >= FluidConstants.BOTTLE && this.getStack(10).getCount() == 1 &&
                (this.getStack(10).getItem() == Items.GLASS_BOTTLE || this.getStack(10).getItem() == Items.BUCKET));
    }

    private boolean CanBucketBeFilled() {
        return (this.fluidStorage.amount >= FluidConstants.BUCKET &&
                this.getStack(10).getItem() == Items.BUCKET &&
                this.getStack(10).getCount() == 1);
    }

    private boolean CanBottleBeFilled() {
        return (this.fluidStorage.amount >= FluidConstants.BOTTLE &&
                this.getStack(10).getItem() == Items.GLASS_BOTTLE &&
                this.getStack(10).getCount() == 1);
    }

    private void fillBucket() {
        try(Transaction transaction = Transaction.openOuter()) {
            this.fluidStorage.extract(FluidVariant.of(ModFluids.STILL_BLOOD),
                    FluidConstants.BUCKET, transaction);
            transaction.commit();
            this.setStack(10, new ItemStack(ModFluids.BLOOD_BUCKET));
        }
    }

    private void fillBottle() {
        try(Transaction transaction = Transaction.openOuter()) {
            this.fluidStorage.extract(FluidVariant.of(ModFluids.STILL_BLOOD),
                    FluidConstants.BOTTLE, transaction);
            transaction.commit();
            this.setStack(10, new ItemStack(ModItems.BLOOD_BOTTLE));
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        Optional<WitchingRecipe> recipe = getCurrentRecipe();

        this.removeStack(0, 1);
        this.removeStack(1, 1);
        this.removeStack(2, 1);
        this.removeStack(3, 1);
        this.removeStack(4, 1);
        this.removeStack(5, 1);
        this.removeStack(6, 1);
        this.removeStack(7, 1);
        this.removeStack(8, 1);

        this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().getOutput(null).getItem(), getStack(OUTPUT_SLOT).getCount() + recipe.get().getOutput(null).getCount()));

        world.playSound(null, getPos(), SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    private void extractFluid() {
        try(Transaction transaction = Transaction.openOuter()) {
            this.fluidStorage.extract(FluidVariant.of(ModFluids.STILL_BLOOD),
                    FluidConstants.BOTTLE, transaction);
            transaction.commit();
        }
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hasFluidBottleInSlot() {
        return this.getStack(9).getItem() == ModItems.BLOOD_BOTTLE;
    }

    private boolean hasFluidBucketInSlot() {
        return this.getStack(9).getItem() == ModFluids.BLOOD_BUCKET;
    }

    private void transferFluidBottleToStorage() {
        try(Transaction transaction = Transaction.openOuter()) {
            this.fluidStorage.insert(FluidVariant.of(ModFluids.STILL_BLOOD),
                    FluidConstants.BOTTLE, transaction);
            transaction.commit();
            this.setStack(9, new ItemStack(Items.GLASS_BOTTLE));
        }
    }

    private void transferFluidBucketToStorage() {
        try(Transaction transaction = Transaction.openOuter()) {
            this.fluidStorage.insert(FluidVariant.of(ModFluids.STILL_BLOOD),
                    FluidConstants.BUCKET, transaction);
            transaction.commit();
            this.setStack(9, new ItemStack(Items.BUCKET));
        }
    }

    private boolean hasEnoughFluid() {
        return this.fluidStorage.amount >= FluidConstants.BOTTLE;
    };

    private boolean hasRecipe() {
        Optional<WitchingRecipe> recipe = getCurrentRecipe();

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(recipe.get().getOutput(null)) && canInsertItemIntoOutputSlot(recipe.get().getOutput(null).getItem());
    }

    private Optional<WitchingRecipe> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for(int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }

        return getWorld().getRecipeManager().getFirstMatch(WitchingRecipe.Type.INSTANCE, inv, getWorld());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
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