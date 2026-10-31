package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.block.entity.TeleporterBlockEntity;
import net.tlotd.item.ModItems;
import net.tlotd.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class TeleporterBlock extends Block implements BlockEntityProvider {

    public static final BooleanProperty LINKED = BooleanProperty.of("linked");

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LINKED);
    }

    public TeleporterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LINKED, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(LINKED, false);

    }

    public static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 16, 2, 16), Block.createCuboidShape(1, 2, 1, 15, 4, 15));

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
        return new TeleporterBlockEntity(pos, state);
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (state.get(LINKED) && blockEntity instanceof TeleporterBlockEntity teleporter && teleporter.pos_y != Integer.MAX_VALUE) {
                BlockPos pos2 = teleporter.relative ? pos.add(teleporter.pos_x, teleporter.pos_y, teleporter.pos_z) : new BlockPos(teleporter.pos_x, teleporter.pos_y, teleporter.pos_z);
                if (world.getBlockState(pos2).isOf(ModBlocks.TELEPORTER)) {
                    world.setBlockState(pos2, ModBlocks.TELEPORTER.getDefaultState(), Block.NOTIFY_ALL);
                }
            }
            playSound(world, pos, SoundEvents.BLOCK_STONE_BREAK);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world.isClient || !entity.isSneaking() || !state.get(LINKED)) {
            return;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!(blockEntity instanceof TeleporterBlockEntity teleporter) || teleporter.pos_y == Integer.MAX_VALUE) {
            return;
        }
        BlockPos pos2 = teleporter.relative ? pos.add(teleporter.pos_x, teleporter.pos_y, teleporter.pos_z) : new BlockPos(teleporter.pos_x, teleporter.pos_y, teleporter.pos_z);
        if (!teleporter.one_way && !world.getBlockState(pos2).isOf(ModBlocks.TELEPORTER)) {
            return;
        }
        double targetX = pos2.getX() + 0.5;
        double targetY = pos2.getY() + 0.25;
        double targetZ = pos2.getZ() + 0.5;
        entity.setSneaking(false);
        playSound(world, pos, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT);
        if (world instanceof ServerWorld serverWorld) {
            entity.teleport(serverWorld, targetX, targetY, targetZ, Set.of(), entity.getYaw(), entity.getPitch());
        }
        playSound(world, pos2, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (world.isClient) {
            return stack.isOf(ModItems.FLASH_DRIVE) ? ItemActionResult.SUCCESS : ItemActionResult.FAIL;
        }
        if (!stack.isOf(ModItems.FLASH_DRIVE) || state.get(LINKED) || !(blockEntity instanceof TeleporterBlockEntity teleporter)) {
            return ItemActionResult.FAIL;
        }
        NbtCompound nbt = stack.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT).copyNbt();
        if (!nbt.contains("pos_y")) {
            player.sendMessage(Text.translatable("block.tlotd.teleporter.no_destination_on_drive"), true);
            playSound(world, pos, SoundEvents.ENTITY_VILLAGER_NO);
            return ItemActionResult.SUCCESS;
        }
        BlockPos pos2 = new BlockPos(nbt.getInt("pos_x"), nbt.getInt("pos_y"), nbt.getInt("pos_z"));
        if (pos.equals(pos2)) {
            player.sendMessage(Text.translatable("block.tlotd.teleporter.destination_same"), true);
            playSound(world, pos, SoundEvents.ENTITY_VILLAGER_NO);
            return ItemActionResult.SUCCESS;
        }
        if (!teleporter.one_way && !world.getBlockState(pos2).isOf(ModBlocks.TELEPORTER)) {
            player.sendMessage(Text.translatable("block.tlotd.teleporter.none_at_destination"), true);
            playSound(world, pos, SoundEvents.ENTITY_VILLAGER_NO);
            return ItemActionResult.SUCCESS;
        }
        BlockEntity blockEntity2 = world.getBlockEntity(pos2);
        if (!(blockEntity2 instanceof TeleporterBlockEntity teleporter2) || world.getBlockState(pos2).get(LINKED)) {
            player.sendMessage(Text.translatable("block.tlotd.teleporter.destination_in_use"), true);
            playSound(world, pos, SoundEvents.ENTITY_VILLAGER_NO);
            return ItemActionResult.SUCCESS;
        }
        world.setBlockState(pos, state.with(LINKED, true));
        BlockState state2 = world.getBlockState(pos2);
        world.setBlockState(pos2, state2.with(LINKED, true));
        teleporter.pos_x = nbt.getInt("pos_x");
        teleporter.pos_y = nbt.getInt("pos_y");
        teleporter.pos_z = nbt.getInt("pos_z");
        teleporter.markDirty();
        teleporter2.pos_x = pos.getX();
        teleporter2.pos_y = pos.getY();
        teleporter2.pos_z = pos.getZ();
        teleporter2.markDirty();
        player.sendMessage(Text.translatable("block.tlotd.teleporter.connected"), true);
        playSound(world, pos, ModSounds.BLOCK_KEYCARD_READER_PLING);
        return ItemActionResult.SUCCESS;
    }

    private void playSound(World world, BlockPos pos, SoundEvent sound) {
        world.playSound(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, sound, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }
}