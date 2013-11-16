package com.jwiz1011;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class EventListener implements Listener {
	public static int bX1 = 0;
	public static int bZ1 = 0;
	public static int bX2 = 0;
	public static int bZ2 = 0;
	public static int fX1 = 0;
	public static int fZ1 = 0;
	public static int fX2 = 0;
	public static int fZ2 = 0;
	public static int selectorOn = 0;
	private int inRegion = 0;
	
	@EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
    	Location loc = event.getPlayer().getLocation();
    	if(loc.getBlockX() >= fX1 && loc.getBlockX() <= fX2 && loc.getBlockZ() >= fZ1 && loc.getBlockZ() <= fZ2 && inRegion == 0){
    		event.getPlayer().sendMessage("Entering region");
    		event.getPlayer().setGameMode(GameMode.CREATIVE);
    		inRegion = 1;
    	}else if((loc.getBlockX() < fX1 || loc.getBlockX() > fX2 || loc.getBlockZ() < fZ1 || loc.getBlockZ() > fZ2) && inRegion == 1){
    		event.getPlayer().sendMessage("Leaving region");
    		event.getPlayer().setGameMode(GameMode.ADVENTURE);
    		inRegion = 0;
    	}
    }
	
	@EventHandler
	public void onInteractEvent(PlayerInteractEvent event){
    	event.getPlayer().sendMessage("Clicked");
    	if(selectorOn == 1){
    		if(event.getAction() == Action.LEFT_CLICK_BLOCK){
    			bX1 = event.getClickedBlock().getX();
    			bZ1 = event.getClickedBlock().getZ();
    			event.getPlayer().sendMessage("1st position selected.");
    			event.getPlayer().sendMessage("Right click for 2nd position.");
    		}
    		if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
    			if(event.getClickedBlock().getX() > bX1){
    				bX2 = event.getClickedBlock().getX();
    			}else{
    				bX2 = bX1;
    				bX1 = event.getClickedBlock().getX();
    			}
    			if(event.getClickedBlock().getZ() > bZ1){
    				bZ2 = event.getClickedBlock().getZ();
    			}else{
    				bZ2 = bZ1;
    				bZ1 = event.getClickedBlock().getZ();
    			}
    			event.getPlayer().sendMessage("2nd position selected");
    			event.getPlayer().sendMessage("Use /rsaccept to confirm.");
    		}
    	}
    }
	
}
