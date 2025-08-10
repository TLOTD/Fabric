package net.tlotd.block;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.tlotd.item.ModItems;

import static net.minecraft.block.SideShapeType.FULL;
import static net.minecraft.block.WallTorchBlock.FACING;
import static net.tlotd.world.dimension.ModDimensions.LUNA_LEVEL_KEY;

public class ModUseBlockCallback {
    public static void interceptBlocks() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            ItemStack item = player.getStackInHand(hand);
            if (player.getWorld().getRegistryKey().equals(LUNA_LEVEL_KEY)) {
                if (item.getItem() == Items.FLINT_AND_STEEL || item.getItem() == Items.FIRE_CHARGE) {
                    return ActionResult.FAIL;
                } else if (item.getItem() == Items.CAMPFIRE || item.getItem() == Items.SOUL_CAMPFIRE || item.getItem() == ModBlocks.SULFUR_CAMPFIRE.asItem()) {
                    BlockState state = ((BlockItem) item.getItem()).getBlock().getDefaultState()
                            .with(CampfireBlock.LIT, false)
                            .with(CampfireBlock.FACING, player.getHorizontalFacing().getOpposite());
                    BlockPos pos = hitResult.getBlockPos().offset(hitResult.getSide());
                    world.setBlockState(pos, state);
                    world.playSound(null, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1f, 1f);
                    world.playSound(null, pos, SoundEvents.BLOCK_CANDLE_EXTINGUISH, SoundCategory.BLOCKS, 1f, 1f);
                    if (!player.getAbilities().creativeMode) {
                        item.decrement(1);
                    }
                    return ActionResult.SUCCESS;
                } else if (item.getItem() == Items.TORCH || item.getItem() == Items.SOUL_TORCH || item.getItem() == ModItems.SULFUR_TORCH) {
                    BlockPos pos = hitResult.getBlockPos();
                    BlockState hitState = world.getBlockState(pos);
                    Direction facing = hitResult.getSide();
                    BlockState torchState;
                    if (hitState.isSideSolid(world, pos, facing,FULL) && (facing != Direction.DOWN)) {
                        torchState = ModBlocks.EXTINGUISHED_TORCH.getDefaultState();
                        if (item.getItem() == Items.TORCH) {
                            if (facing.getAxis() != Direction.Axis.Y) {
                                torchState = ModBlocks.EXTINGUISHED_WALL_TORCH.getDefaultState().with(FACING, facing);
                            }
                        } else if (item.getItem() == Items.SOUL_TORCH) {
                            if (facing.getAxis() != Direction.Axis.Y) {
                                torchState = ModBlocks.EXTINGUISHED_SOUL_WALL_TORCH.getDefaultState().with(FACING, facing);
                            } else {
                                torchState = ModBlocks.EXTINGUISHED_SOUL_TORCH.getDefaultState();
                            }
                        } else if (item.getItem() == ModItems.SULFUR_TORCH) {
                            if (facing.getAxis() != Direction.Axis.Y) {
                                torchState = ModBlocks.EXTINGUISHED_SULFUR_WALL_TORCH.getDefaultState().with(FACING, facing);
                            } else {
                                torchState = ModBlocks.EXTINGUISHED_SULFUR_TORCH.getDefaultState();
                            }
                        }
                        world.setBlockState(pos.offset(facing), torchState);
                        world.playSound(null, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1f, 1f);
                        world.playSound(null, pos, SoundEvents.BLOCK_CANDLE_EXTINGUISH, SoundCategory.BLOCKS, 1f, 1f);
                        if (!player.getAbilities().creativeMode) {
                            item.decrement(1);
                        }
                        return ActionResult.SUCCESS;
                    } else return ActionResult.FAIL;
                }
            }
            return ActionResult.PASS;
        });
    }
}
