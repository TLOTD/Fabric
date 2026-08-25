package net.tlotd.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.util.AdAstraGasNbtHelper;

import java.util.List;

public class OxygenCollectorGUI extends HandledScreen<OxygenCollectorGUIHandler> {

    private static final Identifier TEXTURE = new Identifier(TLOTD.MOD_ID, "textures/gui/oxygen_collector.png");

    public OxygenCollectorGUI(OxygenCollectorGUIHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, Text.translatable("gui.tlotd.oxygen_collector"));
    }

    @Override
    protected void init() {
        super.init();
        titleY = 7;
        playerInventoryTitleY = 40;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
        renderQuality(context, x, y);
    }

    private void renderQuality(DrawContext context, int x, int y) {
        int progress = handler.getScaledAmount();
        context.drawTexture(TEXTURE, x + 84, y + 18 + (18 - progress), 176, 18 - progress, 8, progress);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
        renderQualityTooltip(context, mouseX, mouseY);
    }

    private void renderQualityTooltip(DrawContext context, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        if (mouseX >= x + 84 && mouseX < x + 84 + 8 && mouseY >= y + 18 && mouseY < y + 18 + 18) {
            String gas = "gas.ad_astra.oxygen";
            Formatting formatting = Formatting.AQUA;
            if (handler.getGas() == 2) {
                gas = "gas.tlotd.withered_air";
                formatting = Formatting.RED;
            } else if (handler.getGas() == 1) {
                gas = "gas.tlotd.pipe_weed_smoke";
                formatting = Formatting.YELLOW;
            }
            int amount = handler.getAmount();
            String formattedGas;
            String formattedMaxGas;
            if (Screen.hasShiftDown()) {
                int displayAmount = (int) Math.round((double) amount * 1000 / AdAstraGasNbtHelper.MAX_AMOUNT);
                int displayMax = 1000;
                formattedGas = String.format("%,d", displayAmount);
                formattedMaxGas = String.format("%,d", displayMax);
            } else {
                formattedGas = AdAstraGasNbtHelper.getOxygenString(amount);
                formattedMaxGas = AdAstraGasNbtHelper.getOxygenString(AdAstraGasNbtHelper.MAX_AMOUNT);
            }
            List<Text> tooltip = List.of(Text.translatable("gui.tlotd.oxygen_collector.oxygen_percentage").formatted(Formatting.GRAY), Text.translatable("gui.tlotd.oxygen_collector.oxygen_percentage." + handler.getGas()).formatted(formatting), Text.translatable("gui.tlotd.oxygen_collector.gas").formatted(Formatting.GRAY), Text.translatable("item.tlotd.gas_cylinder.tooltip", formattedGas, formattedMaxGas, formattedGas.replace(',', '.'), formattedMaxGas.replace(',', '.'), Text.translatable(gas)).formatted(formatting));
            context.drawTooltip(textRenderer, tooltip, mouseX, mouseY);
        }
    }
}