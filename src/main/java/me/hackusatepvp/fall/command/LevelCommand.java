package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class LevelCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        String[] levelMessage = new String[] {
          "&6* &7Level: &c" + profile.getLevel(),
          "&6* &7Experience: &c" + profile.getXp()
        };
        Arrays.asList(levelMessage).forEach(message -> player.sendMessage(StringUtil.format(message)));
        return false;
    }
}
