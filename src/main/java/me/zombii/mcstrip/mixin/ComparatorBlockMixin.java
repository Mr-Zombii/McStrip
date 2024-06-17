package me.zombii.mcstrip.mixin;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractRedstoneGateBlock;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ComparatorBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ComparatorBlockEntity;
import net.minecraft.block.enums.ComparatorMode;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.listener.GameEventListener;
import net.minecraft.world.tick.TickPriority;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * A mixin class that provides the implementation for the Comparator block. This makes comparators
 * nearly instant.
 *
 * @aythor M4ximumpizza
 */
@Mixin(ComparatorBlock.class)
public class ComparatorBlockMixin extends AbstractRedstoneGateBlock implements BlockEntityProvider {

    @Shadow
    public static final EnumProperty<ComparatorMode> MODE;

    protected ComparatorBlockMixin(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(POWERED, false)).with(MODE, ComparatorMode.COMPARE));
    }

    @Shadow
    protected MapCodec<? extends AbstractRedstoneGateBlock> getCodec() {
        return null;
    }

    @Shadow
    protected int getUpdateDelayInternal(BlockState state) {
        return 2;
    }

    @Shadow
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    public void updatePowered(World world, BlockPos pos, BlockState state) {
        if (!world.getBlockTickScheduler().isTicking(pos, this)) {
            int i = this.calculateOutputSignal(world, pos, state);
            BlockEntity blockEntity = world.getBlockEntity(pos);
            int j = blockEntity instanceof ComparatorBlockEntity ? ((ComparatorBlockEntity)blockEntity).getOutputSignal() : 0;
            if (i != j || (Boolean)state.get(POWERED) != this.hasPower(world, pos, state)) {
                TickPriority tickPriority = this.isTargetNotAligned(world, pos, state) ? TickPriority.HIGH : TickPriority.NORMAL;
                world.scheduleBlockTick(pos, this, -1, tickPriority);
            }

        }
    }

    @Shadow
    private int calculateOutputSignal(World world, BlockPos pos, BlockState state) {
        int i = this.getPower(world, pos, state);
        if (i == 0) {
            return 0;
        } else {
            int j = this.getMaxInputLevelSides(world, pos, state);
            if (j > i) {
                return 0;
            } else {
                return state.get(MODE) == ComparatorMode.SUBTRACT ? i - j : i;
            }
        }
    }

    static {
        MODE = Properties.COMPARATOR_MODE;
    }
}
