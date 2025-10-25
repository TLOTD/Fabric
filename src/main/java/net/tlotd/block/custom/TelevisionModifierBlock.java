package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
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
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;
import net.tlotd.sound.ModSounds;
import net.tlotd.util.ModTags;
import net.tlotd.util.TelevisionSignalRegistry;
import net.tlotd.util.VideoGameRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

import static net.tlotd.block.custom.TelevisionBlock.*;

public class TelevisionModifierBlock extends Block {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public TelevisionModifierBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
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

    public static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);
    public static final VoxelShape VCR = Block.createCuboidShape(1, 0, 1, 15, 6.5, 15);
    public static final VoxelShape CONSOLE = Block.createCuboidShape(1, 0, 1, 15, 5, 15);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.getBlock().equals(ModBlocks.VIDEOCASSETTE_RECORDER)) {
            return VCR;
        } else if (state.getBlock().equals(ModBlocks.GAME_CONSOLE)) {
            return CONSOLE;
        } else {
            return SHAPE;
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("block.tlotd.videocassette_recorder.tooltip").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(" ").append(Text.translatable("block.minecraft.chiseled_bookshelf").formatted(Formatting.BLUE)));
        super.appendTooltip(stack, world, tooltip, options);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        if (state.getBlock().equals(ModBlocks.VIDEOCASSETTE_RECORDER)) {
            return ModBlocks.VIDEOCASSETTE_RECORDER.asItem().getDefaultStack();
        } else if (state.getBlock().equals(ModBlocks.GAME_CONSOLE)) {
            return ModBlocks.GAME_CONSOLE.asItem().getDefaultStack();
        } else {
            return Blocks.CHISELED_BOOKSHELF.asItem().getDefaultStack();
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        BlockPos tvPos = pos.up();
        BlockState tvState = world.getBlockState(tvPos);
        boolean tvOn = tvState.isIn(ModTags.Blocks.TELEVISIONS_ON);
        if ((state.isOf(ModBlocks.VIDEOCASSETTE_RECORDER) || state.isOf(ModBlocks.VIDEOCASSETTE_RECORDER_BOOKSHELF) || state.isOf(ModBlocks.MEDIA_SYSTEM_BOOKSHELF)) && stack.isIn(ModTags.Items.VHS_CASSETTES) && tvOn) {
            if (!world.isClient) {
                Optional<TelevisionSignalRegistry.SignalEntry> match = TelevisionSignalRegistry.findBySignal(Registries.ITEM.getId(stack.getItem()));
                if (match.isPresent()) {
                    TelevisionSignalRegistry.SignalEntry entry = match.get();
                    BlockState newState = entry.onBlock().getStateWithProperties(tvState)
                            .with(CHANNEL, entry.channel());
                    world.setBlockState(tvPos, newState, 3);
                } else if (stack.isOf(ModItems.VHS_CASSETTE)) {
                        Optional<TelevisionSignalRegistry.SignalEntry> currentEntry =
                                TelevisionSignalRegistry.getAll().stream()
                                        .filter(e -> e.onBlock() == tvState.getBlock())
                                        .findFirst();
                        stack.decrement(1);
                        if (currentEntry.isPresent()) {
                            Identifier signalId = currentEntry.get().signalItem();
                            Item recordedItem = Registries.ITEM.get(signalId);
                            ItemStack recorded = new ItemStack(recordedItem);
                            player.giveItemStack(recorded);
                        } else {
                            player.giveItemStack(ModItems.VHS_CASSETTE_BROKEN.getDefaultStack());
                        }
                    }
                player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
                world.playSound(null, pos, ModSounds.BLOCK_VIDEOCASSETTE_RECORDER, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
            return ActionResult.SUCCESS;
        } else if ((state.isOf(ModBlocks.GAME_CONSOLE) || state.isOf(ModBlocks.GAME_CONSOLE_BOOKSHELF) || state.isOf(ModBlocks.MEDIA_SYSTEM_BOOKSHELF)) && stack.isIn(ModTags.Items.GAME_CARTRIDGES) && tvOn) {
            if (!world.isClient) {
                Optional<VideoGameRegistry.SignalEntry> match = VideoGameRegistry.findBySignal(Registries.ITEM.getId(stack.getItem()));
                if (match.isPresent()) {
                    VideoGameRegistry.SignalEntry entry = match.get();
                    BlockState newState = entry.tvBlock().getStateWithProperties(tvState)
                            .with(CHANNEL, entry.gameID());
                    world.setBlockState(tvPos, newState, 3);
                }
                player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
                world.playSound(null, pos, ModSounds.BLOCK_VIDEOCASSETTE_RECORDER, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
            return ActionResult.SUCCESS;
        } else if (player.isSneaking() && state.isIn(TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "bookshelves")))) {
            if (!world.isClient) {
                if (state.getBlock() == ModBlocks.MEDIA_SYSTEM_BOOKSHELF) {
                    world.setBlockState(pos, ModBlocks.VIDEOCASSETTE_RECORDER_BOOKSHELF.getDefaultState().with(FACING, state.get(FACING)));
                } else {
                    world.setBlockState(pos, Blocks.CHISELED_BOOKSHELF.getDefaultState().with(FACING, state.get(FACING)));
                }
                if (state.getBlock() == ModBlocks.VIDEOCASSETTE_RECORDER_BOOKSHELF) {
                    player.giveItemStack(ModBlocks.VIDEOCASSETTE_RECORDER.asItem().getDefaultStack());
                } else {
                    player.giveItemStack(ModBlocks.GAME_CONSOLE.asItem().getDefaultStack());
                }
                world.playSound(null, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}