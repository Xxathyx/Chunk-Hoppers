package fr.xxathyx.chunkhoppers.util;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;

import fr.xxathyx.chunkhoppers.configuration.Configuration;

public class CropsUtils {
		
	@SuppressWarnings("deprecation")
	public static ItemStack fromCrops(Crops crops) {
		
		Configuration configuration = new Configuration();
		
		int state = 0;
		
		if(crops.getState() != null) {
			state = crops.getState().getData();
		}
				
		if(crops.getItemType().equals(Material.CACTUS)) {
			return new ItemStack(Material.CACTUS, (Math.random() <= 0.5) ? configuration.getCactusMinimum() : configuration.getCactusMaximum());
		}
		
		if(crops.getItemType().equals(Material.SUGAR_CANE_BLOCK)) {
			return new ItemStack(Material.SUGAR_CANE, (Math.random() <= 0.5) ? configuration.getSugarCaneMinimum() : configuration.getSugarCaneMaximum());
		}
		
		if(crops.getItemType().equals(Material.POTATO)) {
						
			if(state == 7) {
				return new ItemStack(Material.POTATO_ITEM, (Math.random() <= 0.5) ? configuration.getPotatoMinimum() : configuration.getPotatoMaximum());
			}
		}
		
		if(crops.getItemType().equals(Material.CARROT)) {
						
			if(state == 7) {
				return new ItemStack(Material.CARROT_ITEM, (Math.random() <= 0.5) ? configuration.getCarrotMinimum() : configuration.getCarrotMaximum());
			}
		}
		
		if(crops.getItemType().equals(Material.MELON_STEM)) {
						
			if(state == 7) {
				return new ItemStack(Material.MELON, (Math.random() <= 0.5) ? configuration.getMelonMinimum() : configuration.getMelonMaximum());
			}
		}
		
		if(crops.getItemType().equals(Material.PUMPKIN_STEM)) {
						
			if(state == 7) {
				return new ItemStack(Material.PUMPKIN, (Math.random() <= 0.5) ? configuration.getPumkinMinimum() : configuration.getPumkinMaximum());
			}
		}
		
		if(crops.getItemType().equals(Material.CROPS)) {
						
			if(state == 7) {
				return new ItemStack(Material.WHEAT, (Math.random() <= 0.5) ? configuration.getWheatMinimum() : configuration.getWheatMaximum());
			}
		}
		
		if(crops.getItemType().equals(Material.NETHER_WARTS)) {
						
			if(state == 3) {
				return new ItemStack(Material.NETHER_STALK, new Random().nextInt((configuration.getWartMaximum() - configuration.getWartMinimum()) + 1) + 2);
			}
		}
		
		if(crops.getItemType().equals(Material.COCOA)) {
			
			if(state == 0) {
				state = 11;
			}
			
			if(state == 8) {
				return new ItemStack(351, (Math.random() <= 0.5) ? configuration.getCocoaMinimum() : configuration.getCocoaMaximum(), (short) 3);
			}
			
			if(state == 9) {
				return new ItemStack(351, (Math.random() <= 0.5) ? configuration.getCocoaMinimum() : configuration.getCocoaMaximum(), (short) 3);
			}
			
			if(state == 10) {
				return new ItemStack(351, (Math.random() <= 0.5) ? configuration.getCocoaMinimum() : configuration.getCocoaMaximum(), (short) 3);
			}
			
			if(state == 11) {
				return new ItemStack(351, (Math.random() <= 0.5) ? configuration.getCocoaMinimum() : configuration.getCocoaMaximum(), (short) 3);
			}
		}
		return new ItemStack(crops.getItemType(), 1);
	}
}