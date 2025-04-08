package net.tlotd.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.effect.ModEffects;

public class ChemicalWasteFluidBlock extends FluidBlock {
    public ChemicalWasteFluidBlock(FlowableFluid fluid, Settings settings) {
        super(fluid, settings);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient) {
            if (entity.isPlayer()) {
                ServerPlayerEntity player = (ServerPlayerEntity) entity;
                if (!player.hasStatusEffect(ModEffects.IRRADIATED)) {
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.IRRADIATED, 60,0,true,false));
                }
            }
        }
        super.onEntityCollision(state, world, pos, entity);
    }
}
