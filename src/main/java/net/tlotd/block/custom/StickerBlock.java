package net.tlotd.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.LanguageDefinition;
import net.minecraft.client.resource.language.LanguageManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;
import net.tlotd.networking.ClientGlobalConfig;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class StickerBlock extends WallMountedBlock {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos blockPos = pos.offset(direction);
        BlockState blockState = world.getBlockState(blockPos);
        if (!state.get(FACE).equals(BlockFace.WALL)) {
            if (state.get(FACE).equals(BlockFace.FLOOR)) {
                return world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos.down(), Direction.UP);
            } else return world.getBlockState(pos.up()).isSideSolidFullSquare(world, pos.up(), Direction.DOWN);
        } else return blockState.isSideSolidFullSquare(world, blockPos, direction.getOpposite());
    }

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
        return (direction == Direction.DOWN && state.get(FACE).equals(BlockFace.FLOOR) || direction == Direction.UP && state.get(FACE).equals(BlockFace.CEILING) || direction == state.get(FACING) && state.get(FACE).equals(BlockFace.WALL)) && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : state;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACE, FACING, WATERLOGGED);
    }

    public StickerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACE, BlockFace.WALL).with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    @Override
    protected MapCodec<? extends WallMountedBlock> getCodec() {
        return null;
    }

    public static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(1.0, 4.0, 0.0, 15.0, 12.0, 0.1);
    public static final VoxelShape EAST_SHAPE = Block.createCuboidShape(15.9, 4.0, 1.0, 16.0, 12.0, 15.0);
    public static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(1.0, 4.0, 15.9, 15.0, 12.0, 16.0);
    public static final VoxelShape WEST_SHAPE = Block.createCuboidShape(0.0, 4.0, 1.0, 0.1, 12.0, 15.0);
    public static final VoxelShape DOWN_SHAPE = Block.createCuboidShape(4.0, 0.0, 1.0, 12.0, 0.1, 15.0);
    public static final VoxelShape DOWN_SHAPE_2 = Block.createCuboidShape(1.0, 0.0, 4.0, 15.0, 0.1, 12.0);
    public static final VoxelShape UP_SHAPE = Block.createCuboidShape(4.0, 15.9, 1.0, 12.0, 16.0, 15.0);
    public static final VoxelShape UP_SHAPE_2 = Block.createCuboidShape(1.0, 15.9, 4.0, 15.0, 16.0, 12.0);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACE)) {
            case FLOOR: {
                if (Objects.requireNonNull(state.get(FACING).getAxis()) == Direction.Axis.X) {
                    return DOWN_SHAPE;
                }
                return DOWN_SHAPE_2;
            }
            case WALL: {
                return switch (state.get(FACING)) {
                    case EAST -> EAST_SHAPE;
                    case WEST -> WEST_SHAPE;
                    case SOUTH -> SOUTH_SHAPE;
                    default -> NORTH_SHAPE;
                };
            }
        }
        if (Objects.requireNonNull(state.get(FACING).getAxis()) == Direction.Axis.X) {
            return UP_SHAPE;
        }
        return UP_SHAPE_2;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        if (Screen.hasShiftDown()) {
            if (stack.isOf(ModBlocks.BW_STICKER.asItem())) {
                tooltip.add(Text.translatable("block.tlotd.bw_sticker.tooltip").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.bw_sticker.tooltip_2").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.bw_sticker.tooltip_3").formatted(Formatting.GRAY));
            } else {
                tooltip.add(Text.translatable("block.tlotd.tlotd_sticker.tooltip").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.tlotd_sticker.tooltip_2").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.tlotd_sticker.tooltip_3").formatted(Formatting.GRAY));
            }
        }
        super.appendTooltip(stack, context, tooltip, options);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player != null && world.isClient) {
            boolean easterEggs = ClientGlobalConfig.easterEggs;
            if (easterEggs && player.getStackInHand(hand).isOf(ModItems.MAULTASCHE /*ModItems.MAULTASCHEN_BROTH*/)) {
                MinecraftClient client = MinecraftClient.getInstance();
                LanguageManager languageManager = client.getLanguageManager();
                LanguageDefinition languageDefinition = languageManager.getLanguage("swg_de");
                if (languageDefinition == null || languageManager.getLanguage().equals("swg_de")) {
                    return ItemActionResult.FAIL;
                }
                languageManager.setLanguage("swg_de");
                client.options.language = "swg_de";
                client.reloadResources();
                client.options.write();
                return ItemActionResult.SUCCESS;
            } else {
                return ItemActionResult.FAIL;
            }
        }
        return ItemActionResult.FAIL;
    }
}