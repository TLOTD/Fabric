package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.tlotd.util.ModTags;

public class HEVChargerBlock extends Block {

    public static final EnumProperty<WallMountLocation> FACE = Properties.WALL_MOUNT_LOCATION;
    public static final DirectionProperty FACING = Properties.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {

        for (Direction direction : ctx.getPlacementDirections()) {
            BlockState blockState = direction.getAxis() == Direction.Axis.Y ? this.getDefaultState().with(FACE, direction == Direction.UP ? WallMountLocation.CEILING : WallMountLocation.FLOOR).with(FACING, ctx.getHorizontalPlayerFacing()).with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER)) : this.getDefaultState().with(FACE, WallMountLocation.WALL).with(FACING, direction).with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
            if (!blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) continue;
            return blockState;
        }
        return null;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACE, FACING, WATERLOGGED);
    }

    public HEVChargerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACE, WallMountLocation.WALL).with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    public static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(2, 0, 0, 14, 16, 2);
    public static final VoxelShape EAST_SHAPE = Block.createCuboidShape(14, 0, 2, 16, 16, 14);
    public static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(2, 0, 14, 14, 16, 16);
    public static final VoxelShape WEST_SHAPE = Block.createCuboidShape(0, 0, 2, 2, 16, 14);
    public static final VoxelShape DOWN_SHAPE = Block.createCuboidShape(0, 0, 2, 16, 2, 14);
    public static final VoxelShape DOWN_SHAPE_2 = Block.createCuboidShape(2, 0, 0, 14, 2, 16);
    public static final VoxelShape UP_SHAPE = Block.createCuboidShape(0, 14, 2, 16, 16, 14);
    public static final VoxelShape UP_SHAPE_2 = Block.createCuboidShape(2, 14, 0, 14, 16, 16);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACE)) {
            case FLOOR: {
                switch (state.get(FACING).getAxis()) {
                    case X: {
                        return DOWN_SHAPE;
                    }
                }
                return DOWN_SHAPE_2;
            }
            case WALL: {
                switch (state.get(FACING)) {
                    case EAST: {
                        return EAST_SHAPE;
                    }
                    case WEST: {
                        return WEST_SHAPE;
                    }
                    case SOUTH: {
                        return SOUTH_SHAPE;
                    }
                }
                return NORTH_SHAPE;
            }
        }
        switch (state.get(FACING).getAxis()) {
            case X: {
                return UP_SHAPE;
            }
        }
        return UP_SHAPE_2;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getInventory().getArmorStack(0).isIn(ModTags.Items.HEV_CHARGER_CHARGABLE) || player.getInventory().getArmorStack(1).isIn(ModTags.Items.HEV_CHARGER_CHARGABLE) || player.getInventory().getArmorStack(2).isIn(ModTags.Items.HEV_CHARGER_CHARGABLE) || player.getInventory().getArmorStack(3).isIn(ModTags.Items.HEV_CHARGER_CHARGABLE)) {
            if (!world.isClient()) {
                if (player.getInventory().getArmorStack(0).isIn(ModTags.Items.HEV_CHARGER_CHARGABLE)) {
                    if (player.getInventory().getArmorStack(0).getDamage() > 30) {
                        player.getInventory().getArmorStack(0).setDamage(player.getInventory().getArmorStack(0).getDamage()-30);
                    } else {
                        player.getInventory().getArmorStack(0).setDamage(0);
                    }
                }
                if (player.getInventory().getArmorStack(1).isIn(ModTags.Items.HEV_CHARGER_CHARGABLE)) {
                    if (player.getInventory().getArmorStack(1).getDamage() > 30) {
                        player.getInventory().getArmorStack(1).setDamage(player.getInventory().getArmorStack(1).getDamage()-30);
                    } else {
                        player.getInventory().getArmorStack(1).setDamage(0);
                    }
                }
                if (player.getInventory().getArmorStack(2).isIn(ModTags.Items.HEV_CHARGER_CHARGABLE)) {
                    if (player.getInventory().getArmorStack(2).getDamage() > 30) {
                        player.getInventory().getArmorStack(2).setDamage(player.getInventory().getArmorStack(2).getDamage()-30);
                    } else {
                        player.getInventory().getArmorStack(2).setDamage(0);
                    }
                }
                if (player.getInventory().getArmorStack(3).isIn(ModTags.Items.HEV_CHARGER_CHARGABLE)) {
                    if (player.getInventory().getArmorStack(3).getDamage() > 30) {
                        player.getInventory().getArmorStack(3).setDamage(player.getInventory().getArmorStack(3).getDamage()-30);
                    } else {
                        player.getInventory().getArmorStack(3).setDamage(0);
                    }
                }
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}
