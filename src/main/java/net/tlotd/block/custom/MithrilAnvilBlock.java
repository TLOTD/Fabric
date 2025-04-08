package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
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
import net.tlotd.block.entity.MithrilAnvilBlockEntity;
import net.tlotd.block.entity.ModBlockEntities;
import net.tlotd.config.ModConfigs;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MithrilAnvilBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final DirectionProperty FACING;
    private static final VoxelShape BASE_SHAPE;
    private static final VoxelShape X_STEP_SHAPE;
    private static final VoxelShape X_STEM_SHAPE;
    private static final VoxelShape X_FACE_SHAPE;
    private static final VoxelShape Z_STEP_SHAPE;
    private static final VoxelShape Z_STEM_SHAPE;
    private static final VoxelShape Z_FACE_SHAPE;
    private static final VoxelShape X_AXIS_SHAPE;
    private static final VoxelShape Z_AXIS_SHAPE;

    public MithrilAnvilBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().rotateYClockwise());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        return direction.getAxis() == Direction.Axis.X ? X_AXIS_SHAPE : Z_AXIS_SHAPE;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        BASE_SHAPE = Block.createCuboidShape(2.0F, 0.0F, 2.0F, 14.0F, 4.0F, 14.0F);
        X_STEP_SHAPE = Block.createCuboidShape(3.0F, 4.0F, 4.0F, 13.0F, 5.0F, 12.0F);
        X_STEM_SHAPE = Block.createCuboidShape(4.0F, 5.0F, 6.0F, 12.0F, 10.0F, 10.0F);
        X_FACE_SHAPE = Block.createCuboidShape(0.0F, 10.0F, 3.0F, 16.0F, 16.0F, 13.0F);
        Z_STEP_SHAPE = Block.createCuboidShape(4.0F, 4.0F, 3.0F, 12.0F, 5.0F, 13.0F);
        Z_STEM_SHAPE = Block.createCuboidShape(6.0F, 5.0F, 4.0F, 10.0F, 10.0F, 12.0F);
        Z_FACE_SHAPE = Block.createCuboidShape(3.0F, 10.0F, 0.0F, 13.0F, 16.0F, 16.0F);
        X_AXIS_SHAPE = VoxelShapes.union(BASE_SHAPE, X_STEP_SHAPE, X_STEM_SHAPE, X_FACE_SHAPE);
        Z_AXIS_SHAPE = VoxelShapes.union(BASE_SHAPE, Z_STEP_SHAPE, Z_STEM_SHAPE, Z_FACE_SHAPE);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MithrilAnvilBlockEntity(pos, state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof MithrilAnvilBlockEntity) {
                ItemScatterer.spawn(world, pos, (MithrilAnvilBlockEntity)blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((MithrilAnvilBlockEntity) world.getBlockEntity(pos));
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.MITHRIL_ANVIL_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    public static final Identifier RECIPIES_FONT_ID = new Identifier("tlotd", "recipies");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        if (Screen.hasShiftDown()) {
            Style style = this.getName().getStyle();
            tooltip.add(Text.literal(""));
            if (ModConfigs.MITHRIL_ANVIL_NEEDS_DIRECT_MOONLIGHT) {
                tooltip.add(Text.translatable("block.tlotd.mithril_anvil.tooltip").formatted(Formatting.GRAY));
                tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.mithril_anvil.tooltip_2").formatted(Formatting.BLUE)));
                tooltip.add(Text.literal(""));
            }
            tooltip.add(Text.literal("\uE001\uE000\uE008\uE009\uE00A\uE00B\uE002\uE00C\uE004\uE003\uE006").setStyle(style.withFont(RECIPIES_FONT_ID)));
            tooltip.add(Text.literal("\uE001\uE000\uE005\uE002\uE00C\uE006\uE003\uE007").setStyle(style.withFont(RECIPIES_FONT_ID)));

        } else {
            tooltip.add(Text.literal(""));
            if (ModConfigs.MITHRIL_ANVIL_NEEDS_DIRECT_MOONLIGHT) {
                tooltip.add(Text.translatable("block.tlotd.mithril_anvil.tooltip").formatted(Formatting.GRAY));
                tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.mithril_anvil.tooltip_2").formatted(Formatting.BLUE)));
                tooltip.add(Text.literal(""));
            }
            tooltip.add(Text.literal("").append(Text.translatable("block.tlotd.mithril_anvil.tooltip_3").formatted(Formatting.DARK_GRAY)).append(Text.translatable("key.keyboard.left.shift").formatted(Formatting.GRAY)).append(Text.translatable("block.tlotd.mithril_anvil.tooltip_4").formatted(Formatting.DARK_GRAY)));
        }
        super.appendTooltip(stack, world, tooltip, options);
    }
}
