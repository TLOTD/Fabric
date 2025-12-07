package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.tlotd.networking.ClientGlobalConfig;
import net.tlotd.networking.ClientTextureCache;
import net.tlotd.world.CustomTextureManager;
import net.tlotd.world.ModGlobalState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PlayerPlushieBlock extends HumanPlushieBlock {

    public static final IntProperty SKIN = IntProperty.of("skin", 0, 127);
    public static final BooleanProperty ALTERNATIVE = BooleanProperty.of("alternative");
    public static final BooleanProperty CUSTOM = BooleanProperty.of("custom");

    private int getDefaultSkinFor(String uuid, boolean formerTLOTD) {
        return switch (uuid) {
            case "212aa6f5-69a9-47d9-9ad5-19b359744edf" -> 1;
            case "67148bd0-1a00-4bca-9d9e-ec246afbcf51" -> 2;
            case "53c68d22-726b-4a37-b92d-8d7c4670a87d" -> 3;
            case "660605b0-a3ac-404c-8f85-039cfddf36de" -> formerTLOTD ? 4 : 0; //AliaSophie
            case "0b026440-781b-40c3-bfee-78a2ee71f042" -> formerTLOTD ? 5 : 0; //Teneba
            case "d9ffb72a-f473-4ebc-936f-6f7d5d694145" -> 6;
            case "ebcc701d-5e03-4e57-9279-1dd595f6a4d4" -> formerTLOTD ? 7 : 0; //ISSO_21_
            case "9d978ae8-0368-442b-b4c4-fd27ad9f4888" -> formerTLOTD ? 8 : 0; //Akitorlp
            case "08c6cfba-40cd-43e2-a929-764e9fadc442" -> 9;
            case "d3018dca-9a16-43f0-8d72-19b93e33fa6b" -> 10;
            case "7c2b3137-729f-47af-b3f8-55a0994a8c61" -> formerTLOTD ? 11 : 0; //VANDAGR
            case "2dc144f0-3e65-4e80-978b-d6356e5d3008" -> 12;
            case "125cda9f-1a5b-40c5-b3a9-02c7988940f6" -> 13;
            case "9b293ed4-2a42-4db7-b615-246d81dc5d0f" -> 14;
            case "75fcce95-16a1-417b-801d-04ebb925d56b" -> 15;
            case "c639c27d-b32b-4785-805e-ba4889006a8b" -> 16;
            case "f31b18df-5db5-4e00-9adc-b66e89c69792" -> 17;
            case "4488478f-78d4-4885-be12-1f2179874912" -> 18;
            case "8365d4fc-c514-4c2e-a4e5-cf39fb26b0f0" -> 19;
            case "1987a906-540f-4ae8-90df-43504c06a6e7" -> formerTLOTD ? 20 : 0; //GenosseNeuro
            case "7af13cd9-4c28-4d48-a3f4-3cfbbce46438" -> 21;
            default -> 0;
        };
    }

    private boolean hasDefaultAlternate(String uuid, boolean formerTLOTD) {
        return switch (uuid) {
            case "212aa6f5-69a9-47d9-9ad5-19b359744edf",
                 "67148bd0-1a00-4bca-9d9e-ec246afbcf51",
                 "53c68d22-726b-4a37-b92d-8d7c4670a87d",
                 "d3018dca-9a16-43f0-8d72-19b93e33fa6b",
                 "125cda9f-1a5b-40c5-b3a9-02c7988940f6" -> true;
            case "ebcc701d-5e03-4e57-9279-1dd595f6a4d4" -> formerTLOTD;
            default -> false;
        };
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        PlayerEntity player = ctx.getPlayer();
        ServerWorld world = null;
        boolean formerTLOTD = false;
        if (player != null && !ctx.getWorld().isClient && player.getServer() != null) {
            world = player.getServer().getOverworld();
            ModGlobalState globalState = ModGlobalState.get(player.getServer());
            formerTLOTD = globalState.formerTlotdRewards();
        }
        int skin = 0;
        boolean alternative = false;
        boolean custom = false;
        if (world != null) {
            CustomTextureManager manager = CustomTextureManager.get(player.getServer());
            int customId = manager.getTexture(player.getUuid());
            if (customId >= 0) {
                skin = customId;
                custom = true;
            } else {
                skin = getDefaultSkinFor(player.getUuidAsString(), formerTLOTD);
                if (player.isSneaking() && (skin == 0 || hasDefaultAlternate(player.getUuidAsString(), formerTLOTD))) {
                    alternative = true;
                }
            }
        }
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER))
                .with(SKIN, skin)
                .with(ALTERNATIVE, alternative)
                .with(CUSTOM, custom);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, SKIN, ALTERNATIVE, CUSTOM);
    }

    public PlayerPlushieBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(SKIN, 0).with(ALTERNATIVE, false).with(CUSTOM, false).with(WATERLOGGED, false));
    }

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");
    public static final Identifier PLAYERS_FONT_ID = new Identifier("tlotd", "players");
    public static final Identifier MODS_FONT_ID = new Identifier("tlotd", "mods");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("block.tlotd.player_plushie.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.player_plushie.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.literal(""));
            tooltip.add(Text.translatable("text.tlotd.player_plushie.ponder", Text.translatable("key.keyboard.shift").formatted(Formatting.WHITE)).formatted(Formatting.DARK_GRAY));
            Style style = this.getName().getStyle();
            tooltip.add(Text.translatable("block.tlotd.player_plushie.tooltip_shift").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("\uE000").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" TLOTD").setStyle(style.withFont(DEFAULT_FONT_ID))));
            tooltip.add(Text.literal("\uE001").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Isla_Nublar").setStyle(style.withFont(DEFAULT_FONT_ID))));
            tooltip.add(Text.literal("\uE002").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" EinsDarki").setStyle(style.withFont(DEFAULT_FONT_ID))));
            if (ClientGlobalConfig.formerTlotdRewards) {
                tooltip.add(Text.literal("\uE003").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" AliaSophie").setStyle(style.withFont(DEFAULT_FONT_ID))));
                tooltip.add(Text.literal("\uE004").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Teneba").setStyle(style.withFont(DEFAULT_FONT_ID))));
            }
            tooltip.add(Text.literal("\uE005").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Alex1666").setStyle(style.withFont(DEFAULT_FONT_ID))));
            if (ClientGlobalConfig.formerTlotdRewards) {
                tooltip.add(Text.literal("\uE006").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" ISSO_21_").setStyle(style.withFont(DEFAULT_FONT_ID))));
                tooltip.add(Text.literal("\uE007").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Akitorlp").setStyle(style.withFont(DEFAULT_FONT_ID))));
            }
            tooltip.add(Text.literal("\uE008").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Polarfoxtm").setStyle(style.withFont(DEFAULT_FONT_ID))));
            tooltip.add(Text.literal("\uE009").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Salsafox").setStyle(style.withFont(DEFAULT_FONT_ID))));
            if (ClientGlobalConfig.formerTlotdRewards) {
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
            if (ClientGlobalConfig.formerTlotdRewards) {
                tooltip.add(Text.literal("\uE013").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" GenosseNeuro").setStyle(style.withFont(DEFAULT_FONT_ID))));
            }
            tooltip.add(Text.literal("\uE014").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Red_ice").setStyle(style.withFont(DEFAULT_FONT_ID))));
            //tooltip.add(Text.literal("\uE015").setStyle(style.withFont(PLAYERS_FONT_ID)).append(Text.literal(" Jxst_Freezy").setStyle(style.withFont(DEFAULT_FONT_ID)))); (dont know his ign)
            tooltip.add(Text.translatable("block.tlotd.player_plushie.tooltip_custom").formatted(Formatting.GRAY));
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player != null && client.getServer() != null) {
                if (!ClientTextureCache.TEXTURES.isEmpty()) {
                    ClientTextureCache.TEXTURES.forEach((uuid, entry) -> tooltip.add(Text.literal("\uE000 ").setStyle(style.withFont(MODS_FONT_ID)).append(Text.literal(entry.playerName).setStyle(style.withFont(DEFAULT_FONT_ID)))));
                } else {
                    tooltip.add(Text.literal("...").formatted(Formatting.DARK_GRAY));
                }
            }
        } else {
            tooltip.add(Text.translatable("block.tlotd.player_plushie.tooltip").formatted(Formatting.GRAY));
            tooltip.add(Text.translatable("block.tlotd.player_plushie.tooltip_2").formatted(Formatting.GRAY));
            tooltip.add(Text.literal(""));
            tooltip.add(Text.translatable("text.tlotd.player_plushie.ponder", Text.translatable("key.keyboard.shift").formatted(Formatting.GRAY)).formatted(Formatting.DARK_GRAY));
        }
        super.appendTooltip(stack, world, tooltip, options);
    }
}
