package net.tlotd.block.entity;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import team.reborn.energy.api.EnergyStorage;

public class OutletBlockEntity extends BlockEntity {

    public OutletBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.OUTLET_BLOCK_ENTITY, pos, state);
    }

    public void tick(World world, BlockPos pos) {
        if (world.isClient) {
            return;
        }
        for (Direction direction : Direction.Type.HORIZONTAL) {
            this.outputToSide(world, pos, direction);
        }
    }

    public void outputToSide(World world, BlockPos pos, Direction side) {
        EnergyStorage target = EnergyStorage.SIDED.find(world, pos.offset(side), side.getOpposite());
        if (target == null || !target.supportsInsertion()) {
            return;
        }
        try (Transaction transaction = Transaction.openOuter()) {
            target.insert(1024, transaction);
            transaction.commit();
        }
    }

    public final EnergyStorage energy = new EnergyStorage() {

        @Override
        public long insert(long maxAmount, TransactionContext transaction) {
            return 0;
        }

        @Override
        public long extract(long maxAmount, TransactionContext transaction) {
            return maxAmount;
        }

        @Override
        public long getAmount() {
            return Long.MAX_VALUE;
        }

        @Override
        public long getCapacity() {
            return Long.MAX_VALUE;
        }

        @Override
        public boolean supportsExtraction() {
            return true;
        }

        @Override
        public boolean supportsInsertion() {
            return false;
        }
    };
}