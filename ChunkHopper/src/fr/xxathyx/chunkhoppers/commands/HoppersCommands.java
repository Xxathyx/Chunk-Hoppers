package fr.xxathyx.chunkhoppers.commands; 

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.xxathyx.chunkhoppers.Main;
import fr.xxathyx.chunkhoppers.configuration.Configuration;
import fr.xxathyx.chunkhoppers.items.ItemStacks;

public class HoppersCommands implements CommandExecutor {
	
	private final Main plugin = Main.getPlugin(Main.class);
	
	private final ItemStacks items = new ItemStacks();
	private final Configuration configuration = new Configuration();
	
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] arg3) {
		
		if(cmd.getName().equalsIgnoreCase("hoppers")) {
			if(sender.hasPermission("chunkhoppers.command.hoppers")) {				
				if(arg3.length == 3) {
					
					if(arg3[0].equalsIgnoreCase("give")) {
				        try { 
				            Integer.parseInt(arg3[2]); 
				        } catch (NumberFormatException e) { 
				            sender.sendMessage(configuration.invalid_number(arg3[2]));
				            return false;
				        }
				        
						String targetName = arg3[1];
						Player target = Bukkit.getPlayer(targetName);
						
						if(target == null) {
							sender.sendMessage(configuration.player_offline(targetName));
							return false;
						}
						
						if(sender instanceof Player) {
							if(target.getInventory().firstEmpty() == -1) {
								sender.sendMessage(configuration.player_inventory_full(targetName));
								return false;
							}else {
								target.getInventory().addItem(items.hopper(Integer.parseInt(arg3[2])));
								sender.sendMessage(configuration.send_hoppers(arg3[2], targetName));
								target.sendMessage(configuration.receive_hoppers(arg3[2]));
								return true;
							}
						}else {
							if(target.getInventory().firstEmpty() == -1) {
								target.getWorld().dropItemNaturally(target.getLocation(), items.hopper(Integer.parseInt(arg3[2])));
								target.sendMessage(configuration.player_inventory_full_dropped());
								return true;
							}else {
								target.getInventory().addItem(items.hopper(Integer.parseInt(arg3[2])));
								sender.sendMessage(configuration.send_hoppers(arg3[2], targetName));
								target.sendMessage(configuration.receive_hoppers(arg3[2]));
								return true;
							}
						}
					}
				}else if(arg3.length == 2) {
					
					if(arg3[0].equalsIgnoreCase("link")) {
						
						if(sender instanceof Player) {
							if(arg3[1].equalsIgnoreCase("buy-sign")) {
								
								Player player = (Player) sender;
								
								if(plugin.getToLinkSigns().containsKey(player.getName())) {
									plugin.getToLinkSigns().remove(player.getName());
								}
								
								plugin.getToLinkSigns().put(player.getName(), null);
								
								player.sendMessage(configuration.buy_sign_to_define());
								return true;
							}
						}
					}
					
					String targetName = arg3[1];
					Player target = Bukkit.getPlayer(targetName);
					
					if(target == null) {
						sender.sendMessage(configuration.player_offline(targetName));
						return false;
					}
					
					if(sender instanceof Player) {
						if(target.getInventory().firstEmpty() == -1) {
							sender.sendMessage(configuration.player_inventory_full(targetName));
							return false;
						}else {
							target.getInventory().addItem(items.hopper(1));
							sender.sendMessage(configuration.send_hoppers("1", targetName));
							target.sendMessage(configuration.receive_hoppers("1"));
							return true;
						}
					}else {
						if(target.getInventory().firstEmpty() == -1) {
							target.getWorld().dropItemNaturally(target.getLocation(), items.hopper(1));
							target.sendMessage(configuration.player_inventory_full_dropped());
							return true;
						}else {
							target.getInventory().addItem(items.hopper(1));
							sender.sendMessage(configuration.send_hoppers("1", targetName));
							target.sendMessage(configuration.receive_hoppers("1"));
							return true;
						}
					}
				}else {
					
					sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Hopper's Commands");
					sender.sendMessage("");
					sender.sendMessage(ChatColor.RED + "» /hoppers give <player> (ammount)");
					sender.sendMessage(ChatColor.RED + "» /hoppers link buy-sign");
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