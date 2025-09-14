package net.tlotd.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tlotd.sound.ModSounds;
import net.tlotd.util.AdAstraOxygenNbtHelper;
import net.tlotd.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String oxygen = "0 \uD83E\uDEA3 / 1K \uD83E\uDEA3";
        if (Screen.hasShiftDown()) {
            oxygen = "0 \uD83E\uDEA3 / 1,000 \uD83E\uDEA3";
            if (stack.hasNbt()) {
                long oxygenAmount = AdAstraOxygenNbtHelper.getOxygen(stack);
                int displayAmount = (int) Math.round((double) oxygenAmount * 1000 / AdAstraOxygenNbtHelper.MAX_AMOUNT);
                String formattedOxygen = String.format("%,d", displayAmount);
                oxygen = formattedOxygen + " \uD83E\uDEA3 / 1,000 \uD83E\uDEA3";
            }
        } else {
            if (stack.hasNbt()) {
                long oxygenAmount = AdAstraOxygenNbtHelper.getOxygen(stack);
                int displayAmount = (int) Math.round((double) oxygenAmount * 1000 / AdAstraOxygenNbtHelper.MAX_AMOUNT);
                String formattedOxygen;
                if (displayAmount == 1000) {
                    formattedOxygen = "1K";
                } else {
                    formattedOxygen = String.valueOf(displayAmount);
                }
                oxygen = formattedOxygen + " \uD83E\uDEA3 / 1K \uD83E\uDEA3";
            }
        }
        tooltip.add(Text.translatable("item.tlotd.oxygen_level.tooltip", oxygen).formatted(Formatting.GOLD));
        super.appendTooltip(stack, world, tooltip, context);
    }

    public float getProgress(ItemStack stack) {
        return AdAstraOxygenNbtHelper.MAX_AMOUNT - AdAstraOxygenNbtHelper.getOxygen(stack);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0f - getProgress(stack) * 13.0f / AdAstraOxygenNbtHelper.MAX_AMOUNT);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0x9fc5e8;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient()) {
            ItemStack chest = user.getInventory().getArmorStack(2);
            if (chest.isIn(ModTags.Items.OXYGEN_CHARGABLE) && AdAstraOxygenNbtHelper.getOxygen(itemStack) > 0 && AdAstraOxygenNbtHelper.getOxygen(chest) < AdAstraOxygenNbtHelper.MAX_AMOUNT) {
                long chestOxygen = AdAstraOxygenNbtHelper.getOxygen(chest);
                long itemOxygen = AdAstraOxygenNbtHelper.getOxygen(itemStack);
                long transfer = Math.min(itemOxygen, AdAstraOxygenNbtHelper.MAX_AMOUNT - chestOxygen);
                AdAstraOxygenNbtHelper.setOxygen(chest, chestOxygen + transfer);
                AdAstraOxygenNbtHelper.setOxygen(itemStack, itemOxygen - transfer);
                world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_PLAYER_BREATH, SoundCategory.PLAYERS, 1.0f, 1.0f);
                return TypedActionResult.success(itemStack);
            }
        }
        return TypedActionResult.pass(itemStack);
    }
}