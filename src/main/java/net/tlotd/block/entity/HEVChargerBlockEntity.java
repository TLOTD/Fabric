package net.tlotd.block.entity;

public class HEVChargerBlockEntity /*extends BlockEntity*/ {

    /*public ItemStack item = ItemStack.EMPTY;

    public final SimpleEnergyStorage energy = new SimpleEnergyStorage(1_000_000L, 1024L, 1024L) {
        @Override
        protected void onFinalCommit() {
            markDirty();
        }
    };

    private final SingleStackStorage itemStorage = new SingleStackStorage() {
        @Override
        protected ItemStack getStack() {
            return item;
        }

        @Override
        protected void setStack(ItemStack stack) {
            item = stack;
        }

        @Override
        protected void onFinalCommit() {
            markDirty();
        }
    };

    public HEVChargerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.HEV_CHARGER_BLOCK_ENTITY, pos, state);
    }

    public Direction facing() {
        return getCachedState().get(HEVChargerBlock.FACING);
    }

    public WallMountLocation face() {
        return getCachedState().get(HEVChargerBlock.FACE);
    }

    public void chargeItem() {
        if (item.isEmpty()) {
            return;
        }
        ContainerItemContext context = ContainerItemContext.ofSingleSlot(itemStorage);
        EnergyStorage target = context.find(EnergyStorage.ITEM);
        if (target == null) {
            return;
        }
        EnergyStorageUtil.move(energy, target, 1024L, null);
    }

    public void tick(World world) {
        if (world.isClient) {
            return;
        }
        this.chargeItem();
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack stack) {
        item = stack;
        markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putLong("energy", energy.amount);
        if (!item.isEmpty()) {
            nbt.put("item", item.writeNbt(new NbtCompound()));
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        energy.amount = nbt.getLong("energy");
        if (nbt.contains("item", NbtElement.COMPOUND_TYPE)) {
            item = ItemStack.fromNbt(nbt.getCompound("item"));
        } else {
            item = ItemStack.EMPTY;
        }
    }

    public final EnergyStorage inputOnlyEnergy = new EnergyStorage() {
        @Override
        public boolean supportsInsertion() {
            return true;
        }
        @Override
        public long insert(long maxAmount, TransactionContext transaction) {
            return energy.insert(maxAmount, transaction);
        }
        @Override
        public boolean supportsExtraction() {
            return false;
        }
        @Override
        public long extract(long maxAmount, TransactionContext transaction) {
            return 0;
        }
        @Override
        public long getAmount() {
            return energy.getAmount();
        }
        @Override
        public long getCapacity() {
            return energy.getCapacity();
        }
    };

    @Override
    public void markDirty() {
        world.updateListeners(pos,getCachedState(),getCachedState(),3);
        super.markDirty();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }*/
}