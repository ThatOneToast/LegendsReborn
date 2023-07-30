package pine.toast.legendsreborn.Entities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pine.toast.legendsreborn.EternalRealms_LegendsReborn;
import pine.toast.legendsreborn.utils.LevelSystem.LevelManager;

import java.util.List;
import java.util.Random;

public class Jackal implements Listener {


  @EventHandler
  public void onSkeletonSpawn(EntitySpawnEvent event) {

    if (!(event.getEntity() instanceof Skeleton skeleton)) return;

    Random random = new Random();
    double SPAWN_CHANCE = 0.07;

    if (random.nextDouble() <= SPAWN_CHANCE) {
      Bukkit.getScheduler().runTaskLater(EternalRealms_LegendsReborn.getPlugin(), () -> {
        skeleton.setCustomName(ChatColor.GOLD + "Jackal");
        skeleton.setCustomNameVisible(false);

        skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(250);
        skeleton.setHealth(250);
        skeleton.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20);
        skeleton.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(20);

        // Create a golden axe
        ItemStack goldenAxe = new ItemStack(Material.GOLDEN_AXE);

        // Set custom display name
        ItemMeta itemMeta = goldenAxe.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GOLD + "Jackal's Golden Axe");

        // Set lore
        itemMeta.setLore(List.of(ChatColor.GRAY + "A powerful golden axe",
                ChatColor.GRAY + "enchanted by a legendary jackal"));

        // Add sharpness enchantment
        itemMeta.addEnchant(Enchantment.DAMAGE_ALL, 6, true);

        // Set base damage to 12
        itemMeta.setCustomModelData(12);

        goldenAxe.setItemMeta(itemMeta);

        // Give the golden axe to the skeleton
        skeleton.getEquipment().setItemInMainHand(goldenAxe);
        skeleton.getEquipment().setItemInMainHandDropChance(0.03f);
      }, 1L);
    }

  }

  @EventHandler
  public void onSkeletonDeath(EntityDeathEvent event) {
    if (!(event.getEntity() instanceof Skeleton skeleton)) {
      return;
    }

    double DROP_CHANCE = 0.03; // 3% Chance

    if (skeleton.getCustomName() == null) return;

    if (!(skeleton.getCustomName().equals(ChatColor.GOLD + "Jackal"))) return;

    if(skeleton.getKiller() == null) return;
    
    LevelManager.addExperience(skeleton.getKiller(), 50);
    
    // Check if the jackal should drop its axe
    Random random = new Random();
    if (random.nextDouble() <= DROP_CHANCE) {
      // Create the jackal's axe

      ItemStack goldenAxe = new ItemStack(Material.GOLDEN_AXE);

      // Set custom display name
      ItemMeta itemMeta = goldenAxe.getItemMeta();
      itemMeta.setDisplayName(ChatColor.GOLD + "Jackal's Golden Axe");

      // Set lore
      itemMeta.setLore(List.of(ChatColor.GRAY + "A powerful golden axe",
              ChatColor.GRAY + "enchanted by a legendary jackal"));

      // Add sharpness enchantment
      itemMeta.addEnchant(Enchantment.DAMAGE_ALL, 6, true);

      // Set base damage to 12
      itemMeta.setCustomModelData(12);

      goldenAxe.setItemMeta(itemMeta);

      // Give the golden axe to the skeleton

      // Drop the axe
      event.getDrops().add(goldenAxe);
    }
  }


}