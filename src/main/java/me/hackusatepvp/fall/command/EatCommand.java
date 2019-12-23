package me.hackusatepvp.fall.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("fall.donor")) {
            player.sendMessage(ChatColor.RED + "You must be a donor to use this command.");
            return true;
        }
        player.setFoodLevel(100);
        player.setSaturation(3.0f);
        return false;
    }
}
