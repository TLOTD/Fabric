package net.tlotd.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class DwarvenForgeGUI extends HandledScreen<DwarvenForgeGUIHandler> {
    private static final Identifier TEXTURE = new Identifier(TLOTD.MOD_ID, "textures/gui/dwarven_forge.png");

    public DwarvenForgeGUI(DwarvenForgeGUIHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, Text.translatable("gui.tlotd.dwarven_forging"));
        this.backgroundWidth = 176;
        this.backgroundHeight = 185;
    }

    @Override
    protected void init() {
        super.init();
        titleY = 7;
        playerInventoryTitleY = 91;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        renderFire(context, x, y);
        renderProgressArrow(context, x, y);
    }

    private void renderFire(DrawContext context, int x, int y) {
        if (handler.hasMaxTemp()) {
            int pos = handler.getFireTexturePos();
            context.drawTexture(TEXTURE, x + 98, y + 46, 183, pos, 14, 14);
        }
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isHeating()) {
            int progress = handler.getScaledProgress();
            context.drawTexture(TEXTURE, x + 66, y + 17 + (72 - progress), 176, 72 - progress, 7, progress);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context,mouseX, mouseY);
    }
}
