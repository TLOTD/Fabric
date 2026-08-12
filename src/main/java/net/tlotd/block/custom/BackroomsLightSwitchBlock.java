package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;

public class BackroomsLightSwitchBlock extends LeverBlock {
    public BackroomsLightSwitchBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Block BlockCheck = Blocks.BEDROCK;
        Block BlockPlace = Blocks.REDSTONE_BLOCK;
        if (state.get(POWERED).equals(true)) {
            BlockCheck = Blocks.REDSTONE_BLOCK;
            BlockPlace = Blocks.BEDROCK;
        }
        state = state.cycle(POWERED);
        world.setBlockState(pos, state, 3);
        float f = state.get(POWERED) ? 0.6F : 0.5F;
        world.playSound(null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
        for (int i = 1; i <= 10; i++) {
            BlockPos checkPos = pos.up(i);
            BlockState checkState = world.getBlockState(checkPos);
            if (checkState.getBlock() == BlockCheck) {
                world.setBlockState(checkPos, BlockPlace.getDefaultState(), Block.NOTIFY_ALL);
                break;
            }
        }
        return ActionResult.SUCCESS;
    }

    public static final VoxelShape SWITCH_NORTH_SHAPE = Block.createCuboidShape(4.0F, 4.0F, 13.0F, 12.0F, 12.0F, 16.0F);
    public static final VoxelShape SWITCH_SOUTH_SHAPE = Block.createCuboidShape(4.0F, 4.0F, 0.0F, 12.0F, 12.0F, 3.0F);
    public static final VoxelShape SWITCH_WEST_SHAPE = Block.createCuboidShape(13.0F, 4.0F, 4.0F, 16.0F, 12.0F, 12.0F);
    public static final VoxelShape SWITCH_EAST_SHAPE = Block.createCuboidShape(0.0F, 4.0F, 4.0F, 3.0F, 12.0F, 12.0F);
    public static final VoxelShape SWITCH_FLOOR_SHAPE = Block.createCuboidShape(4.0F, 0.0F, 4.0F, 12.0F, 3.0F, 12.0F);
    public static final VoxelShape SWITCH_CEILING_SHAPE = Block.createCuboidShape(4.0F, 13.0F, 4.0F, 12.0F, 16.0F, 12.0F);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACE)) {
            case FLOOR -> SWITCH_FLOOR_SHAPE;
            case WALL -> switch (state.get(FACING)) {
                case EAST -> SWITCH_EAST_SHAPE;
                case WEST -> SWITCH_WEST_SHAPE;
                case SOUTH -> SWITCH_SOUTH_SHAPE;
                default -> SWITCH_NORTH_SHAPE;
            };
            default -> SWITCH_CEILING_SHAPE;
        };
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return ModBlocks.LIGHT_SWITCH.asItem().getDefaultStack();
    }
}