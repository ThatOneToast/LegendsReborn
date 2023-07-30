package pine.toast.legendsreborn.utils.ItemManager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemManager {

  public static ItemStack createCustomItem(Material material, String displayName, String[] lore, Map<Enchantment, Integer> enchantments, Boolean hideEnchats, int customModelData, NamespacedKey key) {
    ItemStack item = new ItemStack(material);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(displayName);

    meta.setCustomModelData(customModelData);

    if (hideEnchats) {
      meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    }

    PersistentDataContainer data = meta.getPersistentDataContainer();
    data.set(key, PersistentDataType.STRING, key.toString());

    if (lore != null && lore.length > 0) {
      List<String> formattedLore = new ArrayList<>(Arrays.asList(lore));
      meta.setLore(formattedLore);
    }

    if (enchantments != null && !enchantments.isEmpty()) {
      for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
        meta.addEnchant(entry.getKey(), entry.getValue(), true);
      }
    }

    item.setItemMeta(meta);
    return item;
  }


}