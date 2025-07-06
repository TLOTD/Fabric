package net.tlotd.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tlotd.item.ModItems;
import net.tlotd.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class MithrilMirrorItem extends Item {

    public MithrilMirrorItem(Item.Settings settings) {
        super(settings);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }


    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity player = (PlayerEntity)user;
        if (!world.isClient()) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
            ServerWorld serverWorld = serverPlayer.server.getWorld(serverPlayer.getSpawnPointDimension());
            if (serverWorld != null) {
                BlockPos spawnpoint = serverPlayer.getSpawnPointPosition();
                boolean worldspawn = false;
                if (spawnpoint != null) {
                    Optional<Vec3d> optionalSpawnVec = PlayerEntity.findRespawnPosition(serverWorld, spawnpoint, serverPlayer.getSpawnAngle(), false, false);
                    if (optionalSpawnVec.isPresent()) {
                        BlockPos finalSpawnpoint = spawnpoint;
                        optionalSpawnVec.ifPresent(spawnVec -> {
                            serverPlayer.teleport(serverWorld, spawnVec.getX(), spawnVec.getY(), spawnVec.getZ(), serverPlayer.getSpawnAngle(), 0.5F);
                            serverWorld.playSound(null, finalSpawnpoint, ModSounds.ITEM_MITHRIL_MIRROR, SoundCategory.PLAYERS, 0.4f, 0.8f);
                        });
                    } else {
                        worldspawn = true;
                    }
                }
                else {
                    worldspawn = true;
                }
                if (worldspawn) {
                    spawnpoint = serverPlayer.server.getOverworld().getSpawnPos();
                    serverPlayer.teleport(serverPlayer.server.getOverworld(), spawnpoint.getX(), spawnpoint.getY(), spawnpoint.getZ(), serverPlayer.getSpawnAngle(), 0.5F);
                    while (!serverWorld.isSpaceEmpty(serverPlayer)) {
                        serverPlayer.teleport(serverPlayer.getX(), serverPlayer.getY() + 1.0D, serverPlayer.getZ());
                    }
                    serverWorld.playSound(null, spawnpoint, ModSounds.ITEM_MITHRIL_MIRROR, SoundCategory.PLAYERS, 0.4f, 0.8f);
                }
                player.getInventory().removeOne(stack);
                if (player.getInventory().getEmptySlot() == -1) {
                    player.dropItem(ModItems.FOGGY_MITHRIL_MIRROR);
                } else player.giveItemStack(ModItems.FOGGY_MITHRIL_MIRROR.getDefaultStack());
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 20, 0, false,false));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20, 0, false,false));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 20, 0, false,false));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 20, 0, false,false));
            } else {
                world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHULKER_BULLET_HURT, SoundCategory.BLOCKS, 1f, 1f);
            }
        }
        if (player != null) {
            player.getItemCooldownManager().set(this, 600);
            player.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        return stack;
    }

    private final int useTime = 30;

    public int getMaxUseTime(ItemStack stack) {
        return useTime;
    }

    public static final Identifier TENGWAR_FONT_ID = new Identifier("tlotd", "tengwar");

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.tlotd.mithril_mirror.tooltip").formatted(Formatting.GRAY));
        } else {
            Style style = getName().getStyle();
            tooltip.add(Text.translatable("item.tlotd.mithril_mirror.tooltip_quenya").setStyle(style.withFont(TENGWAR_FONT_ID)).formatted(Formatting.GRAY));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}