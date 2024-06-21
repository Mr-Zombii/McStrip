package me.zombii.mcstrip.mixin.client;

import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.widget.*;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameMenuScreen.class)
public abstract class AdvancementButtonHiderMixin extends Screen {

    protected AdvancementButtonHiderMixin(Text title) {
        super(title);
    }

    @Unique
    private int getRandomNumber() {
        return (int) ((Math.random() * (400)) + 0);
    }

    @Redirect(method = "initWidgets", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/GridWidget$Adder;add(Lnet/minecraft/client/gui/widget/Widget;)Lnet/minecraft/client/gui/widget/Widget;", ordinal = 0))
    private Widget initWidgets(GridWidget.Adder instance, Widget widget) {
        instance.add(ButtonWidget.builder(Text.of("What does this do?"), (button) -> {
            if (getRandomNumber() == 6) {
                throw new RuntimeException("Oh, wow you found a Shiny Error");
            }
        }).width(98).build());
        return null;
    }

}
