package me.hackusatepvp.fall.tags.rare.impl;

import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.tags.Tags;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShrugTag extends Tags {

    public ShrugTag() {
        super(StringUtil.format("Shrug"));
    }

    @Override
    public String getName() {
        return StringUtil.format("&1¯\\_(ツ)_/¯ &7Tag");
    }

    @Override
    public String getPrefix() {
        return "&7[&1¯\\_(ツ)_/¯&7]";
    }

    @Override
    public String getPermission() {
        return "fall.tags.rare.shrug";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public ItemStack getIcon() {
        ItemStack itemStack = new ItemStack(Material.NAME_TAG);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&1¯\\_(ツ)_/¯ &7Tag"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public void onClick(Player player, Profile profile, String tag) {
        if (tag.contains("ツ")) {
            if (player.hasPermission(getPermission())) {
                if (isEnabled()) {
                    if (!profile.getTag().equals("null")) {
                        if (profile.getTag().equalsIgnoreCase(getName())) {
                            profile.setTag("null");
                            player.sendMessage(ChatColor.RED + "You have removed your tag.");
                        }
                    } else {
                        profile.setTag(getName());
                        player.sendMessage(ChatColor.GREEN + "You have updated your tag.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "This tag is no longer available for use.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You do not have this tag unlocked.");
            }
        }
    }
}
