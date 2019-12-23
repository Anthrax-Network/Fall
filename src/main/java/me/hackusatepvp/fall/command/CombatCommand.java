package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CombatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (Fall.getInstance().getCombatManager().inCombat(player)) {
            player.sendMessage(StringUtil.format("&cYou are in combat."));
        } else {
            player.sendMessage(StringUtil.format("&aYou are not in combat."));
        }
        return false;
    }
}
