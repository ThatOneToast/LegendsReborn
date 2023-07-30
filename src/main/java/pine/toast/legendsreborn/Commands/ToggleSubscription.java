package pine.toast.legendsreborn.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import pine.toast.legendsreborn.utils.Keys;


public class ToggleSubscription implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("This command can only be used by players.");
      return true;
    }

    Player player = (Player) sender;

    if (!player.hasPermission("legendsreborn.givesubscription")) {
      player.sendMessage("You do not have permission to use this command.");
      return true;
    }

    Boolean isPlayerPaid = player.getPersistentDataContainer().get(Keys.isPaid, PersistentDataType.BOOLEAN);

    if (isPlayerPaid != null) {
      player.getPersistentDataContainer().set(Keys.isPaid, PersistentDataType.BOOLEAN, !isPlayerPaid);
      player.sendMessage("Your subscription status has been toggled.");
      return true;
    }

    // Player does not have isPaid key in the persistent data container
    player.sendMessage("Unable to toggle subscription status.");
    return false;
  }

}