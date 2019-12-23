package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class DiscordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        String[] discord = new String[] {
                "&7&m------------------------------",
                "&c* &9Discord: &7:https://discord.gg/6dPU8wW",
                "&7&m------------------------------",
        };
        Arrays.asList(discord).forEach(line -> sender.sendMessage(StringUtil.format(line)));
        return false;
    }

    public String format(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}