package me.jadc.jadbreaks.addons;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.jadc.jadbreaks.tools.Conf;
import me.jadc.jadbreaks.tools.Message;
import net.md_5.bungee.api.ChatColor;

public class DeathLocationAid implements Listener, CommandExecutor {
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		Location loc = e.getEntity().getLocation();
		
		Conf.playerData.getConfig().set(p.getUniqueId() + ".lastDeath", loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ());
		Conf.playerData.save();
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		tellDeathLocation(e.getPlayer());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("died")){
			if(sender instanceof Player) {
				tellDeathLocation((Player) sender);
			}else {
				Message.error(sender, "Players only");
			}
		}
		
		return true;
	}
	
	private void tellDeathLocation(Player p) {
		String loc = Conf.playerData.getConfig().getString(p.getUniqueId() + ".lastDeath");
		if(loc != null) {
			p.sendMessage(ChatColor.GOLD + "Your last death was at " + loc);
		}else {
			p.sendMessage(ChatColor.GOLD + "You do not have a death location saved.");
		}
	}
}
