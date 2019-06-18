package me.jadc.jadbreaks.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import me.jadc.jadbreaks.tools.Message;

public class Raw implements CommandExecutor, Listener {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("raw")){
			
			if(args.length <= 0) {
				Message.invalidArgs(sender, "/raw <message>");
				return true;
			}
			
			if(!sender.isOp()) {
				Message.noPerms(sender);
				return true;
			}
			
			String msg = "";
			
			for(String sec : args) {
				msg += sec + " ";
			}
			
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', msg));
		}
		
		return true;
	}
}
