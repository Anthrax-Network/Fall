package me.hackusatepvp.fall.shop.weapons;

import me.hackusatepvp.fall.shop.Shop;
import me.hackusatepvp.fall.shop.armor.Armor;
import me.hackusatepvp.fall.shop.weapons.impl.DiamondSword;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class Weapons {

    private static Map<String, Weapons> byName = new HashMap<>();

    public static Weapons DIAMOND_SWORD = new DiamondSword();

    private String name;



    public Weapons(String name) {
        this.name = name;
        byName.put(name, this);
    }


    public static Weapons getByName(String name) {
        return byName.get(name);
    }

    public abstract String getName();

    public abstract String getCategory();

    public abstract ItemStack getItem();

    public abstract Double getCoast();

    public void onPurchase(Player player, String category, ItemStack itemStack, Double coast) {

    }
}
