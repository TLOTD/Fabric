package net.tlotd.util.triggers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.AdvancementEntityPredicateSerializer;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.*;

import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;

public class HoldItemPlayerTrigger extends AbstractCriterion<HoldItemPlayerTrigger.Conditions> {

    public static final Identifier ID = new Identifier("tlotd", "player_holds_item");

    @Override
    public Identifier getId() {
        return ID;
    }

    @Override
    protected Conditions conditionsFromJson(JsonObject json, LootContextPredicate playerPredicate, AdvancementEntityPredicateDeserializer deserializer) {
        Identifier itemId = new Identifier(json.get("item").getAsString());
        Item item = Registries.ITEM.get(itemId);
        Set<UUID> uuidSet = Collections.emptySet();
        Map<String, Integer> augmentMap = new HashMap<>();
        if (json.has("uuids")) {
            JsonArray array = json.getAsJsonArray("uuids");
            Set<UUID> parsed = new HashSet<>();
            for (JsonElement element : array) {
                parsed.add(UUID.fromString(element.getAsString()));
            }
            uuidSet = parsed;
        }
        if (json.has("augments")) {
            JsonObject augmentsJson = json.getAsJsonObject("augments");

            for (String key : augmentsJson.keySet()) {
                int level = augmentsJson.get(key).getAsInt();
                augmentMap.put(key, level);
            }
        }
        return new Conditions(playerPredicate, item, uuidSet, augmentMap);
    }

    public void trigger(ServerPlayerEntity player) {
        ItemStack stack = player.getMainHandStack();
        this.trigger(player, conditions -> {
            if (!stack.isOf(conditions.item)) {
                return false;
            }
            if (!conditions.allowedUuids.isEmpty() && !conditions.allowedUuids.contains(player.getUuid())) {
                return false;
            }
            for (Map.Entry<String, Integer> entry : conditions.requiredAugments.entrySet()) {
                int level = getAugmentLevel(stack, entry.getKey());
                if (level < entry.getValue()) {
                    return false;
                }
            }
            return true;
        });
    }

    public static class Conditions extends AbstractCriterionConditions {
        private final Item item;
        private final Set<UUID> allowedUuids;
        private final Map<String, Integer> requiredAugments;

        public Conditions(LootContextPredicate playerPredicate, Item item, Set<UUID> allowedUuids, Map<String, Integer> requiredAugments) {
            super(ID, playerPredicate);
            this.item = item;
            this.allowedUuids = allowedUuids;
            this.requiredAugments = requiredAugments;
        }

        @Override
        public JsonObject toJson(AdvancementEntityPredicateSerializer serializer) {
            JsonObject json = super.toJson(serializer);
            json.addProperty("item", Registries.ITEM.getId(item).toString());
            if (!allowedUuids.isEmpty()) {
                JsonArray array = new JsonArray();
                for (UUID uuid : allowedUuids) {
                    array.add(uuid.toString());
                }
                json.add("uuids", array);
            }
            return json;
        }
    }
}