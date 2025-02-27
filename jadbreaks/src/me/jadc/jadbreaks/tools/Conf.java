package me.jadc.jadbreaks.tools;

import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.jadc.jadbreaks.jb;
import me.jadc.jadbreaks.objects.ConfigFile;

public class Conf implements Listener {
	
	public static ConfigFile playerData;
	public static ConfigFile warpData;
	public static ConfigFile log;
	
	public static void initializeConfigurationFiles() {
		jb.getInstance().saveDefaultConfig();
		playerData = new ConfigFile("players");
		warpData = new ConfigFile("warps");
		log = new ConfigFile("log");
	}
	
	// setup player data defaults
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		String uuid = e.getPlayer().getUniqueId().toString();
		
		// Perks
		if(!playerData.getConfig().contains(uuid + ".perks")) playerData.getConfig().set(uuid + ".perks", new ArrayList<String>());
		
		// Health
		if(!playerData.getConfig().contains(uuid + ".health")) playerData.getConfig().set(uuid + ".health", 20.0);
		
		playerData.save();
	}
	
	public static void updateDefaultConfig() {
		jb.getInstance().getConfig().options().copyDefaults(true);
		jb.getInstance().saveConfig();
	}
	
	public static boolean exists(String key) {
		return jb.getInstance().getConfig().contains(key);
	}
	
	public static FileConfiguration instance() {
		return jb.getInstance().getConfig();
	}
	
	
}
