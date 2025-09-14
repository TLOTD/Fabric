package net.tlotd.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class PlanchetteItem extends Item {
    public PlanchetteItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient() && context.getWorld().getBlockState(context.getBlockPos()).getBlock().equals(ModBlocks.OUIJA_BOARD)) {
            int randomId = ThreadLocalRandom.current().nextInt(0, 13);
            Objects.requireNonNull(context.getPlayer()).sendMessage(Text.translatable("generic.tlotd.ouija_message_"+randomId, context.getPlayer().getName()), true);
            context.getPlayer().getItemCooldownManager().set(this, 10);
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.tlotd.desc_occult").formatted(Formatting.RED));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
