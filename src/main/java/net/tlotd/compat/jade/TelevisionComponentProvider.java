package net.tlotd.compat.jade;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec2f;
import net.tlotd.TLOTD;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;
import net.tlotd.util.TelevisionSignalRegistry;
import net.tlotd.util.VideoGameRegistry;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;

import java.util.Optional;

import static net.tlotd.block.custom.TelevisionBlock.CHANNEL;

public enum TelevisionComponentProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (accessor.getBlock().equals(ModBlocks.TELEVISION_ON)) {
            Optional<TelevisionSignalRegistry.SignalEntry> currentEntry = TelevisionSignalRegistry.getAll().stream().filter(e -> e.onBlock() == accessor.getBlock() && e.channel() == accessor.getBlockState().get(CHANNEL)).findFirst();
            Item vhs = ModItems.VHS_CASSETTE_BROKEN;
            if (currentEntry.isPresent()) {
                Identifier signalId = currentEntry.get().signalItem();
                vhs = Registries.ITEM.get(signalId);
            }
            IElementHelper elements = tooltip.getElementHelper();
            IElement vhs_element = elements.item(new ItemStack(vhs), 0.5f).translate(new Vec2f(0, -1));
            tooltip.add(vhs_element);
            tooltip.append(Text.literal(" ").append(Text.translatable(vhs.getTranslationKey() + ".desc")));
        } else if (accessor.getBlock().equals(ModBlocks.TELEVISION_GAME)) {
            Optional<VideoGameRegistry.SignalEntry> currentEntry = VideoGameRegistry.getAll().stream().filter(e -> e.tvBlock() == accessor.getBlock() && e.gameID() == accessor.getBlockState().get(CHANNEL)).findFirst();
            Item game = ModItems.GAME_CARTRIDGE;
            if (currentEntry.isPresent()) {
                Identifier signalId = currentEntry.get().signalItem();
                game = Registries.ITEM.get(signalId);
            }
            IElementHelper elements = tooltip.getElementHelper();
            IElement game_element = elements.item(new ItemStack(game), 0.5f).translate(new Vec2f(0, -1));
            tooltip.add(game_element);
            tooltip.append(Text.literal(" ").append(Text.translatable(game.getTranslationKey() + ".desc")));
        }
    }
    @Override
    public Identifier getUid() {
        return new Identifier(TLOTD.MOD_ID, "television");
    }
}