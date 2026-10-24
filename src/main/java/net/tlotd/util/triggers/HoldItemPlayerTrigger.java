package net.tlotd.util.triggers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

import java.util.*;

import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;

public class HoldItemPlayerTrigger extends AbstractCriterion<HoldItemPlayerTrigger.Conditions> {

    public static final Identifier ID = Identifier.of(TLOTD.MOD_ID, "player_holds_item");

    @Override
    public Codec<Conditions> getConditionsCodec() {
        return Conditions.CODEC;
    }

    public void trigger(ServerPlayerEntity player) {
        ItemStack stack = player.getMainHandStack();

        this.trigger(player, conditions -> {
            if (!conditions.allowedUuids().isEmpty() && !conditions.allowedUuids().contains(player.getUuid())) {
                return false;
            }

            for (Map.Entry<String, Integer> entry : conditions.requiredAugments().entrySet()) {
                if (getAugmentLevel(stack, entry.getKey()) < entry.getValue()) {
                    return false;
                }
            }

            return conditions.item().test(stack);
        });
    }

    public record Conditions(Optional<LootContextPredicate> player, ItemPredicate item, Set<UUID> allowedUuids, Map<String, Integer> requiredAugments) implements AbstractCriterion.Conditions {

        private static final Codec<UUID> UUID_CODEC = Codec.STRING.xmap(UUID::fromString, UUID::toString);

        public static final Codec<Conditions> CODEC = RecordCodecBuilder.create(instance -> instance.group(LootContextPredicate.CODEC.optionalFieldOf("player").forGetter(Conditions::player),
                ItemPredicate.CODEC.fieldOf("item").forGetter(Conditions::item),
                UUID_CODEC.listOf().optionalFieldOf("uuids", List.of()).xmap(Set::copyOf, List::copyOf).forGetter(Conditions::allowedUuids),
                Codec.unboundedMap(Codec.STRING, Codec.INT).optionalFieldOf("augments", Map.of()).forGetter(Conditions::requiredAugments)).apply(instance, Conditions::new));
    }
}