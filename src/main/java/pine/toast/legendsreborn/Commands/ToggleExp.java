package pine.toast.legendsreborn.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import pine.toast.legendsreborn.utils.Keys;

@SuppressWarnings("ALL")
public class ToggleExp implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

    if (!(sender instanceof Player)) sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");

    Player player = (Player) sender;

    PersistentDataContainer container = player.getPersistentDataContainer();

    boolean toggle = container.get(Keys.isPaid, PersistentDataType.BOOLEAN);

    if (toggle) {
      container.set(Keys.showExp, PersistentDataType.BOOLEAN, false);
      player.sendMessage(ChatColor.GRAY + "You have toggled off your experience bar!");
    } else {
      container.set(Keys.showExp, PersistentDataType.BOOLEAN, true);
      player.sendMessage(ChatColor.GRAY + "You have toggled on your experience bar!");
    }

    return true;
  }
}
