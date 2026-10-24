package net.tlotd.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.WorldRenderer;
import net.tlotd.client.LunaSkyRenderer;
import net.tlotd.world.dimension.ModDimensionsDataGenerator;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Inject(method = "renderSky(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;FLnet/minecraft/client/render/Camera;ZLjava/lang/Runnable;)V", at = @At("HEAD"), cancellable = true)
    private void renderLunaSky(Matrix4f positionMatrix, Matrix4f projectionMatrix, float tickDelta, Camera camera, boolean thickFog, Runnable fogCallback, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world != null && client.world.getRegistryKey().equals(ModDimensionsDataGenerator.LUNA_LEVEL_KEY)) {
            ci.cancel();
            LunaSkyRenderer.render(null, camera, tickDelta, false);
        } else if (client.world != null && client.world.getRegistryKey().equals(ModDimensionsDataGenerator.BACKROOMS_LEVEL_KEY)) {
            ci.cancel();
            LunaSkyRenderer.render(null, camera, tickDelta, true);
        }
    }
}