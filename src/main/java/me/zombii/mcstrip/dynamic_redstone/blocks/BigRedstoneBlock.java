package me.zombii.mcstrip.dynamic_redstone.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class BigRedstoneBlock extends Block {
    public static final MapCodec<BigRedstoneBlock> CODEC = createCodec(BigRedstoneBlock::new);

    public MapCodec<BigRedstoneBlock> getCodec() {
        return CODEC;
    }

    public BigRedstoneBlock(Settings settings) {
        super(settings);
    }

    protected boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    protected int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return ImprovedRedstoneWireBlock.MaxStrength - 1;
    }

}
