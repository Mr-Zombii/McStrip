package me.zombii.mcstrip.mixin.client;

import java.net.URI;
import java.util.Objects;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.SharedConstants;
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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {
    private static final Text RETURN_TO_GAME_TEXT = Text.translatable("menu.returnToGame");
    private static final Text STATS_TEXT = Text.translatable("gui.stats");
    private static final Text SEND_FEEDBACK_TEXT = Text.translatable("menu.sendFeedback");
    private static final Text REPORT_BUGS_TEXT = Text.translatable("menu.reportBugs");
    private static final Text FEEDBACK_TEXT = Text.translatable("menu.feedback");
    private static final Text SERVER_LINKS_TEXT = Text.translatable("menu.server_links");
    private static final Text OPTIONS_TEXT = Text.translatable("menu.options");
    private static final Text SHARE_TO_LAN_TEXT = Text.translatable("menu.shareToLan");
    private static final Text PLAYER_REPORTING_TEXT = Text.translatable("menu.playerReporting");
    private static final Text RETURN_TO_MENU_TEXT = Text.translatable("menu.returnToMenu");
    private static final Text SAVING_LEVEL_TEXT = Text.translatable("menu.savingLevel");
    private static final Text GAME_TEXT = Text.translatable("menu.game");
    private static final Text PAUSED_TEXT = Text.translatable("menu.paused");
    private final boolean showMenu;
    @Nullable
    private ButtonWidget exitButton;

    public GameMenuScreenMixin(boolean showMenu) {
        super(showMenu ? GAME_TEXT : PAUSED_TEXT);
        this.showMenu = showMenu;
    }

    public boolean shouldShowMenu() {
        return this.showMenu;
    }

    @Inject(method = "init")
    public void init() {
        if (this.showMenu) {
            this.initWidgets();
        }

        int var10004 = this.showMenu ? 40 : 10;
        int var10005 = this.width;
        Objects.requireNonNull(this.textRenderer);
        this.addDrawableChild(new TextWidget(0, var10004, var10005, 9, this.title, this.textRenderer));
    }

    private void initWidgets() {
        GridWidget gridWidget = new GridWidget();
        gridWidget.getMainPositioner().margin(4, 4, 4, 0);
        GridWidget.Adder adder = gridWidget.createAdder(2);
        adder.add(ButtonWidget.builder(RETURN_TO_GAME_TEXT, (button) -> {
            this.client.setScreen((Screen)null);
            this.client.mouse.lockCursor();
        }).width(204).build(), 2, gridWidget.copyPositioner().marginTop(50));
        adder.add(this.createButton(STATS_TEXT, () -> {
            return new StatsScreen(this, this.client.player.getStatHandler());
        }));
        ServerLinks serverLinks = this.client.player.networkHandler.getServerLinks();
        if (serverLinks.isEmpty()) {
            addFeedbackAndBugsButtons(this, adder);
        } else {
            adder.add(this.createButton(FEEDBACK_TEXT, () -> {
                return new FeedbackScreen(this);
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
        this.exitButton = (ButtonWidget)adder.add(ButtonWidget.builder(text, (button) -> {
            button.active = false;
            this.client.getAbuseReportContext().tryShowDraftScreen(this.client, this, this::disconnect, true);
        }).width(204).build(), 2);
        gridWidget.refreshPositions();
        SimplePositioningWidget.setPos(gridWidget, 0, 0, this.width, this.height, 0.5F, 0.25F);
        gridWidget.forEachChild(this::addDrawableChild);
    }

    static void addFeedbackAndBugsButtons(Screen parentScreen, GridWidget.Adder gridAdder) {
        gridAdder.add(createUrlButton(parentScreen, SEND_FEEDBACK_TEXT, SharedConstants.getGameVersion().isStable() ? Urls.JAVA_FEEDBACK : Urls.SNAPSHOT_FEEDBACK));
        ((ButtonWidget)gridAdder.add(createUrlButton(parentScreen, REPORT_BUGS_TEXT, Urls.SNAPSHOT_BUGS))).active = !SharedConstants.getGameVersion().getSaveVersion().isNotMainSeries();
    }

    private void disconnect() {
        boolean bl = this.client.isInSingleplayer();
        ServerInfo serverInfo = this.client.getCurrentServerEntry();
        this.client.world.disconnect();
        if (bl) {
            this.client.disconnect(new MessageScreen(SAVING_LEVEL_TEXT));
        } else {
            this.client.disconnect();
        }

        TitleScreen titleScreen = new TitleScreen();
        if (bl) {
            this.client.setScreen(titleScreen);
        } else if (serverInfo != null && serverInfo.isRealm()) {
            this.client.setScreen(new RealmsMainScreen(titleScreen));
        } else {
            this.client.setScreen(new MultiplayerScreen(titleScreen));
        }

    }
    private ButtonWidget createButton(Text text, Supplier<Screen> screenSupplier) {
        return ButtonWidget.builder(text, (button) -> {
            assert this.client != null;
            this.client.setScreen((Screen)screenSupplier.get());
        }).width(98).build();
    }

    private static ButtonWidget createUrlButton(Screen parent, Text text, URI uri) {
        return ButtonWidget.builder(text, ConfirmLinkScreen.opening(parent, uri)).width(98).build();
    }

    @Environment(EnvType.CLIENT)
    static class FeedbackScreen extends Screen {
        private static final Text TITLE = Text.translatable("menu.feedback.title");
        public final Screen parent;
        private final ThreePartsLayoutWidget layoutWidget = new ThreePartsLayoutWidget(this);

        protected FeedbackScreen(Screen parent) {
            super(TITLE);
            this.parent = parent;
        }

        protected void init() {
            this.layoutWidget.addHeader(TITLE, this.textRenderer);
            GridWidget gridWidget = (GridWidget)this.layoutWidget.addBody(new GridWidget());
            gridWidget.getMainPositioner().margin(4, 4, 4, 0);
            GridWidget.Adder adder = gridWidget.createAdder(2);
            addFeedbackAndBugsButtons(this, adder);
            this.layoutWidget.addFooter(ButtonWidget.builder(ScreenTexts.BACK, (button) -> {
                this.close();
            }).width(200).build());
            this.layoutWidget.forEachChild(this::addDrawableChild);
            this.initTabNavigation();
        }

        protected void initTabNavigation() {
            this.layoutWidget.refreshPositions();
        }

        public void close() {
            assert this.client != null;
            this.client.setScreen(this.parent);
        }
    }
}
