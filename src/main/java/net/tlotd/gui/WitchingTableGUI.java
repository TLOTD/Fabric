package net.tlotd.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.gui.renderer.FluidStackRenderer;
import net.tlotd.util.FluidStack;
import net.tlotd.util.MouseUtil;

public class WitchingTableGUI extends HandledScreen<WitchingTableGUIHandler> {
    private static final Identifier TEXTURE = new Identifier(TLOTD.MOD_ID, "textures/gui/witching_table.png");

    private FluidStackRenderer fluidStackRenderer;

    public WitchingTableGUI(WitchingTableGUIHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, Text.translatable("gui.tlotd.witching"));
    }

    @Override
    protected void init() {
        assignFluidStackRenderer();
        super.init();
        titleY = 7;
        playerInventoryTitleY = 74;
    }

    private void assignFluidStackRenderer() {
        fluidStackRenderer = new FluidStackRenderer(FluidConstants.BUCKET * 8,
                true, 15, 61);
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        renderFluidTooltip(context, mouseX, mouseY, x, y, handler.fluidStack, 8, 15, fluidStackRenderer);
    }

    private void renderFluidTooltip(DrawContext context, int mouseX, int mouseY, int x, int y,
                                    FluidStack fluidStack, int offsetX, int offsetY, FluidStackRenderer renderer) {
        if(isMouseAboveArea(mouseX, mouseY, x, y, offsetX, offsetY, renderer)) {
            setTooltip(renderer.getTooltip(fluidStack, TooltipContext.Default.BASIC));
        }
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

        fluidStackRenderer.drawFluid(context, handler.fluidStack, x+8, y+15, 16, 56,
                FluidConstants.BUCKET * 8);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(TEXTURE,x+106,y+20,176,0, handler.getScaledProgress(), 44);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context,mouseX, mouseY);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, FluidStackRenderer renderer) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, renderer.getWidth(), renderer.getHeight());
    }
}
