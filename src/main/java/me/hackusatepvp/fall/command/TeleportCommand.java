package me.hackusatepvp.fall.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("fatekits.command.teleport")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Usage: /tp [player|target1|x] [target|target2|y|x] [x|y|z] [y|z] [z]");
                } else {
                    if (args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            player.teleport(target.getLocation());
                            player.sendMessage("You have teleported to " + target.getName() + ".");
                        } else {
                            player.sendMessage("Target not found.");
                        }
                    }

                    if (args.length == 2) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        Player target2 = Bukkit.getPlayerExact(args[1]);
                        if (target != null) {
                            if (target2 != null) {
                                target.teleport(target2.getLocation());
                            } else {
                                player.sendMessage(args[1] + " not found.");
                            }
                        } else {
                            player.sendMessage(args[0] + " not found.");
                        }
                    }

                    if (args.length == 3) {
                        double x = Double.parseDouble(args[0]);
                        double y = Double.parseDouble(args[1]);
                        double z = Double.parseDouble(args[2]);
                        player.teleport(new Location(player.getWorld(), x, y, z));
                        player.sendMessage("You have teleported to (" + x + "," + y + "," + z + ")");
                    }

                    if (args.length == 4) {
                        Player targt = Bukkit.getPlayerExact(args[0]);
                        if (targt != null) {
                            double x = Double.parseDouble(args[1]);
                            double y = Double.parseDouble(args[2]);
                            double z = Double.parseDouble(args[3]);
                            targt.teleport(new Location(targt.getWorld(), x, y, z));
                            player.sendMessage("You have teleported " + targt.getName() + " to (" + x + "," + y + "," + z + ")");
                        } else {
                            player.sendMessage("Target not found.");
                        }
                    }
                }
            } else {
                player.sendMessage("You do not have permissions.");
            }
        } else {
            sender.sendMessage("You must be a player.");
        }
        return false;
    }
}