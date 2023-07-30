package pine.toast.legendsreborn.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pine.toast.legendsreborn.utils.TeamManager.TeamManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TeamCommand implements CommandExecutor {


  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(ChatColor.RED + "Only players can use this command.");
      return true;
    }

    Player player = (Player) sender;
    UUID playerUUID = player.getUniqueId();

    if (args.length == 0) {
      // /tteam - No subcommand provided
      // Handle appropriately (e.g., display help message)
      return true;
    }

    String subcommand = args[0].toLowerCase();

    if (subcommand.equals("create")) {
      // /tteam create <name>
      if (args.length < 2) {
        // Missing team name argument
        // Handle appropriately (e.g., display error message)
        return true;
      }

      String teamName = args[1];

      if (TeamManager.isMemberOfAnyTeam(playerUUID)) {
        // Player is already in a team
        player.sendMessage(ChatColor.RED + "You are already a member of a team.");
      } else {
        TeamManager.createTeam(teamName, playerUUID);
        // Handle the result (e.g., display success message)
        player.sendMessage(ChatColor.GREEN + "Team '" + teamName + "' has been created.");
      }
    } else if (subcommand.equals("join")) {
      // /tteam join
      // Call joinTeam method with null team name (to join latest invited team)
      TeamManager.acceptInvitation(playerUUID);
      // Handle the result (e.g., display success message)
    } else if (subcommand.equals("leave")) {
      // /tteam leave
      // Call leaveTeam method with null team name (to leave current team)
      String teamName = TeamManager.getPlayerTeam(playerUUID);
      TeamManager.leaveTeam(teamName, playerUUID);
      // Handle the result (e.g., display success message)
    } else if (subcommand.equals("transfer")) {
      // /tteam transfer <name>
      if (args.length < 2) {
        // Missing player name argument
        // Handle appropriately (e.g., display error message)
        return true;
      }
      String playerName = args[1];

      for (Player newOwner : Bukkit.getOnlinePlayers()) {
        if (newOwner.getName().equals(sender.getName()))
          sender.sendMessage(ChatColor.RED + " You cannot transfer the team to yourself! Yuh dumb nut.");
        if (newOwner.getName().equalsIgnoreCase(playerName)) {
          UUID newOwnerUUID = newOwner.getUniqueId();
          TeamManager.transferOwner(playerUUID, newOwnerUUID);
          sender.sendMessage(ChatColor.GREEN + "Ownership transferred to player: " + newOwner.getName());
          return true; // Exit the command execution after successful transfer
        }
      }
    } else if (subcommand.equals("delete")) {
      // /tteam delete
      // Handle deleting the team if conditions are met
      String teamName = TeamManager.getPlayerTeam(player.getUniqueId());

      if (teamName != null) {
        UUID owner = player.getUniqueId();
        TeamManager.deleteTeam(teamName, owner);
        player.sendMessage(ChatColor.GREEN + "Your team has been deleted.");
      } else {
        player.sendMessage(ChatColor.RED + "You are not a member of any team.");
      }
    } else if (subcommand.equals("invite")) {
      // /tteam invite <player>
      // Handle inviting a player to the team
      if (args.length < 2) {
        player.sendMessage(ChatColor.RED + "Usage: /tteam invite <player>");
        return true;
      }

      String playerName = args[1];
      Player invitedPlayer = Bukkit.getPlayer(playerName);

      if (sender.getName().equals(invitedPlayer.getName()))
        sender.sendMessage(ChatColor.RED + "Why are you inviting your self? Dumb ass......");

      if (invitedPlayer != null && invitedPlayer.isOnline()) {
        UUID inviterUUID = player.getUniqueId();
        UUID invitedUUID = invitedPlayer.getUniqueId();
        TeamManager.invitePlayer(inviterUUID, invitedUUID);
        player.sendMessage(ChatColor.GREEN + "Invitation sent to " + playerName);
      } else {
        player.sendMessage(ChatColor.RED + "Player not found.");
      }

    } else if (subcommand.equals("info")) {
 
      String teamName = TeamManager.getPlayerTeam(((Player) sender).getUniqueId());

      Set<UUID> memberUUIDs = TeamManager.getMembers(teamName);
      List<String> memberNames = new ArrayList<>();

      // Convert UUIDs to player names
      for (UUID memberId : memberUUIDs) {
        Player member = Bukkit.getPlayer(memberId);
        if (member != null) {
          memberNames.add(member.getName());
        }
      }

      sender.sendMessage(ChatColor.GREEN + "Team Information - " + ChatColor.GOLD + teamName);
      sender.sendMessage(ChatColor.GREEN + "Owner: " + ChatColor.GOLD + TeamManager.getOwner(teamName));
      sender.sendMessage(ChatColor.GREEN + "Members: " + ChatColor.GOLD+ String.join(", ", memberNames));


      // Handle other team information (if applicable)
    } else {
      // Invalid subcommand
      // Handle appropriately (e.g., display error message)
      sender.sendMessage(ChatColor.RED + "THat is not a command what are you retarded?");
    }

    return true;
  }


}
