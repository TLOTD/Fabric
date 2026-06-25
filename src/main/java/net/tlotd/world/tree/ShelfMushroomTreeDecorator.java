package net.tlotd.world.tree;

import com.mojang.serialization.Codec;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.tlotd.block.ModBlocks;
import net.tlotd.block.custom.ShelfMushroomBlock;

public class ShelfMushroomTreeDecorator extends TreeDecorator {
    public static final ShelfMushroomTreeDecorator INSTANCE = new ShelfMushroomTreeDecorator();
    public static final Codec<ShelfMushroomTreeDecorator> CODEC = Codec.unit(() -> INSTANCE);

    @Override
    protected TreeDecoratorType<?> getType() {
        return ModTreeDecoratorTypes.SHELF_MUSHROOM;
    }

    @Override
    public void generate(Generator generator) {
        Random random = generator.getRandom();
        for (BlockPos logPos : generator.getLogPositions()) {
            if (random.nextFloat() < 0.15f) {
                Direction dir = Direction.Type.HORIZONTAL.random(random);
                BlockPos fruitPos = logPos.offset(dir);
                if (generator.isAir(fruitPos)) {
                    generator.replace(fruitPos, ModBlocks.SHELF_MUSHROOM.getDefaultState().with(HorizontalFacingBlock.FACING, dir).with(ShelfMushroomBlock.AGE, random.nextInt(2)));
                }
            }
        }
    }
}