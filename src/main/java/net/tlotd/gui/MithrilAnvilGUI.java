package net.tlotd.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.util.ItemHeatHelper;
import net.tlotd.util.TemperatureUnit;

import java.util.List;

public class MithrilAnvilGUI extends HandledScreen<MithrilAnvilGUIHandler> {
    private static final Identifier TEXTURE = new Identifier(TLOTD.MOD_ID, "textures/gui/mithril_anvil.png");

    public MithrilAnvilGUI(MithrilAnvilGUIHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, Text.translatable("gui.tlotd.mithril_metalworking"));
    }

    @Override
    protected void init() {
        super.init();
        titleY = 7;
        playerInventoryTitleY = 56;
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
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 122, y + 27, 176, 0, handler.getScaledProgress(), 16);
        }
        if (!handler.starlight()) {
            context.drawTexture(TEXTURE, x + 122, y + 27, 176, 16, 16, 16);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
        renderStarlightTooltip(context, mouseX, mouseY);
    }

    private void renderStarlightTooltip(DrawContext context, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        if (mouseX >= x + 122 && mouseX < x + 122 + 16 && mouseY >= y + 27 && mouseY < y + 27 + 16) {
            List<Text> tooltip = List.of(Text.translatable("block.tlotd.mithril_anvil.tooltip").formatted(Formatting.GRAY), Text.translatable("block.tlotd.mithril_anvil.tooltip_2").formatted(handler.starlight() ? Formatting.WHITE : Formatting.RED));
            context.drawTooltip(textRenderer, tooltip, mouseX, mouseY);
        }
    }
}