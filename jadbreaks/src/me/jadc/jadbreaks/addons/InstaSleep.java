package me.jadc.jadbreaks.addons;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import me.jadc.jadbreaks.jb;
import me.jadc.jadbreaks.tools.Message;

public class InstaSleep implements Listener {
	
	int sleepTask;
	
	@EventHandler
	public void onSleep(PlayerBedEnterEvent e) {
		e.setCancelled(true);
		
		Player p = e.getPlayer();
		World w = e.getPlayer().getWorld();
		
		p.setBedSpawnLocation(p.getLocation(), true);
		Message.bar(e.getPlayer(), "Spawn set to " + p.getBedSpawnLocation().getBlockX() + ", " + p.getBedSpawnLocation().getBlockY() + ", " + p.getBedSpawnLocation().getBlockZ());
		
		// Clear weather
		w.setStorm(false);
		w.setThundering(false);
		
		// Change time of day if not day
		if(w.getTime() > 12000) {
			// Smooth day transition
			if(sleepTask == 0) {
				// Announcement
				Bukkit.broadcastMessage(e.getPlayer().getDisplayName() + " went to sleep");
				sleepTask = jb.instance.getServer().getScheduler().runTaskTimerAsynchronously(jb.instance, new Runnable(){
					
					@Override
					public void run() {
						if(w.getTime() > 12000) { 
							w.setTime(w.getTime() + 20);
						}else {
							Bukkit.getServer().getScheduler().cancelTask(sleepTask);
							sleepTask = 0;
						}
					}
					
				}, 0, 1L).getTaskId();
			}
		}
	}
}
