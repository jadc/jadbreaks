package me.jadc.jadbreaks.addons;

import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.jadc.jadbreaks.tools.Effects;
import me.jadc.jadbreaks.tools.Message;
import net.md_5.bungee.api.ChatColor;

public class GodPets implements Listener {

	// Specifies owner of pet when interacted
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Tameable) {
			Tameable pet = (Tameable) e.getRightClicked();
			if (pet.isTamed() && !(pet.getOwner().getUniqueId().equals(e.getPlayer().getUniqueId()))) {
				Message.bar(e.getPlayer(), ChatColor.YELLOW + "I am the pet of " + pet.getOwner().getName() + ".");
			}
		}
	}
	
	@EventHandler
	public void onHurtByNonEntity(EntityDamageEvent e) {
		if(e.getEntity() instanceof Tameable) {
			Tameable pet = (Tameable) e.getEntity();
			if(pet.isTamed()) {
				((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 2, true));
				Effects.emitParticle(e.getEntity().getLocation(), Particle.CRIT, 5, 0, e.getEntity().getHeight() / 2, 0);
				e.setCancelled(!e.getCause().equals(DamageCause.ENTITY_ATTACK));
			}
		}
	}
}
