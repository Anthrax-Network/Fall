package me.hackusatepvp.fall.tags.rare;

import me.hackusatepvp.fall.tags.Category;
import me.hackusatepvp.fall.tags.Tags;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RareCategory extends Category {

    public RareCategory() {
        super("GOLD_INGOT");
    }

    @Override
    public String getName() {
        return "Rare";
    }

    @Override
    public ItemStack getIcon() {
        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&bRare &7Tags"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public ItemStack getBack() {
        ItemStack itemStack = new ItemStack(Material.ARROW);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&7* &cBack"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public ItemStack remove() {
        ItemStack itemStack = new ItemStack(Material.REDSTONE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&7* &cRemove"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }


    @Override
    public Inventory getInventory() {
        Inventory i = Bukkit.createInventory(null, 18, StringUtil.format("&7* &bRare Tags"));
        i.setItem(0, Tags.getByName(StringUtil.format("&bHero &7Tag")).getIcon());
        i.setItem(1, Tags.getByName(StringUtil.format("&cKiller &7Tag")).getIcon());
        i.setItem(2, Tags.getByName(StringUtil.format("Shrug")).getIcon());
        i.setItem(3, Tags.getByName(StringUtil.format("&5Villain &7Tag")).getIcon());
        i.setItem(9, remove());
        i.setItem(13, getBack());
        i.setItem(17, remove());
        return i;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
