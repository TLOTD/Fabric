package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
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
import net.tlotd.block.entity.HEVChargerBlockEntity;
import net.tlotd.util.EnergyNbtHelper;
import net.tlotd.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;

public class HEVChargerBlock extends Block implements BlockEntityProvider {

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

    public static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(2, 0, 0, 14, 16, 3);
    public static final VoxelShape EAST_SHAPE = Block.createCuboidShape(13, 0, 2, 16, 16, 14);
    public static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(2, 0, 13, 14, 16, 16);
    public static final VoxelShape WEST_SHAPE = Block.createCuboidShape(0, 0, 2, 3, 16, 14);
    public static final VoxelShape DOWN_SHAPE = Block.createCuboidShape(0, 0, 2, 16, 3, 14);
    public static final VoxelShape DOWN_SHAPE_2 = Block.createCuboidShape(2, 0, 0, 14, 3, 16);
    public static final VoxelShape UP_SHAPE = Block.createCuboidShape(0, 13, 2, 16, 16, 14);
    public static final VoxelShape UP_SHAPE_2 = Block.createCuboidShape(2, 13, 0, 14, 16, 16);

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
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient()) return ActionResult.SUCCESS;
        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof HEVChargerBlockEntity charger)) return ActionResult.PASS;
        ItemStack held = player.getStackInHand(hand);
        if (!held.isEmpty() && (getAugmentLevel(held, "tlotd:battery_pack") > 0 || held.isIn(ModTags.Items.HEV_CHARGER_CHARGABLE))) {
            long current = EnergyNbtHelper.getEnergy(held);
            long max = EnergyNbtHelper.getMaxEnergyItem(held);
            if (current < max && charger.energy.amount > 0) {
                long needed = max - current;
                long transferable = Math.min(needed, charger.energy.amount);
                EnergyNbtHelper.setEnergy(held, current + transferable);
                charger.energy.amount -= transferable;
                charger.markDirty();
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        }
        if (!held.isEmpty()) return ActionResult.PASS;
        PlayerInventory inv = player.getInventory();
        int[] slots = {3, 2, 1, 0};
        List<ItemStack> chargeables = new ArrayList<>();
        for (int slot : slots) {
            ItemStack armor = inv.getArmorStack(slot);
            if (armor.isEmpty()) continue;
            if (!(getAugmentLevel(armor, "tlotd:battery_pack") > 0 || armor.isIn(ModTags.Items.HEV_CHARGER_CHARGABLE))) continue;
            long current = EnergyNbtHelper.getEnergy(armor);
            long max = EnergyNbtHelper.getMaxEnergyItem(armor);
            if (current < max) {
                chargeables.add(armor);
            }
        }
        if (chargeables.isEmpty()) return ActionResult.PASS;
        long transferablePoints = charger.energy.amount / 1000;
        if (transferablePoints <= 0) return ActionResult.PASS;
        distributeEnergyEvenly(chargeables, charger, transferablePoints);
        charger.markDirty();
        return ActionResult.SUCCESS;
    }

    private void distributeEnergyEvenly(List<ItemStack> stacks,
                                        HEVChargerBlockEntity charger,
                                        long maxPoints) {
        long remaining = maxPoints;
        while (remaining > 0 && !stacks.isEmpty()) {
            Iterator<ItemStack> iterator = stacks.iterator();
            while (iterator.hasNext() && remaining > 0) {
                ItemStack stack = iterator.next();
                long current = EnergyNbtHelper.getEnergy(stack);
                long max = EnergyNbtHelper.getMaxEnergyItem(stack);
                if (current >= max) {
                    iterator.remove();
                    continue;
                }
                EnergyNbtHelper.setEnergy(stack, current + 1000);
                charger.energy.amount -= 1000;
                remaining--;
                if (EnergyNbtHelper.getEnergy(stack) >= max) {
                    iterator.remove();
                }
            }
        }
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new HEVChargerBlockEntity(pos, state);
    }
}
