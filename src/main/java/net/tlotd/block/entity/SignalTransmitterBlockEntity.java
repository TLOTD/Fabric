package net.tlotd.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class SignalTransmitterBlockEntity extends BlockEntity {
    public SignalTransmitterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SIGNAL_TRANSMITTER_BLOCK_ENTITY, pos, state);
    }
}