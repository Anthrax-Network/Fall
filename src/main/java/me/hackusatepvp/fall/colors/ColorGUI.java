package me.hackusatepvp.fall.colors;

import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class ColorGUI {

    public Inventory getColorGI() {
        Inventory i = Bukkit.createInventory(null, 9, StringUtil.format("&9&lColor &7GUI"));
        i.setItem(0, Color.getByName(StringUtil.format("&7* &9Blue")).getIcon());
        i.setItem(1, Color.getByName(StringUtil.format("&7* &cRed")).getIcon());
        i.setItem(2, Color.getByName(StringUtil.format("&7* &aGreen")).getIcon());
        i.setItem(3, Color.getByName(StringUtil.format("&7* &eYellow")).getIcon());
        i.setItem(4, Color.getByName(StringUtil.format("&7* &dPurple")).getIcon());
        return i;
    }
}
