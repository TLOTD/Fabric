package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.util.ModTags;

public class RichDirtBlock extends Block {
    public RichDirtBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getStackInHand(hand).isIn(ItemTags.HOES) || player.getStackInHand(hand).isIn(ModTags.Items.PAXELS)) {
            if (!world.isClient()) {
                world.setBlockState(pos, ModBlocks.RICH_FARMLAND.getStateWithProperties(state));
                if (player.getStackInHand(hand).isIn(ItemTags.HOES) || player.getStackInHand(hand).isIn(ModTags.Items.PAXELS)) {
                    player.getMainHandStack().damage(1, player, e -> e.sendEquipmentBreakStatus((player.getMainHandStack().isIn(ItemTags.HOES) || player.getMainHandStack().isIn(ModTags.Items.PAXELS)) ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND));
                }
                world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            return ActionResult.SUCCESS;
        }
        if (player.getStackInHand(hand).isIn(ItemTags.SHOVELS)) {
            if (!world.isClient()) {
                world.setBlockState(pos, ModBlocks.RICH_DIRT_PATH.getStateWithProperties(state));
                if (player.getStackInHand(hand).isIn(ItemTags.SHOVELS)) {
                    player.getMainHandStack().damage(1, player, e -> e.sendEquipmentBreakStatus(player.getMainHandStack().isIn(ItemTags.SHOVELS) ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND));
                }
                world.playSound(null, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.PLAYERS, 1.0f, 1.0f);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }
}
