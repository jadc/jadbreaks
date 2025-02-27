package me.jadc.jadbreaks.addons;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.jadc.jadbreaks.tools.Conf;

public class Log implements Listener {
	
	public static void logToFile(String l) {
		List<String> log;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		if(Conf.log.getConfig().getList("log") != null) {
			log = Conf.log.getConfig().getStringList("log");
		}else {
			log = new ArrayList<String>();
		}
		
		log.add(date.format(cal.getTime()) + " --> " + l);
		
		Conf.log.getConfig().set("log", log);
		Conf.log.save();
	}
	
	// LOGGING EVENTS
	@EventHandler
	public void onMineDiamond(BlockBreakEvent e) {
		if(e.getBlock().getType().equals(Material.DIAMOND_ORE)) {
			logToFile(e.getPlayer().getDisplayName() + " mined diamond at " + e.getBlock().getX()+ ", " + e.getBlock().getY() + ", " + e.getBlock().getZ());
		}
	}
}
