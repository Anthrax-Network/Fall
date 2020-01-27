package me.hackusatepvp.fall.staff.commands;

import me.hackusatepvp.fall.Fall;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("fall.staff")) {
            player.sendMessage(ChatColor.RED + "You do not have permissions to perform this command.");
            return true;
        }
        if (Fall.getInstance().getStaffManager().isVanish(player)) {
            Fall.getInstance().getStaffManager().removeVanish(player);
            player.sendMessage(ChatColor.RED + "Y ou have removed your vanish");
            Bukkit.getOnlinePlayers().forEach(instance -> instance.showPlayer(player));
        } else {
            Fall.getInstance().getStaffManager().setStaffMode(player);
            player.sendMessage(ChatColor.GREEN + "Poof! Your have disappeared.");
            Bukkit.getOnlinePlayers().forEach(instance -> instance.hidePlayer(player));
        }
        return false;
    }
}
