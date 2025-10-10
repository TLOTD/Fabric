package net.tlotd.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CustomTextureManager extends PersistentState {
    private final Map<UUID, Integer> textureMap = new HashMap<>();

    public static CustomTextureManager get(MinecraftServer server) {
        ServerWorld overworld = server.getOverworld();
        return overworld.getPersistentStateManager().getOrCreate(CustomTextureManager::fromNbt, CustomTextureManager::new, "TLOTD_Textures");
    }

    public static CustomTextureManager fromNbt(NbtCompound tag) {
        CustomTextureManager manager = new CustomTextureManager();
        NbtList list = tag.getList("Textures", NbtElement.COMPOUND_TYPE);
        for (NbtElement e : list) {
            NbtCompound nbt = (NbtCompound) e;
            manager.textureMap.put(nbt.getUuid("Player"), nbt.getInt("Texture"));
        }
        return manager;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtList list = new NbtList();
        for (Map.Entry<UUID, Integer> entry : textureMap.entrySet()) {
            NbtCompound textureData = new NbtCompound();
            textureData.putUuid("Player", entry.getKey());
            textureData.putInt("Texture", entry.getValue());
            list.add(textureData);
        }
        nbt.put("Textures", list);
        return nbt;
    }

    public void setTexture(UUID player, int textureId) {
        textureMap.put(player, textureId);
        markDirty();
    }

    public int getTexture(UUID player) {
        return textureMap.getOrDefault(player, -1);
    }

    public Map<UUID, Integer> getAll() {
        return Collections.unmodifiableMap(textureMap);
    }

    public boolean removeTexture(UUID player) {
        if (textureMap.remove(player) != null) {
            markDirty();
            return true;
        }
        return false;
    }

    public boolean hasTexture(UUID player) {
        return textureMap.containsKey(player);
    }
}