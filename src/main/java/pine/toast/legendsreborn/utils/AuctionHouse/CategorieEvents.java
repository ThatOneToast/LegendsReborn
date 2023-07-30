package pine.toast.legendsreborn.utils.AuctionHouse;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import pine.toast.legendsreborn.utils.EconomySystem.EconomyManager;
import pine.toast.legendsreborn.utils.Keys;

import java.util.Optional;

public class CategorieEvents implements Listener {

  @SuppressWarnings("ConstantConditions")
  @EventHandler
  public void onCategoryInventoryClick(InventoryClickEvent event) {
    InventoryView invView = event.getView();
    Inventory clickedInventory = event.getClickedInventory();

    if (clickedInventory == null) {
      return;
    }

    String inventoryTitle = invView.getTitle();
    String[] categories = {
            ChatColor.GOLD + "Server Category",
            ChatColor.BLUE + "Blocks Category",
            ChatColor.AQUA + "Armor Category",
            ChatColor.RED + "Weapons Category",
            ChatColor.LIGHT_PURPLE + "Misc Category",
            ChatColor.YELLOW + "Tools Category"
    };

    for (String category : categories) {
      if (inventoryTitle.equals(category)) {
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();

        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null) return;
        ItemMeta itemMeta = clickedItem.getItemMeta();

        PersistentDataContainer itemData = itemMeta.getPersistentDataContainer();

        double cost = itemData.get(Keys.cost, PersistentDataType.DOUBLE);
        String seller = itemData.get(Keys.seller, PersistentDataType.STRING);

        handleCategoryInventoryClick(player, seller, cost, category, clickedItem);
        break;
      }
    }
  }

  private void handleCategoryInventoryClick(Player player, String seller, double cost, String category, ItemStack clickedItem) {
    if (player == null || category == null || clickedItem == null) return;

    if (seller == null) {
      player.sendMessage(ChatColor.DARK_RED + "Please contact a Dev, Something serious has gone wrong. ");
      return;
    }

    Player sellerUUID = Bukkit.getPlayer(seller);

    double playerMoney = EconomyManager.getPlayerBalance(player.getUniqueId());

    if (playerMoney < cost) {
      player.sendMessage(ChatColor.RED + "You do not have enough money to purchase this.");
    } else {

      // Pay the seller the specified amount
      EconomyManager.pay(player.getUniqueId(), sellerUUID.getUniqueId(), cost);

      // Remove the item from the category inventory and optionally return it to the player
      Optional<ItemStack> removedItem = AuctionHouseManager.removeItemFromCategory(clickedItem, category.toLowerCase(), true);
      // Give the removed item back to the player
      removedItem.ifPresent(itemStack -> player.getInventory().addItem(itemStack));
    }
  }


}