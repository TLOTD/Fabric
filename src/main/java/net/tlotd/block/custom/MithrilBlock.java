package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class MithrilBlock extends Block {
    public MithrilBlock(Settings settings) {
        super(settings);
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        spawnParticles(world, pos);
    }

    private static void spawnParticles(World world, BlockPos pos) {
        Random random = world.random;
        if (random.nextInt(5) == 1) {
            for(Direction direction : Direction.values()) {
                BlockPos blockPos = pos.offset(direction);
                if (!world.getBlockState(blockPos).isOpaqueFullCube(world, blockPos)) {
                    Direction.Axis axis = direction.getAxis();
                    double e = axis == Direction.Axis.X ? 0.5F + 0.5625F * direction.getOffsetX() : random.nextFloat();
                    double f = axis == Direction.Axis.Y ? 0.5F + 0.5625F * direction.getOffsetY() : random.nextFloat();
                    double g = axis == Direction.Axis.Z ? 0.5F + 0.5625F * direction.getOffsetZ() : random.nextFloat();
                    world.addParticle(ParticleTypes.END_ROD, pos.getX() + e, pos.getY() + f, pos.getZ() + g, 0.0F, 0.0F, 0.0F);
                }
            }
        }
    }
}
