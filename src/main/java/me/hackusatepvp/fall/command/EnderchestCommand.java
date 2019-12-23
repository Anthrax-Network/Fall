package me.hackusatepvp.fall.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderchestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to execute this command.");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("fatekits.donor")) {
            player.sendMessage(ChatColor.RED + "You must be a donor to use this command.");
            return true;
        }
        if (args.length == 0) {
            player.openInventory(player.getEnderChest());
        } else {
            if (!player.hasPermission("fatekits.staff")) {
                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target != null) {
                player.openInventory(target.getEnderChest());
            } else {
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
                if (offlinePlayer.hasPlayedBefore()) {
                    Player off = offlinePlayer.getPlayer();
                    if (off != null) {
                        player.openInventory(off.getEnderChest());
                    }
                }
            }
        }
        return false;
    }
}