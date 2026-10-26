package net.tlotd.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryOps;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.tlotd.TLOTD;
import net.tlotd.util.SpaceSuitTooltipData;

public class SpaceSuitTooltipComponent implements TooltipComponent {
    private final DefaultedList<ItemStack> stacks;
    private static final Identifier EMPTY_SLOT_TEXTURE = Identifier.of(TLOTD.MOD_ID, "textures/gui/container/tooltip/empty_tank.png");

    public SpaceSuitTooltipComponent(SpaceSuitTooltipData data) {
        MinecraftClient client = MinecraftClient.getInstance();
        this.stacks = DefaultedList.ofSize(2, ItemStack.EMPTY);
        if (client.world == null) {
            return;
        }
        RegistryOps<NbtElement> ops = RegistryOps.of(NbtOps.INSTANCE, client.world.getRegistryManager());
        NbtList serialized = data.getSerializedStacks();
        for (int i = 0; i < serialized.size() && i < stacks.size(); i++) {
            final int index = i;
            ItemStack.CODEC.parse(ops, serialized.getCompound(i)).result().ifPresent(item -> stacks.set(index, item));
        }
    }

    @Override
    public int getHeight() {
        return 20;
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return stacks.size() * 18;
    }

    @Override
    public void drawItems(TextRenderer textRenderer, int x, int y, DrawContext context) {
        for (int i = 0; i < stacks.size(); i++) {
            int slotX = x + i * 18;
            ItemStack stack = stacks.get(i);
            if (stack.isEmpty()) {
                context.drawTexture(EMPTY_SLOT_TEXTURE, slotX, y, 0, 0, 16, 16, 16, 16);
            } else {
                context.drawItem(stack, slotX, y);
                context.drawItemInSlot(textRenderer, stack, slotX, y);
            }
        }
    }
}