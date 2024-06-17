package me.zombii.mcstrip;

import net.fabricmc.api.ModInitializer;

import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Set;

import static net.minecraft.item.ItemGroups.HOTBAR;
import static net.minecraft.item.ItemGroups.SEARCH;

public class McStrip implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("randomcrap");

	private static final Identifier ITEM_SEARCH_TAB_TEXTURE_ID = ItemGroup.getTabTextureId("item_search");

	public static final RegistryKey<ItemGroup> REDSTONE_KEY = register("redstone_components");
	public static final RegistryKey<ItemGroup> CONCRETE_KEY = register("colored_concrete");
	public static final RegistryKey<ItemGroup> GLASS_KEY = register("colored_glass");
	public static final RegistryKey<ItemGroup> WOOL_KEY = register("colored_wool");
	public static final RegistryKey<ItemGroup> TOOL_KEY = register("tools_category");

	private static RegistryKey<ItemGroup> register(String id) {
		return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.ofVanilla(id));
	}

	@Override
	public void onInitialize() {
		ItemGroup.Builder redstone = ItemGroup.create(ItemGroup.Row.TOP, 0);
		redstone.displayName(Text.of("Redstone Components"));
		redstone.icon(() -> new ItemStack(Items.REDSTONE));
		redstone.entries(((displayContext, entries) -> {
			entries.add(Items.REDSTONE);
			entries.add(Items.BARREL);
			entries.add(Items.REPEATER);
			entries.add(Items.COMPARATOR);
			entries.add(Items.REDSTONE_TORCH);
			entries.add(Items.REDSTONE_BLOCK);
			entries.add(Items.REDSTONE_LAMP);
			entries.add(Items.STONE_BUTTON);
			entries.add(Items.OAK_BUTTON);
			entries.add(Items.TARGET);
			entries.add(Items.LEVER);
			entries.add(Items.WAXED_COPPER_BULB);
			entries.add(Items.IRON_TRAPDOOR);
			entries.add(Items.OAK_TRAPDOOR);
		}));
		Registry.register(Registries.ITEM_GROUP, REDSTONE_KEY, redstone.build());

		ItemGroup.Builder concrete = ItemGroup.create(ItemGroup.Row.TOP, 1);
		concrete.displayName(Text.of("Colored Concretes"));
		concrete.icon(() -> new ItemStack(Blocks.WHITE_CONCRETE));
		concrete.entries(((displayContext, entries) -> {
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
		}));
		Registry.register(Registries.ITEM_GROUP, CONCRETE_KEY, concrete.build());

		ItemGroup.Builder wool = ItemGroup.create(ItemGroup.Row.TOP, 2);
		wool.displayName(Text.of("Colored Wools"));
		wool.icon(() -> new ItemStack(Blocks.WHITE_WOOL));
		wool.entries(((displayContext, entries) -> {
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
		}));
		Registry.register(Registries.ITEM_GROUP, WOOL_KEY, wool.build());

		ItemGroup.Builder glass = ItemGroup.create(ItemGroup.Row.TOP, 3);
		glass.displayName(Text.of("Colored Glasses"));
		glass.icon(() -> new ItemStack(Blocks.GLASS));
		glass.entries(((displayContext, entries) -> {
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
		}));
		Registry.register(Registries.ITEM_GROUP, GLASS_KEY, glass.build());

		ItemGroup.Builder tools = ItemGroup.create(ItemGroup.Row.TOP, 4);
		tools.displayName(Text.of("Tools"));
		tools.icon(() -> new ItemStack(Items.WOODEN_AXE));
		tools.entries(((displayContext, entries) -> {
			entries.add(Items.WOODEN_AXE);
			entries.add(Items.STICK);
		}));
		Registry.register(Registries.ITEM_GROUP, TOOL_KEY, tools.build());

		// Required Tabs
		Registry.register(Registries.ITEM_GROUP, HOTBAR, ItemGroup.create(ItemGroup.Row.TOP, 5).displayName(Text.translatable("itemGroup.hotbar")).icon(() -> {
			return new ItemStack(Blocks.BOOKSHELF);
		}).special().type(ItemGroup.Type.HOTBAR).build());

		Registry.register(Registries.ITEM_GROUP, SEARCH, ItemGroup.create(ItemGroup.Row.TOP, 6).displayName(Text.translatable("itemGroup.search")).icon(() -> {
			return new ItemStack(Items.COMPASS);
		}).entries((displayContext, entries) -> {
			Set<ItemStack> set = ItemStackSet.create();
			Iterator var4 = Registries.ITEM_GROUP.iterator();

			while(var4.hasNext()) {
				ItemGroup itemGroup = (ItemGroup)var4.next();
				if (itemGroup.getType() != ItemGroup.Type.SEARCH) {
					set.addAll(itemGroup.getSearchTabStacks());
				}
			}

			entries.addAll(set);
		}).texture(ITEM_SEARCH_TAB_TEXTURE_ID).special().type(ItemGroup.Type.SEARCH).build());
	}
}