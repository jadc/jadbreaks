package me.jadc.jadbreaks.addons;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.md_5.bungee.api.ChatColor;

public class ChatPing implements Listener {
	
	public void ping(Player p) {
		p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 0.5F, 2.0F);
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		String[] chunks = e.getMessage().split(" ");
		for(int i = 0; i < chunks.length; i++) {
			
			if(chunks[i].startsWith("@")) {
				String chunk = chunks[i].substring(1);
				
				if(Bukkit.getPlayer(chunk) != null) {
					// Single user ping
					Player t = Bukkit.getPlayer(chunk);
					chunks[i] = ChatColor.BLUE + "@" + t.getDisplayName() + ChatColor.RESET;
					ping(t);
				}else {
					// Everyone ping
					if(chunk.equalsIgnoreCase("everyone")) {
						for(Player a : Bukkit.getOnlinePlayers()) {
							chunks[i] = ChatColor.BLUE + "@everyone";
							ping(a);
						}
					}
				}
			}
		}
		
		e.setMessage(StringUtils.join(chunks, ' '));
	}
}
