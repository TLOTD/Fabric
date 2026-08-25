package net.tlotd.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.nbt.NbtCompound;
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
import net.minecraft.world.World;
import net.tlotd.enchantments.ModEnchantments;
import net.tlotd.item.ModItems;
import net.tlotd.sound.ModSounds;
import net.tlotd.world.dimension.ModDimensionsDataGenerator;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MithrilMirrorItem extends Item {

    private static final String CHARGES_USED_KEY = "ChargesUsed";
    private static final int BASE_MAX_CHARGES = 5;
    private static final int CHARGES_PER_LEVEL = 1;
    public static final Identifier TENGWAR_FONT_ID = new Identifier("tlotd", "tengwar");
    public static final Identifier ILLAGER_FONT_ID = new Identifier("minecraft", "illageralt");
    public static final Identifier SOUL_CHARGES_FONT_ID = new Identifier("tlotd", "soul_charges");

    public MithrilMirrorItem(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
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
        if (!stack.hasNbt()) return false;
        if (EnchantmentHelper.getLevel(ModEnchantments.CURSED_REFLECTION, stack) > 0) return false;
        return stack.getNbt().getInt(CHARGES_USED_KEY) > 0;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round(getProgress(stack) * 13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        if (EnchantmentHelper.getLevel(ModEnchantments.CURSED_REFLECTION, stack) > 0) {
            return 0xB00000;
        }
        return 0x3C009C;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!(user instanceof PlayerEntity player)) return stack;
        if (!world.isClient()) {
            int usedCharges = stack.hasNbt() ? stack.getOrCreateNbt().getInt(CHARGES_USED_KEY) : 0;
            if (usedCharges < getMaxCharges(stack)) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                ServerWorld targetWorld = serverPlayer.server.getWorld(serverPlayer.getSpawnPointDimension());
                if (targetWorld != null) {
                    BlockPos spawnpoint = serverPlayer.getSpawnPointPosition();
                    boolean worldspawn = false;
                    int transLevel = EnchantmentHelper.getLevel(ModEnchantments.TRANSDIMENSIONAL, stack);
                    NbtCompound tag = stack.getOrCreateNbt();
                    int used = tag.getInt(CHARGES_USED_KEY);
                    int maxCharges = getMaxCharges(stack);
                    int remaining = maxCharges - used;
                    boolean crossDim = !targetWorld.getRegistryKey().equals(player.getWorld().getRegistryKey());
                    int cost = 0;
                    if (player.getWorld().getRegistryKey().equals(ModDimensionsDataGenerator.BACKROOMS_LEVEL_KEY)) {
                        player.sendMessage(Text.translatable("item.tlotd.mithril_mirror.cannot_escape", cost).formatted(Formatting.RED), true);
                        world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHULKER_BULLET_HURT, SoundCategory.PLAYERS, 1f, 1f);
                        player.getItemCooldownManager().set(ModItems.MITHRIL_MIRROR, getMaxUseTime(stack) * 20);
                        return stack;
                    } else if (targetWorld.getRegistryKey().equals(ModDimensionsDataGenerator.BACKROOMS_LEVEL_KEY)) {
                        player.sendMessage(Text.translatable("item.tlotd.mithril_mirror.destination_unknown", cost).formatted(Formatting.RED), true);
                        world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHULKER_BULLET_HURT, SoundCategory.PLAYERS, 1f, 1f);
                        player.getItemCooldownManager().set(ModItems.MITHRIL_MIRROR, getMaxUseTime(stack) * 20);
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
                            player.getItemCooldownManager().set(ModItems.MITHRIL_MIRROR, getMaxUseTime(stack) * 20);
                            return stack;
                        }
                    } else if (crossDim) {
                        player.sendMessage(Text.translatable("item.tlotd.mithril_mirror.transdimensional").formatted(Formatting.RED), true);
                        world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHULKER_BULLET_HURT, SoundCategory.PLAYERS, 1f, 1f);
                        player.getItemCooldownManager().set(ModItems.MITHRIL_MIRROR, getMaxUseTime(stack) * 20);
                        return stack;
                    }
                    Optional<Vec3d> optionalSpawnVec = Optional.empty();
                    if (spawnpoint != null)
                        optionalSpawnVec = PlayerEntity.findRespawnPosition(targetWorld, spawnpoint, serverPlayer.getSpawnAngle(), false, false);
                    if (spawnpoint == null || optionalSpawnVec.isEmpty()) {
                        worldspawn = true;
                    } else {
                        Vec3d spawnVec = optionalSpawnVec.get();
                        serverPlayer.teleport(targetWorld, spawnVec.getX(), spawnVec.getY(), spawnVec.getZ(), serverPlayer.getSpawnAngle(), 0.5F);
                        targetWorld.playSound(null, spawnpoint, ModSounds.ITEM_MITHRIL_MIRROR, SoundCategory.PLAYERS, 0.4f, 0.8f);
                    }
                    if (worldspawn) {
                        spawnpoint = serverPlayer.server.getOverworld().getSpawnPos();
                        serverPlayer.teleport(serverPlayer.server.getOverworld(), spawnpoint.getX(), spawnpoint.getY(), spawnpoint.getZ(), serverPlayer.getSpawnAngle(), 0.5F);
                        while (!targetWorld.isSpaceEmpty(serverPlayer)) {
                            serverPlayer.teleport(serverPlayer.getX(), serverPlayer.getY() + 1.0D, serverPlayer.getZ());
                        }
                        targetWorld.playSound(null, spawnpoint, ModSounds.ITEM_MITHRIL_MIRROR, SoundCategory.PLAYERS, 0.4f, 0.8f);
                    }
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 20, 0, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20, 0, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 20, 0, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 20, 0, false, false));
                    int protection = EnchantmentHelper.getLevel(ModEnchantments.REINFORCED_GLASS, stack);
                    if (protection > 0) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, protection * 20, 4, false, false));
                    }
                    if (EnchantmentHelper.getLevel(ModEnchantments.CURSED_REFLECTION, stack) > 0) {
                        player.setHealth(1.0F);
                        world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_WITHER_SPAWN, SoundCategory.PLAYERS, 0.8f, 0.6f);
                    }
                    int curseLevel = EnchantmentHelper.getLevel(ModEnchantments.CURSED_REFLECTION, stack);
                    int restorativeLevel = EnchantmentHelper.getLevel(ModEnchantments.REFILL_CHARGES, stack);
                    if (curseLevel == 0) {
                        used += crossDim ? cost : 1;
                        tag.putInt(CHARGES_USED_KEY, used);
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
                                    stack.getOrCreateNbt().putInt(CHARGES_USED_KEY, 0);
                                    world.playSound(null, player.getBlockPos(), (EnchantmentHelper.getLevel(ModEnchantments.CURSED_REFLECTION, stack) > 0) ? SoundEvents.ENTITY_WITHER_HURT : SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, SoundCategory.PLAYERS, 0.8f, 1.2f);
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
                boolean isCursed = EnchantmentHelper.getLevel(ModEnchantments.CURSED_REFLECTION, stack) > 0;
                Item requiredFuel = isCursed ? ModItems.CURSED_SOUL_FLASK : ModItems.SOUL_FLASK_OF_THE_ABYSS;
                for (int i = 0; i < player.getInventory().size(); i++) {
                    ItemStack invStack = player.getInventory().getStack(i);
                    if (!invStack.isEmpty() && invStack.isOf(requiredFuel)) {
                        invStack.decrement(1);
                        if (!player.getInventory().insertStack(ModItems.TINTED_GLASS_FLASK.getDefaultStack())) {
                            player.dropItem(ModItems.TINTED_GLASS_FLASK.getDefaultStack(), false);
                        }
                        stack.getOrCreateNbt().putInt(CHARGES_USED_KEY, 0);
                        world.playSound(null, player.getBlockPos(), (EnchantmentHelper.getLevel(ModEnchantments.CURSED_REFLECTION, stack) > 0) ? SoundEvents.ENTITY_WITHER_HURT : SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, SoundCategory.PLAYERS, 0.8f, 1.2f);
                        restored = true;
                        break;
                    }
                }
                if (!restored) {
                    player.sendMessage(Text.translatable("item.tlotd.mithril_mirror.foggy.charge_item_missing").formatted(Formatting.RED), true);
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHULKER_BULLET_HURT, SoundCategory.PLAYERS, 1f, 1f);
                }
                player.getItemCooldownManager().set(ModItems.MITHRIL_MIRROR, getMaxUseTime(stack) * 5);
                player.incrementStat(Stats.USED.getOrCreateStat(this));
                return stack;
            }
        }
        player.getItemCooldownManager().set(ModItems.MITHRIL_MIRROR, getMaxUseTime(stack) * 20);
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        return stack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        int speedLevel = EnchantmentHelper.getLevel(ModEnchantments.SHARPER_LENS, stack);
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
        int abyssLevel = EnchantmentHelper.getLevel(ModEnchantments.DEPTH_OF_THE_ABYSS, stack);
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
        if (EnchantmentHelper.getLevel(ModEnchantments.CURSED_REFLECTION, stack) > 0)
            return name.copy().formatted(Formatting.RED);
        if (!stack.hasNbt()) return name;
        if (stack.getOrCreateNbt().getInt(CHARGES_USED_KEY) < getMaxCharges(stack)) return name;
        else return Text.translatable("item.tlotd.mithril_mirror.foggy");
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, java.util.List<Text> tooltip, TooltipContext context) {
        Style style = getName().getStyle();
        NbtCompound tag = stack.getOrCreateNbt();
        int used = tag.getInt(CHARGES_USED_KEY);
        int max = getMaxCharges(stack);
        int remaining = Math.max(0, max - used);
        boolean cursed = EnchantmentHelper.getLevel(ModEnchantments.CURSED_REFLECTION, stack) > 0;
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

    private boolean tryRefill(ItemStack mirror, ItemStack inputStack, PlayerEntity player) {
        if (!mirror.hasNbt()) return false;
        if (mirror.getOrCreateNbt().getInt(CHARGES_USED_KEY) < getMaxCharges(mirror)) return false;
        boolean cursed = EnchantmentHelper.getLevel(ModEnchantments.CURSED_REFLECTION, mirror) > 0;
        if (!inputStack.isOf(cursed ? ModItems.CURSED_SOUL_FLASK : ModItems.SOUL_FLASK_OF_THE_ABYSS)) return false;
        inputStack.decrement(1);
        ItemStack empty = ModItems.TINTED_GLASS_FLASK.getDefaultStack();
        if (!player.getInventory().insertStack(empty)) {
            player.dropItem(empty, false);
        }
        mirror.getOrCreateNbt().putInt(CHARGES_USED_KEY, 0);
        player.playSound(cursed ? SoundEvents.ENTITY_WITHER_HURT : SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, 1f, 1f);
        player.getItemCooldownManager().set(ModItems.MITHRIL_MIRROR, getMaxUseTime(mirror) * 20);
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