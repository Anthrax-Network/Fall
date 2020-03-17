package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            //todo console message
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("fall.commands.info")) {
            player.sendMessage(command.getPermissionMessage());
            return true;
        }
        if (args.length == 0) {
            player.openInventory(Fall.getInstance().getInfoGUI().getInfoGUI());
        }
        return false;
    }
}
