package net.tlotd.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class RadioBlockEntity extends BlockEntity {
    public RadioBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RADIO_BLOCK_ENTITY, pos, state);
    }

    private Identifier currentTrack;

    public @Nullable Identifier getCurrentTrack() {
        return currentTrack;
    }

    public void setCurrentTrack(@Nullable Identifier id) {
        this.currentTrack = id;
        markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (currentTrack != null) nbt.putString("current_track", currentTrack.toString());
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("current_track")) currentTrack = Identifier.tryParse(nbt.getString("current_track"));
    }
}