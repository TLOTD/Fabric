package net.tlotd.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CropBlock.class)
public class CropBlockMixin {
    @ModifyExpressionValue(method = "canPlantOnTop", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean allowTaggedFarmland(boolean original, BlockState floor) {
        return original || floor.isIn(TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "farmland")));
    }
}