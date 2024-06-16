package me.zombii.mcstrip.mixin;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Items.class)
public class ItemsMixin {

    private static List<String> allowedList;

    @Inject(at = @At("HEAD"), method = "register(Lnet/minecraft/item/BlockItem;)Lnet/minecraft/item/Item;", cancellable = true)
    private static void register(BlockItem item, CallbackInfoReturnable<Item> cir) {
        if (allowedList == null) allowedList = List.of(new String[] {

        });

    }

}
