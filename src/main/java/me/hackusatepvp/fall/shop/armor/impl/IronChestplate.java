package me.hackusatepvp.fall.shop.armor.impl;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.shop.armor.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class IronChestplate extends Armor {

    public IronChestplate() {
        super("IronChestplate");
    }

    @Override
    public String getName() {
        return "Diamond Chestplate";
    }

    @Override
    public String getCategory() {
        return "armor";
    }

    @Override
    public Double getCoast() {
        return 320D;
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Material.IRON_CHESTPLATE);
    }

    @Override
    public void onPurchase(Player player, ItemStack itemStack) {
        if (itemStack == getItem()) {
            Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
            if (profile.getBalance() >= getCoast()) {
                Fall.getInstance().getEconomyManager().subtractBalance(profile, getCoast());
                player.getInventory().addItem(itemStack);
                player.sendMessage(ChatColor.GREEN + "You have purchased the " + getName() + " for $" + getCoast());
            } else {
                player.sendMessage(ChatColor.RED + "You cannot afford this item.");
            }
        }
    }
}

