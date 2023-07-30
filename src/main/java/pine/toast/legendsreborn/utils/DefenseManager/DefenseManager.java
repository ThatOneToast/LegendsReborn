package pine.toast.legendsreborn.utils.DefenseManager;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class DefenseManager {



    public static void setDefense(Player player, int amount) {
        // Set the defense attribute of the player to the amount specified

        player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(amount);

    }

    public static void addDefense(Player player, int amount) {
        // Add the amount specified to the defense attribute of the player

        player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(player.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue() + amount);

    }

    public static void removeDefense(Player player, int amount) {
        // Remove the amount specified from the defense attribute of the player

        player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(player.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue() - amount);

    }

    public static void getDefense(Player player) {
        // Get the defense attribute of the player

        player.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue();

    }



}
