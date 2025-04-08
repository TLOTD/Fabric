package net.tlotd.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.sound.ModSounds;
import net.tlotd.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExtractionPickaxeItem extends PickaxeItem {
    public ExtractionPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            BlockState state = context.getWorld().getBlockState(positionClicked);
            if (state.isIn(ModTags.Blocks.EXTRACTABLE_BLOCKS)) {
                context.getWorld().breakBlock(positionClicked, true);
                if (state.isIn(ModTags.Blocks.STONE_EXTRACTABLE_BLOCKS)) {
                    context.getWorld().setBlockState(positionClicked, Blocks.STONE.getDefaultState());
                } else if (state.isIn(ModTags.Blocks.ANDESITE_EXTRACTABLE_BLOCKS)) {
                    context.getWorld().setBlockState(positionClicked, Blocks.ANDESITE.getDefaultState());
                } else if (state.isIn(ModTags.Blocks.DIORITE_EXTRACTABLE_BLOCKS)) {
                    context.getWorld().setBlockState(positionClicked, Blocks.DIORITE.getDefaultState());
                } else if (state.isIn(ModTags.Blocks.GRANITE_EXTRACTABLE_BLOCKS)) {
                    context.getWorld().setBlockState(positionClicked, Blocks.GRANITE.getDefaultState());
                } else if (state.isIn(ModTags.Blocks.TUFF_EXTRACTABLE_BLOCKS)) {
                    context.getWorld().setBlockState(positionClicked, Blocks.TUFF.getDefaultState());
                } else if (state.isIn(ModTags.Blocks.CALCITE_EXTRACTABLE_BLOCKS)) {
                    context.getWorld().setBlockState(positionClicked, Blocks.CALCITE.getDefaultState());
                } else if (state.isIn(ModTags.Blocks.DEEPSLATE_EXTRACTABLE_BLOCKS)) {
                    context.getWorld().setBlockState(positionClicked, Blocks.DEEPSLATE.getDefaultState());
                } else if (state.isIn(ModTags.Blocks.RED_DEEPSLATE_EXTRACTABLE_BLOCKS)) {
                    context.getWorld().setBlockState(positionClicked, ModBlocks.RED_DEEPSLATE.getDefaultState());
                } else if (state.isIn(ModTags.Blocks.BEDROCK_EXTRACTABLE_BLOCKS)) {
                    context.getWorld().setBlockState(positionClicked, Blocks.BEDROCK.getDefaultState());
                } else if (state.isIn(ModTags.Blocks.END_STONE_EXTRACTABLE_BLOCKS)) {
                    context.getWorld().setBlockState(positionClicked, Blocks.END_STONE.getDefaultState());
                } else if (state.isIn(ModTags.Blocks.MOON_ROCK_EXTRACTABLE_BLOCKS)) {
                    context.getWorld().setBlockState(positionClicked, ModBlocks.MOON_ROCK.getDefaultState());
                } else if (state.isIn(ModTags.Blocks.NETHERRACK_EXTRACTABLE_BLOCKS)) {
                    context.getWorld().setBlockState(positionClicked, Blocks.NETHERRACK.getDefaultState());
                } else if (state.isIn(ModTags.Blocks.BASALT_EXTRACTABLE_BLOCKS)) {
                    context.getWorld().setBlockState(positionClicked, Blocks.BASALT.getDefaultState());
                } else if (state.isIn(ModTags.Blocks.BLACKSTONE_EXTRACTABLE_BLOCKS)) {
                    context.getWorld().setBlockState(positionClicked, Blocks.BLACKSTONE.getDefaultState());
                }
                context.getWorld().playSound(null, positionClicked, ModSounds.ITEM_PICKAXE_EXTRACT, SoundCategory.BLOCKS, 1.0f, 1.0f);
                int extraction_damage = 300;
                if (context.getStack().isIn(ModTags.Items.EXTRACTION_II_PICKAXES)) {
                    extraction_damage = 100;
                } else if (context.getStack().isIn(ModTags.Items.EXTRACTION_III_PICKAXES)) {
                    extraction_damage = 30;
                }
                context.getStack().damage(extraction_damage, player, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
                if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                    Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, context.getStack());
                    serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.isIn(ModTags.Items.EXTRACTION_III_PICKAXES)) {
            tooltip.add(Text.translatable("item.tlotd.extraction.tooltip").append(Text.literal(" ").append(Text.translatable("enchantment.level.3"))).formatted(Formatting.GRAY));
        } else if (stack.isIn(ModTags.Items.EXTRACTION_II_PICKAXES)) {
            tooltip.add(Text.translatable("item.tlotd.extraction.tooltip").append(Text.literal(" ").append(Text.translatable("enchantment.level.2"))).formatted(Formatting.GRAY));
        } else {
            tooltip.add(Text.translatable("item.tlotd.extraction.tooltip").append(Text.literal(" ").append(Text.translatable("enchantment.level.1"))).formatted(Formatting.GRAY));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
