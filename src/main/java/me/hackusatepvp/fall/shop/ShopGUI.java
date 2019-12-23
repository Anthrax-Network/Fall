package me.hackusatepvp.fall.shop;

import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ShopGUI {

    public Inventory getMainGUI(Player player) {
        Inventory i = Bukkit.createInventory(null, 9, StringUtil.format("&9Shop"));
        i.setItem(0, Shop.getByName("DIAMOND_CHESTPLATE").getIcon());
        i.setItem(1, Shop.getByName("DIAMOND_SWORD").getIcon());
        i.setItem(2, Shop.getByName("ENCHANTED_BOOK").getIcon());
        return i;
    }


}
