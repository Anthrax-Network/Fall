package me.hackusatepvp.fall.tags.common.impl;

import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.tags.Tags;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HeartTag extends Tags {

    public HeartTag() {
        super(StringUtil.format("&cHeart &7Tag"));
    }

    @Override
    public String getName() {
        return "&cHeart &7Tag";
    }

    @Override
    public String getPrefix() {
        return "&7[&c❤&7]";
    }

    @Override
    public String getPermission() {
        return "fall.tags.common.heart";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public ItemStack getIcon() {
        ItemStack itemStack = new ItemStack(Material.NAME_TAG);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&cHeart &7Tag"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public void onClick(Player player, Profile profile, String tag) {
        if (tag.contains("Heart")) {
            if (player.hasPermission(getPermission())) {
                if (isEnabled()) {
                    if (!profile.getTag().equals("null")) {
                        profile.setTag("null");
                        player.sendMessage(ChatColor.RED + "You have removed your tag.");
                    } else {
                        profile.setTag(StringUtil.format(getName()));
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
