package net.tlotd.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class RadioBlockEntity extends BlockEntity {

    private Identifier currentTrack;

    public RadioBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RADIO_BLOCK_ENTITY, pos, state);
    }

    public @Nullable Identifier getCurrentTrack() {
        return currentTrack;
    }

    public void setCurrentTrack(@Nullable Identifier id) {
        this.currentTrack = id;
        markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        if (currentTrack != null) {
            nbt.putString("current_track", currentTrack.toString());
        }
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        currentTrack = nbt.contains("current_track") ? Identifier.tryParse(nbt.getString("current_track")) : null;
    }
}