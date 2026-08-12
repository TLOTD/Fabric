package net.tlotd.item.custom;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.tlotd.util.EnergyHelper;
import net.tlotd.util.HEVSuitTooltipData;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyItem;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class HEVArmorItem extends ArmorItem {

    public HEVArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() && entity instanceof PlayerEntity player) {
            if (stack == player.getInventory().getArmorStack(2)) {
                player.sendMessage(energyText(stack, player.isSneaking()), true);
            }
        }
    }

    public float getProgress(ItemStack stack) {
        return Math.max(getEnergyCapacity(stack) - getStoredEnergy(stack), 0);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0f - getProgress(stack) * 13.0f / getEnergyCapacity(stack));
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0xFFC074;
    }

    private static final String ITEMS_KEY = "Items";
    private static final int MAX_SLOTS = 2;

    public static DefaultedList<ItemStack> getStoredStacks(ItemStack stack) {
        DefaultedList<ItemStack> list = DefaultedList.ofSize(MAX_SLOTS, ItemStack.EMPTY);
        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.contains(ITEMS_KEY)) {
            return list;
        }
        NbtList nbtList = nbt.getList(ITEMS_KEY, NbtElement.COMPOUND_TYPE);
        for (int i = 0; i < nbtList.size() && i < MAX_SLOTS; i++) {
            list.set(i, ItemStack.fromNbt(nbtList.getCompound(i)));
        }
        return list;
    }

    public static void setStoredStacks(ItemStack stack, DefaultedList<ItemStack> items) {
        NbtList list = new NbtList();
        for (ItemStack item : items) {
            if (!item.isEmpty()) {
                NbtCompound nbt = new NbtCompound();
                item.writeNbt(nbt);
                list.add(nbt);
            }
        }
        stack.getOrCreateNbt().put(ITEMS_KEY, list);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType != ClickType.RIGHT) return false;
        return handleBatteryInteraction(stack, otherStack, cursorStackReference::set, player);
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType != ClickType.RIGHT) return false;
        return handleBatteryInteraction(stack, slot.getStack(), slot::setStack, player);
    }

    private boolean handleBatteryInteraction(ItemStack suit, ItemStack inputStack, Consumer<ItemStack> giveItem, PlayerEntity player) {
        DefaultedList<ItemStack> stored = getStoredStacks(suit);
        if (inputStack.isEmpty()) {
            for (int i = 0; i < stored.size(); i++) {
                if (!stored.get(i).isEmpty()) {
                    ItemStack extracted = stored.get(i);
                    stored.set(i, ItemStack.EMPTY);
                    giveItem.accept(extracted);
                    setStoredStacks(suit, stored);
                    player.playSound(SoundEvents.ITEM_BUNDLE_REMOVE_ONE, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getWorld().getRandom().nextFloat() * 0.4F);
                    return true;
                }
            }
            return false;
        }
        if (!isValidBattery(inputStack)) return false;
        for (int i = 0; i < stored.size(); i++) {
            if (stored.get(i).isEmpty()) {
                stored.set(i, inputStack.copyWithCount(1));
                inputStack.decrement(1);
                setStoredStacks(suit, stored);
                player.playSound(SoundEvents.ITEM_BUNDLE_INSERT, SoundCategory.PLAYERS, 0.8F, 0.8F + player.getWorld().getRandom().nextFloat() * 0.4F);
                return true;
            }
        }
        return false;
    }

    public static boolean isValidBattery(ItemStack stack) {
        return stack.getItem() instanceof BatteryItem;
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        return Optional.of(new HEVSuitTooltipData(getStoredStacks(stack)));
    }

    public static long getStoredEnergy(ItemStack stack) {
        long energy = 0;
        for (ItemStack battery : getStoredStacks(stack)) {
            if (battery.getItem() instanceof SimpleEnergyItem energyItem) {
                energy += energyItem.getStoredEnergy(battery);
            }
        }
        return energy;
    }

    public static long getEnergyCapacity(ItemStack stack) {
        long capacity = 0;
        for (ItemStack battery : getStoredStacks(stack)) {
            if (battery.getItem() instanceof SimpleEnergyItem energyItem) {
                capacity += energyItem.getEnergyCapacity(battery);
            }
        }
        return capacity;
    }

    public static @Nullable EnergyStorage getEnergyStorage(ContainerItemContext context) {
        return context.find(EnergyStorage.ITEM);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(energyText(stack, Screen.hasShiftDown()));
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("item.tlotd.hev_suit_chestplate.tooltip").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(" ").append(Text.translatable("item.tlotd.hev_suit_chestplate.tooltip_2").formatted(Formatting.BLUE)));
        tooltip.add(Text.literal(" ").append(Text.translatable("item.tlotd.hev_suit_chestplate.tooltip_3").formatted(Formatting.BLUE)));
        tooltip.add(Text.literal(" ").append(Text.translatable("item.tlotd.hev_suit_chestplate.tooltip_4").formatted(Formatting.BLUE)));
    }

    private Text energyText(ItemStack stack, boolean detailed) {
        long powerAmount = getStoredEnergy(stack);
        long maxPower = getEnergyCapacity(stack);
        String formattedPower;
        String formattedMaxPower;
        if (detailed) {
            formattedPower = String.format("%,d", powerAmount);
            formattedMaxPower = String.format("%,d", maxPower);
        } else {
            formattedPower = EnergyHelper.getEnergyString((int) powerAmount);
            formattedMaxPower = EnergyHelper.getEnergyString((int) maxPower);
        }
        String formattedPower2 = formattedPower.replace(',', '.');
        String formattedMaxPower2 = formattedMaxPower.replace(',', '.');
        return Text.translatable("item.tlotd.energy_level.tooltip", formattedPower, formattedMaxPower, formattedPower2, formattedMaxPower2).formatted(Formatting.YELLOW);
    }
}