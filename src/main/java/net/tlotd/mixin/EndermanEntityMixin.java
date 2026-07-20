package net.tlotd.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EndermanEntity.class)
public class EndermanEntityMixin {
    @ModifyExpressionValue(method = "isPlayerStaring", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean allowTaggedPumpkins(boolean original, PlayerEntity player) {
        if (original) {
            return true;
        }
        ItemStack helmet = player.getInventory().getArmorStack(3);
        return helmet.isIn(TagKey.of(RegistryKeys.ITEM, new Identifier("c", "carved_pumpkins")));
    }
}