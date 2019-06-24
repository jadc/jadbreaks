package me.jadc.jadbreaks.objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import me.jadc.jadbreaks.jb;
import net.md_5.bungee.api.ChatColor;

public class CustomArrow {
	
	String name;
	int amount;
	
	public CustomArrow(String name, int amount, Material i1) {
		this.name = name;
		this.amount = amount;
		ShapelessRecipe r = new ShapelessRecipe(new NamespacedKey(jb.getInstance(), name.toLowerCase().replaceAll(" ", "_")), getArrow());
		r.addIngredient(Material.ARROW);
		r.addIngredient(i1);
		Bukkit.addRecipe(r);
	}
	
	public CustomArrow(String name, int amount, Material i1, Material i2) {
		this.name = name;
		this.amount = amount;
		ShapelessRecipe r = new ShapelessRecipe(new NamespacedKey(jb.getInstance(), name.toLowerCase().replaceAll(" ", "_")), getArrow());
		r.addIngredient(Material.ARROW);
		r.addIngredient(i1);
		r.addIngredient(i2);
		Bukkit.addRecipe(r);
	}
	
	public CustomArrow(String name, int amount, Material i1, Material i2, Material i3) {
		this.name = name;
		this.amount = amount;
		ShapelessRecipe r = new ShapelessRecipe(new NamespacedKey(jb.getInstance(), name.toLowerCase().replaceAll(" ", "_")), getArrow());
		r.addIngredient(Material.ARROW);
		r.addIngredient(i1);
		r.addIngredient(i2);
		r.addIngredient(i3);
		Bukkit.addRecipe(r);
	}
	
	public CustomArrow(String name, int amount, Material i1, Material i2, Material i3, Material i4) {
		this.name = name;
		this.amount = amount;
		ShapelessRecipe r = new ShapelessRecipe(new NamespacedKey(jb.getInstance(), name.toLowerCase().replaceAll(" ", "_")), getArrow());
		r.addIngredient(Material.ARROW);
		r.addIngredient(i1);
		r.addIngredient(i2);
		r.addIngredient(i3);
		r.addIngredient(i4);
		Bukkit.addRecipe(r);
	}
	
	public CustomArrow(String name, int amount, Material i1, Material i2, Material i3, Material i4, Material i5) {
		this.name = name;
		this.amount = amount;
		ShapelessRecipe r = new ShapelessRecipe(new NamespacedKey(jb.getInstance(), name.toLowerCase().replaceAll(" ", "_")), getArrow());
		r.addIngredient(Material.ARROW);
		r.addIngredient(i1);
		r.addIngredient(i2);
		r.addIngredient(i3);
		r.addIngredient(i4);
		r.addIngredient(i5);
		Bukkit.addRecipe(r);
	}
	
	public CustomArrow(String name, int amount, Material i1, Material i2, Material i3, Material i4, Material i5, Material i6) {
		this.name = name;
		this.amount = amount;
		ShapelessRecipe r = new ShapelessRecipe(new NamespacedKey(jb.getInstance(), name.toLowerCase().replaceAll(" ", "_")), getArrow());
		r.addIngredient(Material.ARROW);
		r.addIngredient(i1);
		r.addIngredient(i2);
		r.addIngredient(i3);
		r.addIngredient(i4);
		r.addIngredient(i5);
		r.addIngredient(i6);
		Bukkit.addRecipe(r);
	}
	
	public CustomArrow(String name, int amount, Material i1, Material i2, Material i3, Material i4, Material i5, Material i6, Material i7) {
		this.name = name;
		this.amount = amount;
		ShapelessRecipe r = new ShapelessRecipe(new NamespacedKey(jb.getInstance(), name.toLowerCase().replaceAll(" ", "_")), getArrow());
		r.addIngredient(Material.ARROW);
		r.addIngredient(i1);
		r.addIngredient(i2);
		r.addIngredient(i3);
		r.addIngredient(i4);
		r.addIngredient(i5);
		r.addIngredient(i6);
		r.addIngredient(i7);
		Bukkit.addRecipe(r);
	}
	
	public CustomArrow(String name, int amount, Material i1, Material i2, Material i3, Material i4, Material i5, Material i6, Material i7, Material i8) {
		this.name = name;
		this.amount = amount;
		ShapelessRecipe r = new ShapelessRecipe(new NamespacedKey(jb.getInstance(), name.toLowerCase().replaceAll(" ", "_")), getArrow());
		r.addIngredient(Material.ARROW);
		r.addIngredient(i1);
		r.addIngredient(i2);
		r.addIngredient(i3);
		r.addIngredient(i4);
		r.addIngredient(i5);
		r.addIngredient(i6);
		r.addIngredient(i7);
		r.addIngredient(i8);
		Bukkit.addRecipe(r);
	}
	
	public ItemStack getArrow() {
		ItemStack arrow = new ItemStack(Material.ARROW);
		arrow.setAmount(amount);
		ItemMeta meta = arrow.getItemMeta();
		meta.setDisplayName(ChatColor.RESET + name);
		meta.addEnchant(Enchantment.DURABILITY, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		arrow.setItemMeta(meta);
		return arrow;
	}
	
	public String getName() {
		return name;
	}
}
