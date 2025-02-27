package me.jadc.jadbreaks.items;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.jadc.jadbreaks.jb;
import me.jadc.jadbreaks.tools.Compare;
import net.md_5.bungee.api.ChatColor;

public class Boof extends ItemStack implements Listener {
	
	public Boof() { 
		super(Material.STICK);
		ItemMeta meta = getItemMeta();
		meta.setDisplayName(ChatColor.RESET + "The Boof");
		setItemMeta(meta);
	}
	
	public static void registerRecipe() {
		ShapedRecipe boofRecipe = new ShapedRecipe(new NamespacedKey(jb.getInstance(), "The_Boof"), new Boof());
		boofRecipe.shape(" pg", " f ", " s ");
		boofRecipe.setIngredient('p', Material.PAPER);
		boofRecipe.setIngredient('g', Material.GRASS);
		boofRecipe.setIngredient('f', Material.FLINT);
		boofRecipe.setIngredient('s', Material.STICK);
		jb.getInstance().getServer().addRecipe(boofRecipe);
	}

	Random r = new Random();
	@EventHandler
	public void onUse(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			if(Compare.compareName(e.getItem(), getItemMeta().getDisplayName())) {
				if(p.getEyeLocation().getBlock().getType() == Material.WATER) return;
				
				Location location = p.getEyeLocation();
				Vector direction = location.getDirection();
				
				p.getWorld().spawnParticle(Particle.CLOUD, location.getX(), location.getY(), 
						location.getZ(), 0, (float) direction.getX(), 0.5, (float) direction.getZ(), 0.2D, null);
				
				p.getWorld().playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 0.1F, 0.5F);
				
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 3*20, 1));
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 5*20, 1));
			}
		}
	}
}
