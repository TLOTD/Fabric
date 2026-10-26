package net.tlotd.mixin;

import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndermanEntity.class)
public abstract class EndermanEntityMixin {
    @Inject(method = "isPlayerStaring", at = @At("HEAD"), cancellable = true)
    private void allowTaggedPumpkins(
            PlayerEntity player,
            CallbackInfoReturnable<Boolean> cir
    ) {
        ItemStack helmet = player.getInventory().getArmorStack(3);

        if (helmet.isIn(TagKey.of(
                RegistryKeys.ITEM,
                Identifier.of("c", "carved_pumpkins")))) {
            cir.setReturnValue(false);
        }
    }
}