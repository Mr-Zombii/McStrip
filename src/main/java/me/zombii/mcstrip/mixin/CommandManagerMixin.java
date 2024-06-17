package me.zombii.mcstrip.mixin;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.SharedConstants;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.*;
import net.minecraft.server.dedicated.command.*;
import net.minecraft.util.profiling.jfr.FlightProfiler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public class CommandManagerMixin {

    @Shadow @Final private CommandDispatcher<ServerCommandSource> dispatcher;

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/AdvancementCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void advReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/AttributeCommand;register(Lcom/mojang/brigadier/CommandDispatcher;Lnet/minecraft/command/CommandRegistryAccess;)V"))
    private void atrReg(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/BossBarCommand;register(Lcom/mojang/brigadier/CommandDispatcher;Lnet/minecraft/command/CommandRegistryAccess;)V"))
    private void bosReg(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/DamageCommand;register(Lcom/mojang/brigadier/CommandDispatcher;Lnet/minecraft/command/CommandRegistryAccess;)V"))
    private void dmgReg(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/EffectCommand;register(Lcom/mojang/brigadier/CommandDispatcher;Lnet/minecraft/command/CommandRegistryAccess;)V"))
    private void effReg(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/MeCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void meReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/ExecuteCommand;register(Lcom/mojang/brigadier/CommandDispatcher;Lnet/minecraft/command/CommandRegistryAccess;)V"))
    private void expReg(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/EnchantCommand;register(Lcom/mojang/brigadier/CommandDispatcher;Lnet/minecraft/command/CommandRegistryAccess;)V"))
    private void encReg(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/FillBiomeCommand;register(Lcom/mojang/brigadier/CommandDispatcher;Lnet/minecraft/command/CommandRegistryAccess;)V"))
    private void flbReg(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/ListCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void lisReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/LocateCommand;register(Lcom/mojang/brigadier/CommandDispatcher;Lnet/minecraft/command/CommandRegistryAccess;)V"))
    private void locReg(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/LootCommand;register(Lcom/mojang/brigadier/CommandDispatcher;Lnet/minecraft/command/CommandRegistryAccess;)V"))
    private void lotReg(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/MessageCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void megReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/ParticleCommand;register(Lcom/mojang/brigadier/CommandDispatcher;Lnet/minecraft/command/CommandRegistryAccess;)V"))
    private void parReg(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/PlaceCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void plaReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/SpreadPlayersCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void sprReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/PlaySoundCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void plsReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/StopSoundCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void stsReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/RandomCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void ranReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/ReloadCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void relReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/ReturnCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void retReg(CommandDispatcher<?> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/RideCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void ridReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/SummonCommand;register(Lcom/mojang/brigadier/CommandDispatcher;Lnet/minecraft/command/CommandRegistryAccess;)V"))
    private void sumReg(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/SayCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void sayReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/ScheduleCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void schReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/TellRawCommand;register(Lcom/mojang/brigadier/CommandDispatcher;Lnet/minecraft/command/CommandRegistryAccess;)V"))
    private void telReg(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/SeedCommand;register(Lcom/mojang/brigadier/CommandDispatcher;Z)V"))
    private void sedReg(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/TagCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void tagReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/TeamCommand;register(Lcom/mojang/brigadier/CommandDispatcher;Lnet/minecraft/command/CommandRegistryAccess;)V"))
    private void temReg(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/WorldBorderCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void wbrReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/TestCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void tesReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/RaidCommand;register(Lcom/mojang/brigadier/CommandDispatcher;Lnet/minecraft/command/CommandRegistryAccess;)V"))
    private void raidReg(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/DebugPathCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void depReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/WardenSpawnTrackerCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void warReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/SpawnArmorTrimsCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void spnReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/ServerPackCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void spcReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/DebugConfigCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V"))
    private void dccReg(CommandDispatcher<ServerCommandSource> dispatcher) {}

}
