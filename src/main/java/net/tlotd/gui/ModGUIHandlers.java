package net.tlotd.gui;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModGUIHandlers {
    public static final ScreenHandlerType<DwarvenForgeGUIHandler> DWARVEN_FORGE_GUI_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(TLOTD.MOD_ID, "dwarven_forge"),
                    new ExtendedScreenHandlerType<>(DwarvenForgeGUIHandler::new));

    public static final ScreenHandlerType<NetheriteAnvilGUIHandler> NETHERITE_ANVIL_GUI_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(TLOTD.MOD_ID, "netherite_anvil"),
                    new ExtendedScreenHandlerType<>(NetheriteAnvilGUIHandler::new));

    public static final ScreenHandlerType<MithrilAnvilGUIHandler> MITHRIL_ANVIL_GUI_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(TLOTD.MOD_ID, "mithril_anvil"),
                    new ExtendedScreenHandlerType<>(MithrilAnvilGUIHandler::new));

    public static final ScreenHandlerType<AugmentationTableGUIHandler> AUGMENTATION_TABLE_GUI_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(TLOTD.MOD_ID, "augmentation_table"),
                    new ExtendedScreenHandlerType<>(AugmentationTableGUIHandler::new));

    public static final ScreenHandlerType<WitchingTableGUIHandler> WITCHING_TABLE_GUI_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(TLOTD.MOD_ID, "witching_table"),
                    new ExtendedScreenHandlerType<>(WitchingTableGUIHandler::new));

    public static final ScreenHandlerType<OxygenCollectorGUIHandler> OXYGEN_COLLECTOR_GUI_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(TLOTD.MOD_ID, "oxygen_collector"),
                    new ExtendedScreenHandlerType<>(OxygenCollectorGUIHandler::new));

    public static final ScreenHandlerType<KeycardProgrammerGUIHandler> KEYCARD_PROGRAMMER_GUI_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(TLOTD.MOD_ID, "keycard_programmer"),
                    new ExtendedScreenHandlerType<>(KeycardProgrammerGUIHandler::new));

    public static final ScreenHandlerType<IncubatorGUIHandler> INCUBATOR_GUI_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(TLOTD.MOD_ID, "incubator"),
                    new ExtendedScreenHandlerType<>(IncubatorGUIHandler::new));

    public static void registerGUIHandlers() {
        TLOTD.LOGGER.info("Registering Screen Handlers for " + TLOTD.MOD_ID);
    }
}
