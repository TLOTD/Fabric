package net.tlotd.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.tlotd.effect.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;

@Mixin(value = GameRenderer.class, priority = 5000)
public abstract class GameRendererMixin {

    @Shadow
    abstract void loadPostProcessor(Identifier id);

    @WrapOperation(method = "onCameraEntitySet", constant = @Constant(classValue = EndermanEntity.class))
    private boolean notSoSuperSecretSettingsAnymore(Object entity, Operation<Boolean> original) {
        if (original.call(entity)) { // is an enderman
            return true;
        }
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) {
            return false;
        }
        if (entity instanceof PlayerEntity player && player.hasStatusEffect(ModEffects.DRUNK)) {
            client.execute(() -> this.loadPostProcessor(Identifier.of("minecraft","shaders/post/phosphor.json")));
        }
        if (entity instanceof PlayerEntity player && player.hasStatusEffect(ModEffects.STONED)) {
            client.execute(() -> this.loadPostProcessor(Identifier.of("minecraft","shaders/post/blobs2.json")));
        }
        return false;
    }

}