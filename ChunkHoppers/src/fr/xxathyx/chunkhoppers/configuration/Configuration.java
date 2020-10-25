package fr.xxathyx.chunkhoppers.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.xxathyx.chunkhoppers.Main;

public class Configuration {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	private File configuration = new File(plugin.getDataFolder(), "configuration.yml");
	
	private FileConfiguration fileconfiguration;
	
	public void setup() throws IOException {
		
		if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdirs();
		}
				
		if(!configuration.exists()) {
			
			fileconfiguration = new YamlConfiguration();
			
			fileconfiguration.set("item.name", "&6&lChunk Recepter");
			fileconfiguration.set("item.description", "&eRetrieving elements within a chunk");
			fileconfiguration.set("insufficient_permissions", "&cYou don't have permission to execute this command.");
			fileconfiguration.set("invalid_number", "&c%hoppers_number% is not a valid number.");
			fileconfiguration.set("player_offline", "&c%player_name% is not online.");
			fileconfiguration.set("player_inventory_full", "&c%player_name% inventory is full.");
			fileconfiguration.set("destroy_inventory_full", "&cYou cannot recover this block, your inventory is full.");
			fileconfiguration.set("send_hoppers", "&aYou have successfully sent " + "%hoppers_number%" + " Chunk Hoppers to " + "%player_name%.");
			fileconfiguration.set("receive_hoppers", "&aYou received " + "%hoppers_number%" + " Chunk Hoppers.");
			
			fileconfiguration.save(configuration);
		}
	}
	
	public FileConfiguration getConfigFile() {
		
		fileconfiguration = new YamlConfiguration();
		
			try {
				fileconfiguration.load(configuration);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
		return fileconfiguration;
    }
	
	public String getMessage(String a) {
		
		String message = a;
		
		String c = ChatColor.translateAlternateColorCodes ('&', message);
		
		return c;
	}
	
	public String getItemName() {
		return getMessage(this.getConfigFile().getString("item.name"));
	}
	
	public String getItemDescription() {
		return getMessage(this.getConfigFile().getString("item.description"));
	}
	
	public String insufficient_permissions() {
		return getMessage(this.getConfigFile().getString("insufficient_permissions"));
	}
	
	public String invalid_number(String number) {
		
		String a = this.getConfigFile().getString("invalid_number");
		
		if(a.contains("%hoppers_number%")) {
			a = a.replace("%hoppers_number%", number);
		}
		return getMessage(a);
	}
	
	public String player_offline(String player_name) {
		
		String a = this.getConfigFile().getString("player_offline");
		
		if(a.contains("%player_name%")) {
			a = a.replace("%player_name%", player_name);
		}
		return getMessage(a);
	}
	
	public String player_inventory_full(String player_name) {
		
		String a = this.getConfigFile().getString("player_inventory_full");
		
		if(a.contains("%player_name%")) {
			a = a.replace("%player_name%", player_name);
		}
		return getMessage(a);
	}
	
	public String send_hoppers(String number, String player_name) {
		
		String a = this.getConfigFile().getString("send_hoppers");
		
		if(a.contains("%hoppers_number%")) {
			a = a.replace("%hoppers_number%", number);
		}
		
		if(a.contains("%player_name%")) {
			a = a.replace("%player_name%", player_name);
		}
		return getMessage(a);
	}
	
	public String receive_hoppers(String number) {
		
		String a = this.getConfigFile().getString("receive_hoppers");
		
		if(a.contains("%hoppers_number%")) {
			a = a.replace("%hoppers_number%", number);
		}
		return getMessage(a);
	}
	
	public String destroy_inventory_full() {
		return getMessage(this.getConfigFile().getString("destroy_inventory_full"));
	}
}