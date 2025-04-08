package net.tlotd.item.custom;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.tlotd.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GuidebookItem extends Item {

    public GuidebookItem(Settings settings) {
        super(settings);
    }

    public ClickEvent WIKI = new ClickEvent(ClickEvent.Action.OPEN_URL, "https://tlotd.net/wiki/guidebook");
    public ClickEvent CONFIG = new ClickEvent(ClickEvent.Action.OPEN_FILE, FabricLoader.getInstance().getConfigDir().toString()+"/tlotd_config.toml");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.tlotd.guidebook.tooltip", "Isla_Nublar", "TLOTD").formatted(Formatting.GRAY));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            if (!user.getItemCooldownManager().isCoolingDown(ModItems.GUIDEBOOK)) {
                user.getItemCooldownManager().set(ModItems.GUIDEBOOK, 10);
                user.sendMessage(Text.translatable("generic.tlotd.text.click_me").setStyle(Style.EMPTY.withClickEvent(WIKI).withColor(Formatting.BLUE)).append(" ").append(Text.translatable("generic.tlotd.text.wiki_link").formatted(Formatting.GRAY)));
                user.sendMessage(Text.translatable("generic.tlotd.text.click_me").setStyle(Style.EMPTY.withClickEvent(CONFIG).withColor(Formatting.GOLD)).append(" ").append(Text.translatable("generic.tlotd.text.config_link").formatted(Formatting.GRAY)));
                user.swingHand(hand);
            }
        }
        return super.use(world, user, hand);
    }
}