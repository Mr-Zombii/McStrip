package me.zombii.mcstrip.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BulbBlock;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({BulbBlock.class})
public class OldCopperBulbMixin extends Block {
    public OldCopperBulbMixin(AbstractBlock.Settings settings) {
        super(settings);
    }

    /**
     * @author Mr_Zombii
     * @reason To make the copper bulbs fast again
     */
    @Overwrite
    public void update(BlockState state, ServerWorld world, BlockPos pos) {
        world.scheduleBlockTick(pos, world.getBlockState(pos).getBlock(), 1);
    }

    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        boolean bl = world.isReceivingRedstonePower(pos);
        if (bl != state.get(BulbBlock.POWERED)) {
            BlockState blockState = state;
            if (!(Boolean)state.get(BulbBlock.POWERED)) {
                world.playSound(null, pos, !((Boolean) (blockState = state.cycle(BulbBlock.LIT)).get(BulbBlock.LIT)) ? SoundEvents.BLOCK_COPPER_BULB_TURN_OFF : SoundEvents.BLOCK_COPPER_BULB_TURN_ON, SoundCategory.BLOCKS);
            }

            world.setBlockState(pos, blockState.with(BulbBlock.POWERED, bl), 3);
        }
    }
}