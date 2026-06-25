package net.tlotd.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.advancement.criterion.*;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.AdvancementEntityPredicateSerializer;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

import java.util.*;

import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;

public class HoldItemPlayerTrigger extends AbstractCriterion<HoldItemPlayerTrigger.Conditions> {

    public static final Identifier ID = new Identifier(TLOTD.MOD_ID, "player_holds_item");

    @Override
    public Identifier getId() {
        return ID;
    }

    @Override
    protected Conditions conditionsFromJson(JsonObject json, LootContextPredicate playerPredicate, AdvancementEntityPredicateDeserializer deserializer) {
        Ingredient ingredient;
        String itemId = null;
        String tagId = null;
        if (json.has("item")) {
            itemId = json.get("item").getAsString();
            ingredient = Ingredient.ofItems(
                    Registries.ITEM.get(new Identifier(itemId))
            );
        } else if (json.has("tag")) {
            tagId = json.get("tag").getAsString();
            ingredient = Ingredient.fromTag(
                    TagKey.of(
                            RegistryKeys.ITEM,
                            new Identifier(tagId)
                    )
            );
        } else {
            throw new JsonParseException("Missing item or tag");
        }
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
        return new Conditions(playerPredicate, ingredient, itemId, tagId, uuidSet, augmentMap);
    }

    public void trigger(ServerPlayerEntity player) {
        ItemStack stack = player.getMainHandStack();
        this.trigger(player, conditions -> {
            if (!conditions.itemPredicate.test(stack)) {
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
        private final Ingredient itemPredicate;
        private final String itemId;
        private final String tagId;
        private final Set<UUID> allowedUuids;
        private final Map<String, Integer> requiredAugments;
        public Conditions(LootContextPredicate playerPredicate, Ingredient itemPredicate, String itemId, String tagId, Set<UUID> allowedUuids, Map<String, Integer> requiredAugments) {
            super(ID, playerPredicate);
            this.itemPredicate = itemPredicate;
            this.itemId = itemId;
            this.tagId = tagId;
            this.allowedUuids = allowedUuids;
            this.requiredAugments = requiredAugments;
        }
        @Override
        public JsonObject toJson(AdvancementEntityPredicateSerializer serializer) {
            JsonObject json = super.toJson(serializer);
            if (itemId != null) {
                json.addProperty("item", itemId);
            }
            if (tagId != null) {
                json.addProperty("tag", tagId);
            }
            if (!allowedUuids.isEmpty()) {
                JsonArray array = new JsonArray();
                for (UUID uuid : allowedUuids) {
                    array.add(uuid.toString());
                }
                json.add("uuids", array);
            }
            if (!requiredAugments.isEmpty()) {
                JsonObject augments = new JsonObject();
                for (Map.Entry<String, Integer> entry : requiredAugments.entrySet()) {
                    augments.addProperty(entry.getKey(), entry.getValue());
                }
                json.add("augments", augments);
            }
            return json;
        }
    }
}