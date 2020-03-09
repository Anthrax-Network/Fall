package me.hackusatepvp.fall.shop.armor;

import me.hackusatepvp.fall.shop.armor.impl.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class Armor {

    private static Map<String, Armor> byName = new HashMap<>();

    public static final Armor DIAMOND_BOOTS = new DiamondBoots();
    public static final Armor DIAMOND_CHESTPLATE = new DiamondChestplate();
    public static final Armor DIAMOND_HELMET = new DiamondHelmet();
    public static final Armor DIAMOND_LEGS = new DiamondLegs();
    public static final Armor IRON_BOOTS = new IronBoots();
    public static final Armor IRON_CHESTPLATE = new IronChestplate();
    public static final Armor IRON_HELMET = new IronHelmet();
    public static final Armor IRON_LEGS = new IronLegs();

    private String name;



    public Armor(String name) {
        this.name = name;

        byName.put(name, this);
    }

    public static Armor getByName(String name) {
        return byName.get(name);
    }

    public abstract String getName();

    public abstract String getCategory();

    public abstract ItemStack getItem();

    public abstract Double getCoast();

    public void onPurchase(Player player, ItemStack itemStack) {

    }



}
