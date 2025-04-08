package net.tlotd.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ShearsItem;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.tlotd.item.ModItems;

import java.util.concurrent.ThreadLocalRandom;

public class SickleItem extends ShearsItem {
    public SickleItem(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            if (context.getWorld().getBlockState(positionClicked).isIn(BlockTags.LEAVES)) {
                context.getWorld().breakBlock(positionClicked, true);
                context.getStack().damage(1, player,
                        playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
                if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                    Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, context.getStack());
                    serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                    int randomItem = ThreadLocalRandom.current().nextInt(1, 5);
                    if (randomItem == 1) {
                        player.giveItemStack(ModItems.MISTLETOE.getDefaultStack());
                    }
                }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }
}