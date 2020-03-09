package me.hackusatepvp.fall.shop.armor.impl;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.shop.armor.Armor;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class DiamondBoots extends Armor {

    public DiamondBoots() {
        super("DIAMOND_BOOTS");
    }

    @Override
    public String getName() {
        return "Diamond Boots";
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
        ItemStack itemStack = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&7* &9Diamond Boots"));
        itemMeta.setLore(StringUtil.format(Arrays.asList("", "&aClick to buy for &n" + getCoast() , "")));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
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
