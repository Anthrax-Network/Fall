package me.hackusatepvp.fall.staff.commands;

import me.hackusatepvp.fall.Fall;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("fall.staff")) {
            player.sendMessage(ChatColor.RED + "You do not have permissions to perform this command.");
            return true;
        }
        if (Fall.getInstance().getStaffManager().isStaffMode(player)) {
            Fall.getInstance().getStaffManager().removeStaffMode(player);
            Fall.getInstance().getStaffManager().removeVanish(player);
            player.getInventory().setContents(Fall.getInstance().getStaffManager().getInventory().get(player));
            player.getInventory().setArmorContents(Fall.getInstance().getStaffManager().getArmor().get(player));
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(ChatColor.RED + "You have removed your staff mode.");
            return true;
        }
        Fall.getInstance().getStaffManager().setStaffMode(player);
        Fall.getInstance().getStaffManager().setVanish(player);
        Fall.getInstance().getStaffManager().getInventory().put(player, player.getInventory().getContents());
        Fall.getInstance().getStaffManager().getArmor().put(player, player.getInventory().getArmorContents());
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setGameMode(GameMode.CREATIVE);
        Bukkit.getOnlinePlayers().forEach(instance -> instance.hidePlayer(player));
        Fall.getInstance().getStaffManager().giveItems(player);
        return false;
     }
}
