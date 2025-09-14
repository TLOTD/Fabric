package net.tlotd.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
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
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OminousAlienKeyItem extends Item {
    public OminousAlienKeyItem(Settings settings) {
        super(settings);
    }

    public static final Identifier SGA_FONT_ID = new Identifier("minecraft", "alt");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("info.tlotd.not_yet_implemented").formatted(Formatting.RED));
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.tlotd.ominous_alien_key.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.ominous_alien_key.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.ominous_alien_key.tooltip_3").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.ominous_alien_key.tooltip_4").formatted(Formatting.GRAY));
        } else {
            Style style = getName().getStyle();
            tooltip.add(Text.translatable("item.tlotd.ominous_alien_key.tooltip").setStyle(style.withFont(SGA_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.ominous_alien_key.tooltip_2").setStyle(style.withFont(SGA_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.ominous_alien_key.tooltip_3").setStyle(style.withFont(SGA_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("item.tlotd.ominous_alien_key.tooltip_4").setStyle(style.withFont(SGA_FONT_ID)).formatted(Formatting.GRAY));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();
        if (!context.getWorld().isClient() && world.getBlockState(pos).isOf(ModBlocks.ALIEN_GATE)) {
            world.setBlockState(pos.add(-1,0,-1), Blocks.AIR.getDefaultState());
            world.setBlockState(pos.add(-1,0,0), Blocks.AIR.getDefaultState());
            world.setBlockState(pos.add(-1,0,1), Blocks.AIR.getDefaultState());
            world.setBlockState(pos.add(0,0,-1), Blocks.AIR.getDefaultState());
            world.setBlockState(pos.add(0,0,0), Blocks.AIR.getDefaultState());
            world.setBlockState(pos.add(0,0,1), Blocks.AIR.getDefaultState());
            world.setBlockState(pos.add(1,0,-1), Blocks.AIR.getDefaultState());
            world.setBlockState(pos.add(1,0,0), Blocks.AIR.getDefaultState());
            world.setBlockState(pos.add(1,0,1), Blocks.AIR.getDefaultState());
            world.addBlockBreakParticles(pos, ModBlocks.ALIEN_PILLAR.getDefaultState());
            world.playSound(null, pos, SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.BLOCKS);
            if (context.getPlayer() instanceof ServerPlayerEntity serverPlayerEntity) {
                Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, context.getStack());
                serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            }
            context.getStack().decrement(1);
            return ActionResult.SUCCESS;
        } else return ActionResult.FAIL;
    }
}
