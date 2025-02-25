package me.jadc.jadbreaks.tools;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Message {
	public static int debugSent = 0;
	
	public static void noPerms(CommandSender p) {
		p.sendMessage(ChatColor.RED + "You lack the permission to execute this command.");
	}
	
	public static void noPerk(CommandSender p) {
		p.sendMessage(ChatColor.RED + "This ability is just within your grasp...");
		p.sendMessage(ChatColor.RED + "You simply lack the required " + ChatColor.GOLD + "perk" + ChatColor.RED + "!");
	}
	
	public static void error(CommandSender p, String msg) {
		p.sendMessage(ChatColor.RED + "An error occured. " + msg + ".");
	}
	
	public static void invalidArgs(CommandSender p, String msg) {
		p.sendMessage(ChatColor.RED + "Invalid arguments: " + msg);
	}
	
	public static void debug(CommandSender p, String msg) {
		p.sendMessage(ChatColor.DARK_GREEN + "<DEBUG> " + ChatColor.GREEN + msg);
	}
	
	public static void d(String msg) {
		debugSent++;
		Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "<DEBUG:" + debugSent + "> " + ChatColor.GREEN + msg);
	}
	
	public static void log(String msg) {
		Bukkit.getLogger().log(Level.INFO, msg);
	}
	
	public static void bar(Player p, String msg) {
		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(msg));
	}
}
