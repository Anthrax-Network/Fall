package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class FallCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        String[] helpMessage = new String[] {
                "&7&m--------------------------------------------",
                "&9&l Command Help",
                "&1* &b/about &7(View all plugin information)",
                "&1* &b/fatetop &7(View the top players on the server)",
                "&1* &b/ping &7(Shows current connection to the server)",
                "&1* &b/kits &7(View and select kits)",
                "&1* &b/level &7(Displays current level)",
                "&1* &b/message &7(Send a direct message to a player)",
                "&1* &b/quest &7(Show all quest info)",
                "&1* &b/rank &7(Show your personal rank info",
                "&1* &b/report &7Report a player for reasonable cause",
                "&1* &b/request &7(Request for staff assistance)",
                "&1* &b/stats &7(Show your personal stats)",
                "&7&m--------------------------------------------",
        };
        Arrays.asList(helpMessage).forEach(msg -> sender.sendMessage(StringUtil.format(msg)));
        return false;
    }
}
