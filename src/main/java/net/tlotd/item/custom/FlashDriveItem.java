package net.tlotd.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class FlashDriveItem extends Item {
    public FlashDriveItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.hasNbt();
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (Objects.requireNonNull(context.getPlayer()).isSneaking() && context.getWorld().getBlockState(context.getBlockPos()).isOf(ModBlocks.TELEPORTER)) {
            NbtCompound nbt = new NbtCompound();
            nbt.putInt("destination_x", context.getBlockPos().getX());
            nbt.putInt("destination_y", context.getBlockPos().getY());
            nbt.putInt("destination_z", context.getBlockPos().getZ());
            context.getStack().setNbt(nbt);
            context.getPlayer().sendMessage(Text.translatable("block.tlotd.teleporter.location_copied"), true);
            context.getWorld().playSound(null, context.getBlockPos(), ModSounds.BLOCK_KEYCARD_READER_PLING, SoundCategory.BLOCKS, 1.0f, 1.0f);
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (!stack.hasNbt()) {
            tooltip.add(Text.translatable("item.tlotd.flash_drive.empty").formatted(Formatting.GRAY));
        } else {
            tooltip.add(Text.translatable("item.tlotd.flash_drive.one_entry").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.flash_drive.coordinates", stack.getNbt().getInt("destination_x"),stack.getNbt().getInt("destination_y"),stack.getNbt().getInt("destination_z")).formatted(Formatting.GRAY));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
