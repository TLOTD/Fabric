package net.tlotd;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.tlotd.block.ModBlocks;
import net.tlotd.block.entity.ModBlockEntities;
import net.tlotd.block.entity.renderer.*;
import net.tlotd.client.ModBlockRenderLayerMap;
import net.tlotd.client.ModItemRenderLayerMap;
import net.tlotd.client.SpaceSuitTooltipComponent;
import net.tlotd.entity.ModBoats;
import net.tlotd.entity.ModEntities;
import net.tlotd.entity.client.*;
import net.tlotd.fluid.ModFluids;
import net.tlotd.gui.*;
import net.tlotd.networking.GlobalConfigNetworking;
import net.tlotd.networking.ModMessages;
import net.tlotd.networking.PlayerDataSyncNetworking;
import net.tlotd.networking.TextureSyncNetworking;
import net.tlotd.util.SpaceSuitTooltipData;

public class TLOTDClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_SPEZI, ModFluids.FLOWING_SPEZI, new SimpleFluidRenderHandler(new Identifier("tlotd:block/spezi_still"), new Identifier("tlotd:block/spezi_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_BEER, ModFluids.FLOWING_BEER, new SimpleFluidRenderHandler(new Identifier("tlotd:block/beer_still"), new Identifier("tlotd:block/beer_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_MEAD, ModFluids.FLOWING_MEAD, new SimpleFluidRenderHandler(new Identifier("tlotd:block/mead_still"), new Identifier("tlotd:block/mead_flow")));
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
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_DROOPFRUIT_JAM, ModFluids.FLOWING_DROOPFRUIT_JAM, new SimpleFluidRenderHandler(new Identifier("tlotd:block/droopfruit_jam_still"), new Identifier("tlotd:block/droopfruit_jam_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_ANCIENT_SOULBERRY_JAM, ModFluids.FLOWING_ANCIENT_SOULBERRY_JAM, new SimpleFluidRenderHandler(new Identifier("tlotd:block/ancient_soulberry_jam_still"), new Identifier("tlotd:block/ancient_soulberry_jam_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_MOLTEN_MITHRIL, ModFluids.FLOWING_MOLTEN_MITHRIL, new SimpleFluidRenderHandler(new Identifier("tlotd:block/molten_mithril_still"), new Identifier("tlotd:block/molten_mithril_flow")));

        ModBlockRenderLayerMap.registerBlockRenderLayerMaps();
        ModItemRenderLayerMap.registerItemRenderLayerMaps();

        EntityRendererRegistry.register(ModEntities.TREX, TRexRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.TREX, TRexModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.INFECTED_TREX, InfectedTRexRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.INFECTED_TREX, InfectedTRexModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.SEAT, SeatRenderer::new);

        EntityRendererRegistry.register(ModEntities.ARMOR_PIERCING_ARROW, SilverthornArrowEntityRenderer::new);

        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, ModBlocks.GINKGO_SIGN_TEXTURE));
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, ModBlocks.GINKGO_HANGING_SIGN_TEXTURE));

        TerraformBoatClientHelper.registerModelLayers(ModBoats.GINKGO_BOAT_ID, false);

        HandledScreens.register(ModGUIHandlers.MITHRIL_ANVIL_GUI_HANDLER, MithrilAnvilGUI::new);
        BlockEntityRendererFactories.register(ModBlockEntities.MITHRIL_ANVIL_BLOCK_ENTITY, MithrilAnvilBlockEntityRenderer::new);

        HandledScreens.register(ModGUIHandlers.WITCHING_TABLE_GUI_HANDLER, WitchingTableGUI::new);
        BlockEntityRendererFactories.register(ModBlockEntities.WITCHING_TABLE_BLOCK_ENTITY, WitchingTableBlockEntityRenderer::new);

        HandledScreens.register(ModGUIHandlers.AUGMENTATION_TABLE_GUI_HANDLER, AugmentationTableGUI::new);
        BlockEntityRendererFactories.register(ModBlockEntities.AUGMENTATION_TABLE_BLOCK_ENTITY, AugmentationTableBlockEntityRenderer::new);

        HandledScreens.register(ModGUIHandlers.INCUBATOR_GUI_HANDLER, IncubatorGUI::new);

        HandledScreens.register(ModGUIHandlers.OXYGEN_COLLECTOR_GUI_HANDLER, OxygenCollectorGUI::new);

        HandledScreens.register(ModGUIHandlers.KEYCARD_PROGRAMMER_GUI_HANDLER, KeycardProgrammerGUI::new);

        ModMessages.registerS2CPackets();
        TextureSyncNetworking.registerClientReceiver();
        GlobalConfigNetworking.registerClientReceiver();
        PlayerDataSyncNetworking.registerClientReceiver();

        TooltipComponentCallback.EVENT.register(data -> {
            if (data instanceof SpaceSuitTooltipData suitData) {
                return new SpaceSuitTooltipComponent(suitData);
            }
            return null;
        });
    }
}
