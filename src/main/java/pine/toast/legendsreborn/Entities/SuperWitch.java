package pine.toast.legendsreborn.Entities;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pine.toast.legendsreborn.EternalRealms_LegendsReborn;
import pine.toast.legendsreborn.utils.LevelSystem.LevelManager;

public class SuperWitch implements Listener {


  @EventHandler
    public void onWitchSpawn(CreatureSpawnEvent event) {
    if (!(event.getEntity() instanceof Witch witch)) return;

    witch.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
    witch.setHealth(100);
    witch.setCustomName(ChatColor.RED + "Super Witch");
    witch.setCustomNameVisible(true);

  }

  @EventHandler
    public void onWitchDrinkPotion(EntityPotionEffectEvent event){
    if(!(event.getEntity() instanceof Witch witch)) return;

    for (Entity entity : witch.getNearbyEntities(15, 15, 15)) {
      if (entity instanceof Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 15*20, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 30*20, 4));
        player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 30*20, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30*20, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 30*20, 1));

        Location Playerloc = player.getLocation();
        player.getWorld().strikeLightning(Playerloc);
      }
    }
  }

  @EventHandler
    public void onWitchDamage(EntityDamageEvent event){
    if(!(event.getEntity() instanceof Witch witch)) return;
    if(!(witch.isGlowing())) return;

    witch.setHealth(0);
  }

  @EventHandler
    public void onWitchDeath(EntityDeathEvent event){
    if(!(event.getEntity() instanceof Witch witch)) return;
    
    if(witch.getKiller() == null) return;

    LevelManager.addExperience(witch.getKiller(), 50);

    for (Entity entity : witch.getNearbyEntities(15, 15, 15)) {
      if (entity instanceof Player player) {
        // clear all potion effects
        player.removePotionEffect(PotionEffectType.WITHER);
        player.removePotionEffect(PotionEffectType.HUNGER);
        player.removePotionEffect(PotionEffectType.POISON);
        player.removePotionEffect(PotionEffectType.SLOW);
        player.removePotionEffect(PotionEffectType.WEAKNESS);

        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 4));
      }
    }
  }


}