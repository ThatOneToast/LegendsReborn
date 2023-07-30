package pine.toast.legendsreborn.Entities;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import pine.toast.legendsreborn.EternalRealms_LegendsReborn;
import pine.toast.legendsreborn.utils.LevelSystem.LevelManager;

public class Tesla implements Listener {

  
  

  @EventHandler
    public void onCreeperTarget(EntityTargetEvent event) {
    if (!(event.getEntity() instanceof Creeper creeper)) return;
    if (!(event.getTarget() instanceof Player player)) return;
    if (!(creeper.isPowered())) return;


    creeper.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200);
    creeper.setHealth(200);

    creeper.setCustomName(ChatColor.AQUA + "Tesla");
    Location playerLocation = player.getLocation();

    for (int i = 0; i < 3; i++) {
      player.getWorld().strikeLightning(playerLocation);
      player.getWorld().strikeLightning(playerLocation);
    }
  }

  @EventHandler
    public void onCreeperDeath(EntityDeathEvent event) {
    if (!(event.getEntity() instanceof Creeper creeper)) return;
    if (!(creeper.isPowered())) return;

    if(creeper.getKiller() == null) return;
    LevelManager.addExperience(creeper.getKiller(), 50 );

    Player killer = creeper.getKiller();
    if (killer != null) {
      Location killersLocal = killer.getLocation();
      for (int i = 0; i < 12; i++) {
        killer.getWorld().strikeLightning(killersLocal);
      }
    }
  }


  @EventHandler
    public void onCreeperExplode(ExplosionPrimeEvent event) {
    if (!(event.getEntity() instanceof Creeper creeper)) return;
    if (!(creeper.isPowered())) return;


    event.setCancelled(true);
    Player target = (Player) creeper.getTarget();
    if (target != null) {
      Location targetLocal = target.getLocation();
      for (int i = 0; i < 9; i++) {
        target.getWorld().strikeLightning(targetLocal);
      }
    }
  }

  @EventHandler
    public void teslaSpawn(EntitySpawnEvent event) {
    if(!(event.getEntity() instanceof Creeper creeper)) return;
    if(creeper.isPowered()) return;

    if(!(Math.random() <= 0.15)) return;

    creeper.setPowered(true);
  }

}