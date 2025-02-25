package me.jadc.jadbreaks.cmd;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jadc.jadbreaks.tools.Effects;
import me.jadc.jadbreaks.tools.Message;

public class Jihad implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("jihad")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;

				p.setHealth(1.0D);
				p.getWorld().createExplosion(p.getLocation().getX(), p.getLocation().getY() + (p.getHeight() / 2), p.getLocation().getZ(), 2.0F, false, false);
				Effects.emitBlockBreak(p.getEyeLocation(), Material.TNT);
				Effects.emitParticle(p.getLocation(), Particle.LAVA, 25, 0, p.getHeight() / 2, 0);
			}else {
				Message.error(sender, "Ingame players only");
			}
			return true;
		}
		
		return true;
	}
	
}
