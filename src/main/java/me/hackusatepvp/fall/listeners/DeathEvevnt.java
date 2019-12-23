package me.hackusatepvp.fall.listeners;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.clans.Clan;
import me.hackusatepvp.fall.clans.ClanPlayer;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.quests.Quest;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvevnt implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity() != null) {
            Player player = (Player) event.getEntity();
            Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
            //Simple killer check
            if (event.getEntity().getKiller() != null) {
                Player killer = event.getEntity().getKiller();
                //Getting the killer profile
                Profile prokiller = Fall.getInstance().getProfileManager().getProfile(killer.getUniqueId());
                //Checking to see if the player that died is in a clan
                if (Fall.getInstance().getClanManager().inClan(player.getUniqueId())) {
                    ClanPlayer clanPlayer = Fall.getInstance().getClanManager().getClanPlayer(player);
                    Clan clan = clanPlayer.getClan();
                    //Adding the death to the clan since the player died
                    clan.setDeaths(clan.getDeaths() + 1);
                }
                //Getting the killers' clan
                if (Fall.getInstance().getClanManager().inClan(killer.getUniqueId())) {
                    ClanPlayer clanPlayer = Fall.getInstance().getClanManager().getClanPlayer(killer);
                    Clan clan = clanPlayer.getClan();
                    //Adding the kill since the killer got a kill
                    clan.setKills(clan.getDeaths() + 1);
                }
                for (Player online : Bukkit.getOnlinePlayers()) {
                    //Checking to see who has kill messages enabled
                    if (Fall.getInstance().getProfileManager().getProfile(online.getUniqueId()).isKillmsg()) {
                        //sending the chat message to those who have kill messages enabled
                        online.sendMessage(StringUtil.format("&c&l" + player.getName() + " &7has died to &b&l" + killer.getName()));
                    }
                }
                prokiller.setKills(prokiller.getKills() + 1);
                profile.setDeaths(profile.getDeaths() + 1);
                player.spigot().respawn();
                prokiller.setXp(profile.getXp() + 15);
                if (prokiller.getLevel() != 100) {
                    if (prokiller.getXp() == 100) {
                        prokiller.setLevel(profile.getLevel() + 1);
                        prokiller.setXp(0);
                        return;
                    }
                }
                //ends the method completely
                Quest quest = Quest.getByName(Fall.getInstance().getProfileManager().getProfile(killer.getUniqueId()).getActiveQuest());
                if (quest != null) {
                    Bukkit.getLogger().info("QUEST WORKED!");
                    quest.onKill(killer, player, event);
                }
                if (Fall.getInstance().getCombatManager().inCombat(player)) {
                    Fall.getInstance().getCombatManager().removeFromCombat(player);
                }
                killer.setLevel(prokiller.getLevel());
                return;
            }
            //todo world death message
            //Checking to see if the player is in a clan
            if (Fall.getInstance().getClanManager().inClan(player.getUniqueId())) {
                ClanPlayer clanPlayer = Fall.getInstance().getClanManager().getClanPlayer(player);
                Clan clan = clanPlayer.getClan();
                //Adding the death to the clan since the player died
                clan.setDeaths(clan.getDeaths() + 1);
            }
            //Applying stats to the players personal stats
            profile.setDeaths(profile.getDeaths() + 1);
            player.setLevel(profile.getLevel());
        }
    }

    private String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
