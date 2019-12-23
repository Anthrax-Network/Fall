package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.Message;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleMessageCommand implements CommandExecutor {
    public ToggleMessageCommand() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("command.togglemessage")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Message.format("&cOnly in-game player can execute this command."));
                return false;
            }

            Player player = (Player)sender;
            Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
            if (!profile.isMessages()) {
                player.sendMessage(Message.format("&ePrivate messages toggled &coff&6."));
                profile.setMessages(true);
                return true;
            }

            player.sendMessage(Message.format("&ePrivate messages toggled &aon&6."));
            profile.setMessages(false);
            return true;

        } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission");
        }

        return false;
    }
}