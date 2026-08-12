package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeverBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class LightSwitchBlock extends LeverBlock {
    public LightSwitchBlock(Settings settings) {
        super(settings);
    }

    public static final VoxelShape SWITCH_NORTH_SHAPE = Block.createCuboidShape(4.0F, 4.0F, 13.0F, 12.0F, 12.0F, 16.0F);
    public static final VoxelShape SWITCH_SOUTH_SHAPE = Block.createCuboidShape(4.0F, 4.0F, 0.0F, 12.0F, 12.0F, 3.0F);
    public static final VoxelShape SWITCH_WEST_SHAPE = Block.createCuboidShape(13.0F, 4.0F, 4.0F, 16.0F, 12.0F, 12.0F);
    public static final VoxelShape SWITCH_EAST_SHAPE = Block.createCuboidShape(0.0F, 4.0F, 4.0F, 3.0F, 12.0F, 12.0F);
    public static final VoxelShape SWITCH_FLOOR_SHAPE = Block.createCuboidShape(4.0F, 0.0F, 4.0F, 12.0F, 3.0F, 12.0F);
    public static final VoxelShape SWITCH_CEILING_SHAPE = Block.createCuboidShape(4.0F, 13.0F, 4.0F, 12.0F, 16.0F, 12.0F);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACE)) {
            case FLOOR -> SWITCH_FLOOR_SHAPE;
            case WALL -> switch (state.get(FACING)) {
                case EAST -> SWITCH_EAST_SHAPE;
                case WEST -> SWITCH_WEST_SHAPE;
                case SOUTH -> SWITCH_SOUTH_SHAPE;
                default -> SWITCH_NORTH_SHAPE;
            };
            default -> SWITCH_CEILING_SHAPE;
        };
    }
}