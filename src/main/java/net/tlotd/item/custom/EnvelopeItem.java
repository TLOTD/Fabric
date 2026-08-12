package net.tlotd.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.tlotd.util.EnvelopeTooltipData;
import net.tlotd.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class EnvelopeItem extends Item {
    public EnvelopeItem(Settings settings) {
        super(settings);
    }

    private static final String ITEMS_KEY = "Items";
    private static final int MAX_SLOTS = 2;

    private static final String SEALED_KEY = "Sealed";
    private static final String SEALED_BY_KEY = "SealedBy";
    private static final String OPENED_BY_KEY = "OpenedBy";

    @Override
    public Text getName(ItemStack stack) {
        Text name = super.getName(stack);
        if (isSealed(stack)) {
            return Text.translatable("item.tlotd.letter");
        } else return name;
    }

    public static DefaultedList<ItemStack> getStoredStacks(ItemStack stack) {
        DefaultedList<ItemStack> list = DefaultedList.ofSize(MAX_SLOTS, ItemStack.EMPTY);
        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.contains(ITEMS_KEY)) return list;
        NbtList nbtList = nbt.getList(ITEMS_KEY, 10);
        for (int i = 0; i < nbtList.size() && i < MAX_SLOTS; i++) {
            list.set(i, ItemStack.fromNbt(nbtList.getCompound(i)));
        }
        return list;
    }

    public static void setStoredStacks(ItemStack stack, DefaultedList<ItemStack> items) {
        NbtList list = new NbtList();
        for (ItemStack s : items) {
            if (!s.isEmpty()) {
                NbtCompound tag = new NbtCompound();
                s.writeNbt(tag);
                list.add(tag);
            }
        }
        stack.getOrCreateNbt().put(ITEMS_KEY, list);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType != ClickType.RIGHT) return false;
        return handleEnvelopeInteraction(stack, otherStack, cursorStackReference::set, player);
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType != ClickType.RIGHT) return false;
        return handleEnvelopeInteraction(stack, slot.getStack(), slot::setStack, player);
    }

    private boolean handleEnvelopeInteraction(ItemStack envelope, ItemStack inputStack, Consumer<ItemStack> giveItem, PlayerEntity player) {
        if (isSealed(envelope)) {
            if (inputStack.isOf(Items.SHEARS)) {
                unseal(envelope, player);
                player.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1F, 1F + player.getWorld().getRandom().nextFloat() * 0.4F);
                if (inputStack.getDamage() + 1 >= inputStack.getMaxDamage()) {
                    inputStack.decrement(1);
                    player.playSound(SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 1f, 1f);
                } else {
                    inputStack.setDamage(inputStack.getDamage() + 1);
                }
                return true;
            }
            return false;
        }
        if (!isSealed(envelope) && inputStack.isOf(Items.HONEYCOMB)) {
            seal(envelope, player);
            player.playSound(SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.PLAYERS, 1F, 1F + player.getWorld().getRandom().nextFloat() * 0.4F);
            inputStack.decrement(1);
            return true;
        }
        DefaultedList<ItemStack> stored = getStoredStacks(envelope);
        if (inputStack.isEmpty()) {
            for (int i = 0; i < stored.size(); i++) {
                if (!stored.get(i).isEmpty()) {
                    ItemStack extracted = stored.get(i);
                    stored.set(i, ItemStack.EMPTY);
                    giveItem.accept(extracted);
                    setStoredStacks(envelope, stored);
                    player.playSound(SoundEvents.ITEM_BUNDLE_REMOVE_ONE, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getWorld().getRandom().nextFloat() * 0.4F);
                    return true;
                }
            }
            return false;
        }
        if (isInvalidItem(inputStack)) return false;
        for (int i = 0; i < stored.size(); i++) {
            if (stored.get(i).isEmpty()) {
                stored.set(i, inputStack.copy());
                inputStack.setCount(0);
                setStoredStacks(envelope, stored);
                player.playSound(SoundEvents.ITEM_BUNDLE_INSERT, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getWorld().getRandom().nextFloat() * 0.4F);
                return true;
            }
        }
        return false;
    }

    private boolean isInvalidItem(ItemStack stack) {
        return stack.isIn(ModTags.Items.ENVELOPE_BLACKLIST);
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        return Optional.of(new EnvelopeTooltipData(getStoredStacks(stack)));
    }

    public static boolean isSealed(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        return nbt != null && nbt.getBoolean(SEALED_KEY);
    }

    public static void seal(ItemStack stack, PlayerEntity player) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putBoolean(SEALED_KEY, true);
        if (player.getEquippedStack(EquipmentSlot.HEAD).isIn(ModTags.Items.HIDES_IDENTITY)) {
            nbt.putString(SEALED_BY_KEY, "UNKNOWN.PLAYER");
        } else {
            nbt.putString(SEALED_BY_KEY, player.getGameProfile().getName());
        }
        nbt.remove(OPENED_BY_KEY);
    }

    public static void unseal(ItemStack stack, PlayerEntity player) {
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putBoolean(SEALED_KEY, false);
        if (player.getEquippedStack(EquipmentSlot.HEAD).isIn(ModTags.Items.HIDES_IDENTITY)) {
            nbt.putString(OPENED_BY_KEY, "UNKNOWN.PLAYER");
        } else {
            nbt.putString(OPENED_BY_KEY, player.getGameProfile().getName());
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null) {
            return;
        }
        if (nbt.contains(SEALED_BY_KEY)) {
            if (nbt.getString(SEALED_BY_KEY).equals("UNKNOWN.PLAYER")) {
                tooltip.add(Text.translatable("item.tlotd.letter.author_unknown").formatted(Formatting.GRAY));
            } else {
                tooltip.add(Text.translatable("item.tlotd.letter.author", nbt.getString(SEALED_BY_KEY)).formatted(Formatting.GRAY));
            }
        }
        if (nbt.contains(OPENED_BY_KEY)) {
            if (nbt.getString(OPENED_BY_KEY).equals("UNKNOWN.PLAYER")) {
                tooltip.add(Text.translatable("item.tlotd.letter.opener_unknown").formatted(Formatting.GRAY));
            } else {
                tooltip.add(Text.translatable("item.tlotd.letter.opener", nbt.getString(OPENED_BY_KEY)).formatted(Formatting.GRAY));
            }
        }
        if (isSealed(stack)) {
            tooltip.add(Text.translatable("item.tlotd.letter.sealed").formatted(Formatting.GRAY));
        }
    }
}