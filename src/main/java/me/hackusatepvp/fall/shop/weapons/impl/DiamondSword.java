package me.hackusatepvp.fall.shop.weapons.impl;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.shop.weapons.Weapons;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DiamondSword extends Weapons {

    public DiamondSword() {
        super("DiamondSword");
    }

    @Override
    public String getName() {
        return "Diamond Sword";
    }

    @Override
    public String getCategory() {
        return "weapons";
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Material.DIAMOND_SWORD);
    }

    @Override
    public Double getCoast() {
        return 580D;
    }

    @Override
    public void onPurchase(Player player, String category, ItemStack itemStack, Double coast) {
        if (category.equals("weapons")) {
            if (itemStack == getItem()) {
                Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
                if (profile.getBalance() >= coast) {
                    Fall.getInstance().getEconomyManager().subtractBalance(profile, coast);
                    player.getInventory().addItem(itemStack);
                    player.sendMessage(ChatColor.GREEN + "You have purchased the " + getName() + " for $" + getCoast());
                }
            }
        }
    }
}
