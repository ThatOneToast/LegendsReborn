package pine.toast.legendsreborn.utils.AuctionHouse;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import pine.toast.legendsreborn.utils.Keys;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ConstantConditions")
public class AuctionHouseManager {

  private static File dataFile;
  private static FileConfiguration dataConfig;

  public static void createAuctionHouseDataManager(Plugin plugin) {

    // Get the data folder and create it if it doesn't exist
    File dataFolder = plugin.getDataFolder();
    if (!dataFolder.exists()) {
      dataFolder.mkdirs();
    }

    // Create or load the data file
    dataFile = new File(dataFolder, "auctionhouse.dat");
    if (!dataFile.exists()) {
      // File doesn't exist, create a new one
      try {
        dataFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // Load the data configuration from the file
    dataConfig = YamlConfiguration.loadConfiguration(dataFile);
  }


  public static void saveInventory(String category, Inventory inventory) {
    ConfigurationSection categorySection = dataConfig.createSection(category);

    categorySection.set("size", inventory.getSize()); // Save the inventory size

    for (int i = 0; i < inventory.getSize(); i++) {
      ItemStack item = inventory.getItem(i);
      if (item != null && item.getType() != Material.AIR) {
        String itemKey = "item_" + i;

        categorySection.set(itemKey + ".material", item.getType().name());
        categorySection.set(itemKey + ".amount", item.getAmount());
        categorySection.set(itemKey + ".data", item.getDurability());
        categorySection.set(itemKey + ".meta", serializeItemMeta(item.getItemMeta()));
      } else {
        categorySection.set("item_" + i, null);
      }
    }

    saveDataConfig();
  }


  public static Inventory loadInventory(String category) {
    ConfigurationSection categorySection = dataConfig.getConfigurationSection(category);
    if (categorySection == null) {
      // Category doesn't exist, create a new inventory
      int inventorySize = 54; // Adjust the size as per your requirements
      Inventory inventory = Bukkit.createInventory(null, inventorySize, ChatColor.GOLD + "Auction House");
      saveInventory(category, inventory); // Save the new inventory to the configuration
      return inventory;
    }

    int inventorySize = categorySection.getInt("size", 9); // Get the inventory size from the configuration, defaulting to 9 if not specified
    Inventory inventory = Bukkit.createInventory(null, inventorySize, ChatColor.GOLD + "Auction House");

    for (String key : categorySection.getKeys(false)) {
      if (key.startsWith("item_")) {
        int slot = Integer.parseInt(key.split("_")[1]);

        ConfigurationSection itemSection = categorySection.getConfigurationSection(key);
        String materialName = itemSection.getString("material");
        int amount = itemSection.getInt("amount");
        short durability = (short) itemSection.getInt("data");
        ItemMeta itemMeta = deserializeItemMeta(itemSection.getString("meta"));

        ItemStack item = new ItemStack(Material.valueOf(materialName), amount, durability);
        item.setItemMeta(itemMeta);
        inventory.setItem(slot, item);
      }
    }

    return inventory;
  }


  private static void saveDataConfig() {
    try {
      dataConfig.save(dataFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static String serializeItemMeta(ItemMeta itemMeta) {
    FileConfiguration metaConfig = new YamlConfiguration();
    metaConfig.set("item", itemMeta);
    return metaConfig.saveToString();
  }


  private static ItemMeta deserializeItemMeta(String serializedMeta) {
    FileConfiguration metaConfig = new YamlConfiguration();
    try {
      metaConfig.loadFromString(serializedMeta);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return (ItemMeta) metaConfig.get("item");
  }


  public static Inventory AuctionMainMenu() {
    Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Auction House");

    // Add items for each category

    // Server category
    ItemStack serverItem = new ItemStack(Material.COMPASS);
    ItemMeta serverMeta = serverItem.getItemMeta();
    serverMeta.setDisplayName(ChatColor.GREEN + "Server");
    serverItem.setItemMeta(serverMeta);
    inv.setItem(0, serverItem);

    ItemStack blocksItem = new ItemStack(Material.STONE);
    ItemMeta blocksMeta = blocksItem.getItemMeta();
    blocksMeta.setDisplayName(ChatColor.BLUE + "Blocks");
    blocksItem.setItemMeta(blocksMeta);
    inv.setItem(2, blocksItem);

    ItemStack weaponsItem = new ItemStack(Material.DIAMOND_SWORD);
    ItemMeta weaponsMeta = weaponsItem.getItemMeta();
    weaponsMeta.setDisplayName(ChatColor.RED + "Weapons");
    weaponsItem.setItemMeta(weaponsMeta);
    inv.setItem(3, weaponsItem);

    ItemStack toolsItem = new ItemStack(Material.IRON_PICKAXE);
    ItemMeta toolsMeta = toolsItem.getItemMeta();
    toolsMeta.setDisplayName(ChatColor.YELLOW + "Tools");
    toolsItem.setItemMeta(toolsMeta);
    inv.setItem(4, toolsItem);

    ItemStack armorItem = new ItemStack(Material.DIAMOND_CHESTPLATE);
    ItemMeta armorMeta = armorItem.getItemMeta();
    armorMeta.setDisplayName(ChatColor.AQUA + "Armor");
    armorItem.setItemMeta(armorMeta);
    inv.setItem(5, armorItem);

    ItemStack miscItem = new ItemStack(Material.CHEST);
    ItemMeta miscMeta = miscItem.getItemMeta();
    miscMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "MISC");
    miscItem.setItemMeta(miscMeta);
    inv.setItem(6, miscItem);


    return inv;
  }


  public static Inventory createCategoryInventory(String categoryName, ChatColor color, int size) {
    String category = categoryName.toLowerCase();

    // Load the inventory from the file
    Inventory inventory = loadInventory(category);

    // Inventory doesn't exist in the file, create a new one
    String title = color + categoryName + " Category";
    inventory = Bukkit.createInventory(null, size, title);

    // Save the inventory to the file
    saveInventory(category, inventory);

    return inventory;
  }


  public static void addItemToCategory(ItemStack item, String categoryName, double price, Player player) {
    // Get the corresponding category inventory based on the category name
    Inventory categoryInventory = loadInventory(categoryName);

    // Update the lore of the item to include the seller and price information
    ItemMeta itemMeta = item.getItemMeta();
    List<String> lore = itemMeta.getLore();
    if (lore == null) {
      lore = new ArrayList<>();
    }

    PersistentDataContainer itemData = itemMeta.getPersistentDataContainer();
    itemData.set(Keys.cost, PersistentDataType.DOUBLE, price);
    itemData.set(Keys.seller, PersistentDataType.STRING, player.getName());


    lore.add(ChatColor.GOLD + "Seller: " + ChatColor.GRAY + player.getName());
    lore.add(ChatColor.GOLD + "Price: " + ChatColor.WHITE + "$" + price);
    itemMeta.setLore(lore);
    item.setItemMeta(itemMeta);

    // Add the item to the category inventory
    categoryInventory.addItem(item);


    // Save the updated category inventory
    saveInventory(categoryName, categoryInventory);
  }

  public static Optional<ItemStack> removeItemFromCategory(ItemStack item, String categoryName, boolean returnItem) {
    // Get the corresponding category inventory based on the category name
    Inventory categoryInventory = loadInventory(categoryName);

    // Check if the item exists in the category inventory
    if (categoryInventory.contains(item)) {
      // Remove the item from the category inventory
      categoryInventory.removeItem(item);

      // Save the updated category inventory
      saveInventory(categoryName, categoryInventory);

      // Return the removed item if specified
      if (returnItem) {
        return Optional.of(item);
      }
    }

    // Item not found in the category inventory or returnItem is false
    return Optional.empty();
  }


}