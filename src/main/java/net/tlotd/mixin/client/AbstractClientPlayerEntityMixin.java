package net.tlotd.mixin.client;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.util.Identifier;
import net.tlotd.util.CapeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin {

    @Inject(method = "getSkinTextures", at = @At("RETURN"), cancellable = true)
    private void overrideCapeTexture(CallbackInfoReturnable<SkinTextures> cir) {
        AbstractClientPlayerEntity player = (AbstractClientPlayerEntity) (Object) this;
        Identifier customCape = CapeManager.getCape(player.getUuidAsString());

        if (customCape != null) {
            SkinTextures original = cir.getReturnValue();

            cir.setReturnValue(new SkinTextures(
                    original.texture(),
                    original.textureUrl(),
                    customCape,
                    original.elytraTexture(),
                    original.model(),
                    original.secure()
            ));
        }
    }
}