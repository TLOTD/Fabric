package net.tlotd.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.tlotd.effect.ModEffects;

public class ModFoodComponents {

    public static final FoodComponent CARAMEL = new FoodComponent.Builder().snack().hunger(2).saturationModifier(0.2f).build();

    public static final FoodComponent HEMP_COOKIE = new FoodComponent.Builder().snack().hunger(2).saturationModifier(0.2f).statusEffect(new StatusEffectInstance(ModEffects.STONED, 600, 0), 1.0F).build();

    public static final FoodComponent TOAST = new FoodComponent.Builder().snack().hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent JAM_TOAST = new FoodComponent.Builder().snack().hunger(5).saturationModifier(0.5f).build();
    public static final FoodComponent DROOPFRUIT_JAM_TOAST = new FoodComponent.Builder().snack().hunger(5).saturationModifier(0.5f).statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 400, 2), 1.0F).build();
    public static final FoodComponent ANCIENT_SOULBERRY_JAM_TOAST = new FoodComponent.Builder().snack().hunger(7).saturationModifier(1.7f).build();

    public static final FoodComponent STRAWBERRY = new FoodComponent.Builder().snack().hunger(2).saturationModifier(0.2f).build();
    public static final FoodComponent CHOCOLATE_STRAWBERRY = new FoodComponent.Builder().snack().hunger(4).saturationModifier(0.4f).build();
    public static final FoodComponent ORANGE = new FoodComponent.Builder().snack().hunger(3).saturationModifier(0.3f).build();
    public static final FoodComponent DINOSAUR_MEAT = new FoodComponent.Builder().hunger(4).saturationModifier(0.4f).meat().build();
    public static final FoodComponent DINOSAUR_MEAT_COOKED = new FoodComponent.Builder().hunger(10).saturationModifier(1.0f).meat().build();

    public static final FoodComponent PORRIDGE = new FoodComponent.Builder().hunger(5).saturationModifier(0.5f).build();

    public static final FoodComponent RAW_SCHNITZEL = new FoodComponent.Builder().hunger(6).saturationModifier(0.5f).meat().build();
    public static final FoodComponent SCHNITZEL = new FoodComponent.Builder().hunger(14).saturationModifier(1.2f).meat().build();

    public static final FoodComponent CALAMARI = new FoodComponent.Builder().snack().hunger(2).saturationModifier(0.2f).meat().build();
    public static final FoodComponent FRIED_CALAMARI = new FoodComponent.Builder().snack().hunger(4).saturationModifier(0.4f).meat().build();

    public static final FoodComponent MAULTASCHE = new FoodComponent.Builder().hunger(4).saturationModifier(0.4f).meat().build();
    public static final FoodComponent MAULTASCHEN_BROTH = new FoodComponent.Builder().hunger(14).saturationModifier(1.4f).meat().build();
    public static final FoodComponent SUSPICIOUS_MAULTASCHEN_BROTH = new FoodComponent.Builder().hunger(14).saturationModifier(1.4f).statusEffect(new StatusEffectInstance(StatusEffects.WITHER, 400, 2), 1.0F).meat().build();
    public static final FoodComponent WOODEN_LIQUID_STEIN = (new FoodComponent.Builder()).hunger(1).saturationModifier(0.1F).build();

    public static final FoodComponent SMALL_SPEZI = (new FoodComponent.Builder()).hunger(1).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 0), 1.0F).build();
    public static final FoodComponent SMALL_BEER = (new FoodComponent.Builder()).hunger(1).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(ModEffects.DRUNK, 200, 0), 1.0F).build();
    public static final FoodComponent LARGE_BEER = (new FoodComponent.Builder()).hunger(1).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(ModEffects.DRUNK, 600, 0), 1.0F).build();

    public static final FoodComponent CURED_MEAT = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f).meat().build();
    public static final FoodComponent COOKED_MEAT = new FoodComponent.Builder().hunger(6).saturationModifier(0.6f).meat().build();
}
