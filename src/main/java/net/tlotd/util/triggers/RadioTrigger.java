package net.tlotd.util.triggers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

import java.util.*;

public class RadioTrigger extends AbstractCriterion<RadioTrigger.Conditions> {

    public static final Identifier ID = Identifier.of(TLOTD.MOD_ID, "radio_play");

    private static final Codec<UUID> UUID_CODEC = Codec.STRING.xmap(UUID::fromString, UUID::toString);

    @Override
    public Codec<Conditions> getConditionsCodec() {
        return Conditions.CODEC;
    }

    public void trigger(ServerPlayerEntity player, ServerWorld world, Identifier playedSignal) {
        Identifier currentDimension = world.getRegistryKey().getValue();

        this.trigger(player, conditions -> {
            if (conditions.signal().isPresent() && !conditions.signal().get().equals(playedSignal)) {
                return false;
            }

            if (conditions.dimension().isPresent() && !conditions.dimension().get().equals(currentDimension)) {
                return false;
            }

            return conditions.allowedUuids().isEmpty() || conditions.allowedUuids().contains(player.getUuid());
        });
    }

    public record Conditions(Optional<LootContextPredicate> player, Optional<Identifier> signal,
                             Optional<Identifier> dimension,
                             Set<UUID> allowedUuids) implements AbstractCriterion.Conditions {

        public static final Codec<Conditions> CODEC = RecordCodecBuilder.create(instance -> instance.group(LootContextPredicate.CODEC.optionalFieldOf("player").forGetter(Conditions::player),

                Identifier.CODEC.optionalFieldOf("signal").forGetter(Conditions::signal),

                Identifier.CODEC.optionalFieldOf("dimension").forGetter(Conditions::dimension),

                UUID_CODEC.listOf().optionalFieldOf("uuids", List.of()).xmap(Set::copyOf, List::copyOf).forGetter(Conditions::allowedUuids)).apply(instance, Conditions::new));
    }
}