package me.hackusatepvp.fall.shop.armor.impl;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.shop.armor.Armor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DiamondLegs extends Armor {

    public DiamondLegs() {
        super("DiamondLegs");
    }

    @Override
    public String getName() {
        return "Diamond Leggings";
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
        return new ItemStack(Material.DIAMOND_LEGGINGS);
    }

    @Override
    public void onPurchase(Player player, String category, ItemStack itemStack, Double coast) {
        if (category.equals("armor")) {
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
