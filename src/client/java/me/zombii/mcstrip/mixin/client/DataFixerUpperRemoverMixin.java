package me.zombii.mcstrip.mixin.client;

import com.mojang.datafixers.DSL;
import net.minecraft.client.main.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mixin(Main.class)
public class DataFixerUpperRemoverMixin {

    @Redirect(method = "main", at = @At(value = "INVOKE", target = "Lnet/minecraft/datafixer/Schemas;optimize(Ljava/util/Set;)Ljava/util/concurrent/CompletableFuture;"))
    private static CompletableFuture<?> optimize(Set<DSL.TypeReference> requiredTypes) {
        return null;
    }

    @Redirect(method = "main", at = @At(value = "INVOKE", target = "Ljava/util/concurrent/CompletableFuture;join()Ljava/lang/Object;"))
    private static <T> T join(CompletableFuture<T> instance) {
        return null;
    }

}
