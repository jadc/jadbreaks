package me.jadc.jadbreaks.cmd;

import java.util.Date;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.jadc.jadbreaks.jb;
import me.jadc.jadbreaks.items.Ashes;
import me.jadc.jadbreaks.tools.Effects;
import me.jadc.jadbreaks.tools.Message;
import net.md_5.bungee.api.ChatColor;

public class TempBan implements CommandExecutor, Listener {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("tempban")){
			
			if(!sender.hasPermission("jadbreaks.mod")) {
				Message.noPerms(sender);
				return true;
			}
			
			if(args.length < 2 || args[0] == null) {
				Message.invalidArgs(sender, "/tempban <user> <minutes> [reason]");
				return true;
			}
			
			Player victim = jb.getInstance().getServer().getPlayer(args[0]);
			int minutes = 0;
			String reason = "You are banned.";
			if(args.length > 2) {
				reason = "";
				for(int i = 2; i < args.length; i++) {
					reason += args[i] + " ";
				}
			}
			
			if(victim == null) {
				Message.error(sender, "Not a real player or player is offline");
				return true;
			}
			
			try{
			    minutes = Integer.parseInt(args[1]);
			}catch (NumberFormatException ex) {
			    Message.invalidArgs(sender, "Integers for minutes only.");
			    return true;
			}
			
			victim.getWorld().strikeLightningEffect(victim.getLocation());
			Effects.emitBlockBreak(victim.getLocation(), Material.COAL_BLOCK);
			Effects.emitBlockBreak(victim.getLocation().add(0, 1, 0), Material.COAL_BLOCK);
			
			//ashes
			if(minutes > 0) victim.getWorld().dropItemNaturally(victim.getLocation(), new Ashes(victim.getDisplayName(), minutes, reason));
			
			Date duration = new Date(System.currentTimeMillis() + minutes * 60 * 1000);
			Bukkit.getBanList(BanList.Type.NAME).addBan(victim.getDisplayName(), ChatColor.RED + reason + ChatColor.GREEN + "(for " + minutes + " minutes)", duration, null);
			victim.kickPlayer(reason);
			
			Bukkit.broadcastMessage(ChatColor.GOLD + victim.getDisplayName() + " has been banned for " + minutes + " minutes.");
			if(reason != "You are banned.") {
				Bukkit.broadcastMessage(ChatColor.YELLOW + "Reason: " + reason);
			}
			
			return true;
		}
		
		return true;
	}

}
