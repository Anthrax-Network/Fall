package me.hackusatepvp.fall.tags.common;

import me.hackusatepvp.fall.tags.Category;
import me.hackusatepvp.fall.tags.Tags;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommonCategory extends Category {

    public CommonCategory() {
        super("STICK");
    }

    @Override
    public String getName() {
        return "Common";
    }

    @Override
    public ItemStack getIcon() {
        ItemStack itemStack = new ItemStack(Material.STICK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&aCommon &7Tags"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public Inventory getInventory() {
        Inventory i = Bukkit.createInventory(null, 9, StringUtil.format("&7* &aCommon Tags"));
        i.setItem(0, Tags.getByName(StringUtil.format("&dCat &7Tag")).getIcon());
        i.setItem(1, Tags.getByName(StringUtil.format("&9Dawn &7Tag")).getIcon());
        i.setItem(2, Tags.getByName(StringUtil.format("&aDog &7Tag")).getIcon());
        i.setItem(3, Tags.getByName(StringUtil.format("&cHeart &7Tag")).getIcon());
        i.setItem(4, Tags.getByName(StringUtil.format("&eOof &7Tag")).getIcon());
        i.setItem(5, Tags.getByName(StringUtil.format("&aSoldier &7Tag")).getIcon());
        return i;
    }
}
