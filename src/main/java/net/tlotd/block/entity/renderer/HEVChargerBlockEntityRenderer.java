package net.tlotd.block.entity.renderer;

import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.tlotd.block.entity.HEVChargerBlockEntity;

public class HEVChargerBlockEntityRenderer implements BlockEntityRenderer<HEVChargerBlockEntity> {
    public HEVChargerBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(HEVChargerBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        WallMountLocation face = entity.face();
        if (face != WallMountLocation.WALL) return;
        ItemStack stack = entity.getItem();
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        Direction facing = entity.facing();
        matrices.push();
        switch (facing) {
            case NORTH -> {
                matrices.translate(0.66f, 0.66f, 0.19f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(0));
            }
            case SOUTH -> {
                matrices.translate(0.34f, 0.66f, 0.81f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
            }
            case WEST -> {
                matrices.translate(0.19f, 0.66f, 0.34f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90));
            }
            case EAST -> {
                matrices.translate(0.81f, 0.66f, 0.66f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(270));
            }
            default -> {
                matrices.pop();
                return;
            }
        }
        matrices.scale(0.25f, 0.25f, 0.25f);
        itemRenderer.renderItem(stack, ModelTransformationMode.NONE, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}