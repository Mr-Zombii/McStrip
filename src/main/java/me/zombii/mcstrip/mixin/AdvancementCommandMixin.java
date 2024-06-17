package me.zombii.mcstrip.mixin;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.AdvancementCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(AdvancementCommand.class)
public class AdvancementCommandMixin {

    /**
     * @author Mr_Zombii
     * @reason Get rid of nasty advancements
     */
    @Overwrite
    public static void register(CommandDispatcher dispatcher) {

    }

}
