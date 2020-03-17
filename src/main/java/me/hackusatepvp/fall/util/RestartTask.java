package me.hackusatepvp.fall.util;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class RestartTask extends BukkitRunnable {
    private int count = 0;
    @Override
    public void run() {
        count++;
        if (count == 43195) {
            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(StringUtil.format("&cServer restarting in 5 seconds")));
        }
        if (count == 43200) {
            Bukkit.getServer().shutdown();
        }
    }
}
