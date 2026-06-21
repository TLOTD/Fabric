package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.tlotd.block.entity.DwarvenForgeBlockEntity;
import net.tlotd.block.entity.ModBlockEntities;
import net.tlotd.block.enum_property.ForgeLit;
import org.jetbrains.annotations.Nullable;

public class DwarvenForgeBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final DirectionProperty FACING;
    public static final EnumProperty<ForgeLit> FIRE = EnumProperty.of("fire", ForgeLit.class);

    public DwarvenForgeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(FIRE, ForgeLit.EXTINGUISHED));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, FIRE);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DwarvenForgeBlockEntity(pos, state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DwarvenForgeBlockEntity) {
                ItemScatterer.spawn(world, pos, (DwarvenForgeBlockEntity)blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((DwarvenForgeBlockEntity) world.getBlockEntity(pos));
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.DWARVEN_FORGE_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        switch (state.get(FIRE)) {
            case EXTINGUISHED -> {}
            case BURNING -> spawnParticles(world,pos, ParticleTypes.FLAME);
            case SOUL -> spawnParticles(world, pos, ParticleTypes.SOUL_FIRE_FLAME);
            case DRAGON -> spawnParticles(world, pos, ParticleTypes.DRAGON_BREATH);
        }
    }

    private static void spawnParticles(World world, BlockPos pos, ParticleEffect particle) {
        Random random = world.random;
        if (random.nextInt(5) != 1) {
            return;
        }
        for(Direction direction : Direction.values()) {
            BlockPos blockPos = pos.offset(direction);
            if (!world.getBlockState(blockPos)
                    .isOpaqueFullCube(world, blockPos)) {
                Direction.Axis axis = direction.getAxis();
                double x = axis == Direction.Axis.X
                        ? 0.5F + 0.5625F * direction.getOffsetX()
                        : random.nextFloat();
                double y = axis == Direction.Axis.Y
                        ? 0.5F + 0.5625F * direction.getOffsetY()
                        : random.nextFloat();
                double z = axis == Direction.Axis.Z
                        ? 0.5F + 0.5625F * direction.getOffsetZ()
                        : random.nextFloat();
                world.addParticle(
                        particle,
                        pos.getX() + x,
                        pos.getY() + y,
                        pos.getZ() + z,
                        0,
                        0,
                        0
                );
            }
        }
    }

    public static int lightLevel(BlockState state) {
        if (state.get(FIRE).equals(ForgeLit.DRAGON)) {
            return 15;
        } else if (state.get(FIRE).equals(ForgeLit.SOUL)) {
            return 12;
        } else if (state.get(FIRE).equals(ForgeLit.BURNING)) {
            return 8;
        }
        return 0;
    }
}