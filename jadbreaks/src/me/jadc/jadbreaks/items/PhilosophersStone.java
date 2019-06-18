package me.jadc.jadbreaks.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import me.jadc.jadbreaks.jb;
import me.jadc.jadbreaks.tools.Effects;
import net.md_5.bungee.api.ChatColor;

public class PhilosophersStone extends ItemStack implements Listener {
	
	String name = ChatColor.RESET + "Philosopher's Stone";
	private List<String> lore = new ArrayList<String>();
	
	public PhilosophersStone() {
		super(Material.RED_DYE);
		
		ItemMeta meta = this.getItemMeta();
		
		meta.setDisplayName(name + ChatColor.YELLOW + " [1]");
		lore.add(ChatColor.GRAY + "RCLICK BLOCK: Transform Block");
		lore.add(ChatColor.GRAY + "RCLICK AIR: Workbench");
		lore.add(ChatColor.GRAY + "Q: Increase radius");
		lore.add(ChatColor.GRAY + "SHIFT Q: Decrease radius");
		meta.setLore(lore);
		
		this.setItemMeta(meta);
	}
	
	public static void registerRecipe() {
		ShapedRecipe pStoneRecipe = new ShapedRecipe(new NamespacedKey(jb.instance, "Philosophers_Stone"), new PhilosophersStone());
		pStoneRecipe.shape("GRG", "RDR", "GRG");
		pStoneRecipe.setIngredient('G', Material.GLOWSTONE_DUST);
		pStoneRecipe.setIngredient('R', Material.REDSTONE);
		pStoneRecipe.setIngredient('D', Material.DIAMOND);
		Bukkit.addRecipe(pStoneRecipe);
	}
	
	public int getCharge(ItemStack i) {
		return Integer.parseInt(i.getItemMeta().getDisplayName().charAt(25) + "");
	}
	
	public void setCharge(ItemStack i, int charge) {
		if(charge <= 1) charge = 1;
		if(charge >= 5) charge = 5;
		
		ItemMeta meta = i.getItemMeta();	
		meta.setDisplayName(name + ChatColor.YELLOW + " [" + charge + "]");
		i.setItemMeta(meta);
	}
	
	public boolean isPStone(ItemStack i) {
		if(i == null) return false;
		if(!i.hasItemMeta()) return false;
		if(i.getItemMeta() == null) return false;
		if(!i.getItemMeta().hasLore()) return false;
		if(i.getItemMeta().getLore() == null) return false;
		return i.getItemMeta().getLore().equals(lore);
	}
	
	// thanks forums
	public List<Block> getBlocks(Block start, int radius){
	    if (radius < 0) {
	        return new ArrayList<Block>(0);
	    }
	    int iterations = (radius * 2) + 1;
	    List<Block> blocks = new ArrayList<Block>(iterations * iterations * iterations);
	    for (int x = -radius; x <= radius; x++) {
	        for (int y = -radius; y <= radius; y++) {
	            for (int z = -radius; z <= radius; z++) {
	                blocks.add(start.getRelative(x, y, z));
	            }
	        }
	    }
	    return blocks;
	}
		 
	
	// Events
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
		if(isPStone(i)) {
			// Workbench
			if(e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
				e.getPlayer().openWorkbench(null, true);
				Effects.playSound(e.getPlayer().getLocation(), Sound.BLOCK_CHEST_OPEN, 0.5F, 1.0F);
				return;
			}
			
			// Transform
			if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if(e.getHand().equals(EquipmentSlot.HAND)) {
					for(Block b : getBlocks(e.getClickedBlock(), getCharge(i) - 1)) {
						Material m = b.getType();
						Material newM = Material.RED_CONCRETE;
						
						if(e.getPlayer().isSneaking()) {
							if(m.equals(Material.STONE)) newM = Material.GRASS_BLOCK;
							
							if(m.equals(Material.COBBLESTONE)) newM = Material.GRASS_BLOCK;
							if(m.equals(Material.GRASS_BLOCK) || m.equals(Material.DIRT)) newM = Material.COBBLESTONE;
							
							if(m.equals(Material.SAND)) newM = Material.COBBLESTONE;
						}else {
							if(m.equals(Material.STONE)) newM = Material.COBBLESTONE;
							if(m.equals(Material.COBBLESTONE)) newM = Material.STONE;
							
							if(m.equals(Material.DIRT) || m.equals(Material.GRASS_BLOCK)) newM = Material.SAND;
							if(m.equals(Material.SAND)) newM = Material.GRASS_BLOCK;
							
							if(m.equals(Material.GRAVEL)) newM = Material.SANDSTONE;
							if(m.equals(Material.SANDSTONE)) newM = Material.GRAVEL;
							
							if(m.equals(Material.NETHERRACK)) newM = Material.COBBLESTONE;
							
							if(m.equals(Material.MELON)) newM = Material.PUMPKIN;
							if(m.equals(Material.PUMPKIN)) newM = Material.MELON;
						}
						
						if(!newM.equals(Material.RED_CONCRETE)) {
							b.setType(newM);
							Effects.emitParticle(b.getLocation(), Particle.SPELL, 5, 0, 0, 0);
						}
					}
					Effects.playSound(e.getClickedBlock().getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 0.5F, 2.0F);
					Effects.playSound(e.getClickedBlock().getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0F, 2.0F);
				}
			}
		}
	}
	
	// Charging
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		ItemStack i = e.getItemDrop().getItemStack();
		if(isPStone(i)) {
			e.setCancelled(true);
			if(e.getPlayer().isSneaking()) {
				setCharge(i, getCharge(i) - 1);
			}else {
				setCharge(i, getCharge(i) + 1);
			}
			Effects.playSound(e.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, (float)(getCharge(i) * 0.4));
		}
	}
}
