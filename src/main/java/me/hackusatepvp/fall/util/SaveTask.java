package me.hackusatepvp.fall.util;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.Setter;
import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.profile.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SaveTask extends BukkitRunnable {
    private int left = 0;

    public void run() {
         ++left;
         if (left == 120) {
             for (Player player : Bukkit.getOnlinePlayers()) {
                 player.sendMessage(StringUtil.format("&7Saving all data."));
                 Fall.getInstance().getProfileManager().unload(player);
                 Fall.getInstance().getProfileManager().load(player);
                 Fall.getInstance().getMySQL().profiles();
             }
         }
    }
}
