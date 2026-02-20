package net.tlotd.compat.jade;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec2f;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;

public enum TelevisionModifierComponentProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        IElementHelper elements = tooltip.getElementHelper();
        IElement vcr = elements.item(new ItemStack(ModBlocks.VIDEOCASSETTE_RECORDER), 0.5f).translate(new Vec2f(0, -1));
        IElement game = elements.item(new ItemStack(ModBlocks.GAME_CONSOLE), 0.5f).translate(new Vec2f(0, -1));

        if (accessor.getBlock().equals(ModBlocks.VIDEOCASSETTE_RECORDER_BOOKSHELF) || accessor.getBlock().equals(ModBlocks.MEDIA_SYSTEM_BOOKSHELF)) {
            tooltip.add(vcr);
            tooltip.append(Text.literal(" ").append(Text.translatable("block.tlotd.videocassette_recorder")));
        }
        if (accessor.getBlock().equals(ModBlocks.GAME_CONSOLE_BOOKSHELF) || accessor.getBlock().equals(ModBlocks.MEDIA_SYSTEM_BOOKSHELF)) {
            tooltip.add(game);
            tooltip.append(Text.literal(" ").append(Text.translatable("block.tlotd.game_console")));
        }
    }

    @Override
    public Identifier getUid() {
        return new Identifier(TLOTD.MOD_ID, "television_modifier");
    }
}