package me.zombii.mcstrip.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;
import java.util.Set;

@Environment(EnvType.CLIENT)
@Mixin(LivingEntityRenderer.class)
public class InvertedPlayerMixin {

    @Unique
    private static final Set<String> upsideDownNames = new HashSet<>();

    public InvertedPlayerMixin() {
    }

    @Inject(
            method = {"shouldFlipUpsideDown"},
            at = {@At("RETURN")},
            cancellable = true
    )
    private static void modifyShouldFlipUpsideDown(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        String name = Formatting.strip(entity.getName().getString());
        cir.setReturnValue(upsideDownNames.contains(name));
    }

    static {
        upsideDownNames.add("M4ximumPizza");
    }
}