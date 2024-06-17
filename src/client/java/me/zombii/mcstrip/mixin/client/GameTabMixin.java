package me.zombii.mcstrip.mixin.client;

import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.screen.world.WorldCreator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreateWorldScreen.GameTab.class)
public abstract class GameTabMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CreateWorldScreen createWorldScreen, CallbackInfo ci) {
        createWorldScreen.getWorldCreator().setCheatsEnabled(true);
        createWorldScreen.getWorldCreator().setGameMode(WorldCreator.Mode.CREATIVE);
    }

}
