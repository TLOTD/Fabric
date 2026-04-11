package net.tlotd.client;

import net.minecraft.block.BlockState;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.tlotd.TLOTD;
import net.tlotd.item.ModItems;
import net.tlotd.util.AugmentNbtHelper;
import net.tlotd.util.ModTags;

import java.util.Comparator;

public class ModItemRenderLayerMap {
    public static void registerItemRenderLayerMaps() {
        ModelPredicateProviderRegistry.register(
                ModItems.EMF_READER, new Identifier(TLOTD.MOD_ID, "proximity"), (stack, world, entity, seed) -> {
                    if (entity == null || world == null) return 0.0f;
                    if (stack.isEmpty() || stack.getItem() != ModItems.EMF_READER) return 0.0f;
                    Entity nearest = world.getOtherEntities(entity, entity.getBoundingBox().expand(15.0), e -> e != entity && e instanceof LivingEntity)
                            .stream()
                            .min(Comparator.comparingDouble(entity::squaredDistanceTo))
                            .orElse(null);
                    if (nearest == null) return 0.0f;
                    double dist = Math.sqrt(entity.squaredDistanceTo(nearest));
                    //System.out.println("Proximity value: " + dist);
                    if (dist <= 3.0) return 1.0f;
                    else if (dist <= 6.0) return 0.8f;
                    else if (dist <= 9.0) return 0.6f;
                    else if (dist <= 12.0) return 0.4f;
                    else if (dist <= 16.0) return 0.2f;
                    else return 0.0f;
                }
        );
        ModelPredicateProviderRegistry.register(
                ModItems.MITHRIL_SWORD, new Identifier(TLOTD.MOD_ID, "proximity"), (stack, world, entity, seed) -> {
                    if (entity == null || world == null) return 0.0f;
                    if (stack.isEmpty() || !(stack.getItem() == ModItems.MITHRIL_SWORD && (AugmentNbtHelper.getAugmentLevel(stack, "tlotd:elder_days_elven_forged") > 0))) return 0.0f;
                    Entity nearest = world.getOtherEntities(entity, entity.getBoundingBox().expand(31.0), e -> e != entity && e instanceof HostileEntity)
                            .stream()
                            .min(Comparator.comparingDouble(entity::squaredDistanceTo))
                            .orElse(null);
                    if (nearest == null) return 0.0f;
                    double dist = Math.sqrt(entity.squaredDistanceTo(nearest));
                    //System.out.println("Proximity value: " + dist);
                    if (dist <= 4.0) return 1.0f;
                    else if (dist <= 8.0) return 0.8f;
                    else if (dist <= 16) return 0.6f;
                    else if (dist <= 24) return 0.4f;
                    else if (dist <= 32) return 0.2f;
                    else return 0.0f;
                }
        );
        ModelPredicateProviderRegistry.register(
                ModItems.MITHRIL_PICKAXE,
                new Identifier(TLOTD.MOD_ID, "proximity"),
                (stack, world, entity, seed) -> {
                    if (entity == null || world == null) return 0.0f;
                    if (stack.isEmpty() || !(stack.getItem() == ModItems.MITHRIL_PICKAXE
                            && AugmentNbtHelper.getAugmentLevel(stack, "tlotd:elder_days_elven_forged") > 0)) {
                        return 0.0f;
                    }
                    BlockPos origin = entity.getBlockPos();
                    int maxRadius = 8;
                    for (int r = 0; r <= maxRadius; r++) {
                        for (int x = -r; x <= r; x++) {
                            for (int y = -r; y <= r; y++) {
                                for (int z = -r; z <= r; z++) {
                                    if (Math.abs(x) != r && Math.abs(y) != r && Math.abs(z) != r) continue;
                                    BlockPos pos = origin.add(x, y, z);
                                    BlockState state = world.getBlockState(pos);
                                    if (state.isIn(ModTags.Blocks.MITHRIL_REACTING)) {
                                        double dist = Math.sqrt(entity.squaredDistanceTo(
                                                pos.getX() + 0.5,
                                                pos.getY() + 0.5,
                                                pos.getZ() + 0.5
                                        ));
                                        System.out.println("Proximity value: " + dist);
                                        if (dist <= 2.0) return 1.0f;
                                        else if (dist <= 3.5) return 0.8f;
                                        else if (dist <= 4.5) return 0.6f;
                                        else if (dist <= 6.5) return 0.4f;
                                        else if (dist <= 9.0) return 0.2f;
                                        else return 0.0f;
                                    }
                                }
                            }
                        }
                    }
                    return 0.0f;
                }
        );
    }
}
