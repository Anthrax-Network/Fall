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
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RiderClass extends Classes {

    public RiderClass() {
        super("Rider");
    }

    @Override
    public String getName() {
        return "Rider";
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.INK_SACK, 1, (byte) 10);
    }

    @Override
    public List<ItemStack> getItems() {
        ItemStack perk = new ItemStack(Material.MONSTER_EGG, 1, (short) 100);
        return Collections.singletonList(perk);
        //return new ItemStack[0];
    }

    @Override
    public String getPrefix() {
        return StringUtil.format("&aRider &7Class");
    }

    @Override
    public Integer getCooldown() {
        return 8600;
    }

    @Override
    public String getPermission() {
        return "fall.classes.rider";
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
        if (getItems().contains(itemStack)) {
            Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
            if (profile.getActiveQuest().equals("Rider")) {

            } else {
                player.getInventory().removeItem(itemStack);
            }
        }
    }
}
