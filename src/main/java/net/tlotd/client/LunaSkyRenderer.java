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

    public static void render(MatrixStack matrices, Camera camera, float tickDelta, boolean noPlanets) {
        RenderSystem.depthMask(false);
        RenderSystem.disableCull();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        renderBlackSky(matrices);
        renderStars(matrices, camera, tickDelta);
        updateShootingStars();
        tickShootingStars();
        renderShootingStars(matrices, camera);
        if (!noPlanets) {
            renderSun(matrices, tickDelta);
            renderEarth(matrices);
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
            buffer.vertex(matrix, -size, size, -size).color(0f, 0f, 0f, 1f).next();
            buffer.vertex(matrix, size, size, -size).color(0f, 0f, 0f, 1f).next();
            buffer.vertex(matrix, size, -size, -size).color(0f, 0f, 0f, 1f).next();
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
            buffer.vertex(matrix, x - right.x() * s - up.x() * s, y - right.y() * s - up.y() * s, z - right.z() * s - up.z() * s).color(star.r * brightness, star.g * brightness, star.b * brightness, alpha).next();
            buffer.vertex(matrix, x - right.x() * s + up.x() * s, y - right.y() * s + up.y() * s, z - right.z() * s + up.z() * s).color(star.r * brightness, star.g * brightness, star.b * brightness, alpha).next();
            buffer.vertex(matrix, x + right.x() * s + up.x() * s, y + right.y() * s + up.y() * s, z + right.z() * s + up.z() * s).color(star.r * brightness, star.g * brightness, star.b * brightness, alpha).next();
            buffer.vertex(matrix, x + right.x() * s - up.x() * s, y + right.y() * s - up.y() * s, z + right.z() * s - up.z() * s).color(star.r * brightness, star.g * brightness, star.b * brightness, alpha).next();
        }
        tessellator.draw();
        matrices.pop();
    }

    private static void renderEarth(MatrixStack matrices) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, EARTH);
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90f));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(67.5f));
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buffer = tess.getBuffer();
        float size = 7.5f;
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        buffer.vertex(matrix, -size, 100f, -size).texture(0f, 0f).next();
        buffer.vertex(matrix, size, 100f, -size).texture(1f, 0f).next();
        buffer.vertex(matrix, size, 100f, size).texture(1f, 1f).next();
        buffer.vertex(matrix, -size, 100f, size).texture(0f, 1f).next();
        tess.draw();
        matrices.pop();
    }

    private static void renderSun(MatrixStack matrices, float tickDelta) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderTexture(0, SUN);
        matrices.push();
        float skyAngle = (MinecraftClient.getInstance().world.getTimeOfDay() % 24000L + tickDelta) / 24000f;
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90f));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(skyAngle * 360f));
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buffer = tess.getBuffer();
        float size = 20;
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        buffer.vertex(matrix, -size, 100f, -size).texture(0f, 0f).next();
        buffer.vertex(matrix, size, 100f, -size).texture(1f, 0f).next();
        buffer.vertex(matrix, size, 100f, size).texture(1f, 1f).next();
        buffer.vertex(matrix, -size, 100f, size).texture(0f, 1f).next();
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
            if (length == 0) {
                i--;
                continue;
            }
            dx /= length;
            dy /= length;
            dz /= length;
            float r;
            float g;
            float b;
            float type = random.nextFloat();
            if (type < 0.166f) {
                r = 1.0f;
                g = 0.9f;
                b = 0.8f;
            } else if (type < 0.333f) {
                r = 1.0f;
                g = 0.8f;
                b = 0.9f;
            } else if (type < 0.5f) {
                r = 0.95f;
                g = 1.0f;
                b = 0.9f;
            } else if (type < 0.666f) {
                r = 0.9f;
                g = 1.0f;
                b = 0.95f;
            } else if (type < 0.833f) {
                r = 0.9f;
                g = 0.8f;
                b = 1.0f;
            } else {
                r = 0.8f;
                g = 0.9f;
                b = 1.0f;
            }
            float distance = 90f;
            stars[i] = new Star((float) (dx * distance), (float) (dy * distance), (float) (dz * distance), 0.15f + random.nextFloat() * 0.25f, 0.5f + random.nextFloat() * 0.5f, 0.5f + random.nextFloat() * 2.0f, random.nextFloat() * (float) Math.PI * 2, r, g, b);
        }
        return stars;
    }

    private static class Star {
        final float x, y, z;
        final float size;
        final float baseBrightness;
        final float twinkleSpeed;
        final float phase;
        final float r, g, b;

        Star(float x, float y, float z, float size, float baseBrightness, float twinkleSpeed, float phase, float r, float g, float b) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.size = size;
            this.baseBrightness = baseBrightness;
            this.twinkleSpeed = twinkleSpeed;
            this.phase = phase;
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }

    private static final int MAX_SHOOTING_STARS = 10;
    private static final ShootingStar[] SHOOTING_STARS = new ShootingStar[MAX_SHOOTING_STARS];
    private static final Random SHOOTING_STAR_RANDOM = new Random(49182L);

    private static class ShootingStar {
        float x;
        float y;
        float z;
        float dx;
        float dy;
        float dz;
        float progress;
        float speed;
        float length;
        float brightness;
        boolean active;

        void spawn(Random random) {
            double theta = random.nextDouble() * Math.PI * 2.0;
            double phi = Math.acos(2.0 * random.nextDouble() - 1.0);
            float radius = 80f;
            x = (float) (Math.sin(phi) * Math.cos(theta) * radius);
            y = (float) (Math.cos(phi) * radius);
            z = (float) (Math.sin(phi) * Math.sin(theta) * radius);
            dx = random.nextFloat() * 2f - 1f;
            dy = -0.15f - random.nextFloat() * 0.35f;
            dz = random.nextFloat() * 2f - 1f;
            float length = (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
            dx /= length;
            dy /= length;
            dz /= length;
            progress = 0f;
            speed = 0.5f + random.nextFloat() * 0.5f;
            this.length = 6f + random.nextFloat() * 10f;
            brightness = 0.8f + random.nextFloat() * 0.2f;
            active = true;
        }
    }

    private static long lastShootingStarTick = -1;

    static {
        for (int i = 0; i < SHOOTING_STARS.length; i++) {
            SHOOTING_STARS[i] = new ShootingStar();
        }
    }

    private static void updateShootingStars() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) {
            return;
        }
        long time = client.world.getTime();
        if (time == lastShootingStarTick) {
            return;
        }
        lastShootingStarTick = time;
        if (time % 20 != 0) {
            return;
        }
        if (SHOOTING_STAR_RANDOM.nextFloat() > 0.75f) {
            return;
        }
        for (ShootingStar star : SHOOTING_STARS) {
            if (!star.active) {
                star.spawn(SHOOTING_STAR_RANDOM);
                break;
            }
        }
    }

    private static void tickShootingStars() {
        for (ShootingStar star : SHOOTING_STARS) {
            if (!star.active) {
                continue;
            }
            star.progress += star.speed / 20.0f;
            if (star.progress >= 1.0f) {
                star.active = false;
            }
        }
    }

    private static void renderShootingStars(MatrixStack matrices, Camera camera) {
        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
        matrices.push();
        Quaternionf rotation = camera.getRotation();
        Vector3f cameraForward = new Vector3f(0, 0, 1).rotate(rotation);
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        for (ShootingStar star : SHOOTING_STARS) {
            if (!star.active) {
                continue;
            }
            float progress = star.progress;
            float travelDistance = 70f * progress;
            float headX = star.x + star.dx * travelDistance;
            float headY = star.y + star.dy * travelDistance;
            float headZ = star.z + star.dz * travelDistance;
            float tailX = headX - star.dx * star.length;
            float tailY = headY - star.dy * star.length;
            float tailZ = headZ - star.dz * star.length;
            Vector3f direction = new Vector3f(star.dx, star.dy, star.dz);
            Vector3f width = new Vector3f(direction).cross(cameraForward).normalize().mul(0.10f);
            float fadeIn = Math.min(progress * 10f, 1f);
            float fadeOut = Math.min((1f - progress) * 5f, 1f);
            float alpha = fadeIn * fadeOut * star.brightness;
            buffer.vertex(matrix, headX - width.x(), headY - width.y(), headZ - width.z()).color(1f, 1f, 1f, alpha).next();
            buffer.vertex(matrix, headX + width.x(), headY + width.y(), headZ + width.z()).color(1f, 1f, 1f, alpha).next();
            buffer.vertex(matrix, tailX + width.x(), tailY + width.y(), tailZ + width.z()).color(1f, 1f, 1f, alpha * 0.05f).next();
            buffer.vertex(matrix, tailX - width.x(), tailY - width.y(), tailZ - width.z()).color(1f, 1f, 1f, alpha * 0.05f).next();
        }
        tessellator.draw();
        matrices.pop();
    }
}