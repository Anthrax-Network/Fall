package me.hackusatepvp.fall.info;
import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.quests.Quest;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class InfoGUI implements Listener {

    public Inventory getInfoGUI() {
        Inventory i = Bukkit.createInventory(null, 36, StringUtil.format("&4Info"));
        int count = 0;
        for (Player online : Bukkit.getOnlinePlayers()) {
            count++;
            ItemStack itemStack = new ItemStack(Material.SKULL_ITEM);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(StringUtil.format(online.getDisplayName()));
            itemStack.setItemMeta(itemMeta);
            i.setItem(count - 1, itemStack);
        }
        return i;
    }

    public Inventory getTargetInfo(Player player) {
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        Inventory i = Bukkit.createInventory(null, 54, StringUtil.format("&9" + player.getName() + "'s &7Info"));
        ItemStack name = new ItemStack(Material.SKULL_ITEM, 1, (short) 3, (byte) SkullType.PLAYER.ordinal());
        ItemMeta namem = name.getItemMeta();
        namem.setDisplayName(StringUtil.format("&9" + player.getName()));
        name.setItemMeta(namem);

        ItemStack rank = new ItemStack(Material.INK_SACK, 1, (byte) 1);
        ItemMeta rankm = rank.getItemMeta();
        rankm.setDisplayName(StringUtil.format("&7Rank &9" + profile.getDonor()));
        rank.setItemMeta(rankm);

        ItemStack quest = new ItemStack(Material.INK_SACK, 1, (byte) 6);
        ItemMeta questm = quest.getItemMeta();
        questm.setDisplayName(StringUtil.format("&7Quest: &9" + profile.getQuest()));
        quest.setItemMeta(questm);

        ItemStack kills = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta killsm = kills.getItemMeta();
        killsm.setDisplayName(StringUtil.format("&7Kills: &9" + profile.getKills()));
        kills.setItemMeta(killsm);

        ItemStack deaths = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
        ItemMeta deathsm = deaths.getItemMeta();
        deathsm.setDisplayName(StringUtil.format("&7Deaths: &9" + profile.getDeaths()));
        deaths.setItemMeta(deathsm);

        ItemStack ip = new ItemStack(Material.BOOK_AND_QUILL);
        ItemMeta ipm = ip.getItemMeta();
        ipm.setDisplayName(StringUtil.format("&7IP: &9" + player.getAddress().getAddress().getHostAddress()));
        ip.setItemMeta(ipm);

        ItemStack level = new ItemStack(Material.NETHER_STAR);
        ItemMeta levelm = level.getItemMeta();
        levelm.setDisplayName(StringUtil.format("&7Level: &9" + profile.getLevel()));
        level.setItemMeta(levelm);

        ItemStack xp = new ItemStack(Material.EXP_BOTTLE);
        ItemMeta xpm = xp.getItemMeta();
        xpm.setDisplayName(StringUtil.format("&7XP: &9" + profile.getXp()));
        xp.setItemMeta(xpm);

        ItemStack next = new ItemStack(Material.INK_SACK, (byte) 14);
        ItemMeta nextm = next.getItemMeta();
        nextm.setDisplayName(StringUtil.format("&7Next Quest: &9" + Quest.getActiveQuest(profile).getNext(profile)));
        next.setItemMeta(nextm);

        i.setItem(1, rank);
        i.setItem(4, name);
        i.setItem(7, quest);
        i.setItem(10, kills);
        i.setItem(11, deaths);
        i.setItem(13, ip);
        i.setItem(19, level);
        i.setItem(20, xp);
        return i;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() != null) {
            Player player = (Player) event.getWhoClicked();
            if (event.getInventory() != null) {
                if (event.getClickedInventory() != null) {
                    if (event.getClickedInventory().getName().equalsIgnoreCase(getInfoGUI().getName())) {
                        event.setCancelled(true);
                        Player target = Bukkit.getPlayerExact(event.getCurrentItem().getItemMeta().getDisplayName());
                        if (target != null) {
                            player.openInventory(getTargetInfo(target));
                        }
                    }
                    if (event.getClickedInventory().getName().contains("Info")) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
