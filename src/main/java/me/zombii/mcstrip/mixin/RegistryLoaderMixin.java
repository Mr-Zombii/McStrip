package me.zombii.mcstrip.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.registry.*;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceFinder;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Mixin(RegistryLoader.class)
public class RegistryLoaderMixin {

    @Redirect(method = "load", at = @At(value = "INVOKE", target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V", ordinal = 1))
    private static void l(List instance, Consumer consumer, @Local Map map) {
        consumer = (loader) -> {
            Registry<?> registry = ((RegistryLoader.Loader)loader).registry();

            try {
                registry.freeze();
            } catch (Exception var4) {
                Exception exception = var4;
                map.put(registry.getKey(), exception);
            }

            if (((RegistryLoader.Loader<?>) loader).data.requiredNonEmpty && registry.size() == 0) {
                map.put(registry.getKey(), new IllegalStateException("Registry must be non-empty"));
            }
        };
    }

    @Redirect(method = "loadFromResource(Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/registry/RegistryOps$RegistryInfoGetter;Lnet/minecraft/registry/MutableRegistry;Lcom/mojang/serialization/Decoder;Ljava/util/Map;)V", at=@At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourceFinder;findResources(Lnet/minecraft/resource/ResourceManager;)Ljava/util/Map;"))
    private static Map<Identifier, Resource> init(ResourceFinder instance, ResourceManager resourceManager) {
            String directoryName = "";
            String fileExtension = "";
            try {
                Field dirName = ResourceFinder.class.getDeclaredField("directoryName");
                dirName.setAccessible(true);
                directoryName = (String) dirName.get(instance);
            } catch (NoSuchFieldException | IllegalAccessException ignored) {
                try {
                    Field dirName = ResourceFinder.class.getDeclaredField("field_39966");
                    dirName.setAccessible(true);
                    directoryName = (String) dirName.get(instance);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            try {
                Field fileExt = ResourceFinder.class.getDeclaredField("fileExtension");
                fileExt.setAccessible(true);
                fileExtension = (String) fileExt.get(instance);
            } catch (NoSuchFieldException | IllegalAccessException ignored) {
                try {
                    Field fileExt = ResourceFinder.class.getDeclaredField("field_39967");
                    fileExt.setAccessible(true);
                    fileExtension = (String) fileExt.get(instance);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

            String finalFileExtension = fileExtension;
            Map<Identifier, Resource> resources = resourceManager.findResources(directoryName, (path) -> path.getPath().endsWith(finalFileExtension));
            Map<Identifier, Resource> resources2 = new HashMap<>();
            resources.keySet().forEach(identifier -> {
                if (
                        !identifier.getNamespace().equals("c") &&
                                !identifier.getNamespace().equals("minecraft") ||
                                identifier.getPath().contains("worldgen/world_preset/normal.json") ||
                                identifier.getPath().contains("worldgen/biome") ||
                                identifier.getPath().contains("worldgen/noise/") ||
                                identifier.getPath().contains("worldgen/multi_noise_biome_source") ||
                                identifier.getPath().contains("damage_type") ||
                                identifier.getPath().contains("flat_level_generator_preset/redstone_ready.json") ||
                                identifier.getPath().contains("world_preset/flat") ||
                                identifier.getPath().contains("dimension_type/") ||
                                identifier.getPath().contains("worldgen/noise_settings")
                ) {
                    resources2.put(identifier, resources.get(identifier));
                }
            });
            return resources2;
    }

}
