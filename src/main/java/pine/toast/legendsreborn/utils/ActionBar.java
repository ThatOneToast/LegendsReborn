package pine.toast.legendsreborn.utils;


import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import pine.toast.legendsreborn.utils.EconomySystem.EconomyManager;
import pine.toast.legendsreborn.utils.LevelSystem.LevelManager;
import pine.toast.mana.Mana;
import pine.toast.mana.ManaManager;
import toast.pine.overhaulsystems.Overhaul_Systems;

import java.util.UUID;


public class ActionBar {


  @SuppressWarnings("ALL")
  public static void initializeMana() {
    Bukkit.getScheduler().scheduleSyncRepeatingTask(Mana.getPlugin(), () -> {
      for (Player player : Bukkit.getOnlinePlayers()) {
        PersistentDataContainer playerData = player.getPersistentDataContainer();

        if (!(playerData.has(Keys.maxMana, PersistentDataType.DOUBLE))) {
          Bukkit.getLogger().warning("You have not registered values! Registering now...");
          playerData.set(Keys.maxMana, PersistentDataType.DOUBLE, 100.0);
        }

        if (!(playerData.has(Keys.manaPerSec, PersistentDataType.DOUBLE))) {
          Bukkit.getLogger().warning("You have not registered values! Registering now...");
          playerData.set(Keys.manaPerSec, PersistentDataType.DOUBLE, 1.0);
        }

        Player.Spigot spigot = player.spigot();
        String className = player.getPersistentDataContainer().get(Keys.selectedClass, PersistentDataType.STRING);

        spigot.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(health(player) + blockCheck(player) + mana(player) + getPlayerBalance(player) + getExperienceString(player) + getLevelString(player)));


        double maxMana = playerData.get(Keys.maxMana, PersistentDataType.DOUBLE);
        double manaPerSec = playerData.get(Keys.manaPerSec, PersistentDataType.DOUBLE);

        double currentMana = ManaManager.mana.get(player.getUniqueId());

        if (currentMana + manaPerSec <= maxMana) {
          ManaManager.mana.put(player.getUniqueId(), currentMana + manaPerSec);
        } else {
          ManaManager.mana.put(player.getUniqueId(), maxMana);
        }
      }

    }, 0, 20);
  }

  private static String getPlayerBalance(Player player) {
    UUID playerUUID = player.getUniqueId();
    double balance = EconomyManager.getPlayerBalance(playerUUID);
    String formattedBalance = String.format("%.2f", balance);
    return ChatColor.BLACK + " | " + ChatColor.GREEN + "💲" + ChatColor.GREEN + formattedBalance;

  }


  private static String getExperienceString(Player player) {
    if (Boolean.TRUE.equals(player.getPersistentDataContainer().get(Keys.showExp, PersistentDataType.BOOLEAN))) {
      double experience = LevelManager.getExperience(player);
      String formattedExperience = String.format("%.0f", experience);
      return ChatColor.GREEN + " ✹" + ChatColor.GREEN + formattedExperience + ChatColor.WHITE + "/" + ChatColor.GREEN + "1000";
    } else {
      return "";
    }
  }


  private static String getLevelString(Player player) {
    if (Boolean.TRUE.equals(player.getPersistentDataContainer().get(Keys.showExp, PersistentDataType.BOOLEAN))) {
      return ChatColor.DARK_GREEN + " ✪" + ChatColor.DARK_GREEN + LevelManager.getLevel(player) + ChatColor.WHITE + "/" + ChatColor.DARK_GREEN + "" + LevelManager.getMaxLevel(player);
    } else {
      return "";
    }
  }

  private static String mana(Player player) {
    double maxMana = ManaManager.getMaxMana(player);
    double mana = ManaManager.mana.get(player.getUniqueId());
    String formattedMana = String.format("%.1f", mana);
    String className = player.getPersistentDataContainer().get(Keys.selectedClass, PersistentDataType.STRING);

    if (mana > maxMana / 2) {
      if (className == "Necromancer")
        return ChatColor.DARK_PURPLE + " ✦" + ChatColor.BOLD + formattedMana + ChatColor.WHITE + "/" + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + maxMana;
      else
        return ChatColor.AQUA + " ✦" + ChatColor.BOLD + formattedMana + ChatColor.WHITE + "/" + ChatColor.AQUA + "" + ChatColor.BOLD + maxMana;
    } else if (mana < maxMana / 2 && mana > maxMana / 4) {
      return ChatColor.YELLOW + " ✦" + ChatColor.BOLD + formattedMana + ChatColor.WHITE + "/" + ChatColor.YELLOW + "" + ChatColor.BOLD + maxMana;
    } else {
      return ChatColor.DARK_RED + " ✦" + ChatColor.BOLD + formattedMana + ChatColor.WHITE + "/" + ChatColor.DARK_RED + "" + ChatColor.BOLD + maxMana;
    }
  }


  private static String health(Player player) {

    double health = player.getHealth();
    double half = player.getMaxHealth() / 2;
    double quarter = player.getMaxHealth() / 4;

    if (health > half) {
      return ChatColor.GREEN + "❤" + ChatColor.BOLD + Math.round(player.getHealth())
              + ChatColor.WHITE + "/" + ChatColor.GREEN + "" + ChatColor.BOLD + Math.round(player.getMaxHealth()) + ChatColor.BLACK + " |";
    } else if (health < half && health > quarter) {
      return ChatColor.YELLOW + "❤" + ChatColor.BOLD + Math.round(player.getHealth())
              + ChatColor.WHITE + "/" + ChatColor.YELLOW + "" + ChatColor.BOLD + Math.round(player.getMaxHealth()) + ChatColor.BLACK + " |";
    } else {
      return ChatColor.RED + "❤" + ChatColor.BOLD + Math.round(player.getHealth())
              + ChatColor.WHITE + "/" + ChatColor.RED + "" + ChatColor.BOLD + Math.round(player.getMaxHealth()) + ChatColor.BLACK + " |";
    }


  }


  private static String blockCheck(Player player) {
    Block block = player.getWorld().getBlockAt(player.getLocation().subtract(0, 5.5, 0));

    PersistentDataContainer playerData = player.getPersistentDataContainer();

    if (!(playerData.get(new NamespacedKey(Overhaul_Systems.getPlugin(), "DoubleJump"), PersistentDataType.BOOLEAN))) {
      return "";
    }
    ;

    if (!block.getType().equals(Material.AIR)) {
      return ChatColor.YELLOW + " ⚡" + ChatColor.BLACK + " |";
    } else {
      return ChatColor.RED + " ⚡" + ChatColor.BLACK + " |";
    }
  }


  // Create
}