package me.hackusatepvp.fall.tags.special;

import me.hackusatepvp.fall.tags.Category;
import me.hackusatepvp.fall.tags.Tags;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpecialCategory extends Category {

    public SpecialCategory() {
        super("BEACON");
    }

    @Override
    public String getName() {
        return "Special";
    }

    @Override
    public ItemStack getIcon() {
        ItemStack itemStack = new ItemStack(Material.BEACON);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&4Special &7Tags"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public Inventory getInventory() {
        Inventory i = Bukkit.createInventory(null, 36, StringUtil.format("&9Special &7Tags"));
        i.setItem(0, Tags.getByName("Christmas").getIcon());
        return i;
    }
}
