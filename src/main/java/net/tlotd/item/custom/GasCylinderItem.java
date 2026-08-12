package net.tlotd.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tlotd.util.AdAstraGasNbtHelper;
import net.tlotd.util.ModTags;

import java.util.Objects;

import static net.tlotd.util.AdAstraGasNbtHelper.getMaxGasItem;
import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;

public class GasCylinderItem extends Item {
    public GasCylinderItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack base = new ItemStack(this);
        AdAstraGasNbtHelper.setOxygen(base, 0);
        return base;
    }

    @Override
    public Text getName(ItemStack stack) {
        String gas = AdAstraGasNbtHelper.getGas(stack);
        return switch (gas) {
            case AdAstraGasNbtHelper.AD_ASTRA_OXYGEN_ID -> Text.translatable("item.tlotd.gas_cylinder.oxygen");
            case AdAstraGasNbtHelper.TLOTD_PIPE_WEED_SMOKE ->
                    Text.translatable("item.tlotd.gas_cylinder.pipe_weed_smoke");
            case AdAstraGasNbtHelper.TLOTD_WITHERED_AIR -> Text.translatable("item.tlotd.gas_cylinder.withered_air");
            default -> Text.translatable("item.tlotd.gas_cylinder.empty");
        };
    }

    public float getProgress(ItemStack stack) {
        String gas = AdAstraGasNbtHelper.getGas(stack);
        return Math.max(getMaxGasItem(stack) - AdAstraGasNbtHelper.getGasAmount(stack, gas), 0);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return !AdAstraGasNbtHelper.getGas(stack).equals("empty");
    }

    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0f - getProgress(stack) * 13.0f / getMaxGasItem(stack));
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        String gas = AdAstraGasNbtHelper.getGas(stack);
        return switch (gas) {
            case AdAstraGasNbtHelper.AD_ASTRA_OXYGEN_ID -> 0xDAE6F0;
            case AdAstraGasNbtHelper.TLOTD_PIPE_WEED_SMOKE -> 0x5b754f;
            case AdAstraGasNbtHelper.TLOTD_WITHERED_AIR -> 0x736156;
            default -> 0xFFFFFF;
        };
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient()) {
            ItemStack chest = user.getInventory().getArmorStack(2);
            if (chest.isIn(ModTags.Items.OXYGEN_STORING) || (getAugmentLevel(chest, "tlotd:oxygen_tank") > 0)) {
                long tankOxygen = AdAstraGasNbtHelper.getOxygen(itemStack);
                if (chest.getItem() instanceof SpaceSuitArmorItem) {
                    if (tankOxygen > 0) {
                        long accepted = AdAstraGasNbtHelper.modifyGasInSuit(chest, "ad_astra:oxygen", tankOxygen);
                        if (accepted > 0) {
                            AdAstraGasNbtHelper.setOxygen(itemStack, tankOxygen - accepted);
                            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_PLAYER_BREATH, SoundCategory.PLAYERS, 1.0f, 1.0f);
                            return TypedActionResult.success(itemStack);
                        }
                    }
                } else {
                    long chestOxygen = AdAstraGasNbtHelper.getOxygen(chest);
                    long maxChestOxygen = AdAstraGasNbtHelper.getMaxGasItem(chest);
                    if (tankOxygen > 0 && chestOxygen < maxChestOxygen) {
                        long transfer = Math.min(tankOxygen, maxChestOxygen - chestOxygen);
                        AdAstraGasNbtHelper.setOxygen(chest, chestOxygen + transfer);
                        AdAstraGasNbtHelper.setOxygen(itemStack, tankOxygen - transfer);
                        world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_PLAYER_BREATH, SoundCategory.PLAYERS, 1.0f, 1.0f);
                        return TypedActionResult.success(itemStack);
                    }
                }
            }
        }
        return TypedActionResult.pass(itemStack);
    }
}