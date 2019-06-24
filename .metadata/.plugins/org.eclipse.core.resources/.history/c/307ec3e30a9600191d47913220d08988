package me.jadc.jadbreaks.tools;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;

public class Effects {
	public static void playSound(Location l, Sound s, float volume, float pitch) {
		l.getWorld().playSound(l, s, volume, pitch);
	}
	
	public static void emitParticle(Location l, Particle particle, int count, double x, double y, double z) {
		l.getWorld().spawnParticle(particle, l, count, x, y, z);
	}
	
	public static void emitColoredParticle(Location l, int count, double x, double y, double z, int red, int green, int blue) {
		l.getWorld().spawnParticle(Particle.REDSTONE, l, count, x, y, z, new DustOptions(Color.fromRGB(red, green, blue), 1));
	}
}
