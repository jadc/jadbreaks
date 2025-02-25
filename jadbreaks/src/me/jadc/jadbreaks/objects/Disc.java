package me.jadc.jadbreaks.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.jadc.jadbreaks.tools.Compare;
import me.jadc.jadbreaks.tools.Message;
import net.md_5.bungee.api.ChatColor;

public class Disc extends ItemStack implements Listener {
	
	private String itemName = ChatColor.AQUA + "Modern Music Disc";
	
	public Disc() {}
	
	public Disc(String fileName, String artist, String name) {
		super(Material.STONE_PRESSURE_PLATE);
		ItemMeta meta = getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + name + " - " + artist);
		meta.setDisplayName(itemName);
		meta.setLore(lore);
		setItemMeta(meta);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if(e.getItem() == null) return;
		if(Compare.compareName(e.getItem(), itemName)) {
			e.setCancelled(true);
			if(e.getClickedBlock().getType().equals(Material.JUKEBOX)) {
				Message.d(e.getItem().getItemMeta().getLore().get(0));
			}
		}
	}
}
