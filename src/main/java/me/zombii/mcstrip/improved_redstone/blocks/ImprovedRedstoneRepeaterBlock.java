package me.zombii.mcstrip.improved_redstone.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RepeaterBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.Direction;

/**
 * An improved class that provides the implementation for the Redstone Repeater block. This makes repeaters
 * nearly instant.
 *
 * @see RepeaterBlock
 * @author M4ximumpizza
 */
public class ImprovedRedstoneRepeaterBlock extends RepeaterBlock {
    public static final BooleanProperty LOCKED;
    public static final IntProperty DELAY;

    public ImprovedRedstoneRepeaterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(DELAY, 1).with(LOCKED, false).with(POWERED, false));
    }

    public int getUpdateDelayInternal(BlockState state) {
        return state.get(DELAY) - 1;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, DELAY, LOCKED, POWERED);
    }

    static {
        LOCKED = Properties.LOCKED;
        DELAY = Properties.DELAY;
    }
}
