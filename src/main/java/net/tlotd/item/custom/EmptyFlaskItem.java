package net.tlotd.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;

import static net.tlotd.block.custom.WitchingTableBlock.*;

public class EmptyFlaskItem extends Item {
    public EmptyFlaskItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        BlockState block = context.getWorld().getBlockState(context.getBlockPos());
        if (!context.getWorld().isClient) {
            if (block.isIn(BlockTags.SOUL_FIRE_BASE_BLOCKS)) {
                context.getStack().decrement(1);
                if (player.getInventory().getEmptySlot() == -1) {
                    Block.dropStack(context.getWorld(), context.getBlockPos(), ModItems.SOUL_FLASK.getDefaultStack());
                } else player.giveItemStack(ModItems.SOUL_FLASK.getDefaultStack());
                context.getWorld().breakBlock(context.getBlockPos(),false);
                player.incrementStat(Stats.USED.getOrCreateStat(this));
                context.getWorld().playSound(null, BlockPos.ofFloored(player.getPos()), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                return ActionResult.SUCCESS;
            } else if (block.isOf(ModBlocks.WITCHING_TABLE) && (block.get(SOUL_CHARGES)+block.get(CURSED_SOUL_CHARGES)+block.get(ABYSSAL_SOUL_CHARGES))>0) {
                context.getStack().decrement(1);
                if (block.get(ABYSSAL_SOUL_CHARGES)>0) {
                    context.getWorld().setBlockState(context.getBlockPos(), block.with(ABYSSAL_SOUL_CHARGES, block.get(ABYSSAL_SOUL_CHARGES)-1), 2);
                    if (player.getInventory().getEmptySlot() == -1) {
                        Block.dropStack(context.getWorld(), context.getBlockPos().up(), ModItems.SOUL_FLASK_OF_THE_ABYSS.getDefaultStack());
                    } else player.giveItemStack(ModItems.SOUL_FLASK_OF_THE_ABYSS.getDefaultStack());
                }
                else if (block.get(CURSED_SOUL_CHARGES)>0) {
                    context.getWorld().setBlockState(context.getBlockPos(), block.with(CURSED_SOUL_CHARGES, block.get(CURSED_SOUL_CHARGES)-1), 2);
                    if (player.getInventory().getEmptySlot() == -1) {
                        Block.dropStack(context.getWorld(), context.getBlockPos().up(), ModItems.CURSED_SOUL_FLASK.getDefaultStack());
                    } else player.giveItemStack(ModItems.CURSED_SOUL_FLASK.getDefaultStack());
                }
                else {
                    context.getWorld().setBlockState(context.getBlockPos(), block.with(SOUL_CHARGES, block.get(SOUL_CHARGES)-1), 2);
                    if (player.getInventory().getEmptySlot() == -1) {
                        Block.dropStack(context.getWorld(), context.getBlockPos().up(), ModItems.SOUL_FLASK.getDefaultStack());
                    } else player.giveItemStack(ModItems.SOUL_FLASK.getDefaultStack());
                }
                player.incrementStat(Stats.USED.getOrCreateStat(this));
                context.getWorld().playSound(null, BlockPos.ofFloored(player.getPos()), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
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
