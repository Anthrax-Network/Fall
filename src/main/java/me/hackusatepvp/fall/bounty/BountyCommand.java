package me.hackusatepvp.fall.bounty;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class BountyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (args.length == 0) {
            String[] message = new String[] {
                    "&7&m------------------------------------------",
                    "&9&lBounty Information",
                    "&7/bounty &bcheck",
                    "&7/bounty &bset",
                    "&7m-------------------------------------------",
            };
            Arrays.asList(message).forEach(instance -> player.sendMessage(StringUtil.format(instance)));
        } else {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("check")) {
                    if (profile.isBounty()) {
                        player.sendMessage(StringUtil.format("&7Your bounty is &9" + Fall.getInstance().getBountyManager().getBounty(player)));
                    } else {
                        player.sendMessage(StringUtil.format("&cYou have no bounty."));
                    }
                } else if (args[0].equalsIgnoreCase("set")) {
                    player.sendMessage(StringUtil.format("&c/bounty set <player> <amount>"));
                } else if (args[0].equalsIgnoreCase("admin")) {
                    if (player.hasPermission("fall.admin")) {
                        player.sendMessage(StringUtil.format("&c/bounty admin <set|remove|add> <player> <amount>"));
                    } else {
                        player.sendMessage(StringUtil.format("&cYou do not have permissions to execute this command."));
                    }
                } else {
                    player.sendMessage(StringUtil.format("&cArgument not found."));
                }
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("check")) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    if (target != null) {
                        Profile tar = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
                        if (!tar.isBounty()) {
                            player.sendMessage(ChatColor.RED + "Player does not have a bounty.");
                            return true;
                        }
                        player.sendMessage(StringUtil.format("&c" + target.getName() + " &7bounty is &9" + Fall.getInstance().getBountyManager().getBounty(player)));
                    }
                } else if (args[0].equalsIgnoreCase("set")) {
                    player.sendMessage(StringUtil.format("&c/bounty set <" + args[1] + "> <amount>"));
                } else if (args[0].equalsIgnoreCase("admin")) {
                    if (player.hasPermission("fall.admin")) {
                        player.sendMessage(StringUtil.format("&c/bounty admin <set|remove|add> <player> <amount>"));
                    } else {
                        player.sendMessage(StringUtil.format("&cYou do not have permissions to execute this command."));
                    }
                } else {
                    player.sendMessage(StringUtil.format("&cArgument not found."));
                }
            }
            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("set")) {
                    Player target = Bukkit.getPlayerExact(args[1]);
                    Profile tar = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
                    Double amount = Double.parseDouble(args[2]);
                    if (profile.getBalance() < amount) {
                        player.sendMessage(StringUtil.format("&cYou do not have enough money."));
                        return true;
                    }
                    if (target.getName().equalsIgnoreCase(player.getName())) {
                        player.sendMessage(StringUtil.format("&cYou cannot set a bounty on yourself."));
                        return true;
                    }
                    if (amount == 0) {
                        player.sendMessage(StringUtil.format("&cInvalid amount."));
                        return true;
                    }
                    if (tar.isBounty()) {
                        player.sendMessage(StringUtil.format("&cPlayer already has a current bounty."));
                    }
                    if (Fall.getInstance().getClanManager().inClan(target.getUniqueId())) {
                        if (Fall.getInstance().getClanManager().inClan(player.getUniqueId())) {
                            if (Fall.getInstance().getClanManager().getClan(Fall.getInstance().getClanManager().getClanPlayer(target)).equals(Fall.getInstance().getClanManager().getClan(Fall.getInstance().getClanManager().getClanPlayer(player)))) {
                                player.sendMessage(StringUtil.format("&cYou cannot set bounties on fellow clan members."));
                                return true;
                            }
                        }
                    }
                    Fall.getInstance().getBountyManager().setBounty(player, true, amount);
                    tar.setBounty(true);
                    Bukkit.getOnlinePlayers().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + player.getName() + " &7has set a &a" + amount + "$ &7bounty on &c" + target.getName())));
                } else if (args[0].equalsIgnoreCase("admin")) {
                    if (player.hasPermission("fall.admin")) {
                        if (args[1].equalsIgnoreCase("remove")) {
                            Player target = Bukkit.getPlayerExact(args[2]);
                            if (target != null) {
                                Profile tar = Fall.getInstance().getProfileManager().getProfile(target.getUniqueId());
                                tar.setBountyamount(0.0);
                                tar.setBounty(false);
                                player.sendMessage(StringUtil.format("&7You have removed the bounty off of &9" + tar.getName()));
                            }
                        }
                    } else {
                        player.sendMessage(StringUtil.format("&cYou do not have permissions to execute this command."));
                    }
                } else {
                    player.sendMessage(StringUtil.format("&cArgument not found."));
                }
            }
            if (args.length == 4) {
                if (args[0].equalsIgnoreCase("admin")) {
                    if (player.hasPermission("fall.admin")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            Player target = Bukkit.getPlayerExact(args[2]);
                            double amount = Double.parseDouble(args[3]);
                            if (target != null) {
                                Profile tar = Fall.getInstance().getProfileManager().getProfile(target.getUniqueId());
                                tar.setBountyamount(amount);
                                player.sendMessage(StringUtil.format("&7You have set the current bounty on &c" + target.getName() + " &7to &a" + amount));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            Player target = Bukkit.getPlayerExact(args[2]);
                            double amount = Double.parseDouble(args[3]);
                            if (target != null) {
                                Profile tar = Fall.getInstance().getProfileManager().getProfile(target.getUniqueId());
                                tar.setBountyamount(tar.getBountyamount() + amount);
                                player.sendMessage(StringUtil.format("&7You have set the current bounty on &c" + target.getName() + " &7to &a" + tar.getBountyamount()));
                            }
                        }
                    } else {
                        player.sendMessage(StringUtil.format("&cYou do not have permissions to execute this command."));
                    }
                }
            }
        }
        return false;
    }
}
