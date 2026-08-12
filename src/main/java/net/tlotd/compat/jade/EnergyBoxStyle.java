package net.tlotd.compat.jade;

import net.minecraft.client.gui.DrawContext;
import snownee.jade.api.ui.IBoxStyle;

public class EnergyBoxStyle implements IBoxStyle {

    public static final EnergyBoxStyle INSTANCE = new EnergyBoxStyle();

    @Override
    public float borderWidth() {
        return 1.0F;
    }

    @Override
    public void render(DrawContext context, float x, float y, float width, float height) {
        int left = (int) x;
        int top = (int) y;
        int right = (int) (x + width);
        int bottom = (int) (y + height);
        context.fill(
                left,
                top,
                right,
                bottom,
                0xFF250000
        );
        for (int stripeX = left + 1; stripeX < right - 1; stripeX += 2) {
            context.fill(
                    stripeX,
                    top + 1,
                    stripeX + 1,
                    bottom - 1,
                    0xFF300000
            );
        }
        context.fill(left, top, right, top + 1, 0xFF707070);
        context.fill(left, bottom - 1, right, bottom, 0xFF707070);
        context.fill(left, top, left + 1, bottom, 0xFF707070);
        context.fill(right - 1, top, right, bottom, 0xFF707070);
    }
}