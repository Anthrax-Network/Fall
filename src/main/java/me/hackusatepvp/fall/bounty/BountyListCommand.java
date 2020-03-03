package me.hackusatepvp.fall.bounty;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.GFG;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class BountyListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        HashMap<String, Double> bounties = new HashMap<>();
        player.sendMessage(StringUtil.format("&7&m---------------------------------"));
        for (Player online : Bukkit.getOnlinePlayers()) {
            Profile tar = Fall.getInstance().getProfileManager().getProfile(online.getUniqueId());
            if (tar.isBounty()) {
                bounties.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue())
                        .forEachOrdered(x -> bounties.put(x.getKey(), x.getValue()));
                bounties.put(online.getName(), tar.getBountyamount());
                String[] message = new String[] {
                       "&b* &a" + bounties.get(online.getName()) + "$ &7" + online.getName(),
                };
                Arrays.asList(message).forEach(msg -> player.sendMessage(StringUtil.format(msg)));
            }
        }
        player.sendMessage(StringUtil.format("&7&m---------------------------------"));

        return false;
    }
}
