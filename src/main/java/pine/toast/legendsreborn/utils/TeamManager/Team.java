package pine.toast.legendsreborn.utils.TeamManager;


import org.bukkit.Bukkit;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Team implements Serializable {
  private final String name;
  private UUID owner;
  private final Set<UUID> members;

  public Team(String name, UUID owner) {
    this.name = name;
    this.owner = owner;
    this.members = new HashSet<>();
  }

  // Getters and setters for the fields

  public void setOwner(UUID newOwner) {
    this.owner = newOwner;
  }
  public void addMember(UUID member) {
    members.add(member);
  }

  public void removeMember(UUID member) {
    members.remove(member);
  }

  public boolean isMember(UUID player) {
    return members.contains(player);
  }
  
  public Set<UUID> getMembers() {
    return new HashSet<>(members);
  }

  public boolean isOwner(UUID player) {
    return owner.equals(player);
  }
  
  
  
  public UUID getOwnerUUID(){
    return owner;
  }
  
  public String getOwnerName(){
    return Bukkit.getPlayer(owner).getName();
  }
  
  public String getName() {
    return name;
  }


  // Other common methods for teams
}
