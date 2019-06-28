package me.jadc.jadbreaks.tools;

import java.text.DecimalFormat;

import org.bukkit.ChatColor;

import net.minecraft.server.v1_14_R1.MinecraftServer;

@SuppressWarnings("deprecation")
public class TPSFetcher {
	
	/*
	 * int query
	 * 0 = last 5 min
	 * 1 = last 10 min
	 * 2 = last 15 min
	 */
	
	public static double getTPS(int query) {
		return MinecraftServer.getServer().recentTps[query];
	}
	
	public static ChatColor getTPSColor(int query) {
		double tps = MinecraftServer.getServer().recentTps[query];
		
		ChatColor color = null;
		
		if(tps >= 19.95) 				color = ChatColor.GREEN;
		if(tps >= 18.5 && tps < 19.95) 	color = ChatColor.YELLOW;
		if(tps >= 17.0 && tps < 18.5) 	color = ChatColor.RED;
		if(tps < 17.0) 					color = ChatColor.DARK_RED;
		
		return color;
	}
	
	public static String getTPSFormatted(int query) {
		return "" + getTPSColor(query) + new DecimalFormat("#0.0").format(getTPS(query)) + ChatColor.RESET;
	}
}
