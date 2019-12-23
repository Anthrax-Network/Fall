package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Iterator;

public class ListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //todo vanish
        String[] list = new String[] {
                "&7&m--------------------------------------",
                "",
                "&7&m--------------------------------------",
        };
        for (String msg : list) {
            msg = msg.replace("%ONLINE%", getStaffOnline() + "");
            sender.sendMessage(StringUtil.format(msg));
        }
        return false;
    }

    private int getStaffOnline() {
        int staff = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
            if (profile.isStaff()) {
                staff = staff + 1;
            }
        }
        return staff;
    }
}
