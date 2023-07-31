package pine.toast.legendsreborn.utils.ClassesClass.Barbarian;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import pine.toast.legendsreborn.utils.ItemManager.ItemManager;
import pine.toast.legendsreborn.utils.Keys;

import java.util.Map;

public class BarbarianItems {


  public static ItemStack DoubleAxe() {
    Map<Enchantment, Integer> enchantments = null;

    ItemStack doubleAxe = ItemManager.createCustomItem(Material.IRON_AXE, ChatColor.WHITE + " Double Axe ",
            new String[]{
                    ChatColor.GRAY + "A double sided axe that can deal damage to multiple enemies",
                    ChatColor.GRAY + "Right click - Double Strike",
                    ChatColor.GRAY + "Damage: +10",
                    ChatColor.GRAY + "Mana Cost: 20"
            },
            enchantments, false, 5, Keys.DoubleAxe);


    return doubleAxe;

  }

}

