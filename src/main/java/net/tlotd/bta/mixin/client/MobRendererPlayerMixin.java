package net.tlotd.bta.mixin.client;

import net.minecraft.client.render.entity.MobRendererPlayer;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.core.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Mixin(MobRendererPlayer.class)
public class MobRendererPlayerMixin {

	private static final Set<UUID> initialized = new HashSet<>();

	@Inject(method = "renderAdditional", at = @At("HEAD"))
	private void injectCape(
		TessellatorGeneral tess,
		Player player,
		float partialTick,
		CallbackInfo ci) {
		if (initialized.add(player.uuid)) {
			player.capeURL = "https://tlotd.net/api/minecraft/cape/" + player.uuid;
		}
	}
}
