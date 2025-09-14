package net.tlotd.painting;

import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class ModPaintings {
    public static final PaintingVariant T = registerPainting("t", new PaintingVariant(16,16));
    public static final PaintingVariant TLOTD_DRAGON = registerPainting("tlotd", new PaintingVariant(16,16));
    public static final PaintingVariant WHITE_CITY = registerPainting("white_city", new PaintingVariant(32,16));
    public static final PaintingVariant TREX_ENCOUNTER = registerPainting("t-rex_encounter", new PaintingVariant(32,16));
    public static final PaintingVariant DRAGONS_CONNECTION = registerPainting("dragons_connection", new PaintingVariant(16,32));
    public static final PaintingVariant SILENT_VOYAGER = registerPainting("silent_voyager", new PaintingVariant(32,32));
    public static final PaintingVariant AUTUMN_CASTLE = registerPainting("autumn_castle", new PaintingVariant(48,48));
    public static final PaintingVariant GOTHIC = registerPainting("gothic", new PaintingVariant(64,48));

    private static PaintingVariant registerPainting(String name, PaintingVariant paintingVariant) {
        return Registry.register(Registries.PAINTING_VARIANT, new Identifier(TLOTD.MOD_ID, name), paintingVariant);
    }

    public static void registerPaintings() {
        TLOTD.LOGGER.debug("Registering Paintings for " + TLOTD.MOD_ID);
    }
}
