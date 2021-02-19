package fr.xxathyx.chunkhoppers.tasks;

import java.util.Arrays;
import java.util.Iterator;

import org.bukkit.Chunk;
import org.bukkit.block.Hopper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
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
		
        if(event.getEntity() instanceof Player) {
        	if(!configuration.hoppers_catch_on_player_death()) {
        		return;
        	}
        }
		
		Hopper hopper = null;
		
        Entity entity = event.getEntity();
        Chunk chunk = entity.getLocation().getChunk();
        
        for (int i = 0; i < chunk.getTileEntities().length; i++) {        	
            if (chunk.getTileEntities()[i] instanceof Hopper) {
            	if(((Hopper) chunk.getTileEntities()[i]).getInventory().getName().equals(configuration.hoppers_item_name())) {
            		hopper = (Hopper) chunk.getTileEntities()[i];
            		break;
            	}
            }
        }
		
        if(hopper != null) {
            if (hopper.getInventory().getName().equals(configuration.hoppers_item_name())) {
            	
                for (int i = 0; i < event.getDrops().size(); i++) {
                	
                	if(!(hopper.getInventory().firstEmpty() == -1)) {
                		
                		hopper.getInventory().addItem(event.getDrops().get(i));
                		
                		if(!Arrays.asList(chunk.getEntities()).isEmpty()) {
                			
                            Iterator<Entity> iterator = Arrays.asList(chunk.getEntities()).iterator();
                    		
                            while(iterator.hasNext()) {
                            	
                            	Entity unknown = iterator.next();
                            	
                    			if(unknown.getType().equals(EntityType.DROPPED_ITEM) && unknown instanceof Item) {
                    				                				
                    				hopper.getInventory().addItem(((Item)unknown).getItemStack());
                    				unknown.remove();
                    			}
                            }
                		}
                	}
                }
            }
        }
	}
}