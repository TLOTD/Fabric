package net.tlotd.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.registry.Registries;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.gui.renderer.FluidStackRenderer;
import net.tlotd.util.FluidStack;

import java.text.NumberFormat;
import java.util.List;

public class WitchingTableGUI extends HandledScreen<WitchingTableGUIHandler> {
    private static final Identifier TEXTURE = new Identifier(TLOTD.MOD_ID, "textures/gui/witching_table.png");
    private static final NumberFormat nf = NumberFormat.getIntegerInstance();

    private FluidStackRenderer fluidStackRenderer;

    public WitchingTableGUI(WitchingTableGUIHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, Text.translatable("gui.tlotd.witching"));
    }

    @Override
    protected void init() {
        assignFluidStackRenderer();
        super.init();
        titleY = 5;
        playerInventoryTitleY = 74;
    }

    private void assignFluidStackRenderer() {
        fluidStackRenderer = new FluidStackRenderer(FluidConstants.BUCKET * 8, true, 15, 61);
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
        renderSoulIcons(context, x, y);
        fluidStackRenderer.drawFluid(context, handler.fluidStack, x + 8, y + 15, 16, 56, FluidConstants.BUCKET * 8);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 106, y + 20, 176, 0, handler.getScaledProgress(), 44);
        }
    }

    private void renderSoulIcons(DrawContext context, int x, int y) {
        int normalSouls = handler.getNormalSouls();
        int cursedSouls = handler.getCursedSouls();
        int abyssalSouls = handler.getAbyssalSouls();
        if (normalSouls + cursedSouls + abyssalSouls > 3) {
            return;
        }
        int iconX = x + 145;
        int iconY = y + 64;
        int[] slotX = {iconX, iconX + 9, iconX + 18};
        int[] slotY = {iconY, iconY + 2, iconY};
        for (int i = 0; i < normalSouls; i++) {
            drawSoulIcon(context, slotX[i], slotY[i], 176);
        }
        for (int i = 0; i < cursedSouls; i++) {
            int slot = normalSouls + i;
            drawSoulIcon(context, slotX[slot], slotY[slot], 181);
        }
        for (int i = 0; i < abyssalSouls; i++) {
            int slot = normalSouls + cursedSouls + i;
            drawSoulIcon(context, slotX[slot], slotY[slot], 186);
        }
    }

    private void drawSoulIcon(DrawContext context, int x, int y, int textureX) {
        context.drawTexture(TEXTURE, x, y, textureX, 45, 5, 5);
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
        if (mouseX >= x + 8 && mouseX < x + 8 + 16 && mouseY >= y + 15 && mouseY < y + 15 + 56) {
            context.drawTooltip(textRenderer, Text.translatable("gui.tlotd.tooltip.liquid_amount_with_capacity", Text.translatable("block." + Registries.FLUID.getId(handler.fluidStack.fluidVariant.getFluid()).toTranslationKey()), nf.format(FluidStack.convertDropletsToMb(handler.fluidStack.getAmount())), nf.format(FluidStack.convertDropletsToMb(FluidConstants.BUCKET * 8))).fillStyle(Style.EMPTY.withColor(Formatting.GRAY)), mouseX, mouseY);
        } else if (mouseX >= x + 144 && mouseX < x + 144 + 25 && mouseY >= y + 63 && mouseY < y + 63 + 9) {
            int souls = handler.getNormalSouls();
            int cursed = handler.getCursedSouls();
            int abyss = handler.getAbyssalSouls();
            int charges = souls + cursed + abyss;
            Formatting format = Formatting.WHITE;
            if (charges == 3) {
                format = Formatting.GREEN;
            } else if (charges > 3) {
                format = Formatting.RED;
            }
            List<Text> tooltip = List.of(Text.translatable("gui.tlotd.witching.charges").append(Text.literal(" " + charges + " / 3").formatted(format)).formatted(Formatting.WHITE), Text.translatable("gui.tlotd.witching.soul_charges", souls).formatted(Formatting.GRAY), Text.translatable("gui.tlotd.witching.cursed_soul_charges", cursed).formatted(Formatting.GRAY), Text.translatable("gui.tlotd.witching.soul_charges_of_the_abyss", abyss).formatted(Formatting.GRAY));
            context.drawTooltip(textRenderer, tooltip, mouseX, mouseY);
        }
    }
}