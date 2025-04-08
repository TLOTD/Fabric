package net.tlotd.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.tlotd.TLOTD;

import java.util.function.Supplier;

public enum ModArmorMaterial implements ArmorMaterial {
    HELIORITE("heliorite", 40, new int[] { 4, 9, 7, 4}, 18, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.2f, () -> Ingredient.ofItems(ModItems.HELIORITE_INGOT)),
    ENDURIUM("endurium", 40, new int[] { 4, 9, 7, 4}, 18, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.2f, () -> Ingredient.ofItems(ModItems.ENDURIUM_INGOT)),
    PALLADIUM("palladium", 45, new int[] { 5, 10, 8, 5}, 21, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.2f, () -> Ingredient.ofItems(ModItems.PALLADIUM_INGOT)),
    JURASSOLINE("jurassoline", 45, new int[] { 5, 10, 8, 5}, 21, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.2f, () -> Ingredient.ofItems(ModItems.JURASSOLINE_INGOT)),
    CINNABAR("cinnabar", 45, new int[] { 5, 10, 8, 5}, 21, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.2f, () -> Ingredient.ofItems(ModItems.CINNABAR_INGOT)),
    NEBULAR("nebular", 45, new int[] { 5, 10, 8, 5}, 21, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4f, 0.2f, () -> Ingredient.ofItems(ModItems.NEBULAR_INGOT)),
    MITHRIL("mithril", 50, new int[] { 6, 12, 10, 6}, 25, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5f, 0.3f, () -> Ingredient.ofItems(ModItems.MITHRIL_INGOT)),

    MONOCLE("victorian", 20, new int[] { 0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0f, 0f, () -> Ingredient.ofItems(Items.GLASS_PANE)),
    VICTORIAN("victorian", 20, new int[] { 0, 3, 2, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0f, 0f, () -> Ingredient.ofItems(Items.LEATHER)),

    PICKELHAUBE("imperial_german_uniform", 20, new int[] { 4, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2f, 0f, () -> Ingredient.ofItems(ModItems.STEEL_INGOT)),
    IMPERIAL_GERMAN_UNIFORM("imperial_german_uniform", 20, new int[] { 0, 3, 2, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0f, 0f, () -> Ingredient.ofItems(Items.LEATHER)),

    ASTRONAUT("space_suit", 20, new int[] { 1, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0f, 0f, () -> Ingredient.ofItems(Items.TINTED_GLASS)),
    SPACE_SUIT("space_suit", 20, new int[] { 0, 3, 2, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0f, 0f, () -> Ingredient.ofItems(Items.LEATHER)),

    CTHONAUT("depth_suit", 20, new int[] { 1, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0f, 0f, () -> Ingredient.ofItems(Items.TINTED_GLASS)),
    DEPTH_SUIT("depth_suit", 20, new int[] { 0, 3, 2, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0f, 0f, () -> Ingredient.ofItems(Items.LEATHER)),

    SCIENTIST("hev_suit", 20, new int[] { 0, 0, 0, 0}, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0f, 0f, () -> Ingredient.ofItems(Items.GLASS_PANE)),
    HEV_SUIT("hev_suit", 30, new int[] { 0, 6, 4, 2}, 0, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 5f, 0.5f, () -> Ingredient.ofItems(ModItems.XEN_CRYSTAL)),

    EMPEROR("emperor", 50, new int[] { 4, 0, 0, 0}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 5f, 0.3f, () -> Ingredient.ofItems(Items.GOLD_INGOT))
    ;

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = {11, 16, 15, 13 };

    ModArmorMaterial(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return TLOTD.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
