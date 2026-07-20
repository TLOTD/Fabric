package net.tlotd.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.tlotd.block.ModBlocks;

public class CauldronBucketItem extends BucketItem {

    public CauldronBucketItem(Fluid fluid, Settings settings) {
        super(fluid, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        BlockHitResult hit = raycast(world, user, RaycastContext.FluidHandling.NONE);
        if (hit.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = hit.getBlockPos();
            BlockState state = world.getBlockState(pos);
            if (!user.isSneaking() && state.isOf(Blocks.CAULDRON)) {
                if (!world.isClient) {
                    Block cauldron = getBlock(stack);
                    world.setBlockState(pos, cauldron.getDefaultState().with(LeveledCauldronBlock.LEVEL, 3));
                    if (!user.getAbilities().creativeMode) {
                        user.setStackInHand(hand, new ItemStack(Items.BUCKET));
                    }
                    world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.emitGameEvent(user, GameEvent.FLUID_PLACE, pos);
                }
                return TypedActionResult.success(stack, world.isClient());
            }
        }
        return super.use(world, user, hand);
    }

    private static Block getBlock(ItemStack stack) {
        Block cauldron = ModBlocks.BLOOD_CAULDRON;
        if (stack.isOf(ModFluids.BEER_BUCKET)) {
            cauldron = ModBlocks.BEER_CAULDRON;
        } else if (stack.isOf(ModFluids.MEAD_BUCKET)) {
            cauldron = ModBlocks.MEAD_CAULDRON;
        } else if (stack.isOf(ModFluids.BOILING_WATER_BUCKET)) {
            cauldron = ModBlocks.BOILING_WATER_CAULDRON;
        } else if (stack.isOf(ModFluids.HOT_COFFEE_BUCKET)) {
            cauldron = ModBlocks.HOT_COFFEE_CAULDRON;
        } if (stack.isOf(ModFluids.HOT_MILK_BUCKET)) {
            cauldron = ModBlocks.HOT_MILK_CAULDRON;
        } if (stack.isOf(ModFluids.HOT_CHOCOLATE_BUCKET)) {
            cauldron = ModBlocks.HOT_CHOCOLATE_CAULDRON;
        }
        return cauldron;
    }
}