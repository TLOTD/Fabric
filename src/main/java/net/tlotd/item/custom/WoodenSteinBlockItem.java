package net.tlotd.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.tlotd.block.ModBlocks;

public class WoodenSteinBlockItem extends BlockItem {

    public WoodenSteinBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.getWorld().isClient()){
            if (entity.getClass() == CowEntity.class || entity.getClass() == MooshroomEntity.class || entity.getClass() == GoatEntity.class) {
                if (user instanceof ServerPlayerEntity serverPlayerEntity) {
                    Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
                    serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
                ItemStack itemStack = new ItemStack(ModBlocks.WOODEN_MILK_STEIN);
                if (!user.getInventory().insertStack(itemStack)) {
                    user.dropItem(itemStack, false);
                }
                if (!user.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
                if (entity.getClass() == CowEntity.class) {
                    user.getWorld().playSound(null, user.getBlockPos(), SoundEvents.ENTITY_COW_MILK, SoundCategory.BLOCKS, 1.0f, 1.0f);
                } else if (entity.getClass() == MooshroomEntity.class) {
                    user.getWorld().playSound(null, user.getBlockPos(), SoundEvents.ENTITY_MOOSHROOM_MILK, SoundCategory.BLOCKS, 1.0f, 1.0f);
                } else {
                    user.getWorld().playSound(null, user.getBlockPos(), SoundEvents.ENTITY_GOAT_MILK, SoundCategory.BLOCKS, 1.0f, 1.0f);
                }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }
}