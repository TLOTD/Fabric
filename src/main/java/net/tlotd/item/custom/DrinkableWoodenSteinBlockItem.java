package net.tlotd.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.effect.ModEffects;
import net.tlotd.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DrinkableWoodenSteinBlockItem extends BlockItem {

    private final String compat;

    public DrinkableWoodenSteinBlockItem(Block block, Settings settings, String compat) {
        super(block, settings);
        this.compat = compat;
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (stack.isOf(ModBlocks.WOODEN_MILK_STEIN.asItem())) {
            user.clearStatusEffects();
        } else if (stack.isOf(ModBlocks.WOODEN_CARAMEL_MILKSHAKE_STEIN.asItem()) || stack.isOf(ModBlocks.WOODEN_STRAWBERRY_MILKSHAKE_STEIN.asItem()) || stack.isOf(ModBlocks.WOODEN_ORANGE_MILKSHAKE_STEIN.asItem()) || stack.isOf(ModBlocks.WOODEN_CHOCOLATE_MILKSHAKE_STEIN.asItem()) || stack.isOf(ModBlocks.WOODEN_BLUE_BERRY_MILKSHAKE_STEIN.asItem())) {
            user.clearStatusEffects();
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 10, 0));
        } else if (stack.isOf(ModBlocks.WOODEN_TORCHBERRY_MILKSHAKE_STEIN.asItem())) {
            user.clearStatusEffects();
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 10, 0));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 10, 0));
        } else if (stack.isOf(ModBlocks.HOT_WOODEN_MILK_STEIN.asItem()) || stack.isOf(ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN.asItem())) {
            user.clearStatusEffects();
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1, 3));
        } else if (stack.isOf(ModBlocks.WOODEN_HOT_COFFEE_STEIN.asItem())) {
            if (user instanceof PlayerEntity) {
                PlayerEntity playerEntity = (PlayerEntity) user;
                //INSOMNIA LOGIC
            }
        }
        super.finishUsing(stack, world, user);
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (stack.isEmpty()) {
            return new ItemStack(ModBlocks.WOODEN_STEIN);
        } else {
            if (user instanceof PlayerEntity && !((PlayerEntity) user).getAbilities().creativeMode) {
                ItemStack itemStack = new ItemStack(ModBlocks.WOODEN_STEIN);
                PlayerEntity playerEntity = (PlayerEntity) user;
                if (!playerEntity.getInventory().insertStack(itemStack)) {
                    playerEntity.dropItem(itemStack, false);
                }
            }

            return stack;
        }
    }

    public int getMaxUseTime(ItemStack stack) {
        return 40;
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    public SoundEvent getDrinkSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier MODS_FONT_ID = new Identifier("tlotd", "mods");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.isOf(ModBlocks.WOODEN_BEER_STEIN.asItem()) || stack.isOf(ModBlocks.WOODEN_MEAD_STEIN.asItem())) {
            tooltip.add(Text.translatable("effect.tlotd.drunkenness").append(Text.literal(" (00:30)")).formatted(Formatting.RED));
        }
        if (context.isCreative() && !compat.isEmpty()) {
            Style style = this.getName().getStyle();
            if (compat.contains("aet")) {
                tooltip.add(Text.literal("\uE008 ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.aether.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
            if (compat.contains("tlf")) {
                tooltip.add(Text.literal("\uE009 ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.twilightforest.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
