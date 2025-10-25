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

import static net.tlotd.block.custom.TelevisionModifierBlock.FACING;

public class ShelfableBlockItem extends BlockItem {
    public ShelfableBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        if (context.getPlayer().isSneaking() && (blockState.isOf(Blocks.CHISELED_BOOKSHELF) || (blockState.isOf(ModBlocks.VIDEOCASSETTE_RECORDER_BOOKSHELF) && context.getStack().isOf(ModBlocks.GAME_CONSOLE.asItem())) || (blockState.isOf(ModBlocks.GAME_CONSOLE_BOOKSHELF) && context.getStack().isOf(ModBlocks.VIDEOCASSETTE_RECORDER.asItem())))) {
            if (world.isClient) {
                return ActionResult.SUCCESS;
            } else {
                BlockState blockState2;
                if (blockState.isOf(Blocks.CHISELED_BOOKSHELF)) {
                    if (context.getStack().isOf(ModBlocks.VIDEOCASSETTE_RECORDER.asItem())) {
                        blockState2 = ModBlocks.VIDEOCASSETTE_RECORDER_BOOKSHELF.getDefaultState().with(FACING, blockState.get(FACING));
                    } else {
                        blockState2 = ModBlocks.GAME_CONSOLE_BOOKSHELF.getDefaultState().with(FACING, blockState.get(FACING));
                    }
                } else {
                    blockState2 = ModBlocks.MEDIA_SYSTEM_BOOKSHELF.getDefaultState().with(FACING, blockState.get(FACING));
                }
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
