package me.jadc.jadbreaks.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.jadc.jadbreaks.tools.Conf;
import me.jadc.jadbreaks.tools.Message;
import net.md_5.bungee.api.ChatColor;

public class BoxOfBlocks implements CommandExecutor, Listener {

	static String blacklistPath = "features.addons.boxofblocks.blacklist";
	String loreText = ChatColor.GRAY + "Unlimited";
	
	//Infinite Place
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if(p.getInventory().getItemInMainHand() == null) return;
		if(!p.getInventory().getItemInMainHand().hasItemMeta()) return;
		if(!p.getInventory().getItemInMainHand().getItemMeta().hasLore()) return;
		
		if(p.getInventory().getItemInMainHand().getItemMeta().getLore().contains(loreText)) {
			p.getInventory().setItemInMainHand(p.getInventory().getItemInMainHand());
		}
	}
	
	//Infinite Pickup
	@EventHandler
	public void onPickup(EntityPickupItemEvent e) {
		if(!(e.getEntity() instanceof Player)) return;
		Player p = (Player) e.getEntity();
		
		ItemStack item = new ItemStack(e.getItem().getItemStack().getType(), 1);
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(loreText);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		if(p.getInventory().contains(item)) e.setCancelled(true);
	}
	
	//Delete if tossed
	@EventHandler
	public void onThrow(PlayerDropItemEvent e) {
		if(e.getItemDrop() == null) return;
		if(!e.getItemDrop().getItemStack().hasItemMeta()) return;
		if(!e.getItemDrop().getItemStack().getItemMeta().hasLore()) return;
		
		if(e.getItemDrop().getItemStack().getItemMeta().getLore().contains(loreText)) {
			e.getItemDrop().remove();
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("bob")) {
			if(!(sender instanceof Player)) {
				Message.error(sender, "Ingame players only");
				return true;
			}
			
			Player p = (Player) sender;
			if(args.length <= 0 || args[0] == null) {
				Message.invalidArgs(p, "/bob <block>");
				return true;
			}
			
			String input = String.join("_", args).toString().toUpperCase();
			
			if(Material.matchMaterial(input) != null) {
				if(!Conf.exists(blacklistPath)) {
					List<String> blacklist = new ArrayList<String>();
					blacklist.add("diamond_block");
					Conf.instance().set(blacklistPath, blacklist);
					Conf.updateDefaultConfig();
				}else {
					Conf.updateDefaultConfig();
					List<String> newBlacklist = Conf.instance().getStringList(blacklistPath);
					int increment = 0;
					for(String m : newBlacklist) {
						increment++;
						if(Material.matchMaterial(m).equals(Material.matchMaterial(input))) {
							// If blacklisted
							Message.error(p, "That item is blacklisted");
							Bukkit.broadcastMessage(ChatColor.DARK_RED + p.getDisplayName() + " tried to spawn in " + String.join(" ", args).toString().toLowerCase() + ".");
							return true;
						}
						if(increment >= newBlacklist.size()) {
							Material block = Material.getMaterial(input);
							if(!block.isBlock()) {
								Message.error(p, "Blocks only");
								return true;
							}else {
								
								ItemStack item = new ItemStack(block, 1);
								ItemMeta meta = item.getItemMeta();
								ArrayList<String> lore = new ArrayList<String>();
								lore.add(loreText);
								meta.setLore(lore);
								item.setItemMeta(meta);
								
								p.getInventory().addItem(item);
								
								Bukkit.getLogger().info(ChatColor.GREEN + p.getDisplayName() + " spawned in " + block.toString());
								p.sendMessage(ChatColor.GREEN + "You summoned unlimited " + input.toLowerCase().replaceAll("_", " ") + ".");
								return true;
							}
						}
					}
				}
			}else {
				Message.error(p, "Not a real block");
				Bukkit.broadcastMessage(ChatColor.DARK_GRAY + p.getDisplayName() + " tried to spawn in " + String.join(" ", args).toString().toLowerCase() + ".");
			}
		}
		
		return true;
	}

}
