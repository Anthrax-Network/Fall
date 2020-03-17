package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (!profile.isStaff()) {
            player.sendMessage(StringUtil.format("&cYou do not have permission to execute this command."));
        }
        if (args.length == 0) {
            player.sendMessage(StringUtil.format("&c/rename <args>"));
        } else {
            if (player.getItemInHand() == null) {
                player.sendMessage(StringUtil.format("&cItem not found."));
                return true;
            }
            ItemStack itemStack = player.getItemInHand();
            String name = "";
            for (int i = 0; i < args.length; ++i) {
                name = name + args[i] + " ";
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(StringUtil.format(name));
            itemStack.setItemMeta(itemMeta);
            player.sendMessage(StringUtil.format("&aYou have renamed the current item in your hand."));
        }
        return false;
    }
}
