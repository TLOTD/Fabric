package net.tlotd.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.tlotd.entity.ModEntities;
import net.tlotd.entity.ai.TRexAttackGoal;
import net.tlotd.entity.client.variant.TRexVariant;
import net.tlotd.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

public class TRexEntity extends AnimalEntity implements VariantHolder<TRexVariant> {
    private static final TrackedData<Integer> VARIANT = DataTracker.registerData(TRexEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public static final String VARIANT_KEY = "Variant";

    private static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(TRexEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public void setVariant(TRexVariant variant) {
        this.dataTracker.set(VARIANT, variant.getId());
    }

    public TRexVariant getVariant() {
        return TRexVariant.byId(this.dataTracker.get(VARIANT));
    }

    public static boolean shouldBabyBeDifferent(Random random) {
        return random.nextInt(1200) == 0;
    }
    
    public static class TRexData
            extends PassiveEntity.PassiveData {
        public final TRexVariant[] variants;

        public TRexData(TRexVariant... variants) {
            super(false);
            this.variants = variants;
        }

        public TRexVariant getRandomVariant(Random random) {
            return this.variants[random.nextInt(this.variants.length)];
        }
    }
    
    public TRexEntity(EntityType<? extends AnimalEntity> entityType, World world) {
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
        this.goalSelector.add(1, new TRexAttackGoal(this, 1, true));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0F, AbstractHorseEntity.class));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.0F));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 0.7));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));

        this.targetSelector.add(0, new RevengeGoal(this));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, GoatEntity.class, false));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, SheepEntity.class, false));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, PigEntity.class, false));
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, CowEntity.class, false));
        this.targetSelector.add(5, new ActiveTargetGoal<>(this, AbstractHorseEntity.class, false));
        this.targetSelector.add(6, new ActiveTargetGoal<>(this, HoglinEntity.class, false));
        this.targetSelector.add(7, new ActiveTargetGoal<>(this, PiglinEntity.class, false));
        this.targetSelector.add(8, new ActiveTargetGoal<>(this, PiglinBruteEntity.class, false));
        this.targetSelector.add(9, new ActiveTargetGoal<>(this, VillagerEntity.class, false));
        this.targetSelector.add(10, new ActiveTargetGoal<>(this, PlayerEntity.class, false));
    }

    public static DefaultAttributeContainer.Builder createTRexAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 100)
                .add(EntityAttributes.GENERIC_ARMOR, 10)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 16)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.BEEF);
    }

    @Override
    @Nullable
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        TRexEntity tRexEntity = ModEntities.TREX.create(world);
        if (tRexEntity != null) {
            TRexVariant variant = shouldBabyBeDifferent(this.random) ? TRexVariant.getRandomUnnatural(this.random) : (this.random.nextBoolean() ? this.getVariant() : ((TRexEntity)entity).getVariant());
            tRexEntity.setVariant(variant);
            tRexEntity.setPersistent();
        }
        return tRexEntity;
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

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt(VARIANT_KEY, this.getVariant().getId());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setVariant(TRexVariant.byId(nbt.getInt(VARIANT_KEY)));
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
        this.dataTracker.startTracking(VARIANT, 0);
        this.dataTracker.startTracking(ATTACKING, false);
    }

    /* VARIANTS */
    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(TRexEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        boolean bl = false;
        if (spawnReason == SpawnReason.BUCKET) {
            return entityData;
        }
        Random random = world.getRandom();
        if (entityData instanceof TRexData) {
            if (((TRexData)entityData).getSpawnedCount() >= 2) {
                bl = true;
            }
        } else {
            entityData = new TRexData(TRexVariant.getRandomNatural(random), TRexVariant.getRandomNatural(random));
        }
        this.setVariant(((TRexData)entityData).getRandomVariant(random));
        if (bl) {
            this.setBreedingAge(-24000);
        }
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }
}
