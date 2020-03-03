package me.hackusatepvp.fall.profile;

import lombok.Getter;
import lombok.Setter;
import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.quests.Quest;
import me.hackusatepvp.fall.rank.Rank;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Profile {
    private final UUID uuid;
    private String chatype;
    private String name, invites, quest, nick, clan, clanrank, leader, prefix, tag, color;
    private double kdr, bountyamount;
    private int kills, deaths, killstreak, level, xp, ladder;
    private boolean tablist, killmsg, chat, scoreboard, staff, messages, messagesound, bounty, god;
    private String donor;
    private String boardstyle, tabtype;
    private String activeQuest, activeClass;
    private Double balance;


    public Profile(UUID uuid) {
        this.uuid = uuid;
    }

    public double getKdr() {
        double ratio = 0.69D;
        if (this.getDeaths() != 0) {
            double divided = ((double) this.getKills()) / ((double) this.getDeaths());
            ratio = Math.round(divided);
        }
        if (ratio == 0.69D) {
            return 0.0D;
        }
        return ratio;
    }

}
