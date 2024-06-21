package me.zombii.mcstrip.mixin.registry;

import me.zombii.mcstrip.ContentControl;
import net.minecraft.registry.*;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Mixin(RegistryLoader.class)
public class RegistryLoaderFloodgateMixin {

    /**
     * @author Mr_Zombii
     * @reason To remove the "registry must be not empty" issue
     */
    @Redirect(method = "load", at = @At(value = "INVOKE", target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V", ordinal = 1))
    private static void foreach(List instance, Consumer consumer) {}

    @Redirect(method = "loadFromResource(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/registry/RegistryOps$RegistryInfoGetter;Lnet/minecraft/registry/MutableRegistry;Lcom/mojang/serialization/Decoder;Ljava/util/Map;)V", at=@At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourceFinder;findResources(Lnet/minecraft/resource/ResourceManager;)Ljava/util/Map;"))
    private static Map<Identifier, Resource> init(ResourceFinder instance, ResourceManager resourceManager) {

        Map<Identifier, Resource> resources = new HashMap<>();
        Map<Identifier, Resource> oldResources =
                resourceManager.findResources(
                        instance.directoryName,
                        (id) -> id.getPath().endsWith(instance.fileExtension)
                );

        oldResources.keySet().forEach(id -> {
            if (
                    ContentControl.resourceController(id.getNamespace(), id.getPath())
            ) {
                resources.put(id, oldResources.get(id));
            }
        });

        return resources;
    }

}
