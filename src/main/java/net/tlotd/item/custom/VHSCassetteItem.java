package net.tlotd.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.tlotd.item.ModItems;
import net.tlotd.world.ModChunkEvents;
import net.tlotd.world.dimension.ModDimensions;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VHSCassetteItem extends Item {

    public VHSCassetteItem(Settings settings) {
        super(settings);
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(this.getDescription().formatted(Formatting.GRAY));
    }

    public MutableText getDescription() {
        return Text.translatable(this.getTranslationKey() + ".desc");
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient() && user instanceof ServerPlayerEntity serverPlayer) {
            if (itemStack.isOf(ModItems.VHS_CASSETTE_PROJECT_KV31) && !serverPlayer.getWorld().getRegistryKey().equals(ModDimensions.BACKROOMS_LEVEL_KEY)) {
                ModChunkEvents.spawnBackroomsStructures(serverPlayer);
                return TypedActionResult.success(itemStack);
            }
        }
        return TypedActionResult.pass(itemStack);
    }
}