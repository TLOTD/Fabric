package net.tlotd.gui;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.tlotd.TLOTD;

public class ModGUIHandlers {
    public static final ScreenHandlerType<DwarvenForgeGUIHandler> DWARVEN_FORGE_GUI_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(TLOTD.MOD_ID, "dwarven_forge"), new ExtendedScreenHandlerType<>(DwarvenForgeGUIHandler::new, BlockPos.PACKET_CODEC));
    public static final ScreenHandlerType<NetheriteAnvilGUIHandler> NETHERITE_ANVIL_GUI_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(TLOTD.MOD_ID, "netherite_anvil"), new ExtendedScreenHandlerType<>(NetheriteAnvilGUIHandler::new, BlockPos.PACKET_CODEC));
    public static final ScreenHandlerType<MithrilAnvilGUIHandler> MITHRIL_ANVIL_GUI_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(TLOTD.MOD_ID, "mithril_anvil"), new ExtendedScreenHandlerType<>(MithrilAnvilGUIHandler::new, BlockPos.PACKET_CODEC));
    public static final ScreenHandlerType<AugmentationTableGUIHandler> AUGMENTATION_TABLE_GUI_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(TLOTD.MOD_ID, "augmentation_table"), new ExtendedScreenHandlerType<>(AugmentationTableGUIHandler::new, BlockPos.PACKET_CODEC));
    public static final ScreenHandlerType<WitchingTableGUIHandler> WITCHING_TABLE_GUI_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(TLOTD.MOD_ID, "witching_table"), new ExtendedScreenHandlerType<>(WitchingTableGUIHandler::new, BlockPos.PACKET_CODEC));
    public static final ScreenHandlerType<OxygenCollectorGUIHandler> OXYGEN_COLLECTOR_GUI_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(TLOTD.MOD_ID, "oxygen_collector"), new ExtendedScreenHandlerType<>(OxygenCollectorGUIHandler::new, BlockPos.PACKET_CODEC));
    public static final ScreenHandlerType<KeycardProgrammerGUIHandler> KEYCARD_PROGRAMMER_GUI_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(TLOTD.MOD_ID, "keycard_programmer"), new ExtendedScreenHandlerType<>(KeycardProgrammerGUIHandler::new, BlockPos.PACKET_CODEC));
    public static final ScreenHandlerType<IncubatorGUIHandler> INCUBATOR_GUI_HANDLER = Registry.register(Registries.SCREEN_HANDLER, Identifier.of(TLOTD.MOD_ID, "incubator"), new ExtendedScreenHandlerType<>(IncubatorGUIHandler::new, BlockPos.PACKET_CODEC));

    public static void registerGUIHandlers() {
        TLOTD.LOGGER.info("Registering Screen Handlers for " + TLOTD.MOD_ID);
    }
}
