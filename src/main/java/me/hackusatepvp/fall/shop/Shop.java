package me.hackusatepvp.fall.shop;

import me.hackusatepvp.fall.quests.impl.StarterQuest;
import me.hackusatepvp.fall.shop.armor.ArmorShop;
import me.hackusatepvp.fall.shop.enchants.EnchantsShop;
import me.hackusatepvp.fall.shop.weapons.WeaponsShop;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public abstract class Shop {

    private static Map<String, Shop> byName = new HashMap<>();

    public static final Shop ARMOR_SHOP = new ArmorShop();
    public static final Shop WEAPON_SHOP = new WeaponsShop();
    public static final Shop ENCHANTS_SHOP = new EnchantsShop();

    private String name;



    public Shop(String name) {
        this.name = name;

        byName.put(name, this);
    }

    public static Shop getByName(String name) {
        return byName.get(name);
    }

    public abstract String getName();

    public abstract ItemStack getIcon();

    public abstract Inventory getInventory();



}
