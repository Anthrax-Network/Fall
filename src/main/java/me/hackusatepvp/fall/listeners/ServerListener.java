package me.hackusatepvp.fall.listeners;

import me.hackusatepvp.fall.Fall;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ServerListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage();
        Player player = event.getPlayer();
        if (command.toLowerCase().contains("/help") || command.contains("/?") || command.contains("/bukkit:?") || command.contains("/bukkit:help") || command.equalsIgnoreCase("/help") || command.equalsIgnoreCase("/?") || command.equalsIgnoreCase("/bukkit:?") || command.equalsIgnoreCase("/bukkit:help")) {
            event.setCancelled(true);
            player.sendMessage(format("&7&m--------------------------------------------"));
            player.sendMessage(format("&9&lFateKits &bVersion: &7" + Fall.getInstance().getDescription().getVersion()));
            player.sendMessage(format("&b&lDiscord: &7https://discord.gg/6dPU8wW"));
            player.sendMessage(format("&b&lTwitter: &7https://twitter.com/fatekits"));
            player.sendMessage(format("&b&lWebsite: &7http://www.fatekits.net"));
            player.sendMessage(format(""));
            player.sendMessage(format("&b/help 2 &7for commands"));
            player.sendMessage(format("&7&m--------------------------------------------"));
        }
        if (command.toLowerCase().contains("/help 2") || command.contains("/? 2") || command.contains("/bukkit:? 2") || command.contains("/bukkit:help 2") || command.equalsIgnoreCase("/help 2") || command.equalsIgnoreCase("/? 2") || command.equalsIgnoreCase("/bukkit:? 2") || command.equalsIgnoreCase("/bukkit:help 2")) {
            event.setCancelled(true);
            player.sendMessage(format("&7&m--------------------------------------------"));
            player.sendMessage(format("&9Commands"));
            player.sendMessage(format("&b/about &7(view all plugins on the server)"));
            player.sendMessage(format("&b/discord &7(join our discord server!)"));
            player.sendMessage(format("&b/fatetop &7(shows the servers top stats)"));
            player.sendMessage(format("&b/ping &7(shows connection info of a player)"));
            player.sendMessage(format("&b/kits &7(opens a kit gui)"));
            player.sendMessage(format("&b/level &7(displays your current level)"));
            player.sendMessage(format("&b/list &7(displays how many players or staff are on)"));
            player.sendMessage(format("&b/login &7(if you are frozen try logging in with your password)"));
            player.sendMessage(format("&b/message &7(send a private message to a player)"));
            player.sendMessage(format(""));
            player.sendMessage(format("&b/help 3 &7for more commands"));
            player.sendMessage(format("&7&m--------------------------------------------"));
        }
        if (command.toLowerCase().equalsIgnoreCase("/help 3") || command.equalsIgnoreCase("/? 3") || command.equalsIgnoreCase("/bukkit:? 3") || command.equalsIgnoreCase("/bukkit:help 3") || command.contains("/help 3") || command.contains("/? 3") || command.contains("/bukkit:? 3") || command.contains("/bukkit:help 3")) {
            event.setCancelled(true);
            player.sendMessage(format("&7&m--------------------------------------------"));
            player.sendMessage(format("&b/rank &7(shows your personal rank information)"));
            player.sendMessage(format("&b/ranks &7(shows all rank information)"));
            player.sendMessage(format("&b/register &7(if you are frozen try this command)"));
            player.sendMessage(format("&b/report &7(report a player for a reasonable cause)"));
            player.sendMessage(format("&b/request &7(request staff assistance)"));
            player.sendMessage(format("&b/kits &7(opens a kit gui)"));
            player.sendMessage(format("&b/stats &7(shows your personal stats)"));
            player.sendMessage(format("&7&m--------------------------------------------"));
        }
        if (command.toLowerCase().equalsIgnoreCase("/pl") || command.equalsIgnoreCase("/plugins") || command.equalsIgnoreCase("/bukkit:pl") || command.equalsIgnoreCase("/bukkit:plugins") || command.equalsIgnoreCase("/about") || command.equalsIgnoreCase("/bukkit:about") || command.contains("/pl") || command.contains("/plugins") || command.contains("/bukkit:pl") || command.contains("/bukkit:plugins") || command.contains("/about") || command.contains("/bukkit:about")) {
            event.setCancelled(true);
            player.sendMessage(format("&7&m--------------------------------------------"));
            player.sendMessage(format("&c#01 &4FateKits: &7" + Fall.getInstance().getDescription().getVersion()));
            player.sendMessage(format("&c#02 &4FateKits-API: &7[1.0.0]"));
            player.sendMessage(format("&c#03 &4Vault: &7[1.5.6-b49]"));
            player.sendMessage(format("&c#04 &4PermissionsEx &7[1.23.4]"));
            player.sendMessage(format("&c#05 &4AsyncWorldEditInjector &7[2.2.2]"));
            player.sendMessage(format("&c#06 &4AsyncWorldEdit &7[2.2.2]"));
            player.sendMessage(format("&c#07 &4WorldEdit &7[6.1;no_git_id]"));
            player.sendMessage(format("&c#08 &4WorldGuard &7[6.1]"));
            player.sendMessage(format("&c#09 &4Buycraft &7[10.3.1]"));
            player.sendMessage(format("&c#10 &4ProtocolLib &7[3.7-SNAPSHOT-b232]"));
            player.sendMessage(format("&c#11 &4Litebans &7[2.3.18]"));
            player.sendMessage(format("&7&m--------------------------------------------"));
        }
        if (command.toLowerCase().equalsIgnoreCase("/version") || command.equalsIgnoreCase("/ver") || command.equalsIgnoreCase("/bukkit:version") || command.equalsIgnoreCase("/bukkit:ver") || command.contains("/version") || command.contains("/ver") || command.contains("/bukkit:version") || command.contains("/bukkit:ver")) {
            event.setCancelled(true);
            player.sendMessage(format("&7&m--------------------------------------------"));
            player.sendMessage(format("&7The server is running &4FateKits &cversion: &7" + Fall.getInstance().getDescription().getVersion()));
            player.sendMessage(format("&7&m--------------------------------------------"));
        }
    }

    public String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
