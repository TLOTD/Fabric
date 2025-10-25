package net.tlotd.mixin.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import net.tlotd.compat.CompatModsCheck;
import net.tlotd.item.ModItemGroups;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.fabricmc.fabric.impl.itemgroup.FabricItemGroup;

@Mixin(CreativeInventoryScreen.class)
public abstract class CustomCreativeTabMixin {

    @Shadow private static ItemGroup selectedTab;
    @Unique private static final Identifier TAB_TLOTD_BG = new Identifier(TLOTD.MOD_ID, "textures/gui/container/creative_inventory/tab_tlotd.png");
    @Unique private static final Identifier SCROLL = new Identifier(TLOTD.MOD_ID, "textures/gui/container/creative_inventory/scrollbar.png");
    @Unique private static final Identifier TAB_TOP_SELECTED = new Identifier(TLOTD.MOD_ID, "textures/gui/container/creative_inventory/tab_tlotd_top_selected.png");
    @Unique private static final Identifier TAB_TOP_INACTIVE = new Identifier(TLOTD.MOD_ID, "textures/gui/container/creative_inventory/tab_tlotd_top_inactive.png");
    @Unique private static final Identifier TAB_BOTTOM_SELECTED = new Identifier(TLOTD.MOD_ID, "textures/gui/container/creative_inventory/tab_tlotd_bottom_selected.png");
    @Unique private static final Identifier TAB_BOTTOM_INACTIVE = new Identifier(TLOTD.MOD_ID, "textures/gui/container/creative_inventory/tab_tlotd_bottom_inactive.png");

    @Inject(
            method = "drawBackground",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V",
                    shift = At.Shift.AFTER
            )
    )
    private void drawCustomBackgroundAndScrollbar(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        CreativeInventoryScreen screen = (CreativeInventoryScreen) (Object) this;
        HandledScreenAccessor hs = (HandledScreenAccessor) screen;
        CreativeInventoryScreenAccessor cis = (CreativeInventoryScreenAccessor) screen;
        int x = hs.getX();
        int y = hs.getY();
        int bgW = hs.getBackgroundWidth();
        int bgH = hs.getBackgroundHeight();
        if (selectedTab == ModItemGroups.TLOTD_1_MATERIALS_GROUP || selectedTab == ModItemGroups.TLOTD_2_WEAPONS_TOOLS_UTILITIES_GROUP || selectedTab == ModItemGroups.TLOTD_3_BLOCKS_GROUP || selectedTab == ModItemGroups.TLOTD_9_COMPAT_GROUP) {
            context.drawTexture(TAB_TLOTD_BG, x, y, 0, 0, bgW, bgH);
            int scrollbarX = x + 175;
            int scrollbarY = y + 18;
            int trackHeight = 112;
            float scrollPos = cis.getScrollPosition();
            int maxOffset = trackHeight - 15;
            int scrollBarOffset = (int) (scrollPos * (maxOffset - 2));
            if (scrollBarOffset < 0) scrollBarOffset = 0;
            if (scrollBarOffset > maxOffset) scrollBarOffset = maxOffset;
            context.drawTexture(SCROLL, scrollbarX, scrollbarY + scrollBarOffset, 0, 0, 12, 15, 16, 16);
        }
    }

    @Inject(method = "renderTabIcon", at = @At("HEAD"), cancellable = true)
    private void mymod$drawCustomTabIcon(DrawContext context, ItemGroup group, CallbackInfo ci) {
        if (!CompatModsCheck.FORGE && (group == ModItemGroups.TLOTD_1_MATERIALS_GROUP || group == ModItemGroups.TLOTD_2_WEAPONS_TOOLS_UTILITIES_GROUP || group == ModItemGroups.TLOTD_3_BLOCKS_GROUP || group == ModItemGroups.TLOTD_9_COMPAT_GROUP)) {
                int currentPage = (selectedTab instanceof FabricItemGroup)
                        ? ((FabricItemGroup) selectedTab).getPage()
                        : 0;
                int groupPage = (group instanceof FabricItemGroup)
                        ? ((FabricItemGroup) group).getPage()
                        : 0;
                if (groupPage != currentPage) return;
                CreativeInventoryScreen screen = (CreativeInventoryScreen) (Object) this;
                HandledScreenAccessor accessor = (HandledScreenAccessor) screen;
                int x = accessor.getX();
                int y = accessor.getY();
                int bgHeight = accessor.getBackgroundHeight();
                boolean topRow = group.getRow() == ItemGroup.Row.TOP;
                int column = group.getColumn();
                int tabX = x + column * 27;
                int tabY = y + (topRow ? -28 : (bgHeight - 6));
                Identifier texture = (selectedTab == group)
                        ? (topRow ? TAB_TOP_SELECTED : TAB_BOTTOM_SELECTED)
                        : (topRow ? TAB_TOP_INACTIVE : TAB_BOTTOM_INACTIVE);
                context.drawTexture(texture, tabX, tabY, 0, 0, 26, 32, 32, 32);
                context.drawItem(group.getIcon(), tabX + 5, tabY + 9);
                ci.cancel();
        }
    }
}