package me.zombii.mcstrip.mixin;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.AdvancementCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(AdvancementCommand.class)
public class AdvancementCommandDisablerMixin {

    /**
     * @author Mr_Zombii
     * @reason Get rid of nasty advancements
     */
    @Overwrite
    public static void register(CommandDispatcher dispatcher) {}

}
