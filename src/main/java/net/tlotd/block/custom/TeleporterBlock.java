package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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
        return this.getDefaultState()
                .with(LINKED, false);

    }

    public static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 0, 0, 16, 2, 16),
            Block.createCuboidShape(1, 2, 1, 15, 4, 15)
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
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TeleporterBlockEntity(pos, state);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (state.get(LINKED) && blockEntity instanceof TeleporterBlockEntity teleporter) {
            if (teleporter.pos_y != 2147483647) {
                BlockPos pos2;
                if (teleporter.relative) {
                    pos2 = new BlockPos(pos.getX()+teleporter.pos_x,pos.getY()+teleporter.pos_y,pos.getZ()+teleporter.pos_z);
                } else {
                    pos2 = new BlockPos(teleporter.pos_x,teleporter.pos_y,teleporter.pos_z);
                }
                if (world.getBlockState(pos2).isOf(ModBlocks.TELEPORTER)) {
                    world.setBlockState(pos2, ModBlocks.TELEPORTER.getDefaultState());
                }
            }
        }
        world.addBlockBreakParticles(pos, state);
        world.playSound(null, pos, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (entity.isSneaking() && state.get(LINKED) && blockEntity instanceof TeleporterBlockEntity teleporter && teleporter.pos_y != 2147483647) {
            BlockPos pos2;
            if (teleporter.relative) {
                pos2 = new BlockPos(pos.getX()+teleporter.pos_x,pos.getY()+teleporter.pos_y,pos.getZ()+teleporter.pos_z);
            } else {
                pos2 = new BlockPos(teleporter.pos_x,teleporter.pos_y,teleporter.pos_z);
            }
            if (teleporter.one_way || world.getBlockState(pos2).isOf(ModBlocks.TELEPORTER)) {
                entity.setSneaking(false);
                world.playSound(null, pos, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.BLOCKS, 1.0f, 1.0f);
                if (teleporter.relative) {
                    entity.teleport(pos.getX()+teleporter.pos_x+0.5,pos.getY()+teleporter.pos_y+0.25,pos.getZ()+teleporter.pos_z+0.5);
                } else {
                    entity.teleport(teleporter.pos_x+0.5,teleporter.pos_y+0.25,teleporter.pos_z+0.5);
                }
                world.playSound(null, pos2, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!world.isClient && stack.isOf(ModItems.FLASH_DRIVE) && !state.get(LINKED) && blockEntity instanceof TeleporterBlockEntity teleporter) {
            if (!stack.hasNbt()) {
                player.sendMessage(Text.translatable("block.tlotd.teleporter.no_destination_on_drive"), true);
                world.playSound(null, pos, SoundEvents.ENTITY_VILLAGER_NO, SoundCategory.BLOCKS, 1.0f, 1.0f);
            } else if (!state.get(LINKED)) {
                BlockPos pos2 = new BlockPos(stack.getNbt().getInt("pos_x"),stack.getNbt().getInt("pos_y"),stack.getNbt().getInt("pos_z"));
                if (pos.equals(pos2)) {
                    player.sendMessage(Text.translatable("block.tlotd.teleporter.destination_same"), true);
                    world.playSound(null, pos, SoundEvents.ENTITY_VILLAGER_NO, SoundCategory.BLOCKS, 1.0f, 1.0f);
                } else {
                    if (teleporter.one_way || world.getBlockState(pos2).isOf(ModBlocks.TELEPORTER)) {
                        BlockEntity blockEntity2 = world.getBlockEntity(pos2);
                        if (!world.getBlockState(pos2).get(LINKED) && blockEntity2 instanceof TeleporterBlockEntity teleporter2) {
                            world.setBlockState(pos,state.with(LINKED, true));
                            world.setBlockState(pos2,state.with(LINKED, true));
                            teleporter.pos_x = stack.getNbt().getInt("pos_x");
                            teleporter.pos_y = stack.getNbt().getInt("pos_y");
                            teleporter.pos_z = stack.getNbt().getInt("pos_z");
                            teleporter.markDirty();
                            teleporter2.pos_x = pos.getX();
                            teleporter2.pos_y = pos.getY();
                            teleporter2.pos_z = pos.getZ();
                            teleporter2.markDirty();
                            player.sendMessage(Text.translatable("block.tlotd.teleporter.connected"), true);
                            world.playSound(null, pos, ModSounds.BLOCK_KEYCARD_READER_PLING, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        } else {
                            player.sendMessage(Text.translatable("block.tlotd.teleporter.destination_in_use"), true);
                            world.playSound(null, pos, SoundEvents.ENTITY_VILLAGER_NO, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        }
                    } else {
                        player.sendMessage(Text.translatable("block.tlotd.teleporter.none_at_destination"), true);
                        world.playSound(null, pos, SoundEvents.ENTITY_VILLAGER_NO, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    }
                }
            } else {
                player.sendMessage(Text.translatable("block.tlotd.teleporter.already_in_use"), true);
                world.playSound(null, pos, SoundEvents.ENTITY_VILLAGER_NO, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}