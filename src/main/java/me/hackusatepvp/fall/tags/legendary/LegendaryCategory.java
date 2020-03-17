package me.hackusatepvp.fall.tags.legendary;

import me.hackusatepvp.fall.tags.Category;
import me.hackusatepvp.fall.tags.Tags;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LegendaryCategory extends Category {

    public LegendaryCategory() {
        super("DIAMOND");
    }

    @Override
    public String getName() {
        return "Legendary";
    }

    @Override
    public ItemStack getIcon() {
        ItemStack itemStack = new ItemStack(Material.DIAMOND);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&5Legendary &7Tags"));
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
        Inventory i = Bukkit.createInventory(null, 18, StringUtil.format("&6&lLegendary &7Tags"));
        i.setItem(0, Tags.getByName(StringUtil.format("&5Legend &7Tag")).getIcon());
        i.setItem(1, Tags.getByName(StringUtil.format("&6Howl &cTag")).getIcon());
        i.setItem(2, Tags.getByName(StringUtil.format("&4Reaper &7Tag")).getIcon());
        i.setItem(3, Tags.getByName(StringUtil.format("&e&lExcalibur &cTag")).getIcon());
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
