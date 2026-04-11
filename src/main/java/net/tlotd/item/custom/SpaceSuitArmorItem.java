package net.tlotd.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.tlotd.item.ModItems;
import net.tlotd.util.AdAstraOxygenNbtHelper;
import net.tlotd.util.SpaceSuitTooltipData;

import java.util.Optional;

public class SpaceSuitArmorItem extends ArmorItem {
    public SpaceSuitArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() && entity.isPlayer()) {
            if (stack == ((PlayerEntity) entity).getInventory().getArmorStack(2)) {
                long maxOxygenRaw = AdAstraOxygenNbtHelper.getMaxOxygenFromSuit(stack);
                long oxygenRaw = AdAstraOxygenNbtHelper.getOxygenFromSuit(stack);
                long maxOxygen = maxOxygenRaw / AdAstraOxygenNbtHelper.MAX_AMOUNT;
                String formattedOxygen;
                String formattedMaxOxygen;
                if (entity.isSneaking()) {
                    int displayAmount = (int) Math.round((double) oxygenRaw * 1000 / AdAstraOxygenNbtHelper.MAX_AMOUNT);
                    formattedOxygen = String.format("%,d", displayAmount);
                    formattedMaxOxygen = maxOxygen + ",000";
                } else {
                    formattedOxygen = AdAstraOxygenNbtHelper.getOxygenString(oxygenRaw);
                    formattedMaxOxygen = maxOxygen + "K";
                }
                if (maxOxygen == 0) {
                    formattedMaxOxygen = maxOxygen + "";
                }
                String formattedOxygen2 = formattedOxygen.replace(',', '.');
                String formattedMaxOxygen2 = formattedMaxOxygen.replace(',', '.');
                ((PlayerEntity) entity).sendMessage(Text.translatable("item.tlotd.oxygen_level.tooltip", formattedOxygen, formattedMaxOxygen, formattedOxygen2, formattedMaxOxygen2).formatted(Formatting.GOLD), true);
            }
        }
    }

    public float getProgress(ItemStack stack) {
        return Math.max(AdAstraOxygenNbtHelper.getMaxOxygenFromSuit(stack) - AdAstraOxygenNbtHelper.getOxygenFromSuit(stack), 0);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0f - getProgress(stack) * 13.0f / AdAstraOxygenNbtHelper.getMaxOxygenFromSuit(stack));
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0xDAE6F0;
    }

    private static final String ITEMS_KEY = "Items";
    private static final int MAX_SLOTS = 2;

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
        DefaultedList<ItemStack> stored = getStoredStacks(stack);
        if (otherStack.isEmpty()) {
            for (int i = 0; i < stored.size(); i++) {
                if (!stored.get(i).isEmpty()) {
                    cursorStackReference.set(stored.get(i));
                    stored.set(i, ItemStack.EMPTY);
                    setStoredStacks(stack, stored);
                    return true;
                }
            }
            return false;
        }
        if (!isValidTank(otherStack)) return false;
        for (int i = 0; i < stored.size(); i++) {
            if (stored.get(i).isEmpty()) {
                stored.set(i, otherStack.copyWithCount(1));
                otherStack.decrement(1);
                setStoredStacks(stack, stored);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType != ClickType.RIGHT) return false;
        ItemStack slotStack = slot.getStack();
        DefaultedList<ItemStack> stored = getStoredStacks(stack);
        if (slotStack.isEmpty()) {
            for (int i = 0; i < stored.size(); i++) {
                if (!stored.get(i).isEmpty()) {
                    ItemStack extracted = stored.get(i);
                    stored.set(i, ItemStack.EMPTY);
                    slot.setStack(extracted);
                    setStoredStacks(stack, stored);
                    return true;
                }
            }
            return false;
        }
        if (!isValidTank(slotStack)) return false;
        for (int i = 0; i < stored.size(); i++) {
            if (stored.get(i).isEmpty()) {
                stored.set(i, slotStack.copyWithCount(1));
                slotStack.decrement(1);
                setStoredStacks(stack, stored);
                return true;
            }
        }
        return false;
    }

    private boolean isValidTank(ItemStack stack) {
        return stack.isOf(ModItems.OXYGEN_TANK);
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        return Optional.of(new SpaceSuitTooltipData(getStoredStacks(stack)));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        DefaultedList<ItemStack> stored = getStoredStacks(stack);
        for (ItemStack s : stored) {
            if (!s.isEmpty()) {
                user.dropItem(s, true);
            }
        }
        setStoredStacks(stack, DefaultedList.ofSize(MAX_SLOTS, ItemStack.EMPTY));
        return TypedActionResult.success(stack, world.isClient());
    }
}