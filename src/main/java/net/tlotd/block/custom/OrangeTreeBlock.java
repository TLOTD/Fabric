package net.tlotd.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.tlotd.item.ModItems;

public class OrangeTreeBlock extends CropBlock {
    public static final IntProperty AGE = IntProperty.of("age",0,9);
    public static final BooleanProperty UPPER = BooleanProperty.of("upper");
    public static final BooleanProperty IGNORE = BooleanProperty.of("ignore");

    public static final int TOP_START = 5;

    public OrangeTreeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0).with(UPPER, false).with(IGNORE, false));
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.ORANGE_SEEDS;
    }

    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 9;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int currentAge = this.getAge(state);
            if (currentAge < this.getMaxAge()) {
                float f = getAvailableMoisture(this, world, pos);
                if (random.nextInt((int)(25.0F / f) + 1) == 0) {
                    if(currentAge < TOP_START-1) {
                        world.setBlockState(pos, this.withAge(currentAge + 1), 2);
                    } else if(!world.getBlockState(pos).get(UPPER) && (world.getBlockState(pos.up(1)).isReplaceable() || world.getBlockState(pos.up(1)).isOf(this))) {
                        world.setBlockState(pos, this.withAge(currentAge + 1).with(IGNORE,true), 0);
                        world.setBlockState(pos.up(1), this.withAge(currentAge + 1).with(UPPER,true), 2);
                        world.setBlockState(pos, this.withAge(currentAge + 1).with(IGNORE,false), 2);
                    }
                }
            }
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getType() == EntityType.BEE) {
            return;
        }
        entity.slowMovement(state, new Vec3d(0.8f, 0.75, 0.8f));
    }

    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int nextAge = this.getAge(state) + this.getGrowthAmount(world);
        int maxAge = this.getMaxAge();
        if (nextAge > maxAge) {
            nextAge = maxAge;
        }
        if (nextAge < TOP_START) {
            world.setBlockState(pos, this.withAge(nextAge), 2);
        } else if(!world.getBlockState(pos).get(UPPER) && (world.getBlockState(pos.up(1)).isReplaceable() || world.getBlockState(pos.up(1)).isOf(this))) {
            world.setBlockState(pos, this.withAge(nextAge).with(UPPER,false).with(IGNORE,true), 0);
            world.setBlockState(pos.up(1), this.withAge(nextAge).with(UPPER,true), 2);
            world.setBlockState(pos, this.withAge(nextAge).with(UPPER,false).with(IGNORE,false), 2);
        } else if (world.getBlockState(pos).get(UPPER)) {
            world.setBlockState(pos.down(1), this.withAge(nextAge).with(UPPER,false).with(IGNORE,true), 0);
            world.setBlockState(pos, this.withAge(nextAge).with(UPPER,true), 2);
            world.setBlockState(pos.down(1), this.withAge(nextAge).with(UPPER,false).with(IGNORE,false), 2);
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return (super.canPlaceAt(state, world, pos) && world.getBlockState(pos).isReplaceable()) ||
                (super.canPlaceAt(state, world, pos) && world.getBlockState(pos).isOf(this) && world.getBlockState(pos).get(AGE) < TOP_START) ||
                (super.canPlaceAt(state, world, pos) && world.getBlockState(pos).isOf(this) && world.getBlockState(pos).get(AGE) >= TOP_START && (world.getBlockState(pos).get(IGNORE) || world.getBlockState(pos.up(1)).isOf(this) && world.getBlockState(pos.up(1)).get(AGE) >= TOP_START)) ||
                (world.getBlockState(pos).isOf(this) && world.getBlockState(pos).get(UPPER) && (world.getBlockState(pos.down(1)).isOf(this) && !world.getBlockState(pos.down(1)).get(UPPER)) && world.getBlockState(pos.down(1)).get(AGE) >= TOP_START);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE, UPPER, IGNORE);
    }

    public static final VoxelShape STAGE_0 = Block.createCuboidShape(5, 0, 5, 11, 5, 11);
    public static final VoxelShape STAGE_1 = Block.createCuboidShape(3, 0, 3, 13, 7, 13);
    public static final VoxelShape STAGE_2 = Block.createCuboidShape(3, 0, 3, 13, 10, 13);
    public static final VoxelShape STAGE_3 = Block.createCuboidShape(2, 0, 2, 14, 12, 14);
    public static final VoxelShape STAGE_4 = Block.createCuboidShape(2, 0, 2, 14, 15, 14);
    public static final VoxelShape STAGE_5 = Block.createCuboidShape(3, 0, 3, 13, 2, 13);
    public static final VoxelShape STAGE_6 = Block.createCuboidShape(1, 0, 1, 15, 4, 15);
    public static final VoxelShape STAGE_7 = Block.createCuboidShape(1, 0, 1, 15, 10, 15);
    public static final VoxelShape STAGE_8 = Block.createCuboidShape(1, 0, 1, 15, 13, 15);
    public static final VoxelShape DEFAULT = Block.createCuboidShape(1, 0, 1, 15, 16, 15);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(AGE)) {
            case 0 -> STAGE_0;
            case 1 -> STAGE_1;
            case 2 -> STAGE_2;
            case 3 -> STAGE_3;
            case 4 -> STAGE_4;
            case 5 -> {if(state.get(UPPER)){yield STAGE_5;}else{yield DEFAULT;}}
            case 6 -> {if(state.get(UPPER)){yield STAGE_6;}else{yield DEFAULT;}}
            case 7 -> {if(state.get(UPPER)){yield STAGE_7;}else{yield DEFAULT;}}
            case 8,9 -> {if(state.get(UPPER)){yield STAGE_8;}else{yield DEFAULT;}}
            default -> DEFAULT;
        };
    }
}