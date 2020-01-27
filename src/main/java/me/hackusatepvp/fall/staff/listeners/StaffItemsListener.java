package me.hackusatepvp.fall.staff.listeners;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class StaffItemsListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (profile.isStaff()) {
            if (event.getItem() == Fall.getInstance().getStaffManager().getVanish(player)) {
                if (Fall.getInstance().getStaffManager().isVanish(player)) {
                    Fall.getInstance().getStaffManager().removeVanish(player);
                    Bukkit.getOnlinePlayers().forEach(instance -> instance.showPlayer(player));
                    player.getInventory().clear();
                    Fall.getInstance().getStaffManager().giveItems(player);
                }
            }
        }
    }


}
