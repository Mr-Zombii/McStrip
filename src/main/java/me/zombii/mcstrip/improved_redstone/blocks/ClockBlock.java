package me.zombii.mcstrip.improved_redstone.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

public class ClockBlock extends HorizontalFacingBlock {
    public static final MapCodec<ClockBlock> CODEC = createCodec(ClockBlock::new);
    private static final BooleanProperty POWERED;
    private static final IntProperty DELAY;

    public ClockBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        Direction direction = state.get(FACING);

        new BlockPos(pos).add();
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, DELAY, POWERED);
    }

    static {
        POWERED = Properties.POWERED;
        DELAY = Properties.DELAY;
    }
}
