package pine.toast.legendsreborn.utils.AuctionHouse;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class AuctionHouseEvents implements Listener {


  @EventHandler
  public void onInventoryClick(InventoryClickEvent event) {
    InventoryView invView = event.getView();
    Inventory clickedInventory = event.getClickedInventory();
    if (clickedInventory == null) {
      return;
    }

    String inventoryTitle = invView.getTitle();
    if (inventoryTitle.equals(ChatColor.GOLD + "Auction House")) {
      event.setCancelled(true);

      ItemStack clickedItem = event.getCurrentItem();
      if (clickedItem == null || clickedItem.getType() == Material.AIR) {
        return;
      }

      Player player = (Player) event.getWhoClicked();
      int clickedSlot = event.getSlot();

      switch (clickedSlot) {
        case 0 -> {
          // Server category
          Inventory serverCategoryInventory = AuctionHouseManager.createServerCategoryInventory();
          player.openInventory(serverCategoryInventory);
        }
        case 2 -> {
          // Blocks category
          Inventory blocksCategoryInventory = AuctionHouseManager.createBlocksCategoryInventory();
          player.openInventory(blocksCategoryInventory);
        }
        case 3 -> {
          // Weapons category
          Inventory weaponsCategoryInventory = AuctionHouseManager.createWeaponsCategoryInventory();
          player.openInventory(weaponsCategoryInventory);
        }
        case 4 -> {
          // Tools category
          Inventory toolsCategoryInventory = AuctionHouseManager.createToolsCategoryInventory();
          player.openInventory(toolsCategoryInventory);
        }
        case 5 -> {
          // Armor category
          Inventory armorCategoryInventory = AuctionHouseManager.createArmorCategoryInventory();
          player.openInventory(armorCategoryInventory);
        }
        case 6 -> {
          // MISC category
          Inventory miscCategoryInventory = AuctionHouseManager.createMiscCategoryInventory();
          player.openInventory(miscCategoryInventory);
        }
        default -> player.sendMessage(ChatColor.RED + "An error has occured. Please contact a dev.");
      }

    }
  }


}