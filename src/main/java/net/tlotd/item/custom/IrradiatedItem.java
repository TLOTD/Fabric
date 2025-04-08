package net.tlotd.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tlotd.effect.ModEffects;
import net.tlotd.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IrradiatedItem extends Item {

    public IrradiatedItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient) {
            if (entity.isPlayer()) {
                ServerPlayerEntity player = (ServerPlayerEntity) entity;
                if (!player.hasStatusEffect(ModEffects.IRRADIATED)) {
                    player.addStatusEffect(new StatusEffectInstance(ModEffects.IRRADIATED, 60,0,true,false));
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        tooltip.add(Text.translatable("item.tlotd.irradiated.tooltip").formatted(Formatting.YELLOW));
        super.appendTooltip(stack, world, tooltip, context);
    }
}