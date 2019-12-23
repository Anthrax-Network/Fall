package me.hackusatepvp.fall.tab;

import com.bizarrealex.azazel.tab.TabAdapter;
import com.bizarrealex.azazel.tab.TabTemplate;
import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.clans.Clan;
import me.hackusatepvp.fall.clans.ClanPlayer;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.quests.Quest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class TabLink implements TabAdapter {
    public TabTemplate getTemplate(Player player) {
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (profile.isTablist()) {
            if (profile.getTabtype().equalsIgnoreCase("blue")) {
                TabTemplate blue = new TabTemplate();
                blue.left(0, "");
                blue.left(1, "&9Player Info");
                blue.left(2, "&7Level: &b" + profile.getLevel());
                blue.left(3, "&7Experience: &b" + profile.getXp());
                blue.left(4, "");
                blue.left(5, "&9Stats");
                blue.left(6, "&7Kills: &b" + profile.getKills());
                blue.left(7, "&7Deaths: &b" + profile.getDeaths());
                blue.left(8, "&7KDR: &b" + profile.getKdr());
                blue.left(9, "");
                blue.left(10, "&9Rank Info");
                if (profile.getDonor().equalsIgnoreCase("starter")) {
                    blue.left(11, "&7Rank &b" + profile.getActiveQuest());
                } else {
                    blue.left(11, "&7Rank &b" + profile.getDonor());
                }
                if (profile.getActiveQuest() != null) {
                    if (Quest.getActiveQuest(profile) != null) {
                        blue.left(12, "&7Progress: &b" + Quest.getActiveQuest(profile).getProgress(profile) + "");
                        blue.left(13, "&7Goal: &b" + Quest.getActiveQuest(profile).getGoal());
                    }
                }

                blue.middle(0, "&9&lFateKits &bKitPvP");
                blue.middle(1, "&7Online: &b" + Bukkit.getOnlinePlayers().size());
                if (Fall.getInstance().getClanManager().inClan(player.getUniqueId())) {
                    ClanPlayer clanPlayer = Fall.getInstance().getClanManager().getClanPlayer(player);
                    Clan clan = clanPlayer.getClan();
                    blue.middle(4, "&9&l" + clan.getName());
                    blue.middle(5, "&7Prefix: &b" + clan.getPrefix());
                    blue.middle(6, "&7Online: &b");
                    blue.middle(7, "&7Members: &b" + clan.getMembers().size());
                    blue.middle(8, "");
                }

                blue.right(1, "&9Ranks");
                blue.right(2, "&b* &7Fate");
                blue.right(3, "&b* &7Saber");
                blue.right(4, "&b* &7Berserker");
                blue.right(5, "&b* &7Lancer");
                blue.right(6, "&b* &7Assassin");
                blue.right(7, "&b* &7Archer");
                blue.right(8, "&b* &7Caster");
                blue.right(9, "&b* &7Rider");
                blue.right(10, "&b* &7Master");
                return blue;


            } else if (profile.getTabtype().equalsIgnoreCase("red")) {
                TabTemplate red = new TabTemplate();
                red.left(0, "");
                red.left(1, "&4Player Info");
                red.left(2, "&7Level: &c" + profile.getLevel());
                red.left(3, "&7Experience: &c" + profile.getXp());
                red.left(4, "");
                red.left(5, "&4Stats");
                red.left(6, "&7Kills: &c" + profile.getKills());
                red.left(7, "&7Deaths: &c" + profile.getDeaths());
                red.left(8, "&7KDR: &c" + profile.getKdr());
                red.left(9, "");
                red.left(10, "&4Rank Info");
                if (profile.getDonor().equalsIgnoreCase("starter")) {
                    red.left(11, "&7Rank &c" + profile.getDonor());
                } else {
                    red.left(11, "&7Rank: &c" + profile.getActiveQuest());
                }
                red.left(12, "&7Progress: &c" + Quest.getByName(profile.getActiveQuest()).getProgress(profile)); /*format.format(Main.getPlugin().getApi().getQuestManager().getRankProgress(player, Main.getPlugin().getApi().getQuestManager().getRankConfig(player))) + "%"); */
                red.left(13, "&7Goal: &c" + Quest.getByName(profile.getActiveQuest()).getGoal());

                red.middle(0, "&4&lFateKits &cKitPvP");
                red.middle(1, "&7Online: &c" + Bukkit.getOnlinePlayers().size());
                if (Fall.getInstance().getClanManager().inClan(player.getUniqueId())) {
                    ClanPlayer clanPlayer = Fall.getInstance().getClanManager().getClanPlayer(player);
                    Clan clan = clanPlayer.getClan();
                    red.middle(4, "&4&l" + clan.getName());
                    red.middle(5, "&7Prefix: &c" + clan.getPrefix());
                    red.middle(6, "&7Members: &c" + clan.getMembers().size());
                    red.middle(7, "");
                    red.middle(8, "");
                }

                red.right(1, "&4Ranks");
                red.right(2, "&c* &7Fate");
                red.right(3, "&c* &7Saber");
                red.right(4, "&c* &7Berserker");
                red.right(5, "&c* &7Lancer");
                red.right(6, "&c* &7Assassin");
                red.right(7, "&c* &7Archer");
                red.right(8, "&c* &7Caster");
                red.right(9, "&c* &7Rider");
                red.right(10, "&c* &7Master");
                return red;
            }
        }
        return null;
    }
}