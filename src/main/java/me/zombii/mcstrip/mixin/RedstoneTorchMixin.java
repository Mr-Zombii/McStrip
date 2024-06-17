package me.zombii.mcstrip.mixin;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(RedstoneTorchBlock.class)
public class RedstoneTorchMixin extends AbstractTorchBlock {

    @Shadow
    public static final BooleanProperty LIT;
    @Shadow
    public static final MapCodec<RedstoneTorchBlock> CODEC = createCodec(RedstoneTorchBlock::new);

    protected RedstoneTorchMixin(Settings settings) {
        super(settings);
    }

    @Shadow
    protected MapCodec<? extends AbstractTorchBlock> getCodec() {
        return null;
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if ((Boolean)state.get(LIT) == this.shouldUnpower(world, pos, state) && !world.getBlockTickScheduler().isTicking(pos, this)) {
            world.scheduleBlockTick(pos, this, -1);
        }
    }

    @Shadow
    public boolean shouldUnpower(World world, BlockPos pos, BlockState state) {
        return world.isEmittingRedstonePower(pos.down(), Direction.DOWN);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        boolean bl = this.shouldUnpower(world, pos, state);

        if ((Boolean)state.get(LIT)) {
            if (bl) {
                world.setBlockState(pos, (BlockState)state.with(LIT, false), 3);
            }
        } else
            world.setBlockState(pos, (BlockState)state.with(LIT, true), 3);
    }

    static {
        LIT = Properties.LIT;
    }
}
