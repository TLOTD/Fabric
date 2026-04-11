package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class HeavyDoorBlock extends DoorBlock {
    public HeavyDoorBlock(Settings settings, BlockSetType blockSetType) {
        super(settings, blockSetType);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        return switch (direction) {
            case EAST -> EAST_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> NORTH_SHAPE;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        if (!state.get(OPEN)) {
            return switch (direction) {
                case EAST -> EAST_SHAPE;
                case SOUTH -> SOUTH_SHAPE;
                case WEST -> WEST_SHAPE;
                default -> NORTH_SHAPE;
            };
        }
        else return Block.createCuboidShape(0F, 0F, 0F, 0F, 0F, 0F);
    }

    public static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 1.0F, 16.0F, 16.0F, 5.0F);
    public static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0.0F, 0.0F, 11.0F, 16.0F, 16.0F, 15.0F);
    public static final VoxelShape WEST_SHAPE = Block.createCuboidShape(11.0F, 0.0F, 0.0F, 15.0F, 16.0F, 16.0F);
    public static final VoxelShape EAST_SHAPE = Block.createCuboidShape(1.0F, 0.0F, 0.0F, 5.0F, 16.0F, 16.0F);
}
