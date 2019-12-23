package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleScoreboardCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
            if (profile.isScoreboard()) {
                profile.setScoreboard(false);
                player.sendMessage(ChatColor.RED + "You have disabled your scoreboard.");
            } else {
                profile.setScoreboard(true);
                player.sendMessage(ChatColor.GREEN + "You have enabled your scoreboard.");
            }
        } else {
            sender.sendMessage(ChatColor.DARK_RED + "You must be a player to use this command.");
        }
        return false;
    }
}
