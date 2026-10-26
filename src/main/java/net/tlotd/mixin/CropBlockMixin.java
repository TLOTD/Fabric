package net.tlotd.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(CropBlock.class)
public abstract class CropBlockMixin {
    @Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
    private void allowTaggedFarmland(
            BlockState floor,
            BlockView world,
            BlockPos pos,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (floor.isOf(Blocks.FARMLAND)
                || floor.isIn(TagKey.of(
                RegistryKeys.BLOCK,
                Identifier.of("c", "farmland")))) {
            cir.setReturnValue(true);
        }
    }
}