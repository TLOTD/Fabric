package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class AdventWreathBlock extends Block {

    public static final IntProperty LIT = IntProperty.of("lit", 0, 4);

    public AdventWreathBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    public static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 16, 4, 5), Block.createCuboidShape(0, 0, 5, 5, 4, 11), Block.createCuboidShape(11, 0, 5, 16, 4, 11), Block.createCuboidShape(0, 0, 11, 16, 4, 16));

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (hand != Hand.MAIN_HAND) {
            return ActionResult.PASS;
        }
        if (!world.isClient()) {
            if (state.get(LIT) < 4 && (player.getStackInHand(hand).isOf(Items.FIRE_CHARGE) || player.getStackInHand(hand).isOf(Items.FLINT_AND_STEEL))) {
                world.setBlockState(pos, state.with(LIT, state.get(LIT) + 1));
                player.incrementStat(Stats.USED.getOrCreateStat(player.getStackInHand(hand).getItem()));
                if (player.getStackInHand(hand).isOf(Items.FIRE_CHARGE)) {
                    world.playSound(null, pos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    if (!player.isCreative()) {
                        player.getStackInHand(hand).decrement(1);
                    }
                } else {
                    world.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    player.getStackInHand(hand).damage(1, player, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                }
                return ActionResult.SUCCESS;
            } else if (state.get(LIT) > 0) {
                world.setBlockState(pos, state.with(LIT, state.get(LIT) - 1));
                world.playSound(null, pos, SoundEvents.BLOCK_CANDLE_EXTINGUISH, SoundCategory.PLAYERS, 1.0f, 1.0f);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }
}