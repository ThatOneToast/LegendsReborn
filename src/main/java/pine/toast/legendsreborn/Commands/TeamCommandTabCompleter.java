package pine.toast.legendsreborn.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamCommandTabCompleter implements TabCompleter {

  private static final List<String> SUBCOMMANDS = List.of("transfer", "create", "join", "leave", "invite", "info", "delete");

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    if (args.length == 1) {
      return filterSubcommands(args[0]);
    } else if (args.length == 2) {
      return filterTeamNames(args[0], args[1]);
    } else if (args.length == 3 && args[0].equalsIgnoreCase("transfer")) {
      return filterPlayerNames(args[2]);
    }
    return null;
  }

  private List<String> filterSubcommands(String arg) {
    List<String> completions = new ArrayList<>();
    for (String subcommand : SUBCOMMANDS) {
      if (startsWithIgnoreCase(subcommand, arg)) {
        completions.add(subcommand);
      }
    }
    return completions;
  }

  private List<String> filterTeamNames(String subcommand, String arg) {
    List<String> completions = new ArrayList<>();
    if (subcommand.equalsIgnoreCase("transfer")) {
      // Add logic to suggest any team name or player name respectively
      for (Player player : Bukkit.getOnlinePlayers()) {
        completions.add(player.getName());
      }
    }
    return completions;
  }

  private List<String> filterPlayerNames(String arg) {
    // Add logic to suggest player names
    List<String> completions = new ArrayList<>();

    for (Player player : Bukkit.getOnlinePlayers()) {
      completions.add(player.getName());
    }
    return completions;
  }

  private boolean startsWithIgnoreCase(String str, String prefix) {
    return str.toLowerCase().startsWith(prefix.toLowerCase());
  }
}
