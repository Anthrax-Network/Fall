package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /pay <player> <amount>");
        } else {
            if (args.length == 1) {
                player.sendMessage(ChatColor.RED + "/pay <" + args[0] + "> <amount>");
                return true;
            }
            if (args.length == 2) {
                Double amount = Double.parseDouble(args[1]);
                if (amount <= profile.getBalance()) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        Profile profile1 = Fall.getInstance().getProfileManager().getProfile(target.getUniqueId());
                        Fall.getInstance().getEconomyManager().setBalance(profile, amount);
                        Fall.getInstance().getEconomyManager().addBalance(profile1, amount);
                        player.sendMessage(ChatColor.GREEN + "You have sent $" + amount + " to " + target.getName());
                        target.sendMessage(ChatColor.GREEN + "You have received $" + amount + " from " + player.getName());
                    } else {
                        player.sendMessage(ChatColor.RED + "Target not found.");
                    }
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "Not enough money.");
                }
                return true;
            }
        }

        return false;
    }
}
