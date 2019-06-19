package me.jadc.jadbreaks.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.jadc.jadbreaks.jb;
import me.jadc.jadbreaks.objects.Perk;
import net.md_5.bungee.api.ChatColor;

public class Info implements CommandExecutor, Listener {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("jb")){
			sender.sendMessage(ChatColor.GREEN + "You are using jadbreaks " + jb.getInstance().getDescription().getVersion() + " for Minecraft " + jb.getInstance().getDescription().getAPIVersion());
			Player p = (Player) sender;
			if(p.isOp()) {
				p.sendMessage(ChatColor.GREEN + "Executing debug functions...");
				
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				p.getInventory().addItem(new Perk("Warpsser", "teleportssation"));
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			}
		}
		
		return true;
	}
}
