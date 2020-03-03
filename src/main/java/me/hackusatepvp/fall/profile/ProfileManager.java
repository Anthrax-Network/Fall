package me.hackusatepvp.fall.profile;

import lombok.Getter;
import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.quests.Quest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager {
    private final String INSERT = "INSERT INTO profiles VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    @Getter
    Fall instance;

    private Map<UUID, Profile> profiles = new HashMap<>();

    public void addProfile(UUID uuid, Profile profile) {
        profiles.put(uuid, profile);
    }

    public Profile getProfile(UUID uuid) {
        return profiles.get(uuid);
    }

    public boolean hasProfile(UUID uuid) {
        return profiles.containsKey(uuid);
    }

    public void load(Player player) {
        Profile playerProfile = new Profile(player.getUniqueId());
        profiles.put(player.getUniqueId(), playerProfile);
        Fall.getInstance().getProfileManager().addProfile(player.getUniqueId(), playerProfile);
        final String SELECT = "SELECT NAME, BOARDSTYLE, ACTIVEQUEST, QUEST, SCOREBOARD, CHAT, TAB, TABSTYLE, KILLS, DEATHS, KILLSTREAK, LEVEL, XP, DONOR, CLAN, CLANRANK, LADDER, INVITES, LEADER, PREFIX, CHATTYPE, BALANCE, STAFF, NICK, MESSAGES, MESSAGESOUNDS, ACTIVECLASS, TAG, COLOR, BOUNTY, BOUNTYAMOUNT, GOD FROM profiles WHERE UUID=?";

        Bukkit.getScheduler().runTaskAsynchronously(Fall.getInstance(), () -> {
            try {
                PreparedStatement preparedStatement = Fall.getInstance().getMySQL().getConnection().prepareStatement(SELECT);
                preparedStatement.setString(1, player.getUniqueId().toString());

                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    playerProfile.setName(rs.getString("NAME"));
                    playerProfile.setBoardstyle(rs.getString("BOARDSTYLE"));
                    playerProfile.setQuest(rs.getString("QUEST"));
                    playerProfile.setActiveQuest(rs.getString("ACTIVEQUEST"));
                    playerProfile.setScoreboard(rs.getBoolean("SCOREBOARD"));
                    playerProfile.setChat(rs.getBoolean("CHAT"));
                    playerProfile.setTablist(rs.getBoolean("TAB"));
                    playerProfile.setTabtype(rs.getString("TABSTYLE"));
                    playerProfile.setKills(rs.getInt("KILLS"));
                    playerProfile.setDeaths(rs.getInt("DEATHS"));
                    playerProfile.setKillstreak(rs.getInt("KILLSTREAK"));
                    playerProfile.setLevel(rs.getInt("LEVEL"));
                    playerProfile.setXp(rs.getInt("XP"));
                    playerProfile.setDonor(rs.getString("DONOR"));
                    playerProfile.setClan(rs.getString("CLAN"));
                    playerProfile.setClanrank(rs.getString("CLANRANK"));
                    playerProfile.setLadder(rs.getInt("LADDER"));
                    playerProfile.setClanrank(rs.getString("INVITES"));
                    playerProfile.setLeader(rs.getString("LEADER"));
                    playerProfile.setPrefix(rs.getString("PREFIX"));
                    playerProfile.setChatype(rs.getString("CHATTYPE"));
                    playerProfile.setBalance(rs.getDouble("BALANCE"));
                    playerProfile.setStaff(rs.getBoolean("STAFF"));
                    playerProfile.setNick(rs.getString("NICK"));
                    playerProfile.setMessages(rs.getBoolean("MESSAGES"));
                    playerProfile.setMessages(rs.getBoolean("MESSAGESOUNDS"));
                    playerProfile.setActiveClass(rs.getString("ACTIVECLASS"));
                    playerProfile.setTag(rs.getString("TAG"));
                    playerProfile.setColor(rs.getString("COLOR"));
                    playerProfile.setBounty(rs.getBoolean("BOUNTY"));
                    playerProfile.setBountyamount(rs.getDouble("BOUNTYAMOUNT"));
                    playerProfile.setGod(rs.getBoolean("GOD"));
                }

            } catch (SQLException e) {
                Bukkit.getLogger().info(e.getMessage());
            }
        });
    }

    public void unload(Player player) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        profiles.remove(player.getUniqueId());
    }

    public boolean existsUser(Player player) {
        PreparedStatement ps;
        try {
            ps = Fall.getInstance().getMySQL().getConnection().prepareStatement("SELECT * FROM profiles WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            Bukkit.getLogger().severe(e.getMessage());
            return false;
        }
    }
    public static void userExists(final Callback<HashMap<String, Boolean>> callback,
                                  final String uuid) {
        // Create a async Bukkit scheduler
        Bukkit.getScheduler().runTaskAsynchronously(Fall.getInstance(), () -> {
            final HashMap<String, Boolean> result = new HashMap<>();
            PreparedStatement ps;

            try {
                ps = Fall.getInstance().getMySQL().getConnection().prepareStatement("SELECT * FROM profiles WHERE UUID = ?");
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();

                result.put(uuid, rs.next());

            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Create a new async Bukkit scheduler
            Bukkit.getScheduler().runTaskAsynchronously(Fall.getInstance(), () -> {
                try {
                    callback.onSuccess(result);
                } catch (Exception ex) {
                    callback.onFailure(ex.getCause());
                }
            });
        });
    }

    public void createStatsUser(Player player) {
        Bukkit.getScheduler().runTaskAsynchronously(Fall.getInstance(), () -> {
            try {
                PreparedStatement preparedStatement = Fall.getInstance().getMySQL().getConnection().prepareStatement(INSERT + " ON DUPLICATE KEY UPDATE UUID='" + player.getUniqueId() + "'");
                preparedStatement.setString(1, player.getUniqueId().toString());
                preparedStatement.setString(2, player.getName());
                preparedStatement.setString(3, "blue");
                preparedStatement.setString(4, "Starter");
                preparedStatement.setString(5, "Starter");
                preparedStatement.setBoolean(6, true);
                preparedStatement.setBoolean(7, true);
                preparedStatement.setBoolean(8, true);
                preparedStatement.setString(9, "blue");
                preparedStatement.setInt(10, 0);
                preparedStatement.setInt(11, 0);
                preparedStatement.setInt(12, 0);
                preparedStatement.setInt(13, 0);
                preparedStatement.setInt(14, 0);
                preparedStatement.setString(15, "starter");
                preparedStatement.setString(16, "null");
                preparedStatement.setString(17, "null");
                preparedStatement.setInt(18, 0);
                preparedStatement.setString(19, "null");
                preparedStatement.setString(20, "null");
                preparedStatement.setString(21, "null");
                preparedStatement.setString(22, "public");
                preparedStatement.setDouble(23, 0.00);
                preparedStatement.setBoolean(24, false);
                preparedStatement.setString(25, "null");
                preparedStatement.setBoolean(26, true);
                preparedStatement.setBoolean(27, true);
                preparedStatement.setString(28, "null");
                preparedStatement.setString(29, "null");
                preparedStatement.setString(30, "&7");
                preparedStatement.setBoolean(31,false);
                preparedStatement.setDouble(32, 0.00);
                preparedStatement.setBoolean(33, false);
                preparedStatement.execute();
                preparedStatement.executeUpdate();
                Fall.getInstance().getProfileManager().load(player);
            } catch (SQLException e) {
                Bukkit.getLogger().severe(e.getMessage());
            }
        });
    }

    public interface Callback<T> {
        void onSuccess(T done);

        void onFailure(Throwable cause);
    }
}