package net.tlotd.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.item.ModItems;

public class EmptyFlaskItem extends Item {
    public EmptyFlaskItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient()) {
            if (user.getY() < world.getBottomY()-64) {
                world.playSound(null, BlockPos.ofFloored(user.getPos()), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                return TypedActionResult.success(this.fill(itemStack, user, ModItems.SOUL_FLASK_OF_THE_ABYSS.getDefaultStack()));
            }
        }
        return TypedActionResult.pass(itemStack);
    }

    protected ItemStack fill(ItemStack stack, PlayerEntity player, ItemStack outputStack) {
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        return ItemUsage.exchangeStack(stack, player, outputStack);
    }
}
