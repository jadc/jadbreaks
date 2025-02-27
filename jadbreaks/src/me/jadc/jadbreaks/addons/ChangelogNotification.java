package me.jadc.jadbreaks.addons;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.jadc.jadbreaks.jb;
import me.jadc.jadbreaks.tools.Conf;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ChangelogNotification implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		changeLogNotification(e.getPlayer());
	}
	
	public static void changeLogNotification(Player p) {
		String version = jb.getInstance().getDescription().getVersion();
		String url = "https://jadc.github.io/jb-info/changelog/";
		
		if(Conf.playerData.getConfig().getString(p.getUniqueId() + ".version") != version) {
			Conf.playerData.getConfig().set(p.getUniqueId() + ".version", version);
			Conf.playerData.save();
			
			String ob = ChatColor.YELLOW + " " + ChatColor.MAGIC + "! " + ChatColor.RESET;
			
			p.sendMessage("");
			p.sendMessage(ob + ChatColor.LIGHT_PURPLE + "The server has recieved a new patch!");
			p.sendMessage(ob + ChatColor.LIGHT_PURPLE + "To see what is new in patch " + version + ", click below.");
			TextComponent message = new TextComponent(ob + ChatColor.AQUA + "" + ChatColor.UNDERLINE + jb.getInstance().getDescription().getName() + " Patch " + version + " Changelog");
			message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(url + version + ".html").create()));
			message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url + version + ".html"));
			p.spigot().sendMessage(message);
			p.sendMessage("");
		}else {
			TextComponent message = new TextComponent(ChatColor.YELLOW + "Welcome back, current patch is " + ChatColor.UNDERLINE + "patch " + version + ".");
			message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(url + version + ".html").create()));
			message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url + version + ".html"));
			p.spigot().sendMessage(message);
		}
	}
}
