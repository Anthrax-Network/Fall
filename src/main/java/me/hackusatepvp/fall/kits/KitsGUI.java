package me.hackusatepvp.fall.kits;

import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class KitsGUI {

    public Inventory getKitsGUI() {
        Inventory i = Bukkit.createInventory(null, 18, StringUtil.format("&4Kits &7GUI"));
        i.setItem(0, Kits.getByName("WOOD_SWORD").getIcon());
        i.setItem(1, Kits.getByName("STONE_SWORD").getIcon());
        i.setItem(2, Kits.getByName("SADDLE").getIcon());
        i.setItem(3, Kits.getByName("POTION").getIcon());
        i.setItem(4, Kits.getByName("BOW").getIcon());
        i.setItem(5, Kits.getByName("SKULL_ITEM").getIcon());
        i.setItem(6, Kits.getByName("GOLD_SWORD").getIcon());
        i.setItem(7, Kits.getByName("DRAGON_EGG").getIcon());
        i.setItem(8, Kits.getByName("DIAMOND_SWORD").getIcon());
        i.setItem(13, Kits.getByName("NETHER_STAR").getIcon());
        return i;
    }
}
