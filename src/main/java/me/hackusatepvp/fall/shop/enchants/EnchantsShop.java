package me.hackusatepvp.fall.shop.enchants;

import me.hackusatepvp.fall.shop.Shop;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantsShop extends Shop {

    public EnchantsShop() {
        super("ENCHANTED_BOOK");
    }

    @Override
    public String getName() {
        return "enchants";
    }

    @Override
    public ItemStack getIcon() {
        ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&9Enchant &7Shop"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public Inventory getInventory() {
        Inventory i = Bukkit.createInventory(null, 18, StringUtil.format("&9Enchant Shop"));

        return i;
    }
}
