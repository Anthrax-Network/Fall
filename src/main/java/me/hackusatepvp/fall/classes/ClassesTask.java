package me.hackusatepvp.fall.classes;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.quests.Quest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ClassesTask extends BukkitRunnable {

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {

            Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
            Classes classes = Classes.getByName(profile.getActiveClass());

            if (classes != null) {
                classes.onTick(player, profile);
            }
        }
    }
}
