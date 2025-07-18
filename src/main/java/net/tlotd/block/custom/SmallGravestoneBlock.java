package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Objects;

public class SmallGravestoneBlock extends GravestoneBlock {

    public static final BooleanProperty SPECIAL = BooleanProperty.of("special");

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        boolean special = false;
        if (!Objects.requireNonNull(ctx.getPlayer()).isSneaking()) {
            if (ctx.getPlayer().getUuid().toString().equals("67148bd0-1a00-4bca-9d9e-ec246afbcf51")) {
                special = true;
            }
        }
        return this.getDefaultState()
                .with(SPECIAL, special)
                .with(FACING, ctx.getHorizontalPlayerFacing())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, SPECIAL);
    }

    public SmallGravestoneBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(SPECIAL, false).with(WATERLOGGED, false));
    }

    public static final VoxelShape Z_SHAPE = Block.createCuboidShape(6, 0, 1, 10, 13, 15);
    public static final VoxelShape X_SHAPE = Block.createCuboidShape(1, 0, 6, 15, 13, 10);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case EAST, WEST -> Z_SHAPE;
            default -> X_SHAPE;
        };
    }

    public static final Identifier DEFAULT_FONT_ID = new Identifier("minecraft", "default");

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(SPECIAL) && player.isSneaking()) {
            if (!world.isClient) {
                player.sendMessage(Text.translatable("block.tlotd.small_gravestone.special").formatted(Formatting.GRAY));
                player.sendMessage(Text.translatable("block.tlotd.small_gravestone.special_2").formatted(Formatting.GRAY));
                player.sendMessage(Text.translatable("block.tlotd.small_gravestone.special_3").formatted(Formatting.GRAY));
                player.sendMessage(Text.translatable("block.tlotd.small_gravestone.special_4").formatted(Formatting.GRAY));

            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}
