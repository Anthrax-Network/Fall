package me.hackusatepvp.fall.tags;

import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TagsGUI {

    public Inventory getTagsGUI(Player player) {
        Inventory i = Bukkit.createInventory(null, 9, StringUtil.format("&4Tags &7GUI"));
        i.setItem(0, Category.getByName("STICK").getIcon());
        i.setItem(1, Category.getByName("GOLD_INGOT").getIcon());
        i.setItem(2, Category.getByName("DIAMOND").getIcon());
        i.setItem(3, Category.getByName("BEACON").getIcon());
        return i;
    }
}
