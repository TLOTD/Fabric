package net.tlotd.bta.mixin;

import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.item.ItemStack;
import net.tlotd.bta.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Registries.class)
public class ItemGroupMixin {

	@Inject(method = "init", at = @At("TAIL"))
	private void addCobblestoneVariants(CallbackInfo ci) {
		List<ItemStack> stones = Registries.ITEM_GROUPS.getItem("minecraft:stones");
		if (stones != null) {
			stones.add(ModBlocks.RED_DEEPSLATE.getDefaultStack());
		}
		List<ItemStack> cobbles = Registries.ITEM_GROUPS.getItem("minecraft:cobblestones");
		if (cobbles != null) {
			cobbles.add(ModBlocks.COBBLED_RED_DEEPSLATE.getDefaultStack());
		}
	}
}
