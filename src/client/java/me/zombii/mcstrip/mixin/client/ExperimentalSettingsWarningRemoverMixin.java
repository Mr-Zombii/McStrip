package me.zombii.mcstrip.mixin.client;

import net.minecraft.client.gui.screen.world.SymlinkWarningScreen;
import net.minecraft.world.level.storage.LevelSummary;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LevelSummary.class)
public class ExperimentalSettingsWarningRemoverMixin {

    @Inject(method = "isExperimental", at = @At(value = "RETURN"), cancellable = true)
    public void isExperimental(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

}
