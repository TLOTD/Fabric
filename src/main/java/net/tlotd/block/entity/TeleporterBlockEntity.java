package net.tlotd.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class TeleporterBlockEntity extends BlockEntity {

    public int pos_x = 0;
    public int pos_y = 2147483647;
    public int pos_z = 0;
    public boolean one_way = false;
    public boolean relative = false;

    public TeleporterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TELEPORTER_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("pos_x", pos_x);
        nbt.putInt("pos_y", pos_y);
        nbt.putInt("pos_z", pos_z);
        nbt.putBoolean("one_way", one_way);
        nbt.putBoolean("relative", relative);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        pos_x = nbt.getInt("pos_x");
        pos_y = nbt.getInt("pos_y");
        pos_z = nbt.getInt("pos_z");
        one_way = nbt.getBoolean("one_way");
        relative = nbt.getBoolean("relative");
    }
}
