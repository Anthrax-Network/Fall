package me.hackusatepvp.fall.shop.enchants;

import me.hackusatepvp.fall.shop.armor.Armor;
import me.hackusatepvp.fall.shop.armor.impl.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class Enchants {
    private static Map<String, Enchants> byName = new HashMap<>();


    private String name;



    public Enchants(String name) {
        this.name = name;

        byName.put(name, this);
    }

    public static Enchants getByName(String name) {
        return byName.get(name);
    }

    public abstract String getName();

    public abstract String getCategory();

    public abstract ItemStack getItem();

    public abstract Double getCoast();

    public void onPurchase(Player player, String category, ItemStack itemStack, Double coast) {

    }
}
