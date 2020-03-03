package me.hackusatepvp.fall.staff.commands;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HideStaffCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (!Fall.getInstance().getStaffManager().isStaff(player.getUniqueId())) {
            player.sendMessage(StringUtil.format("&cSorry this command is only executed by administration."));
            return true;
        }
        if (Fall.getInstance().getStaffManager().getHidestaff().contains(player)) {
            Fall.getInstance().getStaffManager().setHiden(player, false);
            player.sendMessage(StringUtil.format("&aShowing all staff members."));
        } else {
            Fall.getInstance().getStaffManager().setHiden(player, true);
            player.sendMessage(StringUtil.format("&cHiding all staff members."));
        }
        return false;
    }
}
