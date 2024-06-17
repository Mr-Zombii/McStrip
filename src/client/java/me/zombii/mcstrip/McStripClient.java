package me.zombii.mcstrip;

import me.zombii.mcstrip.improved_redstone.ImprovedBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.render.RenderLayer;

public class McStripClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ImprovedBlocks.IMPROVED_REDSTONE_COMPARATOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ImprovedBlocks.IMPROVED_REDSTONE_WALL_TORCH, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ImprovedBlocks.IMPROVED_REDSTONE_REPEATER, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ImprovedBlocks.IMPROVED_REDSTONE_TORCH, RenderLayer.getCutout());
	}
}