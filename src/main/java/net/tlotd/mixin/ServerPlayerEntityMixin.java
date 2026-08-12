package net.tlotd.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.tlotd.effect.ModEffects;
import net.tlotd.world.dimension.ModDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "moveToWorld", at = @At("TAIL"))
    private void onDimensionChange(ServerWorld destination, CallbackInfoReturnable<Entity> cir) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        if (destination.getRegistryKey().equals(ModDimensions.BACKROOMS_LEVEL_KEY)) {
            player.addStatusEffect(new StatusEffectInstance(ModEffects.SUBSPACE_RESISTANCE, 72000, 0, true, false, false));
        }
    }
}