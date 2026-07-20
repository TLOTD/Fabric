package net.tlotd.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class BloodFallingFactory implements ParticleFactory<DefaultParticleType> {

    private final SpriteProvider sprites;

    public BloodFallingFactory(SpriteProvider sprites) {
        this.sprites = sprites;
    }

    @Override
    public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double vx, double vy, double vz) {
        BloodParticle.Falling particle = new BloodParticle.Falling(world, x, y, z);
        particle.setSprite(sprites);
        return particle;
    }
}