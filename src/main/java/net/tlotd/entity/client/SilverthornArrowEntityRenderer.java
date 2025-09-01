package net.tlotd.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.entity.custom.ArmorPiercingArrowEntity;

public class SilverthornArrowEntityRenderer extends ProjectileEntityRenderer<ArmorPiercingArrowEntity> {
    public static final Identifier TEXTURE = new Identifier(TLOTD.MOD_ID, "textures/entity/projectiles/silverthorn_arrow.png");

    public SilverthornArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(ArmorPiercingArrowEntity armorPiercingArrowEntity) {
        return TEXTURE;
    }
}
