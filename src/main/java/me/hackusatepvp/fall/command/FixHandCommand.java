package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.classes.Classes;
import me.hackusatepvp.fall.profile.Profile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FixHandCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (profile.getDonor().equalsIgnoreCase("starter")) {
            player.sendMessage(ChatColor.RED + "You must be a donor to use this command.");
            return true;
        }
        if (profile.getBalance() < 500) {
            player.sendMessage(ChatColor.RED + "You do not have enough balance.");
            return true;
        }
        ItemStack itemStack = player.getItemInHand();
        if (itemStack.getDurability() == 0) {
            player.sendMessage(ChatColor.RED + "Cannot repair the item.");
            return true;
        }
        if (itemStack.getAmount() == 0) {
            player.sendMessage(ChatColor.RED + "Cannot repair the item.");
            return true;
        }
        for (ItemStack item : Classes.ARCHER_CLASS.getItems()) {
            if (item.getItemMeta().getDisplayName().equals(itemStack.getItemMeta().getDisplayName())) {
                player.sendMessage(ChatColor.RED + "Cannot repair the item.");
                return true;
            }
        }
        for (ItemStack item : Classes.ASSASSIN_CLASS.getItems()) {
            if (item.getItemMeta().getDisplayName().equals(itemStack.getItemMeta().getDisplayName())) {
                player.sendMessage(ChatColor.RED + "Cannot repair the item.");
                return true;
            }
        }
        for (ItemStack item : Classes.BERSERKER_CLASS.getItems()) {
            if (item.getItemMeta().getDisplayName().equals(itemStack.getItemMeta().getDisplayName())) {
                player.sendMessage(ChatColor.RED + "Cannot repair the item.");
                return true;
            }
        }
        for (ItemStack item : Classes.CASTER_CLASS.getItems()) {
            if (item.getItemMeta().getDisplayName().equals(itemStack.getItemMeta().getDisplayName())) {
                player.sendMessage(ChatColor.RED + "Cannot repair the item.");
                return true;
            }
        }
        for (ItemStack item : Classes.FATE_CLASS.getItems()) {
            if (item.getItemMeta().getDisplayName().equals(itemStack.getItemMeta().getDisplayName())) {
                player.sendMessage(ChatColor.RED + "Cannot repair the item.");
                return true;
            }
        }
        for (ItemStack item : Classes.LANCER_CLASS.getItems()) {
            if (item.getItemMeta().getDisplayName().equals(itemStack.getItemMeta().getDisplayName())) {
                player.sendMessage(ChatColor.RED + "Cannot repair the item.");
                return true;
            }
        }
        for (ItemStack item : Classes.FATE_CLASS.getItems()) {
            if (item.getItemMeta().getDisplayName().equals(itemStack.getItemMeta().getDisplayName())) {
                player.sendMessage(ChatColor.RED + "Cannot repair the item.");
                return true;
            }
        }
        for (ItemStack item : Classes.SABER_CLASS.getItems()) {
            if (item.getItemMeta().getDisplayName().equals(itemStack.getItemMeta().getDisplayName())) {
                player.sendMessage(ChatColor.RED + "Cannot repair the item.");
                return true;
            }
        }
        for (ItemStack item : Classes.RIDER_CLASS.getItems()) {
            if (item.getItemMeta().getDisplayName().equals(itemStack.getItemMeta().getDisplayName())) {
                player.sendMessage(ChatColor.RED + "Cannot repair the item.");
                return true;
            }
        }
        itemStack.setDurability((short) 0);
        player.sendMessage(ChatColor.GREEN + "You have repaired the item in your hand.");
        Fall.getInstance().getEconomyManager().subtractBalance(profile, 500D);
        return false;
    }
}
