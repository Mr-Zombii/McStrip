package me.zombii.mcstrip.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.screen.world.WorldCreator;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreateWorldScreen.GameTab.class)
public abstract class GameSettingsPresetMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CreateWorldScreen createWorldScreen, CallbackInfo ci) {
        createWorldScreen.getWorldCreator().setCheatsEnabled(true);
        createWorldScreen.getWorldCreator().setGameMode(WorldCreator.Mode.CREATIVE);
        createWorldScreen.getWorldCreator().getGameRules().get(GameRules.DO_DAYLIGHT_CYCLE)
                .set(true, MinecraftClient.getInstance().getServer());
    }

}
