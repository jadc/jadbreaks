package me.jadc.jadbreaks.cmd;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.jadc.jadbreaks.jb;
import me.jadc.jadbreaks.tools.Message;
import net.md_5.bungee.api.ChatColor;

public class Reload implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender.isOp()) {
			
			Instant before = Instant.now();
			
			jb.instance.reloadConfig();
			Bukkit.reload();
			
			Instant after = Instant.now();
			
			double lag = Duration.between(before, after).toMillis();
			
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "Server refreshed; lag lasted " + new DecimalFormat("0.0s").format(lag/1000) + " (" + new DecimalFormat("###ms").format(lag) + ")");
			
		}else {
			Message.noPerms(sender);
		}
		
		return true;
	}
	
}
