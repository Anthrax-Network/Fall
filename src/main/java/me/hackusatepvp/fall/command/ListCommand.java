package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.staff.Staff;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //todo vanish
        List<String> lines = new ArrayList<>();
        lines.add("&7&m--------------------------------------");
        lines.add( "&7There are &9" + getOnline());
        lines.add("");
        lines.add("&9Online Staff:");
        if (Fall.getInstance().getStaffManager().getStaff().size() == 0) {
            lines.add("&cNo staff online.");
            return true;
        }
        List<String> staff = new ArrayList<>();
        for (Player online : Bukkit.getOnlinePlayers()) {
            Profile profile = Fall.getInstance().getProfileManager().getProfile(online.getUniqueId());
            if (profile.isStaff()) {
                if (!Fall.getInstance().getStaffManager().isVanish(online)) {
                    staff.add(online.getDisplayName());
                    lines.add(staff.toString());
                }
            }
        }
        lines.forEach(msg -> sender.sendMessage(StringUtil.format(msg)));
        lines.add("&7&m--------------------------------------");
        return false;
    }

    /*private int getStaffOnline() {
        int staff = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
            if (profile.isStaff()) {
                if (!Fall.getInstance().getStaffManager().isVanish(player)) {
                    staff++;
                }
            }
        }
        return staff;
    } */
    public int getOnline() {
        int online = Bukkit.getOnlinePlayers().size();
        int vanish = Fall.getInstance().getStaffManager().getVanish().size();
        return online - vanish;
    }
}
