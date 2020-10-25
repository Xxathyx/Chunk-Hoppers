package fr.xxathyx.chunkhoppers.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import fr.xxathyx.chunkhoppers.Main;
import fr.xxathyx.chunkhoppers.tasks.TaskSyncRecoverLoots;

public class EntityDeath implements Listener {
	
	private final Main plugin = Main.getPlugin(Main.class);
	
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
    	new TaskSyncRecoverLoots(event).runTaskAsynchronously(plugin);
    }
}