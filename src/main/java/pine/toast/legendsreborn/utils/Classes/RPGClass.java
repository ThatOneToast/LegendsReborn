package pine.toast.legendsreborn.utils.Classes;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import pine.toast.legendsreborn.utils.Keys;

public abstract class RPGClass {
  protected String className;
  protected int health;
  protected double mana;
  protected int defense;
  protected int strength;
  protected float foodExhaustion;
  protected double manaRegenRate;

  public RPGClass(String className, int health, double mana, int defense, int strength, float foodExhaustion, double manaRegenRate) {
    this.className = className;
    this.health = health;
    this.mana = mana;
    this.defense = defense;
    this.strength = strength;
    this.foodExhaustion = foodExhaustion;
    this.manaRegenRate = manaRegenRate;
  }

  public static String getClassName(Player player) {
    PersistentDataContainer dataContainer = player.getPersistentDataContainer();
    return dataContainer.get(Keys.selectedClass, PersistentDataType.STRING);
  }

  public int getHealth() {
    return health;
  }

  public double getMana() {
    return mana;
  }


  public int getDefense() {
    return defense;
  }

  public int getStrength() {
    return strength;
  }

  public float getFoodExhaustion() {
    return foodExhaustion;
  }

  public double getManaRegenRate() {
    return manaRegenRate;
  }
}









