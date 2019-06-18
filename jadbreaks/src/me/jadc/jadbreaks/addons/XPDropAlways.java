package me.jadc.jadbreaks.addons;

import org.bukkit.entity.Boss;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class XPDropAlways implements Listener {
	
	@EventHandler
	public void onMobDeath(EntityDeathEvent e) {
		if(e.getEntity() instanceof Player) return;
		if(e.getEntity() instanceof Boss) return;
		if(e.getEntity() instanceof Monster) e.setDroppedExp(5);
	}
}