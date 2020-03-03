package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (player.hasPermission("fall.admin")) {
            profile.setGod(profile.isGod() ? false : true);
            player.sendMessage(StringUtil.format("&7You have set your god to " + profile.isGod()));
        } else {
            player.sendMessage(ChatColor.RED + "You do not have permissions to execute this command.");
        }
        return false;
    }
}
