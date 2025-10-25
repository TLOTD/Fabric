package net.tlotd.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.entity.ModEntities;
import net.tlotd.item.ModItems;
import net.tlotd.util.ModDamageTypes;

public class ArmorPiercingArrowEntity extends PersistentProjectileEntity {

    public ArmorPiercingArrowEntity(EntityType<? extends ArmorPiercingArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public ArmorPiercingArrowEntity(World world, LivingEntity owner) {
        super(ModEntities.ARMOR_PIERCING_ARROW, owner, world);
    }

    public void tick() {
        super.tick();
        if (this.getWorld().isClient && !this.inGround) {
            this.getWorld().addParticle(ParticleTypes.INSTANT_EFFECT, this.getX(), this.getY(), this.getZ(), 0.0F, 0.0F, 0.0F);
        }
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.SILVERTHORN_ARROW);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity target = entityHitResult.getEntity();
        if (target instanceof LivingEntity living) {
            living.damage(
                    ModDamageTypes.of(getWorld(), ModDamageTypes.SILVERTHORN_ARROW), 10
            );
        }
        this.discard();
    }

    @Override
    public boolean collidesWith(Entity other) {
        return super.collidesWith(other);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        BlockPos pos = blockHitResult.getBlockPos();
        BlockState state = getWorld().getBlockState(pos);
        if (state.isOf(ModBlocks.GLASS_TRAPDOOR) || state.isOf(ModBlocks.GLASS_DOOR) || (state.isIn(TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "glass_blocks"))) && !state.isIn(TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "framed_colorless_glass")))) || state.isIn(TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "glass_panes"))) && !state.isIn(TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "framed_colorless_glass_panes")))) {
            getWorld().breakBlock(pos, true);
            this.inGround = false;
            return;
        }
        super.onBlockHit(blockHitResult);
    }
}