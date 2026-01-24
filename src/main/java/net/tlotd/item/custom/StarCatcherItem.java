package net.tlotd.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.item.ModItems;
import net.tlotd.world.dimension.ModDimensions;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StarCatcherItem extends Item {

    public StarCatcherItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("item.tlotd.star_catcher.tooltip").formatted(Formatting.GRAY));
        if (stack.getItem().equals(ModItems.MITHRIL_STAR_CATCHER)) {
            tooltip.add(Text.literal(" ").append(Text.translatable("item.tlotd.star_catcher.tooltip_2", "800+")).formatted(Formatting.DARK_GREEN));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack item = user.getStackInHand(hand);
        if (!world.isClient()) {
            if (user.getY() >= 800 && (world.getRegistryKey().equals(ModDimensions.LUNA_LEVEL_KEY) || world.getRegistryKey().equals(World.OVERWORLD) && world.isNight())) {
                if (hand == Hand.MAIN_HAND) { item.damage(1,user,e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));}
                else { item.damage(1,user,e -> e.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND));}
                if (user instanceof ServerPlayerEntity serverPlayerEntity) {
                    Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, item);
                    serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
                int randomItem = ThreadLocalRandom.current().nextInt(1, 5);
                ItemStack itemStack = new ItemStack(ModItems.XEN_CRYSTAL);
                switch (randomItem) {
                    case 2 -> itemStack = ModItems.METEORITE_CHUNK.getDefaultStack();
                    case 3 -> itemStack = ModItems.STAR_FRAGMENT.getDefaultStack();
                    case 4 -> itemStack = ModItems.LUNAR_CALLAINUS_LUMP.getDefaultStack();
                }
                user.giveItemStack(itemStack);
                world.playSound(null, BlockPos.ofFloored(user.getPos()), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0f, 1.0f);
                user.getItemCooldownManager().set(this, 10);
            }
        }
        return super.use(world, user, hand);
    }
}