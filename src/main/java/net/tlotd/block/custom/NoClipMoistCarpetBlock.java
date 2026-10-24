package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.block.enum_property.NoClipable;

public class NoClipMoistCarpetBlock extends NoClipBlock {

    public static final IntProperty MOISTURE = IntProperty.of("moisture", 0, 3);

    public NoClipMoistCarpetBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(NOCLIPABLE, NoClipable.SOLID).with(MOISTURE, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NOCLIPABLE).add(MOISTURE);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getStackInHand(hand).isIn(TagKey.of(RegistryKeys.ITEM, Identifier.of("c", "shears"))) || player.getStackInHand(hand).isIn(TagKey.of(RegistryKeys.ITEM, Identifier.of("c", "axes")))) {
            if (!world.isClient) {
                player.incrementStat(Stats.USED.getOrCreateStat(player.getStackInHand(hand).getItem()));
                world.playSound(null, pos, SoundEvents.BLOCK_WOOL_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.addBlockBreakParticles(pos, state);
                world.setBlockState(pos, ModBlocks.FLOOR_TILE.getDefaultState().with(NOCLIPABLE, state.get(NOCLIPABLE)));
                if (stack.isIn(TagKey.of(RegistryKeys.ITEM, Identifier.of("c", "shears"))) || stack.isIn(TagKey.of(RegistryKeys.ITEM, Identifier.of("c", "axes")))) {
                    EquipmentSlot slot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
                    stack.damage(1, player, slot);
                }
            }
            return ItemActionResult.SUCCESS;
        } else if ((player.getStackInHand(hand).isOf(Items.BUCKET) || player.getStackInHand(hand).isOf(ModBlocks.WOODEN_STEIN.asItem())) && state.get(MOISTURE) == 3) {
            if (!world.isClient) {
                Item returnItem = Items.WATER_BUCKET;
                if (player.getStackInHand(hand).isOf(ModBlocks.WOODEN_STEIN.asItem())) {
                    returnItem = ModBlocks.WOODEN_WATER_STEIN.asItem();
                }
                world.setBlockState(pos, state.with(MOISTURE, 0));
                player.getStackInHand(hand).decrement(1);
                player.incrementStat(Stats.USED.getOrCreateStat(player.getStackInHand(hand).getItem()));
                player.getItemCooldownManager().set(returnItem, 1);
                player.getItemCooldownManager().set(player.getStackInHand(hand).getItem(), 1);
                player.giveItemStack(returnItem.getDefaultStack());
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            return ItemActionResult.SUCCESS;
        } else if (player.getStackInHand(hand).isOf(Items.GLASS_BOTTLE) && state.get(MOISTURE) > 0) {
            if (!world.isClient) {
                world.setBlockState(pos, state.with(MOISTURE, state.get(MOISTURE) - 1));
                player.getStackInHand(hand).decrement(1);
                player.incrementStat(Stats.USED.getOrCreateStat(player.getStackInHand(hand).getItem()));
                player.getItemCooldownManager().set(PotionContentsComponent.createStack(Items.POTION, Potions.WATER).getItem(), 1);
                player.getItemCooldownManager().set(player.getStackInHand(hand).getItem(), 1);
                player.giveItemStack(PotionContentsComponent.createStack(Items.POTION, Potions.WATER));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            return ItemActionResult.SUCCESS;
        } else if ((player.getStackInHand(hand).isOf(Items.WATER_BUCKET) || player.getStackInHand(hand).isOf(ModBlocks.WOODEN_WATER_STEIN.asItem())) && state.get(MOISTURE) == 0) {
            if (!world.isClient) {
                Item returnItem = Items.BUCKET;
                if (player.getStackInHand(hand).isOf(ModBlocks.WOODEN_WATER_STEIN.asItem())) {
                    returnItem = ModBlocks.WOODEN_STEIN.asItem();
                }
                world.setBlockState(pos, state.with(MOISTURE, 3));
                player.getStackInHand(hand).decrement(1);
                player.incrementStat(Stats.USED.getOrCreateStat(player.getStackInHand(hand).getItem()));
                player.getItemCooldownManager().set(returnItem, 1);
                player.getItemCooldownManager().set(player.getStackInHand(hand).getItem(), 1);
                player.giveItemStack(returnItem.getDefaultStack());
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            return ItemActionResult.SUCCESS;
        } else if (player.getStackInHand(hand).isOf(PotionContentsComponent.createStack(Items.POTION, Potions.WATER).getItem()) && state.get(MOISTURE) < 3) {
            if (!world.isClient) {
                world.setBlockState(pos, state.with(MOISTURE, state.get(MOISTURE) + 1));
                player.getStackInHand(hand).decrement(1);
                player.incrementStat(Stats.USED.getOrCreateStat(player.getStackInHand(hand).getItem()));
                player.getItemCooldownManager().set(Items.GLASS_BOTTLE, 1);
                player.getItemCooldownManager().set(player.getStackInHand(hand).getItem(), 1);
                player.giveItemStack(Items.GLASS_BOTTLE.getDefaultStack());
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.FAIL;
    }
}