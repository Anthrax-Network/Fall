package me.hackusatepvp.fall.kits.impl;

import me.hackusatepvp.fall.kits.Kits;
import me.hackusatepvp.fall.quests.Quest;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class RiderKit extends Kits {

    public RiderKit() {
        super("SADDLE");
    }

    @Override
    public String getName() {
        return "Rider";
    }

    @Override
    public String getColor() {
        return "&a";
    }

    @Override
    public String getPermission() {
        return "fall.kits.rider";
    }

    @Override
    public String getDelay() {
        return "1h";
    }

    @Override
    public String getCommand() {
        return "kit Rider";
    }

    @Override
    public ItemStack getIcon() {
        ItemStack itemStack = new ItemStack(Material.SADDLE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format(getColor() + "&l" + getName() + " Kit"));
        itemMeta.setLore(StringUtil.format(Arrays.asList("&7Purchase at " + getColor() + "store.fatekits.net", "&7Unlocks at " + getColor() + "&n" + Quest.getByName(getName()).getGoal() + "&r&7 kills", "", "&7Delay: " + getColor() + getDelay(), "&7Permission: " + getColor() + getPermission(),
                "&7Override: " + getColor() + "false", "", "&7Click to equip this kit.")));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public void onClick(Player player, ItemStack itemStack) {
        if (itemStack.getItemMeta().getDisplayName().equals(getIcon().getItemMeta().getDisplayName())) {
            if (player.hasPermission(getPermission())) {
                player.performCommand(getCommand());
            } else {
                player.sendMessage(ChatColor.RED + "You do not have this kit unlocked.");
            }
        } else {
            player.sendMessage("Try again.");
        }
    }
}
