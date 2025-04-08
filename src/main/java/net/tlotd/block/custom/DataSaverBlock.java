package net.tlotd.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class DataSaverBlock extends Block {

    public static final BooleanProperty FREQUENCY_1 = BooleanProperty.of("frequency_1");
    public static final BooleanProperty FREQUENCY_2 = BooleanProperty.of("frequency_2");
    public static final BooleanProperty FREQUENCY_3 = BooleanProperty.of("frequency_3");
    public static final BooleanProperty FREQUENCY_4 = BooleanProperty.of("frequency_4");
    public static final BooleanProperty CHANNEL_1 = BooleanProperty.of("channel_1");
    public static final BooleanProperty CHANNEL_2 = BooleanProperty.of("channel_2");
    public static final BooleanProperty CHANNEL_3 = BooleanProperty.of("channel_3");
    public static final BooleanProperty CHANNEL_4 = BooleanProperty.of("channel_4");
    public static final BooleanProperty CHANNEL_5 = BooleanProperty.of("channel_5");
    public static final BooleanProperty CHANNEL_6 = BooleanProperty.of("channel_6");
    public static final BooleanProperty CHANNEL_7 = BooleanProperty.of("channel_7");
    public static final BooleanProperty CHANNEL_8 = BooleanProperty.of("channel_8");
    public static final BooleanProperty CHANNEL_9 = BooleanProperty.of("channel_9");

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FREQUENCY_1, FREQUENCY_2, FREQUENCY_3, FREQUENCY_4, CHANNEL_1,CHANNEL_2,CHANNEL_3,CHANNEL_4,CHANNEL_5,CHANNEL_6,CHANNEL_7,CHANNEL_8,CHANNEL_9);
    }

    public DataSaverBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FREQUENCY_1, false).with(FREQUENCY_2, false).with(FREQUENCY_3, false).with(FREQUENCY_4, false).with(CHANNEL_1, false).with(CHANNEL_2, false).with(CHANNEL_3, false).with(CHANNEL_4, false).with(CHANNEL_5, false).with(CHANNEL_6, false).with(CHANNEL_7, false).with(CHANNEL_8, false).with(CHANNEL_9, false));
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return Blocks.BEDROCK.asItem().getDefaultStack();
    }
}
