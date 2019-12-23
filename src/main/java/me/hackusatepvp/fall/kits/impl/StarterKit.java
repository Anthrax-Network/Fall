package me.hackusatepvp.fall.kits.impl;

import me.hackusatepvp.fall.kits.Kits;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;

public class StarterKit extends Kits {

    public StarterKit() {
        super("WOOD_SWORD");
    }

    @Override
    public String getName() {
        return "Starter";
    }

    @Override
    public String getColor() {
        return "&f";
    }

    @Override
    public String getPermission() {
        return "fall.kits.starter";
    }

    @Override
    public String getDelay() {
        return "1m";
    }

    @Override
    public String getCommand() {
        return "kit Starter";
    }

    @Override
    public ItemStack getIcon() {
        ItemStack itemStack = new ItemStack(Material.WOOD_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format(getColor() + "&l" + getName() + " Kit"));
        itemMeta.setLore(StringUtil.format(Arrays.asList("&7Default Kit", "", "&7Delay: " + getColor() + getDelay(), "&7Permission: " + getColor() + getPermission(),
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
