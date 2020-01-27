package me.hackusatepvp.fall.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Iterator;

import static me.hackusatepvp.fall.util.StringUtil.format;

public class BroadcastCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Usage: /bc [message]");
            } else {
                String reason = "";
                for (int i = 0; i < args.length; ++i) {
                    reason = reason + args[i] + " ";
                }
                for (Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(format("&7[&4FateKits&] " + reason));
                }
            }

            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("fatekits.command.broadcast")) {
            player.sendMessage(ChatColor.RED + "You do not have permissions to execute this command.");
            return true;
        }
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /bc [message]");
            return true;
        } else {
            String reason = "";
            for (int i = 0; i < args.length; ++i) {
                reason = reason + args[i] + " ";
            }
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.sendMessage(format("&7[&4FateKits&] " + reason));
            }
        }
        return false;
    }
}