package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
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
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;
import net.tlotd.sound.ModSounds;
import net.tlotd.util.ModTags;
import net.tlotd.util.VideoGameRegistry;

import java.util.Optional;

import static net.tlotd.block.custom.KeycardProgrammerBlock.ON;

public class ComputerBlock extends Block {

    public static final IntProperty SCREEN = IntProperty.of("screen", 0, 127);
    public static final BooleanProperty MENU = BooleanProperty.of("menu");
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing()).with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER)).with(SCREEN, 0).with(MENU, false);
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
        builder.add(FACING, WATERLOGGED, SCREEN, MENU);
    }

    public ComputerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false).with(SCREEN, 0).with(MENU, false));
    }

    public static final VoxelShape NORTH_SHAPE = VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 16, 13, 12), Block.createCuboidShape(0, 0, 12.5, 16, 1, 16));
    public static final VoxelShape WEST_SHAPE = VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 12, 13, 16), Block.createCuboidShape(12.5, 0, 0, 16, 1, 16));
    public static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(Block.createCuboidShape(0, 0, 4, 16, 13, 16), Block.createCuboidShape(0, 0, 0, 16, 1, 3.5));
    public static final VoxelShape EAST_SHAPE = VoxelShapes.union(Block.createCuboidShape(4, 0, 0, 16, 13, 16), Block.createCuboidShape(0, 0, 0, 3.5, 1, 16));

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case EAST -> EAST_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> NORTH_SHAPE;
        };
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public MutableText getName() {
        return Text.translatable("block.tlotd.computer");
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return ModBlocks.COMPUTER.asItem().getDefaultStack();
    }

    private static void TurnOffBlocks(World world, BlockPos pos) {
        if (world.getBlockState(pos.north()).isIn(ModTags.Blocks.COMPUTER_ACCESSORIES)) {
            world.setBlockState(pos.north(), world.getBlockState(pos.north()).with(ON, false));
        }
        if (world.getBlockState(pos.east()).isIn(ModTags.Blocks.COMPUTER_ACCESSORIES)) {
            world.setBlockState(pos.east(), world.getBlockState(pos.east()).with(ON, false));
        }
        if (world.getBlockState(pos.south()).isIn(ModTags.Blocks.COMPUTER_ACCESSORIES)) {
            world.setBlockState(pos.south(), world.getBlockState(pos.south()).with(ON, false));
        }
        if (world.getBlockState(pos.west()).isIn(ModTags.Blocks.COMPUTER_ACCESSORIES)) {
            world.setBlockState(pos.west(), world.getBlockState(pos.west()).with(ON, false));
        }
    }

    public static BlockState handleComputerUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand) {
        return handleComputerUse(state, world, pos, player, player.getStackInHand(hand));
    }

    public static BlockState handleComputerUse(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack) {
        if (world.isClient) return state;
        BlockState newState = state;
        if (player.isSneaking()) {
            if (state.isIn(ModTags.Blocks.COMPUTERS_OFF)) {
                newState = ModBlocks.COMPUTER_ON.getStateWithProperties(state);
                for (Direction direction : Direction.Type.HORIZONTAL) {
                    BlockPos neighborPos = pos.offset(direction);
                    BlockState neighbor = world.getBlockState(neighborPos);
                    if (neighbor.isIn(ModTags.Blocks.COMPUTER_ACCESSORIES)) {
                        world.setBlockState(neighborPos, neighbor.with(ON, true));
                    }
                }
            } else {
                newState = ModBlocks.COMPUTER.getStateWithProperties(state).with(SCREEN, 0);
                TurnOffBlocks(world, pos);
            }
            world.playSound(null, pos, ModSounds.BLOCK_COMPUTER_INTERACT, SoundCategory.BLOCKS, 1.0f, 1.0f);
        } else if (state.isIn(ModTags.Blocks.COMPUTERS_ON)) {
            String app = getFlashDriveApp(stack);
            if (stack.isIn(ModTags.Items.GAME_CARTRIDGES) || (stack.isOf(ModItems.FLASH_DRIVE) && !app.isEmpty())) {
                Identifier id = stack.isIn(ModTags.Items.GAME_CARTRIDGES) ? Registries.ITEM.getId(stack.getItem()) : Identifier.tryParse(app);
                if (id != null) {
                    Optional<VideoGameRegistry.SignalEntry> match = VideoGameRegistry.findBySignal(id);
                    if (match.isPresent()) {
                        VideoGameRegistry.SignalEntry entry = match.get();
                        newState = entry.computerBlock().getStateWithProperties(world.getBlockState(pos)).with(SCREEN, entry.gameID());
                    }
                }
                player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
                world.playSound(null, pos, ModSounds.BLOCK_COMPUTER_INTERACT, SoundCategory.BLOCKS, 1.0f, 1.0f);
            } else {
                newState = state.cycle(MENU);
            }
        }
        return newState;
    }

    private static String getFlashDriveApp(ItemStack stack) {
        NbtComponent customData = stack.get(DataComponentTypes.CUSTOM_DATA);
        if (customData == null) {
            return "";
        }
        NbtCompound nbt = customData.copyNbt();
        return nbt.getString("app");
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
        TurnOffBlocks((World) world, pos);
    }


    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }
        BlockState newState = handleComputerUse(state, world, pos, player, player.getMainHandStack());
        if (newState != state) {
            world.setBlockState(pos, newState);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ItemActionResult.SUCCESS;
        }
        BlockState newState = handleComputerUse(state, world, pos, player, stack);
        if (newState != state) {
            world.setBlockState(pos, newState);
        }
        return ItemActionResult.SUCCESS;
    }
}