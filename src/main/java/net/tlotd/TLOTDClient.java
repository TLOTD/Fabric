package net.tlotd;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.tlotd.block.ModBlocks;
import net.tlotd.block.entity.ModBlockEntities;
import net.tlotd.block.entity.renderer.*;
import net.tlotd.entity.ModBoats;
import net.tlotd.entity.ModEntities;
import net.tlotd.entity.client.*;
import net.tlotd.fluid.ModFluids;
import net.tlotd.gui.MithrilAnvilGUI;
import net.tlotd.gui.ModGUIHandlers;
import net.tlotd.gui.WitchingTableGUI;
import net.tlotd.networking.ModMessages;

public class TLOTDClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_BEER, ModFluids.FLOWING_BEER, new SimpleFluidRenderHandler(new Identifier("tlotd:block/beer_still"), new Identifier("tlotd:block/beer_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_HOT_MILK, ModFluids.FLOWING_HOT_MILK, new SimpleFluidRenderHandler(new Identifier("tlotd:block/hot_milk_still"), new Identifier("tlotd:block/hot_milk_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_HOT_CHOCOLATE, ModFluids.FLOWING_HOT_CHOCOLATE, new SimpleFluidRenderHandler(new Identifier("tlotd:block/hot_chocolate_still"), new Identifier("tlotd:block/hot_chocolate_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_OIL, ModFluids.FLOWING_OIL, new SimpleFluidRenderHandler(new Identifier("tlotd:block/oil_still"), new Identifier("tlotd:block/oil_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_BLOOD, ModFluids.FLOWING_BLOOD, new SimpleFluidRenderHandler(new Identifier("tlotd:block/blood_still"), new Identifier("tlotd:block/blood_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_CHEMICAL_WASTE, ModFluids.FLOWING_CHEMICAL_WASTE, new SimpleFluidRenderHandler(new Identifier("tlotd:block/chemical_waste_still"), new Identifier("tlotd:block/chemical_waste_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_SWEET_BERRY_JAM, ModFluids.FLOWING_SWEET_BERRY_JAM, new SimpleFluidRenderHandler(new Identifier("tlotd:block/sweet_berry_jam_still"), new Identifier("tlotd:block/sweet_berry_jam_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_GLOW_BERRY_JAM, ModFluids.FLOWING_GLOW_BERRY_JAM, new SimpleFluidRenderHandler(new Identifier("tlotd:block/glow_berry_jam_still"), new Identifier("tlotd:block/glow_berry_jam_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_STRAWBERRY_JAM, ModFluids.FLOWING_STRAWBERRY_JAM, new SimpleFluidRenderHandler(new Identifier("tlotd:block/strawberry_jam_still"), new Identifier("tlotd:block/strawberry_jam_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_ORANGE_MARMELADE, ModFluids.FLOWING_ORANGE_MARMELADE, new SimpleFluidRenderHandler(new Identifier("tlotd:block/orange_marmelade_still"), new Identifier("tlotd:block/orange_marmelade_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_BLUE_BERRY_JAM, ModFluids.FLOWING_BLUE_BERRY_JAM, new SimpleFluidRenderHandler(new Identifier("tlotd:block/blue_berry_jam_still"), new Identifier("tlotd:block/blue_berry_jam_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_ANCIENT_SOULBERRY_JAM, ModFluids.FLOWING_ANCIENT_SOULBERRY_JAM, new SimpleFluidRenderHandler(new Identifier("tlotd:block/ancient_soulberry_jam_still"), new Identifier("tlotd:block/ancient_soulberry_jam_flow")));

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.APPARATUS, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_BEER, ModFluids.FLOWING_BEER);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_HOT_MILK, ModFluids.FLOWING_HOT_MILK);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_HOT_CHOCOLATE, ModFluids.FLOWING_HOT_CHOCOLATE);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_OIL, ModFluids.FLOWING_OIL);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_BLOOD, ModFluids.FLOWING_BLOOD);

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_SWEET_BERRY_JAM, ModFluids.FLOWING_SWEET_BERRY_JAM);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_GLOW_BERRY_JAM, ModFluids.FLOWING_GLOW_BERRY_JAM);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_STRAWBERRY_JAM, ModFluids.FLOWING_STRAWBERRY_JAM);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_ORANGE_MARMELADE, ModFluids.FLOWING_ORANGE_MARMELADE);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_BLUE_BERRY_JAM, ModFluids.FLOWING_BLUE_BERRY_JAM);
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_ANCIENT_SOULBERRY_JAM, ModFluids.FLOWING_ANCIENT_SOULBERRY_JAM);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_BOTTOM, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_BOTTOM_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_MIDDLE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_MIDDLE_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_TOP, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_TOP_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_SPLIT, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_SPLIT_PANE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_TILED, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAMED_GLASS_TILED_PANE, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PRESERVES_JAR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SWEET_BERRY_JAM_JAR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLOW_BERRY_JAM_JAR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STRAWBERRY_JAM_JAR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ORANGE_MARMELADE_JAR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUE_BERRY_JAM_JAR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ANCIENT_SOULBERRY_JAM_JAR, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BW_STICKER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TLOTD_STICKER, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PRESENT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FESTIVE_LIGHTS, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STRAWBERRY_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ORANGE_TREE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EFFIGIES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GOAT_HEAD, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SKELETON, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EMERGING_SKELETON, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.XEN_CRYSTAL_CLUSTER, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CREEPER_PLUSHIE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ZOMBIE_PLUSHIE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLAYER_PLUSHIE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GARBAGE_CAN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RADIO, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RADIO_ON, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SIGNAL_TRANSMITTER, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OXYGEN_COLLECTOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLOOD_CAULDRON, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MINING_ELEVATOR_CONTROLLER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MINING_ELEVATOR_BASE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLASS_ELEVATOR_CONTROLLER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLASS_ELEVATOR_BASE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLASS_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLASS_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GINKGO_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GINKGO_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EDELWEISS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_EDELWEISS, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GINKGO_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_GINKGO_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GINKGO_LEAVES, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_WATER_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_APPLE_JUICE_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_ORANGE_JUICE_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_BEER_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_MILK_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_STRAWBERRY_MILKSHAKE_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_ORANGE_MILKSHAKE_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_CHOCOLATE_MILKSHAKE_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HOT_WOODEN_MILK_STEIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TREX_HEAD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GREEN_TREX_HEAD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRAY_TREX_HEAD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SCULK_TREX_HEAD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INFECTED_TREX_HEAD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SICKENED_TREX_HEAD, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.TREX, TRexRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.TREX, TRexModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.INFECTED_TREX, InfectedTRexRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.INFECTED_TREX, InfectedTRexModel::getTexturedModelData);

        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, ModBlocks.GINKGO_SIGN_TEXTURE));
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, ModBlocks.GINKGO_HANGING_SIGN_TEXTURE));

        TerraformBoatClientHelper.registerModelLayers(ModBoats.GINKGO_BOAT_ID, false);

        HandledScreens.register(ModGUIHandlers.MITHRIL_ANVIL_GUI_HANDLER, MithrilAnvilGUI::new);
        BlockEntityRendererFactories.register(ModBlockEntities.MITHRIL_ANVIL_BLOCK_ENTITY, MithrilAnvilBlockEntityRenderer::new);

        HandledScreens.register(ModGUIHandlers.WITCHING_TABLE_GUI_HANDLER, WitchingTableGUI::new);
        BlockEntityRendererFactories.register(ModBlockEntities.WITCHING_TABLE_BLOCK_ENTITY, WitchingTableBlockEntityRenderer::new);

        ModMessages.registerS2CPackets();
    }
}
