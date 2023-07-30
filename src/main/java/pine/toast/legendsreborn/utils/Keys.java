package pine.toast.legendsreborn.utils;

import org.bukkit.NamespacedKey;
import pine.toast.legendsreborn.EternalRealms_LegendsReborn;
import pine.toast.mana.Mana;

import java.util.UUID;

public class Keys {

  // Player Keys
  public static final NamespacedKey isPaid = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "isPaid");
  public static final NamespacedKey level = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "level");
  public static final NamespacedKey maxLevel = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "maxLevel");
  public static final NamespacedKey experience = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "experience");
  public static final NamespacedKey showExp = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "showExp");

  // Classes Keys
  public static final NamespacedKey selectedClass = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "selectedClass");
  public static final NamespacedKey foodExhaustion = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "foodExhaustion");

  // Mana Keys
  public static final NamespacedKey maxMana = new NamespacedKey(Mana.getPlugin(), "maxMana");
  public static final NamespacedKey manaPerSec = new NamespacedKey(Mana.getPlugin(), "manaPerSec");

  // Items
  public static final NamespacedKey Vengeance = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "Vengeance");
  public static final NamespacedKey DeathsReaper = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "DeathsReaper");
  public static final NamespacedKey DoubleAxe = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "DoubleAxe");
  public static final NamespacedKey BrokenWand = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "BrokenWand");
  public static final NamespacedKey Starfire = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "Starfire");
  public static final NamespacedKey WoH = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "Woh");
  public static final NamespacedKey ToF = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "ToF");
  public static final NamespacedKey AetherWalker = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "AetherWalker");
  public static final NamespacedKey embrace = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "embrace");

  // Auction House
  public static final NamespacedKey cost = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "cost");
  public static final NamespacedKey seller = new NamespacedKey(EternalRealms_LegendsReborn.getPlugin(), "seller");

}
