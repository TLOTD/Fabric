package net.tlotd.compat.create.fan_processing_types;

import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.fluid.ModFluids;
import net.tlotd.item.ModItems;
import net.tlotd.particle.ModParticles;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EffegiesProcessingType implements FanProcessingType {

    public static final EffegiesProcessingType INSTANCE = new EffegiesProcessingType();

    private EffegiesProcessingType() {
    }

    @Override
    public boolean isValidAt(World world, BlockPos pos) {
        return world.getBlockState(pos).isOf(ModBlocks.EFFIGIES);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean canProcess(ItemStack stack, World world) {
        long time = world.getTimeOfDay() % 24000L;
        boolean night = time >= 12800 && time < 23200;
        if (!night) {
            return false;
        }
        return stack.isOf(Items.GLASS_BOTTLE) || stack.isOf(Items.BUCKET);
    }

    @Override
    public @Nullable List<ItemStack> process(ItemStack stack, World world) {
        List<ItemStack> result = new ArrayList<>();
        if (stack.isOf(Items.GLASS_BOTTLE)) {
            result.add(new ItemStack(ModItems.BLOOD_BOTTLE));
        } else if (stack.isOf(Items.BUCKET)) {
            result.add(new ItemStack(ModFluids.BLOOD_BUCKET));
        } else {
            return null;
        }
        return result;
    }

    @Override
    public void spawnProcessingParticles(World world, Vec3d pos) {
        long time = world.getTimeOfDay() % 24000L;
        boolean night = time >= 12800 && time < 23200;
        if (night) {
            Random random = world.random;
            double x = pos.x + (random.nextDouble() - 0.5) * 0.5;
            double y = pos.y + (random.nextDouble() - 0.5);
            double z = pos.z + (random.nextDouble() - 0.5) * 0.5;
            world.addParticle(ModParticles.DRIPPING_BLOOD, x, y, z, 0.0, 0.05, 0.0);
        }
    }

    @Override
    public void morphAirFlow(AirFlowParticleAccess access, Random random) {
        access.setColor(0x8A0000);
        access.setAlpha(0.8f);
    }

    @Override
    public void affectEntity(Entity entity, World world) {
        if (entity instanceof LivingEntity livingEntity && !livingEntity.hasStatusEffect(StatusEffects.WEAKNESS)) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 0, true, false));
        }
    }
}