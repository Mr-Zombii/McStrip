package me.zombii.mcstrip.mixin.bugfixes;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.Settings.class)
public class BlockSettingsNullFixerMixin {

    @Inject(at = @At("HEAD"), method = "copy", cancellable = true)
    private static void copy(AbstractBlock block, CallbackInfoReturnable<AbstractBlock.Settings> cir) {
        if (block == null) cir.setReturnValue(AbstractBlock.Settings.create());
    }

    @Inject(at = @At("HEAD"), method = "copyShallow", cancellable = true)
    private static void copyShallow(AbstractBlock block, CallbackInfoReturnable<AbstractBlock.Settings> cir) {
        if (block == null) cir.setReturnValue(AbstractBlock.Settings.create());
    }

    @Inject(at = @At("HEAD"), method = "dropsLike", cancellable = true)
    private void dropsLike(Block source, CallbackInfoReturnable<AbstractBlock.Settings> cir) {
        if (source == null) cir.setReturnValue(AbstractBlock.Settings.create());
    }

}
