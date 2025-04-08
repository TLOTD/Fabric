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

public class RichDirtBlock extends Block {
    public RichDirtBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            if (player.getMainHandStack().isIn(ItemTags.HOES) || player.getOffHandStack().isIn(ItemTags.HOES)) {
                world.setBlockState(pos, ModBlocks.RICH_FARMLAND.getStateWithProperties(state));
                if (player.getMainHandStack().isIn(ItemTags.HOES)) {
                    player.getMainHandStack().damage(1,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                } else if (player.getOffHandStack().isIn(ItemTags.HOES)) {
                    player.getMainHandStack().damage(1,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND));
                }
                world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                return ActionResult.SUCCESS;
            }
            if (player.getMainHandStack().isIn(ItemTags.SHOVELS) || player.getOffHandStack().isIn(ItemTags.SHOVELS)) {
                world.setBlockState(pos, ModBlocks.RICH_DIRT_PATH.getStateWithProperties(state));
                if (player.getMainHandStack().isIn(ItemTags.SHOVELS)) {
                    player.getMainHandStack().damage(1,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                } else if (player.getOffHandStack().isIn(ItemTags.SHOVELS)) {
                    player.getMainHandStack().damage(1,player,e -> e.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND));
                }
                world.playSound(null, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.PLAYERS, 1.0f, 1.0f);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }
}
