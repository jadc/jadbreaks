package me.jadc.jadbreaks.addons;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.jadc.jadbreaks.jb;
import me.jadc.jadbreaks.tools.Conf;

public class DeathSave implements Listener {
	
	HashMap<UUID, Integer> hungerMap = new HashMap<UUID, Integer>();
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		
		// Inventory Save if PvP
		if(e.getEntity().getKiller() instanceof Player) {
			e.setKeepInventory(Conf.instance().getBoolean("features.addons.deathSave.keepInvIfPvP"));
		}
		
		// Hunger Save
		if(Conf.instance().getBoolean("features.addons.deathSave.keepHunger")) {
			hungerMap.put(e.getEntity().getUniqueId(), e.getEntity().getFoodLevel());
		}
		
		// Exp Save
		if(Conf.instance().getBoolean("features.addons.deathSave.keepExp")) {
			e.setDroppedExp(0);
			e.setKeepLevel(true);
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		
		if(Conf.instance().getBoolean("features.addons.deathSave.keepHealth")) {
			Bukkit.getScheduler().runTaskLaterAsynchronously(jb.getInstance(), new Runnable() {
			    @Override
			    public void run() {
			    	e.getPlayer().setHealth(e.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue() / 5);
			    }
			}, 5);
		}
		
		if(Conf.instance().getBoolean("features.addons.deathSave.keepHunger")) {
			Bukkit.getScheduler().runTaskLaterAsynchronously(jb.getInstance(), new Runnable() {
			    @Override
			    public void run() {
			    	if(hungerMap.get(e.getPlayer().getUniqueId()) > 0) {
			    		e.getPlayer().setFoodLevel(hungerMap.get(e.getPlayer().getUniqueId()));
			    	}else {
			    		e.getPlayer().setFoodLevel(1);
			    	}
			    	e.getPlayer().setSaturation(0);
			    }
			}, 5);
		}
	}
}
