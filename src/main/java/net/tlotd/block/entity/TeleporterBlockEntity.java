package net.tlotd.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class TeleporterBlockEntity extends BlockEntity {

    public int destination_x = 0;
    public int destination_y = 2147483647;
    public int destination_z = 0;
    public boolean one_way = false;
    public boolean relative = false;

    public TeleporterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TELEPORTER_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("destination_x", destination_x);
        nbt.putInt("destination_y", destination_y);
        nbt.putInt("destination_z", destination_z);
        nbt.putBoolean("one_way", one_way);
        nbt.putBoolean("relative", relative);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        destination_x = nbt.getInt("destination_x");
        destination_y = nbt.getInt("destination_y");
        destination_z = nbt.getInt("destination_z");
        one_way = nbt.getBoolean("one_way");
        relative = nbt.getBoolean("relative");
    }
}
