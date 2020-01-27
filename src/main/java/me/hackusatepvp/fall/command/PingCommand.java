package me.hackusatepvp.fall.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player.");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Ping: &9" + ((CraftPlayer)player).getHandle().ping));
        } else {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target != null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Ping: &9" + ((CraftPlayer)target).getHandle().ping));
            }
        }
        return false;
    }
}