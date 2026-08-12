package net.tlotd.block.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.tlotd.block.entity.WitchingTableBlockEntity;
import net.tlotd.block.entity.ModBlockEntities;
import net.tlotd.item.ModItems;
import net.tlotd.networking.ClientGlobalConfig;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.tlotd.api.TlotdAPI.enlightened;

public class WitchingTableBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final IntProperty SOUL_CHARGES = IntProperty.of("soul_charges", 0, 3);
    public static final IntProperty CURSED_SOUL_CHARGES = IntProperty.of("cursed_soul_charges", 0, 3);
    public static final IntProperty ABYSSAL_SOUL_CHARGES = IntProperty.of("abyssal_soul_charges", 0, 3);

    public WitchingTableBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 12, 16);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WitchingTableBlockEntity(pos, state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof WitchingTableBlockEntity) {
                ItemScatterer.spawn(world, pos, (WitchingTableBlockEntity) blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            if (player.getStackInHand(hand).isOf(ModItems.SOUL_FLASK) || player.getStackInHand(hand).isOf(ModItems.CURSED_SOUL_FLASK) || player.getStackInHand(hand).isOf(ModItems.SOUL_FLASK_OF_THE_ABYSS)) {
                return ActionResult.FAIL;
            }
            NamedScreenHandlerFactory screenHandlerFactory = ((WitchingTableBlockEntity) world.getBlockEntity(pos));
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.WITCHING_TABLE_BLOCK_ENTITY, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SOUL_CHARGES, CURSED_SOUL_CHARGES, ABYSSAL_SOUL_CHARGES);
    }

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier ILLAGER_FONT_ID = new Identifier("minecraft", "illageralt");
    public static final Identifier TOOLTIP_FONT_ID = new Identifier("tlotd", "tooltip");

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        Style style = getName().getStyle();
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;
        if (enlightened(player) >= 10 && Screen.hasShiftDown()) {
            tooltip.add(Text.literal("\uE002 ").setStyle(style.withFont(TOOLTIP_FONT_ID)).append(Text.translatable("item.tlotd.desc_enlightened").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GOLD))));
            if (ClientGlobalConfig.bloodWitching && ClientGlobalConfig.soulWitching) {
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_bs").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_bs_2").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_bs_3").formatted(Formatting.GRAY));
            } else if (ClientGlobalConfig.bloodWitching) {
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_b").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_b_2").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_b_3").formatted(Formatting.GRAY));
            } else if (ClientGlobalConfig.soulWitching) {
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_s").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_s_2").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_s_3").formatted(Formatting.GRAY));
            } else {
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip").formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_2").formatted(Formatting.GRAY));
            }
        } else {
            if (enlightened(player) >= 10) {
                tooltip.add(Text.literal("\uE001 ").setStyle(style.withFont(TOOLTIP_FONT_ID)).append(Text.translatable("item.tlotd.desc_enlightened").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GOLD))));
            } else {
                tooltip.add(Text.literal("\uE000 ").setStyle(style.withFont(TOOLTIP_FONT_ID)).append(Text.translatable("item.tlotd.desc_not_enlightened").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.DARK_GRAY))));
            }
            if (ClientGlobalConfig.bloodWitching && ClientGlobalConfig.soulWitching) {
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_bs").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_bs_2").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_bs_3").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
            } else if (ClientGlobalConfig.bloodWitching) {
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_b").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_b_2").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_b_3").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
            } else if (ClientGlobalConfig.soulWitching) {
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_s").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_s_2").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_s_3").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
            } else {
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
                tooltip.add(Text.translatable("block.tlotd.witching_table.tooltip_2").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
            }
        }
        tooltip.add(Text.translatable("item.tlotd.desc_occult").formatted(Formatting.RED));
        super.appendTooltip(stack, world, tooltip, options);
    }
}