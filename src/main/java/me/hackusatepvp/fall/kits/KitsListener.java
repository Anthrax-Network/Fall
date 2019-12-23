package me.hackusatepvp.fall.kits;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class KitsListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() != null) {
            Player player = (Player) event.getWhoClicked();
            if (event.getInventory() != null) {
                if (event.getClickedInventory() != null) {
                    if (event.getCurrentItem() != null) {
                        if (event.getClickedInventory().getName().equals(Fall.getInstance().getKitsGUI().getKitsGUI().getName())) {
                            event.setCancelled(true);
                            if (Kits.getByName(event.getCurrentItem().getType().name()) != null) {
                                Kits kits = Kits.getByName(event.getCurrentItem().getType().name());
                                kits.onClick(player, kits.getIcon());
                            }
                        }
                    }
                }
            }
        }
    }
}
