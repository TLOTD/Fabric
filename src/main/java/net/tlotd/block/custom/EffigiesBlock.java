package net.tlotd.block.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.tlotd.block.ModBlocks;
import net.tlotd.particle.ModParticles;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.tlotd.api.TlotdAPI.enlightened;
import static net.tlotd.block.custom.ModCauldronBlock.LEVEL;

public class EffigiesBlock extends Block {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

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
                if (!player.hasStatusEffect(StatusEffects.WEAKNESS)) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60,0,true,false));
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
        float f = random.nextFloat();
        if (f <= 0.25F) {
            long time = world.getTimeOfDay() % 24000L;
            if (time >= 12800 && time < 23200) {
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
        }
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
            tooltip.add(Text.translatable("block.tlotd.effigies.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.effigies.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.effigies.tooltip_3").formatted(Formatting.GRAY));
        } else {
            if (enlightened(player) >= 10) {
                tooltip.add(Text.literal("\uE001 ").setStyle(style.withFont(TOOLTIP_FONT_ID)).append(Text.translatable("item.tlotd.desc_enlightened").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GOLD))));
            } else {
                tooltip.add(Text.literal("\uE000 ").setStyle(style.withFont(TOOLTIP_FONT_ID)).append(Text.translatable("item.tlotd.desc_not_enlightened").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.DARK_GRAY))));
            }
            tooltip.add(Text.translatable("block.tlotd.effigies.tooltip").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.effigies.tooltip_2").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.effigies.tooltip_3").setStyle(style.withFont(ILLAGER_FONT_ID)).formatted(Formatting.GRAY));
        }
        tooltip.add(Text.translatable("item.tlotd.desc_occult").formatted(Formatting.RED));
        super.appendTooltip(stack, world, tooltip, options);
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        float f = random.nextFloat();
        if (f <= 0.25F) {
            long time = world.getTimeOfDay() % 24000L;
            if (time >= 12800 && time < 23200) {
                createParticle(world, pos, state);
            }
        }
    }

    private static void createParticle(World world, BlockPos pos, BlockState state) {
        Random random = world.random;
        Vec3d offset = state.getModelOffset(world, pos);
        double x = pos.getX() + 0.15 + random.nextDouble() * 0.70 + offset.x;
        double y = pos.getY() + 0.25 + offset.y;
        double z = pos.getZ() + 0.15 + random.nextDouble() * 0.70 + offset.z;
        world.addParticle(
                ModParticles.DRIPPING_BLOOD, x, y, z, 0.0, 0.0, 0.0
        );
    }
}
