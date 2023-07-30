package pine.toast.legendsreborn.utils.ClassesClass;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scheduler.BukkitRunnable;
import pine.toast.legendsreborn.EternalRealms_LegendsReborn;
import pine.toast.legendsreborn.utils.Keys;

public class ClassScoreboard {



    public static void updatePlayerScoreboard(Player player) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("class", "dummy", ChatColor.GREEN + player.getPersistentDataContainer().get(Keys.selectedClass, PersistentDataType.STRING));
        objective.setDisplaySlot(DisplaySlot.BELOW_NAME);

        String className = player.getPersistentDataContainer().get(Keys.selectedClass, PersistentDataType.STRING);
        if (className != null) {
            objective.getScore(className + " ").setScore(-1);
        } else {
            objective.getScore("None ").setScore(-1);
        }

        player.setScoreboard(scoreboard);
    }


    public static void updateAllPlayerScoreboards() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    updatePlayerScoreboard(player);
                }
            }
        }.runTaskTimer(EternalRealms_LegendsReborn.getPlugin(), 0, 20);

    }




}
