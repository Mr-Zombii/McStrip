package me.zombii.mcstrip;

import me.zombii.mcstrip.improved_redstone.ImprovedBlocks;
import me.zombii.mcstrip.improved_redstone.ImprovedItems;
import me.zombii.mcstrip.improved_redstone.blocks.*;
import net.fabricmc.api.ModInitializer;

import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

import static me.zombii.mcstrip.improved_redstone.ImprovedBlocks.*;
import static net.minecraft.block.Blocks.createLightLevelFromLitBlockState;
import static net.minecraft.item.ItemGroups.HOTBAR;
import static net.minecraft.item.ItemGroups.SEARCH;

public class McStrip implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("randomcrap");

	private static final Identifier ITEM_SEARCH_TAB_TEXTURE_ID = ItemGroup.getTabTextureId("item_search");

	public static final RegistryKey<ItemGroup> REDSTONE_KEY = register("redstone_components");
	public static final RegistryKey<ItemGroup> IMPROVED_REDSTONE_KEY = register("improved_redstone_components");
//	public static final RegistryKey<ItemGroup> BARREL_KEY = register("barrel_values");
	public static final RegistryKey<ItemGroup> CONCRETE_KEY = register("colored_concrete");
	public static final RegistryKey<ItemGroup> GLASS_KEY = register("colored_glass");
	public static final RegistryKey<ItemGroup> WOOL_KEY = register("colored_wool");
	public static final RegistryKey<ItemGroup> TOOL_KEY = register("tools_category");
	public static final RegistryKey<ItemGroup> SLIME_STONE_KEY = register("slime_stone");

	private static RegistryKey<ItemGroup> register(String id) {
		return RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.ofVanilla(id));
	}

	private static Block register(Identifier id, Block block) {
		return Registry.register(Registries.BLOCK, id, block);
	}

	private static void idk(Object o) {}

	@Override
	public void onInitialize() {
		ImprovedBlocks.IMPROVED_REDSTONE_REPEATER = register(Identifier.of(modId, "improved_repeater"), new ImprovedRedstoneRepeaterBlock(AbstractBlock.Settings.create().breakInstantly().sounds(BlockSoundGroup.STONE).pistonBehavior(PistonBehavior.DESTROY)));
		ImprovedBlocks.IMPROVED_REDSTONE_COMPARATOR = register(Identifier.of(modId, "improved_comparator"), new ImprovedComparatorBlock(AbstractBlock.Settings.create().breakInstantly().sounds(BlockSoundGroup.STONE).pistonBehavior(PistonBehavior.DESTROY)));
		ImprovedBlocks.IMPROVED_REDSTONE_TORCH = register(Identifier.of(modId, "improved_redstone_torch"), new ImprovedRedstoneTorchBlock(AbstractBlock.Settings.create().noCollision().breakInstantly().luminance(createLightLevelFromLitBlockState(7)).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY)));
		ImprovedBlocks.IMPROVED_REDSTONE_WALL_TORCH = register(Identifier.of(modId, "improved_redstone_wall_torch"), new ImprovedRedstoneWallTorchBlock(AbstractBlock.Settings.create().noCollision().breakInstantly().luminance(createLightLevelFromLitBlockState(7)).sounds(BlockSoundGroup.WOOD).dropsLike(ImprovedBlocks.IMPROVED_REDSTONE_TORCH).pistonBehavior(PistonBehavior.DESTROY)));
		ImprovedBlocks.IMPROVED_REDSTONE_LAMP = register(Identifier.of(modId, "improved_redstone_lamp"), new ImprovedRedstoneLamp(AbstractBlock.Settings.create().luminance(createLightLevelFromLitBlockState(15)).strength(0.3F).sounds(BlockSoundGroup.GLASS).allowsSpawning(Blocks::always)));
		ImprovedBlocks.IMPROVED_OBSERVER = register(Identifier.of(modId, "improved_observer"), new ObserverBlock(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).strength(3.0F).requiresTool().solidBlock(Blocks::never)));

		ImprovedItems.IMPROVED_REDSTONE_REPEATER = Items.register(IMPROVED_REDSTONE_REPEATER);
		ImprovedItems.IMPROVED_REDSTONE_COMPARATOR = Items.register(IMPROVED_REDSTONE_COMPARATOR);
		ImprovedItems.IMPROVED_REDSTONE_LAMP = Items.register(IMPROVED_REDSTONE_LAMP);
		ImprovedItems.IMPROVED_REDSTONE_TORCH = Items.register(new VerticallyAttachableBlockItem(IMPROVED_REDSTONE_TORCH, IMPROVED_REDSTONE_WALL_TORCH, new Item.Settings(), Direction.DOWN));
		ImprovedItems.IMPROVED_OBSERVER = Items.register(IMPROVED_OBSERVER);

		ItemGroup.Builder redstone = ItemGroup.create(ItemGroup.Row.TOP, 0);
		redstone.displayName(Text.of("Redstone Components"));
		redstone.icon(() -> new ItemStack(Items.REDSTONE));
		redstone.entries(((displayContext, entries) -> {
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
		}));
		Registry.register(Registries.ITEM_GROUP, REDSTONE_KEY, redstone.build());

		ItemGroup.Builder improved_redstone = ItemGroup.create(ItemGroup.Row.TOP, 1);
		improved_redstone.displayName(Text.of("Better Redstone Components"));
		improved_redstone.icon(() -> new ItemStack(ImprovedBlocks.IMPROVED_REDSTONE_REPEATER));
		improved_redstone.entries(((displayContext, entries) -> {
			entries.add(ImprovedItems.IMPROVED_REDSTONE_TORCH);
			entries.add(ImprovedItems.IMPROVED_REDSTONE_COMPARATOR);
			entries.add(ImprovedItems.IMPROVED_REDSTONE_REPEATER);
			entries.add(ImprovedItems.IMPROVED_REDSTONE_LAMP);
			entries.add(ImprovedItems.IMPROVED_OBSERVER);
		}));
		Registry.register(Registries.ITEM_GROUP, IMPROVED_REDSTONE_KEY, improved_redstone.build());

		ItemGroup.Builder slimestone = ItemGroup.create(ItemGroup.Row.TOP, 2);
		slimestone.displayName(Text.of("Slime Stone"));
		slimestone.icon(() -> new ItemStack(Items.SLIME_BLOCK));
		slimestone.entries(((displayContext, entries) -> {
			entries.add(Items.SLIME_BLOCK);
			entries.add(Items.PISTON);
			entries.add(Items.STICKY_PISTON);
			entries.add(Items.OBSERVER);
			entries.add(Items.OBSIDIAN);
		}));
		Registry.register(Registries.ITEM_GROUP, SLIME_STONE_KEY, slimestone.build());

//		ItemGroup.Builder barrels = ItemGroup.create(ItemGroup.Row.TOP, 0);
//		barrels.displayName(Text.of("Barrel Strengths"));
//		barrels.icon(() -> new ItemStack(Items.BARREL));
//		barrels.entries(((displayContext, entries) -> {
////			entries.add(Items.BARREL);
////			entries.add(getFilledItem(Items.BARREL, Items.WOODEN_AXE, 1));
//			entries.add(getFilledItem(Blocks.BARREL, Items.WOODEN_AXE, 27));
//		}));
//		Registry.register(Registries.ITEM_GROUP, BARREL_KEY, barrels.build());

		ItemGroup.Builder concrete = ItemGroup.create(ItemGroup.Row.TOP, 3);
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

		ItemGroup.Builder wool = ItemGroup.create(ItemGroup.Row.TOP, 4);
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

		ItemGroup.Builder glass = ItemGroup.create(ItemGroup.Row.TOP, 5);
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

		ItemGroup.Builder tools = ItemGroup.create(ItemGroup.Row.BOTTOM, 0);
		tools.displayName(Text.of("Tools"));
		tools.icon(() -> new ItemStack(Items.WOODEN_AXE));
		tools.entries(((displayContext, entries) -> {
			entries.add(Items.WOODEN_AXE);
			entries.add(Items.STICK);
		}));
		Registry.register(Registries.ITEM_GROUP, TOOL_KEY, tools.build());

		// Required Tabs
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
		}).texture(ITEM_SEARCH_TAB_TEXTURE_ID).special().type(ItemGroup.Type.SEARCH).build());
	}
//
//	public static ItemStack getFilledItem(Block item, Item fill, int count) {
//		NbtCompound compound = new NbtCompound();
//		DefaultedList<ItemStack> items = DefaultedList.of();
//		for (int i = 0; i < count; i++) {
//			items.add(fill.getDefaultStack());
//		}
//		RegistryWrapper.WrapperLookup lookup = RegistryWrapper.WrapperLookup.of(Stream.of(Registries.ITEM.getTagCreatingWrapper()));
//		Inventories.writeNbt(compound, items, lookup);
//		NbtCompound compound1 = new NbtCompound();
//		compound1.putString("id", "minecraft:barrel");
//		compound1.put("slots", compound);
//		var barrel = ItemStack.fromNbt(lookup, compound1).get();
//		BlockItem.setBlockEntityData(barrel, BlockEntityType.BARREL, compound);
//		return barrel;
//	}
}