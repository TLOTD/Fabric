package net.tlotd.compat.create.ponder;

import com.simibubi.create.infrastructure.ponder.AllCreatePonderTags;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.tlotd.block.ModBlocks;

public final class CreatePonderCompat {

    public static void register(PonderTagRegistrationHelper<Identifier> helper) {
        helper.addToTag(AllCreatePonderTags.DISPLAY_SOURCES)
                .add(Registries.BLOCK.getId(ModBlocks.SIGNAL_TRANSMITTER))
                .add(Registries.BLOCK.getId(ModBlocks.RADIO))
                .add(Registries.BLOCK.getId(ModBlocks.TELEVISION));
    }
}