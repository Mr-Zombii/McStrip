package me.zombii.mcstrip.mixin;

import net.minecraft.block.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Blocks.class)
public class BlocksMixin {

	private static List<String> allowedList;

	@Inject(at = @At("HEAD"), method = "register(Ljava/lang/String;Lnet/minecraft/block/Block;)Lnet/minecraft/block/Block;", cancellable = true)
	private static void register(String id, Block block, CallbackInfoReturnable<Block> cir) {
		if (allowedList == null) allowedList = List.of("",
				// Concretes
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
				"black_concrete",

				// Wools
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
				"black_wool",

				// Glasses
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
				"black_stained_glass",

				// Redstone Components
				"comparator",
				"repeater",
				"waxed_copper_bulb",
				"redstone_wire",
				"redstone_torch",
				"redstone_wall_torch",
				"redstone_lamp",
				"target",

				// Doors
				"oak_trapdoor",
				"iron_trapdoor",

				// Activators
				"oak_button",
				"stone_button",
				"lever",
				"oak_pressure_plate",
				"light_weighted_pressure_plate",
				"heavy_weighted_pressure_plate",
				"redstone_block",

				// Misc Blocks
				"air",
				"barrel",

				"sandstone",
				"grass_block",
				"stone",
				"bedrock",

				"bookshelf",

				// Improved Items
				"improved_redstone_torch",
				"improved_redstone_wall_torch",
				"improved_repeater",
				"improved_comparator"
		);

		if (!allowedList.contains(id)) {
			cir.setReturnValue(block);
		}
	}

	@Inject(at = @At("HEAD"), method = "createOldStairsBlock", cancellable = true)
	private static void init(Block block, CallbackInfoReturnable<Block> cir) {
		if (block == null) cir.setReturnValue(new StairsBlock(Blocks.AIR.getDefaultState(), AbstractBlock.Settings.create()));
	}
}