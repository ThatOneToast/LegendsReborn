package pine.toast.legendsreborn.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pine.toast.legendsreborn.utils.AuctionHouse.AuctionHouseManager;

public class AHCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (args.length == 0) {
      // Main command "ah" without any subcommand
      if (!(sender instanceof Player)) {
        sender.sendMessage(ChatColor.RED + "Must be a player to use this command.");
        return true;
      }

      Player player = (Player) sender;
      openAuctionMainMenu(player);
      return true;

    } else if (args[0].equalsIgnoreCase("sell")) {
      if (args.length < 3) {
        sender.sendMessage(ChatColor.RED + "Usage: /ah sell <category> <price>");
        return true;
      }

      String categoryName = args[1].toLowerCase();
      double price = 0;

      try {
        price = Double.parseDouble(args[2]);
      } catch (NumberFormatException e) {
        sender.sendMessage(ChatColor.RED + "Invalid price. Please specify a valid number.");
        return true;
      }

      // Subcommand "sell"
      Player player = (Player) sender;
      ItemStack item = player.getInventory().getItemInMainHand();

      // Add the item to the specified category
      AuctionHouseManager.addItemToCategory(item, categoryName, price, player);
      player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
      player.sendMessage(ChatColor.GREEN + "Item has been listed for auction in the " + categoryName + " category.");
      return true;
    }

    sender.sendMessage(ChatColor.RED + "Invalid subcommand. Usage: /ah sell <category> <price>");
    return true;
  }


  private void openAuctionMainMenu(Player player) {
    Inventory inventory = AuctionHouseManager.AuctionMainMenu();
    player.openInventory(inventory);
  }

}