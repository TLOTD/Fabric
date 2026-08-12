package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.tlotd.block.enum_property.NoClipable;
import net.tlotd.util.NoclipTracker;

public class NoClipBlock extends Block {

    public static final EnumProperty<NoClipable> NOCLIPABLE = EnumProperty.of("state", NoClipable.class);

    public NoClipBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(NOCLIPABLE, NoClipable.SOLID));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(NOCLIPABLE).equals(NoClipable.SOLID)) {
            return VoxelShapes.fullCube();
        } else return Block.createCuboidShape(0F, 0F, 0F, 0F, 0F, 0F);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NOCLIPABLE);
    }

    @Override
    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.fullCube();
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (state.get(NOCLIPABLE).equals(NoClipable.PORTALING)) {
            if (!world.isClient()) {
                if (entity instanceof ServerPlayerEntity serverPlayer) {
                    NoclipTracker.noclip(serverPlayer);
                }
            }
        }
    }
}