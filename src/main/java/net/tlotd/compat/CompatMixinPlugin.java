package net.tlotd.compat;

import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.objectweb.asm.tree.ClassNode;

import java.util.List;
import java.util.Set;

public class CompatMixinPlugin implements IMixinConfigPlugin {

    private static final String CONNECTOR_CLASS = "org.sinytra.connector.ConnectorEarlyLoader";

    private boolean connectorPresent;

    @Override
    public void onLoad(String mixinPackage) {
        try {
            Class.forName(CONNECTOR_CLASS, false, CompatMixinPlugin.class.getClassLoader());
            connectorPresent = true;
        } catch (ClassNotFoundException | LinkageError e) {
            connectorPresent = false;
        }
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.endsWith("CustomCreativeTabMixin") || mixinClassName.endsWith("CreativeInventoryScreenAccessor")) {
            return !connectorPresent;
        }
        return true;
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}