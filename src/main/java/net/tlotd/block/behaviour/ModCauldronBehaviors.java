package net.tlotd.block.behaviour;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.tlotd.block.ModBlocks;
import net.tlotd.fluid.ModFluids;
import net.tlotd.item.ModItems;

public class ModCauldronBehaviors {

    public static final Object2ObjectOpenHashMap<Item, CauldronBehavior> BEER_CAULDRON_BEHAVIOR =
            Util.make(new Object2ObjectOpenHashMap<>(), map ->
                    map.defaultReturnValue(
                            (state, world, pos, player, hand, stack) -> ActionResult.PASS
                    )
            );

    public static final Object2ObjectOpenHashMap<Item, CauldronBehavior> MEAD_CAULDRON_BEHAVIOR =
            Util.make(new Object2ObjectOpenHashMap<>(), map ->
                    map.defaultReturnValue(
                            (state, world, pos, player, hand, stack) -> ActionResult.PASS
                    )
            );

    public static final Object2ObjectOpenHashMap<Item, CauldronBehavior> BLOOD_CAULDRON_BEHAVIOR =
            Util.make(new Object2ObjectOpenHashMap<>(), map ->
                    map.defaultReturnValue(
                            (state, world, pos, player, hand, stack) -> ActionResult.PASS
                    )
            );

    public static final Object2ObjectOpenHashMap<Item, CauldronBehavior> BOILING_WATER_CAULDRON_BEHAVIOR =
            Util.make(new Object2ObjectOpenHashMap<>(), map ->
                    map.defaultReturnValue(
                            (state, world, pos, player, hand, stack) -> ActionResult.PASS
                    )
            );

    public static final Object2ObjectOpenHashMap<Item, CauldronBehavior> HOT_COFFEE_CAULDRON_BEHAVIOR =
            Util.make(new Object2ObjectOpenHashMap<>(), map ->
                    map.defaultReturnValue(
                            (state, world, pos, player, hand, stack) -> ActionResult.PASS
                    )
            );

    public static final Object2ObjectOpenHashMap<Item, CauldronBehavior> HOT_MILK_CAULDRON_BEHAVIOR =
            Util.make(new Object2ObjectOpenHashMap<>(), map ->
                    map.defaultReturnValue(
                            (state, world, pos, player, hand, stack) -> ActionResult.PASS
                    )
            );

    public static final Object2ObjectOpenHashMap<Item, CauldronBehavior> HOT_CHOCOLATE_CAULDRON_BEHAVIOR =
            Util.make(new Object2ObjectOpenHashMap<>(), map ->
                    map.defaultReturnValue(
                            (state, world, pos, player, hand, stack) -> ActionResult.PASS
                    )
            );

    private static CauldronBehavior emptyOneLevel(Item returnItem) {
        return (state, world, pos, player, hand, stack) -> {
            if (!world.isClient()) {
                int level = state.get(LeveledCauldronBlock.LEVEL);
                if (!player.isCreative()) {
                    stack.decrement(1);
                }
                if (!player.getInventory().insertStack(returnItem.getDefaultStack())) {
                    player.dropItem(returnItem.getDefaultStack(), false);
                }
                if (level == 1) {
                    world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                } else {
                    world.setBlockState(pos, state.with(LeveledCauldronBlock.LEVEL, level - 1));
                }
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(state));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            return ActionResult.success(world.isClient());
        };
    }

    private static CauldronBehavior emptyFull(Item returnItem) {
        return (state, world, pos, player, hand, stack) -> {
            if (state.get(LeveledCauldronBlock.LEVEL) != 3) {
                return ActionResult.PASS;
            }
            if (!world.isClient()) {
                if (!player.isCreative()) {
                    stack.decrement(1);
                }
                if (!player.getInventory().insertStack(returnItem.getDefaultStack())) {
                    player.dropItem(returnItem.getDefaultStack(), false);
                }
                world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(Blocks.CAULDRON.getDefaultState()));
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            return ActionResult.success(world.isClient());
        };
    }

    private static CauldronBehavior fillOneLevel(Block cauldronBlock, Item returnItem) {
        return (state, world, pos, player, hand, stack) -> {
            if (!world.isClient()) {
                if (!player.isCreative()) {
                    stack.decrement(1);
                }
                ItemStack emptyBottle = returnItem.getDefaultStack();
                if (!player.getInventory().insertStack(emptyBottle)) {
                    player.dropItem(emptyBottle, false);
                }
                world.setBlockState(pos, cauldronBlock.getDefaultState().with(LeveledCauldronBlock.LEVEL, 1));
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(cauldronBlock.getDefaultState()));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1, 1);
            }
            return ActionResult.success(world.isClient());
        };
    }

    private static ActionResult fillCauldron(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack) {
        int level = state.get(LeveledCauldronBlock.LEVEL);
        if (level >= 3) {
            return ActionResult.PASS;
        }
        if (!world.isClient()) {
            ItemStack bottle = Items.GLASS_BOTTLE.getDefaultStack();
            if (stack.isOf(ModItems.BEER_CAN)) {
                bottle = ModItems.DRINK_CAN.getDefaultStack();
            }
            if (!player.isCreative()) {
                stack.decrement(1);
            }

            if (!player.getInventory().insertStack(bottle)) {
                player.dropItem(bottle, false);
            }
            world.setBlockState(pos, state.with(LeveledCauldronBlock.LEVEL, level + 1));
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(state));
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
        return ActionResult.success(world.isClient());
    }

    private static CauldronBehavior fillFull(Block cauldronBlock, Item returnItem) {
        return (state, world, pos, player, hand, stack) -> {
            if (!world.isClient()) {
                if (!player.isCreative()) {
                    stack.decrement(1);
                }
                ItemStack emptyStein = returnItem.getDefaultStack();
                if (!player.getInventory().insertStack(emptyStein)) {
                    player.dropItem(emptyStein, false);
                }
                world.setBlockState(pos, cauldronBlock.getDefaultState().with(LeveledCauldronBlock.LEVEL, 3));
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(cauldronBlock.getDefaultState()));
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1, 1);
            }
            return ActionResult.success(world.isClient());
        };
    }

    private static CauldronBehavior brewBehavior(Block cauldronBlock) {
        return (state, world, pos, player, hand, stack) -> {
            if (!world.isClient()) {
                if (!player.isCreative()) {
                    stack.decrement(1);
                }
                int level = state.get(LeveledCauldronBlock.LEVEL);
                world.setBlockState(pos, cauldronBlock.getDefaultState().with(LeveledCauldronBlock.LEVEL, level));
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(cauldronBlock.getDefaultState()));
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1, 1);
            }
            return ActionResult.success(world.isClient());
        };
    }

    private static CauldronBehavior brewMeadBehavior(Block cauldronBlock) {
        return (state, world, pos, player, hand, stack) -> {
            int level = state.get(LeveledCauldronBlock.LEVEL);
            if (level > 1 && !(stack.getCount() > 1)) {
                return ActionResult.PASS;
            }
            if (!world.isClient()) {
                if (!player.isCreative()) {
                    stack.decrement(Math.min(level, 2));
                }
                ItemStack bottle = Items.GLASS_BOTTLE.getDefaultStack();
                bottle.setCount(Math.min(level, 2));
                if (!player.getInventory().insertStack(bottle)) {
                    player.dropItem(bottle, false);
                }
                world.setBlockState(pos, cauldronBlock.getDefaultState().with(LeveledCauldronBlock.LEVEL, level));
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(cauldronBlock.getDefaultState()));
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1, 1);
            }
            return ActionResult.success(world.isClient());
        };
    }

    public static void register() {

        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(
                ModBlocks.WOODEN_WATER_STEIN.asItem(),
                fillFull(Blocks.WATER_CAULDRON, ModBlocks.WOODEN_STEIN.asItem())
        );
        CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(
                ModBlocks.WOODEN_STEIN.asItem(),
                emptyFull(ModBlocks.WOODEN_WATER_STEIN.asItem())
        );

        CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(
                Items.WHEAT,
                brewBehavior(ModBlocks.BEER_CAULDRON)
        );
        CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(
                Items.HONEY_BOTTLE,
                brewMeadBehavior(ModBlocks.MEAD_CAULDRON)
        );

        BOILING_WATER_CAULDRON_BEHAVIOR.put(
                ModItems.GROUND_COFFEE_BEANS,
                brewBehavior(ModBlocks.HOT_COFFEE_CAULDRON)
        );

        HOT_MILK_CAULDRON_BEHAVIOR.put(
                Items.COCOA_BEANS,
                brewBehavior(ModBlocks.HOT_CHOCOLATE_CAULDRON)
        );

        BEER_CAULDRON_BEHAVIOR.put(
                Items.GLASS_BOTTLE,
                emptyOneLevel(ModItems.BEER_BOTTLE)
        );
        BEER_CAULDRON_BEHAVIOR.put(
                ModItems.DRINK_CAN,
                emptyOneLevel(ModItems.BEER_CAN)
        );
        BEER_CAULDRON_BEHAVIOR.put(
                Items.BUCKET,
                emptyFull(ModFluids.BEER_BUCKET)
        );
        BEER_CAULDRON_BEHAVIOR.put(
                ModItems.BEER_BOTTLE,
                ModCauldronBehaviors::fillCauldron
        );
        BEER_CAULDRON_BEHAVIOR.put(
                ModItems.BEER_CAN,
                ModCauldronBehaviors::fillCauldron
        );
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(
                ModItems.BEER_BOTTLE,
                fillOneLevel(ModBlocks.BEER_CAULDRON, Items.GLASS_BOTTLE)
        );
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(
                ModItems.BEER_CAN,
                fillOneLevel(ModBlocks.BEER_CAULDRON, ModItems.DRINK_CAN)
        );
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(
                ModBlocks.WOODEN_BEER_STEIN.asItem(),
                fillFull(ModBlocks.BEER_CAULDRON, ModBlocks.WOODEN_STEIN.asItem())
        );
        BEER_CAULDRON_BEHAVIOR.put(
                ModBlocks.WOODEN_STEIN.asItem(),
                emptyFull(ModBlocks.WOODEN_BEER_STEIN.asItem())
        );
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(
                ModItems.BEER_GOAT_HORN,
                fillFull(ModBlocks.BEER_CAULDRON, Items.GOAT_HORN)
        );
        BEER_CAULDRON_BEHAVIOR.put(
                Items.GOAT_HORN,
                emptyFull(ModItems.BEER_GOAT_HORN)
        );

        MEAD_CAULDRON_BEHAVIOR.put(
                Items.GLASS_BOTTLE,
                emptyOneLevel(ModItems.MEAD_BOTTLE)
        );
        MEAD_CAULDRON_BEHAVIOR.put(
                Items.BUCKET,
                emptyFull(ModFluids.MEAD_BUCKET)
        );
        MEAD_CAULDRON_BEHAVIOR.put(
                ModItems.MEAD_BOTTLE,
                ModCauldronBehaviors::fillCauldron
        );
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(
                ModItems.MEAD_BOTTLE,
                fillOneLevel(ModBlocks.MEAD_CAULDRON, Items.GLASS_BOTTLE)
        );
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(
                ModBlocks.WOODEN_MEAD_STEIN.asItem(),
                fillFull(ModBlocks.MEAD_CAULDRON, ModBlocks.WOODEN_STEIN.asItem())
        );
        MEAD_CAULDRON_BEHAVIOR.put(
                ModBlocks.WOODEN_STEIN.asItem(),
                emptyFull(ModBlocks.WOODEN_MEAD_STEIN.asItem())
        );
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(
                ModItems.MEAD_GOAT_HORN,
                fillFull(ModBlocks.MEAD_CAULDRON, Items.GOAT_HORN)
        );
        MEAD_CAULDRON_BEHAVIOR.put(
                Items.GOAT_HORN,
                emptyFull(ModItems.MEAD_GOAT_HORN)
        );

        BLOOD_CAULDRON_BEHAVIOR.put(
                Items.GLASS_BOTTLE,
                emptyOneLevel(ModItems.BLOOD_BOTTLE)
        );
        BLOOD_CAULDRON_BEHAVIOR.put(
                Items.BUCKET,
                emptyFull(ModFluids.BLOOD_BUCKET)
        );
        BLOOD_CAULDRON_BEHAVIOR.put(
                ModItems.BLOOD_BOTTLE,
                ModCauldronBehaviors::fillCauldron
        );
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(
                ModItems.BLOOD_BOTTLE,
                fillOneLevel(ModBlocks.BLOOD_CAULDRON, Items.GLASS_BOTTLE)
        );

        BOILING_WATER_CAULDRON_BEHAVIOR.put(
                Items.GLASS_BOTTLE,
                emptyOneLevel(Items.POTION)
        );
        BOILING_WATER_CAULDRON_BEHAVIOR.put(
                Items.BUCKET,
                emptyFull(ModFluids.BOILING_WATER_BUCKET)
        );
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(
                ModBlocks.WOODEN_BOILING_WATER_STEIN.asItem(),
                fillFull(ModBlocks.BOILING_WATER_CAULDRON, ModBlocks.WOODEN_STEIN.asItem())
        );
        BOILING_WATER_CAULDRON_BEHAVIOR.put(
                ModBlocks.WOODEN_STEIN.asItem(),
                emptyFull(ModBlocks.WOODEN_BOILING_WATER_STEIN.asItem())
        );

        HOT_COFFEE_CAULDRON_BEHAVIOR.put(
                Items.BUCKET,
                emptyFull(ModFluids.HOT_COFFEE_BUCKET)
        );
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(
                ModBlocks.WOODEN_HOT_COFFEE_STEIN.asItem(),
                fillFull(ModBlocks.HOT_COFFEE_CAULDRON, ModBlocks.WOODEN_STEIN.asItem())
        );
        HOT_COFFEE_CAULDRON_BEHAVIOR.put(
                ModBlocks.WOODEN_STEIN.asItem(),
                emptyFull(ModBlocks.WOODEN_HOT_COFFEE_STEIN.asItem())
        );

        HOT_MILK_CAULDRON_BEHAVIOR.put(
                Items.BUCKET,
                emptyFull(ModFluids.HOT_MILK_BUCKET)
        );
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(
                ModBlocks.HOT_WOODEN_MILK_STEIN.asItem(),
                fillFull(ModBlocks.HOT_MILK_CAULDRON, ModBlocks.WOODEN_STEIN.asItem())
        );
        HOT_MILK_CAULDRON_BEHAVIOR.put(
                ModBlocks.WOODEN_STEIN.asItem(),
                emptyFull(ModBlocks.HOT_WOODEN_MILK_STEIN.asItem())
        );

        HOT_CHOCOLATE_CAULDRON_BEHAVIOR.put(
                Items.BUCKET,
                emptyFull(ModFluids.HOT_CHOCOLATE_BUCKET)
        );
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(
                ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN.asItem(),
                fillFull(ModBlocks.HOT_CHOCOLATE_CAULDRON, ModBlocks.WOODEN_STEIN.asItem())
        );
        HOT_CHOCOLATE_CAULDRON_BEHAVIOR.put(
                ModBlocks.WOODEN_STEIN.asItem(),
                emptyFull(ModBlocks.WOODEN_HOT_CHOCOLATE_STEIN.asItem())
        );
    }
}