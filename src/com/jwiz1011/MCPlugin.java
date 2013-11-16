package com.jwiz1011;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCPlugin extends JavaPlugin {	
    public void onEnable(){
		this.getServer().getPluginManager().registerEvents(new EventListener(), this);
        getLogger().info("MCPlugin is working properly.");
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	if(cmd.getName().equalsIgnoreCase("mcplugintest")){
    		sender.sendMessage("MCPlugin is working properly.");
    		return true;
    	}
    	if(cmd.getName().equalsIgnoreCase("regionselect")){
    		if (sender instanceof Player) {
    			Player player = (Player) sender;
    			EventListener.selectorOn = 1;
    			player.sendMessage("Region selection mode enabled. Use /rscancel to exit.");
    			player.sendMessage("Left click for 1st position.");
    			return true;
    	    } else {
    	    	sender.sendMessage("You must be a player to use that command.");
    	    	return false;
    	    }
    	}
    	if(cmd.getName().equalsIgnoreCase("rscancel")){
    		sender.sendMessage("Region selection mode canceled.");
    		EventListener.selectorOn = 0;
    		return true;
    	}
    	if(cmd.getName().equalsIgnoreCase("rsaccept")){
    		sender.sendMessage("Selected region confirmed.");
    		EventListener.fX1 = EventListener.bX1;
    		EventListener.fZ1 = EventListener.bZ1;
    		EventListener.fX2 = EventListener.bX2;
    		EventListener.fZ2 = EventListener.bZ2;
    		EventListener.selectorOn = 0;
    		return true;
    	}
    	return false;
    }
}
