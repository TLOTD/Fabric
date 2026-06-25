package net.tlotd.world.tree;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.tlotd.TLOTD;

public class ModTreeDecoratorTypes {

    public static final TreeDecoratorType<ShelfMushroomTreeDecorator>
            SHELF_MUSHROOM = Registry.register(
            Registries.TREE_DECORATOR_TYPE,
            new Identifier(TLOTD.MOD_ID, "shelf_mushroom"),
            new TreeDecoratorType<>(ShelfMushroomTreeDecorator.CODEC)
    );

    public static void registerDecorators() {
        TLOTD.LOGGER.info("Registering Tree Decorators for " + TLOTD.MOD_ID);
    }
}