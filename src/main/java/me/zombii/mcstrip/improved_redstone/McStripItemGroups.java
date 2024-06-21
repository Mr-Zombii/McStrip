package me.zombii.mcstrip.improved_redstone;

import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Set;

import static net.minecraft.item.ItemGroups.HOTBAR;
import static net.minecraft.item.ItemGroups.SEARCH;

public class McStripItemGroups {

    static RegistryKey<ItemGroup> register(String id) {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.ofVanilla(id));
    }

    public static final RegistryKey<ItemGroup> REDSTONE_ITEMGROUP_KEY = register("redstone_components");
    public static final RegistryKey<ItemGroup> BETTER_REDSTONE_ITEMGROUP_KEY = register("improved_redstone_components");
    public static final RegistryKey<ItemGroup> CONCRETE_ITEMGROUP_KEY = register("colored_concrete");
    public static final RegistryKey<ItemGroup> STAINED_GLASS_ITEMGROUP_KEY = register("colored_glass");
    public static final RegistryKey<ItemGroup> COLORED_WOOL_ITEMGROUP_KEY = register("colored_wool");
    public static final RegistryKey<ItemGroup> MULTIUSE_ITEMGROUP_TOOL_KEY = register("tools_category");
    public static final RegistryKey<ItemGroup> SLIMESTONE_ITEMGROUP_KEY = register("slime_stone");

    static ItemGroup registerItemGroup(RegistryKey<ItemGroup> registryKey, ItemGroup.Builder group) {
        return Registry.register(Registries.ITEM_GROUP, registryKey, group.build());
    }

    static ItemGroup.Builder makeItemgroup(
            ItemGroup.Row row, int column,
            String title, ItemConvertible itemIcon,
            ItemGroup.EntryCollector entries
    ) {
        ItemGroup.Builder builder = ItemGroup.create(row, column);
        builder.displayName(Text.of(title));
        builder.icon(() -> new ItemStack(itemIcon));
        builder.entries(entries);
        return builder;
    }

    public static void registerItemGroups() {
        registerItemGroup(
                REDSTONE_ITEMGROUP_KEY,
                makeItemgroup(
                        ItemGroup.Row.TOP, 0,
                        "Redstone Components", Items.REDSTONE,
                        ((displayContext, entries) -> {
                            entries.add(Items.REDSTONE);
                            entries.add(Items.REPEATER);
                            entries.add(Items.COMPARATOR);

                            entries.add(Items.REDSTONE_TORCH);
                            entries.add(Items.REDSTONE_BLOCK);

                            entries.add(Items.REDSTONE_LAMP);
                            entries.add(Items.WAXED_COPPER_BULB);

                            entries.add(Items.IRON_TRAPDOOR);
                            entries.add(Items.OAK_TRAPDOOR);

                            entries.add(Items.STONE_BUTTON);
                            entries.add(Items.OAK_BUTTON);

                            entries.add(Items.LEVER);
                            entries.add(Items.TARGET);
                            entries.add(Items.BARREL);
                        })
                )
        );

        registerItemGroup(
                BETTER_REDSTONE_ITEMGROUP_KEY,
                makeItemgroup(
                        ItemGroup.Row.TOP, 1,
                        "Improved Redstone Components", ImprovedItems.IMPROVED_REDSTONE,
                        ((displayContext, entries) -> {
                            entries.add(ImprovedItems.IMPROVED_REDSTONE);
                            entries.add(ImprovedItems.IMPROVED_REDSTONE_BLOCK);
                            entries.add(ImprovedItems.IMPROVED_REDSTONE_TORCH);
                            entries.add(ImprovedItems.IMPROVED_REDSTONE_COMPARATOR);
                            entries.add(ImprovedItems.IMPROVED_REDSTONE_REPEATER);
                            entries.add(ImprovedItems.IMPROVED_REDSTONE_LAMP);
                            entries.add(ImprovedItems.IMPROVED_OBSERVER);
                        })
                )
        );

        registerItemGroup(
                SLIMESTONE_ITEMGROUP_KEY,
                makeItemgroup(
                        ItemGroup.Row.TOP, 2,
                        "Slimestone Components", Items.SLIME_BLOCK,
                        ((displayContext, entries) -> {
                            entries.add(Items.SLIME_BLOCK);
                            entries.add(Items.PISTON);
                            entries.add(Items.STICKY_PISTON);
                            entries.add(Items.OBSERVER);
                            entries.add(Items.OBSIDIAN);
                        })
                )
        );

        registerItemGroup(
                CONCRETE_ITEMGROUP_KEY,
                makeItemgroup(
                        ItemGroup.Row.TOP, 3,
                        "Colored Concretes", Items.WHITE_CONCRETE,
                        ((displayContext, entries) -> {
                            entries.add(Items.WHITE_CONCRETE);
                            entries.add(Items.GRAY_CONCRETE);
                            entries.add(Items.LIGHT_GRAY_CONCRETE);
                            entries.add(Items.BLACK_CONCRETE);
                            entries.add(Items.BROWN_CONCRETE);
                            entries.add(Items.RED_CONCRETE);
                            entries.add(Items.ORANGE_CONCRETE);
                            entries.add(Items.YELLOW_CONCRETE);
                            entries.add(Items.GREEN_CONCRETE);
                            entries.add(Items.LIME_CONCRETE);
                            entries.add(Items.CYAN_CONCRETE);
                            entries.add(Items.LIGHT_BLUE_CONCRETE);
                            entries.add(Items.BLUE_CONCRETE);
                            entries.add(Items.PURPLE_CONCRETE);
                            entries.add(Items.MAGENTA_CONCRETE);
                            entries.add(Items.PINK_CONCRETE);
                        })
                )
        );

        registerItemGroup(
                COLORED_WOOL_ITEMGROUP_KEY,
                makeItemgroup(
                        ItemGroup.Row.TOP, 4,
                        "Colored Wools", Items.WHITE_WOOL,
                        ((displayContext, entries) -> {
                            entries.add(Items.WHITE_WOOL);
                            entries.add(Items.GRAY_WOOL);
                            entries.add(Items.LIGHT_GRAY_WOOL);
                            entries.add(Items.BLACK_WOOL);
                            entries.add(Items.BROWN_WOOL);
                            entries.add(Items.RED_WOOL);
                            entries.add(Items.ORANGE_WOOL);
                            entries.add(Items.YELLOW_WOOL);
                            entries.add(Items.GREEN_WOOL);
                            entries.add(Items.LIME_WOOL);
                            entries.add(Items.CYAN_WOOL);
                            entries.add(Items.LIGHT_BLUE_WOOL);
                            entries.add(Items.BLUE_WOOL);
                            entries.add(Items.PURPLE_WOOL);
                            entries.add(Items.MAGENTA_WOOL);
                            entries.add(Items.PINK_WOOL);
                        })
                )
        );

        registerItemGroup(
                STAINED_GLASS_ITEMGROUP_KEY,
                makeItemgroup(
                        ItemGroup.Row.TOP, 5,
                        "Stained Glasses", Items.WHITE_STAINED_GLASS,
                        ((displayContext, entries) -> {
                            entries.add(Items.GLASS);
                            entries.add(Items.WHITE_STAINED_GLASS);
                            entries.add(Items.GRAY_STAINED_GLASS);
                            entries.add(Items.LIGHT_GRAY_STAINED_GLASS);
                            entries.add(Items.BLACK_STAINED_GLASS);
                            entries.add(Items.BROWN_STAINED_GLASS);
                            entries.add(Items.RED_STAINED_GLASS);
                            entries.add(Items.ORANGE_STAINED_GLASS);
                            entries.add(Items.YELLOW_STAINED_GLASS);
                            entries.add(Items.GREEN_STAINED_GLASS);
                            entries.add(Items.LIME_STAINED_GLASS);
                            entries.add(Items.CYAN_STAINED_GLASS);
                            entries.add(Items.LIGHT_BLUE_STAINED_GLASS);
                            entries.add(Items.BLUE_STAINED_GLASS);
                            entries.add(Items.PURPLE_STAINED_GLASS);
                            entries.add(Items.MAGENTA_STAINED_GLASS);
                            entries.add(Items.PINK_STAINED_GLASS);
                        })
                )
        );

        registerItemGroup(
                MULTIUSE_ITEMGROUP_TOOL_KEY,
                makeItemgroup(
                        ItemGroup.Row.TOP, 5,
                        "Extra Tools", Items.WOODEN_AXE,
                        ((displayContext, entries) -> {
                            entries.add(Items.WOODEN_AXE);
                            entries.add(Items.STICK);
                        })
                )
        );

        // Tabs required by the base game
        Registry.register(Registries.ITEM_GROUP, HOTBAR, ItemGroup.create(ItemGroup.Row.BOTTOM, 5).displayName(Text.translatable("itemGroup.hotbar")).icon(() -> {
            return new ItemStack(Blocks.BOOKSHELF);
        }).special().type(ItemGroup.Type.HOTBAR).build());

        Registry.register(Registries.ITEM_GROUP, SEARCH, ItemGroup.create(ItemGroup.Row.TOP, 6).displayName(Text.translatable("itemGroup.search")).icon(() -> {
            return new ItemStack(Items.COMPASS);
        }).entries((displayContext, entries) -> {
            Set<ItemStack> set = ItemStackSet.create();

            for (ItemGroup itemGroup : Registries.ITEM_GROUP) {
                if (itemGroup.getType() != ItemGroup.Type.SEARCH) {
                    set.addAll(itemGroup.getSearchTabStacks());
                }
            }

            entries.addAll(set);
        }).texture(ItemGroups.ITEM_SEARCH_TAB_TEXTURE_ID).special().type(ItemGroup.Type.SEARCH).build());
    }

}
