package me.hackusatepvp.fall.classes.impl;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.classes.Classes;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class LancerClass extends Classes {

    public LancerClass() {
        super("Lancer");
    }

    @Override
    public String getName() {
        return "Lancer";
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.INK_SACK, 1, (byte) 5);
    }

    @Override
    public List<ItemStack> getItems() {
        ItemStack sword = new ItemStack(Material.GOLD_SWORD);
        ItemMeta swordm = sword.getItemMeta();
        swordm.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
        sword.setItemMeta(swordm);
        ItemStack perk = new ItemStack(Material.MONSTER_EGG, 1, (short) 100);
        return Arrays.asList(sword, perk);
        //return new ItemStack[0];
    }

    @Override
    public String getPrefix() {
        return StringUtil.format("&5Lancer &7Class");
    }

    @Override
    public Integer getCooldown() {
        return 8600;
    }

    @Override
    public String getPermission() {
        return "fall.classes.lancer";
    }

    @Override
    public void onTick(Player player, Profile profile) {
        if (Fall.getInstance().getClassesManager().isCooldown(player)) {
            int left = Fall.getInstance().getClassesManager().getCooldown(player);
            --left;
            Fall.getInstance().getClassesManager().setCooldown(player, left);
            if (left == 0) {
                Fall.getInstance().getClassesManager().removeCooldown(player);
                player.sendMessage(ChatColor.GREEN + "Your class cooldown has expired.");
            }
        }
    }
}
