package pine.toast.legendsreborn.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pine.toast.legendsreborn.utils.EconomySystem.EconomyManager;

public class Pay implements CommandExecutor {


  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    // Check if the command sender is a player
    if (!(sender instanceof Player)) {
      sender.sendMessage(ChatColor.RED + "Only players can use this command.");
      return true;
    }

    Player senderPlayer = (Player) sender;

    // Check if the command has the correct number of arguments
    if (args.length < 2) {
      senderPlayer.sendMessage(ChatColor.RED + "Usage: /pay <player> <amount>");
      return true;
    }

    // Parse the amount argument
    double amount;
    try {
      amount = Double.parseDouble(args[1]);
    } catch (NumberFormatException e) {
      senderPlayer.sendMessage(ChatColor.RED + "Invalid amount specified.");
      return true;
    }

    // Check if the sender has enough balance to make the payment
    double senderBalance = EconomyManager.getPlayerBalance(senderPlayer.getUniqueId());
    if (senderBalance < amount) {
      senderPlayer.sendMessage(ChatColor.RED + "Insufficient balance.");
      return true;
    }

    Player targetPlayer = Bukkit.getPlayer(args[0]);
    if (targetPlayer == null || !targetPlayer.isOnline()) {
      senderPlayer.sendMessage(ChatColor.RED + "Player not found or offline.");
      return true;
    }

    // Perform the payment
    EconomyManager.withdraw(senderPlayer.getUniqueId(), amount);
    EconomyManager.deposit(targetPlayer.getUniqueId(), amount);

    senderPlayer.sendMessage(ChatColor.GREEN + "Successfully paid " + ChatColor.WHITE + targetPlayer.getName() + ChatColor.GREEN + " $" + amount);
    targetPlayer.sendMessage(ChatColor.GREEN + "Received $" + amount + " from " + ChatColor.WHITE + senderPlayer.getName());

    return true;
  }


}