package net.tlotd.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class KeycardReaderBlockEntity extends BlockEntity {

    public KeycardReaderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.KEYCARD_READER_BLOCK_ENTITY, pos, state);
    }

    private String password = "";

    public boolean hasPassword() {
        return password.isEmpty();
    }

    public @Nullable String getPassword() {
        return password;
    }

    public void setPassword(@Nullable String pw) {
        this.password = pw;
        markDirty();
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
