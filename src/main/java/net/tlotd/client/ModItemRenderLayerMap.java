package net.tlotd.client;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.item.ModItems;

import java.util.Comparator;

public class ModItemRenderLayerMap {
    public static void registerItemRenderLayerMaps() {
        ModelPredicateProviderRegistry.register(
                ModItems.EMF_READER,
                new Identifier(TLOTD.MOD_ID, "proximity"),
                (stack, world, entity, seed) -> {
                    if (entity == null || world == null) return 0.0f;
                    if (stack.isEmpty() || stack.getItem() != ModItems.EMF_READER) return 0.0f;
                    Entity nearest = world.getOtherEntities(entity, entity.getBoundingBox().expand(11.0), e -> e != entity && e instanceof LivingEntity)
                            .stream()
                            .min(Comparator.comparingDouble(entity::squaredDistanceTo))
                            .orElse(null);
                    if (nearest == null) return 0.0f;
                    double dist = Math.sqrt(entity.squaredDistanceTo(nearest));
                    System.out.println("Proximity value: " + dist);
                    if (dist <= 3.0) return 1.0f;
                    else if (dist <= 6.0) return 0.8f;
                    else if (dist <= 9.0) return 0.6f;
                    else if (dist <= 12.0) return 0.4f;
                    else if (dist <= 15.0) return 0.2f;
                    else return 0.0f;
                }
        );
    }
}
