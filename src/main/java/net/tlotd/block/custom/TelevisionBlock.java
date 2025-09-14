package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
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
import net.tlotd.block.ModBlocks;
import net.tlotd.config.ModConfigs;
import net.tlotd.item.ModItems;
import net.tlotd.sound.ModSounds;
import net.tlotd.world.SignalTrackingArray;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TelevisionBlock extends Block {

    public static final IntProperty CHANNEL = IntProperty.of("channel", 0, 9);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = FacingBlock.FACING;

    private static String[] VHS_DISC_KEYS = null;
    private static String[] getVHSDiscKeys() {
        if (VHS_DISC_KEYS == null) {
            VHS_DISC_KEYS = new String[]{
                    ModItems.VHS_CASSETTE_BROKEN.getTranslationKey(), // channel 0
                    ModItems.VHS_CASSETTE_1.getTranslationKey(),
                    ModItems.VHS_CASSETTE_2.getTranslationKey(),
                    ModItems.VHS_CASSETTE_3.getTranslationKey(),
                    ModItems.VHS_CASSETTE_4.getTranslationKey(),
                    ModItems.VHS_CASSETTE_5.getTranslationKey(),
                    ModItems.VHS_CASSETTE_6.getTranslationKey(),
                    ModItems.VHS_CASSETTE_7.getTranslationKey(),
                    ModItems.VHS_CASSETTE_8.getTranslationKey(),
                    ModItems.VHS_CASSETTE_9.getTranslationKey()
            };
        }
        return VHS_DISC_KEYS;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER))
                .with(CHANNEL,0);
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
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(WATERLOGGED, false)
                .with(CHANNEL, 0));
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
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return ModBlocks.TELEVISION.asItem().getDefaultStack();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.literal(""));
        if (!ModConfigs.ALL_SIGNALS_UNLOCKED) {
            tooltip.add(Text.translatable("block.tlotd.television.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.signal_transmitter").formatted(Formatting.BLUE)));
        }
        tooltip.add(Text.translatable("block.tlotd.television.tooltip_2").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.videocassette_recorder").formatted(Formatting.BLUE)));
        super.appendTooltip(stack, world, tooltip, options);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.isSneaking()) {
            if (!world.isClient) {
                if (state.getBlock().equals(ModBlocks.TELEVISION)) {
                    ServerWorld serverWorld = (ServerWorld) world;
                    SignalTrackingArray tracker = SignalTrackingArray.get(serverWorld);
                    int channel = 0;
                    if (ModConfigs.ALL_SIGNALS_UNLOCKED || world.getBlockState(pos.up()).isOf(ModBlocks.INTERDIMENSIONAL_RECEIVER)) {
                        channel = state.get(CHANNEL);
                    } else if (tracker.hasAnySignals()) {
                        for (int i = 0; i <= 9; i++) {
                            if (tracker.hasSignal(getVHSDiscKeys()[i]) && state.get(CHANNEL) <= i) {
                                channel = i;
                                break;
                            }
                        }
                    }
                    BlockState newState = ModBlocks.TELEVISION_ON.getStateWithProperties(state).with(CHANNEL, channel);
                    world.setBlockState(pos, newState);
                    world.playSound(null, pos, ModSounds.BLOCK_TELEVISION_SWITCH_CHANNEL, SoundCategory.BLOCKS, 1f, 1f);
                } else if (state.getBlock().equals(ModBlocks.TELEVISION_ON)) {
                    world.setBlockState(pos, ModBlocks.TELEVISION.getStateWithProperties(state));
                    world.playSound(null, pos, ModSounds.BLOCK_TELEVISION_SWITCH_CHANNEL, SoundCategory.BLOCKS, 1f, 1f);
                }
            }
            return ActionResult.SUCCESS;
        }
        else if (state.getBlock().equals(ModBlocks.TELEVISION_ON)) {
            if (!world.isClient) {
                ServerWorld serverWorld = (ServerWorld) world;
                SignalTrackingArray tracker = SignalTrackingArray.get(serverWorld);
                int currentChannel = state.get(CHANNEL);
                int nextChannel = currentChannel;
                if (ModConfigs.ALL_SIGNALS_UNLOCKED || world.getBlockState(pos.up()).isOf(ModBlocks.INTERDIMENSIONAL_RECEIVER)) {
                    nextChannel = (currentChannel + 1) % 10;
                } else if (tracker.hasAnySignals()) {
                    for (int i = currentChannel + 1; i <= 9; i++) {
                        if (tracker.hasSignal(getVHSDiscKeys()[i])) {
                            nextChannel = i;
                            break;
                        }
                    }
                    if (nextChannel == currentChannel) nextChannel = 0;
                }
                if (nextChannel != currentChannel) {
                    BlockState newState = ModBlocks.TELEVISION_ON.getStateWithProperties(state).with(CHANNEL, nextChannel);
                    world.setBlockState(pos, newState);
                    world.playSound(null, pos, ModSounds.BLOCK_TELEVISION_SWITCH_CHANNEL, SoundCategory.BLOCKS, 1f, 1f);
                }
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}