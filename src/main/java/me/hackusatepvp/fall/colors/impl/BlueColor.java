package me.hackusatepvp.fall.colors.impl;

import me.hackusatepvp.fall.colors.Color;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BlueColor extends Color {
    public BlueColor() {
        super(StringUtil.format("&7* &9Blue"));
    }

    @Override
    public String getPrefix() {
        return "&9";
    }

    @Override
    public String getPermission() {
        return "fall.colors.blue";
    }

    @Override
    public ItemStack getIcon() {
        ItemStack itemStack = new ItemStack(Material.INK_SACK, 1, (byte) 12);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&7* &9Blue"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public void onClick(Player player, Profile profile, ItemStack itemStack) {
        if (itemStack.getItemMeta().getDisplayName().equals(getIcon().getItemMeta().getDisplayName())) {
            if (player.hasPermission(getPermission())) {
                profile.setColor(getPrefix());
                player.sendMessage(ChatColor.GREEN + "You have updated your color.");
            } else {
                player.sendMessage(ChatColor.RED + "You do not have this color unlocked.");
            }
        } else {
            player.sendMessage("Color not found!");
        }
    }
}
