package me.hackusatepvp.fall.profile;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.quests.Quest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class ProfileListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        ProfileManager.userExists(new ProfileManager.Callback<HashMap<String, Boolean>>() {
            @Override
            public void onSuccess(HashMap<String, Boolean> done) {
                if (done.get(player.getUniqueId().toString()) != Boolean.TRUE) {
                    Fall.getInstance().getProfileManager().createStatsUser(player);
                    Fall.getInstance().getProfileManager().addProfile(player.getUniqueId(), new Profile(player.getUniqueId()));
                    Fall.getInstance().getProfileManager().load(player);
                    Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
                    switch (profile.getActiveQuest()) {
                        case "starter":
                            profile.setActiveQuest(Quest.STARTER_QUEST.getName());
                            break;
                        case "master":
                            profile.setActiveQuest(Quest.MASTER_QUEST.getName());
                        case "rider":
                            profile.setActiveQuest(Quest.RIDER_QUEST.getName());
                            break;
                        case "caster":
                            profile.setActiveQuest(Quest.CASTER_QUEST.getName());
                            break;
                        case "archer":
                            profile.setActiveQuest(Quest.ARCHER_QUEST.getName());
                            break;
                        case "assassin":
                            profile.setActiveQuest(Quest.ASSASSIN_QUEST.getName());
                            break;
                        case "lancer":
                            profile.setActiveQuest(Quest.LANCER_QUEST.getName());
                            break;
                        case "berserker":
                            profile.setActiveQuest(Quest.BERSERKER_QUEST.getName());
                            break;
                        case "saber":
                            profile.setActiveQuest(Quest.SABER_QUEST.getName());
                            break;
                        case "fate":
                            profile.setActiveQuest(Quest.FATE_QUEST.getName());
                            break;
                    }
                    player.setLevel(profile.getLevel());
                    player.setExp(profile.getXp());
                } else {
                    Fall.getInstance().getProfileManager().load(player);
                    Fall.getInstance().getProfileManager().addProfile(player.getUniqueId(), Fall.getInstance().getProfileManager().getProfile(player.getUniqueId()));
                }

            }

            @Override
            public void onFailure(Throwable cause) {
                Bukkit.getLogger().severe(cause.getMessage());
            }
        }, player.getUniqueId().toString());
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Fall.getInstance().getProfileManager().unload(player);

    }
}