package me.hackusatepvp.fall.util;

import lombok.Getter;
import me.hackusatepvp.fall.Fall;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQL {

    private String host = Fall.getInstance().getConfig().getString("MySQL.host");
    private String port = Fall.getInstance().getConfig().getString("MySQL.port");
    private String database = Fall.getInstance().getConfig().getString("MySQL.database");
    private String username = Fall.getInstance().getConfig().getString("MySQL.username");
    private String password = Fall.getInstance().getConfig().getString("MySQL.password");



    private Connection con;
    private ConsoleCommandSender console = Bukkit.getConsoleSender();

    @Getter
    Fall instance;

    public MySQL(Fall plugin) {
        this.instance = plugin;
    }

    // connect
    public void profiles() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
                console.sendMessage(StringUtil.format("&7A secure connection to the MySQL has &asuccessfully &7been established."));
                PreparedStatement profiles = this.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS profiles (UUID VARCHAR(36)," +
                        "NAME TEXT, " + "BOARDSTYLE TEXT, " + "QUEST TEXT, ACTIVEQUEST TEXT, " + "SCOREBOARD BOOLEAN NOT NULL DEFAULT FALSE, " + "CHAT BOOLEAN NOT NULL DEFAULT FALSE, " + "TAB BOOLEAN NOT NULL DEFAULT FALSE, "  + "TABSTYLE TEXT, " +
                        "KILLS INT(0), DEATHS INT(0), KILLSTREAK INT(0), LEVEL INT(0), XP INT(0), DONOR TEXT, CLAN TEXT, CLANRANK TEXT, LADDER INT(0), INVITES TEXT, LEADER TEXT, PREFIX TEXT, CHATTYPE TEXT, STAFF BOOLEAN NOT NULL DEFAULT FALSE, " +
                        "BALANCE DOUBLE, NICK TEXT, MESSAGES BOOLEAN NOT NULL DEFAULT FALSE, MESSAGESOUNDS BOOLEAN NOT NULL DEFAULT FALSE, ACTIVECLASS TEXT, TAG TEXT, COLOR TEXT, " +
                        "PRIMARY KEY (UUID))");
                profiles.executeUpdate();

                PreparedStatement clans = this.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS clans (UUID INT(4)," +
                        "NAME TEXT, PREFIX TEXT, LEADER TEXT, SIZE INT(0), " +
                        "PRIMARY KEY (UUID))");
                clans.executeUpdate();
            } catch (SQLException e) {
                Bukkit.getLogger().severe(e.getMessage());
            }
        }
    }


    // disconnect
    public void disconnectProfiles() {
        if (isConnected()) {
            try {
                con.close();
                console.sendMessage(StringUtil.format("&7The secure connection to MySQL has &csuccessfully &7been closed."));
            } catch (SQLException e) {
                Bukkit.getLogger().info(e.getMessage());
            }
        }
    }

    public void disconnectClans() {
        if (isConnectedClan()) {
            try {
                con.close();
                console.sendMessage(StringUtil.format("&7The secure connection to MySQL has &csuccessfully &7been closed."));
            } catch (SQLException e) {
                Bukkit.getLogger().info(e.getMessage());
            }
        }
    }


    // isConnected
    public boolean isConnected() {
        return (con != null);
    }

    // getConnection
    public Connection getConnection() {
        return con;
    }

    // isConnected
    public boolean isConnectedClan() {
        return (con != null);
    }

    // getConnection
    public Connection getConnectionClan() {
        return con;
    }
}