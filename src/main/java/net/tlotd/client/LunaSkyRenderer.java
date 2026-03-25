package net.tlotd.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.tlotd.TLOTD;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Random;

public class LunaSkyRenderer {

    private static final Identifier SUN = new Identifier(TLOTD.MOD_ID, "textures/environment/sun.png");
    private static final Identifier EARTH = new Identifier(TLOTD.MOD_ID, "textures/environment/earth.png");
    private static final int STAR_COUNT = 1024;
    private static final Star[] STARS = generateStars();

    public static void render(MatrixStack matrices, Camera camera, float tickDelta, boolean starsOnly) {
        RenderSystem.depthMask(false);
        RenderSystem.disableCull();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        renderBlackSky(matrices);
        renderStars(matrices, camera, tickDelta);
        if (!starsOnly) {
            renderSun(matrices);
            renderEarth(matrices, tickDelta);
        }
        RenderSystem.enableCull();
        RenderSystem.depthMask(true);
    }

    private static void renderBlackSky(MatrixStack matrices) {
        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
        matrices.push();
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buffer = tess.getBuffer();
        float size = 100f;
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        for (int i = 0; i < 6; i++) {
            buffer.vertex(matrix, -size, -size, -size).color(0f, 0f, 0f, 1f).next();
            buffer.vertex(matrix, -size,  size, -size).color(0f, 0f, 0f, 1f).next();
            buffer.vertex(matrix,  size,  size, -size).color(0f, 0f, 0f, 1f).next();
            buffer.vertex(matrix,  size, -size, -size).color(0f, 0f, 0f, 1f).next();
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90f));
            matrix = matrices.peek().getPositionMatrix();
        }
        tess.draw();
        matrices.pop();
    }

    private static void renderStars(MatrixStack matrices, Camera camera, float tickDelta) {
        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
        matrices.push();
        Quaternionf rotation = camera.getRotation();
        Vector3f right = new Vector3f(1, 0, 0).rotate(rotation);
        Vector3f up = new Vector3f(0, 1, 0).rotate(rotation);
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        MinecraftClient client = MinecraftClient.getInstance();
        float globalTime = (client.world.getTime() + tickDelta) / 20f;
        for (Star star : STARS) {
            float brightness = 0.5f + 0.5f * (float) Math.sin(globalTime * star.twinkleSpeed + star.phase);
            float alpha = brightness * star.baseBrightness;
            float s = star.size;
            float x = star.x;
            float y = star.y;
            float z = star.z;
            buffer.vertex(matrix, x - right.x() * s - up.x() * s, y - right.y() * s - up.y() * s, z - right.z() * s - up.z() * s).color(1f, 1f, 1f, alpha).next();
            buffer.vertex(matrix, x - right.x() * s + up.x() * s, y - right.y() * s + up.y() * s, z - right.z() * s + up.z() * s).color(1f, 1f, 1f, alpha).next();
            buffer.vertex(matrix, x + right.x() * s + up.x() * s, y + right.y() * s + up.y() * s, z + right.z() * s + up.z() * s).color(1f, 1f, 1f, alpha).next();
            buffer.vertex(matrix, x + right.x() * s - up.x() * s, y + right.y() * s - up.y() * s, z + right.z() * s - up.z() * s).color(1f, 1f, 1f, alpha).next();
        }
        tessellator.draw();
        matrices.pop();
    }

    private static void renderSun(MatrixStack matrices) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, SUN);
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90f));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(67.5f));
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buffer = tess.getBuffer();
        float size = 20f;
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        buffer.vertex(matrix, -size, 100f, -size).texture(0f, 0f).next();
        buffer.vertex(matrix,  size, 100f, -size).texture(1f, 0f).next();
        buffer.vertex(matrix,  size, 100f,  size).texture(1f, 1f).next();
        buffer.vertex(matrix, -size, 100f,  size).texture(0f, 1f).next();
        tess.draw();
        matrices.pop();
    }

    private static void renderEarth(MatrixStack matrices, float tickDelta) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, EARTH);
        matrices.push();
        float skyAngle = (MinecraftClient.getInstance().world.getTimeOfDay() % 24000L + tickDelta) / 24000f;
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90f));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(skyAngle * 360f));
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buffer = tess.getBuffer();
        float size = 7.5f;
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        buffer.vertex(matrix, -size, 100f, -size).texture(0f, 0f).next();
        buffer.vertex(matrix,  size, 100f, -size).texture(1f, 0f).next();
        buffer.vertex(matrix,  size, 100f,  size).texture(1f, 1f).next();
        buffer.vertex(matrix, -size, 100f,  size).texture(0f, 1f).next();
        tess.draw();
        matrices.pop();
    }

    private static Star[] generateStars() {
        Random random = new Random(10842L);
        Star[] stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            double dx = random.nextDouble() * 2.0 - 1.0;
            double dy = random.nextDouble() * 2.0 - 1.0;
            double dz = random.nextDouble() * 2.0 - 1.0;
            double length = Math.sqrt(dx * dx + dy * dy + dz * dz);
            dx /= length;
            dy /= length;
            dz /= length;
            float distance = 90f;
            stars[i] = new Star((float)(dx * distance), (float)(dy * distance), (float)(dz * distance), 0.15f + random.nextFloat() * 0.25f, 0.5f + random.nextFloat() * 0.5f, 0.5f + random.nextFloat() * 2.0f, random.nextFloat() * (float)Math.PI * 2);
        }
        return stars;
    }

    private static class Star {
        final float x, y, z;
        final float size;
        final float baseBrightness;
        final float twinkleSpeed;
        final float phase;

        Star(float x, float y, float z, float size, float baseBrightness, float twinkleSpeed, float phase) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.size = size;
            this.baseBrightness = baseBrightness;
            this.twinkleSpeed = twinkleSpeed;
            this.phase = phase;
        }
    }
}