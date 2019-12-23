package me.hackusatepvp.fall.tags.special.impl;

import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.tags.Tags;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChristmasTag extends Tags {

    public ChristmasTag() {
        super("Christmas");
    }

    @Override
    public String getName() {
        return "Christmas";
    }

    @Override
    public String getPrefix() {
        return "&7[&4C&2h&fr&9i&4s&2t&fm&9a&4s&7]";
    }

    @Override
    public String getPermission() {
        return "fall.tags.special.christmas";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public ItemStack getIcon() {
        ItemStack itemStack = new ItemStack(Material.SNOW_BALL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&4Christmas &7Tag"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public void onClick(Player player, Profile profile, String tag) {
        if (tag.contains("Christmas")) {
            if (player.hasPermission(getPermission())) {
                if (isEnabled()) {
                    if (!profile.getTag().equals("null")) {
                        profile.setTag("null");
                        player.sendMessage(ChatColor.RED + "You have removed your tag.");
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
