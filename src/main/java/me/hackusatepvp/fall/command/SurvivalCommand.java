package me.hackusatepvp.fall.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.hackusatepvp.fall.util.StringUtil.format;

public class SurvivalCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to execute this command.");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("fatekits.command.creative")) {
            player.sendMessage(ChatColor.RED + "You do not have permissions to execute this command.");
            return true;
        }
        if (args.length == 0) {
            player.setGameMode(GameMode.SURVIVAL);
        } else {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(ChatColor.RED + "Target not found.");
                return true;
            }
            target.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(format("&7Updated &9" + target.getName() + "'s &7gamemode."));
        }
        return false;
    }

}
