package net.tlotd.mixin;

//Thanks DEEP mod for code

import net.minecraft.core.block.BlockLogicSand;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.generate.chunk.perlin.overworld.ChunkDecoratorOverworld;
import net.minecraft.core.world.generate.feature.WorldFeatureOre;
import net.tlotd.block.custom.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(value = ChunkDecoratorOverworld.class, remap = false)
public class ModChunkDecoratorOverworld {
	@Shadow
	@Final
	private World world;

	@Inject(method = "decorate(Lnet/minecraft/core/world/chunk/Chunk;)V", at = @At(value = "TAIL"))
	public void addCustomOre(Chunk chunk, CallbackInfo ci) {
		BlockLogicSand.fallInstantly = true;

		int chunkX = chunk.xPosition;
		int chunkZ = chunk.zPosition;
		int minY = this.world.getWorldType().getMinY();
		int maxY = this.world.getWorldType().getMaxY();
		int rangeY = maxY + 1 - minY;
		int deepRangeY = maxY / 3 + 1 - minY;
		int mithrilRangeY = minY + 3 - minY;

		float oreHeightModifier = (float)rangeY / 128.0F;


		BlockLogicSand.fallInstantly = true;
		int x = chunkX * 16;
		int z = chunkZ * 16;

		//Cinnabar
		Random randCinnabar = new Random((long)chunkX * 186375209438L + (long)chunkZ * 904726381002L);
		for(int i4 = 0; (float)i4 < 5.0F * oreHeightModifier; ++i4) {
			int j7 = x + randCinnabar.nextInt(16);
			int k10 = minY + randCinnabar.nextInt(deepRangeY);
			int j13 = z + randCinnabar.nextInt(16);
			(new WorldFeatureOre(BlockLogicCinnabarOre.variantMap, 8)).place(this.world, randCinnabar, j7, k10, j13);
		}

		//Endurium
		Random randEndurium = new Random((long)chunkX * 729513846275L + (long)chunkZ * 312548790163L);
		for(int i4 = 0; (float)i4 < 5.0F * oreHeightModifier; ++i4) {
			int j7 = x + randEndurium.nextInt(16);
			int k10 = minY + randEndurium.nextInt(deepRangeY);
			int j13 = z + randEndurium.nextInt(16);
			(new WorldFeatureOre(BlockLogicEnduriumOre.variantMap, 8)).place(this.world, randEndurium, j7, k10, j13);
		}

		//Heliorite
		Random randHeliorite = new Random((long)chunkX * 450198732655L + (long)chunkZ * 893721654390L);
		for(int i4 = 0; (float)i4 < 5.0F * oreHeightModifier; ++i4) {
			int j7 = x + randHeliorite.nextInt(16);
			int k10 = minY + randHeliorite.nextInt(deepRangeY);
			int j13 = z + randHeliorite.nextInt(16);
			(new WorldFeatureOre(BlockLogicHelioriteOre.variantMap, 8)).place(this.world, randHeliorite, j7, k10, j13);
		}

		//Jurassoline
		Random randJurassoline = new Random((long)chunkX * 567901234876L + (long)chunkZ * 120384756291L);
		for(int i4 = 0; (float)i4 < 5.0F * oreHeightModifier; ++i4) {
			int j7 = x + randJurassoline.nextInt(16);
			int k10 = minY + randJurassoline.nextInt(deepRangeY);
			int j13 = z + randJurassoline.nextInt(16);
			(new WorldFeatureOre(BlockLogicJurassolineOre.variantMap, 8)).place(this.world, randJurassoline, j7, k10, j13);
		}

		//Mithril
		Random randMithril = new Random((long)chunkX * 348920187364L + (long)chunkZ * 678912345678L);
		for(int i4 = 0; (float)i4 < 5.0F * oreHeightModifier; ++i4) {
			int j7 = x + randMithril.nextInt(16);
			int k10 = minY + randMithril.nextInt(mithrilRangeY);
			int j13 = z + randMithril.nextInt(16);
			(new WorldFeatureOre(BlockLogicMithrilOre.variantMap, 8)).place(this.world, randMithril, j7, k10, j13);
		}

		//Nebular
		Random randNebular = new Random((long)chunkX * 987654321012L + (long)chunkZ * 203948576102L);
		for(int i4 = 0; (float)i4 < 5.0F * oreHeightModifier; ++i4) {
			int j7 = x + randNebular.nextInt(16);
			int k10 = minY + randNebular.nextInt(deepRangeY);
			int j13 = z + randNebular.nextInt(16);
			(new WorldFeatureOre(BlockLogicNebularOre.variantMap, 8)).place(this.world, randNebular, j7, k10, j13);
		}

		//Palladium
		Random randPalladium = new Random((long)chunkX * 435098123789L + (long)chunkZ * 759384102345L);
		for(int i4 = 0; (float)i4 < 5.0F * oreHeightModifier; ++i4) {
			int j7 = x + randPalladium.nextInt(16);
			int k10 = minY + randPalladium.nextInt(deepRangeY);
			int j13 = z + randPalladium.nextInt(16);
			(new WorldFeatureOre(BlockLogicPalladiumOre.variantMap, 8)).place(this.world, randPalladium, j7, k10, j13);
		}

		//Fossil
		Random randFossil = new Random((long)chunkX * 648273910283L + (long)chunkZ * 517209384756L);
		for(int i4 = 0; (float)i4 < 2.5F * oreHeightModifier; ++i4) {
			int j7 = x + randFossil.nextInt(16);
			int k10 = minY + randFossil.nextInt(deepRangeY);
			int j13 = z + randFossil.nextInt(16);
			(new WorldFeatureOre(BlockLogicFossil.variantMap, 8)).place(this.world, randFossil, j7, k10, j13);
		}

		BlockLogicSand.fallInstantly = false;
	}

}
