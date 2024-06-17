package me.zombii.mcstrip.mixin.client;

import java.net.URI;
import java.util.Objects;
import java.util.function.Supplier;

import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.screen.GameMenuScreen.FeedbackScreen;
import net.minecraft.client.gui.screen.advancement.AdvancementsScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.SocialInteractionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.realms.gui.screen.RealmsMainScreen;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.server.ServerLinks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Urls;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {
    @Shadow protected abstract ButtonWidget createButton(Text text, Supplier<Screen> screenSupplier);

    @Shadow
    static void addFeedbackAndBugsButtons(Screen parentScreen, GridWidget.Adder gridAdder) {
    }

    @Shadow @Nullable private ButtonWidget exitButton;

    @Shadow protected abstract void disconnect();

    private static final Text RETURN_TO_GAME_TEXT = Text.translatable("menu.returnToGame");
    private static final Text STATS_TEXT = Text.translatable("gui.stats");
    private static final Text FEEDBACK_TEXT = Text.translatable("menu.feedback");
    private static final Text SERVER_LINKS_TEXT = Text.translatable("menu.server_links");
    private static final Text OPTIONS_TEXT = Text.translatable("menu.options");
    private static final Text SHARE_TO_LAN_TEXT = Text.translatable("menu.shareToLan");
    private static final Text PLAYER_REPORTING_TEXT = Text.translatable("menu.playerReporting");
    private static final Text RETURN_TO_MENU_TEXT = Text.translatable("menu.returnToMenu");

    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Unique
    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Redirect(method = "initWidgets", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/GridWidget$Adder;add(Lnet/minecraft/client/gui/widget/Widget;)Lnet/minecraft/client/gui/widget/Widget;", ordinal = 0))
    private Widget initWidgets(GridWidget.Adder instance, Widget widget) {
        instance.add(ButtonWidget.builder(Text.of("What does this do?"), (button) -> {
            if (getRandomNumber(0, 400) == 6) {
                throw new RuntimeException("Oh, wow you found a Shiny Error");
            }
        }).width(98).build());
        return null;
    }

}
