package net.tlotd.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.tlotd.block.custom.WitchingTableBlock.*;
import static net.tlotd.block.custom.WitchingTableBlock.CURSED_SOUL_CHARGES;

public class AbyssFlaskItem extends Item {
    public AbyssFlaskItem(Settings settings) { super(settings); }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()) {
            ItemStack item = context.getPlayer().getStackInHand(context.getHand());
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            BlockState block = context.getWorld().getBlockState(positionClicked);
            if (block.isOf(ModBlocks.WITCHING_TABLE) && (block.get(SOUL_CHARGES)+block.get(CURSED_SOUL_CHARGES)+block.get(ABYSSAL_SOUL_CHARGES))<3) {
                int charges = block.get(ABYSSAL_SOUL_CHARGES);
                context.getWorld().setBlockState(positionClicked, block.with(ABYSSAL_SOUL_CHARGES, charges+1), 2);
                context.getWorld().playSound(null, positionClicked, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                    Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, context.getStack());
                    serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
                item.decrement(1);
                context.getPlayer().giveItemStack(ModItems.TINTED_GLASS_FLASK.getDefaultStack());
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier ILLAGER_FONT_ID = new Identifier("minecraft", "illageralt");

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().styled(style -> style.withColor(0x3C009C));
    }
    
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        Style style = getName().getStyle();
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.tlotd.soul_flask_of_the_abyss.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.soul_flask_of_the_abyss.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.soul_flask_of_the_abyss.tooltip_3").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.soul_flask_of_the_abyss.tooltip_4").formatted(Formatting.GRAY));
        } else {
            tooltip.add(Text.literal("Und wenn du lange in").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.literal("einen Abgrund blickst").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY).append(Text.literal(",").setStyle(style.withFont(DEFAULT_FONT_ID)).formatted(Formatting.GRAY)));
            tooltip.add(Text.literal("blickt der Abgrund").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.literal("auch in dich hinein").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY).append(Text.literal(",").setStyle(style.withFont(DEFAULT_FONT_ID)).formatted(Formatting.GRAY)));
        }
        tooltip.add(Text.translatable("item.tlotd.desc_eldritch").setStyle(style.withColor(0x3C009C)));
        super.appendTooltip(stack, world, tooltip, context);
    }
}