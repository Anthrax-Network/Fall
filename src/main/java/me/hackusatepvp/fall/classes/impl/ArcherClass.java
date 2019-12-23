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

public class ArcherClass extends Classes {

    public ArcherClass() {
        super("Archer");
    }

    @Override
    public String getName() {
        return "Archer";
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.INK_SACK, 1, (byte) 11);
    }

    @Override
    public List<ItemStack> getItems() {
        ItemStack bow = new ItemStack(Material.BOW);
        ItemStack arrow = new ItemStack(Material.ARROW, 1);
        ItemMeta bowm = bow.getItemMeta();
        bowm.addEnchant(Enchantment.ARROW_DAMAGE, 4, true);
        bow.setDurability((short) 383);
        bowm.setDisplayName(StringUtil.format("&9Fail-Not"));
        bow.setItemMeta(bowm);
        return Arrays.asList(bow, arrow);
    }

    @Override
    public String getPrefix() {
        return StringUtil.format("&eArcher &7Class");
    }

    @Override
    public Integer getCooldown() {
        return 8600;
    }

    @Override
    public String getPermission() {
        return "fall.classes.archer";
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

    @Override
    public void onItemUse(Player player, Classes classes, ItemStack itemStack) {
        if (itemStack.getType() == Material.BOW) {
            Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
            if (profile.getActiveQuest().equals("Archer")) {
                player.getInventory().removeItem(itemStack);
            } else {
                player.getInventory().removeItem(itemStack);
            }
        }
    }
}
