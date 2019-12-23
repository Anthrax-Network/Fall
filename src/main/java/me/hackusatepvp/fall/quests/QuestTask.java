package me.hackusatepvp.fall.quests;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class QuestTask extends BukkitRunnable {

    // the purpose of the class too is to make it easier for you, it will run the onTick method provided in the Quest class every second IF the active quest of the player is valid

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {

            Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
            Quest quest = Quest.getByName(profile.getActiveQuest());

            if (quest != null) {
                quest.onTick(player, profile);
            }
        }
    }
}
