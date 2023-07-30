package pine.toast.legendsreborn.utils.ClassesClass.Barbarian;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import pine.toast.legendsreborn.utils.Keys;
import pine.toast.legendsreborn.utils.TeamManager.TeamManager;
import pine.toast.mana.ManaManager;

public class BarbarianEvent implements Listener {


  @EventHandler
  public void onDoubleAxeInteract(PlayerInteractEvent event) {
    ItemStack mainHandItem = event.getPlayer().getInventory().getItemInMainHand();

    if (mainHandItem.getType() == Material.AIR) {
      return;
    }

    if (!(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(Keys.DoubleAxe, PersistentDataType.STRING)))
      return;

    Player player = event.getPlayer();
    String playerClass = player.getPersistentDataContainer().get(Keys.selectedClass, PersistentDataType.STRING);

    // Check if the player's class is one of the allowed classes
    if (!playerClass.equals("Barbarian") && !playerClass.equals("Warlock")) {
      return;
    }


    if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
      if (ManaManager.getMana(player) >= 20) {
        ManaManager.setMana(player, ManaManager.getMana(player) - 20);
        Location playerLocation = player.getLocation();

        for (Entity entity : playerLocation.getWorld().getNearbyEntities(playerLocation, 3, 3, 3)) {
          if (entity instanceof LivingEntity && !(entity instanceof Player)) {
            LivingEntity livingEntity = (LivingEntity) entity;

            String playerTeam = TeamManager.getPlayerTeam(player.getUniqueId());
            String entityTeam = TeamManager.getPlayerTeam(entity.getUniqueId());

            if (playerTeam != null && playerTeam.equals(entityTeam)) {
              // The player and the entity are on the same team
              continue; // Skip damaging the entity
            }

            livingEntity.damage(10);
          }
        }
      }
    }


  }


}