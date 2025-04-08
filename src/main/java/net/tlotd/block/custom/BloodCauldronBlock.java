package net.tlotd.block.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import net.tlotd.block.ModBlocks;
import net.tlotd.fluid.ModFluids;
import net.tlotd.item.ModItems;

public class BloodCauldronBlock extends Block {

    public static final IntProperty LEVEL = IntProperty.of("level", 1, 3);

    public static void decrementFluidLevel(BlockState state, World world, BlockPos pos) {
        int i = state.get(LEVEL) - 1;
        BlockState blockState = i == 0 ? Blocks.CAULDRON.getDefaultState() : (BlockState)state.with(LEVEL, i);
        world.setBlockState(pos, blockState);
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(LEVEL, 1);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }

    public BloodCauldronBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LEVEL,1));
    }

    private static final VoxelShape SHAPE = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), VoxelShapes.union(createCuboidShape(0.0F, 0.0F, 4.0F, 16.0F, 3.0F, 12.0F), new VoxelShape[]{createCuboidShape(4.0F, 0.0F, 0.0F, 12.0F, 3.0F, 16.0F), createCuboidShape(2.0F, 0.0F, 2.0F, 14.0F, 3.0F, 14.0F), createCuboidShape(2.0F, 4.0F, 2.0F, 14.0F, 16.0F, 14.0F)}), BooleanBiFunction.ONLY_FIRST);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            if (state.get(LEVEL) == 3 && (player.getMainHandStack().getItem() == Items.BUCKET || player.getOffHandStack().getItem() == Items.BUCKET)) {
                world.setBlockState(pos, Blocks.CAULDRON.getStateWithProperties(state));
                if (player.getMainHandStack().getItem() == Items.BUCKET) {
                    player.getMainHandStack().decrement(1);

                } else if (player.getOffHandStack().getItem() == Items.BUCKET) {
                    player.getOffHandStack().decrement(1);
                }
                player.incrementStat(Stats.USED.getOrCreateStat(Items.BUCKET));
                player.getItemCooldownManager().set(ModFluids.BLOOD_BUCKET, 1);
                player.giveItemStack(ModFluids.BLOOD_BUCKET.getDefaultStack());
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                return ActionResult.SUCCESS;
            }
            if (!player.getItemCooldownManager().isCoolingDown(Items.GLASS_BOTTLE) && (player.getMainHandStack().getItem() == Items.GLASS_BOTTLE || player.getOffHandStack().getItem() == Items.GLASS_BOTTLE)) {
                decrementFluidLevel(state, world, pos);
                if (player.getMainHandStack().getItem() == Items.GLASS_BOTTLE) {
                    player.getMainHandStack().decrement(1);

                } else if (player.getOffHandStack().getItem() == Items.GLASS_BOTTLE) {
                    player.getOffHandStack().decrement(1);
                }
                player.incrementStat(Stats.USED.getOrCreateStat(Items.GLASS_BOTTLE));
                player.getItemCooldownManager().set(Items.GLASS_BOTTLE, 1);
                player.giveItemStack(ModItems.BLOOD_BOTTLE.getDefaultStack());
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.PLAYERS, 1.0f, 1.0f);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return Items.CAULDRON.getDefaultStack();
    }
}
