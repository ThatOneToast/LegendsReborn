package pine.toast.legendsreborn.Setups;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import pine.toast.legendsreborn.utils.EconomySystem.EconomyManager;
import pine.toast.legendsreborn.utils.Keys;

import java.util.Objects;
import java.util.UUID;

public class FirstJoin implements Listener {

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {

    Player player = event.getPlayer();

    player.setHealthScale(20.0);

    String className = player.getPersistentDataContainer().get(Keys.selectedClass, PersistentDataType.STRING);
    PersistentDataContainer playerData = player.getPersistentDataContainer();
    Boolean isPaid = playerData.get(Keys.isPaid, PersistentDataType.BOOLEAN);

    if (!player.hasPlayedBefore()) {


      playerData.set(Keys.maxMana, PersistentDataType.DOUBLE, 100.0);
      playerData.set(Keys.manaPerSec, PersistentDataType.DOUBLE, 1.0);
      playerData.set(Keys.level, PersistentDataType.INTEGER, 0);
      playerData.set(Keys.experience, PersistentDataType.INTEGER, 0);
      playerData.set(Keys.showExp, PersistentDataType.BOOLEAN, true);
      playerData.set(Keys.isPaid, PersistentDataType.BOOLEAN, false);

      player.sendMessage(ChatColor.GRAY + "You can turn off the experience bar by typing " + ChatColor.GREEN + "/toggleexp");
      player.sendMessage(ChatColor.GRAY + "Welcome to Legends Reborn! Please select a class to begin your journey!");


      UUID playerUUID = player.getUniqueId();
      EconomyManager.deposit(playerUUID, 50.0);
      SelectClass.openClassSelectGUI(player); // Line 601 ( SelectClass.java ) Kicking player after class selection menu is closed.


    } else {

      // Has to be removed in final production \\
      playerData.set(Keys.isPaid, PersistentDataType.BOOLEAN, true);
      EconomyManager.deposit(player.getUniqueId(), 50.0);
      SelectClass.openClassSelectGUI(player);

      if (className == null) SelectClass.openClassSelectGUI(player);

      switch (Objects.requireNonNull(className)) {
        case "Barbarian":
          player.setHealthScale(40.0);
          player.setWalkSpeed(0.15f);
          playerData.set(Keys.maxLevel, PersistentDataType.INTEGER, 25);

        case "Mage":
          player.setWalkSpeed(0.2f);
          playerData.set(Keys.maxLevel, PersistentDataType.INTEGER, 25);

        case "Knight":
          player.setWalkSpeed(0.2f);
          playerData.set(Keys.maxLevel, PersistentDataType.INTEGER, 25);

        case "Assassin":
          player.setWalkSpeed(0.33f);
          player.setSilent(true);
          playerData.set(Keys.maxLevel, PersistentDataType.INTEGER, 25);

        case "Archer":
          player.setWalkSpeed(0.2f);
          playerData.set(Keys.maxLevel, PersistentDataType.INTEGER, 25);

        case "Necromancer":
          player.setWalkSpeed(0.2f);
          playerData.set(Keys.maxLevel, PersistentDataType.INTEGER, 25);

        case "Paladin":
          player.setHealthScale(40.0);
          player.setWalkSpeed(0.2f);
          playerData.set(Keys.maxLevel, PersistentDataType.INTEGER, 25);

        case "Priest":
          player.setWalkSpeed(0.2f);
          playerData.set(Keys.maxLevel, PersistentDataType.INTEGER, 25);

        case "Shaman":
          player.setHealthScale(40.0);
          player.setWalkSpeed(0.2f);
          playerData.set(Keys.maxLevel, PersistentDataType.INTEGER, 25);

        case "Warlock":
          player.setHealthScale(40.0);
          player.setWalkSpeed(0.2f);
          playerData.set(Keys.maxLevel, PersistentDataType.INTEGER, 25);

        default:
          player.setWalkSpeed(0.2f);
          player.sendMessage(ChatColor.RED + "You do not have a class selected! Please select a class to begin your journey!");
      }


    }

  }
}
