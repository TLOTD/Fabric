package net.tlotd.item.custom;

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
import net.tlotd.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OxygenTankItem extends Item {
    public OxygenTankItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        String oxygen = "0.0%";
        Float oxygenPercentage;
        if(stack.hasNbt()) {
            oxygenPercentage = stack.getNbt().getInt("tlotd:oxygen")/10.0F;
            oxygen = String.join("",String.valueOf(oxygenPercentage), "%");
        }
        tooltip.add(Text.translatable("item.tlotd.oxygen_level.tooltip", oxygen).formatted(Formatting.GRAY));
    }

    public float getProgress(ItemStack stack) {
        if (!stack.hasNbt()) {
            return 1000f;
        } else {
            return 1000f - stack.getNbt().getInt("tlotd:oxygen");
        }
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0f - getProgress(stack) * 13.0f / 1000f);
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
            if (itemStack.hasNbt() && itemStack.getNbt().getInt("tlotd:oxygen") > 0 && chest.isIn(ModTags.Items.OXYGEN_CHARGABLE)) {
                int chestOxygen = 0;
                if (chest.getNbt() != null) {
                    chestOxygen = chest.getNbt().getInt("tlotd:oxygen");
                }
                int itemOxygen = itemStack.getNbt().getInt("tlotd:oxygen");
                if (chestOxygen + itemOxygen <= 1000) {
                    chestOxygen = chestOxygen + itemOxygen;
                    itemOxygen = 0;
                } else {
                    itemOxygen = itemOxygen - (1000 - chestOxygen);
                    chestOxygen = 1000;
                }
                NbtCompound itemNbtData = new NbtCompound();
                itemNbtData.putInt("tlotd:oxygen", itemOxygen);
                itemStack.setNbt(itemNbtData);
                NbtCompound chestNbtData = new NbtCompound();
                chestNbtData.putInt("tlotd:oxygen", chestOxygen);
                chest.setNbt(chestNbtData);
                world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_PLAYER_BREATH, SoundCategory.PLAYERS, 1.0f, 1.0f);
                return TypedActionResult.success(itemStack);
            }
        }
        return TypedActionResult.pass(itemStack);
    }
}