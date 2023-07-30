package pine.toast.legendsreborn.Entities;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pine.toast.legendsreborn.EternalRealms_LegendsReborn;
import pine.toast.legendsreborn.utils.LevelSystem.LevelManager;

import java.util.Arrays;
import java.util.Objects;

public class Magma implements Listener {


  

  ItemStack magmaRod = new ItemStack(Material.BLAZE_ROD);

  {
    magmaRod.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
    magmaRod.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 10);
    magmaRod.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 10);
    magmaRod.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 10);
    magmaRod.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 10);
    magmaRod.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 10);
    magmaRod.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 10);

    String[] magmaLore = new String[4];
    magmaLore[0] = ChatColor.RED + "Magma Rod";
    magmaLore[1] = ChatColor.GOLD + "A weapon made of magma";
    magmaLore[2] = ChatColor.GOLD + "and fire. It is extremely";
    magmaLore[3] = ChatColor.GOLD + "hot to the touch.";
    magmaRod.getItemMeta().setLore(Arrays.asList(magmaLore));

    Objects.requireNonNull(magmaRod.getItemMeta()).setDisplayName(ChatColor.YELLOW + "Magma Rod");
    magmaRod.getItemMeta().addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier("generic.attackDamage", 30, AttributeModifier.Operation.ADD_NUMBER));
  }

  @EventHandler
  public void onZombieTarget(EntityTargetEvent event) {

    if (!(event.getEntity() instanceof Zombie zombie)) return;
    if (!(event.getTarget() instanceof Player player)) return;
    if (!(zombie.getName().equals(ChatColor.YELLOW + "Magma"))) return;

    zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200);
    zombie.setHealth(200);
    zombie.setCustomNameVisible(true);

    Objects.requireNonNull(zombie.getEquipment()).setHelmet(new ItemStack(Material.MAGMA_BLOCK));
    zombie.getEquipment().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
    zombie.getEquipment().setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));
    zombie.getEquipment().setBoots(new ItemStack(Material.GOLDEN_BOOTS));
    zombie.getEquipment().setItemInOffHand(new ItemStack(Material.BLAZE_POWDER));
    zombie.getEquipment().setItemInMainHand(new ItemStack(magmaRod));

    zombie.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 10000000, 1, false, false, false));
    zombie.setVisualFire(true);
    player.setFireTicks(533);
    Location playerLocal = player.getLocation();
    player.getWorld().createExplosion(playerLocal, 4, true, true);


  }

  @EventHandler
  public void onZombieDeath(EntityDeathEvent event) {
    if (!(event.getEntity() instanceof Zombie zombie)) return;
    if (!(zombie.getName().equals(ChatColor.YELLOW + "Magma"))) return;

    zombie.getWorld().createExplosion(zombie.getLocation(), 6, true, true);
    zombie.getWorld().createExplosion(zombie.getLocation(), 6, true, true);
    
    if(zombie.getKiller() == null) return;

    LevelManager.addExperience(zombie.getKiller(), 50);

    if (Math.random() <= 0.08) {
        zombie.getKiller().getInventory().addItem(magmaRod);
    }
  }

  @EventHandler
  public void magamSpawn(EntitySpawnEvent event) {
    if (!(event.getEntity() instanceof Zombie zombie)) return;

    if (!(Math.random() <= 0.15)) return;

    zombie.setCustomName(ChatColor.YELLOW + "Magma");
    zombie.setCustomNameVisible(false);

  }


}