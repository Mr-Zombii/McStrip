package me.zombii.mcstrip.mixin;

import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LootTables.class)
public class LootTablesMixin {

    @Inject(method = "register", at = @At("HEAD"), cancellable = true)
    private static void register(String id, CallbackInfoReturnable<RegistryKey<LootTable>> cir) {
        cir.setReturnValue(null);
        cir.cancel();
    }

}
