package pine.toast.legendsreborn.utils.AuctionHouse;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class Categories implements Listener {

  @EventHandler
  public void onToolsCategoryInventoryClick(InventoryClickEvent event) {
    InventoryView invView = event.getView();
    Inventory clickedInventory = event.getClickedInventory();
    if (clickedInventory == null) {
      return;
    }

    String inventoryTitle = invView.getTitle();
    if (inventoryTitle.equals(ChatColor.YELLOW + "Tools Category")) {
      event.setCancelled(true);

      // TODO: Handle tools category inventory clicks
    }
  }

  @EventHandler
  public void onArmorCategoryInventoryClick(InventoryClickEvent event) {
    InventoryView invView = event.getView();
    Inventory clickedInventory = event.getClickedInventory();
    if (clickedInventory == null) {
      return;
    }

    String inventoryTitle = invView.getTitle();
    if (inventoryTitle.equals(ChatColor.AQUA + "Armor Category")) {
      event.setCancelled(true);

      // TODO: Handle armor category inventory clicks
    }
  }

  @EventHandler
  public void onMiscCategoryInventoryClick(InventoryClickEvent event) {
    InventoryView invView = event.getView();
    Inventory clickedInventory = event.getClickedInventory();
    if (clickedInventory == null) {
      return;
    }

    String inventoryTitle = invView.getTitle();
    if (inventoryTitle.equals(ChatColor.LIGHT_PURPLE + "MISC Category")) {
      event.setCancelled(true);

      // TODO: Handle MISC category inventory clicks
    }
  }

  @EventHandler
  public void onBlocksCategoryInventoryClick(InventoryClickEvent event) {
    InventoryView invView = event.getView();
    Inventory clickedInventory = event.getClickedInventory();
    if (clickedInventory == null) {
      return;
    }

    String inventoryTitle = invView.getTitle();
    if (inventoryTitle.equals(ChatColor.BLUE + "Blocks Category")) {
      event.setCancelled(true);

      // TODO: Handle blocks category inventory clicks
    }
  }

  @EventHandler
  public void onWeaponsCategoryInventoryClick(InventoryClickEvent event) {
    InventoryView invView = event.getView();
    Inventory clickedInventory = event.getClickedInventory();
    if (clickedInventory == null) {
      return;
    }

    String inventoryTitle = invView.getTitle();
    if (inventoryTitle.equals(ChatColor.RED + "Weapons Category")) {
      event.setCancelled(true);

      // TODO: Handle weapons category inventory clicks
    }
  }

  @EventHandler
  public void onServerCategoryInventoryClick(InventoryClickEvent event) {
    InventoryView invView = event.getView();
    Inventory clickedInventory = event.getClickedInventory();
    if (clickedInventory == null) {
      return;
    }

    String inventoryTitle = invView.getTitle();
    if (inventoryTitle.equals(ChatColor.GOLD + "Server Category")) {
      event.setCancelled(true);

      // TODO: Handle server category inventory clicks
    }
  }

}