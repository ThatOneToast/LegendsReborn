package pine.toast.legendsreborn.utils.ClassesClass.Mage.Robes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import pine.toast.legendsreborn.utils.Keys;
import pine.toast.legendsreborn.utils.LevelSystem.LevelManager;

import java.util.Arrays;

public class SorcerersEmbrace {

  public static ItemStack createEmbraceItem(Player player) {
    ItemStack embrace = new ItemStack(Material.LEATHER_CHESTPLATE);
    if (embrace.getItemMeta() == null) return new ItemStack(Material.PAPER);
    ItemMeta embraceMeta = embrace.getItemMeta();


    PersistentDataContainer data = embraceMeta.getPersistentDataContainer();

    embraceMeta.setUnbreakable(true);

    int level = LevelManager.getLevel(player);
    double mana = level * 10;

    data.set(Keys.embrace, PersistentDataType.DOUBLE, mana);


    // Customize the item's display name and lore
    embraceMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Robes of Mystical Resonance");
    embraceMeta.setLore(Arrays.asList(
            ChatColor.GRAY + "A legendary garment woven from pure magic.",
            ChatColor.GRAY + "It resonates with the wearer's essence,",
            ChatColor.GRAY + "increasing their mana by " + ChatColor.AQUA + mana,
            ChatColor.GRAY + "Harness its power and transcend the arcane limits."
    ));

    embrace.setItemMeta(embraceMeta);
    return embrace;
  }
}