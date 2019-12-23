package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.info.Info;
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
            player.sendMessage(ChatColor.RED + "Usage: /info <player>");
        } else {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target != null) {
                Info info = new Info(target);
                Fall.getInstance().getInfoManager().addInfo(target.getUniqueId(), info);
                player.openInventory(Fall.getInstance().getInfoGUI().getInfoGUI(target));
            } else {
                player.sendMessage(ChatColor.RED + "Target not found");
            }
        }
        return false;
    }
}
