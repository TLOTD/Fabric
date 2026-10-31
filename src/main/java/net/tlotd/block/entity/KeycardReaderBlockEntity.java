package net.tlotd.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;

public class KeycardReaderBlockEntity extends BlockEntity {

    private String password = "";

    public KeycardReaderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.KEYCARD_READER_BLOCK_ENTITY, pos, state);
    }

    public boolean hasNoPassword() {
        return password.isEmpty();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putString("password", password);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        password = nbt.getString("password");
    }
}