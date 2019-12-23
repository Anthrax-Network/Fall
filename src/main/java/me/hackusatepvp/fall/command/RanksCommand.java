package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class RanksCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
      /*  sender.sendMessage(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "--------------------------------------------");
        sender.sendMessage(ChatColor.GOLD + "* " + ChatColor.WHITE + "Starter: " + ChatColor.GRAY + "0 kills");
        sender.sendMessage(ChatColor.GOLD + "* " + ChatColor.GRAY + "Master: " + ChatColor.GRAY + "15 kills");
        sender.sendMessage(ChatColor.GOLD + "* " + ChatColor.GREEN + "Rider: " + ChatColor.GRAY + "30 kills"); // i've never done that before with this kinda system but I BELIEVE (over 50% sure) you can do it in one class
        sender.sendMessage(ChatColor.GOLD + "* " + ChatColor.LIGHT_PURPLE + "Caster: " + ChatColor.GRAY + "60 kills");
        sender.sendMessage(ChatColor.GOLD + "* " + ChatColor.YELLOW + "Archer: " + ChatColor.GRAY + "120 kills");
        sender.sendMessage(ChatColor.GOLD + "* " + ChatColor.DARK_BLUE + "Assassin: " + ChatColor.GRAY + "240 kills");
        sender.sendMessage(ChatColor.GOLD + "* " + ChatColor.DARK_PURPLE + "Lancer: " + ChatColor.GRAY + "480 kills");
        sender.sendMessage(ChatColor.GOLD + "* " + ChatColor.GOLD + "Berserker: " + ChatColor.GRAY + "960 kills");
        sender.sendMessage(ChatColor.GOLD + "* " + ChatColor.BLUE + "Saber: " + ChatColor.GRAY + "1920 kills");
        sender.sendMessage(ChatColor.GOLD + "* " + ChatColor.DARK_RED + "Fate: " + ChatColor.GRAY + "3840 kills");
        sender.sendMessage(ChatColor.GOLD + "* " + ChatColor.DARK_RED + "I BET: " + ChatColor.GRAY + "7680 kills");
        sender.sendMessage(ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + "--------------------------------------------"); */
        String[] message = new String[] {
          "&7&m--------------------------------------------",
          "&6* &fStarter: &70 kills",
          "&6* &7Master: 15 kills",
          "&6* &aRider: &730 kills",
          "&6* &dCaster: &760 kills",
          "&6* &eArcher: &7120 kills",
          "&6* &1Assassin: &7240 kills",
          "&6* &5Lancer: &7480 kills",
          "&6* Berserker: &7960 kills",
          "&6* &9Saber: &71920 kills",
          "&6* &4Fate: &73840 kills"
        };
        Arrays.asList(message).forEach(msg -> sender.sendMessage(StringUtil.format(msg)));
        return false;
    }
}