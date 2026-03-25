package net.tlotd.util.triggers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class RadioTrigger extends AbstractCriterion<RadioTrigger.Conditions> {

    public static final Identifier ID = new Identifier(TLOTD.MOD_ID, "radio_play");

    @Override
    public Identifier getId() {
        return ID;
    }

    @Override
    protected Conditions conditionsFromJson(JsonObject json, LootContextPredicate playerPredicate, AdvancementEntityPredicateDeserializer deserializer) {
        Identifier signal = null;
        Identifier dimension = null;
        Set<UUID> uuids = new HashSet<>();
        if (json.has("signal")) {
            signal = new Identifier(json.get("signal").getAsString());
        }
        if (json.has("dimension")) {
            dimension = new Identifier(json.get("dimension").getAsString());
        }
        if (json.has("uuids")) {
            JsonArray array = json.getAsJsonArray("uuids");
            for (JsonElement element : array) {
                uuids.add(UUID.fromString(element.getAsString()));
            }
        }
        return new Conditions(playerPredicate, signal, dimension, uuids);
    }

    public void trigger(ServerPlayerEntity player, ServerWorld world, Identifier playedSignal) {
        this.trigger(player, conditions -> {
            if (conditions.signal != null &&
                    !conditions.signal.equals(playedSignal)) {
                return false;
            }
            if (conditions.dimension != null &&
                    !world.getRegistryKey().getValue().equals(conditions.dimension)) {
                return false;
            }
            if (!conditions.allowedUuids.isEmpty() &&
                    !conditions.allowedUuids.contains(player.getUuid())) {
                return false;
            }
            return true;
        });
    }

    public static class Conditions extends AbstractCriterionConditions {
        private final Identifier signal;
        private final Identifier dimension;
        private final Set<UUID> allowedUuids;
        public Conditions(LootContextPredicate predicate, Identifier signal, Identifier dimension, Set<UUID> uuids) {
            super(ID, predicate);
            this.signal = signal;
            this.dimension = dimension;
            this.allowedUuids = uuids;
        }
    }
}