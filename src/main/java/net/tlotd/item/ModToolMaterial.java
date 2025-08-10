package net.tlotd.item;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum ModToolMaterial implements ToolMaterial {
    BAMBOO(0, 42, 2.0F, 0.0F, 12, () -> Ingredient.ofItems(Items.BAMBOO)),
    HELIORITE(5, 2324,11.0f, 4.0f,18, () -> Ingredient.ofItems(ModItems.HELIORITE_INGOT)),
    ENDURIUM(5, 2580,11.0f, 4.0f,18, () -> Ingredient.ofItems(ModItems.ENDURIUM_INGOT)),
    PALLADIUM(6, 2484,14.0f, 5.0f,21, () -> Ingredient.ofItems(ModItems.PALLADIUM_INGOT)),
    JURASSOLINE(6, 2612,14.0f, 5.0f,21, () -> Ingredient.ofItems(ModItems.JURASSOLINE_INGOT)),
    CINNABAR(7, 3162,14.0f, 6.0f,21, () -> Ingredient.ofItems(ModItems.CINNABAR_INGOT)),
    NEBULAR(7, 3418,14.0f, 6.0f,21, () -> Ingredient.ofItems(ModItems.NEBULAR_INGOT)),
    MITHRIL(8, 4096,16.0f, 7.0f,25, () -> Ingredient.ofItems(ModItems.MITHRIL_INGOT)),
    ASTRAL(9, -1,18.0f, 8.0f,28, () -> Ingredient.ofItems()),
    DIVINE(10, -1,20.0f, 9.0f,32, () -> Ingredient.ofItems()),
    CATACLYSMIC(11, -1,22.0f, 10.0f,36, () -> Ingredient.ofItems()),
    ELDRITCH(12, -1,24.0f, 11.0f,40, () -> Ingredient.ofItems())
    ;

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantablility;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterial(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantablility, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantablility = enchantablility;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantablility;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}