package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.tlotd.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.tlotd.block.custom.BloodCauldronBlock.LEVEL;

public class EffigiesBlock extends Block {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public static final DirectionProperty FACING = FacingBlock.FACING;

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = Direction.UP;
        return (Block.sideCoversSmallSquare(world, pos.offset(direction), direction.getOpposite())|| world.getBlockState(pos.offset(direction)).isIn(BlockTags.LEAVES));
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return true;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient) {
            if (entity.isPlayer()) {
                ServerPlayerEntity player = (ServerPlayerEntity) entity;
                if (!player.hasStatusEffect(StatusEffects.BAD_OMEN) || !player.hasStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE)) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.BAD_OMEN, 60,0,true,false));
                }
            }
        }
        super.onEntityCollision(state, world, pos, entity);
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

    public EffigiesBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        int search_y;
        for(search_y = 0; search_y>=-10; search_y--){
            if((world.getBlockState(pos.add(0, search_y,0)).isOf(Blocks.CAULDRON)) || (world.getBlockState(pos.add(0, search_y,0)).isOf(ModBlocks.BLOOD_CAULDRON) && world.getBlockState(pos.add(0, search_y,0)).get(LEVEL) < 3)) {
                if (world.getBlockState(pos.add(0, search_y,0)).isOf(Blocks.CAULDRON)) {
                    world.setBlockState(pos.add(0, search_y,0), ModBlocks.BLOOD_CAULDRON.getStateWithProperties(state));
                } else if (world.getBlockState(pos.add(0, search_y,0)).isOf(ModBlocks.BLOOD_CAULDRON)) {
                    if (world.getBlockState(pos.add(0, search_y,0)).get(LEVEL) == 1) {
                        world.setBlockState(pos.add(0, search_y,0), ModBlocks.BLOOD_CAULDRON.getStateWithProperties(state).with(LEVEL,2));
                    } else if (world.getBlockState(pos.add(0, search_y,0)).get(LEVEL) == 2) {
                        world.setBlockState(pos.add(0, search_y,0), ModBlocks.BLOOD_CAULDRON.getStateWithProperties(state).with(LEVEL,3));
                    }
                }
                world.playSound(null, pos.add(0, search_y,0), SoundEvents.BLOCK_CHORUS_FLOWER_GROW, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.addParticle(ParticleTypes.DRIPPING_LAVA,pos.getX(), pos.getY(), pos.getZ(),0.0F, 0.5F, 0.0F);
            }
        }
    }

    public static final Identifier ILLAGER_FONT_ID = new Identifier("minecraft", "illageralt");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("block.tlotd.effigies.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.effigies.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.effigies.tooltip_3").formatted(Formatting.GRAY));
        } else {
            Style style = getName().getStyle();
            tooltip.add(Text.translatable("block.tlotd.effigies.tooltip").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.effigies.tooltip_2").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.effigies.tooltip_3").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
        }
        super.appendTooltip(stack, world, tooltip, options);
    }
}
