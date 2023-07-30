package pine.toast.legendsreborn.Entities.Extras;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EnderDragon;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class DragonHealthRunnable extends BukkitRunnable {
  private final EnderDragon enderDragon;
  private final double healthIncrease;

  public DragonHealthRunnable(Plugin plugin, EnderDragon enderDragon, double healthIncrease) {
    this.enderDragon = enderDragon;
    this.healthIncrease = healthIncrease;

    // Run the task repeatedly every 3 seconds (60 ticks)
    this.runTaskTimer(plugin, 0L, 60L);
  }

  @Override
    public void run() {
    double currentHealth = enderDragon.getHealth();
    double newHealth = currentHealth + healthIncrease;
    double maxHealth = enderDragon.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();

    // Ensure the new health does not exceed the maximum health
    if (newHealth > maxHealth) {
      newHealth = maxHealth;
    }

    // Update the dragon's health
    enderDragon.setHealth(newHealth);
  }
}