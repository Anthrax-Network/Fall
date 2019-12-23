package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.clans.ClanPlayer;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Nope");
            return true;
        }
        Player player = (Player) sender;
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (args.length == 0) {
            List<String> statMessage = new ArrayList<>();
            statMessage.add("&7&m-----------------------------------");
            statMessage.add("&b* &7Kills: &9" + profile.getKills() + "");
            statMessage.add("&b* &7Deaths: &9" + profile.getDeaths() + "");
            statMessage.add("&b* &7KDR: &9" + profile.getKdr() + "");
            if (Fall.getInstance().getClanManager().inClan(player.getUniqueId())) {
                ClanPlayer clanPlayer = Fall.getInstance().getClanManager().getClanPlayer(player);
                statMessage.add("&9&l" + clanPlayer.getClan().getName() + "'s Info");
                statMessage.add("&b* &7Rank: &9" + clanPlayer.getRank());
            }
            statMessage.add("&7&m-----------------------------------");
            statMessage.forEach(message -> player.sendMessage(StringUtil.format(message)));
        } else {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target != null) {
                Profile tar = Fall.getInstance().getProfileManager().getProfile(target.getUniqueId());
                List<String> statMessage = new ArrayList<>();
                statMessage.add("&7&m-----------------------------------");
                statMessage.add("&b* &7Kills: &9" + tar.getKills() + "");
                statMessage.add("&b* &7Deaths: &9" + tar.getDeaths() + "");
                statMessage.add("&b* &7KDR: &9" + tar.getKdr() + "");
                if (Fall.getInstance().getClanManager().inClan(target.getUniqueId())) {
                    ClanPlayer clanPlayer = Fall.getInstance().getClanManager().getClanPlayer(target);
                    statMessage.add("&9&l" + clanPlayer.getClan().getName() + "'s Info");
                    statMessage.add("&b* &7Rank: &9" + clanPlayer.getRank());
                }
                statMessage.add("&7&m-----------------------------------");
                statMessage.forEach(message -> player.sendMessage(StringUtil.format(message)));
            } else {
                player.sendMessage(ChatColor.RED + "Target not found.");
            }
        }

        return false;
    }
}
