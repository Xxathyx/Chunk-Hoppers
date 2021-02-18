package fr.xxathyx.chunkhoppers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

import fr.xxathyx.chunkhoppers.commands.HoppersCommands;
import fr.xxathyx.chunkhoppers.configuration.Configuration;
import fr.xxathyx.chunkhoppers.listeners.BlockGrow;
import fr.xxathyx.chunkhoppers.listeners.ClickSign;
import fr.xxathyx.chunkhoppers.listeners.DestroyRecepter;
import fr.xxathyx.chunkhoppers.listeners.EntityDeath;

public class Main extends JavaPlugin {
	
	private Map<String, Sign> toLinkSigns = new HashMap<String, Sign>();
	
	private final List<Location> toReplaceLocations = new ArrayList<Location>();
	
	private final List<Integer> toReplaceBlocksIDs = new ArrayList<Integer>();
	private final List<Byte> toReplaceBlocksData = new ArrayList<Byte>();
	
	private Configuration configuration;
	private Economy economy = null;
	
	public void onEnable() {
		
		if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
			this.getLogger().severe(ChatColor.DARK_RED + "[Chunk-Hoppers]: " + ChatColor.RED + "Vault plugin not found/installed, this can cause problems.");
		}
		
		setupEconomy();
		
		getCommand("hoppers").setExecutor(new HoppersCommands());
		
		Bukkit.getServer().getPluginManager().registerEvents(new EntityDeath(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new BlockGrow(), this);
		
		Bukkit.getServer().getPluginManager().registerEvents(new DestroyRecepter(), this);
		
		Bukkit.getServer().getPluginManager().registerEvents(new ClickSign(), this);
		
		configuration = new Configuration();
		
		try {
			configuration.setup();
			configuration.updateConfiguration();
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		
	    Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	    	
			@SuppressWarnings("deprecation")
			public void run() {
	    		
				if(!toReplaceLocations.isEmpty()) {
					
		    		for(int i = 0; i < toReplaceLocations.size(); i++) {
		    			
		    			toReplaceLocations.get(i).getBlock().setTypeId(toReplaceBlocksIDs.get(i));
		    			toReplaceLocations.get(i).getBlock().setData(toReplaceBlocksData.get(i));
		    					    			
		    			toReplaceLocations.remove(i);
		    			toReplaceBlocksIDs.remove(i);
		    			toReplaceBlocksData.remove(i);
		    		}
				}
	        }	
	    }, 1L, 1L);
	}
	
	public void setupEconomy() {
		
        RegisteredServiceProvider<Economy> registeredServiceProvider = getServer().getServicesManager().getRegistration(Economy.class);
        
        if(registeredServiceProvider != null) {
            economy = registeredServiceProvider.getProvider();
            System.out.print(ChatColor.DARK_GRAY + "[Chunk-Hoppers]: " + ChatColor.GRAY + "Vault Economy successfully setup.");
            return;
        }
	}
	
	public Economy getEconomy() {
		return economy;
	}
	
	public Map<String, Sign> getToLinkSigns() {
		return toLinkSigns;
	}
	
	public List<Location> getToReplaceLocations() {
		return toReplaceLocations;
	}
	
	public List<Integer> getToReplaceBlocksIDs() {
		return toReplaceBlocksIDs;
	}
	
	public List<Byte> getToReplaceBlocksData() {
		return toReplaceBlocksData;
	}
}