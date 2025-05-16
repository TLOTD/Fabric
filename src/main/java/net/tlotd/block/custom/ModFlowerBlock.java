package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.tlotd.block.ModBlocks;

public class ModFlowerBlock extends FlowerBlock implements Fertilizable {

    public ModFlowerBlock(StatusEffect suspiciousStewEffect, int effectDuration, Settings settings) {
        super(suspiciousStewEffect, effectDuration, settings);
    }

    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        dropStack(world, pos, new ItemStack(this));
    }

    protected static final VoxelShape ATHELAS_SHAPE = Block.createCuboidShape(1.0F, 0.0F, 1.0F, 15.0F, 10.0F, 15.0F);

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Vec3d vec3d = state.getModelOffset(world, pos);
        if (state.isOf(ModBlocks.ATHELAS)) {
            return ATHELAS_SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
        } else {
            return SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
        }
    }
}