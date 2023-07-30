package pine.toast.legendsreborn.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AHTabCompleter implements TabCompleter {

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    List<String> completions = new ArrayList<>();

    if (args.length == 1) {
      // Tab completing the subcommand argument
      String input = args[0].toLowerCase();
      if ("sell".startsWith(input)) {
        completions.add("sell");
      }
    } else if (args.length == 2) {
    // Tab completing the category argument
    completions.add("blocks");
    completions.add("armor");
    completions.add("tools");
    completions.add("weapons");
    completions.add("misc");
    }
    
    return completions;
  }
}