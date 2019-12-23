package me.hackusatepvp.fall.info;
import me.hackusatepvp.fall.Fall;
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

import java.util.UUID;

public class InfoGUI implements Listener {

    public Inventory getInfoGUI(Player player) {
        Inventory i = Bukkit.createInventory(null, 9, "MESSAGE");
        Info profile = Fall.getInstance().getInfoManager().getInfo(player.getUniqueId());

        ItemStack name = new ItemStack(Material.PAPER);

        ItemMeta namem = name.getItemMeta();

        namem.setDisplayName(ChatColor.RED + profile.getName());

        name.setItemMeta(namem);

        i.setItem(0, name);
        return i;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() != null) {
            Player player = (Player) event.getWhoClicked();
            if (event.getInventory() != null) {
                if (event.getClickedInventory() != null) {
                    if (event.getClickedInventory().getName().equalsIgnoreCase("MESSAGE")) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
