package me.hackusatepvp.fall.colors;

import me.hackusatepvp.fall.Fall;

import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ColorListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() != null) {
            Player player = (Player) event.getWhoClicked();
            Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
            if (event.getInventory() != null) {
                if (event.getClickedInventory() != null) {
                    if (event.getClickedInventory().getName().equalsIgnoreCase(Fall.getInstance().getColorGUI().getColorGI().getName())) {
                        if (event.getCurrentItem() != null) {
                            if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Remove")) {
                                profile.setColor("&7");
                                player.sendMessage(StringUtil.format("&cYou have reset your color"));
                            }
                            if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Staff")) {
                                if (profile.isStaff()) {
                                    player.sendMessage(StringUtil.format("&cThis feature is in development."));
                                } else {
                                    player.sendMessage(StringUtil.format("&cYou do not have permission to use these colors."));
                                }
                            }
                            if (event.getClickedInventory().getName().equals(Fall.getInstance().getColorGUI().getColorGI().getName())) {
                                event.setCancelled(true);
                                Color color = Color.getByName(event.getCurrentItem().getItemMeta().getDisplayName());
                                color.onClick(player, profile, event.getCurrentItem());
                            }
                        }
                    }
                }
            }
        }
    }
}
