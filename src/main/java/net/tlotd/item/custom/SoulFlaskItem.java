package net.tlotd.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;

import static net.tlotd.block.custom.WitchingTableBlock.CHARGES;

public class SoulFlaskItem extends Item {
    public SoulFlaskItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()) {
            ItemStack item = context.getPlayer().getStackInHand(context.getHand());
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            BlockState block = context.getWorld().getBlockState(positionClicked);
            if (block.isOf(ModBlocks.WITCHING_TABLE) && block.get(CHARGES)<3) {
                int charges = block.get(CHARGES);
                context.getWorld().setBlockState(positionClicked, block.with(CHARGES, charges+1), 2);
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
}
