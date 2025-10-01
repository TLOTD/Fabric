package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.tlotd.block.entity.ModBlockEntities;
import net.tlotd.block.entity.OxygenCollectorBlockEntity;
import net.tlotd.compat.ModCheckOthers;
import net.tlotd.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OxygenCollectorBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public static final DirectionProperty FACING = FacingBlock.FACING;

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return (world.getBlockState(pos.add(0,-1,0)).isIn(ModTags.Blocks.OXYGEN_PROVIDERS) && !world.getBlockState(pos.add(0,-1,0)).isOf(Blocks.FLOWER_POT));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
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

        return !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    public OxygenCollectorBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    private static final VoxelShape BASE_SHAPE = Block.createCuboidShape(0,-16,0,16,4,16);

    public static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0.0, 4.0, 4.0, 16.0, 16.0, 12.0),
            Block.createCuboidShape(7.0, 4.0, 1.0, 13.0, 11.0, 15.0),
            BASE_SHAPE);
    public static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(4.0, 4.0, 0.0, 12.0, 16.0, 16.0),
            Block.createCuboidShape(1.0, 4.0, 7.0, 15.0, 11.0, 13.0),
            BASE_SHAPE);
    public static final VoxelShape WEST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0.0, 4.0, 4.0, 16.0, 16.0, 12.0),
            Block.createCuboidShape(3.0, 4.0, 1.0, 9.0, 11.0, 15.0),
            BASE_SHAPE);
    public static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(4.0, 4.0, 0.0, 12.0, 16.0, 16.0),
            Block.createCuboidShape(1.0, 4.0, 3.0, 15.0, 11.0, 9.0),
            BASE_SHAPE);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case EAST -> EAST_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> NORTH_SHAPE;
        };
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier MODS_FONT_ID = new Identifier("tlotd", "mods");
    public static final Identifier RECIPIES_FONT_ID = new Identifier("tlotd", "recipies");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        Style style = this.getName().getStyle();
        if (options.isCreative()){
            tooltip.add(Text.literal("\uE015 ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.translatable("mod.ad_astra.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
        }
        if (ModCheckOthers.PATCHOULI) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Text.literal("\uE007 ").setStyle(style.withFont(RECIPIES_FONT_ID)).append(Text.translatable("item.patchouli.guide_book.tlotd.guiding_grimoire").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                tooltip.add(Text.literal("   ").append(Text.translatable("item.patchouli.guide_book.tlotd.guiding_grimoire.category").formatted(Formatting.DARK_GRAY)).append(Text.translatable("item.patchouli.guide_book.tlotd.guiding_grimoire.category.exploration").formatted(Formatting.GRAY)));
                tooltip.add(Text.literal("   ").append(Text.translatable("item.patchouli.guide_book.tlotd.guiding_grimoire.chapter").formatted(Formatting.DARK_GRAY)).append(Text.translatable("item.patchouli.guide_book.tlotd.guiding_grimoire.chapter.the_moon").formatted(Formatting.GRAY)));
            } else {
                tooltip.add(Text.literal("\uE007 ").setStyle(style.withFont(RECIPIES_FONT_ID)).append(Text.translatable("mod.patchouli.name").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
        }
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("block.tlotd.oxygen_collector.tooltip").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.oxygen_collector.tooltip_2")).formatted(Formatting.BLUE));
        super.appendTooltip(stack, world, tooltip, options);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new OxygenCollectorBlockEntity(pos, state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof OxygenCollectorBlockEntity) {
                ItemScatterer.spawn(world, pos, (OxygenCollectorBlockEntity)blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((OxygenCollectorBlockEntity) world.getBlockEntity(pos));
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.OXYGEN_COLLECTOR_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }
}
