package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class RanksCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        String[] message = new String[] {
          "&7&m--------------------------------------------",
          "&6* &fStarter: &70 kills",
          "&6* &7Master: &715 kills",
          "&6* &aRider: &730 kills",
          "&6* &dCaster: &760 kills",
          "&6* &eArcher: &7120 kills",
          "&6* &1Assassin: &7240 kills",
          "&6* &5Lancer: &7480 kills",
          "&6* Berserker: &7960 kills",
          "&6* &9Saber: &71920 kills",
          "&6* &4Fate: &73840 kills",
          "&7&m--------------------------------------------",
        };
        Arrays.asList(message).forEach(msg -> sender.sendMessage(StringUtil.format(msg)));
        return false;
    }
}