package net.tlotd.compat.create.ponder;

import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import org.jetbrains.annotations.NotNull;

public class TLOTDPonderPlugin implements PonderPlugin {

    @Override
    public void registerTags(PonderTagRegistrationHelper<Identifier> helper) {
        CreatePonderCompat.register(helper);
    }

    @Override
    public @NotNull String getModId() {
        return TLOTD.MOD_ID;
    }
}