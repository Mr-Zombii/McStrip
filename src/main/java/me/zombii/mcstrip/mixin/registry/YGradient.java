package me.zombii.mcstrip.mixin.registry;

import com.mojang.datafixers.kinds.App;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Function;

@Mixin(DensityFunctionTypes.YClampedGradient.class)
public class YGradient {

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", remap = false, target = "Lcom/mojang/serialization/codecs/RecordCodecBuilder;mapCodec(Ljava/util/function/Function;)Lcom/mojang/serialization/MapCodec;"))
    private static <O> MapCodec<DensityFunctionTypes.YClampedGradient> asw(Function<RecordCodecBuilder.Instance<O>, ? extends App<RecordCodecBuilder.Mu<O>, O>> builder) {
        MapCodec<DensityFunctionTypes.YClampedGradient> god = RecordCodecBuilder.mapCodec((instance) -> {
            return instance.group(
                    Codec.intRange(-200000 * 2, 200000 * 2).fieldOf("from_y").forGetter(DensityFunctionTypes.YClampedGradient::fromY),
                    Codec.intRange(-200000 * 2, 200000 * 2).fieldOf("to_y").forGetter(DensityFunctionTypes.YClampedGradient::toY),
                    DensityFunctionTypes.CONSTANT_RANGE.fieldOf("from_value").forGetter(DensityFunctionTypes.YClampedGradient::fromValue),
                    DensityFunctionTypes.CONSTANT_RANGE.fieldOf("to_value").forGetter(DensityFunctionTypes.YClampedGradient::toValue)).apply(instance, DensityFunctionTypes.YClampedGradient::new);
        });
        return god;
    }

}
