package me.zombii.mcstrip.mixin.disablers;

import me.zombii.mcstrip.improved_redstone.McStripItemGroups;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.item.ItemGroups.INVENTORY;

@Mixin(ItemGroups.class)
public class ItemGroupFixerAndDisablerMixin {

    @Unique
    private static final Identifier INVENTORY_TAB_TEXTURE_ID = ItemGroup.getTabTextureId("inventory");

    @Inject(method = "registerAndGetDefault", at = @At("HEAD"), cancellable = true)
    private static void registerAndGetDefault(Registry<ItemGroup> registry, CallbackInfoReturnable<ItemGroup> cir) {
        cir.setReturnValue(Registry.register(registry, INVENTORY, ItemGroup.create(ItemGroup.Row.BOTTOM, 6).displayName(Text.translatable("itemGroup.inventory")).icon(() -> {
            return new ItemStack(Blocks.BARREL);
        }).texture(INVENTORY_TAB_TEXTURE_ID).noRenderedName().special().type(ItemGroup.Type.INVENTORY).noScrollbar().build()));
    }

    @Inject(method = "getDefaultTab", at = @At("HEAD"), cancellable = true)
    private static void getDefaultTab(CallbackInfoReturnable<ItemGroup> cir) {
        cir.setReturnValue(Registries.ITEM_GROUP.getOrThrow(McStripItemGroups.REDSTONE_ITEMGROUP_KEY));
    }

}
