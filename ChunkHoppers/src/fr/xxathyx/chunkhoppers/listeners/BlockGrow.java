package fr.xxathyx.chunkhoppers.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Hopper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.CocoaPlant;
import org.bukkit.material.CocoaPlant.CocoaPlantSize;
import org.bukkit.material.Crops;

import fr.xxathyx.chunkhoppers.Main;
import fr.xxathyx.chunkhoppers.configuration.Configuration;
import fr.xxathyx.chunkhoppers.util.CropsUtils;

public class BlockGrow implements Listener {
	
	private final Main plugin = Main.getPlugin(Main.class);
	
	private final Configuration configuration = new Configuration();
	
	@SuppressWarnings("deprecation")
	@EventHandler
    public void onBlockGrow(BlockGrowEvent event) {
		
		if(configuration.hoppers_catch_growing_plantations()) {
			
			if(event.getNewState().getType().equals(Material.LONG_GRASS) || (event.getNewState().getType().equals(Material.GRASS) ||
					(event.getNewState().getType().equals(Material.YELLOW_FLOWER)))) {
				return;
			}
			
        	Location location = event.getNewState().getBlock().getLocation();
        	
        	int block_id = event.getNewState().getType().getId();
			Byte block_data = event.getNewState().getData().getData();
			
			if(event.getNewState().getType().equals(Material.CACTUS) || event.getNewState().getType().equals(Material.SUGAR_CANE_BLOCK) ||
					event.getNewState().getType().equals(Material.MELON_BLOCK) || event.getNewState().getType().equals(Material.PUMPKIN)) {
				event.setCancelled(true);
			}
								
	        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
	        	
	    		Hopper hopper = null;
	    		
				int state = event.getNewState().getData().getData();
	    		
	            Chunk chunk = event.getNewState().getChunk();
	            
	            ItemStack item = new ItemStack(Material.AIR, 1);
	            
	            if(event.getNewState().getType().equals(Material.CACTUS) || event.getNewState().getType().equals(Material.SUGAR_CANE_BLOCK) ||
	            		event.getNewState().getType().equals(Material.PUMPKIN_STEM) || event.getNewState().getType().equals(Material.MELON_STEM) || event.getNewState().getType().equals(Material.COCOA)) {
		            if(Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].contains("v1_8")) {
		            	
			            item = CropsUtils.fromCrops(new Crops(event.getNewState().getType(), (byte) state));
		            	
		            }else {
		            			            	
		            	if(event.getNewState().getType().equals(Material.CACTUS)) {
		            		item = new ItemStack(Material.CACTUS, (Math.random() <= 0.5) ? configuration.getCactusMinimum() : configuration.getCactusMaximum());
		        		}
		        		
		        		if(event.getNewState().getType().equals(Material.SUGAR_CANE_BLOCK)) {
		        			item = new ItemStack(Material.SUGAR_CANE, (Math.random() <= 0.5) ? configuration.getSugarCaneMinimum() : configuration.getSugarCaneMaximum());
		        		}
		        		
		        		if(event.getNewState().getType().equals(Material.MELON_STEM)) {
		        						
		        			if(state == 7) {
		        				item = new ItemStack(Material.MELON, (Math.random() <= 0.5) ? configuration.getMelonMinimum() : configuration.getMelonMaximum());
		        			}
		        		}
		        		
		        		if(event.getNewState().getType().equals(Material.PUMPKIN_STEM)) {
		        						
		        			if(state == 7) {
		        				item = new ItemStack(Material.PUMPKIN, (Math.random() <= 0.5) ? configuration.getPumkinMinimum() : configuration.getPumkinMaximum());
		        			}
		        		}
		        		
		        		if(event.getNewState().getType().equals(Material.COCOA)) {
		        			
		        			if(state == 0) {
		        				state = 11;
		        			}
		        			
		        			if(state == 8) {
		        				item = new ItemStack(351, (Math.random() <= 0.5) ? configuration.getCocoaMinimum() : configuration.getCocoaMaximum(), (short) 3);
		        			}
		        			
		        			if(state == 9) {
		        				item = new ItemStack(351, (Math.random() <= 0.5) ? configuration.getCocoaMinimum() : configuration.getCocoaMaximum(), (short) 3);
		        			}
		        			
		        			if(state == 10) {
		        				item = new ItemStack(351, (Math.random() <= 0.5) ? configuration.getCocoaMinimum() : configuration.getCocoaMaximum(), (short) 3);
		        			}
		        			
		        			if(state == 11) {
		        				item = new ItemStack(351, (Math.random() <= 0.5) ? configuration.getCocoaMinimum() : configuration.getCocoaMaximum(), (short) 3);
		        			}
		        		}
		            }
	            }else {
		            item = CropsUtils.fromCrops(new Crops(event.getNewState().getType(), (byte) state));
	            }
	            	                
	            for (int i = 0; i < chunk.getTileEntities().length; i++) {        	
	                if (chunk.getTileEntities()[i] instanceof Hopper) {
	                    hopper = (Hopper) chunk.getTileEntities()[i];
	                }
	            }
	    		
	            if(hopper != null) {
	            	
	            	if(state == 7 || state == 3 || state == 8 || state == 9 || state == 10 || state == 11) {
	            		
	            		if(state == 3) {
	            			if(event.getNewState().getType().equals(Material.NETHER_WARTS) || event.getNewState().getType().equals(Material.COCOA)) {
		            			if(event.getNewState().getType().equals(Material.NETHER_WARTS)) {
		            				event.getNewState().getBlock().setType(Material.NETHER_WARTS);
		            			}
	            			}
	            		}
	            		
	            		if(state == 7) {
	            			if(event.getNewState().getType().equals(Material.POTATO)) {
	            				event.getNewState().getBlock().setType(Material.POTATO);
	            			}
	            			
	            			if(event.getNewState().getType().equals(Material.CARROT)) {
	            				event.getNewState().getBlock().setType(Material.CARROT);
	            			}
	            			
	            			if(event.getNewState().getType().equals(Material.MELON_STEM)) {
	            				event.getNewState().getBlock().setType(Material.MELON_STEM);
	            			}
	            			
	            			if(event.getNewState().getType().equals(Material.PUMPKIN_STEM)) {
	            				event.getNewState().getBlock().setType(Material.PUMPKIN_STEM);
	            			}
	            			
	            			if(event.getNewState().getType().equals(Material.CROPS)) {
	            				event.getNewState().getBlock().setType(Material.CROPS);
	            			}
	            		}
	            		
	            		if(state == 11 || state == 10 || state == 9 || state == 8) {
	            			if(event.getNewState().getType().equals(Material.COCOA)) {
	            				
	            				Block block = event.getBlock();
	            				BlockFace face = ((CocoaPlant) block.getState().getData()).getFacing();
	            				
	                            block.getLocation().getWorld().getBlockAt(event.getBlock().getLocation()).setType(Material.COCOA);
	                            	                            
	                            BlockState block_state = block.getState();
	                            
	                            block_state.setData(new CocoaPlant(CocoaPlantSize.SMALL, face));
	                            block_state.update();
	            			}
	            		}
	            	}
	            	
	                if (hopper.getInventory().getName().equals(configuration.hoppers_item_name())) {
	                	if(!(hopper.getInventory().firstEmpty() == -1)) {
	        				hopper.getInventory().addItem(item);
	                	}
	                }
	            }else {
	            	plugin.getToReplaceLocations().add(location);
	            	plugin.getToReplaceBlocksIDs().add(block_id);
	            	plugin.getToReplaceBlocksData().add(block_data);
	            }
	        }, 1L);
		}
    }
}