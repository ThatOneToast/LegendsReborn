package pine.toast.legendsreborn.utils.ClassesClass.Mage;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import pine.toast.legendsreborn.utils.ItemManager.ItemManager;

import java.util.HashMap;
import java.util.Map;

public class MageItems {


  public static ItemStack TomeOfFrost() {
    Map<Enchantment, Integer> enchantments = new HashMap<>();

    enchantments.put(Enchantment.DAMAGE_ALL, 1);

    return ItemManager.createCustomItem(Material.BOOK, ChatColor.AQUA + "Tome of Frost",
            new String[]{
                    ChatColor.GRAY + "A mystical book containing icy spells.",
                    ChatColor.GRAY + "This tome has the power to freeze your foes.",
                    ChatColor.GRAY + "Cost: 75"
            },
            enchantments, true, 6);
  }

  public static ItemStack AetherWalker() {
    Map<Enchantment, Integer> ench = new HashMap<>();

    ench.put(Enchantment.ARROW_DAMAGE, 1);

    return ItemManager.createCustomItem(Material.STICK, ChatColor.LIGHT_PURPLE + "Aether Walker",
            new String[]{
                    ChatColor.GRAY + "This wand transports you +10 blocks",
                    ChatColor.GRAY + "Cost: 60 Mana"
            },
            ench, true, 7);
  }

  public static ItemStack StarFire() {
    Map<Enchantment, Integer> ench = null;

    return ItemManager.createCustomItem(Material.STICK, ChatColor.DARK_GREEN + "StarFire",
            new String[]{
                    ChatColor.GRAY + "Strike up to 1 enemy with a bolt of Lightning",
                    ChatColor.GRAY + "Right click - Lightning Bolt",
                    ChatColor.GRAY + "Mana Cost: 10"
            },
            ench, true, 3);
  }

  public static ItemStack WoH() {
    Map<Enchantment, Integer> ench = null;

    return ItemManager.createCustomItem(Material.STICK, ChatColor.DARK_BLUE + " Wand of WoH",
            new String[]{
                    ChatColor.GRAY + "A powerful wand that can summon explosions",
                    ChatColor.GRAY + "Right click - Explosive Blast",
                    ChatColor.GRAY + "Explosion Radius: 1",
                    ChatColor.GRAY + "Mana Cost: 50"
            },
            ench, true, 2);
  }

  public static ItemStack brokenWand() {
    Map<Enchantment, Integer> ench = null;

    return ItemManager.createCustomItem(Material.STICK, ChatColor.GRAY + "Broken Wand",
            new String[]{
                    ChatColor.GRAY + "A broken wand, it's useless.",
                    ChatColor.GRAY + "Right click - Magic Missile",
                    ChatColor.GRAY + "Damage: +5",
                    ChatColor.GRAY + "Mana Cost: 15"
            },
            ench, true, 1);
  }


}