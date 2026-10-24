package net.tlotd.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterial {

    public static final RegistryEntry<ArmorMaterial> HELIORITE = register("heliorite", new int[]{4, 9, 7, 4}, 18, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.5f, 0.15f, () -> Ingredient.ofItems(ModItems.HELIORITE_INGOT));
    public static final RegistryEntry<ArmorMaterial> ENDURIUM = register("endurium", new int[]{4, 9, 7, 4}, 18, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.5f, 0.15f, () -> Ingredient.ofItems(ModItems.ENDURIUM_INGOT));

    public static final RegistryEntry<ArmorMaterial> PALLADIUM = register("palladium", new int[]{5, 10, 8, 5}, 21, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4.0f, 0.2f, () -> Ingredient.ofItems(ModItems.PALLADIUM_INGOT));
    public static final RegistryEntry<ArmorMaterial> JURASSOLINE = register("jurassoline", new int[]{5, 10, 8, 5}, 21, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4.0f, 0.2f, () -> Ingredient.ofItems(ModItems.JURASSOLINE_INGOT));

    public static final RegistryEntry<ArmorMaterial> CINNABAR = register("cinnabar", new int[]{5, 10, 8, 5}, 21, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4.5f, 0.25f, () -> Ingredient.ofItems(ModItems.CINNABAR_INGOT));
    public static final RegistryEntry<ArmorMaterial> NEBULAR = register("nebular", new int[]{5, 10, 8, 5}, 21, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4.5f, 0.25f, () -> Ingredient.ofItems(ModItems.NEBULAR_INGOT));

    public static final RegistryEntry<ArmorMaterial> MITHRIL = register("mithril", new int[]{6, 12, 10, 6}, 25, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5.0f, 0.3f, () -> Ingredient.ofItems(ModItems.MITHRIL_INGOT));

    public static final RegistryEntry<ArmorMaterial> MONOCLE = register("victorian", new int[]{0, 3, 2, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0f, 0f, () -> Ingredient.ofItems(Items.GLASS_PANE));
    public static final RegistryEntry<ArmorMaterial> VICTORIAN = register("victorian", new int[]{0, 3, 2, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0f, 0f, () -> Ingredient.ofItems(Items.LEATHER));

    public static final RegistryEntry<ArmorMaterial> PICKELHAUBE = register("imperial_german_uniform", new int[]{4, 3, 2, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3f, 0f, () -> Ingredient.ofItems(ModItems.STEEL_INGOT));
    public static final RegistryEntry<ArmorMaterial> IMPERIAL_GERMAN_UNIFORM = register("imperial_german_uniform", new int[]{4, 3, 2, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0f, 0f, () -> Ingredient.ofItems(Items.LEATHER));

    public static final RegistryEntry<ArmorMaterial> ASTRONAUT = register("space_suit", new int[]{1, 3, 2, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0f, 0f, () -> Ingredient.ofItems(Items.TINTED_GLASS));
    public static final RegistryEntry<ArmorMaterial> SPACE_SUIT = register("space_suit", new int[]{1, 3, 2, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0f, 0f, () -> Ingredient.ofItems(Items.LEATHER));

    public static final RegistryEntry<ArmorMaterial> CTHONAUT = register("depth_suit", new int[]{1, 3, 2, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0f, 0f, () -> Ingredient.ofItems(Items.TINTED_GLASS));
    public static final RegistryEntry<ArmorMaterial> DEPTH_SUIT = register("depth_suit", new int[]{1, 3, 2, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0f, 0f, () -> Ingredient.ofItems(Items.LEATHER));

    public static final RegistryEntry<ArmorMaterial> SCIENTIST = register("hev_suit", new int[]{0, 6, 4, 2}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0f, 0f, () -> Ingredient.ofItems(Items.GLASS_PANE));
    public static final RegistryEntry<ArmorMaterial> HEV_SUIT = register("hev_suit", new int[]{0, 6, 4, 2}, 0, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5f, 0.5f, () -> Ingredient.ofItems(ModItems.XEN_CRYSTAL));

    public static final RegistryEntry<ArmorMaterial> EMPEROR = register("emperor", new int[]{4, 0, 0, 0}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 3f, 0f, () -> Ingredient.ofItems(Items.GOLD_INGOT));

    private static RegistryEntry<ArmorMaterial> register(String id, int[] protection, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        EnumMap<ArmorItem.Type, Integer> defense = new EnumMap<>(ArmorItem.Type.class);

        defense.put(ArmorItem.Type.BOOTS, protection[0]);
        defense.put(ArmorItem.Type.LEGGINGS, protection[1]);
        defense.put(ArmorItem.Type.CHESTPLATE, protection[2]);
        defense.put(ArmorItem.Type.HELMET, protection[3]);
        defense.put(ArmorItem.Type.BODY, 0);

        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(TLOTD.MOD_ID, id), new ArmorMaterial(defense, enchantability, equipSound, repairIngredient, List.of(new ArmorMaterial.Layer(Identifier.of(TLOTD.MOD_ID, id))), toughness, knockbackResistance));
    }
}