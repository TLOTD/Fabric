package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.effect.ModEffects;

public class WoodenSteinBlock extends Block {

    public static final DirectionProperty FACING = FacingBlock.FACING;

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public WoodenSteinBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    private static final VoxelShape BASE_SHAPE = Block.createCuboidShape(5,0,5,11,8,11);

    public static final VoxelShape WEST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(7.5, 2.0, 3.0, 8.5, 7.0, 5.0),
            BASE_SHAPE
    );
    public static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(11.0, 2.0, 7.5, 13.0, 7.0, 8.5),
            BASE_SHAPE
    );
    public static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(7.5, 2.0, 11.0, 8.5, 7.0, 13.0),
            BASE_SHAPE
    );
    public static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(3.0, 2.0, 7.5, 5.0, 7.0, 8.5),
            BASE_SHAPE
    );

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case EAST -> EAST_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> BASE_SHAPE;
        };
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!this.equals(ModBlocks.WOODEN_STEIN)) {
            player.getHungerManager().add(1,0.1f);
            if (this.equals(ModBlocks.WOODEN_BEER_STEIN)) {
                player.addStatusEffect(new StatusEffectInstance(ModEffects.DRUNK, 600));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200));
            } else if (this.equals(ModBlocks.WOODEN_MILK_STEIN)) {
                player.clearStatusEffects();
            } else if (this.equals(ModBlocks.WOODEN_STRAWBERRY_MILKSHAKE_STEIN) || this.equals(ModBlocks.WOODEN_ORANGE_MILKSHAKE_STEIN) || this.equals(ModBlocks.WOODEN_CHOCOLATE_MILKSHAKE_STEIN)) {
                player.clearStatusEffects();
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 10, 0));
            } else if (this.equals(ModBlocks.HOT_WOODEN_MILK_STEIN) || this.equals(ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN)) {
                player.clearStatusEffects();
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1, 3));
            }
            world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.setBlockState(pos, ModBlocks.WOODEN_STEIN.getStateWithProperties(state));
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.FAIL;
        }
    }
}
