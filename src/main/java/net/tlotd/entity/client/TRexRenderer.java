package net.tlotd.entity.client;

import com.google.common.collect.Maps;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.tlotd.TLOTD;
import net.tlotd.entity.client.variant.TRexVariant;
import net.tlotd.entity.custom.TRexEntity;

import java.util.Map;

public class TRexRenderer extends MobEntityRenderer<TRexEntity, TRexModel<TRexEntity>> {

    public static final Map<TRexVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(TRexVariant.class), (map) -> {
                map.put(TRexVariant.BROWN,
                        new Identifier(TLOTD.MOD_ID, "textures/entity/t-rex/brown.png"));
                map.put(TRexVariant.GREEN,
                        new Identifier(TLOTD.MOD_ID, "textures/entity/t-rex/green.png"));
                map.put(TRexVariant.GRAY,
                        new Identifier(TLOTD.MOD_ID, "textures/entity/t-rex/gray.png"));
                map.put(TRexVariant.SCULK,
                        new Identifier(TLOTD.MOD_ID, "textures/entity/t-rex/sculk.png"));
                map.put(TRexVariant.INFECTED,
                        new Identifier(TLOTD.MOD_ID, "textures/entity/t-rex/infected.png"));
                map.put(TRexVariant.SICKENED,
                        new Identifier(TLOTD.MOD_ID, "textures/entity/t-rex/sickened.png"));
            });

    public TRexRenderer(EntityRendererFactory.Context context) {
        super(context, new TRexModel<>(context.getPart(ModModelLayers.TREX)), 2);
    }

    @Override
    public Identifier getTexture(TRexEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(TRexEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1.0f, 1.0f, 1.0f);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
