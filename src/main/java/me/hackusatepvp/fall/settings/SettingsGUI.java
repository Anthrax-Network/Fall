package me.hackusatepvp.fall.settings;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.StringUtil;
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

import java.util.Arrays;

public class SettingsGUI implements Listener {

    public Inventory getGUI(Player player) {
        Inventory i = Bukkit.createInventory(null, 9, StringUtil.format("&c&lSettings GUI"));
        i.setItem(0, getChat(player));
        i.setItem(1, getChatType(player));
        i.setItem(2, getTablist(player));
        i.setItem(3, getTabStyle(player));
        i.setItem(4, getBoard(player));
        i.setItem(5, getScoreboard(player));
        i.setItem(6, getMessages(player));
        i.setItem(7, getMessageSounds(player));
        return i;
    }

    public ItemStack getChat(Player player) {
        ItemStack itemStack = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&7* &c&lChat"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getTablist(Player player) {
        ItemStack itemStack = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&7* &b&lTablist"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getBoard(Player player) {
        ItemStack itemStack = new ItemStack(Material.PAINTING);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&7* &c&lBoard"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getScoreboard(Player player) {
        ItemStack itemStack = new ItemStack(Material.WOOD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&7* &b&lBoard Style"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getChatType(Player player) {
        ItemStack itemStack = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&7* &cChat-Type"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getTabStyle(Player player) {
        ItemStack itemStack = new ItemStack(Material.ITEM_FRAME);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&7* &b&lTab Style"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getMessages(Player player) {
        ItemStack itemStack = new ItemStack(Material.BOOK_AND_QUILL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&7* &b&lMessages"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getMessageSounds(Player player) {
        ItemStack itemStack = new ItemStack(Material.NOTE_BLOCK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(StringUtil.format("&7* &b&lMessage-Sounds"));
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
                    if (event.getClickedInventory().getName().contains("Settings")) {
                        event.setCancelled(true);
                        if (event.getCurrentItem().hasItemMeta()) {
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getChat(player).getItemMeta().getDisplayName())) {
                                if (profile.isChat()) profile.setChat(true);
                                else profile.setChat(false);
                            }
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getTablist(player).getItemMeta().getDisplayName())) {
                                if (profile.isTablist()) profile.setTablist(true);
                                else profile.setTablist(false);
                                // that's a ternary operator, it's like a simplified if statement ---> thing ? when it's true : when it's false
                               // profile.setScoreboard(profile.isScoreboard() ? false : true);
                            }

                            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getScoreboard(player).getItemMeta().getDisplayName())) {
                                if (profile.getBoardstyle().equalsIgnoreCase("blue")) {
                                    profile.setBoardstyle("red");
                                } else if (profile.getBoardstyle().equalsIgnoreCase("red")) {
                                    profile.setBoardstyle("green");
                                } else if (profile.getBoardstyle().equalsIgnoreCase("green")) {
                                    profile.setBoardstyle("white");
                                } else if (profile.getBoardstyle().equalsIgnoreCase("white")) {
                                    profile.setBoardstyle("blue");
                                }
                            }
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getTabStyle(player).getItemMeta().getDisplayName())) {
                                if (profile.getTabtype().equalsIgnoreCase("blue")) {
                                    profile.setTabtype("red");
                                } else if (profile.getTabtype().equalsIgnoreCase("red")) {
                                    profile.setTabtype("green");
                                } else if (profile.getTabtype().equalsIgnoreCase("green")) {
                                    profile.setTabtype("white");
                                } else if (profile.getTabtype().equalsIgnoreCase("white")) {
                                    profile.setTabtype("blue");
                                }
                            }
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getBoard(player).getItemMeta().getDisplayName())) {
                                if (profile.isStaff()) profile.setScoreboard(true);
                                else profile.setScoreboard(false);
                            }
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getChatType(player).getItemMeta().getDisplayName())) {
                                if (Fall.getInstance().getClanManager().inClan(player.getUniqueId())) {
                                     if (profile.isStaff()) {
                                         if (profile.getChatype().equals("public")) {
                                             profile.setChatype("clan");
                                         } else if (profile.getChatype().equals("clan")) {
                                             profile.setChatype("staff");
                                         } else {
                                             profile.setChatype("public");
                                         }
                                         return;
                                     }
                                     if (profile.getChatype().equals("public")) {
                                         profile.setChatype("clan");
                                     } else {
                                         profile.setChatype("public");
                                     }
                                } else {
                                    player.sendMessage(ChatColor.RED + "You only have the \"public\" setting available.");
                                }
                            }
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getMessages(player).getItemMeta().getDisplayName())) {
                                if (profile.isMessages()) profile.setMessages(true);
                                else profile.setMessages(false);
                            }
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getMessageSounds(player).getItemMeta().getDisplayName())) {
                                if (profile.isMessagesound()) profile.setMessages(true);
                                else profile.setMessages(false);
                            }
                        }
                        //next
                    }
                }
            }
        }
    }
}
