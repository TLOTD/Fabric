package net.tlotd.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import net.tlotd.entity.ai.InfectedTRexAttackGoal;
import net.tlotd.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

public class InfectedTRexEntity extends HostileEntity {

    private static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(InfectedTRexEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Override
    protected void mobTick() {
        if (getMaxHealth() != getHealth()) {
            if (getMaxHealth()/4 >= getHealth() && !this.hasStatusEffect(StatusEffects.RESISTANCE)) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600,2));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600,2));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 600,4));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600,3));
            } else if (getMaxHealth()/3 >= getHealth() && !this.hasStatusEffect(StatusEffects.SPEED)) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600,0));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 600,3));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600,2));
            } else if (getMaxHealth()/2 >= getHealth() && !this.hasStatusEffect(StatusEffects.STRENGTH)) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 600,2));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600,1));
            } else if (!this.hasStatusEffect(StatusEffects.REGENERATION)) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600,0));
            }
        }
        super.mobTick();
    }

    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effect) {
        if (effect.getEffectType() == StatusEffects.WEAKNESS || effect.getEffectType() == StatusEffects.SLOWNESS
                || effect.getEffectType().getTranslationKey().contains("effect.spore")) {
            return false;
        }
        return super.canHaveStatusEffect(effect);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public InfectedTRexEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
        
        if (this.isAttacking() & attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 10;
            attackAnimationState.start(this.age);
        } else {
            --this.attackAnimationTimeout;
        }
        if (!this.isAttacking()) {
            attackAnimationState.stop();
        }
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new InfectedTRexAttackGoal(this, 1, true));
        this.goalSelector.add(2, new WanderAroundFarGoal(this, 0.7));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));

        this.targetSelector.add(0, new RevengeGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, false));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PassiveEntity.class, false));
    }

    public static DefaultAttributeContainer.Builder createInfectedTRexAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 300)
                .add(EntityAttributes.GENERIC_ARMOR, 30)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 24)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1);
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return ModSounds.ENTITY_TREX_IDLE;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_TREX_HURT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return ModSounds.ENTITY_TREX_DEATH;
    }

    public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING, false);
    }
}
