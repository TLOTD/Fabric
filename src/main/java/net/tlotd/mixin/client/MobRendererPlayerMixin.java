package net.tlotd.mixin.client;

import net.minecraft.client.render.entity.MobRendererPlayer;
import net.minecraft.core.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobRendererPlayer.class)
public class MobRendererPlayerMixin {

	@Inject(method = "renderSpecials", at = @At("HEAD"), remap = false)
	private void injectCapeOverride(Player player, float partialTick, CallbackInfo ci) {
		String uuid = player.uuid.toString();
		switch (uuid) {
			case "212aa6f5-69a9-47d9-9ad5-19b359744edf":
				player.capeURL = "https://tlotd.net/minecraft/capes/212aa6f5-69a9-47d9-9ad5-19b359744edf.png";
				break;
			case "67148bd0-1a00-4bca-9d9e-ec246afbcf51":
				player.capeURL = "https://tlotd.net/minecraft/capes/67148bd0-1a00-4bca-9d9e-ec246afbcf51.png";
				break;
			case "53c68d22-726b-4a37-b92d-8d7c4670a87d":
				player.capeURL = "https://tlotd.net/minecraft/capes/53c68d22-726b-4a37-b92d-8d7c4670a87d.png";
				break;
			case "d9ffb72a-f473-4ebc-936f-6f7d5d694145":
				player.capeURL = "https://tlotd.net/minecraft/capes/d9ffb72a-f473-4ebc-936f-6f7d5d694145.png";
				break;
			case "08c6cfba-40cd-43e2-a929-764e9fadc442":
				player.capeURL = "https://tlotd.net/minecraft/capes/08c6cfba-40cd-43e2-a929-764e9fadc442.png";
				break;
			case "d3018dca-9a16-43f0-8d72-19b93e33fa6b":
				player.capeURL = "https://tlotd.net/minecraft/capes/d3018dca-9a16-43f0-8d72-19b93e33fa6b.png";
				break;
			case "2dc144f0-3e65-4e80-978b-d6356e5d3008":
				player.capeURL = "https://tlotd.net/minecraft/capes/2dc144f0-3e65-4e80-978b-d6356e5d3008.png";
				break;
			case "125cda9f-1a5b-40c5-b3a9-02c7988940f6":
				player.capeURL = "https://tlotd.net/minecraft/capes/125cda9f-1a5b-40c5-b3a9-02c7988940f6.png";
				break;
			case "75fcce95-16a1-417b-801d-04ebb925d56b":
				player.capeURL = "https://tlotd.net/minecraft/capes/75fcce95-16a1-417b-801d-04ebb925d56b.png";
				break;
			case "c639c27d-b32b-4785-805e-ba4889006a8b":
				player.capeURL = "https://tlotd.net/minecraft/capes/c639c27d-b32b-4785-805e-ba4889006a8b.png";
				break;
			case "f31b18df-5db5-4e00-9adc-b66e89c69792":
				player.capeURL = "https://tlotd.net/minecraft/capes/f31b18df-5db5-4e00-9adc-b66e89c69792.png";
				break;
			case "4488478f-78d4-4885-be12-1f2179874912":
				player.capeURL = "https://tlotd.net/minecraft/capes/4488478f-78d4-4885-be12-1f2179874912.png";
				break;
			case "8365d4fc-c514-4c2e-a4e5-cf39fb26b0f0":
				player.capeURL = "https://tlotd.net/minecraft/capes/8365d4fc-c514-4c2e-a4e5-cf39fb26b0f0.png";
				break;
			case "1987a906-540f-4ae8-90df-43504c06a6e7":
				player.capeURL = "https://tlotd.net/minecraft/capes/1987a906-540f-4ae8-90df-43504c06a6e7.png";
				break;
			case "7af13cd9-4c28-4d48-a3f4-3cfbbce46438":
				player.capeURL = "https://tlotd.net/minecraft/capes/7af13cd9-4c28-4d48-a3f4-3cfbbce46438.png";
				break;

			case "3abc0daa-7851-47c9-b5c7-1872e0014275":
				player.capeURL = "https://tlotd.net/minecraft/capes/3abc0daa-7851-47c9-b5c7-1872e0014275.png";
				break;

			//Supporter Capes:
			//hobblerox (BTA! texture submissions)
			case "18fb3279-ce41-40ca-be5c-6017f384f22f":
				player.capeURL = "https://tlotd.net/minecraft/capes/18fb3279-ce41-40ca-be5c-6017f384f22f.png";
				break;
		}
	}
}
