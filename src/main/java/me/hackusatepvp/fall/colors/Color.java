package me.hackusatepvp.fall.colors;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class Color {

    private static Map<String, Color> byName = new HashMap<>();


    private String name;



    public Color(String name) {
        this.name = name;

        byName.put(name, this);
    }

    public static Color getByName(String name) {
        return byName.get(name);
    }

    public abstract String getPrefix();

    public abstract String getPermission();

    public abstract ItemStack getIcon();
}
