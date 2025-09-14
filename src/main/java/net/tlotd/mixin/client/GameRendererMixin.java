package net.tlotd.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;
import net.tlotd.effect.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Shadow
    abstract void loadPostProcessor(Identifier id);
    @Shadow
    public abstract void disablePostProcessor();

    @Unique
    private static boolean hadDrunk = false;
    @Unique
    private static boolean hadStoned = false;

    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(float tickDelta, long startTime, boolean tick, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        boolean hasDrunk = client.player.hasStatusEffect(ModEffects.DRUNK);
        boolean hasStoned = client.player.hasStatusEffect(ModEffects.STONED);

        if (hasDrunk && !hadDrunk) {
            this.loadPostProcessor(new Identifier("minecraft", "shaders/post/phosphor.json"));
        } else if (!hasDrunk && hadDrunk) {
            this.disablePostProcessor();
        }

        if (hasStoned && !hadStoned) {
            this.loadPostProcessor(new Identifier("minecraft", "shaders/post/blobs2.json"));
        } else if (!hasStoned && hadStoned) {
            this.disablePostProcessor();
        }

        hadDrunk = hasDrunk;
        hadStoned = hasStoned;
    }
}