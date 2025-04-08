package net.tlotd.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.effect.ModEffects;

public class DrinkableWoodenSteinBlockItem extends BlockItem {

    public DrinkableWoodenSteinBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (stack.getItem() == ModBlocks.WOODEN_BEER_STEIN.asItem()) {
            user.addStatusEffect(new StatusEffectInstance(ModEffects.DRUNK, 600));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200));
        } else if (stack.getItem() == ModBlocks.WOODEN_MILK_STEIN.asItem()) {
            user.clearStatusEffects();
        } else if (stack.getItem() == ModBlocks.WOODEN_STRAWBERRY_MILKSHAKE_STEIN.asItem() || stack.getItem() == ModBlocks.WOODEN_ORANGE_MILKSHAKE_STEIN.asItem() || stack.getItem() == ModBlocks.WOODEN_CHOCOLATE_MILKSHAKE_STEIN.asItem()) {
            user.clearStatusEffects();
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 10, 0));
        } else if (stack.getItem() == ModBlocks.HOT_WOODEN_MILK_STEIN.asItem() || stack.getItem() == ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN.asItem()) {
            user.clearStatusEffects();
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1, 3));
        }
        super.finishUsing(stack, world, user);
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (stack.isEmpty()) {
            return new ItemStack(ModBlocks.WOODEN_STEIN);
        } else {
            if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode) {
                ItemStack itemStack = new ItemStack(ModBlocks.WOODEN_STEIN);
                PlayerEntity playerEntity = (PlayerEntity)user;
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
}
