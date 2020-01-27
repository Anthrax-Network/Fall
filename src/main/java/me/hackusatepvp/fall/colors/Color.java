package me.hackusatepvp.fall.colors;

import me.hackusatepvp.fall.colors.impl.*;
import me.hackusatepvp.fall.profile.Profile;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class Color {

    private static Map<String, Color> byName = new HashMap<>();

    private static final Color BLUE_COLOR = new BlueColor();
    private static final Color GREEN_COLOR = new GreenColor();
    private static final Color RED_COLOR = new RedColor();
    private static final Color YELLOW_COLOR = new YellowColor();
    private static final Color PURPLE_COLOR = new PurpleColor();

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

    public void onClick(Player player, Profile profile, ItemStack itemStack) {

    }
}
