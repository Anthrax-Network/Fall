package me.hackusatepvp.fall.shop.weapons;

import me.hackusatepvp.fall.shop.Shop;
import me.hackusatepvp.fall.shop.armor.Armor;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WeaponsShop extends Shop {

    public WeaponsShop() {
        super("DIAMOND_SWORD");
    }

    @Override
    public String getName() {
        return "weapons";
    }

    @Override
    public ItemStack getIcon() {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&9Weapon &7Shop"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public Inventory getInventory() {
        Inventory i = Bukkit.createInventory(null, 18, StringUtil.format("&9Weapon Shop"));
        i.setItem(0, Weapons.getByName("DiamondSword").getItem());
        return i;
    }
}
