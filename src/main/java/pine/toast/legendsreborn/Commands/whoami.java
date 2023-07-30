package pine.toast.legendsreborn.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import pine.toast.legendsreborn.utils.Keys;
import pine.toast.legendsreborn.utils.LevelSystem.LevelManager;
import pine.toast.mana.ManaManager;

import java.util.ArrayList;
import java.util.List;


public class whoami implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

    if (!(commandSender instanceof Player player))
      commandSender.sendMessage(ChatColor.RED + "You must be a player to use this command!");

    Player player = (Player) commandSender;
    PersistentDataContainer playerData = player.getPersistentDataContainer();

    String className = playerData.get(Keys.selectedClass, PersistentDataType.STRING);

    double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
    double armor = player.getAttribute(Attribute.GENERIC_ARMOR).getValue();
    double armorToughness = player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getValue();
    double maxMana = ManaManager.getMaxMana(player);

    int experience = LevelManager.getExperience(player);
    int level = LevelManager.getLevel(player);
    int maxLevel = LevelManager.getMaxLevel(player);


    // Create the inventory
    Inventory inventory = Bukkit.createInventory(player, 9, ChatColor.GREEN + "Player Stats");

    // Create player's head item
    ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
    SkullMeta headMeta = (SkullMeta) playerHead.getItemMeta();
    headMeta.setOwningPlayer(player);
    headMeta.setDisplayName(ChatColor.YELLOW + player.getName());

    // Add stats and class information
    List<String> lore = new ArrayList<>();
    lore.add("");
    lore.add(ChatColor.GOLD + "Class: " + ChatColor.WHITE + className);
    lore.add("");
    lore.add(ChatColor.AQUA + "Health: " + ChatColor.WHITE + maxHealth);
    lore.add(ChatColor.AQUA + "Mana: " + ChatColor.WHITE + maxMana);
    lore.add(ChatColor.AQUA + "Armor: " + ChatColor.WHITE + armor);
    lore.add(ChatColor.AQUA + "Armor Toughness: " + ChatColor.WHITE + armorToughness);
    lore.add("");
    lore.add(ChatColor.GREEN + "Experience: " + ChatColor.WHITE + experience + " out of 1000");
    lore.add(ChatColor.GREEN + "Level: " + ChatColor.WHITE + level + " out of " + maxLevel);

    headMeta.setLore(lore);
    playerHead.setItemMeta(headMeta);

    // Add player's head to the center slot (slot 4)
    inventory.setItem(4, playerHead);

    
    // Open the inventory for the player
    player.openInventory(inventory);

    return true;

  }
}
