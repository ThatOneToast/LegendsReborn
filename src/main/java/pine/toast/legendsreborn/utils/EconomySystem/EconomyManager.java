package pine.toast.legendsreborn.utils.EconomySystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.bukkit.Bukkit.getLogger;

public class EconomyManager implements Serializable {

  private static Map<UUID, Double> playerBalances;
  private static File balancesFile;

  public EconomyManager(File file) {
    playerBalances = new HashMap<>();
    balancesFile = file;
    loadBalancesFromFile();
  }

  public static void createBalancesFile(Plugin plugin) {
    File balanceFile = new File(plugin.getDataFolder(), "balances.dat");

    if (!balanceFile.exists()) {
      try {
        boolean balanceFileCreated = balanceFile.createNewFile();
        if (balanceFileCreated) {
          getLogger().info("balances.dat file created.");
          // Perform additional actions for a successful file creation
        } else {
          getLogger().severe("Failed to create balances.dat file!");
          // Perform additional actions for a failed file creation
          // For example, attempt a recovery mechanism with retries
          int maxRetries = 3;
          int currentRetry = 0;
          boolean success = false;
          while (currentRetry < maxRetries && !success) {
            try {
              // Add a short delay before retrying
              Thread.sleep(1000);
              success = balanceFile.createNewFile();
              currentRetry++;
            } catch (InterruptedException | IOException e) {
              getLogger().severe("Failed to create balances.dat file on retry " + currentRetry);
              e.printStackTrace();
            }
          }
          if (success) {
            getLogger().info("balances.dat file created on retry " + currentRetry);
          } else {
            getLogger().severe("Failed to create balances.dat file after " + currentRetry + " retries!");
            // Perform additional actions for a failed recovery
          }
        }
      } catch (IOException e) {
        getLogger().severe("Failed to create balances.dat file!");
        e.printStackTrace();
      }
    }
  }


  public static double getPlayerBalance(UUID playerId) {
    return playerBalances.getOrDefault(playerId, 0.0);
  }


  public static void setPlayerBalance(UUID playerId, double balance) {
    playerBalances.put(playerId, balance);
    saveBalancesToFile();
  }


  public static void deposit(UUID playerId, double amount) {
    double currentBalance = getPlayerBalance(playerId);
    playerBalances.put(playerId, currentBalance + amount);
    saveBalancesToFile();
  }


  public static void withdraw(UUID playerId, double amount) {
    double currentBalance = getPlayerBalance(playerId);
    if (currentBalance >= amount) {
      playerBalances.put(playerId, currentBalance - amount);
      saveBalancesToFile();
    }
  }

  public static void pay(UUID senderId, UUID recipientId, double amount) {
    double senderBalance = getPlayerBalance(senderId);
    Player player = Bukkit.getPlayer(senderId);
    if (senderBalance >= amount) {
      withdraw(senderId, amount);
      deposit(recipientId, amount);

      player.sendMessage(ChatColor.GREEN + "You paid " + ChatColor.GOLD + amount + ChatColor.GREEN + " to " + Bukkit.getPlayer(recipientId).getName());
      player.sendMessage(ChatColor.GOLD + Bukkit.getPlayer(senderId).getName() + ChatColor.GREEN + " paid you " + ChatColor.GOLD + amount);
    } else {
      player.sendMessage(ChatColor.RED + "Insufficient funds");
    }
  }


  public static void loadBalancesFromFile() {
    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(balancesFile))) {
      playerBalances = (HashMap<UUID, Double>) inputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      // Handle the exception appropriately (e.g., logging an error)
      getLogger().warning("Failed to load player balances from file: " + balancesFile.getName());
    }
  }

  public static void saveBalancesToFile() {
    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(balancesFile))) {
      outputStream.writeObject(playerBalances);
    } catch (IOException e) {
      // Handle the exception appropriately (e.g., logging an error)
      getLogger().warning("Failed to save player balances to file: " + balancesFile.getName());
    }
  }

}