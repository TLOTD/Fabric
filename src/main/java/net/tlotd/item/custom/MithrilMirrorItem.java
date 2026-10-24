package net.tlotd.item.custom;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.tlotd.components.ModComponents;
import net.tlotd.enchantments.ModEnchantments;
import net.tlotd.item.ModItems;
import net.tlotd.sound.ModSounds;
import net.tlotd.world.dimension.ModDimensionsDataGenerator;

import java.util.List;
import java.util.Optional;

public class MithrilMirrorItem extends Item {

    public MithrilMirrorItem(Settings settings) {
        super(settings);
    }

    private static final String CHARGES_USED_KEY = "ChargesUsed";
    private static final int BASE_MAX_CHARGES = 5;
    private static final int CHARGES_PER_LEVEL = 1;
    public static final Identifier TENGWAR_FONT_ID = Identifier.of("tlotd", "tengwar");
    public static final Identifier ILLAGER_FONT_ID = Identifier.of("minecraft", "illageralt");
    public static final Identifier SOUL_CHARGES_FONT_ID = Identifier.of("tlotd", "soul_charges");

    private static int getChargesUsed(ItemStack stack) {
        return stack.getOrDefault(ModComponents.MITHRIL_MIRROR_CHARGES_USED, 0);
    }

    private static void setChargesUsed(ItemStack stack, int charges) {
        stack.set(ModComponents.MITHRIL_MIRROR_CHARGES_USED, Math.max(0, charges));
    }

    public float getProgress(ItemStack stack) {
        int used = getChargesUsed(stack);
        int maxCharges = getMaxCharges(stack);
        return maxCharges <= 0 ? 0.0f : Math.max(1.0f - ((float) used / maxCharges), 0.0f);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        if (EnchantmentHelper.getEnchantments(stack).getEnchantmentEntries().stream().anyMatch(entry -> entry.getKey().getKey().filter(ModEnchantments.CURSED_REFLECTION::equals).isPresent() && entry.getIntValue() > 0)) {
            return false;
        }
        return getChargesUsed(stack) > 0;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round(getProgress(stack) * 13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        boolean hasCursedReflection = EnchantmentHelper.getEnchantments(stack).getEnchantmentEntries().stream().anyMatch(entry -> entry.getKey().getKey().filter(ModEnchantments.CURSED_REFLECTION::equals).isPresent() && entry.getIntValue() > 0);
        return hasCursedReflection ? 0xB00000 : 0x3C009C;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!(user instanceof PlayerEntity player)) return stack;
        if (!world.isClient()) {
            int usedCharges = getChargesUsed(stack);
            if (usedCharges < getMaxCharges(stack)) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                ServerWorld targetWorld = serverPlayer.server.getWorld(serverPlayer.getSpawnPointDimension());
                if (targetWorld != null) {
                    int transLevel = EnchantmentHelper.getEnchantments(stack).getEnchantmentEntries().stream().filter(entry -> entry.getKey().getKey().filter(ModEnchantments.TRANSDIMENSIONAL::equals).isPresent()).mapToInt(Object2IntMap.Entry::getIntValue).findFirst().orElse(0);
                    int used = getChargesUsed(stack);
                    int maxCharges = getMaxCharges(stack);
                    int remaining = maxCharges - used;
                    boolean crossDim = !targetWorld.getRegistryKey().equals(player.getWorld().getRegistryKey());
                    int cost = 0;
                    if (player.getWorld().getRegistryKey().equals(ModDimensionsDataGenerator.BACKROOMS_LEVEL_KEY)) {
                        player.sendMessage(Text.translatable("item.tlotd.mithril_mirror.cannot_escape", cost).formatted(Formatting.RED), true);
                        world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHULKER_BULLET_HURT, SoundCategory.PLAYERS, 1f, 1f);
                        player.getItemCooldownManager().set(ModItems.MITHRIL_MIRROR, getMaxUseTime(stack, user) * 20);
                        return stack;
                    } else if (targetWorld.getRegistryKey().equals(ModDimensionsDataGenerator.BACKROOMS_LEVEL_KEY)) {
                        player.sendMessage(Text.translatable("item.tlotd.mithril_mirror.destination_unknown", cost).formatted(Formatting.RED), true);
                        world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHULKER_BULLET_HURT, SoundCategory.PLAYERS, 1f, 1f);
                        player.getItemCooldownManager().set(ModItems.MITHRIL_MIRROR, getMaxUseTime(stack, user) * 20);
                        return stack;
                    } else if (crossDim && transLevel > 0) {
                        cost = switch (transLevel) {
                            case 1 -> 5;
                            case 2 -> 4;
                            case 3 -> 3;
                            case 4 -> 2;
                            default -> 1;
                        };
                        if (remaining < cost) {
                            player.sendMessage(Text.translatable("item.tlotd.mithril_mirror.charges_requirement", cost).formatted(Formatting.RED), true);
                            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHULKER_BULLET_HURT, SoundCategory.PLAYERS, 1f, 1f);
                            player.getItemCooldownManager().set(ModItems.MITHRIL_MIRROR, getMaxUseTime(stack, user) * 20);
                            return stack;
                        }
                    } else if (crossDim) {
                        player.sendMessage(Text.translatable("item.tlotd.mithril_mirror.transdimensional").formatted(Formatting.RED), true);
                        world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHULKER_BULLET_HURT, SoundCategory.PLAYERS, 1f, 1f);
                        player.getItemCooldownManager().set(ModItems.MITHRIL_MIRROR, getMaxUseTime(stack, user) * 20);
                        return stack;
                    }
                    TeleportTarget respawnTarget = serverPlayer.getRespawnTarget(false, TeleportTarget.NO_OP);
                    ServerWorld respawnWorld = respawnTarget.world();
                    Vec3d respawnPos = respawnTarget.pos();
                    serverPlayer.teleport(respawnWorld, respawnPos.x, respawnPos.y, respawnPos.z, respawnTarget.yaw(), respawnTarget.pitch());
                    BlockPos soundPos = BlockPos.ofFloored(respawnPos);
                    respawnWorld.playSound(null, soundPos, ModSounds.ITEM_MITHRIL_MIRROR, SoundCategory.PLAYERS, 0.4F, 0.8F);
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 20, 0, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20, 0, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 20, 0, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 20, 0, false, false));
                    int protection = EnchantmentHelper.getEnchantments(stack).getEnchantmentEntries().stream().filter(entry -> entry.getKey().getKey().filter(ModEnchantments.REINFORCED_GLASS::equals).isPresent()).mapToInt(Object2IntMap.Entry::getIntValue).findFirst().orElse(0);
                    if (protection > 0) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, protection * 20, 4, false, false));
                    }
                    int curseLevel = EnchantmentHelper.getEnchantments(stack).getEnchantmentEntries().stream().filter(entry -> entry.getKey().getKey().filter(ModEnchantments.CURSED_REFLECTION::equals).isPresent()).mapToInt(Object2IntMap.Entry::getIntValue).findFirst().orElse(0);
                    int restorativeLevel = EnchantmentHelper.getEnchantments(stack).getEnchantmentEntries().stream().filter(entry -> entry.getKey().getKey().filter(ModEnchantments.REFILL_CHARGES::equals).isPresent()).mapToInt(Object2IntMap.Entry::getIntValue).findFirst().orElse(0);
                    if (curseLevel > 0) {
                        player.setHealth(1.0F);
                        world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_WITHER_SPAWN, SoundCategory.PLAYERS, 0.8f, 0.6f);
                    }
                    if (curseLevel == 0) {
                        used += crossDim ? cost : 1;
                        setChargesUsed(stack, used);
                    }
                    if (used >= maxCharges) {
                        Item requiredFuel = (curseLevel > 0) ? ModItems.CURSED_SOUL_FLASK : ModItems.SOUL_FLASK_OF_THE_ABYSS;
                        if (restorativeLevel > 0) {
                            for (int i = 0; i < player.getInventory().size(); i++) {
                                ItemStack invStack = player.getInventory().getStack(i);
                                if (!invStack.isEmpty() && invStack.isOf(requiredFuel)) {
                                    invStack.decrement(1);
                                    if (!player.getInventory().insertStack(ModItems.TINTED_GLASS_FLASK.getDefaultStack())) {
                                        player.dropItem(ModItems.TINTED_GLASS_FLASK.getDefaultStack(), false);
                                    }
                                    setChargesUsed(stack, 0);
                                    world.playSound(null, player.getBlockPos(), (curseLevel > 0) ? SoundEvents.ENTITY_WITHER_HURT : SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, SoundCategory.PLAYERS, 0.8f, 1.2f);
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHULKER_BULLET_HURT, SoundCategory.BLOCKS, 1f, 1f);
                }
            } else {
                boolean restored = false;
                boolean isCursed = EnchantmentHelper.getEnchantments(stack).getEnchantmentEntries().stream().anyMatch(entry -> entry.getKey().getKey().filter(ModEnchantments.CURSED_REFLECTION::equals).isPresent() && entry.getIntValue() > 0);
                Item requiredFuel = isCursed ? ModItems.CURSED_SOUL_FLASK : ModItems.SOUL_FLASK_OF_THE_ABYSS;
                for (int i = 0; i < player.getInventory().size(); i++) {
                    ItemStack invStack = player.getInventory().getStack(i);
                    if (!invStack.isEmpty() && invStack.isOf(requiredFuel)) {
                        invStack.decrement(1);
                        if (!player.getInventory().insertStack(ModItems.TINTED_GLASS_FLASK.getDefaultStack())) {
                            player.dropItem(ModItems.TINTED_GLASS_FLASK.getDefaultStack(), false);
                        }
                        setChargesUsed(stack, 0);
                        world.playSound(null, player.getBlockPos(), isCursed ? SoundEvents.ENTITY_WITHER_HURT : SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, SoundCategory.PLAYERS, 0.8f, 1.2f);
                        restored = true;
                        break;
                    }
                }
                if (!restored) {
                    player.sendMessage(Text.translatable("item.tlotd.mithril_mirror.foggy.charge_item_missing").formatted(Formatting.RED), true);
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHULKER_BULLET_HURT, SoundCategory.PLAYERS, 1f, 1f);
                }
                player.getItemCooldownManager().set(ModItems.MITHRIL_MIRROR, getMaxUseTime(stack, user) * 5);
                player.incrementStat(Stats.USED.getOrCreateStat(this));
                return stack;
            }
        }
        player.getItemCooldownManager().set(ModItems.MITHRIL_MIRROR, getMaxUseTime(stack, user) * 20);
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        return stack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        int speedLevel = EnchantmentHelper.getEnchantments(stack).getEnchantmentEntries().stream().filter(entry -> entry.getKey().getKey().filter(ModEnchantments.SHARPER_LENS::equals).isPresent()).mapToInt(Object2IntMap.Entry::getIntValue).findFirst().orElse(0);
        int useTime = 40;
        if (speedLevel > 0) {
            useTime = switch (speedLevel) {
                case 1 -> 35;
                case 2 -> 30;
                case 3 -> 25;
                case 4 -> 22;
                case 5 -> 20;
                case 6 -> 18;
                case 7 -> 16;
                case 8 -> 14;
                case 9 -> 12;
                default -> 10;
            };
        }
        return useTime;
    }

    public static int getMaxCharges(ItemStack stack) {
        int abyssLevel = EnchantmentHelper.getEnchantments(stack).getEnchantmentEntries().stream().filter(entry -> entry.getKey().getKey().filter(ModEnchantments.DEPTH_OF_THE_ABYSS::equals).isPresent()).mapToInt(Object2IntMap.Entry::getIntValue).findFirst().orElse(0);
        return BASE_MAX_CHARGES + (abyssLevel * CHARGES_PER_LEVEL);
    }

    private static String getChargeGlyphs(int remaining, int max, boolean cursed) {
        remaining = Math.max(0, Math.min(remaining, max));
        String bottleEmpty = cursed ? "\uE008" : "\uE010";
        String bottleFull = cursed ? "\uE00B" : "\uE013";
        String empty = cursed ? "\uE009" : "\uE011";
        String full = cursed ? "\uE00C" : "\uE014";
        String tipEmpty = cursed ? "\uE00A" : "\uE012";
        String tipFull = cursed ? "\uE00D" : "\uE015";
        StringBuilder bar = new StringBuilder();
        bar.append(remaining == 0 ? bottleEmpty : bottleFull);
        for (int i = 0; i < max - 1; i++) {
            bar.append(i < remaining ? full : empty);
        }
        bar.append(remaining == max ? tipFull : tipEmpty);
        return bar.toString();
    }

    @Override
    public Text getName(ItemStack stack) {
        Text name = super.getName(stack);
        if (EnchantmentHelper.getEnchantments(stack).getEnchantmentEntries().stream().anyMatch(entry -> entry.getKey().getKey().filter(ModEnchantments.CURSED_REFLECTION::equals).isPresent() && entry.getIntValue() > 0)) {
            return name.copy().formatted(Formatting.RED);
        }
        if (getChargesUsed(stack) < getMaxCharges(stack)) {
            return name;
        }
        return Text.translatable("item.tlotd.mithril_mirror.foggy");
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        Style style = getName().getStyle();
        int used = getChargesUsed(stack);
        int max = getMaxCharges(stack);
        int remaining = Math.max(0, max - used);
        boolean cursed = EnchantmentHelper.getEnchantments(stack).getEnchantmentEntries().stream().anyMatch(entry -> entry.getKey().getKey().filter(ModEnchantments.CURSED_REFLECTION::equals).isPresent() && entry.getIntValue() > 0);
        String pictogram = getChargeGlyphs(remaining, max, cursed);
        tooltip.add(Text.literal(pictogram).setStyle(Style.EMPTY.withFont(SOUL_CHARGES_FONT_ID).withColor(Formatting.WHITE)));
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.tlotd.mithril_mirror.tooltip").formatted(Formatting.GRAY));
        } else if (cursed) {
            tooltip.add(Text.translatable("item.tlotd.mithril_mirror.tooltip").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
        } else {
            tooltip.add(Text.translatable("item.tlotd.mithril_mirror.tooltip_tengwar").setStyle(style.withFont(TENGWAR_FONT_ID)).formatted(Formatting.GRAY));
        }
        if (cursed) {
            tooltip.add(Text.translatable("item.tlotd.desc_occult").formatted(Formatting.RED));
        } else {
            tooltip.add(Text.translatable("item.tlotd.desc_eldritch").setStyle(style.withColor(0x3C009C)));
        }
        super.appendTooltip(stack, context, tooltip, type);
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

    private boolean tryRefill(ItemStack mirror, ItemStack inputStack, PlayerEntity player) {
        if (getChargesUsed(mirror) < getMaxCharges(mirror)) {
            return false;
        }
        boolean cursed = EnchantmentHelper.getEnchantments(mirror).getEnchantmentEntries().stream().anyMatch(entry -> entry.getKey().getKey().filter(ModEnchantments.CURSED_REFLECTION::equals).isPresent() && entry.getIntValue() > 0);
        Item requiredFuel = cursed ? ModItems.CURSED_SOUL_FLASK : ModItems.SOUL_FLASK_OF_THE_ABYSS;
        if (!inputStack.isOf(requiredFuel)) {
            return false;
        }
        inputStack.decrement(1);
        ItemStack empty = ModItems.TINTED_GLASS_FLASK.getDefaultStack();
        if (!player.getInventory().insertStack(empty)) {
            player.dropItem(empty, false);
        }
        setChargesUsed(mirror, 0);
        player.playSound(cursed ? SoundEvents.ENTITY_WITHER_HURT : SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, 1f, 1f);
        player.getItemCooldownManager().set(ModItems.MITHRIL_MIRROR, getMaxUseTime(mirror, player) * 20);
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