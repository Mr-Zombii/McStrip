package me.zombii.mcstrip;

import me.zombii.mcstrip.improved_redstone.blocks.ImprovedRedstoneWireBlock;
import me.zombii.mcstrip.improved_redstone.ImprovedBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.texture.atlas.AtlasLoader;

import static me.zombii.mcstrip.improved_redstone.ImprovedBlocks.IMPROVED_REDSTONE;

public class McStripClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		setupRenderlayers();
		setupColorProviders();
	}

	void setupRenderlayers() {
		RenderLayer layer = RenderLayer.getCutout();
		BlockRenderLayerMap.INSTANCE.putBlock(ImprovedBlocks.IMPROVED_REDSTONE_COMPARATOR, layer);
		BlockRenderLayerMap.INSTANCE.putBlock(ImprovedBlocks.IMPROVED_REDSTONE_WALL_TORCH, layer);
		BlockRenderLayerMap.INSTANCE.putBlock(ImprovedBlocks.IMPROVED_REDSTONE_REPEATER, layer);
		BlockRenderLayerMap.INSTANCE.putBlock(ImprovedBlocks.IMPROVED_REDSTONE_TORCH, layer);
		BlockRenderLayerMap.INSTANCE.putBlock(ImprovedBlocks.IMPROVED_REDSTONE, layer);
	}
	void setupColorProviders() {
		ColorProviderRegistry.BLOCK.register(ImprovedRedstoneWireBlock::getBlockColor, IMPROVED_REDSTONE);
	}
}