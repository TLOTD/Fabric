package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.tlotd.block.entity.KeycardReaderBlockEntity;
import net.tlotd.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class KeycardReaderBlock extends ButtonBlock implements BlockEntityProvider {

    public static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(4, 2, 0, 12, 14, 1);
    public static final VoxelShape WEST_SHAPE = Block.createCuboidShape(15, 2, 4, 16, 14, 12);
    public static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(4, 2, 15, 12, 14, 16);
    public static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0, 2, 4, 1, 14, 12);
    public static final VoxelShape DOWN_SHAPE = Block.createCuboidShape(2, 0, 4, 14, 1, 12);
    public static final VoxelShape DOWN_SHAPE_2 = Block.createCuboidShape(4, 0, 2, 12, 1, 14);
    public static final VoxelShape UP_SHAPE = Block.createCuboidShape(2, 15, 4, 14, 16, 12);
    public static final VoxelShape UP_SHAPE_2 = Block.createCuboidShape(4, 15, 2, 12, 16, 14);

    public KeycardReaderBlock(Settings settings, BlockSetType blockSetType, int pressTicks, boolean wooden) {
        super(settings, blockSetType, pressTicks, wooden);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!world.isClient && stack.isOf(ModItems.KEYCARD) && blockEntity instanceof KeycardReaderBlockEntity keycardReaderBlockEntity) {
            if (!stack.hasNbt()) {
                player.sendMessage(Text.translatable("block.tlotd.keycard_reader.no_password"));
                world.playSound(null, pos, SoundEvents.ENTITY_VILLAGER_NO, SoundCategory.BLOCKS, 1.0f, 1.0f);
                return ActionResult.SUCCESS;
            }
            else {
                if (keycardReaderBlockEntity.password.isEmpty()) {
                    keycardReaderBlockEntity.password = stack.getNbt().getString("password");
                    keycardReaderBlockEntity.markDirty();
                    world.playSound(null, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    player.sendMessage(Text.translatable("block.tlotd.keycard_reader.password_set"));
                    return ActionResult.SUCCESS;
                }
                else {
                    if (keycardReaderBlockEntity.password.equals(stack.getNbt().getString("password"))) {
                        if (state.get(POWERED)) {
                            return ActionResult.CONSUME;
                        }
                        this.powerOn(state, world, pos);
                        this.playClickSound(player, world, pos, true);
                        world.emitGameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
                        world.playSound(null, pos, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        return ActionResult.SUCCESS;
                    } else {
                        world.playSound(null, pos, SoundEvents.ENTITY_VILLAGER_NO, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        return ActionResult.SUCCESS;
                    }
                }
            }
        }
        return ActionResult.FAIL;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACE)) {
            case FLOOR: {
                switch (state.get(FACING).getAxis()) {
                    case X: {
                        return DOWN_SHAPE;
                    }
                }
                return DOWN_SHAPE_2;
            }
            case WALL: {
                switch (state.get(FACING)) {
                    case EAST: {
                        return EAST_SHAPE;
                    }
                    case WEST: {
                        return WEST_SHAPE;
                    }
                    case SOUTH: {
                        return SOUTH_SHAPE;
                    }
                }
                return NORTH_SHAPE;
            }
        }
        switch (state.get(FACING).getAxis()) {
            case X: {
                return UP_SHAPE;
            }
        }
        return UP_SHAPE_2;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new KeycardReaderBlockEntity(pos, state);
    }
}
