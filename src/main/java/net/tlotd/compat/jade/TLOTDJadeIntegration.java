package net.tlotd.compat.jade;

import net.tlotd.block.custom.*;
import net.tlotd.block.entity.HEVChargerBlockEntity;
import net.tlotd.block.entity.KeycardReaderBlockEntity;
import net.tlotd.block.entity.RadioBlockEntity;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class TLOTDJadeIntegration implements IWailaPlugin {

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(HEVChargerComponentProvider.INSTANCE, HEVChargerBlockEntity.class);
        registration.registerBlockDataProvider(KeycardReaderComponentProvider.INSTANCE, KeycardReaderBlockEntity.class);

        registration.registerBlockDataProvider(RadioComponentProvider.INSTANCE, RadioBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(HEVChargerComponentProvider.INSTANCE, HEVChargerBlock.class);
        registration.registerBlockComponent(WitchingTableComponentProvider.INSTANCE, WitchingTableBlock.class);
        registration.registerBlockComponent(TelevisionModifierComponentProvider.INSTANCE, TelevisionModifierBlock.class);
        registration.registerBlockComponent(TelevisionComponentProvider.INSTANCE, TelevisionBlock.class);
        registration.registerBlockComponent(ComputerComponentProvider.INSTANCE, ComputerBlock.class);
        registration.registerBlockComponent(RadioComponentProvider.INSTANCE, RadioBlock.class);
        registration.registerBlockComponent(KeycardReaderComponentProvider.INSTANCE, KeycardReaderBlock.class);
        registration.registerBlockComponent(PlayerPlushieComponentProvider.INSTANCE, PlayerPlushieBlock.class);
    }
}