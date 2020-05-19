package fr.xxathyx.chunkhoppers.items;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.xxathyx.chunkhoppers.configuration.Configuration;

public class ItemStacks {
	
	private Configuration configuration = new Configuration();
	
	public ItemStack hopper(int ammount) {
		
		ItemStack hopper = new ItemStack(Material.HOPPER, ammount);
	    ItemMeta hopper_meta = hopper.getItemMeta();
	    
	    hopper_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	    hopper_meta.setDisplayName(configuration.getItemName());
	    hopper_meta.setLore(Arrays.asList(new String[] { configuration.getItemDescription() }));
	    
	    hopper.setItemMeta(hopper_meta);
	    
	    return hopper;
	}
}