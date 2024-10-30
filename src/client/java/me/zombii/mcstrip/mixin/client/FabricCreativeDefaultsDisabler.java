package me.zombii.mcstrip.mixin.client;

import net.fabricmc.fabric.impl.client.itemgroup.FabricCreativeGuiComponents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.HashSet;
import java.util.Set;

@Mixin(FabricCreativeGuiComponents.class)
public class FabricCreativeDefaultsDisabler {

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Ljava/util/Set;of(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/Set;"))
    private static <E> Set<RegistryKey<ItemGroup>> clinit0(E e1, E e2, E e3) {
        System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        Set<RegistryKey<ItemGroup>> groupKeys = new HashSet<>();
        groupKeys.add(ItemGroups.INVENTORY);

        return groupKeys;
    }
}
