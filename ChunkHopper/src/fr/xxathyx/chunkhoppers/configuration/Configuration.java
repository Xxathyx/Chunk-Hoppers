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
	
	private File configurationFile = new File(plugin.getDataFolder(), "configuration.yml");
	
	private FileConfiguration fileconfiguration;
	
	public void setup() throws IOException {
		
		if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdirs();
		}
				
		if(!configurationFile.exists()) {
			
			fileconfiguration = new YamlConfiguration();
			
			fileconfiguration.set("hoppers.item.name", "&6&lChunk Recepter");
			fileconfiguration.set("hoppers.item.description", "&eRetrieving elements within a chunk");
			
			fileconfiguration.set("hoppers-catch-growing-plantations", true);
			fileconfiguration.set("hoppers-catch-on-player-death", true);
			
			fileconfiguration.set("hoppers.buy-sign-line.1", "&6&l[Chunk Hopper]");
			fileconfiguration.set("hoppers.buy-sign-line.2", "&e&lPRICE: 100000$");
			fileconfiguration.set("hoppers.buy-sign-line.3", "&bQUANTITY: x1");
			fileconfiguration.set("hoppers.buy-sign-line.4", "&a&lCLICK TO BUY");
			fileconfiguration.set("hoppers.buy-sign-price", 100000);
			fileconfiguration.set("hoppers.buy-sign-quantity", 1);
			
			fileconfiguration.set("crops.cactus.minimum", 1);
			fileconfiguration.set("crops.cactus.maximum", 1);
			fileconfiguration.set("crops.sugar-cane.minimum", 1);
			fileconfiguration.set("crops.sugar-cane.maximum", 1);
			fileconfiguration.set("crops.pumkin.minimum", 1);
			fileconfiguration.set("crops.pumkin.maximum", 1);
			fileconfiguration.set("crops.potato.minimum", 2);
			fileconfiguration.set("crops.potato.maximum", 3);
			fileconfiguration.set("crops.carrot.minimum", 2);
			fileconfiguration.set("crops.carrot.maximum", 3);
			fileconfiguration.set("crops.melon.minimum", 8);
			fileconfiguration.set("crops.melon.maximum", 8);
			fileconfiguration.set("crops.wheat.minimum", 1);
			fileconfiguration.set("crops.wheat.maximum", 1);
			fileconfiguration.set("crops.cocoa.minimum", 3);
			fileconfiguration.set("crops.cocoa.maximum", 3);
			fileconfiguration.set("crops.nether-wart.minimum", 1);
			fileconfiguration.set("crops.nether-wart.maximum", 4);
			
			fileconfiguration.set("messages.insufficient-permissions", "&cYou don't have permission to execute this command.");
			fileconfiguration.set("messages.insufficient-money", "&cYou don't have the money to afford this chunk hopper.");
			fileconfiguration.set("messages.invalid-number", "&c%hoppers_number% is not a valid number.");
			fileconfiguration.set("messages.player-offline", "&c%player_name% is not online.");
			fileconfiguration.set("messages.player-inventory-full", "&c%player_name% inventory is full.");
			fileconfiguration.set("messages.player-inventory-full-dropped", "&cYour inventory is full, pickup the hopper from the ground.");
			fileconfiguration.set("messages.buy-sign-to-define", "&aYou have to define a buy sign, to define it click on a sign.");
			fileconfiguration.set("messages.buy-sign-defined", "&aYou have successfully defined this sign has hoppers buy sign.");
			fileconfiguration.set("messages.destroy-inventory-full", "&cYou cannot recover this block, your inventory is full.");
			fileconfiguration.set("messages.send-hoppers", "&aYou have successfully sent %hoppers_number% Chunk Hoppers to %player_name%.");
			fileconfiguration.set("messages.receive-hoppers", "&aYou received %hoppers_number% Chunk Hoppers.");
			
			fileconfiguration.save(configurationFile);
		}
	}
	
	public FileConfiguration getConfigFile() {
		
		fileconfiguration = new YamlConfiguration();
		
			try {
				fileconfiguration.load(configurationFile);
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

	public String getFirstBuySignLine() {
		return getMessage(getConfigFile().getString("hoppers.buy-sign-line.1"));
	}
	
	public String getSecondBuySignLine() {
		return getMessage(getConfigFile().getString("hoppers.buy-sign-line.2"));
	}
	
	public String getThirdBuySignLine() {
		return getMessage(getConfigFile().getString("hoppers.buy-sign-line.3"));
	}
	
	public String getFourthBuySignLine() {
		return getMessage(getConfigFile().getString("hoppers.buy-sign-line.4"));
	}
	
	public double getBuySignPrice() {
		return getConfigFile().getDouble("hoppers.buy-sign-price");
	}
	
	public int getBuySignQuantity() {
		return getConfigFile().getInt("hoppers.buy-sign-quantity");
	}
	
	public int getCactusMinimum() {
		return getConfigFile().getInt("crops.cactus.minimum");
	}
	
	public int getCactusMaximum() {
		return getConfigFile().getInt("crops.cactus.maximum");
	}
	
	public int getSugarCaneMinimum() {
		return getConfigFile().getInt("crops.sugar-cane.minimum");
	}
	
	public int getSugarCaneMaximum() {
		return getConfigFile().getInt("crops.sugar-cane.maximum");
	}
	
	public int getPumkinMinimum() {
		return getConfigFile().getInt("crops.pumkin.minimum");
	}
	
	public int getPumkinMaximum() {
		return getConfigFile().getInt("crops.pumkin.maximum");
	}
	
	public int getPotatoMinimum() {
		return getConfigFile().getInt("crops.potato.minimum");
	}
	
	public int getPotatoMaximum() {
		return getConfigFile().getInt("crops.potato.maximum");
	}
	
	public int getCarrotMinimum() {
		return getConfigFile().getInt("crops.carrot.minimum");
	}
	
	public int getCarrotMaximum() {
		return getConfigFile().getInt("crops.carrot.maximum");
	}
	
	public int getMelonMinimum() {
		return getConfigFile().getInt("crops.melon.minimum");
	}
	
	public int getMelonMaximum() {
		return getConfigFile().getInt("crops.melon.maximum");
	}
	
	public int getWheatMinimum() {
		return getConfigFile().getInt("crops.wheat.minimum");
	}
	
	public int getWheatMaximum() {
		return getConfigFile().getInt("crops.wheat.maximum");
	}
	
	public int getCocoaMinimum() {
		return getConfigFile().getInt("crops.cocoa.minimum");
	}
	
	public int getCocoaMaximum() {
		return getConfigFile().getInt("crops.cocoa.maximum");
	}
	
	public int getWartMinimum() {
		return getConfigFile().getInt("crops.nether-wart.minimum");
	}
	
	public int getWartMaximum() {
		return getConfigFile().getInt("crops.nether-wart.maximum");
	}
	
	public String hoppers_item_name() {
		return getMessage(getConfigFile().getString("hoppers.item.name"));
	}
	
	public String hoppers_item_description() {
		return getMessage(getConfigFile().getString("hoppers.item.description"));
	}
	
	public boolean hoppers_catch_growing_plantations() {
		return getConfigFile().getBoolean("hoppers.catch-growing-plantations");
	}
	
	public boolean hoppers_catch_on_player_death() {
		return getConfigFile().getBoolean("hoppers.catch-on-player-death");
	}
	
	public String insufficient_permissions() {
		return getMessage(getConfigFile().getString("messages.insufficient-permissions"));
	}
	
	public String insufficient_money() {
		return getMessage(getConfigFile().getString("messages.insufficient-money"));
	}
	
	public String invalid_number(String number) {
		
		String a = getConfigFile().getString("messages.invalid-number");
		
		if(a.contains("%hoppers_number%")) {
			a = a.replace("%hoppers_number%", number);
		}
		return getMessage(a);
	}
	
	public String player_offline(String player_name) {
		
		String a = getConfigFile().getString("messages.player-offline");
		
		if(a.contains("%player_name%")) {
			a = a.replace("%player_name%", player_name);
		}
		return getMessage(a);
	}
	
	public String player_inventory_full(String player_name) {
		
		String a = getConfigFile().getString("messages.player-inventory-full");
		
		if(a.contains("%player_name%")) {
			a = a.replace("%player_name%", player_name);
		}
		return getMessage(a);
	}
	
	public String player_inventory_full_dropped() {
		return getMessage(getConfigFile().getString("messages.player-inventory-full-dropped"));
	}
	
	public String send_hoppers(String number, String player_name) {
		
		String a = getConfigFile().getString("messages.send-hoppers");
		
		if(a.contains("%hoppers_number%")) {
			a = a.replace("%hoppers_number%", number);
		}
		
		if(a.contains("%player_name%")) {
			a = a.replace("%player_name%", player_name);
		}
		return getMessage(a);
	}
	
	public String receive_hoppers(String number) {
		
		String a = getConfigFile().getString("messages.receive-hoppers");
		
		if(a.contains("%hoppers_number%")) {
			a = a.replace("%hoppers_number%", number);
		}
		return getMessage(a);
	}
	
	public String destroy_inventory_full() {
		return getMessage(getConfigFile().getString("messages.destroy-inventory-full"));
	}
	
	public String buy_sign_to_define() {
		return getMessage(getConfigFile().getString("messages.buy-sign-to-define"));
	}
	
	public String buy_sign_defined() {
		return getMessage(getConfigFile().getString("messages.buy-sign-defined"));
	}
	
	public void updateConfiguration() throws IOException, InvalidConfigurationException {
		
		if(isOld()) {
			updateFromOld();
		}
		
		FileConfiguration fileconfiguration = new YamlConfiguration();
		
		if(getConfigFile().getString("hoppers.item.name") != null) {
			fileconfiguration.set("hoppers.item.name", getConfigFile().getString("hoppers.item.name"));
		}else {
			fileconfiguration.set("hoppers.item.name", "&6&lChunk Recepter");
		}
		
		if(getConfigFile().getString("hoppers.item.description") != null) {
			fileconfiguration.set("hoppers.item.description", getConfigFile().getString("hoppers.item.description"));
		}else {
			fileconfiguration.set("hoppers.item.description", "&eRetrieving elements within a chunk");
		}
		
		fileconfiguration.set("hoppers.catch-growing-plantations", hoppers_catch_growing_plantations());
		fileconfiguration.set("hoppers.catch-on-player-death", hoppers_catch_on_player_death());
		
		if(getConfigFile().getString("hoppers.buy-sign-line.1") != null) {
			fileconfiguration.set("hoppers.buy-sign-line.1", getConfigFile().getString("hoppers.buy-sign-line.1"));
		}else {
			fileconfiguration.set("hoppers.buy-sign-line.1", "&6&l[Chunk Hopper]");
		}
		
		if(getConfigFile().getString("hoppers.buy-sign-line.2") != null) {
			fileconfiguration.set("hoppers.buy-sign-line.2", getConfigFile().getString("hoppers.buy-sign-line.2"));
		}else {
			fileconfiguration.set("hoppers.buy-sign-line.2", "&ePRICE: 100000$");
		}
		
		if(getConfigFile().getString("hoppers.buy-sign-line.3") != null) {
			fileconfiguration.set("hoppers.buy-sign-line.3", getConfigFile().getString("hoppers.buy-sign-line.3"));

		}else {
			fileconfiguration.set("hoppers.buy-sign-line.3", "&eQUANTITY: x1");
		}
		
		if(getConfigFile().getString("hoppers.buy-sign-line.4") != null) {
			fileconfiguration.set("hoppers.buy-sign-line.4", getConfigFile().getString("hoppers.buy-sign-line.4"));
		}else {
			fileconfiguration.set("hoppers.buy-sign-line.4", "&a&lCLICK TO BUY");
		}
		
		fileconfiguration.set("hoppers.buy-sign-price", getConfigFile().getDouble("hoppers.buy-sign-price"));
		fileconfiguration.set("hoppers.buy-sign-quantity", getConfigFile().getInt("hoppers.buy-sign-quantity"));
		
		fileconfiguration.set("crops.cactus.minimum", getConfigFile().getInt("crops.cactus.minimum"));
		fileconfiguration.set("crops.cactus.maximum", getConfigFile().getInt("crops.cactus.maximum"));
		fileconfiguration.set("crops.sugar-cane.minimum", getConfigFile().getInt("crops.sugar-cane.minimum"));
		fileconfiguration.set("crops.sugar-cane.maximum", getConfigFile().getInt("crops.sugar-cane.maximum"));
		fileconfiguration.set("crops.pumkin.minimum", getConfigFile().getInt("crops.pumkin.minimum"));
		fileconfiguration.set("crops.pumkin.maximum", getConfigFile().getInt("crops.pumkin.maximum"));
		fileconfiguration.set("crops.potato.minimum", getConfigFile().getInt("crops.potato.minimum"));
		fileconfiguration.set("crops.potato.maximum", getConfigFile().getInt("crops.potato.maximum"));
		fileconfiguration.set("crops.carrot.minimum", getConfigFile().getInt("crops.carrot.minimum"));
		fileconfiguration.set("crops.carrot.maximum", getConfigFile().getInt("crops.carrot.maximum"));
		fileconfiguration.set("crops.melon.minimum", getConfigFile().getInt("crops.melon.minimum"));
		fileconfiguration.set("crops.melon.maximum", getConfigFile().getInt("crops.melon.maximum"));
		fileconfiguration.set("crops.wheat.minimum", getConfigFile().getInt("crops.wheat.minimum"));
		fileconfiguration.set("crops.wheat.maximum", getConfigFile().getInt("crops.wheat.maximum"));
		fileconfiguration.set("crops.cocoa.minimum", getConfigFile().getInt("crops.cocoa.minimum"));
		fileconfiguration.set("crops.cocoa.maximum", getConfigFile().getInt("crops.cocoa.maximum"));
		fileconfiguration.set("crops.nether-wart.minimum", getConfigFile().getInt("crops.nether-wart.minimum"));
		fileconfiguration.set("crops.nether-wart.maximum", getConfigFile().getInt("crops.nether-wart.maximum"));
		
		if(getConfigFile().getString("messages.insufficient-money") != null) {
			fileconfiguration.set("messages.insufficient-money", getConfigFile().getString("messages.insufficient-money"));
		}else {
			fileconfiguration.set("messages.insufficient-money", "&cYou don't have the money to afford this chunk hopper.");
		}
		
		fileconfiguration.set("messages.insufficient-permissions", getConfigFile().getString("messages.insufficient-permissions"));
		fileconfiguration.set("messages.invalid-number", getConfigFile().getString("messages.invalid-number"));
		fileconfiguration.set("messages.player-offline", getConfigFile().getString("messages.player-offline"));
		fileconfiguration.set("messages.player-inventory-full", getConfigFile().getString("messages.player-inventory-full"));
		
		if(getConfigFile().getString("messages.player-inventory-full-dropped") != null) {
			fileconfiguration.set("messages.player-inventory-full-dropped", getConfigFile().getString("messages.player-inventory-full-dropped"));
		}else {
			fileconfiguration.set("messages.player-inventory-full-dropped", "&cYour inventory is full, pickup the hopper from the ground.");
		}
		
		fileconfiguration.set("messages.destroy-inventory-full", getConfigFile().getString("messages.destroy-inventory-full"));
		
		if(getConfigFile().getString("messages.buy-sign-to-define") != null) {
			fileconfiguration.set("messages.buy-sign-to-define", getConfigFile().getString("messages.buy-sign-to-define"));
		}else {
			fileconfiguration.set("messages.buy-sign-to-define", "&aYou have to define a buy sign, to define it click on a sign.");
		}
		
		if(getConfigFile().getString("messages.buy-sign-defined") != null) {
			fileconfiguration.set("messages.buy-sign-defined", getConfigFile().getString("messages.buy-sign-defined"));
		}else {
			fileconfiguration.set("messages.buy-sign-defined", "&aYou have successfully defined this sign has hoppers buy sign.");
		}
		
		fileconfiguration.set("messages.send-hoppers", getConfigFile().getString("messages.send-hoppers"));
		fileconfiguration.set("messages.receive-hoppers", getConfigFile().getString("messages.receive-hoppers"));
		
		fileconfiguration.save(configurationFile);
	}
	
	public boolean isOld() throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		FileConfiguration fileconfiguration = new YamlConfiguration();
		
		fileconfiguration.load(configurationFile);
		
		if(fileconfiguration.getString("insufficient_permissions") != null) {
			System.out.print(ChatColor.DARK_GRAY + "[Chunk-Hoppers]: " + ChatColor.GRAY + " Old configuration detected.");
			return true;
		}
		return false;
	}
	
	public void updateFromOld() throws IOException {
		
		String item_name = getConfigFile().getString("item.name");
		String item_description = getConfigFile().getString("item.description");
		String insufficient_permissions = getConfigFile().getString("insufficient_permissions");
		String invalid_number = getConfigFile().getString("invalid_number");
		String player_offline = getConfigFile().getString("player_offline");
		String player_inventory_full = getConfigFile().getString("player_inventory_full");
		String player_inventory_full_dropped = getConfigFile().getString("player_inventory_full_dropped");
		String destroy_inventory_full = getConfigFile().getString("destroy_inventory_full");
		String send_hoppers = getConfigFile().getString("send_hoppers");
		String receive_hoppers = getConfigFile().getString("receive_hoppers");
		
		configurationFile.delete();
		
		fileconfiguration = new YamlConfiguration();
		
		fileconfiguration.set("hoppers.item.name", item_name);
		fileconfiguration.set("hoppers.item.description", item_description);
		
		fileconfiguration.set("hoppers.catch-growing-plantations", true);
		fileconfiguration.set("hoppers.catch-on-player-death", true);
		
		fileconfiguration.set("hoppers.buy-sign-line.1", "&6&l[Chunk Hopper]");
		fileconfiguration.set("hoppers.buy-sign-line.2", "&ePRICE: 100000$");
		fileconfiguration.set("hoppers.buy-sign-line.3", "&eQUANTITY: x1");
		fileconfiguration.set("hoppers.buy-sign-line.4", "&a&lCLICK TO BUY");
		fileconfiguration.set("hoppers.buy-sign-price", 100000);
		fileconfiguration.set("hoppers.buy-sign-quantity", 1);
		
		fileconfiguration.set("crops.cactus.minimum", 1);
		fileconfiguration.set("crops.cactus.maximum", 1);
		fileconfiguration.set("crops.sugar-cane.minimum", 1);
		fileconfiguration.set("crops.sugar-cane.maximum", 1);
		fileconfiguration.set("crops.pumkin.minimum", 1);
		fileconfiguration.set("crops.pumkin.maximum", 1);
		fileconfiguration.set("crops.potato.minimum", 2);
		fileconfiguration.set("crops.potato.maximum", 3);
		fileconfiguration.set("crops.carrot.minimum", 2);
		fileconfiguration.set("crops.carrot.maximum", 3);
		fileconfiguration.set("crops.melon.minimum", 8);
		fileconfiguration.set("crops.melon.maximum", 8);
		fileconfiguration.set("crops.wheat.minimum", 1);
		fileconfiguration.set("crops.wheat.maximum", 1);
		fileconfiguration.set("crops.cocoa.minimum", 1);
		fileconfiguration.set("crops.cocoa.maximum", 4);
		fileconfiguration.set("crops.nether-wart.minimum", 1);
		fileconfiguration.set("crops.nether-wart.maximum", 4);
		
		fileconfiguration.set("messages.insufficient-permissions", insufficient_permissions);
		fileconfiguration.set("messages.insufficient-money", "&cYou don't have the money to afford this chunk hopper.");
		fileconfiguration.set("messages.invalid-number", invalid_number);
		fileconfiguration.set("messages.player-offline", player_offline);
		fileconfiguration.set("messages.player-inventory-full", player_inventory_full);
		
		if(player_inventory_full_dropped != null) {
			fileconfiguration.set("messages.player-inventory-full-dropped", player_inventory_full_dropped);
		}
		
		fileconfiguration.set("messages.destroy-inventory-full", destroy_inventory_full);
		fileconfiguration.set("messages.buy-sign-to-define", "&aYou have to define a buy sign, to define it click on a sign.");
		fileconfiguration.set("messages.buy-sign-defined", "&aYou have successfully defined this sign has hoppers buy sign.");
		fileconfiguration.set("messages.send-hoppers", send_hoppers);
		fileconfiguration.set("messages.receive-hoppers", receive_hoppers);
		
		fileconfiguration.save(configurationFile);
		
		System.out.print(ChatColor.DARK_GRAY + "[Chunk-Hoppers]: " + ChatColor.GRAY + " Old configuration updated, check the new one with a lot of features.");
	}
}