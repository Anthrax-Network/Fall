package me.hackusatepvp.fall.classes.impl;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.classes.Classes;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MasterClass extends Classes {

    public MasterClass() {
        super("Master");
    }

    @Override
    public String getName() {
        return "Master";
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.INK_SACK, 1, (byte) 8);
    }

   @Override
    public List<ItemStack> getItems() {
        ItemStack magic = new ItemStack(Material.GOLD_HOE);
        ItemMeta magicm = magic.getItemMeta();
        magicm.setDisplayName(StringUtil.format("&7*&9Magic&7*"));
        magic.setItemMeta(magicm);
        return Collections.singletonList(magic);
    }

    @Override
    public String getPrefix() {
        return StringUtil.format("&7Master &7Class");
    }

    @Override
    public Integer getCooldown() {
        return 8600;
    }

    @Override
    public String getPermission() {
        return "fall.classes.master";
    }

    @Override
    public void onTick(Player player, Profile profile) {
        if (Fall.getInstance().getClassesManager().isCooldown(player)) {
            int left = Fall.getInstance().getClassesManager().getCooldown(player);
            --left;
            Fall.getInstance().getClassesManager().setCooldown(player, left);
            if (left == 0) {
                Fall.getInstance().getClassesManager().removeCooldown(player);
                player.sendMessage(ChatColor.GREEN + "Your class cooldown has expired.");
            }
        }
    }

    @Override
    public void onItemUse(Player player, Classes classes, ItemStack itemStack) {
        if (itemStack.getType() == Material.GOLD_HOE) {
            Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
            if (profile.getActiveQuest().equals("Master")) {

            } else {
                player.getInventory().removeItem(itemStack);
            }
        }
    }
}

