package pine.toast.legendsreborn.utils.ClassesClass.Mage;

import net.md_5.bungee.api.chat.hover.content.Item;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import pine.toast.legendsreborn.utils.Keys;
import pine.toast.legendsreborn.utils.TeamManager.TeamManager;
import pine.toast.mana.ManaManager;

public class MageEvents implements Listener {


  @EventHandler
  public void onAetherUse(PlayerInteractEvent event) {

    Player player = event.getPlayer();

    if (!(player.getInventory().getItemInMainHand().isSimilar(MageItems.AetherWalker()))) return;

    if (event.getAction() == Action.RIGHT_CLICK_AIR) {
      Location playerloc = player.getLocation();
      Vector direction = playerloc.getDirection().normalize();
      Location teleLoc = playerloc.add(direction.multiply(10));

      double mana = ManaManager.getMana(player);

      if (mana >= 60) {
        ManaManager.setMana(player, ManaManager.getMana(player) - 60);
        player.teleport(teleLoc);

      } else {
        player.sendMessage(ChatColor.RED + "You do not have enough mana to use this item!");
      }

    }

  }


  @EventHandler
  public void ontofUse(PlayerInteractEvent event) {

    Player player = event.getPlayer();

    if (!(player.getInventory().getItemInMainHand().isSimilar(MageItems.TomeOfFrost()))) return;

    double mana = ManaManager.getMana(player);

    if (mana < 75) {
      player.sendMessage(ChatColor.RED + "You dont have enought mana for this item.");
    } else {

      ManaManager.setMana(player, mana - 75);
      if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

        for (Entity entities : player.getNearbyEntities(10, 10, 10)) {
          if (!(entities instanceof Player players)) return;

          String playerTeam = TeamManager.getPlayerTeam(player.getUniqueId());
          String nearbyPlayerTeam = TeamManager.getPlayerTeam(players.getUniqueId());

          if (playerTeam == null || nearbyPlayerTeam == null) continue;

          if (playerTeam.equals(nearbyPlayerTeam)) return;

          PotionEffect potion = new PotionEffect(PotionEffectType.SLOW, 10, 255, true);
          players.sendMessage(ChatColor.RED + "You have been frozen by " + ChatColor.GOLD + player.getName());
          players.addPotionEffect(potion);

        }

      }

    }

  }

  @EventHandler()
  public void onTotmeUse(EntityResurrectEvent event) {

    if (!(event.getEntity() instanceof Player player)) return;

    String className = player.getPersistentDataContainer().get(Keys.selectedClass, PersistentDataType.STRING);

    boolean allow = false;

    switch (className) {
      case "Mage", "Shaman", "Necromancer" -> allow = true;
    }

    double mana = ManaManager.getMana(player);

    if (mana >= 100) {
      if (!(allow)) {

        player.sendMessage(ChatColor.RED + "You are not intelligent enough to use totems!");
        event.setCancelled(true);
      }
    } else {
      player.sendMessage(ChatColor.RED + "You didnt have enough mana to cheat death.");
    }

  }


  @EventHandler
  public void onWohUse(PlayerInteractEvent event) {
    ItemStack mainHandItem = event.getPlayer().getInventory().getItemInMainHand();

    if (mainHandItem.getType() == Material.AIR) {
      return;
    }

    if (!(event.getPlayer().getInventory().getItemInMainHand().isSimilar(MageItems.WoH())))
      return;

    Player player = event.getPlayer();
    String playerClass = player.getPersistentDataContainer().get(Keys.selectedClass, PersistentDataType.STRING);

    // Check if the player's class is one of the allowed classes
    if (!playerClass.equals("Necromancer") && !playerClass.equals("Mage") && !playerClass.equals("Shaman")) {
      return;
    }


    if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
      Location location = player.getLocation();

      double mana = ManaManager.getMana(player);

      if (mana >= 50) {
        player.getWorld().createExplosion(location, 3, true, false, player);
        ManaManager.setMana(player, mana - 50);
      } else {
        player.sendMessage(ChatColor.RED + "You do not have enough mana to use this item!");
      }

    } else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
      // Get the block the player is looking at
      Location location = player.getTargetBlock(null, 100).getLocation();

      // Create an explosion at the block if the player has enough mana
      double mana = ManaManager.getMana(player);

      if (mana >= 50) {
        player.getWorld().createExplosion(location, 4, true, false, player);
        ManaManager.setMana(player, mana - 50);
      } else {
        player.sendMessage(ChatColor.RED + "You do not have enough mana to use this item!");
      }
    }

  }

  @EventHandler
  public void onStarfireUse(PlayerInteractEvent event) {
    ItemStack mainHandItem = event.getPlayer().getInventory().getItemInMainHand();

    if (mainHandItem.getType() == Material.AIR) {
      return;
    }

    if (!(event.getPlayer().getInventory().getItemInMainHand().isSimilar(MageItems.StarFire())))
      return;
    Player player = event.getPlayer();


    String playerClass = player.getPersistentDataContainer().get(Keys.selectedClass, PersistentDataType.STRING);

    // Check if the player's class is one of the allowed classes
    if (!playerClass.equals("Necromancer") && !playerClass.equals("Mage") && !playerClass.equals("Shaman")) {
      return;
    }


    if (event.getAction() == Action.LEFT_CLICK_AIR) {
      // Get the block the player is looking at
      Location location = player.getTargetBlock(null, 100).getLocation();

      // Summon lightning at the block if the player has enough mana
      double mana = ManaManager.getMana(player);

      if (mana >= 10) {
        player.getWorld().strikeLightning(location);
        ManaManager.setMana(player, mana - 10);
      } else {
        event.setCancelled(true);
      }
    } else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
      // Get entities within 10 blocks of the player
      for (Entity entity : player.getNearbyEntities(10, 10, 10)) {
        if (entity instanceof Item) {
          continue;
        }

        if (entity instanceof Player targetPlayer) {
          String playerTeam = TeamManager.getPlayerTeam(player.getUniqueId());
          String targetPlayerTeam = TeamManager.getPlayerTeam(targetPlayer.getUniqueId());
          if (playerTeam != null && playerTeam.equals(targetPlayerTeam)) {
            // The player and the target player are on the same team
            continue; // Skip summoning lightning on the target player
          }
        }

        double mana = ManaManager.getMana(player);

        if (mana >= 10) {
          player.getWorld().strikeLightning(entity.getLocation());
          ManaManager.setMana(player, mana - 10);
        } else {
          event.setCancelled(true);
        }
      }
    }

  }


  @EventHandler
  public void onBrokenWandUse(PlayerInteractEvent event) {
    ItemStack mainHandItem = event.getPlayer().getInventory().getItemInMainHand();

    if (mainHandItem.getType() == Material.AIR) {
      return;
    }


    if (!mainHandItem.isSimilar(MageItems.brokenWand())) {
      return;
    }

    Player player = event.getPlayer();

    String playerClass = player.getPersistentDataContainer().get(Keys.selectedClass, PersistentDataType.STRING);

    if (playerClass == null) {
      return;
    }

    // Check if the player's class is one of the allowed classes
    if (!playerClass.equals("Necromancer") && !playerClass.equals("Mage") && !playerClass.equals("Shaman")) {
      return;
    }

    if (event.getAction() == Action.RIGHT_CLICK_AIR) {
      double currentMana = ManaManager.getMana(player);
      if (currentMana < 15) {
        player.sendMessage(ChatColor.RED + "You don't have enough mana!");
      } else {
        ManaManager.setMana(player, currentMana - 15);
        launchMagicMissile(player);
      }
    }
  }

  private void launchMagicMissile(Player player) {
    Vector direction = player.getEyeLocation().getDirection();
    Location startLocation = player.getEyeLocation().add(direction.normalize().multiply(2));

    Arrow arrow = player.launchProjectile(Arrow.class, direction);
    arrow.setShooter(player);
    arrow.setVelocity(direction.multiply(2));

    player.getWorld().spawnParticle(Particle.REDSTONE, startLocation, 20, 0.1, 0.1, 0.1, 0.1, new Particle.DustOptions(Color.PURPLE, 3));

  }


}