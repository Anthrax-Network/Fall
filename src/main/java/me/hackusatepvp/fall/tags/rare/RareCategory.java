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
    public Inventory getInventory() {
        Inventory i = Bukkit.createInventory(null, 9, StringUtil.format("&7* &bRare Tags"));
        i.setItem(0, Tags.getByName(StringUtil.format("&bHero &7Tag")).getIcon());
        return i;
    }
}
