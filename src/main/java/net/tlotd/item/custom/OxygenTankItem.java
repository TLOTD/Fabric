package net.tlotd.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tlotd.util.AdAstraOxygenNbtHelper;
import net.tlotd.util.ModTags;

import static net.tlotd.util.AdAstraOxygenNbtHelper.getMaxOxygenItem;
import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;

public class OxygenTankItem extends Item {
    public OxygenTankItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack base = new ItemStack(this);
        AdAstraOxygenNbtHelper.setOxygen(base, 0);
        return base;
    }

    public float getProgress(ItemStack stack) {
        return getMaxOxygenItem(stack) - AdAstraOxygenNbtHelper.getOxygen(stack);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0f - getProgress(stack) * 13.0f / getMaxOxygenItem(stack));
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0xDAE6F0;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient()) {
            ItemStack chest = user.getInventory().getArmorStack(2);
            if (chest.isIn(ModTags.Items.OXYGEN_STORING) || (getAugmentLevel(chest, "tlotd:oxygen_tank") > 0)) {
                long tankOxygen = AdAstraOxygenNbtHelper.getOxygen(itemStack);
                long chestOxygen = AdAstraOxygenNbtHelper.getOxygen(chest);
                long maxChestOxygen = AdAstraOxygenNbtHelper.getMaxOxygenItem(chest);
                if (tankOxygen > 0 && chestOxygen < maxChestOxygen) {
                    long transfer = Math.min(tankOxygen, maxChestOxygen - chestOxygen);
                    AdAstraOxygenNbtHelper.setOxygen(chest, chestOxygen + transfer);
                    AdAstraOxygenNbtHelper.setOxygen(itemStack, tankOxygen - transfer);
                    world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_PLAYER_BREATH, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    return TypedActionResult.success(itemStack);
                }
            }
        }
        return TypedActionResult.pass(itemStack);
    }
}