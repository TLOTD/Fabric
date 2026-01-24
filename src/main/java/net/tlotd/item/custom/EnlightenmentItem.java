package net.tlotd.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tlotd.item.ModItems;
import net.tlotd.networking.PlayerDataSyncNetworking;
import net.tlotd.networking.PlayerDataSyncPacket;
import net.tlotd.util.EntityDataSaver;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnlightenmentItem extends Item {
    public EnlightenmentItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public Text getName(ItemStack stack) {
        Text name = super.getName(stack);
        if (stack.isOf(ModItems.TOME_OF_ENLIGHTENMENT)) return name.copy().styled(style -> style.withColor(Formatting.GOLD));
        if (stack.isOf(ModItems.ARTIFACT_OF_ENLIGHTENMENT)) return name.copy().styled(style -> style.withColor(Formatting.RED));
        if (stack.isOf(ModItems.EYE_OF_ENLIGHTENMENT)) return name.copy().styled(style -> style.withColor(0x3C009C));
        return name;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            int new_enlightened = 10;
            if (user.getStackInHand(hand).isOf(ModItems.ARTIFACT_OF_ENLIGHTENMENT)) {
                new_enlightened = 20;
            } else if (user.getStackInHand(hand).isOf(ModItems.EYE_OF_ENLIGHTENMENT)) {
                new_enlightened = 30;
            }
            EntityDataSaver data = (EntityDataSaver) user;
            NbtCompound nbt = data.getPersistentData();
            int enlightened = nbt.getInt("Enlightened");
            if (new_enlightened > enlightened) {
                nbt.putInt("Enlightened", new_enlightened);
                PlayerDataSyncNetworking.sendToClient(
                        (ServerPlayerEntity) user,
                        new PlayerDataSyncPacket(nbt)
                );
                world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1f, 1f);
                user.getStackInHand(hand).decrement(1);
            } else {
                world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_SHULKER_BULLET_HURT, SoundCategory.PLAYERS, 1f, 1f);
            }
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        int enlightened = 10;
        if (stack.isOf(ModItems.ARTIFACT_OF_ENLIGHTENMENT)) {
            enlightened = 20;
        } else if (stack.isOf(ModItems.EYE_OF_ENLIGHTENMENT)) {
            enlightened = 30;
        }
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("item.tlotd.enlightenment.tooltip").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.tlotd.enlightenment.tooltip_2", enlightened).formatted(Formatting.BLUE));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
