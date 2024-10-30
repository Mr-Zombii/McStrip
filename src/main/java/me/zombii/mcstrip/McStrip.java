package me.zombii.mcstrip;

import me.zombii.mcstrip.improved_redstone.ImprovedBlocks;
import me.zombii.mcstrip.improved_redstone.ImprovedItems;
import me.zombii.mcstrip.improved_redstone.McStripItemGroups;
import net.fabricmc.api.ModInitializer;

import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class McStrip implements ModInitializer {

	public static final String MOD_ID = "mcstrip";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ImprovedBlocks.registerBlocks();
		ImprovedItems.registerItems();
		McStripItemGroups.registerItemGroups();
	}

}