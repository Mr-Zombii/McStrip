package me.zombii.mcstrip.mixin;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * A mixin class that provides the implementation for the Redstone Repeater block. This makes repeaters
 * nearly instant.
 *
 * @see RepeaterBlock
 * @author M4ximumpizza
 */
@Mixin(RepeaterBlock.class)
public class RedstoneRepeaterMixin extends AbstractRedstoneGateBlock {
    @Shadow
    public static final BooleanProperty LOCKED;
    @Shadow
    public static final IntProperty DELAY;
    @Shadow
    public static final MapCodec<RepeaterBlock> CODEC = createCodec(RepeaterBlock::new);

    public RedstoneRepeaterMixin(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(DELAY, 500)).with(LOCKED, false)).with(POWERED, false));
    }
    @Shadow
    public MapCodec<RepeaterBlock> getCodec() {
        return CODEC;
    }

    public boolean isLocked(WorldView world, BlockPos pos, BlockState state) {
        return this.getMaxInputLevelSides(world, pos, state) > 0;
    }

    @Override
    public int getUpdateDelayInternal(BlockState state) {
        return (Integer)state.get(DELAY) - 1;
    }

    static {
        LOCKED = Properties.LOCKED;
        DELAY = Properties.DELAY;
    }
}