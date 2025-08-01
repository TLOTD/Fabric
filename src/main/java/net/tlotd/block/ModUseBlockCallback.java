package net.tlotd.block;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;

import static net.tlotd.world.dimension.ModDimensions.LUNA_LEVEL_KEY;

public class ModUseBlockCallback {
    public static void interceptBlocks() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            ItemStack item = player.getStackInHand(hand);
            if (player.getWorld().getRegistryKey().equals(LUNA_LEVEL_KEY)) {
                if (item.getItem() == Items.FLINT_AND_STEEL || item.getItem() == Items.FIRE_CHARGE) {
                    return ActionResult.FAIL; // Cancel the action
                } else if (item.getItem() == Items.CAMPFIRE || item.getItem() == Items.SOUL_CAMPFIRE || item.getItem() == ModBlocks.SULFUR_CAMPFIRE.asItem()) {
                    BlockState state = ((BlockItem) item.getItem()).getBlock().getDefaultState()
                            .with(CampfireBlock.LIT, false)
                            .with(CampfireBlock.FACING, player.getHorizontalFacing().getOpposite());
                    BlockPos pos = hitResult.getBlockPos().offset(hitResult.getSide());
                    world.setBlockState(pos, state);
                    world.playSound(null, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1f, 1f);
                    if (!player.getAbilities().creativeMode) {
                        item.decrement(1);
                    }
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });
    }
}
