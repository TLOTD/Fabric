package net.tlotd.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.tlotd.effect.ModEffects;
import net.tlotd.item.ModItems;
import net.tlotd.world.dimension.ModDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Shadow
    abstract void loadPostProcessor(Identifier id);

    @Shadow
    public abstract void disablePostProcessor();

    @Unique
    private static Identifier currentShader = null;

    @Unique
    private static int lastPerspective = -1;

    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(float tickDelta, long startTime, boolean tick, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;

        int currentPerspective = client.options.getPerspective().ordinal();
        boolean perspectiveChanged = currentPerspective != lastPerspective;
        lastPerspective = currentPerspective;

        Identifier targetShader = null;

        //if (client.world.getRegistryKey().equals(ModDimensions.BACKROOMS_LEVEL_KEY)) {
        //    targetShader = new Identifier("minecraft", "shaders/post/ntsc.json");
        //}

        ItemStack helmet = client.player.getEquippedStack(EquipmentSlot.HEAD);
        if (helmet.isOf(ModItems.ASTRONAUT_HELMET)) {
            targetShader = new Identifier("minecraft", "shaders/post/desaturate.json");
        }

        if (client.player.hasStatusEffect(ModEffects.STONED)) {
            targetShader = new Identifier("minecraft", "shaders/post/blobs2.json");
        } else if (client.player.hasStatusEffect(ModEffects.DRUNKENNESS)) {
            targetShader = new Identifier("minecraft", "shaders/post/phosphor.json");
        }

        if (perspectiveChanged || !Objects.equals(currentShader, targetShader)) {
            if (targetShader != null) {
                this.loadPostProcessor(targetShader);
            } else {
                this.disablePostProcessor();
            }
            currentShader = targetShader;
        }
    }
}