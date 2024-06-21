package me.zombii.mcstrip.improved_redstone;

import me.zombii.mcstrip.McStrip;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class ImprovedItems {

    public static Item IMPROVED_REDSTONE_REPEATER;
    public static Item IMPROVED_REDSTONE_COMPARATOR;

    public static Item IMPROVED_REDSTONE_TORCH;

    public static Item IMPROVED_REDSTONE_LAMP;
    public static Item IMPROVED_OBSERVER;

    public static Item IMPROVED_REDSTONE;
    public static Item IMPROVED_REDSTONE_BLOCK;

    public static void registerItems() {
        ImprovedItems.IMPROVED_REDSTONE_REPEATER = Items.register(ImprovedBlocks.IMPROVED_REDSTONE_REPEATER);
        ImprovedItems.IMPROVED_REDSTONE_COMPARATOR = Items.register(ImprovedBlocks.IMPROVED_REDSTONE_COMPARATOR);
        ImprovedItems.IMPROVED_REDSTONE_LAMP = Items.register(ImprovedBlocks.IMPROVED_REDSTONE_LAMP);
        ImprovedItems.IMPROVED_REDSTONE_TORCH = Items.register(
                new VerticallyAttachableBlockItem(
                        ImprovedBlocks.IMPROVED_REDSTONE_TORCH,
                        ImprovedBlocks.IMPROVED_REDSTONE_WALL_TORCH,
                        new Item.Settings(),
                        Direction.DOWN
                )
        );
        ImprovedItems.IMPROVED_OBSERVER = Items.register(ImprovedBlocks.IMPROVED_OBSERVER);
        ImprovedItems.IMPROVED_REDSTONE = Items.register(
                Identifier.of(McStrip.MOD_ID, "improved_redstone"),
                new AliasedBlockItem(
                        ImprovedBlocks.IMPROVED_REDSTONE,
                        new Item.Settings()
                )
        );
        ImprovedItems.IMPROVED_REDSTONE_BLOCK = Items.register(ImprovedBlocks.IMPROVED_REDSTONE_BLOCK);
    }

}
