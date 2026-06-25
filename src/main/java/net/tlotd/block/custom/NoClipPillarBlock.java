package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.block.enum_property.NoClipable;
import net.tlotd.item.ModItems;
import net.tlotd.util.NoclipTracker;

public class NoClipPillarBlock extends PillarBlock {

    public static final EnumProperty<NoClipable> NOCLIPABLE = EnumProperty.of("state", NoClipable.class);

    public NoClipPillarBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(AXIS, Direction.Axis.Y).with(NOCLIPABLE, NoClipable.SOLID));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(NOCLIPABLE).equals(NoClipable.SOLID)) {
            return VoxelShapes.fullCube();
        }
        else return Block.createCuboidShape(0F, 0F, 0F, 0F, 0F, 0F);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS).add(NOCLIPABLE);
    }

    @Override
    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.fullCube();
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (state.get(NOCLIPABLE).equals(NoClipable.PORTALING)) {
            if (!world.isClient()) {
                if (entity instanceof ServerPlayerEntity serverPlayer) {
                    NoclipTracker.noclip(serverPlayer);
                }
            }
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            if (state.isOf(ModBlocks.STRIPPED_YELLOW_WALLPAPERED_WALL) && player.getStackInHand(hand).isOf(ModItems.YELLOW_WALLPAPER)) {
                player.incrementStat(Stats.USED.getOrCreateStat(player.getStackInHand(hand).getItem()));
                world.playSound(null, pos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.setBlockState(pos, ModBlocks.YELLOW_WALLPAPERED_WALL.getDefaultState().with(NOCLIPABLE, state.get(NOCLIPABLE)));
                player.getStackInHand(hand).decrement(1);
            }
        }
        return ActionResult.FAIL;
    }
}
