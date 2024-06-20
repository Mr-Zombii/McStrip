package me.zombii.mcstrip.mixin;

import net.minecraft.Bootstrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Bootstrap.class)
public class BootstrapFunctionDisablerMixin {

    @Redirect(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/FireBlock;registerDefaultFlammables()V"))
    private static void registerFlammables() {
    }

    @Redirect(method = "initialize", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/ComposterBlock;registerDefaultCompostableItems()V"))
    private static void registerDefaultCompostableItems() {
    }

}
