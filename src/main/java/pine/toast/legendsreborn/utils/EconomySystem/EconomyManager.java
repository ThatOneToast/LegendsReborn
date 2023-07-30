package pine.toast.legendsreborn.utils.EconomySystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EconomyManager implements Serializable {

  private static Map<UUID, Double> playerBalances;
  private static File balancesFile;

  public EconomyManager(File file) {
    playerBalances = new HashMap<>();
    balancesFile = file;
    loadBalancesFromFile();
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
      Bukkit.getLogger().warning("Failed to load player balances from file: " + balancesFile.getName());
    }
  }

  public static void saveBalancesToFile() {
    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(balancesFile))) {
      outputStream.writeObject(playerBalances);
    } catch (IOException e) {
      // Handle the exception appropriately (e.g., logging an error)
      Bukkit.getLogger().warning("Failed to save player balances to file: " + balancesFile.getName());
    }
  }

}