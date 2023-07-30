package pine.toast.legendsreborn;

import com.jeff_media.armorequipevent.ArmorEquipEvent;
import org.bukkit.plugin.java.JavaPlugin;
import pine.toast.legendsreborn.Commands.*;
import pine.toast.legendsreborn.Entities.*;
import pine.toast.legendsreborn.Entities.Extras.Items;
import pine.toast.legendsreborn.Setups.FirstJoin;
import pine.toast.legendsreborn.Setups.SelectClass;
import pine.toast.legendsreborn.utils.ActionBar;
import pine.toast.legendsreborn.utils.AuctionHouse.AuctionHouseEvents;
import pine.toast.legendsreborn.utils.AuctionHouse.AuctionHouseManager;
import pine.toast.legendsreborn.utils.AuctionHouse.CategorieEvents;
import pine.toast.legendsreborn.utils.ClassesClass.Barbarian.BarbarianEvent;
import pine.toast.legendsreborn.utils.ClassesClass.ClassScoreboard;
import pine.toast.legendsreborn.utils.ClassesClass.Mage.MageEvents;
import pine.toast.legendsreborn.utils.ClassesClass.Mage.Robes.SorcerersEvent;
import pine.toast.legendsreborn.utils.EconomySystem.EconomyManager;
import pine.toast.legendsreborn.utils.EconomySystem.MoneyPerKill;
import pine.toast.legendsreborn.utils.LevelSystem.Experience;
import pine.toast.legendsreborn.utils.TeamManager.TeamManager;

import java.io.File;
import java.io.IOException;


public final class EternalRealms_LegendsReborn extends JavaPlugin {

  private static EternalRealms_LegendsReborn plugin;

  @Override
  public void onEnable() {
    // Plugin startup logic
    ActionBar.initializeMana();
    ArmorEquipEvent.registerListener(this);
    AuctionHouseManager.createAuctionHouseDataManager(this);

    plugin = this;

    getServer().getPluginManager().registerEvents(new SelectClass(), this);
    getServer().getPluginManager().registerEvents(new FirstJoin(), this);
    getServer().getPluginManager().registerEvents(new MoneyPerKill(), this);

    // Mobs
    getServer().getPluginManager().registerEvents(new Magma(), this);
    getServer().getPluginManager().registerEvents(new SuperWitch(), this);
    getServer().getPluginManager().registerEvents(new Tesla(), this);
    getServer().getPluginManager().registerEvents(new Enderdragon(), this);
    getServer().getPluginManager().registerEvents(new Jackal(), this);

    // Items
    getServer().getPluginManager().registerEvents(new MageEvents(), this);
    getServer().getPluginManager().registerEvents(new Items(), this);
    getServer().getPluginManager().registerEvents(new BarbarianEvent(), this);
    getServer().getPluginManager().registerEvents(new Experience(), this);
    getServer().getPluginManager().registerEvents(new SorcerersEvent(), this);


    // Auction House
    getServer().getPluginManager().registerEvents(new CategorieEvents(), this);
    getServer().getPluginManager().registerEvents(new AuctionHouseEvents(), this);

    getCommand("ah").setExecutor(new AHCommand());
    getCommand("ah").setTabCompleter(new AHTabCompleter());


    getCommand("togglesubscription").setExecutor(new ToggleSubscription());
    getCommand("whoami").setExecutor(new whoami());
    getCommand("togglexp").setExecutor(new ToggleExp());
    getCommand("tteam").setExecutor(new TeamCommand());
    getCommand("tteam").setTabCompleter(new TeamCommandTabCompleter());
    getCommand("pay").setExecutor(new Pay());

    // Define the file path for teams.dat
    File teamsFile = new File(getDataFolder(), "teams.dat");
    File balanceFile = new File(getDataFolder(), "balances.dat");


    // Create an instance of TeamManager
    TeamManager teamManager = new TeamManager(teamsFile);

    // Create an instance of EconomyManager
    EconomyManager economyManager = new EconomyManager(balanceFile);

    // Create an instance of InventoryManager

    // Check if teams.dat file exists
    if (!teamsFile.exists()) {
      try {
        boolean teamsFileCreated = teamsFile.createNewFile();
        if (teamsFileCreated) {
          getLogger().info("teams.dat file created.");
          // Perform additional actions for a successful file creation
        } else {
          getLogger().severe("Failed to create teams.dat file!");
          // Perform additional actions for a failed file creation
        }
      } catch (IOException e) {
        getLogger().severe("Failed to create teams.dat file!");
        e.printStackTrace();
        return;
      }
    }

    // Check if balances.dat file exists
    if (!balanceFile.exists()) {
      try {
        boolean balanceFileCreated = balanceFile.createNewFile();
        if (balanceFileCreated) {
          getLogger().info("balances.dat file created.");
          // Perform additional actions for a successful file creation
        } else {
          getLogger().severe("Failed to create balances.dat file!");
          // Perform additional actions for a failed file creation
        }
      } catch (IOException e) {
        getLogger().severe("Failed to create balances.dat file!");
        e.printStackTrace();
        return;
      }
    }


    TeamManager.loadTeamsFromFile();


    EconomyManager.loadBalancesFromFile();


    ClassScoreboard.updateAllPlayerScoreboards();

  }

  public static EternalRealms_LegendsReborn getPlugin() {
    return plugin;
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic

    TeamManager.saveTeamsToFile();
    EconomyManager.saveBalancesToFile();

  }
}
