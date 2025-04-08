package net.tlotd.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;

import static net.tlotd.block.custom.VideocassetteRecorderBlock.FACING;

public class VCRBlockItem extends BlockItem {
    public VCRBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(Blocks.BOOKSHELF) && context.getPlayer().isSneaking()) {
            if (world.isClient) {
                return ActionResult.SUCCESS;
            } else {
                BlockState blockState2 = ModBlocks.VIDEOCASSETTE_RECORDER_BOOKSHELF.getDefaultState().with(FACING, context.getHorizontalPlayerFacing());
                Block.pushEntitiesUpBeforeBlockChange(blockState, blockState2, world, blockPos);
                world.setBlockState(blockPos, blockState2, 2);
                context.getStack().decrement(1);
                world.playSound(null, blockPos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1, 1);
                return ActionResult.CONSUME;
            }
        } else {
            return this.place(new ItemPlacementContext(context));
        }
    }
}
