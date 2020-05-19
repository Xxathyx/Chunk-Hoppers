package fr.xxathyx.chunkhoppers.listeners;

import org.bukkit.Material;
import org.bukkit.block.Hopper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import fr.xxathyx.chunkhoppers.configuration.Configuration;
import fr.xxathyx.chunkhoppers.items.ItemStacks;

public class DestroyRecepter implements Listener {
	
	private ItemStacks item = new ItemStacks();
	private Configuration configuration = new Configuration();
	
	@EventHandler
    public void onDestroyRecepter(BlockBreakEvent event) {
		
		if(event.getBlock().getState() instanceof Hopper) {
			
			Hopper hopper = (Hopper) event.getBlock().getState();
			
			if(hopper.getInventory().getName().equals(configuration.getItemName())) {
				
				if(event.getPlayer().getInventory().firstEmpty() == -1) {
					event.setCancelled(true);
					event.getPlayer().sendMessage(configuration.destroy_inventory_full());
					return;
				}else {
					event.setCancelled(true);
					event.getBlock().setType(Material.AIR);
					event.getPlayer().getInventory().addItem(item.hopper(1));
					event.getPlayer().sendMessage(configuration.receive_hoppers("1"));
					return;
				}
			}
		}
	}
}