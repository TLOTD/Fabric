package net.tlotd.block.custom;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
//import net.tlotd.block.entity.HEVChargerBlockEntity;
//import net.tlotd.block.entity.ModBlockEntities;
import net.tlotd.util.EnergyHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HEVChargerBlock extends WallMountedBlock {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        for (Direction direction : ctx.getPlacementDirections()) {
            BlockState blockState = direction.getAxis() == Direction.Axis.Y ? this.getDefaultState().with(FACE, direction == Direction.UP ? BlockFace.CEILING : BlockFace.FLOOR).with(FACING, ctx.getHorizontalPlayerFacing()).with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER)) : this.getDefaultState().with(FACE, BlockFace.WALL).with(FACING, direction).with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
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
        this.setDefaultState(this.stateManager.getDefaultState().with(FACE, BlockFace.WALL).with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    @Override
    protected MapCodec<? extends WallMountedBlock> getCodec() {
        return null;
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
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        super.appendTooltip(stack, context, tooltip, options);
        String formattedMaxPower;
        if (Screen.hasShiftDown()) {
            formattedMaxPower = String.format("%,d", 1000000);
        } else {
            formattedMaxPower = EnergyHelper.getEnergyString(1000000);
        }
        String formattedMaxPower2 = formattedMaxPower.replace(',', '.');
        tooltip.add(Text.translatable("item.tlotd.energy_level.tooltip", 0, formattedMaxPower, 0, formattedMaxPower2).formatted(Formatting.YELLOW));
    }
}