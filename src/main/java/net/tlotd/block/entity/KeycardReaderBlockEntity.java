package net.tlotd.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class KeycardReaderBlockEntity extends BlockEntity {

    public String password = "";

    public KeycardReaderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.KEYCARD_READER_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putString("password", password);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        password = nbt.getString("password");
    }
}
