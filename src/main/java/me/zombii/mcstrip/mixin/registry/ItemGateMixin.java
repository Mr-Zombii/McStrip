package me.zombii.mcstrip.mixin.registry;

import me.zombii.mcstrip.ContentControl;
import me.zombii.mcstrip.McStrip;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(Items.class)
public class ItemGateMixin {

    @Inject(at = @At("HEAD"), method = "register(Lnet/minecraft/block/Block;[Lnet/minecraft/block/Block;)Lnet/minecraft/item/Item;", cancellable = true)
    private static void registerBlocks(Block block, Block[] blocks, CallbackInfoReturnable<Item> cir) {
        BlockItem blockItem = new BlockItem(block, new Item.Settings());
        Block[] var3 = blocks;
        int var4 = blocks.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Block block2 = var3[var5];
            if (block2 != null) {
                Item.BLOCK_ITEMS.put(block2, blockItem);
            }
        }

        cir.setReturnValue(Items.register(blockItem));
    }

    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void init(CallbackInfo ci) {
        ContentControl.initCommonAllowList();
        ContentControl.initItemAllowList();
    }

    private static boolean didAirOnce = false;

    @Inject(at = @At("HEAD"), method = "register(Lnet/minecraft/util/Identifier;Lnet/minecraft/item/Item;)Lnet/minecraft/item/Item;", cancellable = true)
    private static void registerItem(Identifier id, Item item, CallbackInfoReturnable<Item> cir) {

        if (Objects.equals(id.getNamespace(), "minecraft")) {
            if (!ContentControl.itemAllowList.contains(id.getPath())) {
                McStrip.LOGGER.info("Stopped item \"{}\" From Registering", id);
                cir.setReturnValue(item);
            }

            if (id.getPath().equals("air")) {
                if (didAirOnce) cir.setReturnValue(item);
                didAirOnce = true;
            }
        }
    }


}
