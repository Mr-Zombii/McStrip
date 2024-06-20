package me.zombii.mcstrip.mixin;

import net.minecraft.registry.Registry;
import net.minecraft.registry.SimpleRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SimpleRegistry.class)
public class SimpleRegistryHackMixin {

    @Shadow private boolean frozen;

    @Inject(method = "freeze", at = @At("HEAD"), cancellable = true)
    private void freeze(CallbackInfoReturnable<Registry<?>> cir) {
        if (!frozen) {
            frozen = true;
        }
        cir.setReturnValue((Registry<?>) this);
    }

}
