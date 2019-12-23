package me.hackusatepvp.fall.tags;

import me.hackusatepvp.fall.tags.common.CommonCategory;
import me.hackusatepvp.fall.tags.legendary.LegendaryCategory;
import me.hackusatepvp.fall.tags.rare.RareCategory;
import me.hackusatepvp.fall.tags.special.SpecialCategory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.util.HashMap;
import java.util.Map;

public abstract class Category {

    private static Map<String, Category> byName = new HashMap<>();

    private static final Category COMMON_CATEGORY = new CommonCategory();
    private static final Category RARE_CATEGORY = new RareCategory();
    private static final Category LEGEND_CATEGORY = new LegendaryCategory();
    private static final Category SPECIAL_CATEGORY = new SpecialCategory();

    private String name;

    public Category(String name) {
        this.name = name;

        byName.put(name, this);
    }

    public static Category getByName(String name) {
        return byName.get(name);
    }

    public abstract String getName();

    public abstract ItemStack getIcon();

    public abstract Inventory getInventory();

}
