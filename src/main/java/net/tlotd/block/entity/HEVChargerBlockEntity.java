package net.tlotd.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class HEVChargerBlockEntity extends BlockEntity {

    public final SimpleEnergyStorage energy = new SimpleEnergyStorage(1000000L, 1024L, 0L) {
        @Override
        protected void onFinalCommit() {
            markDirty();
        }
    };

    public HEVChargerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.HEV_CHARGER_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putLong("energy", energy.amount);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        energy.amount = nbt.getLong("energy");
    }
}