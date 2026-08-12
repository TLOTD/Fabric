package net.tlotd.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;

public class AugmentationTableGUI extends HandledScreen<AugmentationTableGUIHandler> {
    private static final Identifier TEXTURE = new Identifier(TLOTD.MOD_ID, "textures/gui/augmentation_table.png");

    public AugmentationTableGUI(AugmentationTableGUIHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, Text.translatable("gui.tlotd.augmenting"));
    }

    @Override
    protected void init() {
        super.init();
        titleY = 5;
        playerInventoryTitleY = 73;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        renderProgressArrow(context, x, y);
        renderProgressArrow2(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 83, y + 37, 176, 0, handler.getScaledProgress(), 12);
        }
    }

    private void renderProgressArrow2(DrawContext context, int x, int y) {
        if (!handler.isCrafting()) {
            return;
        }
        int fullWidth = 22;
        int progressWidth = handler.getScaledProgress2();
        int drawX = x + 103 + (fullWidth - progressWidth);
        int drawY = y + 37;
        int textureU = 176 + (fullWidth - progressWidth);
        int textureV = 12;
        context.drawTexture(TEXTURE, drawX, drawY, textureU, textureV, progressWidth, 12);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
        renderInfoTooltip(context, mouseX, mouseY);
    }

    private void renderInfoTooltip(DrawContext context, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        if (mouseX >= x + 83 && mouseX < x + 83 + 22 && mouseY >= y + 37 && mouseY < y + 37 + 12) {
            context.drawTooltip(textRenderer, Text.translatable("gui.tlotd.augmenting.add").formatted(Formatting.GRAY), mouseX, mouseY);
        } else if (mouseX >= x + 127 && mouseX < x + 127 + 22 && mouseY >= y + 37 && mouseY < y + 37 + 12) {
            context.drawTooltip(textRenderer, Text.translatable("gui.tlotd.augmenting.remove").formatted(Formatting.GRAY), mouseX, mouseY);
        }
    }
}