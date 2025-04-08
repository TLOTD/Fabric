package net.tlotd.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.entity.custom.InfectedTRexEntity;

public class InfectedTRexRenderer extends MobEntityRenderer<InfectedTRexEntity, InfectedTRexModel<InfectedTRexEntity>> {
private static final Identifier TEXTURE = new Identifier(TLOTD.MOD_ID, "textures/entity/t-rex/infected.png");

    public InfectedTRexRenderer(EntityRendererFactory.Context context) {
        super(context, new InfectedTRexModel<>(context.getPart(ModModelLayers.INFECTED_TREX)), 2f);
    }

    @Override
    public Identifier getTexture(InfectedTRexEntity entity) {
        return TEXTURE;
    }
}
