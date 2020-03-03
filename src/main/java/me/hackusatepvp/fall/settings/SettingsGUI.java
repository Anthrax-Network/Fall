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
        ItemStack itemStack = new ItemStack(Material.MAP);
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
                                profile.setChat(profile.isChat() ? false : true);
                                player.sendMessage(StringUtil.format("&7Your chat is now " + profile.isChat()));
                            }
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getTablist(player).getItemMeta().getDisplayName())) {
                                profile.setTablist(profile.isTablist() ? false : true);
                                player.sendMessage(StringUtil.format("&7Your tablist is now " + profile.isTablist()));
                                // that's a ternary operator, it's like a simplified if statement ---> thing ? when it's true : when it's false
                               // profile.setScoreboard(profile.isScoreboard() ? false : true);
                            }

                            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getScoreboard(player).getItemMeta().getDisplayName())) {
                                if (profile.getBoardstyle().equalsIgnoreCase("blue")) {
                                    profile.setBoardstyle("red");
                                    player.sendMessage(StringUtil.format("&7Your scoreboard is now &cred"));
                                } else if (profile.getBoardstyle().equalsIgnoreCase("red")) {
                                    profile.setBoardstyle("green");
                                    player.sendMessage(StringUtil.format("&7Your scoreboard is now &agreen"));
                                } else if (profile.getBoardstyle().equalsIgnoreCase("green")) {
                                    profile.setBoardstyle("white");
                                    player.sendMessage(StringUtil.format("&7Your scoreboard is now &fwhite"));
                                } else if (profile.getBoardstyle().equalsIgnoreCase("white")) {
                                    profile.setBoardstyle("blue");
                                    player.sendMessage(StringUtil.format("&7Your scoreboard is now &9blue"));
                                }
                            }
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getTabStyle(player).getItemMeta().getDisplayName())) {
                                if (profile.getTabtype().equalsIgnoreCase("blue")) {
                                    profile.setTabtype("red");
                                    player.sendMessage(StringUtil.format("&7Your tab is now &cred"));
                                } else if (profile.getTabtype().equalsIgnoreCase("red")) {
                                    profile.setTabtype("green");
                                    player.sendMessage(StringUtil.format("&7Your tab is now &agreen"));
                                } else if (profile.getTabtype().equalsIgnoreCase("green")) {
                                    profile.setTabtype("white");
                                    player.sendMessage(StringUtil.format("&7Your tab is now &fwhite"));
                                } else if (profile.getTabtype().equalsIgnoreCase("white")) {
                                    profile.setTabtype("blue");
                                    player.sendMessage(StringUtil.format("&7Your tab is now &9blue"));
                                }
                            }
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getBoard(player).getItemMeta().getDisplayName())) {
                                profile.setScoreboard(profile.isScoreboard() ? false : true);
                            }
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getChatType(player).getItemMeta().getDisplayName())) {
                                if (Fall.getInstance().getClanManager().inClan(player.getUniqueId())) {
                                     if (profile.isStaff()) {
                                         if (profile.getChatype().equals("public")) {
                                             player.sendMessage(StringUtil.format("&7You are now speaking in &9clan chat."));
                                             profile.setChatype("clan");
                                         } if (profile.getChatype().equals("clan")) {
                                             profile.setChatype("staff");
                                             player.sendMessage(StringUtil.format("&7You are now speaking in &4staff chat."));
                                         } if (profile.getChatype().equalsIgnoreCase("staff")) {
                                             player.sendMessage(StringUtil.format("&7You are now speaking in &apublic chat."));
                                         }
                                         return;
                                     }
                                     if (profile.getChatype().equals("public")) {
                                         profile.setChatype("clan");
                                         player.sendMessage(StringUtil.format("&7You are now speaking in &9clan chat."));
                                     } else {
                                         profile.setChatype("public");
                                         player.sendMessage(StringUtil.format("&7You are now speaking in &apublic chat."));
                                     }
                                } else {
                                    player.sendMessage(ChatColor.RED + "You only have the \"public\" setting available.");
                                }
                            }
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getMessages(player).getItemMeta().getDisplayName())) {
                                profile.setMessages(profile.isMessages() ? false : true);
                                player.sendMessage(StringUtil.format("&7Your messages is now " + profile.isMessages()));
                            }
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getMessageSounds(player).getItemMeta().getDisplayName())) {
                                profile.setMessagesound(profile.isMessagesound() ? false : true);
                                player.sendMessage(StringUtil.format("&7Your message-sounds is now " + profile.isMessages()));
                            }
                        }
                        //next
                    }
                }
            }
        }
    }
}
