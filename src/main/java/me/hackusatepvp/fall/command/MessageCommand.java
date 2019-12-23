package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MessageCommand implements CommandExecutor {
    public static HashMap<CommandSender, Player> reply = new HashMap();

    public MessageCommand() {
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length <= 1) {
                sender.sendMessage(ChatColor.RED + "Usage: /message <player> <message>");
            } else if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(ChatColor.RED + "Player isn't online.");
            } else if (Bukkit.getPlayer(args[0]).getName() == sender.getName()) {
                sender.sendMessage(ChatColor.RED + "You cannot message yourself");
            } else {
                Player target = Bukkit.getPlayerExact(args[0]);
                Profile tar = Fall.getInstance().getProfileManager().getProfile(target.getUniqueId());
                boolean toggle = tar.isMessages();
                if (toggle || sender.hasPermission("fatekits.staff")) {
                    Player player = ((Player) sender).getPlayer();
                    String msgto = Message.format("&7(&9me &b-> &c" + Bukkit.getPlayer(args[0]).getName() + "&7) &f" + Message.toString(args, 1));
                    sender.sendMessage(msgto);
                    String msgrecieve = Message.format("&7(&9" + sender.getName() + " &b->  &cme&7) &f" + Message.toString(args, 1));
                    Bukkit.getPlayer(args[0]).sendMessage(msgrecieve);
                    reply.put(sender, Bukkit.getPlayer(args[0]));
                    reply.put(Bukkit.getPlayer(args[0]), (Player)sender);
                    if (Fall.getInstance().getProfileManager().getProfile(player.getUniqueId()).isMessagesound()) {
                        player.playSound(target.getLocation(), Sound.NOTE_PLING, 1, 1);
                    }
                    //todo get staff and send them messages if they have social spy on.
                    return true;
                }

                sender.sendMessage(Message.format("&6" + Bukkit.getPlayer(args[0]).getName() + "&e doesn't have messages enabled!"));
            }
        }

        return false;
    }
}
