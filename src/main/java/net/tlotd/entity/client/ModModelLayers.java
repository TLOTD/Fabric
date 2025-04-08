package net.tlotd.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModModelLayers {
    public static final EntityModelLayer TREX =
            new EntityModelLayer(new Identifier(TLOTD.MOD_ID, "t-rex"), "main");
    public static final EntityModelLayer INFECTED_TREX =
            new EntityModelLayer(new Identifier(TLOTD.MOD_ID, "infected_t-rex"), "main");
}
