package net.tlotd.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.screen.slot.Slot;
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

public class PipeItem extends Item {

    private static final String CONTENT_KEY = "Content";
    private static final String CHARGES_USED_KEY = "ChargesUsed";
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
        if (!stack.hasNbt()) return 1.0f;
        NbtCompound tag = stack.getNbt();
        int used = tag.getInt(CHARGES_USED_KEY);
        int maxCharges = getMaxCharges(stack);
        return Math.max(1.0f - ((float) used / (float) maxCharges), 0);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        if (stack.isOf(ModItems.JOINT)) return true;
        if (!stack.hasNbt()) return false;
        if (!stack.getNbt().contains(CHARGES_USED_KEY, NbtElement.NUMBER_TYPE)) return false;
        if (stack.getNbt().getString(CONTENT_KEY).equals("empty")) return false;
        return true;
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

    private static String getChargeGlyphs(int remaining, int max) {
        remaining = Math.max(0, Math.min(remaining, max));
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
            int usedCharges = (stack.isOf(ModItems.JOINT) || (stack.hasNbt() && stack.getNbt().contains(CHARGES_USED_KEY, NbtElement.NUMBER_TYPE))) ? stack.getOrCreateNbt().getInt(CHARGES_USED_KEY) : 64;
            if (usedCharges < getMaxCharges(stack)) {
                user.addStatusEffect(new StatusEffectInstance(ModEffects.STONED, 600));
                NbtCompound tag = stack.getOrCreateNbt();
                int used = tag.getInt(CHARGES_USED_KEY);
                int refillLevel = EnchantmentHelper.getLevel(ModEnchantments.REFILL_CHARGES, stack);
                int maxCharges = getMaxCharges(stack);
                used++;
                tag.putInt(CHARGES_USED_KEY, used);
                if (used >= maxCharges) {
                    if (stack.isOf(ModItems.JOINT)) {
                        stack.decrement(1);
                    } else {
                        tag.putString(CONTENT_KEY, "empty");
                        if (refillLevel > 0) {
                            for (int i = 0; i < player.getInventory().size(); i++) {
                                ItemStack invStack = player.getInventory().getStack(i);
                                if (!invStack.isEmpty() && invStack.isOf(ModItems.PIPE_WEED)) {
                                    invStack.decrement(1);
                                    tag.putString(CONTENT_KEY, "tlotd:pipe_weed");
                                    tag.putInt(CHARGES_USED_KEY, 0);
                                    player.playSound(SoundEvents.BLOCK_GRASS_STEP, SoundCategory.PLAYERS, 1f, 1f);
                                    break;
                                }
                            }
                        }
                    }
                }
            } else {
                boolean restored = false;
                for (int i = 0; i < player.getInventory().size(); i++) {
                    ItemStack invStack = player.getInventory().getStack(i);
                    if (!invStack.isEmpty() && invStack.isOf(ModItems.PIPE_WEED)) {
                        invStack.decrement(1);
                        stack.getOrCreateNbt().putString(CONTENT_KEY, "tlotd:pipe_weed");
                        stack.getOrCreateNbt().putInt(CHARGES_USED_KEY, 0);
                        world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_GRASS_STEP, SoundCategory.PLAYERS, 0.8f, 1.2f);
                        restored = true;
                        break;
                    }
                }
                if (!restored) {
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHULKER_BULLET_HURT, SoundCategory.PLAYERS, 1f, 1f);
                }
            }
            player.getItemCooldownManager().set(ModItems.JOINT, 20);
            player.getItemCooldownManager().set(ModItems.PIPE, 20);
            player.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        return stack;
    }

    @Override
    public Text getName(ItemStack stack) {
        Text name = super.getName(stack);
        if (stack.isOf(ModItems.JOINT) || !stack.hasNbt()) return name;
        else if (!stack.getNbt().contains(CHARGES_USED_KEY, NbtElement.NUMBER_TYPE)) return name;
        else if (stack.getOrCreateNbt().getString(CONTENT_KEY).equals("tlotd:pipe_weed")) {
            return Text.translatable("item.tlotd.pipe.pipe_weed");
        } else return name;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        boolean clean = !stack.isOf(ModItems.JOINT) && (!stack.hasNbt() || !stack.getNbt().contains(CHARGES_USED_KEY, NbtElement.NUMBER_TYPE));
        NbtCompound tag = stack.getOrCreateNbt();
        String content = tag.getString(CONTENT_KEY);
        int used = tag.getInt(CHARGES_USED_KEY);
        int max = getMaxCharges(stack);
        int remaining = clean ? 0 : Math.max(0, max - used);
        String pictogram = getChargeGlyphs(remaining, max);
        tooltip.add(Text.literal(pictogram).setStyle(Style.EMPTY.withFont(SOUL_CHARGES_FONT_ID).withColor(Formatting.WHITE)));
        if (stack.isOf(ModItems.JOINT) || content.equals("tlotd:pipe_weed")) {
            tooltip.add(Text.translatable("effect.tlotd.stoned").append(Text.literal(" (00:30)")).formatted(Formatting.RED));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType != ClickType.RIGHT) return false;
        return tryRefill(stack, otherStack, player);
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType != ClickType.RIGHT) return false;
        return tryRefill(stack, slot.getStack(), player);
    }

    private boolean tryRefill(ItemStack pipe, ItemStack inputStack, PlayerEntity player) {
        if ((pipe.isOf(ModItems.JOINT) || pipe.hasNbt()) && pipe.getOrCreateNbt().getInt(CHARGES_USED_KEY) < getMaxCharges(pipe))
            return false;
        if (!inputStack.isOf(ModItems.PIPE_WEED)) return false;
        inputStack.decrement(1);
        pipe.getOrCreateNbt().putString(CONTENT_KEY, "tlotd:pipe_weed");
        pipe.getOrCreateNbt().putInt(CHARGES_USED_KEY, 0);
        player.playSound(SoundEvents.BLOCK_GRASS_STEP, 1f, 1f);
        player.getItemCooldownManager().set(ModItems.JOINT, 20);
        player.getItemCooldownManager().set(ModItems.PIPE, 20);
        return true;
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