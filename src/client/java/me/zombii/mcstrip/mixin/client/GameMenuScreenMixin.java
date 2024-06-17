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
import net.minecraft.client.gui.screen.advancement.AdvancementsScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.SocialInteractionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.client.gui.widget.SimplePositioningWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.client.gui.widget.ThreePartsLayoutWidget;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {
    @Shadow protected abstract ButtonWidget createButton(Text text, Supplier<Screen> screenSupplier);

    @Shadow abstract void addFeedbackAndBugsButtons(Screen parentScreen, GridWidget.Adder gridAdder);

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

    @Inject(method = "initWidgets", at = @At("HEAD"))
    private void initWidgets(CallbackInfo ci) {
        GridWidget gridWidget = new GridWidget();
        gridWidget.getMainPositioner().margin(4, 4, 4, 0);
        GridWidget.Adder adder = gridWidget.createAdder(2);
        adder.add(ButtonWidget.builder(RETURN_TO_GAME_TEXT, (button) -> {
            client.setScreen(null);
            this.client.mouse.lockCursor();
        }).width(204).build(), 2, gridWidget.copyPositioner().marginTop(50));
        adder.add(createButton(STATS_TEXT, () -> {
            return new StatsScreen(this, this.client.player.getStatHandler());
        }));
        ServerLinks serverLinks = this.client.player.networkHandler.getServerLinks();
        if (serverLinks.isEmpty()) {
            addFeedbackAndBugsButtons(this, adder);
        } else {
            adder.add(this.createButton(FEEDBACK_TEXT, () -> {
                return new GameMenuScreen.FeedbackScreen(this);
            }));
            adder.add(this.createButton(SERVER_LINKS_TEXT, () -> {
                return new ServerLinksScreen(this, serverLinks);
            }));
        }

        adder.add(this.createButton(OPTIONS_TEXT, () -> {
            return new OptionsScreen(this, this.client.options);
        }));
        if (this.client.isIntegratedServerRunning() && !this.client.getServer().isRemote()) {
            adder.add(this.createButton(SHARE_TO_LAN_TEXT, () -> {
                return new OpenToLanScreen(this);
            }));
        } else {
            adder.add(this.createButton(PLAYER_REPORTING_TEXT, () -> {
                return new SocialInteractionsScreen(this);
            }));
        }

        Text text = this.client.isInSingleplayer() ? RETURN_TO_MENU_TEXT : ScreenTexts.DISCONNECT;
        exitButton = (ButtonWidget)adder.add(ButtonWidget.builder(text, (button) -> {
            button.active = false;
            this.client.getAbuseReportContext().tryShowDraftScreen(this.client, this, this::disconnect, true);
        }).width(204).build(), 2);
        gridWidget.refreshPositions();
        SimplePositioningWidget.setPos(gridWidget, 0, 0, this.width, this.height, 0.5F, 0.25F);
        gridWidget.forEachChild(this::addDrawableChild);
    }

}
