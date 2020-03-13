package me.hackusatepvp.fall.util;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.Setter;
import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.clans.Clan;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.profile.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveTask extends BukkitRunnable {
    private int left = 0;

    public void run() {
         ++left;
         if (left == 120) {
             for (Player player : Bukkit.getOnlinePlayers()) {
                 Profile playerProfile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
                 try {
                     PreparedStatement preparedStatement = Fall.getInstance().getMySQL().getConnection().prepareStatement("UPDATE profiles SET NAME = ? , BOARDSTYLE  = ? , QUEST = ? , ACTIVEQUEST = ?, SCOREBOARD = ? , CHAT = ?, TAB = ? , TABSTYLE = ? , KILLS = ? , DEATHS = ? , KILLSTREAK = ? , LEVEL = ? , XP = ?, DONOR = ?, CLAN = ?, CLANRANK = ?, LADDER =? , INVITES =? , LEADER = ?, PREFIX = ?, CHATTYPE = ?, BALANCE = ?, STAFF = ?, NICK =?, MESSAGES =?, MESSAGESOUNDS =?, ACTIVECLASS =?, TAG =?, COLOR =?, BOUNTY =?, BOUNTYAMOUNT =?, GOD =? WHERE UUID = ?");
                     preparedStatement.setString(1, player.getName());
                     preparedStatement.setString(2, playerProfile.getBoardstyle());
                     preparedStatement.setString(3, playerProfile.getQuest());
                     preparedStatement.setString(4, playerProfile.getActiveQuest());
                     preparedStatement.setBoolean(5, playerProfile.isScoreboard());
                     preparedStatement.setBoolean(6, playerProfile.isChat());
                     preparedStatement.setBoolean(7, playerProfile.isTablist());
                     preparedStatement.setString(8, playerProfile.getTabtype());
                     preparedStatement.setInt(9, playerProfile.getKills());
                     preparedStatement.setInt(10, playerProfile.getDeaths());
                     preparedStatement.setInt(11, playerProfile.getKillstreak());
                     preparedStatement.setInt(12, playerProfile.getLevel());
                     preparedStatement.setInt(13, playerProfile.getXp());
                     preparedStatement.setString(14, playerProfile.getDonor());
                     preparedStatement.setString(15, playerProfile.getClan());
                     preparedStatement.setString(16, playerProfile.getClanrank());
                     preparedStatement.setInt(17, playerProfile.getLadder());
                     preparedStatement.setString(18, playerProfile.getInvites());
                     preparedStatement.setString(19, playerProfile.getLeader());
                     preparedStatement.setString(20, playerProfile.getPrefix());
                     preparedStatement.setString(21, playerProfile.getChatype());
                     preparedStatement.setDouble(22, playerProfile.getBalance());
                     preparedStatement.setBoolean(23, playerProfile.isStaff());
                     preparedStatement.setString(24, playerProfile.getNick());
                     preparedStatement.setBoolean(25, playerProfile.isMessages());
                     preparedStatement.setBoolean(26, playerProfile.isMessagesound());
                     preparedStatement.setString(27, playerProfile.getActiveClass());
                     preparedStatement.setString(28, playerProfile.getTag());
                     preparedStatement.setString(29, playerProfile.getColor());
                     preparedStatement.setBoolean(30, playerProfile.isBounty());
                     preparedStatement.setDouble(31, playerProfile.getBountyamount());
                     preparedStatement.setBoolean(32, playerProfile.isGod());
                     preparedStatement.setString(33, player.getUniqueId().toString());
                     preparedStatement.executeUpdate();
                     Bukkit.getLogger().info("[Fall] Updated all profile entries.");
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             if (Fall.getInstance().getClanManager().getClanPlayer(player) != null) {
                 Clan clan = Fall.getInstance().getClanManager().getClan(Fall.getInstance().getClanManager().getClanPlayer(player));
                 try {
                     PreparedStatement preparedStatement = Fall.getInstance().getMySQL().getConnection().prepareStatement("UPDATE clans SET NAME = ?, PREFIX = ?, LEADER = ?, SIZE = ? WHERE UUID = ?");
                     preparedStatement.setString(1, clan.getName());
                     preparedStatement.setString(2, clan.getPrefix());
                     preparedStatement.setString(3, clan.getLeader());
                     preparedStatement.setInt(4, clan.getSize());
                     preparedStatement.setInt(5, clan.getUuid());
                     preparedStatement.executeUpdate();
                     Bukkit.getLogger().info("[Fall] Updated all clan entries.");
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
                 left = 0;
             }
         }
    }
}
