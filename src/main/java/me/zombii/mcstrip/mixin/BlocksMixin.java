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
		if (allowedList == null) allowedList = List.of(new String[] {
				"redstone_wire",
				"redstone_torch",
				"redstone_wall_torch",
				"redstone_lamp",
				"redstone_block",
				"target",

				"deepslate",
				"grass_block",
				"sand",
				"short_grass",
				"snow",
				"barrier",
				"sandstone",
				"bedrock",
				"stone",
				"air"
		});

		if (!allowedList.contains(id)) cir.setReturnValue(block);
	}

	@Inject(at = @At("HEAD"), method = "createOldStairsBlock", cancellable = true)
	private static void init(Block block, CallbackInfoReturnable<Block> cir) {
		if (block == null) cir.setReturnValue(new StairsBlock(Blocks.AIR.getDefaultState(), AbstractBlock.Settings.create()));
	}
}