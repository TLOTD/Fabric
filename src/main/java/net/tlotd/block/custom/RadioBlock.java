package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
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
import net.tlotd.sound.ModSounds;
import net.tlotd.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.tlotd.block.custom.DataSaverBlock.*;
import static net.tlotd.block.custom.DataSaverBlock.FREQUENCY_4;

public class RadioBlock extends Block {

    public static final IntProperty FREQUENCY = IntProperty.of("frequency", 0, 4);

    public static final IntProperty WOOD_TYPE = IntProperty.of("wood_type", 1, 13);

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public static final DirectionProperty FACING = FacingBlock.FACING;

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER))
                .with(FREQUENCY, 0)
                .with(WOOD_TYPE, 1);
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
        builder.add(FACING, WATERLOGGED, FREQUENCY, WOOD_TYPE);
    }

    public RadioBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false).with(FREQUENCY, 0).with(WOOD_TYPE, 1));
    }

    public static final VoxelShape Z_SHAPE = Block.createCuboidShape(4.0, 0.0, 0.0, 12.0, 11.0, 16.0);
    public static final VoxelShape X_SHAPE = Block.createCuboidShape(0.0, 0.0, 4.0, 16.0, 11.0, 12.0);

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
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        if (!ModConfigs.ALL_SIGNALS_UNLOCKED) {
            tooltip.add(Text.literal(""));
            tooltip.add(Text.translatable("block.tlotd.radio.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.radio.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.signal_transmitter").formatted(Formatting.BLUE)));
        }
        super.appendTooltip(stack, world, tooltip, options);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return ModBlocks.RADIO.asItem().getDefaultStack();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockPos zero = new BlockPos(0,world.getBottomY(),0);
        if (player.isSneaking()) {
            MinecraftClient.getInstance().getSoundManager().stopAll();
            if (!world.isClient) {
                if (state.getBlock().equals(ModBlocks.RADIO)) {
                    if (ModConfigs.ALL_SIGNALS_UNLOCKED) {
                        world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state));
                        if (state.get(FREQUENCY) == 1) {
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.1"), true);
                            world.playSound(null, pos, ModSounds.RADIO_FREQUENCY_1, SoundCategory.RECORDS, 1.0f, 1.0f);
                        } else if (state.get(FREQUENCY) == 2) {
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.2"), true);
                            world.playSound(null, pos, ModSounds.RADIO_FREQUENCY_2, SoundCategory.RECORDS, 1.0f, 1.0f);
                        } else if (state.get(FREQUENCY) == 3) {
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.3"), true);
                            world.playSound(null, pos, ModSounds.RADIO_FREQUENCY_3, SoundCategory.RECORDS, 1.0f, 1.0f);
                        } else if (state.get(FREQUENCY) == 4) {
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.4"), true);
                            world.playSound(null, pos, ModSounds.RADIO_FREQUENCY_4, SoundCategory.RECORDS, 1.0f, 1.0f);
                        }
                    } else if (world.getBlockState(zero).getBlock().equals(ModBlocks.BEDROCK)) {
                        if (world.getBlockState(zero).get(FREQUENCY_1) && state.get(FREQUENCY) <= 1) {
                            world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state).with(FREQUENCY, 1));
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.1"), true);
                            world.playSound(null, pos, ModSounds.RADIO_FREQUENCY_1, SoundCategory.RECORDS, 1.0f, 1.0f);
                        } else if (world.getBlockState(zero).get(FREQUENCY_2) && state.get(FREQUENCY) <= 2) {
                            world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state).with(FREQUENCY, 2));
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.2"), true);
                            world.playSound(null, pos, ModSounds.RADIO_FREQUENCY_2, SoundCategory.RECORDS, 1.0f, 1.0f);
                        } else if (world.getBlockState(zero).get(FREQUENCY_3) && state.get(FREQUENCY) <= 3) {
                            world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state).with(FREQUENCY, 3));
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.3"), true);
                            world.playSound(null, pos, ModSounds.RADIO_FREQUENCY_3, SoundCategory.RECORDS, 1.0f, 1.0f);
                        } else if (world.getBlockState(zero).get(FREQUENCY_4) && state.get(FREQUENCY) <= 4) {
                            world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state).with(FREQUENCY, 4));
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.4"), true);
                            world.playSound(null, pos, ModSounds.RADIO_FREQUENCY_4, SoundCategory.RECORDS, 1.0f, 1.0f);
                        } else {
                            world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state).with(FREQUENCY, 0));
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.0"), true);
                        }
                    } else {
                        world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state).with(FREQUENCY, 0));
                        player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.0"), true);
                    }
                } else {
                    MinecraftClient.getInstance().getSoundManager().stopAll();
                    world.setBlockState(pos, ModBlocks.RADIO.getStateWithProperties(state));
                }
            }
            world.playSound(null, pos, ModSounds.BLOCK_RADIO_SWITCH_FREQUENCY, SoundCategory.BLOCKS, 1.0f, 1.0f);
            return ActionResult.SUCCESS;
        } else {
            if (player.getMainHandStack().isIn(ItemTags.LOGS) || player.getMainHandStack().isIn(ItemTags.BAMBOO_BLOCKS) || player.getMainHandStack().isIn(ModTags.Items.GINKGO_LOGS)){
                if (!world.isClient) {
                    if (player.getMainHandStack().isIn(ItemTags.OAK_LOGS)) {
                        world.setBlockState(pos, world.getBlockState(pos).with(WOOD_TYPE, 1));
                    } else if (player.getMainHandStack().isIn(ItemTags.SPRUCE_LOGS)) {
                        world.setBlockState(pos, world.getBlockState(pos).with(WOOD_TYPE, 2));
                    } else if (player.getMainHandStack().isIn(ItemTags.BIRCH_LOGS)) {
                        world.setBlockState(pos, world.getBlockState(pos).with(WOOD_TYPE, 3));
                    } else if (player.getMainHandStack().isIn(ItemTags.JUNGLE_LOGS)) {
                        world.setBlockState(pos, world.getBlockState(pos).with(WOOD_TYPE, 4));
                    } else if (player.getMainHandStack().isIn(ItemTags.ACACIA_LOGS)) {
                        world.setBlockState(pos, world.getBlockState(pos).with(WOOD_TYPE, 5));
                    } else if (player.getMainHandStack().isIn(ItemTags.DARK_OAK_LOGS)) {
                        world.setBlockState(pos, world.getBlockState(pos).with(WOOD_TYPE, 6));
                    } else if (player.getMainHandStack().isIn(ItemTags.MANGROVE_LOGS)) {
                        world.setBlockState(pos, world.getBlockState(pos).with(WOOD_TYPE, 7));
                    } else if (player.getMainHandStack().isIn(ItemTags.CHERRY_LOGS)) {
                        world.setBlockState(pos, world.getBlockState(pos).with(WOOD_TYPE, 8));
                    } else if (player.getMainHandStack().isIn(ModTags.Items.GINKGO_LOGS)) {
                        world.setBlockState(pos, world.getBlockState(pos).with(WOOD_TYPE, 10));
                    } else if (player.getMainHandStack().isIn(ItemTags.BAMBOO_BLOCKS)) {
                        world.setBlockState(pos, world.getBlockState(pos).with(WOOD_TYPE, 11));
                    } else if (player.getMainHandStack().isIn(ItemTags.CRIMSON_STEMS)) {
                        world.setBlockState(pos, world.getBlockState(pos).with(WOOD_TYPE, 12));
                    } else if (player.getMainHandStack().isIn(ItemTags.WARPED_STEMS)) {
                        world.setBlockState(pos, world.getBlockState(pos).with(WOOD_TYPE, 13));
                    }
                }
                world.playSound(null, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                return ActionResult.SUCCESS;
            } else if (state.getBlock().equals(ModBlocks.RADIO_ON)) {
                MinecraftClient.getInstance().getSoundManager().stopAll();
                if (!world.isClient) {
                    if (ModConfigs.ALL_SIGNALS_UNLOCKED) {
                        if (state.get(FREQUENCY) < 4) {
                            world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state.with(FREQUENCY, state.get(FREQUENCY)+1)));
                        } else {
                            world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state.with(FREQUENCY, 1)));
                        }
                        if (state.get(FREQUENCY) == 1) {
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.2"), true);
                            world.playSound(null, pos, ModSounds.RADIO_FREQUENCY_2, SoundCategory.RECORDS, 1.0f, 1.0f);
                        } else if (state.get(FREQUENCY) == 2) {
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.3"), true);
                            world.playSound(null, pos, ModSounds.RADIO_FREQUENCY_3, SoundCategory.RECORDS, 1.0f, 1.0f);
                        } else if (state.get(FREQUENCY) == 3) {
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.4"), true);
                            world.playSound(null, pos, ModSounds.RADIO_FREQUENCY_4, SoundCategory.RECORDS, 1.0f, 1.0f);
                        } else if (state.get(FREQUENCY) == 4) {
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.1"), true);
                            world.playSound(null, pos, ModSounds.RADIO_FREQUENCY_1, SoundCategory.RECORDS, 1.0f, 1.0f);
                        }
                    } else if (world.getBlockState(zero).getBlock().equals(ModBlocks.BEDROCK)) {
                        if (world.getBlockState(zero).get(FREQUENCY_1) && state.get(FREQUENCY) < 1) {
                            world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state).with(FREQUENCY,1));
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.1"), true);
                            world.playSound(null, pos, ModSounds.RADIO_FREQUENCY_1, SoundCategory.RECORDS, 1.0f, 1.0f);
                        } else if (world.getBlockState(zero).get(FREQUENCY_2) && state.get(FREQUENCY) < 2) {
                            world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state).with(FREQUENCY,2));
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.2"), true);
                            world.playSound(null, pos, ModSounds.RADIO_FREQUENCY_2, SoundCategory.RECORDS, 1.0f, 1.0f);
                        } else if (world.getBlockState(zero).get(FREQUENCY_3) && state.get(FREQUENCY) < 3) {
                            world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state).with(FREQUENCY,3));
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.3"), true);
                            world.playSound(null, pos, ModSounds.RADIO_FREQUENCY_3, SoundCategory.RECORDS, 1.0f, 1.0f);
                        } else if (world.getBlockState(zero).get(FREQUENCY_4) && state.get(FREQUENCY) < 4) {
                            world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state).with(FREQUENCY,4));
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.4"), true);
                            world.playSound(null, pos, ModSounds.RADIO_FREQUENCY_4, SoundCategory.RECORDS, 1.0f, 1.0f);
                        } else {
                            world.setBlockState(pos, ModBlocks.RADIO_ON.getStateWithProperties(state).with(FREQUENCY,0));
                            player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.0"), true);
                        }
                    } else {
                        player.sendMessage(Text.translatable("messages.tlotd.radio.frequency.0"), true);
                    }
                }
                world.playSound(null, pos, ModSounds.BLOCK_RADIO_SWITCH_FREQUENCY, SoundCategory.BLOCKS, 1.0f, 1.0f);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        MinecraftClient.getInstance().getSoundManager().stopAll();
        world.playSound(null, pos, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
        super.onBreak(world, pos, state, player);
    }
}