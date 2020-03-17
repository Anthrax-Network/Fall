package me.hackusatepvp.fall.clans;

import lombok.Getter;
import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ClanManager {
    private final String INSERT = "INSERT INTO clans VALUES(?,?,?,?,?)";

    @Getter private Set<Clan> clans = new HashSet<>();
    @Getter private Set<ClanPlayer> clanPlayers = new HashSet<>();


    // look i gotta dip for a couple hours I have to volunteer somewhere but when i get back ill msg u see if ur still on, maybe I can help out a little more
    // either way I was actually impressed and keep finding ways to make this better, there's ALWAYS ways to make projects better

    public void addToClan(Clan clan, ClanPlayer clanPlayer) {
        clan.getMembers().add(clanPlayer);
    }

    public void removeFromClan(Clan clan, ClanPlayer clanPlayer, Player player) {
        clan.getMembers().remove(clanPlayer);
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        profile.setClan("null");
        profile.setPrefix("null");
        profile.setLeader("null");
        profile.setClanrank("null");
        profile.setLadder(0);
    }

    public Clan getClan(String name) {

        for (Clan clan : clans) {
            if (clan.getName().equalsIgnoreCase(name)) {
                return clan;
            }
        }

        return null;
    }

    public Clan getClan(ClanPlayer clanPlayer) {

        for (Clan clan : clans) {
            for (ClanPlayer member : clan.getMembers()) {
                if (clanPlayer == member) {
                    return clan;
                }
            }
        }

        return null;
    }

    public ClanPlayer getClanPlayer(Player player) {

        for (Clan clan : clans) {
            for (ClanPlayer members : clan.getMembers()) {
                if (members.getUuid().equals(player.getUniqueId()))
                    return members;
            }
        }

        return null;
    }

    public boolean inClan(UUID uuid) {
        for (Clan clan : clans) {
            for (ClanPlayer members : clan.getMembers()) {
                if (members.getUuid().equals(uuid))
                    return true;
            }
        }
        return !Fall.getInstance().getProfileManager().getProfile(uuid).getClan().equalsIgnoreCase("null");
    }

    public Player getMembersOnline(String name) {
        for (Clan clan : clans) {
            if (clan.getName().equals(name)) {
                for (ClanPlayer clanPlayer : clan.getMembers()) {
                   Player member = Bukkit.getPlayer(clanPlayer.getUuid());
                    member.isOnline();
                }
            }
        }
        return null;
    }

    public boolean isNaughtyWord(String s) {
        List<String> naughty = Fall.getInstance().getConfig().getStringList("Naughty.Words");
        return naughty.contains(s.toLowerCase());
    }

    public Set<Clan> getClans() {
        return clans;
    }

    public void deleteClan(Clan clan) {
        final String SELECT = "DELETE FROM `clans` WHERE `clans`.`UUID` =" + clan.getUuid();
        Bukkit.getScheduler().runTaskAsynchronously(Fall.getInstance(), () -> {
            try {
                PreparedStatement preparedStatement = Fall.getInstance().getMySQL().getConnection().prepareStatement(SELECT);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                Bukkit.getLogger().severe(e.getMessage());
            }
        });
    }

    public void load(Clan clan) {
        clans.add(clan);
       // Fall.getInstance().getProfileManager().addProfile(player.getUniqueId(), playerProfile);
        final String SELECT = "SELECT NAME, PREFIX, LEADER, SIZE FROM clans WHERE UUID=?";

        Bukkit.getScheduler().runTaskAsynchronously(Fall.getInstance(), () -> {
            try {
                PreparedStatement preparedStatement = Fall.getInstance().getMySQL().getConnection().prepareStatement(SELECT);
                preparedStatement.setInt(1, clan.getUuid());
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    clan.setName(rs.getString("NAME"));
                    clan.setPrefix(rs.getString("PREFIX"));
                    clan.setLeader(rs.getString("LEADER"));
                    clan.setSize(rs.getInt("SIZE"));

                }

            } catch (SQLException e) {
                Bukkit.getLogger().severe(e.getMessage());
            }
        });
    }

    public void unload(Clan clan) {

        try {
            PreparedStatement preparedStatement = Fall.getInstance().getMySQL().getConnection().prepareStatement("UPDATE clans SET NAME = ?, PREFIX = ?, LEADER = ?, SIZE = ? WHERE UUID = ?");
            preparedStatement.setString(1, clan.getName());
            preparedStatement.setString(2, clan.getPrefix());
            preparedStatement.setString(3, clan.getLeader());
            preparedStatement.setInt(4, clan.getSize());
            preparedStatement.setInt(5, clan.getUuid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        clans.remove(clan);
    }
    public boolean existsClan(Clan clan) {
        PreparedStatement ps;
        try {
            ps = Fall.getInstance().getMySQL().getConnection().prepareStatement("SELECT * FROM clans WHERE UUID = ?");
            ps.setString(1, clan.getUuid().toString());
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            Bukkit.getLogger().severe(e.getMessage());
            return false;
        }
    }
    public static void userExists(final Callback<HashMap<String, Boolean>> callback, final String uuid) {
        // Create a async Bukkit scheduler
        Bukkit.getScheduler().runTaskAsynchronously(Fall.getInstance(), () -> {
            final HashMap<String, Boolean> result = new HashMap<>();
            PreparedStatement ps;

            try {
                ps = Fall.getInstance().getMySQL().getConnection().prepareStatement("SELECT * FROM clans WHERE UUID = ?");
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

    public void createClan(Clan clan) {
        Bukkit.getScheduler().runTaskAsynchronously(Fall.getInstance(), () -> {
            try {
                PreparedStatement preparedStatement = Fall.getInstance().getMySQL().getConnection().prepareStatement(INSERT + " ON DUPLICATE KEY UPDATE UUID='" + clan.getUuid().toString() + "'");
                preparedStatement.setInt(1, clan.getUuid());
                preparedStatement.setString(2, clan.getName());
                preparedStatement.setString(3, clan.getPrefix());
                preparedStatement.setString(4, clan.getLeader());
                preparedStatement.setInt(5, clan.getSize());
                preparedStatement.execute();
                preparedStatement.executeUpdate();
                Fall.getInstance().getClanManager().load(clan);
                clans.add(clan);
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
