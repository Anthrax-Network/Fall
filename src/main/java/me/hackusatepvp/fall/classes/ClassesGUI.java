package me.hackusatepvp.fall.classes;

import com.mysql.jdbc.TimeUtil;
import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.StringUtil;
import me.hackusatepvp.fall.util.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassesGUI implements Listener {

    public Inventory getGUI(Player player) {
        Inventory i = Bukkit.createInventory(null, 9, StringUtil.format("&cClasses GUI"));
        i.setItem(0, getMasterClass(player));
        i.setItem(1, getRiderClass(player));
        i.setItem(2, getCasterClass(player));
        i.setItem(3, getArcherClass(player));
        i.setItem(4, getAssassinClass(player));
        i.setItem(5, getLancerClass(player));
        i.setItem(6, getBerserkerClass(player));
        i.setItem(7, getSaberClass(player));
        i.setItem(8, getFateClass(player));

        return i;
    }

    public ItemStack getMasterClass(Player player) {
        Classes classes = Classes.getByName("Master");
        if (player.hasPermission(classes.getPermission())) {
            ItemStack itemStack = classes.getIcon();
            ItemMeta im = itemStack.getItemMeta();
            im.setDisplayName(classes.getPrefix());
            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add("&cCannot use this kit. " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                lore.add("");
                StringUtil.format(lore);
                im.setLore(lore);
                itemStack.setItemMeta(im);
                return itemStack;
            } else {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add("&aClick to use this kit.");
                lore.add("");
                StringUtil.format(lore);
                im.setLore(lore);
                itemStack.setItemMeta(im);
                return itemStack;
            }
        } else {
            ItemStack itemStack = new ItemStack(Material.REDSTONE);
            ItemMeta im = itemStack.getItemMeta();
            im.setDisplayName(classes.getPrefix());
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("&cYou do not have this kit unlocked.");
            lore.add("");
            lore.forEach(StringUtil::format);
            im.setLore(lore);
            itemStack.setItemMeta(im);
            return itemStack;
        }
    }

    public ItemStack getRiderClass(Player player) {
        Classes classes = Classes.getByName("Rider");
        if (player.hasPermission(classes.getPermission())) {
            ItemStack itemStack = classes.getIcon();
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(classes.getPrefix());
            itemStack.setItemMeta(itemMeta);
            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add("&cCannot use this kit. " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                lore.add("");
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);
                return itemStack;
            }
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("&aClick to use this kit.");
            lore.add("");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        ItemStack itemStack = new ItemStack(Material.REDSTONE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(classes.getPrefix());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getCasterClass(Player player) {
        Classes classes = Classes.getByName("Caster");
        if (player.hasPermission(classes.getPermission())) {
            ItemStack itemStack = classes.getIcon();
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(classes.getPrefix());
            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add("&cCannot use this kit. " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                lore.add("");
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);
                return itemStack;
            }
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("&aClick to use this kit.");
            lore.add("");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        ItemStack itemStack = new ItemStack(Material.REDSTONE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(classes.getPrefix());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getArcherClass(Player player) {
        Classes classes = Classes.getByName("Archer");
        if (player.hasPermission(classes.getPermission())) {
            ItemStack itemStack = classes.getIcon();
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(classes.getPrefix());
            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add("&cCannot use this kit. " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                lore.add("");
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);
                return itemStack;
            }
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("&aClick to use this kit.");
            lore.add("");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        ItemStack itemStack = new ItemStack(Material.REDSTONE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(classes.getPrefix());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getAssassinClass(Player player) {
        Classes classes = Classes.getByName("Rider");
        if (player.hasPermission(classes.getPermission())) {
            ItemStack itemStack = classes.getIcon();
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(classes.getPrefix());
            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add("&cCannot use this kit. " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                lore.add("");
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);
                return itemStack;
            }
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("&aClick to use this kit.");
            lore.add("");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        ItemStack itemStack = new ItemStack(Material.REDSTONE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(classes.getPrefix());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getLancerClass(Player player) {
        Classes classes = Classes.getByName("Lancer");
        if (player.hasPermission(classes.getPermission())) {
            ItemStack itemStack = classes.getIcon();
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(classes.getPrefix());
            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add("&cCannot use this kit. " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                lore.add("");
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);
                return itemStack;
            }
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("&aClick to use this kit.");
            lore.add("");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        ItemStack itemStack = new ItemStack(Material.REDSTONE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(classes.getPrefix());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getBerserkerClass(Player player) {
        Classes classes = Classes.getByName("Berserker");
        if (player.hasPermission(classes.getPermission())) {
            ItemStack itemStack = classes.getIcon();
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(classes.getPrefix());
            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add("&cCannot use this kit. " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                lore.add("");
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);
                return itemStack;
            }
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("&aClick to use this kit.");
            lore.add("");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        ItemStack itemStack = new ItemStack(Material.REDSTONE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(classes.getPrefix());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getSaberClass(Player player) {
        Classes classes = Classes.getByName("Saber");
        if (player.hasPermission(classes.getPermission())) {
            ItemStack itemStack = classes.getIcon();
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(classes.getPrefix());
            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add("&cCannot use this kit. " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                lore.add("");
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);
                return itemStack;
            }
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("&aClick to use this kit.");
            lore.add("");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        ItemStack itemStack = new ItemStack(Material.REDSTONE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(classes.getPrefix());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getFateClass(Player player) {
        Classes classes = Classes.getByName("Fate");
        if (player.hasPermission(classes.getPermission())) {
            ItemStack itemStack = classes.getIcon();
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(classes.getPrefix());
            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add("&cCannot use this kit. " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                lore.add("");
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);
                return itemStack;
            }
            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add("&aClick to use this kit.");
            lore.add("");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }
        ItemStack itemStack = new ItemStack(Material.REDSTONE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(classes.getPrefix());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() != null) {
            Player player = (Player) event.getWhoClicked();
            Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
            if (event.getInventory() != null) {
                if (event.getClickedInventory() != null) {
                    if (event.getClickedInventory().getTitle().equals(getGUI(player).getTitle())) {
                        event.setCancelled(true);
                        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getMasterClass(player).getItemMeta().getDisplayName())) {
                            Classes classes = Classes.getByName("Master");
                            if (!player.hasPermission(classes.getPermission())) {
                                player.sendMessage(ChatColor.RED + "You do not have permission to use this class kit.");
                                return;
                            }
                            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                                player.sendMessage(ChatColor.RED + "You cannot use another kit for " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                                return;
                            }
                            Fall.getInstance().getClassesManager().setCooldown(player, classes.getCooldown());
                            profile.setActiveClass(classes.getName());
                            classes.getItems().forEach(item -> player.getInventory().addItem(item));
                        }
                        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getRiderClass(player).getItemMeta().getDisplayName())) {
                            Classes classes = Classes.getByName("Rider");
                            if (!player.hasPermission(classes.getPermission())) {
                                player.sendMessage(ChatColor.RED + "You do not have permission to use this class kit.");
                                return;
                            }
                            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                                player.sendMessage(ChatColor.RED + "You cannot use another kit for " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                                return;
                            }
                            Fall.getInstance().getClassesManager().setCooldown(player, classes.getCooldown());
                            profile.setActiveClass(classes.getName());
                            classes.getItems().forEach(item -> player.getInventory().addItem(item));
                        }
                        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getCasterClass(player).getItemMeta().getDisplayName())) {
                            Classes classes = Classes.getByName("Caster");
                            if (!player.hasPermission(classes.getPermission())) {
                                player.sendMessage(ChatColor.RED + "You do not have permission to use this class kit.");
                                return;
                            }
                            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                                player.sendMessage(ChatColor.RED + "You cannot use another kit for " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                                return;
                            }
                            Fall.getInstance().getClassesManager().setCooldown(player, classes.getCooldown());
                            profile.setActiveClass(classes.getName());
                            classes.getItems().forEach(item -> player.getInventory().addItem(item));
                        }
                        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getArcherClass(player).getItemMeta().getDisplayName())) {
                            Classes classes = Classes.getByName("Archer");
                            if (!player.hasPermission(classes.getPermission())) {
                                player.sendMessage(ChatColor.RED + "You do not have permission to use this class kit.");
                                return;
                            }
                            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                                player.sendMessage(ChatColor.RED + "You cannot use another kit for " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                                return;
                            }
                            Fall.getInstance().getClassesManager().setCooldown(player, classes.getCooldown());
                            profile.setActiveClass(classes.getName());
                            classes.getItems().forEach(item -> player.getInventory().addItem(item));
                        }
                        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getAssassinClass(player).getItemMeta().getDisplayName())) {
                            Classes classes = Classes.getByName("Assassin");
                            if (!player.hasPermission(classes.getPermission())) {
                                player.sendMessage(ChatColor.RED + "You do not have permission to use this class kit.");
                                return;
                            }
                            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                                player.sendMessage(ChatColor.RED + "You cannot use another kit for " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                                return;
                            }
                            Fall.getInstance().getClassesManager().setCooldown(player, classes.getCooldown());
                            profile.setActiveClass(classes.getName());
                            classes.getItems().forEach(item -> player.getInventory().addItem(item));
                        }
                        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getLancerClass(player).getItemMeta().getDisplayName())) {
                            Classes classes = Classes.getByName("Lancer");
                            if (!player.hasPermission(classes.getPermission())) {
                                player.sendMessage(ChatColor.RED + "You do not have permission to use this class kit.");
                                return;
                            }
                            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                                player.sendMessage(ChatColor.RED + "You cannot use another kit for " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                                return;
                            }
                            Fall.getInstance().getClassesManager().setCooldown(player, classes.getCooldown());
                            profile.setActiveClass(classes.getName());
                            classes.getItems().forEach(item -> player.getInventory().addItem(item));
                        }
                        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getBerserkerClass(player).getItemMeta().getDisplayName())) {
                            Classes classes = Classes.getByName("Berserker");
                            if (!player.hasPermission(classes.getPermission())) {
                                player.sendMessage(ChatColor.RED + "You do not have permission to use this class kit.");
                                return;
                            }
                            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                                player.sendMessage(ChatColor.RED + "You cannot use another kit for " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                                return;
                            }
                            Fall.getInstance().getClassesManager().setCooldown(player, classes.getCooldown());
                            profile.setActiveClass(classes.getName());
                            classes.getItems().forEach(item -> player.getInventory().addItem(item));
                        }
                        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getSaberClass(player).getItemMeta().getDisplayName())) {
                            Classes classes = Classes.getByName("Saber");
                            if (!player.hasPermission(classes.getPermission())) {
                                player.sendMessage(ChatColor.RED + "You do not have permission to use this class kit.");
                                return;
                            }
                            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                                player.sendMessage(ChatColor.RED + "You cannot use another kit for " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                                return;
                            }
                            Fall.getInstance().getClassesManager().setCooldown(player, classes.getCooldown());
                            profile.setActiveClass(classes.getName());
                            classes.getItems().forEach(item -> player.getInventory().addItem(item));
                        }
                        if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getFateClass(player).getItemMeta().getDisplayName())) {
                            Classes classes = Classes.getByName("Fate");
                            if (!player.hasPermission(classes.getPermission())) {
                                player.sendMessage(ChatColor.RED + "You do not have permission to use this class kit.");
                                return;
                            }
                            if (Fall.getInstance().getClassesManager().isCooldown(player)) {
                                player.sendMessage(ChatColor.RED + "You cannot use another kit for " + TimeUtils.IntegerCountdown.setFormat(Fall.getInstance().getClassesManager().getCooldown(player)));
                                return;
                            }
                            Fall.getInstance().getClassesManager().setCooldown(player, classes.getCooldown());
                            profile.setActiveClass(classes.getName());
                            classes.getItems().forEach(item -> player.getInventory().addItem(item));
                        }
                    }
                }
            }
        }
    }
}
