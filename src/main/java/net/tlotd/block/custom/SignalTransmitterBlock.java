package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
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
import net.tlotd.block.ModBlocks;
import net.tlotd.sound.ModSounds;
import net.tlotd.util.ModTags;
import net.tlotd.world.SignalTrackingArray;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SignalTransmitterBlock extends Block {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = FacingBlock.FACING;

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

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    public SignalTransmitterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    public static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(1, 0, 1, 15, 11.5, 15),
            Block.createCuboidShape(2, 11.5, 2, 14, 15, 14),
            Block.createCuboidShape(3, 15, 3, 13, 16, 13)
    );

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("block.tlotd.signal_transmitter.tooltip").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.signal_transmitter.tooltip_2")).formatted(Formatting.BLUE));
        tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.signal_transmitter.tooltip_3")).formatted(Formatting.BLUE));
        tooltip.add(Text.translatable("block.tlotd.signal_transmitter.tooltip_4").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(" 2x ").append(Text.translatable("block.tlotd.signal_transmitter_antenna")).formatted(Formatting.BLUE));
        tooltip.add(Text.literal(" ").append(Text.translatable("block.minecraft.lightning_rod.upside_down")).formatted(Formatting.BLUE));
        tooltip.add(Text.literal(" ").append(Text.translatable("block.minecraft.lightning_rod")).formatted(Formatting.BLUE));
        super.appendTooltip(stack, world, tooltip, options);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;
        ServerWorld serverWorld = (ServerWorld) world;
        SignalTrackingArray tracker = SignalTrackingArray.get(serverWorld);
        boolean antennaCompleted =
                world.getBlockState(pos.up()).isOf(ModBlocks.SIGNAL_TRANSMITTER_ANTENNA) &&
                        world.getBlockState(pos.up(2)).isOf(ModBlocks.SIGNAL_TRANSMITTER_ANTENNA) &&
                        world.getBlockState(pos.up(2)).get(SignalTransmitterAntennaBlock.UPPER) &&
                        world.getBlockState(pos.up(3)).isOf(Blocks.LIGHTNING_ROD) &&
                        world.getBlockState(pos.up(3)).get(FACING) == Direction.DOWN &&
                        world.getBlockState(pos.up(4)).isOf(Blocks.LIGHTNING_ROD);
        if (!antennaCompleted) {
            player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.incomplete"), false);
            world.playSound(null, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 1.0f, 1.0f);
            return ActionResult.SUCCESS;
        }
        ItemStack stack = player.getMainHandStack();
        if (!stack.isEmpty() && stack.isIn(ModTags.Items.TRANSMITTABLE_SIGNALS)) {
            Identifier id = Registries.ITEM.getId(stack.getItem());
            if (tracker.hasSignal(id)) {
                tracker.removeSignal(id);
                player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.removed"), true);
            } else {
                tracker.addSignal(id);
                player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.added"), true);
            }
            world.playSound(null, pos, ModSounds.BLOCK_VIDEOCASSETTE_RECORDER, SoundCategory.BLOCKS, 1.0f, 1.0f);
        } else {
            if (!tracker.hasAnySignals()) {
                player.sendMessage(
                        Text.translatable("block.tlotd.signal_transmitter.list_empty"),
                        false
                );
            } else {
                player.sendMessage(
                        Text.translatable("block.tlotd.signal_transmitter.list", tracker.getSignalCount()),
                        false
                );
                for (Identifier sig : tracker.getAllSignals()) {
                    String keyName = "item." + sig.getNamespace() + "." + sig.getPath();
                    String descKey = keyName + ".desc";
                    Text nameText;
                    Text descText;
                    if (Language.getInstance().hasTranslation(keyName)) {
                        nameText = Text.translatable(keyName);
                    } else {
                        String prettyName = sig.getPath().replace('_', ' ');
                        prettyName = Character.toUpperCase(prettyName.charAt(0)) + prettyName.substring(1);
                        nameText = Text.literal(prettyName);
                    }
                    if (Language.getInstance().hasTranslation(descKey)) {
                        descText = Text.translatable(descKey);
                    } else {
                        descText = Text.literal("");
                    }
                    player.sendMessage(
                            Text.literal(" ").formatted(Formatting.GRAY)
                                    .append(nameText)
                                    .append(Text.literal(": "))
                                    .append(descText),
                            false
                    );
                }
            }
            world.playSound(null, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }
        return ActionResult.SUCCESS;
    }
}
