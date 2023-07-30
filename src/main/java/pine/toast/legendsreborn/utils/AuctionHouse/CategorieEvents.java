package pine.toast.legendsreborn.utils.AuctionHouse;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
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
import java.util.UUID;

public class CategorieEvents implements Listener {

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

      Player player = (Player) event.getWhoClicked();

      ItemStack clickedItem = event.getCurrentItem();
      if (clickedItem == null) return;
      ItemMeta itemMeta = clickedItem.getItemMeta();

      PersistentDataContainer itemData = itemMeta.getPersistentDataContainer();

      double cost = itemData.get(Keys.cost, PersistentDataType.DOUBLE);
      String seller = itemData.get(Keys.seller, PersistentDataType.STRING);


      // TODO: Handle server category inventory clicks
      if (event.getClick() == ClickType.LEFT) {

        Player SellerUUID = Bukkit.getPlayer(seller);

        double money = EconomyManager.getPlayerBalance(player.getUniqueId());

        if (money < cost) {
          player.sendMessage(ChatColor.RED + "You do not have enough money to purchase this.");
        } else return;

        // Pay the seller the specified amount
        EconomyManager.pay(player.getUniqueId(), SellerUUID.getUniqueId(), cost);

        // Remove the item from the server category inventory and optionally return it to the player
        Optional<ItemStack> removedItem = AuctionHouseManager.removeItemFromCategory(clickedItem, "server", true);
        if (removedItem.isPresent()) {
          // Give the removed item back to the player
          player.getInventory().addItem(removedItem.get());
        }
      }
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

      Player player = (Player) event.getWhoClicked();

      ItemStack clickedItem = event.getCurrentItem();
      if (clickedItem == null) return;
      ItemMeta itemMeta = clickedItem.getItemMeta();

      PersistentDataContainer itemData = itemMeta.getPersistentDataContainer();

      double cost = itemData.get(Keys.cost, PersistentDataType.DOUBLE);
      String seller = itemData.get(Keys.seller, PersistentDataType.STRING);

      // TODO: Handle blocks category inventory clicks
      if (event.getClick() == ClickType.LEFT) {

        Player SellerUUID = Bukkit.getPlayer(seller);

        double money = EconomyManager.getPlayerBalance(player.getUniqueId());

        if (money < cost) {
          player.sendMessage(ChatColor.RED + "You do not have enough money to purchase this.");
        } else return;

        // Pay the seller the specified amount
        EconomyManager.pay(player.getUniqueId(), SellerUUID.getUniqueId(), cost);

        // Remove the item from the blocks category inventory and optionally return it to the player
        Optional<ItemStack> removedItem = AuctionHouseManager.removeItemFromCategory(clickedItem, "blocks", true);
        if (removedItem.isPresent()) {
          // Give the removed item back to the player
          player.getInventory().addItem(removedItem.get());
        }
      }
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

      Player player = (Player) event.getWhoClicked();

      ItemStack clickedItem = event.getCurrentItem();
      if (clickedItem == null) return;
      ItemMeta itemMeta = clickedItem.getItemMeta();

      PersistentDataContainer itemData = itemMeta.getPersistentDataContainer();

      double cost = itemData.get(Keys.cost, PersistentDataType.DOUBLE);
      String seller = itemData.get(Keys.seller, PersistentDataType.STRING);

      // TODO: Handle armor category inventory clicks
      if (event.getClick() == ClickType.LEFT) {

        Player SellerUUID = Bukkit.getPlayer(seller);

        double money = EconomyManager.getPlayerBalance(player.getUniqueId());

        if (money < cost) {
          player.sendMessage(ChatColor.RED + "You do not have enough money to purchase this.");
        } else return;

        // Pay the seller the specified amount
        EconomyManager.pay(player.getUniqueId(), SellerUUID.getUniqueId(), cost);

        // Remove the item from the armor category inventory and optionally return it to the player
        Optional<ItemStack> removedItem = AuctionHouseManager.removeItemFromCategory(clickedItem, "armor", true);
        if (removedItem.isPresent()) {
          // Give the removed item back to the player
          player.getInventory().addItem(removedItem.get());
        }
      }
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

      Player player = (Player) event.getWhoClicked();

      ItemStack clickedItem = event.getCurrentItem();
      if (clickedItem == null) return;
      ItemMeta itemMeta = clickedItem.getItemMeta();

      PersistentDataContainer itemData = itemMeta.getPersistentDataContainer();

      double cost = itemData.get(Keys.cost, PersistentDataType.DOUBLE);
      String seller = itemData.get(Keys.seller, PersistentDataType.STRING);

      // TODO: Handle weapons category inventory clicks
      if (event.getClick() == ClickType.LEFT) {

        Player SellerUUID = Bukkit.getPlayer(seller);

        double money = EconomyManager.getPlayerBalance(player.getUniqueId());

        if (money < cost) {
          player.sendMessage(ChatColor.RED + "You do not have enough money to purchase this.");
        } else return;

        // Pay the seller the specified amount
        EconomyManager.pay(player.getUniqueId(), SellerUUID.getUniqueId(), cost);

        // Remove the item from the weapons category inventory and optionally return it to the player
        Optional<ItemStack> removedItem = AuctionHouseManager.removeItemFromCategory(clickedItem, "weapons", true);
        // Give the removed item back to the player
        removedItem.ifPresent(itemStack -> player.getInventory().addItem(itemStack));
      }
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
    if (inventoryTitle.equals(ChatColor.LIGHT_PURPLE + "Misc Category")) {
      event.setCancelled(true);

      Player player = (Player) event.getWhoClicked();

      ItemStack clickedItem = event.getCurrentItem();
      if (clickedItem == null) return;
      ItemMeta itemMeta = clickedItem.getItemMeta();

      PersistentDataContainer itemData = itemMeta.getPersistentDataContainer();

      double cost = itemData.get(Keys.cost, PersistentDataType.DOUBLE);
      String seller = itemData.get(Keys.seller, PersistentDataType.STRING);

      // TODO: Handle misc category inventory clicks
      if (event.getClick() == ClickType.LEFT) {

        Player SellerUUID = Bukkit.getPlayer(seller);

        double money = EconomyManager.getPlayerBalance(player.getUniqueId());

        if (money < cost) {
          player.sendMessage(ChatColor.RED + "You do not have enough money to purchase this.");
        } else return;

        // Pay the seller the specified amount
        EconomyManager.pay(player.getUniqueId(), SellerUUID.getUniqueId(), cost);

        // Remove the item from the misc category inventory and optionally return it to the player
        Optional<ItemStack> removedItem = AuctionHouseManager.removeItemFromCategory(clickedItem, "misc", true);
        if (removedItem.isPresent()) {
          // Give the removed item back to the player
          player.getInventory().addItem(removedItem.get());
        }
      }
    }
  }

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

      Player player = (Player) event.getWhoClicked();

      ItemStack clickedItem = event.getCurrentItem();
      if (clickedItem == null) return;
      ItemMeta itemMeta = clickedItem.getItemMeta();

      PersistentDataContainer itemData = itemMeta.getPersistentDataContainer();

      double cost = itemData.get(Keys.cost, PersistentDataType.DOUBLE);
      String seller = itemData.get(Keys.seller, PersistentDataType.STRING);

      // TODO: Handle tools category inventory clicks
      if (event.getClick() == ClickType.LEFT) {

        if(seller == null) player.sendMessage(ChatColor.DARK_RED + "Please contact a Dev, Something serious has gone wrong. ");
        UUID SellerUUID = Bukkit.getPlayer(seller).getUniqueId();
        double money = EconomyManager.getPlayerBalance(player.getUniqueId());

        if (money < cost) {
          player.sendMessage(ChatColor.RED + "You do not have enough money to purchase this.");
        } else return;

        // Pay the seller the specified amount
        EconomyManager.pay(player.getUniqueId(), SellerUUID, cost);

        // Remove the item from the tools category inventory and optionally return it to the player
        Optional<ItemStack> removedItem = AuctionHouseManager.removeItemFromCategory(clickedItem, "tools", true);
        if (removedItem.isPresent()) {
          // Give the removed item back to the player
          player.getInventory().addItem(removedItem.get());
        }
      }
    }
  }


}