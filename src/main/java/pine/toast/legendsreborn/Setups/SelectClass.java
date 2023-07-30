package pine.toast.legendsreborn.Setups;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import pine.toast.legendsreborn.utils.Classes.Barbarian;
import pine.toast.legendsreborn.utils.Classes.Knight;
import pine.toast.legendsreborn.utils.Classes.Mage;
import pine.toast.legendsreborn.utils.Classes.Paid.*;
import pine.toast.legendsreborn.utils.Classes.RPGClass;
import pine.toast.legendsreborn.utils.ClassesClass.Barbarian.BarbarianItems;
import pine.toast.legendsreborn.utils.ClassesClass.Mage.MageItems;
import pine.toast.legendsreborn.utils.ClassesClass.Mage.Robes.SorcerersEmbrace;
import pine.toast.legendsreborn.utils.Keys;
import pine.toast.legendsreborn.utils.LevelSystem.LevelManager;
import pine.toast.mana.ManaManager;

import java.util.ArrayList;
import java.util.List;


public class SelectClass implements Listener {

  @SuppressWarnings("ALL")
  public static void openClassSelectGUI(Player player) {
    Inventory gui = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&5ClassSelect"));

    // Soacer
    ItemStack spacer = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    ItemMeta spacerMeta = spacer.getItemMeta();
    spacerMeta.setDisplayName(" ");
    spacer.setItemMeta(spacerMeta);


    // Create a barbarian item with the lore being the description of the class.
    ItemStack barbarian = new ItemStack(Material.IRON_AXE);
    ItemMeta barbarianMeta = barbarian.getItemMeta();
    barbarianMeta.setDisplayName(ChatColor.DARK_RED + "Barbarian");

    barbarianMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

    List<String> lore = new ArrayList<>();
    lore.add(ChatColor.GRAY + "A strong warrior who uses brute force to defeat his enemies.");
    lore.add(ChatColor.GRAY + "Barbarians are known for their high health and damage.");
    lore.add(ChatColor.GRAY + "They are also known for their lack of mana and magic.");
    lore.add(ChatColor.GRAY + "Barbarians are a great class for beginners.");

    lore.add(" ");
    lore.add(ChatColor.GRAY + "Exhaustion is how quickly your hunger bar depletes. ( Lower is better )");
    lore.add(ChatColor.GRAY + "Defense is your armor bar ( Higher is better )");
    lore.add(" ");

    lore.add(ChatColor.GREEN + "Health: 150");
    lore.add(ChatColor.GREEN + "Mana: 20");
    lore.add(ChatColor.GREEN + "Mana Regen: +0.1");
    lore.add(ChatColor.GREEN + "Damage: +6");
    lore.add(ChatColor.GREEN + "Defense: +5");
    lore.add(ChatColor.GREEN + "Exhaustion: +3");


    barbarianMeta.setLore(lore);
    barbarian.setItemMeta(barbarianMeta);

    // Create a mage item with the lore being the description of the class.
    ItemStack mage = new ItemStack(Material.BLAZE_ROD);
    ItemMeta mageMeta = mage.getItemMeta();
    mageMeta.setDisplayName(ChatColor.DARK_PURPLE + "Mage");

    mageMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

    List<String> lore2 = new ArrayList<>();
    lore2.add(ChatColor.GRAY + "A powerful wizard who uses magic to defeat his enemies.");
    lore2.add(ChatColor.GRAY + "Mages are known for their high mana and magic.");
    lore2.add(ChatColor.GRAY + "They are also known for their lack of health and defense.");
    lore2.add(ChatColor.GRAY + "Mages are a great class for experienced players.");

    lore2.add(" ");
    lore2.add(ChatColor.GRAY + "Exhaustion is how quickly your hunger bar depletes. ( Lower is better )");
    lore2.add(ChatColor.GRAY + "Defense is your armor bar ( Higher is better )");
    lore2.add(" ");

    lore2.add(ChatColor.GREEN + "Health: 50");
    lore2.add(ChatColor.GREEN + "Mana: 100");
    lore2.add(ChatColor.GREEN + "Mana Regen: +5");
    lore2.add(ChatColor.GREEN + "Damage: +1");
    lore2.add(ChatColor.GREEN + "Defense: -4");
    lore2.add(ChatColor.GREEN + "Exhaustion: +2");

    mageMeta.setLore(lore2);
    mage.setItemMeta(mageMeta);

    // Create a knight item with the lore being the description of the class.
    ItemStack knight = new ItemStack(Material.IRON_SWORD);
    ItemMeta knightMeta = knight.getItemMeta();
    knightMeta.setDisplayName(ChatColor.DARK_BLUE + "Knight");

    knightMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

    List<String> lore3 = new ArrayList<>();
    lore3.add(ChatColor.GRAY + "A strong warrior who uses a sword to defeat his enemies.");
    lore3.add(ChatColor.GRAY + "Knights are known for their high health and defense.");
    lore3.add(ChatColor.GRAY + "They are also known for their lack of mana and magic.");
    lore3.add(ChatColor.GRAY + "Knights are a great class for beginners.");

    lore3.add(" ");
    lore3.add(ChatColor.GRAY + "Exhaustion is how quickly your hunger bar depletes. ( Lower is better )");
    lore3.add(ChatColor.GRAY + "Defense is your armor bar ( Higher is better )");
    lore3.add(" ");

    lore3.add(ChatColor.GREEN + "Health: 75");
    lore3.add(ChatColor.GREEN + "Mana: 20");
    lore3.add(ChatColor.GREEN + "Mana Regen: +0.3");
    lore3.add(ChatColor.GREEN + "Damage: +3");
    lore3.add(ChatColor.GREEN + "Defense: +3");
    lore3.add(ChatColor.GREEN + "Exhaustion: +1");

    knightMeta.setLore(lore3);
    knight.setItemMeta(knightMeta);


        /*
            - Paid Classes -
                Assassin
                Archer
                Necromancer
                Paladin
                Priest
                Shaman
                Warlock
         */

    ItemStack Warlock = new ItemStack(Material.NETHERITE_SWORD);
    ItemMeta WarlockMeta = Warlock.getItemMeta();
    WarlockMeta.setDisplayName(ChatColor.DARK_RED + "Warlock");

    WarlockMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

    List<String> lore10 = new ArrayList<>();
    lore10.add(ChatColor.GRAY + "A strong warrior who uses brute force to defeat his enemies.");
    lore10.add(ChatColor.GRAY + "Warlocks are known for their high health and damage.");
    lore10.add(ChatColor.GRAY + "They are also known for their lack of mana and magic.");
    lore10.add(ChatColor.GRAY + "Warlocks are a great class for beginners.");

    lore10.add(" ");
    lore10.add(ChatColor.GRAY + "Exhaustion is how quickly your hunger bar depletes. ( Lower is better )");
    lore10.add(ChatColor.GRAY + "Defense is your armor bar ( Higher is better )");
    lore10.add(" ");

    lore10.add(ChatColor.GREEN + "Health: 50");
    lore10.add(ChatColor.GREEN + "Mana: 20");
    lore10.add(ChatColor.GREEN + "Mana Regen: +0.3");
    lore10.add(ChatColor.GREEN + "Damage: +3");
    lore10.add(ChatColor.GREEN + "Defense: +3");
    lore10.add(ChatColor.GREEN + "Exhaustion: +1");

    WarlockMeta.setLore(lore10);
    Warlock.setItemMeta(WarlockMeta);


    ItemStack Shamon = new ItemStack(Material.TOTEM_OF_UNDYING);
    ItemMeta ShamonMeta = Shamon.getItemMeta();
    ShamonMeta.setDisplayName(ChatColor.DARK_GREEN + "Shaman");

    ShamonMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

    List<String> lore9 = new ArrayList<>();
    lore9.add(ChatColor.GRAY + "A powerful wizard who uses magic to defeat his enemies.");
    lore9.add(ChatColor.GRAY + "Shamons are known for their high mana and magic.");
    lore9.add(ChatColor.GRAY + "Shamons have Large amounts of health, low defensive abilities and low damage.");
    lore9.add(ChatColor.GRAY + "Shamons have I regenative powers.");
    lore9.add(ChatColor.GRAY + "Shamons are a great class for experienced players.");

    lore9.add(" ");
    lore9.add(ChatColor.GRAY + "Exhaustion is how quickly your hunger bar depletes. ( Lower is better )");
    lore9.add(ChatColor.GRAY + "Defense is your armor bar ( Higher is better )");
    lore9.add(" ");

    lore9.add(ChatColor.GREEN + "Health: 175");
    lore9.add(ChatColor.GREEN + "Mana: 200");
    lore9.add(ChatColor.GREEN + "Mana Regen: +20");
    lore9.add(ChatColor.GREEN + "Damage: +0");
    lore9.add(ChatColor.GREEN + "Defense: -4");
    lore9.add(ChatColor.GREEN + "Exhaustion: +0.5");

    ShamonMeta.setLore(lore9);
    Shamon.setItemMeta(ShamonMeta);


    ItemStack priest = new ItemStack(Material.BOOK);
    ItemMeta priestMeta = priest.getItemMeta();
    priestMeta.setDisplayName(ChatColor.WHITE + "Priest");

    priestMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

    List<String> lore8 = new ArrayList<>();
    lore8.add(ChatColor.GRAY + "A powerful wizard who uses magic to defeat his enemies.");
    lore8.add(ChatColor.GRAY + "Priests are known for their high mana and magic.");
    lore8.add(ChatColor.GRAY + "They are also known for their lack of health and defense.");
    lore8.add(ChatColor.GRAY + "Priests are a great class for experienced players.");
    lore8.add(ChatColor.GRAY + "Priests are avoided by hostile mobs.");
    lore8.add(ChatColor.GRAY + "Priests Can regenerate Mana really quickly.");

    lore8.add(" ");
    lore8.add(ChatColor.GRAY + "Exhaustion is how quickly your hunger bar depletes. ( Lower is better )");
    lore8.add(ChatColor.GRAY + "Defense is your armor bar ( Higher is better )");
    lore8.add(" ");

    lore8.add(ChatColor.GREEN + "Health: 20");
    lore8.add(ChatColor.GREEN + "Mana: 60");
    lore8.add(ChatColor.GREEN + "Mana Regen: +35");
    lore8.add(ChatColor.GREEN + "Damage: +0");
    lore8.add(ChatColor.GREEN + "Defense: -4");
    lore8.add(ChatColor.GREEN + "Exhaustion: +2");

    priestMeta.setLore(lore8);
    priest.setItemMeta(priestMeta);


    ItemStack Paladin = new ItemStack(Material.SHIELD);
    ItemMeta PaladinMeta = Paladin.getItemMeta();
    PaladinMeta.setDisplayName(ChatColor.GOLD + "Paladin");

    PaladinMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

    List<String> lore7 = new ArrayList<>();
    lore7.add(ChatColor.GRAY + "A strong warrior who uses a sword to defeat his enemies.");
    lore7.add(ChatColor.GRAY + "And a shield to protect himself.");
    lore7.add(ChatColor.GRAY + "Paladins are known for their high defense and low health.");
    lore7.add(ChatColor.GRAY + "High usage of mana, low max mana.");
    lore7.add(ChatColor.GRAY + "Paladins are a great class for the middle guys.");

    lore7.add(" ");
    lore7.add(ChatColor.GRAY + "Exhaustion is how quickly your hunger bar depletes. ( Lower is better )");
    lore7.add(ChatColor.GRAY + "Defense is your armor bar ( Higher is better )");
    lore7.add(" ");

    lore7.add(ChatColor.GREEN + "Health: 35");
    lore7.add(ChatColor.GREEN + "Mana: 35");
    lore7.add(ChatColor.GREEN + "Mana Regen: +2.5");
    lore7.add(ChatColor.GREEN + "Damage: +5");
    lore7.add(ChatColor.GREEN + "Defense: +6");
    lore7.add(ChatColor.GREEN + "Exhaustion: +3");

    PaladinMeta.setLore(lore7);
    Paladin.setItemMeta(PaladinMeta);


    ItemStack necormancer = new ItemStack(Material.WITHER_SKELETON_SKULL);
    ItemMeta necromancerMeta = necormancer.getItemMeta();
    necromancerMeta.setDisplayName(ChatColor.DARK_GRAY + "Necromancer");

    necromancerMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

    List<String> lore6 = new ArrayList<>();
    lore6.add(ChatColor.GRAY + "A powerful wizard who uses dark magic to defeat his enemies.");
    lore6.add(ChatColor.GRAY + "Necromancers are known for their high mana and magic.");
    lore6.add(ChatColor.GRAY + "They are also known for their lack of health and defense.");
    lore6.add(ChatColor.GRAY + "Necromancers are a great class for experienced players.");

    lore6.add(" ");
    lore6.add(ChatColor.GRAY + "Exhaustion is how quickly your hunger bar depletes. ( Lower is better )");
    lore6.add(ChatColor.GRAY + "Defense is your armor bar ( Higher is better )");
    lore6.add(" ");

    lore6.add(ChatColor.GREEN + "Health: 65");
    lore6.add(ChatColor.GREEN + "Mana: 175");
    lore6.add(ChatColor.GREEN + "Mana Regen: +15");
    lore6.add(ChatColor.GREEN + "Damage: +2");
    lore6.add(ChatColor.GREEN + "Defense: -5");
    lore6.add(ChatColor.GREEN + "Exhaustion: +0.75");

    necromancerMeta.setLore(lore6);
    necormancer.setItemMeta(necromancerMeta);


    ItemStack archer = new ItemStack(Material.BOW);
    ItemMeta ArcherMeta = archer.getItemMeta();
    ArcherMeta.setDisplayName(ChatColor.DARK_GREEN + "Archer");

    ArcherMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

    List<String> lore5 = new ArrayList<>();
    lore5.add(ChatColor.GRAY + "A skilled archer who uses a bow to defeat his enemies.");
    lore5.add(ChatColor.GRAY + "Archers are known for their high damage and evasion.");
    lore5.add(ChatColor.GRAY + "They specialize in quick and precise strikes.");
    lore5.add(ChatColor.GRAY + "Archers are a great class for experienced players.");

    lore5.add(" ");
    lore5.add(ChatColor.GRAY + "Exhaustion is how quickly your hunger bar depletes. ( Lower is better )");
    lore5.add(ChatColor.GRAY + "Defense is your armor bar ( Higher is better )");
    lore5.add(" ");

    lore5.add(ChatColor.GREEN + "Health: 20");
    lore5.add(ChatColor.GREEN + "Mana: 20");
    lore5.add(ChatColor.GREEN + "Mana Regen: +0.1");
    lore5.add(ChatColor.GREEN + "Damage: +2");
    lore5.add(ChatColor.GREEN + "Defense: +2");
    lore5.add(ChatColor.GREEN + "Exhaustion: +2");

    ArcherMeta.setLore(lore5);
    archer.setItemMeta(ArcherMeta);


    ItemStack assassin = new ItemStack(Material.IRON_SWORD);
    ItemMeta assassinMeta = assassin.getItemMeta();
    assassinMeta.setDisplayName(ChatColor.DARK_GRAY + "Assassin");

    assassinMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

    List<String> lore4 = new ArrayList<>();
    lore4.add(ChatColor.GRAY + "A stealthy and agile fighter who excels in surprise attacks.");
    lore4.add(ChatColor.GRAY + "Assassins are known for their high damage and evasion.");
    lore4.add(ChatColor.GRAY + "They specialize in quick and precise strikes.");
    lore4.add(ChatColor.GRAY + "Assassins are completely silent when walking and sprinting.");
    lore4.add(ChatColor.GRAY + "Assassins are a great class for experienced players.");

    lore4.add(" ");
    lore4.add(ChatColor.GRAY + "Exhaustion is how quickly your hunger bar depletes. ( Lower is better )");
    lore4.add(ChatColor.GRAY + "Defense is your armor bar ( Higher is better )");
    lore4.add(" ");

    lore4.add(ChatColor.GREEN + "Health: 40");
    lore4.add(ChatColor.GREEN + "Mana: 30");
    lore4.add(ChatColor.GREEN + "Mana Regen: +1.5");
    lore4.add(ChatColor.GREEN + "Speed: +33%");
    lore4.add(ChatColor.GREEN + "Damage: +4");
    lore4.add(ChatColor.GREEN + "Defense: +1");
    lore4.add(ChatColor.GREEN + "Exhaustion: +0.5");

    assassinMeta.setLore(lore4);
    assassin.setItemMeta(assassinMeta);


    PersistentDataContainer playerData = player.getPersistentDataContainer();
    Boolean isPaid = playerData.get(Keys.isPaid, PersistentDataType.BOOLEAN);


    if (isPaid) {
      Inventory paidGui = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&5 Premium Class Select"));

      // Line 1

      paidGui.addItem(barbarian);
      paidGui.addItem(mage);
      paidGui.addItem(knight);

      // Line 2


      // Line 3

      paidGui.setItem(18, assassin);
      paidGui.setItem(19, archer);
      paidGui.setItem(20, necormancer);
      paidGui.setItem(21, Paladin);
      paidGui.setItem(22, priest);
      paidGui.setItem(23, Shamon);
      paidGui.setItem(24, Warlock);
      player.openInventory(paidGui);

    } else {

      gui.addItem(barbarian);
      gui.addItem(mage);
      gui.addItem(knight);
      player.openInventory(gui);

    }


  }


  @EventHandler
  public void onInventoryClick(InventoryClickEvent event) {
    if (event.getInventory().getHolder() == null && event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&5ClassSelect"))) {
      event.setCancelled(true);
      event.setResult(Event.Result.DENY);

      Player player = (Player) event.getWhoClicked();

      ItemStack clickedItem = event.getCurrentItem();

      if (clickedItem == null) return;
      ItemMeta clickedItemMeta = clickedItem.getItemMeta();
      if (clickedItemMeta == null) return;
      String className = clickedItemMeta.getDisplayName();



            /*
             - Free Classes -
                Barbarian
                Mage
                Knight
             */
      if (className.equals(ChatColor.DARK_RED + "Barbarian")) {
        player.getPersistentDataContainer().set(Keys.selectedClass, PersistentDataType.STRING, "Barbarian");
        enforceClass(player);
      } else if (className.equals(ChatColor.DARK_PURPLE + "Mage")) {
        player.getPersistentDataContainer().set(Keys.selectedClass, PersistentDataType.STRING, "Mage");
        enforceClass(player);
      } else if (className.equals(ChatColor.DARK_BLUE + "Knight")) {
        player.getPersistentDataContainer().set(Keys.selectedClass, PersistentDataType.STRING, "Knight");
        enforceClass(player);
      }


    } else if (event.getInventory().getHolder() == null && event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&5 Premium Class Select"))) {

      event.setCancelled(true);
      event.setResult(Event.Result.DENY);

      Player player = (Player) event.getWhoClicked();

      ItemStack clickedItem = event.getCurrentItem();
      if (clickedItem == null) return;
      ItemMeta clickedItemMeta = clickedItem.getItemMeta();
      if (clickedItemMeta == null) return;
      String className = clickedItemMeta.getDisplayName();

            /*
             - Free Classes -
                Barbarian
                Mage
                Knight
             */
      if (className.equals(ChatColor.DARK_RED + "Barbarian")) {
        player.getPersistentDataContainer().set(Keys.selectedClass, PersistentDataType.STRING, "Barbarian");
        enforceClass(player);
      } else if (className.equals(ChatColor.DARK_PURPLE + "Mage")) {
        player.getPersistentDataContainer().set(Keys.selectedClass, PersistentDataType.STRING, "Mage");
        enforceClass(player);
      } else if (className.equals(ChatColor.DARK_BLUE + "Knight")) {
        player.getPersistentDataContainer().set(Keys.selectedClass, PersistentDataType.STRING, "Knight");
        enforceClass(player);
      }


            /*
                - Paid Classes -
                    Assassin
                    Archer
                    Necromancer
                    Paladin
                    Priest
                    Rogue
                    Shaman
                    Warlock
                    Warrior
             */

      else if (className.equals(ChatColor.DARK_GRAY + "Assassin")) {
        player.getPersistentDataContainer().set(Keys.selectedClass, PersistentDataType.STRING, "Assassin");
        enforceClass(player);
      } else if (className.equals(ChatColor.DARK_GREEN + "Archer")) {
        player.getPersistentDataContainer().set(Keys.selectedClass, PersistentDataType.STRING, "Archer");
        enforceClass(player);
      } else if (className.equals(ChatColor.DARK_GRAY + "Necromancer")) {
        player.getPersistentDataContainer().set(Keys.selectedClass, PersistentDataType.STRING, "Necromancer");
        enforceClass(player);
      } else if (className.equals(ChatColor.GOLD + "Paladin")) {
        player.getPersistentDataContainer().set(Keys.selectedClass, PersistentDataType.STRING, "Paladin");
        enforceClass(player);
      } else if (className.equals(ChatColor.WHITE + "Priest")) {
        player.getPersistentDataContainer().set(Keys.selectedClass, PersistentDataType.STRING, "Priest");
        enforceClass(player);
      } else if (className.equals(ChatColor.DARK_GREEN + "Shaman")) {
        player.getPersistentDataContainer().set(Keys.selectedClass, PersistentDataType.STRING, "Shaman");
        enforceClass(player);
      } else if (className.equals(ChatColor.DARK_RED + "Warlock")) {
        player.getPersistentDataContainer().set(Keys.selectedClass, PersistentDataType.STRING, "Warlock");
        enforceClass(player);
      }

    }
  }


  private void enforceClass(Player player) {
    PersistentDataContainer dataContainer = player.getPersistentDataContainer();

    if (dataContainer.has(Keys.selectedClass, PersistentDataType.STRING)) {
      String selectedClassName = dataContainer.get(Keys.selectedClass, PersistentDataType.STRING);

      RPGClass selectedClass = switch (selectedClassName) {
        case "Barbarian" -> new Barbarian();
        case "Mage" -> new Mage();
        case "Knight" -> new Knight();
        case "Assassin" -> new Assassin();
        case "Archer" -> new Archer();
        case "Necromancer" -> new Necromancer();
        case "Paladin" -> new Paladin();
        case "Priest" -> new Priest();
        case "Shaman" -> new Shaman();
        case "Warlock" -> new Warlock();
        default -> null;
      };

      if (selectedClass != null) {
        PersistentDataContainer playerData = player.getPersistentDataContainer();
        double health = selectedClass.getHealth();
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        player.setHealth(health);
        player.setFoodLevel(20);
        player.setSaturation(10.0f);
        // Set any other attributes specific to the selected class

        // Set health scale to 1 row of hearts
        player.setHealthScale(20.0);

        // Set armor
        player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(selectedClass.getDefense());

        // Set mana
        double maxMana = selectedClass.getMana();
        ManaManager.setMaxMana(player, maxMana);

        double manaRegenRate = selectedClass.getManaRegenRate();
        ManaManager.setManaPerSec(player, manaRegenRate);

        // Set strength
        double strength = selectedClass.getStrength();
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(strength);

        // Set food exhaustion
        float foodExhaustion = selectedClass.getFoodExhaustion();
        player.setExhaustion(foodExhaustion);
        playerData.set(Keys.foodExhaustion, PersistentDataType.FLOAT, foodExhaustion);


        player.sendMessage(ChatColor.GRAY + "You have selected the " + ChatColor.GREEN + selectedClassName + ChatColor.GRAY + " class. \n " + ChatColor.WHITE + "Press Escape to confirm your choice.");
      }
    }
  }


  @EventHandler
  public void onPlayerMove(PlayerMoveEvent event) {
    Player player = event.getPlayer();
    PersistentDataContainer dataContainer = player.getPersistentDataContainer();

    if (dataContainer.has(Keys.selectedClass, PersistentDataType.STRING)) {
      String selectedClass = dataContainer.get(Keys.selectedClass, PersistentDataType.STRING);

      if (selectedClass != null && player.isSprinting()) {
        float foodExhaustion = dataContainer.get(Keys.foodExhaustion, PersistentDataType.FLOAT);

        // Update the player's food exhaustion based on their movement
        if (event.getFrom().distanceSquared(event.getTo()) > 0) {
          player.setExhaustion(player.getExhaustion() + foodExhaustion);
        }
      }
    }
  }


  @SuppressWarnings("ALL")
  @EventHandler
  public void onInventoryClose(InventoryCloseEvent event) {
    if (event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&5ClassSelect")) || event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&5 Premium Class Select"))) {
      Player player = (Player) event.getPlayer();
      PersistentDataContainer dataContainer = player.getPersistentDataContainer();

      String className = dataContainer.get(Keys.selectedClass, PersistentDataType.STRING);

      if (className == null) {
        player.sendMessage(ChatColor.RED + "You must select a class before you can play!");
        openClassSelectGUI(player);
      }

      switch (className) {
        case "Barbarian" -> giveBarbarianStarterItems(player);
        case "Mage" -> giveMageStarterItems(player);
        case "Knight" -> giveKnightStarterItems(player);
        case "Assassin" -> giveAssassinStarterItems(player);
        case "Archer" -> giveArcherStarterItems(player);
        case "Necromancer" -> giveNecromancerStarterItems(player);
        case "Paladin" -> givePaladinStarterItems(player);
        case "Priest" -> givePriestStarterItems(player);
        case "Shaman" -> giveShamonStarterItems(player);
        case "Warlock" -> giveWarlockStarterItems(player);
        default -> player.sendMessage(ChatColor.RED + "Invalid class selected! Please contact a server administrator.");
      }
      // Kicking the player to update all new values for other aspects of the plugin.
      // player.kickPlayer(ChatColor.GREEN + "THIS IS INTENDED! \n DO NOT CONTACT A SERVER ADMIN REGARDING THIS! \n Please reconnect. ");

    }
  }

// Define the starter item methods for each class

  private void giveBarbarianStarterItems(Player player) {
    // Give barbarian starter items here
    // Example:
    player.getInventory().addItem(new ItemStack(Material.IRON_AXE));
    player.getInventory().addItem(new ItemStack(Material.APPLE, 64));

    player.getInventory().addItem(BarbarianItems.DoubleAxe());
    // Add any other starter items specific to the barbarian class
  }

  private void giveMageStarterItems(Player player) {
    // Give mage starter items here
    // Example:
    player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 4));
    player.getInventory().addItem(new ItemStack(Material.BREAD, 64));

    LevelManager.setLevel(player, 25);

    player.getInventory().addItem(MageItems.WoH());
    player.getInventory().addItem(MageItems.brokenWand());
    player.getInventory().addItem(MageItems.StarFire());
    player.getInventory().addItem(SorcerersEmbrace.createEmbraceItem(player));
    player.getInventory().addItem(MageItems.AetherWalker());
    player.getInventory().addItem(MageItems.TomeOfFrost());


    // Add any other starter items specific to the mage class
  }

  private void giveKnightStarterItems(Player player) {
    // Give knight starter items here
    // Example:
    player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
    player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 16));
    // Add any other starter items specific to the knight class
  }

  private void giveAssassinStarterItems(Player player) {
    // Give assassin starter items here
    // Example:
    player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
    player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 16));
    // Add any other starter items specific to the assassin class
  }

  private void giveArcherStarterItems(Player player) {
    // Give archer starter items here
    // Example:
    player.getInventory().addItem(new ItemStack(Material.BOW));
    player.getInventory().addItem(new ItemStack(Material.ARROW, 24));
    player.getInventory().addItem(new ItemStack(Material.COOKED_CHICKEN, 16));

    // Add any other starter items specific to the archer class
  }

  private void giveNecromancerStarterItems(Player player) {
    // Give necromancer starter items here
    // Example:
    player.getInventory().addItem(new ItemStack(Material.STICK));
    player.getInventory().addItem(new ItemStack(Material.BONE, 16));
    player.getInventory().addItem(new ItemStack(Material.BREWING_STAND, 16));
    player.getInventory().addItem(new ItemStack(Material.BLAZE_POWDER, 4));
    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));
    player.getInventory().addItem(new ItemStack(Material.GLASS_BOTTLE, 16));
    // Add any other starter items specific to the necromancer class


  }

  private void givePaladinStarterItems(Player player) {
    // Give paladin starter items here
    // Example:
    player.getInventory().addItem(new ItemStack(Material.GOLDEN_SWORD));
    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));
    player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 16));
    player.getInventory().addItem(new ItemStack(Material.SHIELD, 2));
    // Add any other starter items specific to the paladin class
  }

  private void givePriestStarterItems(Player player) {
    // Give priest starter items here
    // Example:
    player.getInventory().addItem(new ItemStack(Material.STICK));
    player.getInventory().addItem(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 2));
    player.getInventory().addItem(new ItemStack(Material.IRON_CHESTPLATE));
    player.getInventory().addItem(new ItemStack(Material.IRON_LEGGINGS));
    // Add any other starter items specific to the priest class
  }

  private void giveShamonStarterItems(Player player) {
    // Give shamon starter items here
    // Example:
    player.getInventory().addItem(new ItemStack(Material.BONE));
    player.getInventory().addItem(new ItemStack(Material.FIREWORK_ROCKET, 16));
    player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 16));
    player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));


    // Add any other starter items specific to the shamon class
  }

  private void giveWarlockStarterItems(Player player) {
    // Give warlock starter items here
    // Example:
    player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
    player.getInventory().addItem(new ItemStack(Material.POTION, 2));
    player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 16));
    player.getInventory().addItem(new ItemStack(Material.BLAZE_ROD, 4));
    player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));
    // Add any other starter items specific to the warlock class
  }


}
