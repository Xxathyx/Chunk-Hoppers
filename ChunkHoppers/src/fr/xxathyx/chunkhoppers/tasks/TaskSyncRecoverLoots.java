package fr.xxathyx.chunkhoppers.tasks;

import org.bukkit.Chunk;
import org.bukkit.block.Hopper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import fr.xxathyx.chunkhoppers.configuration.Configuration;

public class TaskSyncRecoverLoots extends BukkitRunnable {
	
	private final Configuration configuration = new Configuration();
	
	private EntityDeathEvent event;
	
	public TaskSyncRecoverLoots(EntityDeathEvent event) {
		this.event = event;
	}
	
	@Override
	public void run() {
				
		Hopper hopper = null;
		
        Entity entity = event.getEntity();
        Chunk chunk = entity.getLocation().getChunk();

        for (int i = 0; i < chunk.getTileEntities().length; i++) {        	
            if (chunk.getTileEntities()[i] instanceof Hopper) {
                hopper = (Hopper) chunk.getTileEntities()[i];
            }
        }
		
        if(hopper != null) {
            if (hopper.getInventory().getName().equals(configuration.getItemName())) {
                for (int i = 0; i < event.getDrops().size(); i++) {
                	if(!(hopper.getInventory().firstEmpty() == -1)) {
                		hopper.getInventory().addItem(event.getDrops().get(i));
                		
                		for(int j = 0; j < chunk.getEntities().length; j++) {
                			if(chunk.getEntities()[j] instanceof Item) {
                				hopper.getInventory().addItem(((Item)chunk.getEntities()[j]).getItemStack());
                			}
                		}
                		
                		for(int j = 0; j < chunk.getEntities().length; j++) {
                			if(chunk.getEntities()[j] instanceof Item) {
                				chunk.getEntities()[j].remove();
                			}
                		}
                	}
                }
            }
        }
	}
}