package me.zombii.mcstrip.mixin.registry;

import me.zombii.mcstrip.ContentControl;
import me.zombii.mcstrip.McStrip;
import net.minecraft.block.*;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Blocks.class)
public class BlockGateMixin {

	@Inject(method = "<clinit>", at = @At("HEAD"))
	private static void init(CallbackInfo ci) {
		ContentControl.initCommonAllowList();
		ContentControl.initBlockAllowList();
	}

	@Inject(at = @At("HEAD"), method = "register(Ljava/lang/String;Lnet/minecraft/block/Block;)Lnet/minecraft/block/Block;", cancellable = true)
	private static void register(String strId, Block block, CallbackInfoReturnable<Block> cir) {
		Identifier id = Identifier.of(strId);

		if (id.getNamespace().equals("minecraft")) {
			if (!ContentControl.blockAllowList.contains(strId)) {
				McStrip.LOGGER.info("Stopped block \"{}\" from registering", id);
				cir.setReturnValue(block);
			}
		}
	}

	@Inject(at = @At("HEAD"), method = "createOldStairsBlock", cancellable = true)
	private static void init(Block block, CallbackInfoReturnable<Block> cir) {
		if (block == null) cir.setReturnValue(new StairsBlock(Blocks.AIR.getDefaultState(), AbstractBlock.Settings.create()));
	}
}