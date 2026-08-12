package net.tlotd.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
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
import net.tlotd.util.ModTags;
import net.tlotd.util.VideoGameRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static net.tlotd.block.custom.ComputerBlock.SCREEN;

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
        BlockState state = context.getWorld().getBlockState(context.getBlockPos());
        if (Objects.requireNonNull(context.getPlayer()).isSneaking() && (state.isIn(ModTags.Blocks.COMPUTERS_ON) || state.isOf(ModBlocks.TELEPORTER))) {
            NbtCompound nbt = new NbtCompound();
            if (context.getStack().getNbt() != null) {
                nbt = context.getStack().getNbt();
            }
            if (state.isOf(ModBlocks.TELEPORTER)) {
                nbt.putString("pos", "xyz");
                nbt.putInt("pos_x", context.getBlockPos().getX());
                nbt.putInt("pos_y", context.getBlockPos().getY());
                nbt.putInt("pos_z", context.getBlockPos().getZ());
                context.getStack().setNbt(nbt);
                context.getPlayer().sendMessage(Text.translatable("block.tlotd.teleporter.location_copied"), true);
            } else {
                Optional<VideoGameRegistry.SignalEntry> matched = VideoGameRegistry.getAll().stream().filter(entry -> entry.computerBlock() == state.getBlock() && entry.gameID() == state.get(SCREEN)).findFirst();
                if (matched.isEmpty()) {
                    return ActionResult.PASS;
                }
                VideoGameRegistry.SignalEntry entry = matched.get();
                nbt.putString("app", entry.signalItem().toString());
                context.getStack().setNbt(nbt);
                context.getPlayer().sendMessage(Text.translatable("block.tlotd.computer_on.application_copied"), true);
            }
            context.getWorld().playSound(null, context.getBlockPos(), ModSounds.BLOCK_KEYCARD_READER_PLING, SoundCategory.BLOCKS, 1.0f, 1.0f);
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        int data = 0;
        if (stack.getNbt() != null) {
            if (!stack.getNbt().getString("pos").isEmpty()) {
                data++;
            }
            if (!stack.getNbt().getString("app").isEmpty()) {
                data++;
            }
            if (!stack.getNbt().getString("password").isEmpty()) {
                data++;
            }
            tooltip.add(Text.literal(data + " ♣ / 3 ♣ ").append(Text.translatable("item.tlotd.flash_drive.data")).formatted(Formatting.GREEN));
            if (Screen.hasShiftDown()) {
                if (!stack.getNbt().getString("pos").isEmpty()) {
                    tooltip.add(Text.literal("└ root").formatted(Formatting.GRAY));
                    if (!stack.getNbt().getString("password").isEmpty()) {
                        tooltip.add(Text.literal("   ├ ").append(Text.translatable("item.tlotd.flash_drive.password")).formatted(Formatting.GRAY));
                        tooltip.add(Text.literal("   │  └ type: ").append(Text.translatable("item.tlotd.flash_drive.password_type")).formatted(Formatting.GRAY));
                    }
                    if (!stack.getNbt().getString("app").isEmpty()) {
                        tooltip.add(Text.literal("   ├ ").append(Text.translatable("item.tlotd.flash_drive.application", Text.translatable("item." + stack.getNbt().getString("app").replace(":", ".") + ".app"))).formatted(Formatting.GRAY));
                        tooltip.add(Text.literal("   │  └ id: " + stack.getNbt().getString("app")).formatted(Formatting.GRAY));
                    }
                    tooltip.add(Text.literal("   └ ").append(Text.translatable("item.tlotd.flash_drive.coordinates")).formatted(Formatting.GRAY));
                    tooltip.add(Text.literal("      ├ x: " + stack.getNbt().getInt("pos_x")).formatted(Formatting.GRAY));
                    tooltip.add(Text.literal("      ├ y: " + stack.getNbt().getInt("pos_y")).formatted(Formatting.GRAY));
                    tooltip.add(Text.literal("      └ z: " + stack.getNbt().getInt("pos_z")).formatted(Formatting.GRAY));
                } else if (!stack.getNbt().getString("app").isEmpty()) {
                    tooltip.add(Text.literal("└ root").formatted(Formatting.GRAY));
                    if (!stack.getNbt().getString("password").isEmpty()) {
                        tooltip.add(Text.literal("   ├ ").append(Text.translatable("item.tlotd.flash_drive.password")).formatted(Formatting.GRAY));
                        tooltip.add(Text.literal("   │  └ type: ").append(Text.translatable("item.tlotd.flash_drive.password_type")).formatted(Formatting.GRAY));
                    }
                    tooltip.add(Text.literal("   └ ").append(Text.translatable("item.tlotd.flash_drive.application", Text.translatable("item." + stack.getNbt().getString("app").replace(":", ".") + ".app"))).formatted(Formatting.GRAY));
                    tooltip.add(Text.literal("      └ id: " + stack.getNbt().getString("app")).formatted(Formatting.GRAY));
                } else if (!stack.getNbt().getString("password").isEmpty()) {
                    tooltip.add(Text.literal("└ root").formatted(Formatting.GRAY));
                    tooltip.add(Text.literal("   └ ").append(Text.translatable("item.tlotd.flash_drive.password")).formatted(Formatting.GRAY));
                    tooltip.add(Text.literal("      └ type: ").append(Text.translatable("item.tlotd.flash_drive.password_type")).formatted(Formatting.GRAY));
                }
            } else {
                if (!stack.getNbt().getString("password").isEmpty()) {
                    tooltip.add(Text.literal(" ").append(Text.translatable("item.tlotd.keycard")).formatted(Formatting.GRAY));
                }
                if (!stack.getNbt().getString("app").isEmpty()) {
                    tooltip.add(Text.literal(" ").append(Text.translatable("item." + stack.getNbt().getString("app").replace(":", ".") + ".desc")).formatted(Formatting.GRAY));
                }
                if (!stack.getNbt().getString("pos").isEmpty()) {
                    tooltip.add(Text.literal(" ").append(Text.translatable("item.tlotd.flash_drive.position")).formatted(Formatting.GRAY));
                }
            }
        } else {
            tooltip.add(Text.literal("0 ♣ / 3 ♣ ").append(Text.translatable("item.tlotd.flash_drive.data")).formatted(Formatting.GREEN));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}
