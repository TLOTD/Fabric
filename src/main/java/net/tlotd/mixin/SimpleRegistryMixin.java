package net.tlotd.mixin;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.registry.SimpleRegistry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.TagKey;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SimpleRegistry.class)
public abstract class SimpleRegistryMixin<T> {

    @Shadow
    public abstract RegistryKey<?> getKey();

    @Shadow
    private Map<TagKey<T>, RegistryEntryList.Named<T>> tagToEntryList;

    @Inject(method = "populateTags", at = @At("HEAD"))
    private void diagnoseNullTagEntries(Map<TagKey<T>, List<RegistryEntry<T>>> tagEntries, CallbackInfo ci) {
        System.err.println("[TagDebug] Existing tag map: " + tagToEntryList);
        for (TagKey<T> tag : tagToEntryList.keySet()) {
            if (tag == null) {
                System.err.println("[TagDebug] NULL key in existing tag map!");
            }
        }
        System.err.println("[TagDebug] Checking difference for registry: " + getKey());
        Set<TagKey<T>> existingKeys = tagToEntryList.keySet();
        Set<TagKey<T>> incomingKeys = tagEntries.keySet();
        System.err.println("[TagDebug] Existing map class: " + tagToEntryList.getClass().getName());
        System.err.println("[TagDebug] Incoming map class: " + tagEntries.getClass().getName());
        System.err.println("[TagDebug] Existing tag count: " + existingKeys.size());
        System.err.println("[TagDebug] Incoming tag count: " + incomingKeys.size());
        for (TagKey<T> tag : existingKeys) {
            if (tag == null) {
                System.err.println("[TagDebug] NULL in existing tag keys!");
            }
        }
        for (TagKey<T> tag : incomingKeys) {
            if (tag == null) {
                System.err.println("[TagDebug] NULL in incoming tag keys!");
            }
        }
        System.err.println("[TagDebug] Finished key inspection.");
    }
}