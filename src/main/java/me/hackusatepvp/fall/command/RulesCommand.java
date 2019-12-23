package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.hackusatepvp.fall.util.StringUtil.format;

public class RulesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {

            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(StringUtil.format("&7&m----------------------------------------"));
            player.sendMessage(format("&9&lRules &7Help"));
            player.sendMessage(format(""));
            player.sendMessage(format("&b/rules alts &7(view all alt account rules&7)"));
            player.sendMessage(format("&b/rules chat &7(view all chat rules&7)"));
            player.sendMessage(format("&b/rules mods &7(view all modification rules&7)"));
            player.sendMessage(format("&b/rules pvp &7(view all pvp rules&7)"));
            player.sendMessage(StringUtil.format("&7&m----------------------------------------"));
        } else {
            if (args[0].equalsIgnoreCase("alts")) {
                player.sendMessage(StringUtil.format("&7&m----------------------------------------"));
                player.sendMessage(format("&9Alt Account &7Rules"));
                player.sendMessage("");
                player.sendMessage(format("&b#1 &3- &7Boosting on alt accounts will result in stats being wiped on both accounts and a month ban on all accounts."));
                player.sendMessage(format("&b#2 &3- &7Ban evading will result in an increase of the ban or even a blacklist."));
                player.sendMessage(format("&b#3 &3- &7Mute evading will result in a 1 week ban.&7"));
                player.sendMessage(StringUtil.format("&7&m----------------------------------------"));
            }
            if (args[0].equalsIgnoreCase("alt")) {
                player.sendMessage(StringUtil.format("&7&m----------------------------------------"));
                player.sendMessage(format("&9Alt Account &7Rules"));
                player.sendMessage("");
                player.sendMessage(format("&b#1 &3- &7Boosting on alt accounts will result in stats being wiped on both accounts and a month ban on all accounts."));
                player.sendMessage(format("&b#2 &3- &7Ban evading will result in an increase of the ban or even a blacklist."));
                player.sendMessage(StringUtil.format("&7&m----------------------------------------"));
            }
            if (args[0].equalsIgnoreCase("chat")) {
                player.sendMessage(StringUtil.format("&7&m----------------------------------------"));
                player.sendMessage(format("&9Chat &7Rules"));
                player.sendMessage("");
                player.sendMessage(format("&b#1 &3- &7Spamming will result in a warning, excessive spamming will result in a temporary ban or mute."));
                player.sendMessage(format("&b#2 &3- &7Racial slurs will result in a 3 month ban. No exceptions."));
                player.sendMessage(format("&b#3 &3- &7Disrespecting players or staff will result in a one week mute"));
                player.sendMessage(format("&b#4 &3- &7Toxicity will result in a one day mute."));
                player.sendMessage(format("&b#5 &3- &7Advertising will result in a one month ban."));
                player.sendMessage(format("&b#6 &3- &7DDOSing, Doxing, impersonating all result in a blacklist, this includes threats."));
                player.sendMessage(format("&b#7 &3- &7Death threats will result in 3 day mute this includes \"kys\"."));
                player.sendMessage(StringUtil.format("&7&m----------------------------------------"));
            }
            if (args[0].equalsIgnoreCase("mods")) {
                player.sendMessage(StringUtil.format("&7&m----------------------------------------"));
                player.sendMessage(format("&b#1 &3- &7Blacklisted mods will result in a 3 month ban."));
                player.sendMessage(StringUtil.format("&7&m----------------------------------------"));
            }
            if (args[0].equalsIgnoreCase("pvp")) {
                player.sendMessage(StringUtil.format("&7&m----------------------------------------"));
                player.sendMessage(format("&b#1 &3- &7Any combat hacks will result in a permanent ban."));
                player.sendMessage(format("&b#2 &3- &7Macros will result in a permanent ban."));
                player.sendMessage(format("&b#3 &3- &7Movement cheats will result in a permanent ban."));
                player.sendMessage(StringUtil.format("&7&m----------------------------------------"));
            }
        }
        return false;
    }
}