package net.tlotd.compat.jade;

import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.tlotd.TLOTD;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import static net.tlotd.block.custom.PlayerPlushieBlock.CUSTOM;
import static net.tlotd.block.custom.PlayerPlushieBlock.SKIN;

public enum PlayerPlushieComponentProvider implements IBlockComponentProvider {
    INSTANCE;

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier PLAYERS_FONT_ID = new Identifier("roads-n-vehicles", "players");

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (!accessor.getBlockState().get(CUSTOM)) {
            Style style = Style.EMPTY.withFormatting(Formatting.WHITE);
            switch (accessor.getBlockState().get(SKIN)) {
                case 1 -> tooltip.add(Text.literal("\uE000").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" TLOTD").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 2 -> tooltip.add(Text.literal("\uE001").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Isla_Nublar").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 3 -> tooltip.add(Text.literal("\uE002").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" EinsDarki").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 4 -> tooltip.add(Text.literal("\uE003").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" AliaSophie").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 5 -> tooltip.add(Text.literal("\uE004").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Teneba").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 6 -> tooltip.add(Text.literal("\uE005").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Alex1666").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 7 -> tooltip.add(Text.literal("\uE006").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" ISSO_21_").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 8 -> tooltip.add(Text.literal("\uE007").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Akitorlp").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 9 -> tooltip.add(Text.literal("\uE008").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Polarfoxtm").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 10 -> tooltip.add(Text.literal("\uE009").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Salsafox").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 11 -> tooltip.add(Text.literal("\uE00A").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" VANDAGR").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 12 -> tooltip.add(Text.literal("\uE00B").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" TLOTDShido").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 13 -> tooltip.add(Text.literal("\uE00C").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" nischi2612").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 14 -> tooltip.add(Text.literal("\uE00D").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" SK_Chuuya").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 15 -> tooltip.add(Text.literal("\uE00E").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" akashic_system").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 16 -> tooltip.add(Text.literal("\uE00F").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" MagicKVO").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 17 -> tooltip.add(Text.literal("\uE010").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" TheVexTv").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 18 -> tooltip.add(Text.literal("\uE011").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Glamin").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 19 -> tooltip.add(Text.literal("\uE012").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Jakx444").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 20 -> tooltip.add(Text.literal("\uE013").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" GenosseNeuro").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
                case 21 -> tooltip.add(Text.literal("\uE014").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Red_ice").setStyle(style.withFont(DEFAULT_FONT_ID).withFormatting(Formatting.GRAY))));
            }
        }
    }

    @Override
    public Identifier getUid() {
        return new Identifier(TLOTD.MOD_ID, "player_plushie");
    }
}