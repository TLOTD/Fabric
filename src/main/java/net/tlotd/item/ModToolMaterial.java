package net.tlotd.item;

import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.tlotd.util.ModTags;

import java.util.function.Supplier;

public enum ModToolMaterial implements ToolMaterial {
    BAMBOO(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 42, 2.0F, 0.0F, 12, () -> Ingredient.ofItems(Items.BAMBOO)),
    HELIORITE(ModTags.Blocks.INCORRECT_FOR_LEVEL_5_TOOL, 2324,11.0f, 5.0f,18, () -> Ingredient.ofItems(ModItems.HELIORITE_INGOT)),
    ENDURIUM(ModTags.Blocks.INCORRECT_FOR_LEVEL_5_TOOL, 2580,11.0f, 5.0f,18, () -> Ingredient.ofItems(ModItems.ENDURIUM_INGOT)),
    PALLADIUM(ModTags.Blocks.INCORRECT_FOR_LEVEL_6_TOOL, 2484,14.0f, 6.0f,21, () -> Ingredient.ofItems(ModItems.PALLADIUM_INGOT)),
    JURASSOLINE(ModTags.Blocks.INCORRECT_FOR_LEVEL_6_TOOL, 2612,14.0f, 6.0f,21, () -> Ingredient.ofItems(ModItems.JURASSOLINE_INGOT)),
    CINNABAR(ModTags.Blocks.INCORRECT_FOR_LEVEL_7_TOOL, 3162,14.0f, 7.0f,21, () -> Ingredient.ofItems(ModItems.CINNABAR_INGOT)),
    NEBULAR(ModTags.Blocks.INCORRECT_FOR_LEVEL_7_TOOL, 3418,14.0f, 7.0f,21, () -> Ingredient.ofItems(ModItems.NEBULAR_INGOT)),
    MITHRIL(ModTags.Blocks.INCORRECT_FOR_LEVEL_8_TOOL, 4096,16.0f, 8.0f,25, () -> Ingredient.ofItems(ModItems.MITHRIL_INGOT)),

    ASTRAL(ModTags.Blocks.INCORRECT_FOR_LEVEL_9_TOOL, -1,18.0f, 9.0f,28, () -> Ingredient.ofItems()),
    DIVINE(ModTags.Blocks.INCORRECT_FOR_LEVEL_10_TOOL, -1,20.0f, 10.0f,32, () -> Ingredient.ofItems()),
    CATACLYSMIC(ModTags.Blocks.INCORRECT_FOR_LEVEL_11_TOOL, -1,22.0f, 11.0f,36, () -> Ingredient.ofItems()),
    ELDRITCH(ModTags.Blocks.INCORRECT_FOR_LEVEL_12_TOOL, -1,24.0f, 12.0f,40, () -> Ingredient.ofItems())
    ;

    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantablility;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterial(TagKey<Block> inverseTag, int itemDurability, float miningSpeed, float attackDamage, int enchantablility, Supplier<Ingredient> repairIngredient) {
        this.inverseTag = inverseTag;
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
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
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