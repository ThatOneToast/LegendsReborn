package pine.toast.legendsreborn.utils.EconomySystem;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import pine.toast.legendsreborn.utils.LevelSystem.LevelManager;

import java.util.UUID;

public class MoneyPerKill implements Listener {


  @EventHandler
  public void onEntityDeath(EntityDeathEvent event) {

    if(event.getEntity().getKiller() == null) return;
    
    Player player = event.getEntity().getKiller();
    UUID playerUUID = player.getUniqueId();
    int currentLevel = LevelManager.getLevel(player);

    
    
    EconomyManager.deposit(playerUUID, (0.25 * currentLevel) );

  }


}