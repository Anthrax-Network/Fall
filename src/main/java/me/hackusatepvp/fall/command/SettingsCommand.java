package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SettingsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Nope");
            return true;
        }
        Player player = (Player) sender;
        player.openInventory(Fall.getInstance().getSettingsGUI().getGUI(player));
        return false;
    }
}
