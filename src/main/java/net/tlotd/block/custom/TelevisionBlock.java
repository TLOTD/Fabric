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
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.tlotd.block.ModBlocks;
import net.tlotd.sound.ModSounds;
import net.tlotd.util.ModTags;
import net.tlotd.util.TelevisionSignalRegistry;
import net.tlotd.world.RadioStation;
import net.tlotd.world.SignalTrackingArray;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class TelevisionBlock extends Block {

    public static final IntProperty CHANNEL = IntProperty.of("channel", 0, 127);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing()).with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER)).with(CHANNEL, 0);
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
        builder.add(FACING, WATERLOGGED, CHANNEL);
    }

    public TelevisionBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false).with(CHANNEL, 0));
    }

    public static final VoxelShape Z_SHAPE = Block.createCuboidShape(3, 0, 0, 13, 12, 16);
    public static final VoxelShape X_SHAPE = Block.createCuboidShape(0, 0, 3, 16, 12, 13);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case EAST, WEST -> Z_SHAPE;
            default -> X_SHAPE;
        };
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public MutableText getName() {
        return Text.translatable("block.tlotd.television");
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return ModBlocks.TELEVISION.asItem().getDefaultStack();
    }

    public static BlockState handleTelevisionUse(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (world.isClient) {
            return state;
        }
        ServerWorld serverWorld = (ServerWorld) world;
        SignalTrackingArray tracker = SignalTrackingArray.get(serverWorld);
        boolean isOn = state.getBlock() == ModBlocks.TELEVISION_ON || state.getBlock().getDefaultState().isIn(ModTags.Blocks.TELEVISIONS_ON);
        int currentChannel = state.getOrEmpty(TelevisionBlock.CHANNEL).orElse(0);
        boolean hasOverride = world.getBlockState(pos.up()).isIn(ModTags.Blocks.INTERDIMENSIONAL_RECEIVERS);
        List<Identifier> receivableSignals = tracker.getAvailableSignals(pos);
        if (player.isSneaking()) {
            Optional<TelevisionSignalRegistry.SignalEntry> currentEntry = TelevisionSignalRegistry.getAll().stream().filter(e -> e.channel() == currentChannel && (e.onBlock() == state.getBlock() || e.offBlock() == state.getBlock())).findFirst();
            BlockState newState;
            if (isOn) {
                if (currentEntry.isPresent()) {
                    newState = currentEntry.get().offBlock().getStateWithProperties(state);
                } else {
                    newState = ModBlocks.TELEVISION.getStateWithProperties(state).with(TelevisionBlock.CHANNEL, 0);
                }
            } else {
                boolean canReceiveCurrentChannel = currentEntry.isPresent() && (hasOverride || receivableSignals.contains(currentEntry.get().signalItem()));
                if (canReceiveCurrentChannel) {
                    newState = currentEntry.get().onBlock().getStateWithProperties(state).with(TelevisionBlock.CHANNEL, currentEntry.get().channel());
                } else {
                    newState = ModBlocks.TELEVISION_ON.getStateWithProperties(state).with(TelevisionBlock.CHANNEL, 0);
                }
            }
            world.setBlockState(pos, newState, 3);
            world.playSound(null, pos, ModSounds.BLOCK_TELEVISION_SWITCH_CHANNEL, SoundCategory.BLOCKS, 1f, 1f);
            return newState;
        }
        if (isOn) {
            List<TelevisionSignalRegistry.SignalEntry> availableSignals;
            if (hasOverride) {
                availableSignals = TelevisionSignalRegistry.getAll().stream().toList();
            } else {
                availableSignals = TelevisionSignalRegistry.getAll().stream().filter(e -> receivableSignals.contains(e.signalItem())).toList();
            }
            if (availableSignals.isEmpty()) {
                return state;
            }
            int currentIndex = -1;
            for (int i = 0; i < availableSignals.size(); i++) {
                TelevisionSignalRegistry.SignalEntry entry = availableSignals.get(i);
                if ((entry.onBlock() == state.getBlock() || entry.offBlock() == state.getBlock()) && entry.channel() == currentChannel) {
                    currentIndex = i;
                    break;
                }
            }
            int nextIndex = (currentIndex + 1) % availableSignals.size();
            TelevisionSignalRegistry.SignalEntry next = availableSignals.get(nextIndex);
            BlockState newState = next.onBlock().getStateWithProperties(state).with(TelevisionBlock.CHANNEL, next.channel());
            world.setBlockState(pos, newState, 3);
            world.playSound(null, pos, ModSounds.BLOCK_TELEVISION_SWITCH_CHANNEL, SoundCategory.BLOCKS, 1f, 1f);
            return newState;
        }

        return state;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("block.tlotd.television.tooltip").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.signal_transmitter").formatted(Formatting.BLUE)));
        tooltip.add(Text.translatable("block.tlotd.television.tooltip_2").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.videocassette_recorder").formatted(Formatting.BLUE)));
        tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.game_console").formatted(Formatting.BLUE)));
        super.appendTooltip(stack, world, tooltip, options);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) return ActionResult.SUCCESS;
        world.setBlockState(pos, handleTelevisionUse(state, world, pos, player));
        return ActionResult.SUCCESS;
    }
}