package fr.xxathyx.chunkhoppers.commands; 

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.xxathyx.chunkhoppers.configuration.Configuration;
import fr.xxathyx.chunkhoppers.items.ItemStacks;

public class Hoppers implements CommandExecutor {
	
	private ItemStacks items = new ItemStacks();
	private Configuration configuration = new Configuration();
	
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg3) {
		
		if(cmd.getName().equalsIgnoreCase("hoppers")) {
			if(sender.hasPermission("chunkhoppers.command.hoppers")) {				
				if(arg3.length == 3) {
					if(arg3[0].equalsIgnoreCase("give")) {
				        try { 
				            Integer.parseInt(arg3[2].toString()); 
				        } catch (NumberFormatException e) { 
				            sender.sendMessage(configuration.invalid_number(arg3[2].toString()));
				            return false;
				        }
				        
						String targetName = arg3[1].toString();
						Player target = Bukkit.getPlayer(targetName);
						
						if(!(target.isOnline())) {
							sender.sendMessage(configuration.player_offline(targetName));
							return false;
						}

						if(target.getInventory().firstEmpty() == -1) {
							sender.sendMessage(configuration.player_inventory_full(targetName));
							return false;
						}else {
							target.getInventory().addItem(items.hopper(Integer.parseInt(arg3[2].toString())));
							sender.sendMessage(configuration.send_hoppers(arg3[2].toString(), targetName));
							target.sendMessage(configuration.receive_hoppers(arg3[2].toString()));
							return false;
						}
					}
				}else if(arg3.length == 2) {
					
					String targetName = arg3[1].toString();
					Player target = Bukkit.getPlayer(targetName);
					
					if(!(Bukkit.getOnlinePlayers().contains(target))) {
						sender.sendMessage(configuration.player_offline(targetName));
						return false;
					}
					
					if(target.getInventory().firstEmpty() == -1) {
						sender.sendMessage(configuration.player_inventory_full(targetName));
						return false;
					}else {
						target.getInventory().addItem(items.hopper(1));
						sender.sendMessage(configuration.send_hoppers("1", targetName));
						target.sendMessage(configuration.receive_hoppers("1"));
						return false;
					}
					
				}else {
					sender.sendMessage(ChatColor.RED + "Utilisation -> /hoppers give <name> (ammount)");
					sender.sendMessage(ChatColor.RED + "Example : /hoppers give Xxathyx 1");
					return false;
				}
			}else {
				sender.sendMessage(configuration.insufficient_permissions());
				return false;
			}
		}
		return false;
	}
}