package fr.xxathyx.chunkhoppers.listeners;

import org.bukkit.Chunk;
import org.bukkit.block.Hopper;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import fr.xxathyx.chunkhoppers.configuration.Configuration;

public class EntityDeath implements Listener {
	
	private Configuration configuration = new Configuration();
	
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        Hopper hopper = null;

        Entity entity = event.getEntity();
        Chunk chunk = entity.getLocation().getChunk();

        for (int i = 0; i < chunk.getTileEntities().length; i++) {        	
            if (chunk.getTileEntities()[i] instanceof Hopper) {
                hopper = (Hopper) chunk.getTileEntities()[i];
            }
        }
        
        if (hopper != null) {
            if (hopper.getInventory().getName().equals(configuration.getItemName())) {
                for (int i = 0; i < event.getDrops().size(); i++) {
                	if(!(hopper.getInventory().firstEmpty() == -1)) {
                		hopper.getInventory().addItem(event.getDrops().get(i));
                	}
                }
                if(!(hopper.getInventory().firstEmpty() == -1)) {
                	event.getDrops().clear();
                }
            }
        }
    }
}