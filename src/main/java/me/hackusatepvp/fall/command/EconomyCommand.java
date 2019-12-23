package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.NumberFormat;

public class EconomyCommand implements CommandExecutor {
    //TODO cleanup the messages

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
            if (args.length == 0) {
                double bal = Fall.getInstance().getEconomyManager().getBalance(profile);
                NumberFormat format = NumberFormat.getCurrencyInstance();
                sender.sendMessage(ChatColor.RED + "Your balance is " + ChatColor.DARK_RED + format.format(bal));
            } else {
                if (player.hasPermission("fatekits.command.balance.admin")) {
                    if (args[0].equalsIgnoreCase("set")) {
                        if (args.length == 1) {
                            player.sendMessage(ChatColor.RED + "Usage: /bal set <player> amount");
                            return true;
                        }
                        if (args.length == 2) {
                            player.sendMessage(ChatColor.RED + "Usage: /bal set " + args[1] + " amount");
                            return true;
                        }

                        if (args.length == 3) {
                            Player target = Bukkit.getPlayerExact(args[1]);
                            if (target != null) {
                                Profile tar = Fall.getInstance().getProfileManager().getProfile(target.getUniqueId());
                                Fall.getInstance().getEconomyManager().setBalance(tar, Double.parseDouble(args[2]));
                                player.sendMessage(ChatColor.GRAY + "You have set the balance of " + ChatColor.RED + target.getName() + ChatColor.GRAY + " to " + ChatColor.GOLD + Double.parseDouble(args[2]));
                            } else {
                                OfflinePlayer offplayer = Bukkit.getOfflinePlayer(args[0]);
                                Profile profile1 = Fall.getInstance().getProfileManager().getProfile(offplayer.getUniqueId());
                                Fall.getInstance().getEconomyManager().setBalance(profile1, Double.parseDouble(args[2]));
                                player.sendMessage(ChatColor.GRAY + "You have set the balance of " + ChatColor.RED + offplayer.getName() + ChatColor.GRAY + " to " + ChatColor.GOLD + Double.parseDouble(args[2]));
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You do not have permissions.");
                }
                if (args[0].equalsIgnoreCase("add")) {
                    if (player.hasPermission("fatekits.balance.admin")) {
                        if (args.length == 1) {
                            player.sendMessage(ChatColor.RED + "Usage: /bal add <player> <amount>");
                            return true;
                        }
                        if (args.length == 2) {
                            player.sendMessage(ChatColor.RED + "Usage: /bal add " + args[1] + " amount");
                            return true;
                        }

                        if (args.length == 3) {
                            Player target = Bukkit.getPlayerExact(args[1]);
                            double amount = Double.parseDouble(args[2]);
                            if (target != null) {
                                Profile tar = Fall.getInstance().getProfileManager().getProfile(target.getUniqueId());
                                Fall.getInstance().getEconomyManager().addBalance(tar, amount);
                                player.sendMessage(ChatColor.GRAY + "You have added " + amount + " to " + ChatColor.RED + target.getName() + "'s " + ChatColor.GRAY + "balance.");
                            } else {
                                OfflinePlayer offplayer = Bukkit.getOfflinePlayer(args[0]);
                                Profile tar = Fall.getInstance().getProfileManager().getProfile(offplayer.getUniqueId());
                                Fall.getInstance().getEconomyManager().addBalance(tar, amount);
                                player.sendMessage(ChatColor.GRAY + "You have added " + amount + " to " + ChatColor.RED + offplayer.getName() + "'s " + ChatColor.GRAY + "balance.");
                            }
                        }

                    } else {
                        sender.sendMessage(ChatColor.RED + "You do not have permissions.");
                    }
                } else {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (args.length == 1) {
                        if (target != null) {
                            sender.sendMessage(ChatColor.RED + args[0] + "'s balance is " + ChatColor.DARK_RED + ChatColor.UNDERLINE + Fall.getInstance().getProfileManager().getProfile(target.getUniqueId()).getBalance());
                        } else {
                            OfflinePlayer offplayer = Bukkit.getOfflinePlayer(args[0]);
                            sender.sendMessage(ChatColor.RED + args[0] + "'s balance is " + ChatColor.DARK_RED + ChatColor.UNDERLINE + Fall.getInstance().getProfileManager().getProfile(offplayer.getUniqueId()).getBalance());
                        }
                    }
                }
            }
        } else {
            sender.sendMessage(ChatColor.DARK_RED + "You must be a player to use this command.");
        }
        return false;
    }
}