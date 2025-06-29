package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ArchaeologyTableBlock extends Block {
    public ArchaeologyTableBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!world.isClient) {
            if (itemStack.isOf(ModItems.PLANT_FOSSIL)) {
                itemStack.decrement(1);
                int itemInt = ThreadLocalRandom.current().nextInt(1, 9);
                ItemStack randomItem = new ItemStack(ModBlocks.ROSE);
                switch (itemInt) {
                    case 2 -> randomItem = ModBlocks.IRIS.asItem().getDefaultStack();
                    case 3 -> randomItem = ModBlocks.EDELWEISS.asItem().getDefaultStack();
                    case 4 -> randomItem = ModBlocks.ATHELAS.asItem().getDefaultStack();
                    case 5 -> randomItem = ModBlocks.GINKGO_SAPLING.asItem().getDefaultStack();
                    case 6 -> randomItem = ModItems.STRAWBERRY_SEEDS.getDefaultStack();
                    case 7 -> randomItem = ModItems.ORANGE_SEEDS.getDefaultStack();
                    case 8 -> randomItem = ModItems.CANNABIS_SEEDS.getDefaultStack();
                }
                Block.dropStack(world, pos.up(), randomItem);
                world.playSound(null, pos, SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("block.tlotd.archaeology_table.tooltip").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.archaeology_table.tooltip_2")).formatted(Formatting.BLUE));
        tooltip.add(Text.literal(" ").append(Text.translatable("block.tlotd.archaeology_table.tooltip_3")).formatted(Formatting.BLUE));
        super.appendTooltip(stack, world, tooltip, options);
    }
}
