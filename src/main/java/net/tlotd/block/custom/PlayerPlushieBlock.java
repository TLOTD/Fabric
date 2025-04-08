package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.tlotd.config.ModConfigs;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class PlayerPlushieBlock extends HumanPlushieBlock {

    public static final IntProperty SKIN = IntProperty.of("skin", 0, 127);
    public static final BooleanProperty ALTERNATIVE = BooleanProperty.of("alternative");

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        int skin = 0;
        boolean alternative = false;
        if (!Objects.requireNonNull(ctx.getPlayer()).isSneaking()) {
            switch (ctx.getPlayer().getUuid().toString()) {
                case "212aa6f5-69a9-47d9-9ad5-19b359744edf" -> {skin = 1;}
                case "67148bd0-1a00-4bca-9d9e-ec246afbcf51" -> {skin = 2;}
                case "53c68d22-726b-4a37-b92d-8d7c4670a87d" -> {skin = 3;}
                case "660605b0-a3ac-404c-8f85-039cfddf36de" -> {skin = 4;}
                case "0b026440-781b-40c3-bfee-78a2ee71f042" -> {if (ModConfigs.FORMER_TLOTD_REWARDS) {skin = 5;}} //Teneba
                case "d9ffb72a-f473-4ebc-936f-6f7d5d694145" -> {skin = 6;}
                case "ebcc701d-5e03-4e57-9279-1dd595f6a4d4" -> {if (ModConfigs.FORMER_TLOTD_REWARDS) {skin = 7;}} //ISSO_21_
                case "9d978ae8-0368-442b-b4c4-fd27ad9f4888" -> {if (ModConfigs.FORMER_TLOTD_REWARDS) {skin = 8;}} //Akitorlp
                case "08c6cfba-40cd-43e2-a929-764e9fadc442" -> {skin = 9;}
                case "d3018dca-9a16-43f0-8d72-19b93e33fa6b" -> {skin = 10;}
                case "7c2b3137-729f-47af-b3f8-55a0994a8c61" -> {if (ModConfigs.FORMER_TLOTD_REWARDS) {skin = 11;}} //VANDAGR
                case "2dc144f0-3e65-4e80-978b-d6356e5d3008" -> {skin = 12;}
                case "125cda9f-1a5b-40c5-b3a9-02c7988940f6" -> {skin = 13;}
                case "9b293ed4-2a42-4db7-b615-246d81dc5d0f" -> {skin = 14;}
                case "75fcce95-16a1-417b-801d-04ebb925d56b" -> {skin = 15;}
                case "c639c27d-b32b-4785-805e-ba4889006a8b" -> {skin = 16;}
                case "f31b18df-5db5-4e00-9adc-b66e89c69792" -> {skin = 17;}
                case "4488478f-78d4-4885-be12-1f2179874912" -> {skin = 18;}
                case "8365d4fc-c514-4c2e-a4e5-cf39fb26b0f0" -> {skin = 19;}
                case "1987a906-540f-4ae8-90df-43504c06a6e7" -> {skin = 20;}
                case "7af13cd9-4c28-4d48-a3f4-3cfbbce46438" -> {skin = 21;}
                //case "????????-????-????-????-????????????" -> {skin = 22;} Jxst_Freezy (dont know his ign)
                default -> {
                }
            }
        } else {
            switch (ctx.getPlayer().getUuid().toString()) {
                case "212aa6f5-69a9-47d9-9ad5-19b359744edf" -> {skin = 1; alternative = true;}
                case "67148bd0-1a00-4bca-9d9e-ec246afbcf51" -> {skin = 2; alternative = true;}
                case "53c68d22-726b-4a37-b92d-8d7c4670a87d" -> {skin = 3; alternative = true;}
                case "ebcc701d-5e03-4e57-9279-1dd595f6a4d4" -> {if (ModConfigs.FORMER_TLOTD_REWARDS) {skin = 7; alternative = true;}} //ISSO_21_
                case "d3018dca-9a16-43f0-8d72-19b93e33fa6b" -> {skin = 10; alternative = true;}
                case "125cda9f-1a5b-40c5-b3a9-02c7988940f6" -> {skin = 13; alternative = true;}
                default -> {
                    alternative = true;
                }
            }
        }
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER))
                .with(SKIN, skin)
                .with(ALTERNATIVE, alternative);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, SKIN, ALTERNATIVE);
    }

    public PlayerPlushieBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(SKIN, 0).with(ALTERNATIVE, false).with(WATERLOGGED, false));
    }

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier PLAYERS_FONT_ID = new Identifier("tlotd", "players");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        if (Screen.hasShiftDown()) {

            Style style = this.getName().getStyle();

            tooltip.add(Text.translatable("block.tlotd.player_plushie.tooltip_shift").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("\uE000").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" TLOTD").setStyle(style.withFont(DEFAULT_FONT_ID))));
            tooltip.add(Text.literal("\uE001").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Isla_Nublar").setStyle(style.withFont(DEFAULT_FONT_ID))));
            tooltip.add(Text.literal("\uE002").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" EinsDarki").setStyle(style.withFont(DEFAULT_FONT_ID))));
            tooltip.add(Text.literal("\uE003").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" AliaSophie").setStyle(style.withFont(DEFAULT_FONT_ID))));
            if (ModConfigs.FORMER_TLOTD_REWARDS) {
                tooltip.add(Text.literal("\uE004").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Teneba").setStyle(style.withFont(DEFAULT_FONT_ID))));
            }
            tooltip.add(Text.literal("\uE005").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Alex1666").setStyle(style.withFont(DEFAULT_FONT_ID))));
            if (ModConfigs.FORMER_TLOTD_REWARDS) {
                tooltip.add(Text.literal("\uE006").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" ISSO_21_").setStyle(style.withFont(DEFAULT_FONT_ID))));
                tooltip.add(Text.literal("\uE007").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Akitorlp").setStyle(style.withFont(DEFAULT_FONT_ID))));
            }
            tooltip.add(Text.literal("\uE008").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Polarfoxtm").setStyle(style.withFont(DEFAULT_FONT_ID))));
            tooltip.add(Text.literal("\uE009").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Salsafox").setStyle(style.withFont(DEFAULT_FONT_ID))));
            if (ModConfigs.FORMER_TLOTD_REWARDS) {
                tooltip.add(Text.literal("\uE00A").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" VANDAGR").setStyle(style.withFont(DEFAULT_FONT_ID))));
            }
            tooltip.add(Text.literal("\uE00B").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" TLOTDShido").setStyle(style.withFont(DEFAULT_FONT_ID))));
            tooltip.add(Text.literal("\uE00C").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" nischi2612").setStyle(style.withFont(DEFAULT_FONT_ID))));
            tooltip.add(Text.literal("\uE00D").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" SK_Chuuya").setStyle(style.withFont(DEFAULT_FONT_ID))));
            tooltip.add(Text.literal("\uE00E").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" akashic_system").setStyle(style.withFont(DEFAULT_FONT_ID))));
            tooltip.add(Text.literal("\uE00F").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" MagicKVO").setStyle(style.withFont(DEFAULT_FONT_ID))));
            tooltip.add(Text.literal("\uE010").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" TheVexTv").setStyle(style.withFont(DEFAULT_FONT_ID))));
            tooltip.add(Text.literal("\uE011").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Glamin").setStyle(style.withFont(DEFAULT_FONT_ID))));
            tooltip.add(Text.literal("\uE012").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Jakx444").setStyle(style.withFont(DEFAULT_FONT_ID))));
            tooltip.add(Text.literal("\uE013").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" NightHawk241199").setStyle(style.withFont(DEFAULT_FONT_ID))));
            tooltip.add(Text.literal("\uE014").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Red_ice").setStyle(style.withFont(DEFAULT_FONT_ID))));
            //tooltip.add(Text.literal("\uE015").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Jxst_Freezy").setStyle(style.withFont(DEFAULT_FONT_ID)))); (dont know his ign)
        } else {
            tooltip.add(Text.translatable("block.tlotd.player_plushie.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.player_plushie.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.literal(""));
            tooltip.add(Text.literal("").append(Text.translatable("block.tlotd.player_plushie.tooltip_3").formatted(Formatting.DARK_GRAY)).append(Text.translatable("key.keyboard.left.shift").formatted(Formatting.GRAY)).append(Text.translatable("block.tlotd.player_plushie.tooltip_4")).formatted(Formatting.DARK_GRAY));
        }
        super.appendTooltip(stack, world, tooltip, options);
    }
}
