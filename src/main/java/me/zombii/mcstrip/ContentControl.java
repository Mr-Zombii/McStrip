package me.zombii.mcstrip;

import java.util.ArrayList;
import java.util.List;

public class ContentControl {

    public static boolean itemAllowListLoaded;
    public static boolean blockAllowListLoaded;
    public static boolean commonAllowListLoaded;

    public static List<String> itemAllowList = new ArrayList<>();
    public static List<String> blockAllowList = new ArrayList<>();

    static void addToCommonList(String... entrys) {
        itemAllowList.addAll(List.of(entrys));
        blockAllowList.addAll(List.of(entrys));
    }

    public static void initCommonAllowList() {
        if (commonAllowListLoaded) return;

        // Concrete
        addToCommonList(
                "white_concrete",
                "orange_concrete",
                "magenta_concrete",
                "light_blue_concrete",
                "yellow_concrete",
                "lime_concrete",
                "pink_concrete",
                "light_gray_concrete",
                "gray_concrete",
                "cyan_concrete",
                "purple_concrete",
                "blue_concrete",
                "brown_concrete",
                "green_concrete",
                "red_concrete",
                "black_concrete"
        );

        // Wool
        addToCommonList(
                "white_wool",
                "orange_wool",
                "magenta_wool",
                "light_blue_wool",
                "yellow_wool",
                "lime_wool",
                "pink_wool",
                "light_gray_wool",
                "gray_wool",
                "cyan_wool",
                "purple_wool",
                "blue_wool",
                "brown_wool",
                "green_wool",
                "red_wool",
                "black_wool"
        );

        // Glass
        addToCommonList(
                "glass",
                "white_stained_glass",
                "orange_stained_glass",
                "magenta_stained_glass",
                "light_blue_stained_glass",
                "yellow_stained_glass",
                "lime_stained_glass",
                "pink_stained_glass",
                "light_gray_stained_glass",
                "gray_stained_glass",
                "cyan_stained_glass",
                "purple_stained_glass",
                "blue_stained_glass",
                "brown_stained_glass",
                "green_stained_glass",
                "red_stained_glass",
                "black_stained_glass"
        );

        // Misc
        addToCommonList(
                "air",
                "barrel",
                "sandstone",
                "bedrock",
                "bookshelf",
                "oak_sign",
                "obsidian"
        );

        // Redstone Components
        addToCommonList(
                // Non-full blocks
                "comparator",
                "repeater",
                "redstone_torch",

                "oak_trapdoor",
                "iron_trapdoor",

                // Full blocks
                "waxed_copper_bulb",
                "redstone_lamp",
                "target",

                // Power Emitters
                "oak_pressure_plate",
                "light_weighted_pressure_plate",
                "heavy_weighted_pressure_plate",
                "redstone_block",
                "oak_button",
                "stone_button",
                "lever",

                // Slimestone
                "piston",
                "sticky_piston",
                "slime_block",
                "observer"
        );

        commonAllowListLoaded = true;
    }

    public static void initItemAllowList() {
        if (itemAllowListLoaded) return;
        itemAllowList.addAll(List.of(
                "stick",
                "paper",
                "compass",
                "wooden_axe",
                "redstone"
        ));

        itemAllowListLoaded = true;
    }

    public static void initBlockAllowList() {
        if (blockAllowListLoaded) return;
        blockAllowList.addAll(List.of(
                "piston_head",
                "redstone_wire",
                "redstone_wall_torch"
        ));

        blockAllowListLoaded = true;
    }

    public static boolean resourceController(String namespace, String path) {

        if (
                path.contains("worldgen/world_preset/normal.json") ||
                path.contains("worldgen/biome") ||
                path.contains("worldgen/noise/") ||
                path.contains("worldgen/multi_noise_biome_source") ||
                path.contains("damage_type") ||
                path.contains("flat_level_generator_preset/redstone_ready.json") ||
                path.contains("world_preset/flat") ||
                path.contains("dimension_type/") ||
                path.contains("worldgen/noise_settings")
        ) return true;

        return !namespace.equals("minecraft") && !namespace.equals("c");
    }

}
