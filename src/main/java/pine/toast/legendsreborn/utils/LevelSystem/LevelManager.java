package pine.toast.legendsreborn.utils.LevelSystem;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import pine.toast.legendsreborn.utils.Keys;

public class LevelManager {


  @SuppressWarnings("ALL")
  public static void addExperience(Player player, int amount) {
    int currentLevel = player.getPersistentDataContainer().get(Keys.level, PersistentDataType.INTEGER);
    int experience = player.getPersistentDataContainer().get(Keys.experience, PersistentDataType.INTEGER);
    int maxLevel = player.getPersistentDataContainer().get(Keys.maxLevel, PersistentDataType.INTEGER);

    player.getPersistentDataContainer().set(Keys.experience, PersistentDataType.INTEGER, experience + amount);


    if (experience + amount >= 1000) {
      if (currentLevel >= maxLevel) {
        return;
      } else {
        currentLevel++;
        player.getPersistentDataContainer().set(Keys.level, PersistentDataType.INTEGER, currentLevel);
        player.getPersistentDataContainer().set(Keys.experience, PersistentDataType.INTEGER, 0);

        player.sendMessage(ChatColor.GRAY + "You leveled up to level " + ChatColor.GREEN + currentLevel + ChatColor.GRAY + "!");
        String className = player.getPersistentDataContainer().get(Keys.selectedClass, PersistentDataType.STRING);
        Experience.buffs(player, currentLevel, className);
      }
    }
  }

  @SuppressWarnings("ALL")
  public static void removeExperience(Player player, int amount) {
    int currentLevel = player.getPersistentDataContainer().get(Keys.level, PersistentDataType.INTEGER);
    int experience = player.getPersistentDataContainer().get(Keys.experience, PersistentDataType.INTEGER);
    int maxLevel = player.getPersistentDataContainer().get(Keys.maxLevel, PersistentDataType.INTEGER);

    player.getPersistentDataContainer().set(Keys.experience, PersistentDataType.INTEGER, experience - amount);

  }

  @SuppressWarnings("ALL")
  public static void setExperience(Player player, int amount) {
    int currentLevel = player.getPersistentDataContainer().get(Keys.level, PersistentDataType.INTEGER);
    int experience = player.getPersistentDataContainer().get(Keys.experience, PersistentDataType.INTEGER);
    int maxLevel = player.getPersistentDataContainer().get(Keys.maxLevel, PersistentDataType.INTEGER);

    player.getPersistentDataContainer().set(Keys.experience, PersistentDataType.INTEGER, amount);

  }

  @SuppressWarnings("ALL")
  public static void setLevel(Player player, int level) {
    int maxLevel = player.getPersistentDataContainer().get(Keys.maxLevel, PersistentDataType.INTEGER);
    int currentLevel = player.getPersistentDataContainer().get(Keys.level, PersistentDataType.INTEGER);

    if (level > maxLevel) {
      player.sendMessage(ChatColor.RED + "You have reached the max level!");
      player.getPersistentDataContainer().set(Keys.level, PersistentDataType.INTEGER, maxLevel);
      return;
    } else {
      player.getPersistentDataContainer().set(Keys.level, PersistentDataType.INTEGER, level);
      player.sendMessage(ChatColor.GRAY + "You are level " + ChatColor.GREEN + level + ChatColor.GRAY + "!");
    }
  }

  @SuppressWarnings("ALL")
  public static void setMaxLevel(Player player, int maxLevel) {
    player.getPersistentDataContainer().set(Keys.maxLevel, PersistentDataType.INTEGER, maxLevel);
  }

  @SuppressWarnings("ALL")
  public static Integer getExperience(Player player) {
    return player.getPersistentDataContainer().get(Keys.experience, PersistentDataType.INTEGER);
  }

  @SuppressWarnings("ALL")
  public static Integer getLevel(Player player) {
    return player.getPersistentDataContainer().get(Keys.level, PersistentDataType.INTEGER);
  }

  @SuppressWarnings("ALL")
  public static Integer getMaxLevel(Player player) {
    return player.getPersistentDataContainer().get(Keys.maxLevel, PersistentDataType.INTEGER);
  }

}
