package net.tlotd.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.tlotd.fluid.ModFluids;

@Environment(EnvType.CLIENT)
public class BloodParticle extends SpriteBillboardParticle {

    private final Fluid fluid;

    protected BloodParticle(ClientWorld world, double x, double y, double z, Fluid fluid) {
        super(world, x, y, z);
        this.fluid = fluid;
        this.gravityStrength = 0.06F;
        this.setBoundingBoxSpacing(0.01F, 0.01F);
        this.setColor(0.45F, 0.02F, 0.10F);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        updateAge();
        if (this.dead) return;
        this.velocityY -= gravityStrength;
        this.move(this.velocityX, this.velocityY, this.velocityZ);
        updateVelocity();
        if (this.dead) return;
        this.velocityX *= 0.98;
        this.velocityY *= 0.98;
        this.velocityZ *= 0.98;
        if (fluid != Fluids.EMPTY) {
            BlockPos pos = BlockPos.ofFloored(this.x, this.y, this.z);
            FluidState state = this.world.getFluidState(pos);
            if (state.getFluid() == fluid && this.y < pos.getY() + state.getHeight(world, pos)) {
                markDead();
            }
        }
    }

    protected void updateAge() {
        if (this.maxAge-- <= 0) {
            markDead();
        }
    }

    protected void updateVelocity() {
    }

    public static class Dripping extends BloodParticle {

        public Dripping(ClientWorld world, double x, double y, double z) {
            super(world, x, y, z, ModFluids.STILL_BLOOD);

            this.gravityStrength *= 0.02F;
            this.maxAge = 40;
        }

        @Override
        protected void updateAge() {
            if (this.maxAge-- <= 0) {
                markDead();
                world.addParticle(ModParticles.FALLING_BLOOD, x, y, z, velocityX, velocityY, velocityZ);
            }
        }

        @Override
        protected void updateVelocity() {
            velocityX *= 0.02;
            velocityY *= 0.02;
            velocityZ *= 0.02;
        }
    }

    public static class Falling extends BloodParticle {
        public Falling(ClientWorld world, double x, double y, double z) {
            super(world, x, y, z, ModFluids.STILL_BLOOD);
            this.maxAge = (int) (64 / (Math.random() * 0.8 + 0.2));
        }

        @Override
        protected void updateVelocity() {
            if (onGround) {
                markDead();
                world.addParticle(ModParticles.LANDING_BLOOD, x, y, z, 0, 0, 0);
            }
        }
    }

    public static class Landing extends BloodParticle {
        public Landing(ClientWorld world, double x, double y, double z) {
            super(world, x, y, z, Fluids.EMPTY);
            this.maxAge = (int) (16 / (Math.random() * 0.8 + 0.2));
        }
    }
}