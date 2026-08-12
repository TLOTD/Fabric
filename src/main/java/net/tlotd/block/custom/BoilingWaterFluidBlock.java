package net.tlotd.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.util.ModDamageTypes;

public class BoilingWaterFluidBlock extends FluidBlock {
    public BoilingWaterFluidBlock(FlowableFluid fluid, Settings settings) {
        super(fluid, settings);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world.isClient()) {
            return;
        }
        if (entity instanceof LivingEntity living) {
            if (entity.age % 20 == 0) {
                living.damage(ModDamageTypes.of(entity.getWorld(), ModDamageTypes.BOILING), 1.0F);
            }
        }
    }
}