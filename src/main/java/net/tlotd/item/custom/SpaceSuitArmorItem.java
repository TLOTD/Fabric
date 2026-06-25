package net.tlotd.item.custom;

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
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.tlotd.util.AdAstraGasNbtHelper;
import net.tlotd.util.ModTags;
import net.tlotd.util.SpaceSuitTooltipData;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class SpaceSuitArmorItem extends ArmorItem {
    public SpaceSuitArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() && entity instanceof PlayerEntity player) {
            if (stack == player.getInventory().getArmorStack(2)) {
                Map<String, AdAstraGasNbtHelper.GasInfo> gases = AdAstraGasNbtHelper.getSuitGasContents(stack);
                MutableText display = Text.empty();
                if (gases.isEmpty()) {
                    display.append(Text.translatable("item.tlotd.gas_cylinder.tooltip", "0", "0", "0", "0", AdAstraGasNbtHelper.gasName("empty")));
                } else {
                    boolean first = true;
                    for (Map.Entry<String, AdAstraGasNbtHelper.GasInfo> entry : gases.entrySet()) {
                        String gas = entry.getKey();
                        AdAstraGasNbtHelper.GasInfo info = entry.getValue();
                        String formattedGas;
                        String formattedMaxGas;
                        if (entity.isSneaking()) {
                            int displayAmount = (int) Math.round((double) info.amount * 1000 / AdAstraGasNbtHelper.MAX_AMOUNT);
                            int displayMax = (int) Math.round((double) info.max * 1000 / AdAstraGasNbtHelper.MAX_AMOUNT);
                            formattedGas = String.format("%,d", displayAmount);
                            formattedMaxGas = String.format("%,d", displayMax);
                        } else {
                            formattedGas = AdAstraGasNbtHelper.getOxygenString(info.amount);
                            formattedMaxGas = AdAstraGasNbtHelper.getOxygenString(info.max);
                        }
                        String formattedGas2 = formattedGas.replace(',', '.');
                        String formattedMaxGas2 = formattedMaxGas.replace(',', '.');
                        if (!first) {
                            display.append(Text.literal(" | "));
                        }
                        display.append(Text.translatable("item.tlotd.gas_cylinder.tooltip", formattedGas, formattedMaxGas, formattedGas2, formattedMaxGas2, AdAstraGasNbtHelper.gasName(gas)));
                        first = false;
                    }
                }
                player.sendMessage(display.formatted(Formatting.GOLD), true);
            }
        }
    }

    public float getProgress(ItemStack stack) {
        return Math.max(AdAstraGasNbtHelper.getMaxGasFromSuit(stack) - AdAstraGasNbtHelper.getGasAmountFromSuit(stack, AdAstraGasNbtHelper.AD_ASTRA_OXYGEN_ID), 0);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0f - getProgress(stack) * 13.0f / AdAstraGasNbtHelper.getMaxGasFromSuit(stack));
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
        return handleTankInteraction(stack, otherStack, cursorStackReference::set, player);
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType != ClickType.RIGHT) return false;
        return handleTankInteraction(stack, slot.getStack(), slot::setStack, player);
    }

    private boolean handleTankInteraction(ItemStack suit, ItemStack inputStack, Consumer<ItemStack> giveItem, PlayerEntity player) {
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
        if (!isValidTank(inputStack)) return false;
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

    private boolean isValidTank(ItemStack stack) {
        return stack.isIn(ModTags.Items.OXYGEN_STORING);
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {
        return Optional.of(new SpaceSuitTooltipData(getStoredStacks(stack)));
    }
}