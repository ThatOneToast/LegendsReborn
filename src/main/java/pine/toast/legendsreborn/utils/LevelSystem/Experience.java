package pine.toast.legendsreborn.utils.LevelSystem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.persistence.PersistentDataType;
import pine.toast.legendsreborn.utils.DamageManager;
import pine.toast.legendsreborn.utils.DefenseManager.DefenseManager;
import pine.toast.legendsreborn.utils.HealthManager.HealthManager;
import pine.toast.legendsreborn.utils.Keys;
import pine.toast.mana.ManaManager;

public class Experience implements Listener {


    public static void buffs(Player player, int currentLevel, String className) {
        if (currentLevel % 5 == 0) {

            switch (className) {
                case "Barbarian" -> {
                    HealthManager.addMaxHealth(player, 3);
                    DefenseManager.addDefense(player, 2);
                    ManaManager.setManaPerSec(player, ManaManager.getMaxMana(player) + 0.2);
                    ManaManager.setMaxMana(player, ManaManager.getMaxMana(player) + 2);
                    DamageManager.addDamage(player, 3);

                }
                case "Mage" -> {
                    HealthManager.addMaxHealth(player, 1);
                    DefenseManager.addDefense(player, 1);
                    ManaManager.setManaPerSec(player, ManaManager.getMaxMana(player) + 1.5);
                    ManaManager.setMaxMana(player, ManaManager.getMaxMana(player) + 35);
                    DamageManager.addDamage(player, 1);
                }
                case "Knight" -> {
                    // Apply buffs for Knight class
                    HealthManager.addMaxHealth(player, 2);
                    DefenseManager.addDefense(player, 3);
                    ManaManager.setManaPerSec(player, ManaManager.getMaxMana(player) + 0.5);
                    ManaManager.setMaxMana(player, ManaManager.getMaxMana(player) + 10);
                    DamageManager.addDamage(player, 2);
                }
                case "Assassin" -> {
                    // Apply buffs for Assassin class
                    HealthManager.addMaxHealth(player, 3);
                    DefenseManager.addDefense(player, 1);
                    ManaManager.setManaPerSec(player, ManaManager.getMaxMana(player) + 0.5);
                    ManaManager.setMaxMana(player, ManaManager.getMaxMana(player) + 10);
                    DamageManager.addDamage(player, 1);
                }
                case "Archer" -> {
                    // Apply buffs for Archer class
                    HealthManager.addMaxHealth(player, 2);
                    DefenseManager.addDefense(player, 2);
                    ManaManager.setManaPerSec(player, ManaManager.getMaxMana(player) + 0.2);
                    ManaManager.setMaxMana(player, ManaManager.getMaxMana(player) + 2);
                    DamageManager.addDamage(player, 1);
                }
                case "Necromancer" -> {
                    // Apply buffs for Necromancer class
                    HealthManager.addMaxHealth(player, 1);
                    DefenseManager.addDefense(player, 2);
                    ManaManager.setManaPerSec(player, ManaManager.getMaxMana(player) + 15);
                    ManaManager.setMaxMana(player, ManaManager.getMaxMana(player) + 60);
                    DamageManager.addDamage(player, 1);
                }
                case "Paladin" -> {
                    // Apply buffs for Paladin class
                    HealthManager.addMaxHealth(player, 3);
                    DefenseManager.addDefense(player, 3);
                    ManaManager.setManaPerSec(player, ManaManager.getMaxMana(player) + 1);
                    ManaManager.setMaxMana(player, ManaManager.getMaxMana(player) + 3);
                    DamageManager.addDamage(player, 2);
                }
                case "Priest" -> {
                    // Apply buffs for Priest class
                    HealthManager.addMaxHealth(player, 2);
                    DefenseManager.addDefense(player, 2);
                    ManaManager.setManaPerSec(player, ManaManager.getMaxMana(player) + 30);
                    ManaManager.setMaxMana(player, ManaManager.getMaxMana(player) + 30);
                    DamageManager.addDamage(player, 1);
                }
                case "Shaman" -> {
                    // Apply buffs for Shaman class
                    HealthManager.addMaxHealth(player, 3);
                    DefenseManager.addDefense(player, 1);
                    ManaManager.setManaPerSec(player, ManaManager.getMaxMana(player) + 10);
                    ManaManager.setMaxMana(player, ManaManager.getMaxMana(player) + 50);
                    DamageManager.addDamage(player, 1);
                }
                case "Warlock" -> {
                    // Apply buffs for Warlock class
                    HealthManager.addMaxHealth(player, 1);
                    DefenseManager.addDefense(player, 1);
                    ManaManager.setManaPerSec(player, ManaManager.getMaxMana(player) + 0.5);
                    ManaManager.setMaxMana(player, ManaManager.getMaxMana(player) + 10);
                    DamageManager.addDamage(player, 1);
                }
                default -> System.out.println("Invalid class name");
            }
        }
    }



    @EventHandler
    public void onExperiencePickUp(PlayerExpChangeEvent event) {

        int amount = event.getAmount() / 2;

        LevelManager.addExperience(event.getPlayer(), amount);

    }

    @EventHandler
    public void onPlayerDie(PlayerDeathEvent event){
        int experience = event.getEntity().getPersistentDataContainer().get(Keys.experience, PersistentDataType.INTEGER);

        LevelManager.setExperience(event.getEntity(), experience / 2);
        event.getEntity().sendMessage("You lost " + experience / 2 + " experience!");
    }

}
