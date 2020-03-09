package me.hackusatepvp.fall.shop.armor;

import me.hackusatepvp.fall.shop.Shop;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ArmorShop extends Shop {

    public ArmorShop() {
        super("DIAMOND_CHESTPLATE");
    }

    @Override
    public String getName() {
        return "armor";
    }

    @Override
    public ItemStack getIcon() {
        ItemStack itemStack = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&9Armor &7Shop"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getDiamondHelmet() {
        ItemStack itemStack = Armor.DIAMOND_HELMET.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&9Diamond Helmet"));
        String[] lore = new String[] {
                "&aBuy for " + Armor.DIAMOND_HELMET.getCoast() + ".",
                "&cSell for N/A"
        };
        List<String> lines = Arrays.asList(lore);
        itemMeta.setLore(lines);
        return itemStack;
    }

    public ItemStack getDiamondChestPlate() {
        ItemStack itemStack = Armor.DIAMOND_CHESTPLATE.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&9Diamond Helmet"));
        String[] lore = new String[] {
                "&aBuy for " + Armor.DIAMOND_CHESTPLATE.getCoast() + ".",
                "&cSell for N/A"
        };
        List<String> lines = Arrays.asList(lore);
        itemMeta.setLore(lines);
        return itemStack;
    }

    public ItemStack getDiamondLegs() {
        ItemStack itemStack = Armor.DIAMOND_LEGS.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&9Diamond Helmet"));
        String[] lore = new String[] {
                "&aBuy for " + Armor.DIAMOND_LEGS.getCoast() + ".",
                "&cSell for N/A"
        };
        List<String> lines = Arrays.asList(lore);
        itemMeta.setLore(lines);
        return itemStack;
    }

    public ItemStack getDiamondBoots() {
        ItemStack itemStack = Armor.DIAMOND_BOOTS.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&9Diamond Helmet"));
        String[] lore = new String[] {
                "&aBuy for " + Armor.DIAMOND_BOOTS.getCoast() + ".",
                "&cSell for N/A"
        };
        List<String> lines = Arrays.asList(lore);
        itemMeta.setLore(lines);
        return itemStack;
    }

    public ItemStack getIronHelmet() {
        ItemStack itemStack = Armor.IRON_HELMET.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&9Diamond Helmet"));
        String[] lore = new String[] {
                "&aBuy for " + Armor.IRON_HELMET.getCoast() + ".",
                "&cSell for N/A"
        };
        List<String> lines = Arrays.asList(lore);
        itemMeta.setLore(lines);
        return itemStack;
    }

    public ItemStack getIronChestPlate() {
        ItemStack itemStack = Armor.IRON_CHESTPLATE.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&9Diamond Helmet"));
        String[] lore = new String[] {
                "&aBuy for " + Armor.IRON_CHESTPLATE.getCoast() + ".",
                "&cSell for N/A"
        };
        List<String> lines = Arrays.asList(lore);
        itemMeta.setLore(lines);
        return itemStack;
    }

    public ItemStack getIronLegs() {
        ItemStack itemStack = Armor.IRON_LEGS.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&9Diamond Helmet"));
        String[] lore = new String[] {
                "&aBuy for " + Armor.IRON_LEGS.getCoast() + ".",
                "&cSell for N/A"
        };
        List<String> lines = Arrays.asList(lore);
        itemMeta.setLore(lines);
        return itemStack;
    }

    public ItemStack getIronBoots() {
        ItemStack itemStack = Armor.IRON_BOOTS.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&9Diamond Helmet"));
        String[] lore = new String[] {
                "&aBuy for " + Armor.IRON_BOOTS.getCoast() + ".",
                "&cSell for N/A"
        };
        List<String> lines = Arrays.asList(lore);
        itemMeta.setLore(lines);
        return itemStack;
    }


    @Override
    public Inventory getInventory() {
        Inventory i = Bukkit.createInventory(null, 18, StringUtil.format("&9Armor Shop"));
        i.setItem(0, getIronHelmet());
        i.setItem(1, getIronChestPlate());
        i.setItem(2, getIronLegs());
        i.setItem(3, getIronBoots());
        i.setItem(4, getDiamondHelmet());
        i.setItem(5, getDiamondChestPlate());
        i.setItem(6, getDiamondLegs());
        i.setItem(7, getDiamondBoots());
        return i;
    }
}
