package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.util.Message;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReplyCommand implements CommandExecutor {
    public ReplyCommand() {
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /reply <message>");
        } else if (MessageCommand.reply.get(sender) == null) {
            sender.sendMessage(ChatColor.RED + "No one to reply to.");
        } else if (((Player)MessageCommand.reply.get(sender)).getName() == sender.getName()) {
            sender.sendMessage(ChatColor.RED + "You cannot message yourself!");
        } else if (args[0].equalsIgnoreCase("who")) {
            sender.sendMessage(Message.format("&bYou are in a conversation with: &a" + ((Player)MessageCommand.reply.get(sender)).getName()));
        } else {
            if (Fall.getInstance().getProfileManager().getProfile(MessageCommand.reply.get(sender).getUniqueId()).isMessages() || sender.hasPermission("togglepm.bypass")) {
                String msgto = Message.format("&7(&9me &b-> &c" + ((Player)MessageCommand.reply.get(sender)).getName() + "&7) &f" + Message.toString(args, 0));
                sender.sendMessage(msgto);
                String msgget = Message.format("&7(&9" + sender.getName() + " &b-> &cme&7) &f" + Message.toString(args, 0));
                ((Player)MessageCommand.reply.get(sender)).sendMessage(Message.format(msgget));
                return true;
            }

            sender.sendMessage(Message.format("&6" + ((Player)MessageCommand.reply.get(sender)).getName() + "&e doesn't have messages enabled!"));
        }

        return false;
    }
}
