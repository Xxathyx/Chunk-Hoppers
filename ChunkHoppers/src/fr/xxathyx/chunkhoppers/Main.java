package fr.xxathyx.chunkhoppers;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.xxathyx.chunkhoppers.commands.Hoppers;
import fr.xxathyx.chunkhoppers.configuration.Configuration;
import fr.xxathyx.chunkhoppers.listeners.DestroyRecepter;
import fr.xxathyx.chunkhoppers.listeners.EntityDeath;

public class Main extends JavaPlugin {
		
	private Configuration configuration;
		
	public void onEnable() {
		
		getCommand("hoppers").setExecutor(new Hoppers());
		
		Bukkit.getServer().getPluginManager().registerEvents(new EntityDeath(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DestroyRecepter(), this);
				
		configuration = new Configuration();
		
		try {
			configuration.setup();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onDisable() {
		
	}
}