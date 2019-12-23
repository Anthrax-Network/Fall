package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TagsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        player.openInventory(Fall.getInstance().getTagsGUI().getTagsGUI(player));
        return false;
    }
}
