package pine.toast.legendsreborn.utils.ClassesClass.Mage.Robes;

import com.jeff_media.armorequipevent.ArmorEquipEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import pine.toast.legendsreborn.utils.Keys;
import pine.toast.mana.ManaManager;

public class SorcerersEvent implements Listener {

  @EventHandler
  public void onEmbraceEquip(ArmorEquipEvent event) {
    if (event.getNewArmorPiece() == null) return;
    ItemStack newArmor = event.getNewArmorPiece();
    ItemMeta armorMeta = newArmor.getItemMeta();

    if (armorMeta == null) return;

    PersistentDataContainer data = armorMeta.getPersistentDataContainer();

    if (!(data.has(Keys.embrace, PersistentDataType.DOUBLE))) {
      return;
    }

    Player player = event.getPlayer();

    double addedMana = data.get(Keys.embrace, PersistentDataType.DOUBLE);
    double currentMana = ManaManager.getMaxMana(player);

    ManaManager.setMaxMana(player, currentMana + addedMana);
  }

  @EventHandler
  public void onEmbraceRemove(ArmorEquipEvent event) {
    if (event.getOldArmorPiece() == null) return;
    ItemStack oldArmor = event.getOldArmorPiece();
    ItemMeta armorMeta = oldArmor.getItemMeta();

    if (armorMeta == null) return;

    PersistentDataContainer data = armorMeta.getPersistentDataContainer();
    if (!(data.has(Keys.embrace, PersistentDataType.DOUBLE))) {
      return;
    }

    Player player = event.getPlayer();

    double currentMana = ManaManager.getMaxMana(player);
    double addedMana = data.get(Keys.embrace, PersistentDataType.DOUBLE);

    ManaManager.setMaxMana(player, currentMana - addedMana);
  }


}