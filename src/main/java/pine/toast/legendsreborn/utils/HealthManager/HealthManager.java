package pine.toast.legendsreborn.utils.HealthManager;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class HealthManager {



    public static double getMaxHealth(Player player) {
        return player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();

    }

    public static void setMaxHealth(Player player, double amount) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(amount);
    }

    public static void addMaxHealth(Player player, double amount) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(getMaxHealth(player) + amount);
    }

    public static void removeMaxHealth(Player player, double amount) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(getMaxHealth(player) - amount);
    }



}
