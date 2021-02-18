package fr.xxathyx.chunkhoppers.listeners;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.xxathyx.chunkhoppers.Main;
import fr.xxathyx.chunkhoppers.configuration.Configuration;
import fr.xxathyx.chunkhoppers.items.ItemStacks;
import net.milkbowl.vault.economy.EconomyResponse;

public class ClickSign implements Listener {
	
	private final Main plugin = Main.getPlugin(Main.class);
	
	private final Configuration configuration = new Configuration();
	private final ItemStacks items = new ItemStacks();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onSignClick(PlayerInteractEvent event) {
		
		if(event.getClickedBlock() != null) {
	        if(event.getClickedBlock().getType() == Material.SIGN || event.getClickedBlock().getType() == Material.SIGN_POST || event.getClickedBlock().getType() == Material.WALL_SIGN) {
	        	
	            if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
	            	
	                Sign sign = (Sign) event.getClickedBlock().getState();
	                
	                if(sign.getLine(0).equals(configuration.getFirstBuySignLine()) && sign.getLine(1).equals(configuration.getSecondBuySignLine()) &&
	                		sign.getLine(2).equals(configuration.getThirdBuySignLine()) && sign.getLine(3).equals(configuration.getFourthBuySignLine())) {
	                	
	                	Player player = event.getPlayer();
	                	
						EconomyResponse resp = plugin.getEconomy().withdrawPlayer(player.getName(), configuration.getBuySignPrice());
						
						if(!resp.transactionSuccess()) {
							player.sendMessage(configuration.insufficient_money());
							return;
						}
	                	
						if(player.getInventory().firstEmpty() == -1) {
							player.getWorld().dropItemNaturally(player.getLocation(), items.hopper(configuration.getBuySignQuantity()));
							player.sendMessage(configuration.player_inventory_full_dropped());
						}else {
							player.getInventory().addItem(items.hopper(configuration.getBuySignQuantity()));
							player.sendMessage(configuration.receive_hoppers(String.valueOf(configuration.getBuySignQuantity())));
							
							player.updateInventory();
						}
	                	
	                    sign.update();
	                    return;
	                }
	                
	                if(plugin.getToLinkSigns().containsKey(event.getPlayer().getName())) {
	                	
	                	if(sign != null) {
	                		
	                		sign.setLine(0, configuration.getFirstBuySignLine());
	                		sign.setLine(1, configuration.getSecondBuySignLine());
	                		sign.setLine(2, configuration.getThirdBuySignLine());
	                		sign.setLine(3, configuration.getFourthBuySignLine());
	                		
	                		sign.update();
	                		
	                		plugin.getToLinkSigns().remove(event.getPlayer().getName());
	                		event.getPlayer().sendMessage(configuration.buy_sign_defined());
	                		return;
	                	}
	                }
	            }
	        }
		}
	}
}