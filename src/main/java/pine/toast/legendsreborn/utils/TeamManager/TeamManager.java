package pine.toast.legendsreborn.utils.TeamManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.*;


public class TeamManager implements Serializable {
  private static Map<String, Team> teams;
  private static Map<UUID, String> pendingInvitations;
  private static File teamsFile;

  public TeamManager(File teamsFile) {
    this.teamsFile = teamsFile;
    this.teams = new HashMap<>();
    loadTeamsFromFile();
  }

  public static void createTeam(String name, UUID owner) {
    if (teams.containsKey(name)) {
      // Team name already exists
      // Handle appropriately (e.g., display error message)
      return;
    }

    Team team = new Team(name, owner); // Replace ConcreteTeam with your specific team implementation
    team.addMember(owner);
    team.setOwner(owner);
    teams.put(name, team);
    saveTeamsToFile();

    // Handle the result (e.g., display success message)
    Player player = Bukkit.getPlayer(owner);
    if (player != null) {
      player.sendMessage(ChatColor.GREEN + "You are now the owner of the team.");
    }
  }

  public static void joinTeam(String name, UUID player) {
    Team team = teams.get(name);
    if (team != null) {
      team.addMember(player);
      saveTeamsToFile();
    }
  }

  public static void leaveTeam(String name, UUID player) {
    Team team = teams.get(name);
    if (team != null) {
      team.removeMember(player);
      saveTeamsToFile();
    }
  }

  public static boolean isMember(String name, UUID player) {
    Team team = teams.get(name);
    return team != null && team.isMember(player);
  }

  public static boolean isMemberOfAnyTeam(UUID playerUUID) {
    for (Team team : teams.values()) {
      if (team.isMember(playerUUID)) {
        return true;
      }
    }
    return false;
  }

  public static boolean isOwner(String name, UUID player) {
    Team team = teams.get(name);
    return team != null && team.isOwner(player);
  }


  public static Set<UUID> getMembers(String name) {
    Team team = teams.get(name);
    return (team != null) ? team.getMembers() : Collections.emptySet();
  }


  public static void loadTeamsFromFile() {
    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(teamsFile))) {
      teams = (HashMap<String, Team>) inputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      // Handle the exception appropriately (e.g., logging an error)
      e.printStackTrace();
    }
  }

  public static void saveTeamsToFile() {
    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(teamsFile))) {
      outputStream.writeObject(teams);
    } catch (IOException e) {
      // Handle the exception appropriately (e.g., logging an error)
      e.printStackTrace();
    }
  }


  public static boolean teamExists(String teamName) {
    return teams.containsKey(teamName);
  }

  public static String getPlayerTeam(UUID playerUUID) {
    for (Map.Entry<String, Team> entry : teams.entrySet()) {
      Team team = entry.getValue();
      if (team.isMember(playerUUID)) {
        return entry.getKey();
      }
    }
    return null;
  }

  public static void deleteTeam(String teamName, UUID owner) {
    Team team = teams.get(teamName);
    if (team != null && team.isOwner(owner) && team.getMembers().size() == 1) {
      teams.remove(teamName);
      saveTeamsToFile();
    }
  }

  public static void transferOwner(UUID currentOwner, UUID newOwner) {
    for (Team team : teams.values()) {
      if (team.isOwner(currentOwner) && team.getMembers().size() > 1) {
        team.setOwner(newOwner);
        team.removeMember(currentOwner);
        saveTeamsToFile();
        return; // Exit the loop after the first successful transfer
      }
    }
  }


  public static void invitePlayer(UUID inviterUUID, UUID invitedUUID) {
    String inviteTeam = getPlayerTeam(inviterUUID);
    String invitedTeam = getPlayerTeam(invitedUUID);

    if (isMember(inviteTeam, inviterUUID)) {
      if (invitedTeam == null) {
        pendingInvitations.put(invitedUUID, inviteTeam);
        Player invitedPlayer = Bukkit.getPlayer(invitedUUID);
        if (invitedPlayer != null && invitedPlayer.isOnline()) {
          invitedPlayer.sendMessage(ChatColor.GREEN + "You have been invited to join team: " + inviteTeam);
          invitedPlayer.sendMessage(ChatColor.GREEN + "To join, use /tteam join");
        }
      }
    } else {
      Player inviterPlayer = Bukkit.getPlayer(inviterUUID);

      inviterPlayer.sendMessage(ChatColor.RED + " You are not in a team, YUH DUMB NUT!");


    }
  }

  public static void acceptInvitation(UUID playerUUID) {
    if (pendingInvitations.containsKey(playerUUID)) {
      String teamName = pendingInvitations.get(playerUUID);
      joinTeam(teamName, playerUUID);
      pendingInvitations.remove(playerUUID);
      Player player = Bukkit.getPlayer(playerUUID);
      if (player != null) {
        player.sendMessage(ChatColor.GREEN + "You have joined team: " + teamName);
      }
    } else {
      Player player = Bukkit.getPlayer(playerUUID);
      if (player != null) {
        player.sendMessage(ChatColor.RED + "You have not been invited to any team.");
      }
    }
  }


  public static String getOwnerName(String teamName) {
    Team team = teams.get(teamName);
    if (team != null) {
      return team.getOwnerName();
    }
    return null;
  }

  public static Object getOwner(String teamName) {
    Team team = teams.get(teamName);
    if (team != null) {
      return team.getOwnerName();
    }
    return null; // Return null if the team doesn't exist
  }

}
