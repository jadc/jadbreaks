package me.jadc.jadbreaks.addons;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.jadc.jadbreaks.tools.Conf;
import me.jadc.jadbreaks.tools.Message;
import net.md_5.bungee.api.ChatColor;

public class MaintenanceMode implements Listener, CommandExecutor {
	
	public static final String maintenanceMessage = ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "Temporarily offline for maintenance.";
	
	@EventHandler
	public void onJoin(AsyncPlayerPreLoginEvent e) {
		if(Conf.instance().getBoolean("maintenance")) {
			if(!Bukkit.getOfflinePlayer(e.getUniqueId()).isOp()) {
				e.disallow(Result.KICK_OTHER, maintenanceMessage);
			}
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("maintenance")){
			if(!sender.isOp()) {
				Message.noPerms(sender);
			}else {
				Conf.instance().set("maintenance", !Conf.instance().getBoolean("maintenance"));
				Conf.updateDefaultConfig();
				sender.sendMessage(ChatColor.BOLD + "Maintenance mode is now " + ChatColor.YELLOW + Conf.instance().getBoolean("maintenance"));
			}
			
			if(Conf.instance().getBoolean("maintenance")) {
				for(Player a : Bukkit.getOnlinePlayers()) {
					if(!a.isOp()) {
						a.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10*20, 255));
						a.kickPlayer(maintenanceMessage);
					}
				}
			}
		}
		
		return true;
	}
}
