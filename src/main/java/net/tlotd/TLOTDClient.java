package net.tlotd;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.util.Identifier;
import net.tlotd.client.*;
import net.tlotd.fluid.ModFluids;
import net.tlotd.networking.GlobalConfigNetworking;
import net.tlotd.networking.PlayerDataSyncNetworking;
import net.tlotd.networking.TextureSyncNetworking;
import net.tlotd.particle.BloodDrippingFactory;
import net.tlotd.particle.BloodFallingFactory;
import net.tlotd.particle.BloodLandingFactory;
import net.tlotd.util.EnvelopeTooltipData;
import net.tlotd.particle.ModParticles;
import net.tlotd.util.HEVSuitTooltipData;
import net.tlotd.util.SpaceSuitTooltipData;

public class TLOTDClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ParticleFactoryRegistry.getInstance().register(ModParticles.COPPER_FIRE_FLAME, FlameParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.SULPHUR_FIRE_FLAME, FlameParticle.Factory::new);

        ParticleFactoryRegistry.getInstance().register(ModParticles.DRIPPING_BLOOD, BloodDrippingFactory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.FALLING_BLOOD, BloodFallingFactory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.LANDING_BLOOD, BloodLandingFactory::new);

        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_BOILING_WATER, ModFluids.FLOWING_BOILING_WATER, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/boiling_water_still"), Identifier.of("tlotd:block/boiling_water_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_SPEZI, ModFluids.FLOWING_SPEZI, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/spezi_still"), Identifier.of("tlotd:block/spezi_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_BEER, ModFluids.FLOWING_BEER, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/beer_still"), Identifier.of("tlotd:block/beer_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_MEAD, ModFluids.FLOWING_MEAD, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/mead_still"), Identifier.of("tlotd:block/mead_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_HOT_MILK, ModFluids.FLOWING_HOT_MILK, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/hot_milk_still"), Identifier.of("tlotd:block/hot_milk_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_HOT_CHOCOLATE, ModFluids.FLOWING_HOT_CHOCOLATE, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/hot_chocolate_still"), Identifier.of("tlotd:block/hot_chocolate_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_HOT_COFFEE, ModFluids.FLOWING_HOT_COFFEE, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/hot_coffee_still"), Identifier.of("tlotd:block/hot_coffee_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_OIL, ModFluids.FLOWING_OIL, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/oil_still"), Identifier.of("tlotd:block/oil_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_BLOOD, ModFluids.FLOWING_BLOOD, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/blood_still"), Identifier.of("tlotd:block/blood_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_CHEMICAL_WASTE, ModFluids.FLOWING_CHEMICAL_WASTE, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/chemical_waste_still"), Identifier.of("tlotd:block/chemical_waste_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_SWEET_BERRY_JAM, ModFluids.FLOWING_SWEET_BERRY_JAM, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/sweet_berry_jam_still"), Identifier.of("tlotd:block/sweet_berry_jam_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_GLOW_BERRY_JAM, ModFluids.FLOWING_GLOW_BERRY_JAM, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/glow_berry_jam_still"), Identifier.of("tlotd:block/glow_berry_jam_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_STRAWBERRY_JAM, ModFluids.FLOWING_STRAWBERRY_JAM, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/strawberry_jam_still"), Identifier.of("tlotd:block/strawberry_jam_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_ORANGE_MARMELADE, ModFluids.FLOWING_ORANGE_MARMELADE, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/orange_marmelade_still"), Identifier.of("tlotd:block/orange_marmelade_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_BLUE_BERRY_JAM, ModFluids.FLOWING_BLUE_BERRY_JAM, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/blue_berry_jam_still"), Identifier.of("tlotd:block/blue_berry_jam_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_DROOPFRUIT_JAM, ModFluids.FLOWING_DROOPFRUIT_JAM, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/droopfruit_jam_still"), Identifier.of("tlotd:block/droopfruit_jam_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_ANCIENT_SOULBERRY_JAM, ModFluids.FLOWING_ANCIENT_SOULBERRY_JAM, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/ancient_soulberry_jam_still"), Identifier.of("tlotd:block/ancient_soulberry_jam_flow")));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_MOLTEN_MITHRIL, ModFluids.FLOWING_MOLTEN_MITHRIL, new SimpleFluidRenderHandler(Identifier.of("tlotd:block/molten_mithril_still"), Identifier.of("tlotd:block/molten_mithril_flow")));

        ModBlockRenderLayerMap.registerBlockRenderLayerMaps();
        ModItemRenderLayerMap.registerItemRenderLayerMaps();

        /*EntityRendererRegistry.register(ModEntities.TREX, TRexRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.TREX, TRexModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.INFECTED_TREX, InfectedTRexRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.INFECTED_TREX, InfectedTRexModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.SEAT, SeatRenderer::new);

        EntityRendererRegistry.register(ModEntities.ARMOR_PIERCING_ARROW, SilverthornArrowEntityRenderer::new);

        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, ModBlocks.GINKGO_SIGN_TEXTURE));
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, ModBlocks.GINKGO_HANGING_SIGN_TEXTURE));

        TerraformBoatClientHelper.registerModelLayers(ModBoats.GINKGO_BOAT_ID, false);

        HandledScreens.register(ModGUIHandlers.DWARVEN_FORGE_GUI_HANDLER, DwarvenForgeGUI::new);
        BlockEntityRendererFactories.register(ModBlockEntities.DWARVEN_FORGE_BLOCK_ENTITY, DwarvenForgeBlockEntityRenderer::new);

        HandledScreens.register(ModGUIHandlers.NETHERITE_ANVIL_GUI_HANDLER, NetheriteAnvilGUI::new);
        BlockEntityRendererFactories.register(ModBlockEntities.NETHERITE_ANVIL_BLOCK_ENTITY, NetheriteAnvilBlockEntityRenderer::new);

        HandledScreens.register(ModGUIHandlers.MITHRIL_ANVIL_GUI_HANDLER, MithrilAnvilGUI::new);
        BlockEntityRendererFactories.register(ModBlockEntities.MITHRIL_ANVIL_BLOCK_ENTITY, MithrilAnvilBlockEntityRenderer::new);

        HandledScreens.register(ModGUIHandlers.WITCHING_TABLE_GUI_HANDLER, WitchingTableGUI::new);
        BlockEntityRendererFactories.register(ModBlockEntities.WITCHING_TABLE_BLOCK_ENTITY, WitchingTableBlockEntityRenderer::new);

        HandledScreens.register(ModGUIHandlers.AUGMENTATION_TABLE_GUI_HANDLER, AugmentationTableGUI::new);
        BlockEntityRendererFactories.register(ModBlockEntities.AUGMENTATION_TABLE_BLOCK_ENTITY, AugmentationTableBlockEntityRenderer::new);

        BlockEntityRendererFactories.register(ModBlockEntities.HEV_CHARGER_BLOCK_ENTITY, HEVChargerBlockEntityRenderer::new);

        HandledScreens.register(ModGUIHandlers.INCUBATOR_GUI_HANDLER, IncubatorGUI::new);

        HandledScreens.register(ModGUIHandlers.OXYGEN_COLLECTOR_GUI_HANDLER, OxygenCollectorGUI::new);

        HandledScreens.register(ModGUIHandlers.KEYCARD_PROGRAMMER_GUI_HANDLER, KeycardProgrammerGUI::new);

        ModMessages.registerS2CPackets();*/
        TextureSyncNetworking.registerClientReceiver();
        GlobalConfigNetworking.registerClientReceiver();
        PlayerDataSyncNetworking.registerClientReceiver();

        TooltipComponentCallback.EVENT.register(data -> {
            if (data instanceof SpaceSuitTooltipData suitData) {
                return new SpaceSuitTooltipComponent(suitData);
            } else if (data instanceof HEVSuitTooltipData hevSuitData) {
                return new HEVSuitTooltipComponent(hevSuitData);
            } else if (data instanceof EnvelopeTooltipData envelopeData) {
                return new LetterTooltipComponent(envelopeData);
            }
            return null;
        });
    }
}
