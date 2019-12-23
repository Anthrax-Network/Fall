package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (!player.hasPermission("fall.donor")) {
            player.sendMessage(ChatColor.RED + "This is a donor command. Purchase a rank /buy");
            return true;
        }
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /nick <nick>");
        } else {
            player.setCustomName(args[0]);
            player.setCustomNameVisible(true);
            Bukkit.getOnlinePlayers().forEach(instance -> instance.setCustomNameVisible(true));
            profile.setName(args[0]);
            player.sendMessage("Shit is set you fucking wanker.");
        }

        return false;
    }
}
