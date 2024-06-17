package me.zombii.mcstrip.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.screen.world.WorldCreator;
import net.minecraft.client.gui.screen.world.WorldScreenOptionGrid;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.*;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.function.BooleanSupplier;

@Mixin(CreateWorldScreen.WorldTab.class)
public abstract class WorldTabMixin {

    @Shadow @Final private static Text WORLD_TAB_TITLE_TEXT;

    @Shadow @Final private static Text MAP_FEATURES_TEXT;

    @Shadow @Final private static Text MAP_FEATURES_INFO_TEXT;

    @Shadow @Final private static Text BONUS_ITEMS_TEXT;

    @Shadow @Final private static Text AMPLIFIED_GENERATOR_INFO_TEXT;

    @Mutable
    @Shadow @Final private ButtonWidget customizeButton;

    @Shadow protected abstract void openCustomizeScreen();

    @Mutable
    @Shadow @Final private TextFieldWidget seedField;

    @Shadow @Final private static Text SEED_INFO_TEXT;

    @Shadow @Final private static Text ENTER_SEED_TEXT;

    @Shadow protected abstract CyclingButtonWidget.Values<WorldCreator.WorldType> getWorldTypes();

    @Inject(method = "<init>", at = @At(shift = At.Shift.AFTER, value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/world/CreateWorldScreen$WorldTab$1;<init>(Lnet/minecraft/client/gui/screen/world/CreateWorldScreen$WorldTab;Lnet/minecraft/client/font/TextRenderer;IILnet/minecraft/text/Text;Lnet/minecraft/client/gui/screen/world/CreateWorldScreen;)V"))
    private void init(CreateWorldScreen createWorldScreen, CallbackInfo ci, @Local GridWidget.Adder adder, @Local CyclingButtonWidget cyclingButtonWidget) {
        this.seedField = new TextFieldWidget(createWorldScreen.textRenderer, 308, 20, Text.translatable("selectWorld.enterSeed")) {
            protected MutableText getNarrationMessage() {
                return super.getNarrationMessage().append(ScreenTexts.SENTENCE_SEPARATOR).append(CreateWorldScreen.WorldTab.SEED_INFO_TEXT);
            }
        };
        this.seedField.setPlaceholder(SEED_INFO_TEXT);
        this.seedField.setText(createWorldScreen.getWorldCreator().getSeed());
        this.seedField.setChangedListener((seed) -> {
            createWorldScreen.getWorldCreator().setSeed(this.seedField.getText());
        });
        adder.add(LayoutWidgets.createLabeledWidget(createWorldScreen.textRenderer, this.seedField, ENTER_SEED_TEXT), 2);
        WorldScreenOptionGrid.Builder builder = WorldScreenOptionGrid.builder(310);
        WorldCreator var10003 = createWorldScreen.getWorldCreator();
        var10003.setGenerateStructures(false);
        var10003.setBonusChestEnabled(false);
        WorldScreenOptionGrid worldScreenOptionGrid = builder.build((widget) -> {
            adder.add(widget, 2);
        });
        createWorldScreen.getWorldCreator().addListener((creator) -> {
            worldScreenOptionGrid.refresh();
        });
    }

}
