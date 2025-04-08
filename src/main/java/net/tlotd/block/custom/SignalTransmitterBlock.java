package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
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
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;
import net.tlotd.sound.ModSounds;
import net.tlotd.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.tlotd.block.custom.DataSaverBlock.*;

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
        tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.signal_transmitter_antenna").formatted(Formatting.BLUE)));
        tooltip.add(Text.literal(" ").append(Text.translatable("block.minecraft.lightning_rod").formatted(Formatting.BLUE)));
        super.appendTooltip(stack, world, tooltip, options);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            BlockPos zero = new BlockPos(0,world.getBottomY(),0);
            if (player.getMainHandStack().isIn(ModTags.Items.VHS_CASSETTES) || player.getMainHandStack().isIn(ModTags.Items.MUSIC_DISCS)) {
                if(!world.getBlockState(zero).getBlock().equals(ModBlocks.BEDROCK)){world.setBlockState(zero,ModBlocks.BEDROCK.getDefaultState());} //ensures data block is placed beforehand
                if(player.getStackInHand(hand).isOf(ModItems.VHS_CASSETTE_1)){if(world.getBlockState(zero).get(CHANNEL_1)){player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.removed"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_1,false));} else {player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.added"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_1,true));}}
                else if(player.getStackInHand(hand).isOf(ModItems.VHS_CASSETTE_2)){if(world.getBlockState(zero).get(CHANNEL_2)){player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.removed"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_2,false));} else {player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.added"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_2,true));}}
                else if(player.getStackInHand(hand).isOf(ModItems.VHS_CASSETTE_3)){if(world.getBlockState(zero).get(CHANNEL_3)){player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.removed"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_3,false));} else {player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.added"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_3,true));}}
                else if(player.getStackInHand(hand).isOf(ModItems.VHS_CASSETTE_4)){if(world.getBlockState(zero).get(CHANNEL_4)){player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.removed"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_4,false));} else {player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.added"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_4,true));}}
                else if(player.getStackInHand(hand).isOf(ModItems.VHS_CASSETTE_5)){if(world.getBlockState(zero).get(CHANNEL_5)){player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.removed"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_5,false));} else {player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.added"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_5,true));}}
                else if(player.getStackInHand(hand).isOf(ModItems.VHS_CASSETTE_6)){if(world.getBlockState(zero).get(CHANNEL_6)){player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.removed"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_6,false));} else {player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.added"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_6,true));}}
                else if(player.getStackInHand(hand).isOf(ModItems.VHS_CASSETTE_7)){if(world.getBlockState(zero).get(CHANNEL_7)){player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.removed"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_7,false));} else {player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.added"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_7,true));}}
                else if(player.getStackInHand(hand).isOf(ModItems.VHS_CASSETTE_8)){if(world.getBlockState(zero).get(CHANNEL_8)){player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.removed"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_8,false));} else {player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.added"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_8,true));}}
                else if(player.getStackInHand(hand).isOf(ModItems.VHS_CASSETTE_9)){if(world.getBlockState(zero).get(CHANNEL_9)){player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.removed"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_9,false));} else {player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.added"),true);world.setBlockState(zero,world.getBlockState(zero).with(CHANNEL_9,true));}}
                else if(player.getStackInHand(hand).isOf(ModItems.MUSIC_DISC_1)){if(world.getBlockState(zero).get(FREQUENCY_1)){player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.removed"),true);world.setBlockState(zero,world.getBlockState(zero).with(FREQUENCY_1,false));} else {player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.added"),true);world.setBlockState(zero,world.getBlockState(zero).with(FREQUENCY_1,true));}}
                else if(player.getStackInHand(hand).isOf(ModItems.MUSIC_DISC_2)){if(world.getBlockState(zero).get(FREQUENCY_2)){player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.removed"),true);world.setBlockState(zero,world.getBlockState(zero).with(FREQUENCY_2,false));} else {player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.added"),true);world.setBlockState(zero,world.getBlockState(zero).with(FREQUENCY_2,true));}}
                else if(player.getStackInHand(hand).isOf(ModItems.MUSIC_DISC_3)){if(world.getBlockState(zero).get(FREQUENCY_3)){player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.removed"),true);world.setBlockState(zero,world.getBlockState(zero).with(FREQUENCY_3,false));} else {player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.added"),true);world.setBlockState(zero,world.getBlockState(zero).with(FREQUENCY_3,true));}}
                else if(player.getStackInHand(hand).isOf(ModItems.MUSIC_DISC_4)){if(world.getBlockState(zero).get(FREQUENCY_4)){player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.removed"),true);world.setBlockState(zero,world.getBlockState(zero).with(FREQUENCY_4,false));} else {player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.added"),true);world.setBlockState(zero,world.getBlockState(zero).with(FREQUENCY_4,true));}}
                world.playSound(null, pos, ModSounds.BLOCK_VIDEOCASSETTE_RECORDER, SoundCategory.BLOCKS, 1.0f, 1.0f);
            } else {
                player.sendMessage(Text.translatable("block.tlotd.signal_transmitter.list"));
                if(world.getBlockState(zero).get(CHANNEL_1)){player.sendMessage(Text.translatable("item.tlotd.vhs_cassette_1.desc").formatted(Formatting.GRAY));}
                if(world.getBlockState(zero).get(CHANNEL_2)){player.sendMessage(Text.translatable("item.tlotd.vhs_cassette_2.desc").formatted(Formatting.GRAY));}
                if(world.getBlockState(zero).get(CHANNEL_3)){player.sendMessage(Text.translatable("item.tlotd.vhs_cassette_3.desc").formatted(Formatting.GRAY));}
                if(world.getBlockState(zero).get(CHANNEL_4)){player.sendMessage(Text.translatable("item.tlotd.vhs_cassette_4.desc").formatted(Formatting.GRAY));}
                if(world.getBlockState(zero).get(CHANNEL_5)){player.sendMessage(Text.translatable("item.tlotd.vhs_cassette_5.desc").formatted(Formatting.GRAY));}
                if(world.getBlockState(zero).get(CHANNEL_6)){player.sendMessage(Text.translatable("item.tlotd.vhs_cassette_6.desc").formatted(Formatting.GRAY));}
                if(world.getBlockState(zero).get(CHANNEL_7)){player.sendMessage(Text.translatable("item.tlotd.vhs_cassette_7.desc").formatted(Formatting.GRAY));}
                if(world.getBlockState(zero).get(CHANNEL_8)){player.sendMessage(Text.translatable("item.tlotd.vhs_cassette_8.desc").formatted(Formatting.GRAY));}
                if(world.getBlockState(zero).get(CHANNEL_9)){player.sendMessage(Text.translatable("item.tlotd.vhs_cassette_9.desc").formatted(Formatting.GRAY));}
                if(world.getBlockState(zero).get(FREQUENCY_1)){player.sendMessage(Text.translatable("item.tlotd.music_disc_1.desc").formatted(Formatting.GRAY));}
                if(world.getBlockState(zero).get(FREQUENCY_2)){player.sendMessage(Text.translatable("item.tlotd.music_disc_2.desc").formatted(Formatting.GRAY));}
                if(world.getBlockState(zero).get(FREQUENCY_3)){player.sendMessage(Text.translatable("item.tlotd.music_disc_3.desc").formatted(Formatting.GRAY));}
                if(world.getBlockState(zero).get(FREQUENCY_4)){player.sendMessage(Text.translatable("item.tlotd.music_disc_4.desc").formatted(Formatting.GRAY));}
                world.playSound(null, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
        }
        return ActionResult.SUCCESS;
    }
}
