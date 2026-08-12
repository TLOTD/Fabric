package net.tlotd.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tlotd.block.ModBlocks;
import net.tlotd.item.ModItems;
import net.tlotd.sound.ModSounds;
import net.tlotd.util.ModTags;
import net.tlotd.world.ModGlobalState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.tlotd.block.custom.AlienControlPanelBlock.HARVESTED;
import static net.tlotd.util.AugmentNbtHelper.getAugmentLevel;

public class PaxelItem extends MiningToolItem {
    public PaxelItem(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings) {
        super((float) attackDamage, attackSpeed, material, ModTags.Blocks.PAXEL_MINEABLE, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            BlockState state = context.getWorld().getBlockState(positionClicked);
            if ((state.isOf(Blocks.DIRT) || state.isOf(Blocks.GRASS_BLOCK)) && context.getWorld().getBlockState(positionClicked.up()).isOf(Blocks.AIR)) {
                if (player.isSneaking()) {
                    context.getWorld().setBlockState(positionClicked, Blocks.DIRT_PATH.getDefaultState());
                    context.getWorld().playSound(null, positionClicked, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.PLAYERS, 1.0f, 1.0f);
                } else {
                    context.getWorld().setBlockState(positionClicked, Blocks.FARMLAND.getDefaultState());
                    context.getWorld().playSound(null, positionClicked, SoundEvents.ITEM_HOE_TILL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                }
                context.getStack().damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
                if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                    Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, context.getStack());
                    serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
                return ActionResult.SUCCESS;
            } else if ((state.isOf(ModBlocks.RICH_DIRT) || state.isOf(ModBlocks.RICH_GRASS_BLOCK)) && context.getWorld().getBlockState(positionClicked.up()).isOf(Blocks.AIR)) {
                if (player.isSneaking()) {
                    context.getWorld().setBlockState(positionClicked, ModBlocks.RICH_DIRT_PATH.getDefaultState());
                    context.getWorld().playSound(null, positionClicked, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.PLAYERS, 1.0f, 1.0f);
                } else {
                    context.getWorld().setBlockState(positionClicked, ModBlocks.RICH_FARMLAND.getDefaultState());
                    context.getWorld().playSound(null, positionClicked, SoundEvents.ITEM_HOE_TILL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                }
                context.getStack().damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
                if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                    Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, context.getStack());
                    serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                }
                return ActionResult.SUCCESS;
            } else if (getAugmentLevel(context.getStack(), "tlotd:extraction") > 0) {
                boolean compat = false;
                if (player != null && !context.getWorld().isClient && player.getServer() != null) {
                    ModGlobalState globalState = ModGlobalState.get(player.getServer());
                    compat = globalState.extractionOreCompat();
                }
                String name = state.getBlock().getTranslationKey();
                int damage = 1;
                int miningLevel = this.getMaterial().getMiningLevel();
                int blockLevel = 0;
                if (state.isIn(ModTags.Blocks.TOOL_LEVEL_10)) {
                    blockLevel = 10;
                } else if (state.isIn(ModTags.Blocks.TOOL_LEVEL_9)) {
                    blockLevel = 9;
                } else if (state.isIn(ModTags.Blocks.TOOL_LEVEL_8)) {
                    blockLevel = 8;
                } else if (state.isIn(ModTags.Blocks.TOOL_LEVEL_7)) {
                    blockLevel = 7;
                } else if (state.isIn(ModTags.Blocks.TOOL_LEVEL_6)) {
                    blockLevel = 6;
                } else if (state.isIn(ModTags.Blocks.TOOL_LEVEL_5)) {
                    blockLevel = 5;
                } else if (state.isIn(ModTags.Blocks.TOOL_LEVEL_4)) {
                    blockLevel = 4;
                } else if (state.isIn(BlockTags.NEEDS_DIAMOND_TOOL)) {
                    blockLevel = 3;
                } else if (state.isIn(BlockTags.NEEDS_IRON_TOOL)) {
                    blockLevel = 2;
                } else if (state.isIn(BlockTags.NEEDS_STONE_TOOL)) {
                    blockLevel = 1;
                }
                if (miningLevel >= blockLevel && (state == ModBlocks.ALIEN_CONTROL_PANEL.getStateWithProperties(state).with(HARVESTED, false) || state.isIn(ModTags.Blocks.EXTRACTABLE_BLOCKS) || (compat && name.contains("_ore")))) {
                    context.getWorld().breakBlock(positionClicked, true);
                    if (state.isOf(ModBlocks.ALIEN_CONTROL_PANEL) && !state.get(HARVESTED)) {
                        context.getWorld().setBlockState(positionClicked, state.with(HARVESTED, true));
                        ItemStack circuit = ModItems.FRAGMENTED_FUTURISTIC_CIRCUIT_BOARD.getDefaultStack();
                        circuit.setCount(3);
                        Block.dropStack(context.getWorld(), context.getBlockPos().up(), circuit);
                    }
                    if (state.isIn(ModTags.Blocks.STONE_EXTRACTABLE_BLOCKS)) {
                        context.getWorld().setBlockState(positionClicked, Blocks.STONE.getDefaultState());
                    } else if (state.isIn(ModTags.Blocks.ANDESITE_EXTRACTABLE_BLOCKS) || (compat && name.contains("andesite") && name.contains("ore"))) {
                        context.getWorld().setBlockState(positionClicked, Blocks.ANDESITE.getDefaultState());
                    } else if (state.isIn(ModTags.Blocks.DIORITE_EXTRACTABLE_BLOCKS) || (compat && name.contains("diorite") && name.contains("ore"))) {
                        context.getWorld().setBlockState(positionClicked, Blocks.DIORITE.getDefaultState());
                    } else if (state.isIn(ModTags.Blocks.GRANITE_EXTRACTABLE_BLOCKS) || (compat && name.contains("granite") && name.contains("ore"))) {
                        context.getWorld().setBlockState(positionClicked, Blocks.GRANITE.getDefaultState());
                    } else if (state.isIn(ModTags.Blocks.TUFF_EXTRACTABLE_BLOCKS) || (compat && name.contains("tuff") && name.contains("ore"))) {
                        context.getWorld().setBlockState(positionClicked, Blocks.TUFF.getDefaultState());
                    } else if (state.isIn(ModTags.Blocks.CALCITE_EXTRACTABLE_BLOCKS) || (compat && name.contains("calcite") && name.contains("ore"))) {
                        context.getWorld().setBlockState(positionClicked, Blocks.CALCITE.getDefaultState());
                    } else if (state.isIn(ModTags.Blocks.RED_DEEPSLATE_EXTRACTABLE_BLOCKS) || (compat && name.contains("red_deepslate") && name.contains("ore"))) {
                        context.getWorld().setBlockState(positionClicked, ModBlocks.RED_DEEPSLATE.getDefaultState());
                        damage = 2;
                    } else if (state.isIn(ModTags.Blocks.DEEPSLATE_EXTRACTABLE_BLOCKS) || (compat && name.contains("deepslate") && name.contains("ore"))) {
                        context.getWorld().setBlockState(positionClicked, Blocks.DEEPSLATE.getDefaultState());
                        damage = 2;
                    } else if (state.isIn(ModTags.Blocks.BEDROCK_EXTRACTABLE_BLOCKS) || (compat && name.contains("bedrock") && name.contains("ore"))) {
                        context.getWorld().setBlockState(positionClicked, Blocks.BEDROCK.getDefaultState());
                        damage = 10;
                    } else if (state.isIn(ModTags.Blocks.END_STONE_EXTRACTABLE_BLOCKS) || (compat && name.contains("end") && name.contains("ore"))) {
                        context.getWorld().setBlockState(positionClicked, Blocks.END_STONE.getDefaultState());
                        damage = 5;
                    } else if (state.isIn(ModTags.Blocks.NETHERRACK_EXTRACTABLE_BLOCKS) || (compat && name.contains("nether") && name.contains("ore"))) {
                        context.getWorld().setBlockState(positionClicked, Blocks.NETHERRACK.getDefaultState());
                        damage = 3;
                    } else if (state.isIn(ModTags.Blocks.BASALT_EXTRACTABLE_BLOCKS) || (compat && name.contains("basalt") && name.contains("ore"))) {
                        context.getWorld().setBlockState(positionClicked, Blocks.BASALT.getDefaultState());
                        damage = 3;
                    } else if (state.isIn(ModTags.Blocks.BLACKSTONE_EXTRACTABLE_BLOCKS) || (compat && name.contains("blackstone") && name.contains("ore"))) {
                        context.getWorld().setBlockState(positionClicked, Blocks.BLACKSTONE.getDefaultState());
                        damage = 3;
                    } else if (state.isIn(ModTags.Blocks.MOON_ROCK_EXTRACTABLE_BLOCKS)) {
                        context.getWorld().setBlockState(positionClicked, ModBlocks.MEGAREGOLITH.getDefaultState());
                        damage = 5;
                    }
                    context.getWorld().playSound(null, positionClicked, ModSounds.ITEM_PICKAXE_EXTRACT, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    int extraction_damage = damage * 20;
                    if (getAugmentLevel(context.getStack(), "tlotd:extraction") >= 3) {
                        extraction_damage = damage;
                    } else if (getAugmentLevel(context.getStack(), "tlotd:extraction") == 2) {
                        extraction_damage = damage * 10;
                    }
                    context.getStack().damage(extraction_damage, player, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
                    if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                        Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, context.getStack());
                        serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                    }
                    return ActionResult.SUCCESS;
                }
            }
        }
        return ActionResult.FAIL;
    }
}