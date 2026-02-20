package net.tlotd.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.tlotd.effect.ModEffects;
import net.tlotd.enchantments.ModEnchantments;
import net.tlotd.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class PipeItem extends Item {

    private static final int BASE_MAX_CHARGES = 3;
    private static final int CHARGES_PER_LEVEL = 1;
    private static final int USE_TIME = 20;
    public static final Identifier SOUL_CHARGES_FONT_ID = new Identifier("tlotd", "soul_charges");

    public PipeItem(Settings settings) {
        super(settings);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.TOOT_HORN;
    }

    public float getProgress(ItemStack stack) {
        if (stack.isOf(ModItems.PIPE)) return 0.0f;
        if (!stack.hasNbt()) return 1.0f;
        NbtCompound tag = stack.getNbt();
        int used = tag.getInt("ChargesUsed");
        int maxCharges = getMaxCharges(stack);
        return 1.0f - ((float) used / (float) maxCharges);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        if (stack.isOf(ModItems.PIPE_WEED_PIPE) || stack.isOf(ModItems.JOINT)) return true;
        if (!stack.hasNbt()) return false;
        return stack.getNbt().getInt("ChargesUsed") > 0;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round(getProgress(stack) * 13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0x486B3E;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    public int getMaxUseTime(ItemStack stack) {
        return USE_TIME;
    }

    private int getMaxCharges(ItemStack stack) {
        int abyssLevel = EnchantmentHelper.getLevel(ModEnchantments.RESOURCEFUL_SMOKING, stack);
        return BASE_MAX_CHARGES + (abyssLevel * CHARGES_PER_LEVEL);
    }

    private static String getChargeGlyphs(int remaining, int max, boolean empty) {
        remaining = Math.max(0, Math.min(remaining, max));
        if (empty) {
            remaining = 0;
        }
        StringBuilder bar = new StringBuilder();
        bar.append(remaining == 0 ? "\uE038" : "\uE03B");
        for (int i = 0; i < max - 1; i++) {
            bar.append(i < remaining ? "\uE03C" : "\uE039");
        }
        bar.append(remaining == max ? "\uE03D" : "\uE03A");
        return bar.toString();
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!(user instanceof PlayerEntity player)) return stack;
        if (!world.isClient()) {
            if (stack.isOf(ModItems.PIPE)) {
                boolean restored = false;
                for (int i = 0; i < player.getInventory().size(); i++) {
                    ItemStack invStack = player.getInventory().getStack(i);
                    if (!invStack.isEmpty() && invStack.isOf(ModItems.PIPE_WEED)) {
                        invStack.decrement(1);
                        ItemStack filledPipe = ModItems.PIPE_WEED_PIPE.getDefaultStack();
                        Map<Enchantment, Integer> enchants = EnchantmentHelper.get(stack);
                        EnchantmentHelper.set(enchants, filledPipe);
                        NbtCompound newTag = stack.getOrCreateNbt().copy();
                        newTag.putInt("ChargesUsed", 0);
                        filledPipe.setNbt(newTag);
                        player.getInventory().removeOne(stack);
                        if (!player.getInventory().insertStack(filledPipe)) {
                            player.dropItem(filledPipe, false);
                        }
                        world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_GRASS_STEP, SoundCategory.PLAYERS, 0.8f, 1.2f);
                        restored = true;
                        break;
                    }
                }
                if (!restored) {
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHULKER_BULLET_HURT, SoundCategory.PLAYERS, 1f, 1f);
                }
            }
            if (stack.isOf(ModItems.PIPE_WEED_PIPE) || stack.isOf(ModItems.JOINT)) {
                user.addStatusEffect(new StatusEffectInstance(ModEffects.STONED, 600));
                NbtCompound tag = stack.getOrCreateNbt();
                int used = tag.getInt("ChargesUsed");
                int refillLevel = EnchantmentHelper.getLevel(ModEnchantments.REFILL_CHARGES, stack);
                int maxCharges = getMaxCharges(stack);
                used++;
                tag.putInt("ChargesUsed", used);
                if (used >= maxCharges) {
                    boolean refilled = false;
                    if (refillLevel > 0 && stack.isOf(ModItems.PIPE_WEED_PIPE)) {
                        for (int i = 0; i < player.getInventory().size(); i++) {
                            ItemStack invStack = player.getInventory().getStack(i);
                            if (!invStack.isEmpty() && invStack.isOf(ModItems.PIPE_WEED)) {
                                invStack.decrement(1);
                                tag.putInt("ChargesUsed", 0);
                                refilled = true;
                                world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_GRASS_STEP, SoundCategory.PLAYERS, 1f, 1f);
                                break;
                            }
                        }
                    }
                    if (!refilled) {
                        player.getInventory().removeOne(stack);
                        if (stack.isOf(ModItems.PIPE_WEED_PIPE)) {
                            ItemStack depleted = ModItems.PIPE.getDefaultStack();
                            depleted.setNbt(stack.getOrCreateNbt().copy());
                            Map<Enchantment, Integer> enchants = EnchantmentHelper.get(stack);
                            EnchantmentHelper.set(enchants, depleted);
                            if (!player.getInventory().insertStack(depleted)) {
                                player.dropItem(depleted, false);
                            }
                        }
                    }
                }
            }
            player.getItemCooldownManager().set(ModItems.PIPE, 20);
            player.getItemCooldownManager().set(ModItems.PIPE_WEED_PIPE, 20);
            player.getItemCooldownManager().set(ModItems.JOINT, 20);
            player.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        return stack;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound tag = stack.getOrCreateNbt();
        int used = tag.getInt("ChargesUsed");
        int max = getMaxCharges(stack);
        int remaining = Math.max(0, max - used);
        boolean empty = stack.isOf(ModItems.PIPE);
        String pictogram = getChargeGlyphs(remaining, max, empty);
        tooltip.add(Text.literal(pictogram).setStyle(Style.EMPTY.withFont(SOUL_CHARGES_FONT_ID).withColor(Formatting.WHITE)));
        if (stack.isOf(ModItems.PIPE_WEED_PIPE)) {
            tooltip.add(Text.translatable("effect.tlotd.stoned").append(Text.literal(" (00:30)")).formatted(Formatting.RED));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public int getEnchantability() {
        return 10;
    }
}