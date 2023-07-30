package pine.toast.legendsreborn.Entities;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EnderDragonChangePhaseEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.util.Vector;
import pine.toast.legendsreborn.Entities.Extras.DragonHealthRunnable;
import pine.toast.legendsreborn.EternalRealms_LegendsReborn;

import java.util.Random;

public class Enderdragon implements Listener {


  @EventHandler
  public void onEnderDragonSpawn(EntitySpawnEvent event) {
    if (!(event.getEntity() instanceof EnderDragon)) return;

    EnderDragon dragon = (EnderDragon) event.getEntity();

    dragon.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(2500);
    dragon.setHealth(2500);
    // Regen health
    new DragonHealthRunnable(EternalRealms_LegendsReborn.getPlugin(), dragon, 10.0);

  }

  @EventHandler
  public void onEnderDragonDamage(EntityDamageByEntityEvent event) {
    if (!(event.getDamager() instanceof EnderDragon enderDragon)) {
      return;
    }

    Entity entity = event.getEntity();
    Location entityLoc = entity.getLocation();
    entity.getWorld().strikeLightning(entityLoc);

    // Deal additional damage to the entity
    double additionalDamage = 30.0;
    if (entity instanceof LivingEntity livingEntity) {
      if (!(livingEntity.getHealth() > 30)) return;
      livingEntity.setHealth(livingEntity.getHealth() - additionalDamage);
    }
  }

  @EventHandler
  public void onEnderdragonAttacks(EnderDragonChangePhaseEvent event) {
    Random random = new Random();
    double chance = 0.5; // 50% chance for lightning strikes, 50% chance for players being flung up

    for (Entity entity : event.getEntity().getWorld().getEntities()) {
      if (!(entity instanceof Player)) {
        continue;
      }

      if (random.nextDouble() < chance) {
        // Lightning strikes
        Location loc = entity.getLocation();
        entity.getWorld().strikeLightning(loc);

        // Deal additional damage to the entity
        double additionalDamage = 30.0;
        LivingEntity livingEntity = (LivingEntity) entity;
        if (!(livingEntity.getHealth() > 30)) return;
        livingEntity.setHealth(livingEntity.getHealth() - additionalDamage);
      } else {
        // Players being flung up
        Player player = (Player) entity;// Modify player's velocity to make them go up
        Vector velocity = player.getVelocity();
        velocity.setY(30); // Set the Y velocity to 30 blocks per tick
        player.setVelocity(velocity);
      }
    }
  }


}