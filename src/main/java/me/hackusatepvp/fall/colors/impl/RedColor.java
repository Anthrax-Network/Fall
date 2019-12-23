package me.hackusatepvp.fall.colors.impl;

import me.hackusatepvp.fall.colors.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RedColor extends Color {


    public RedColor() {
        super("red");
    }

    @Override
    public String getPrefix() {
        return "&c";
    }

    @Override
    public String getPermission() {
        return "fall.colors.red";
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.INK_SACK, 1, (byte) 1);
    }
}
