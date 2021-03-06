package me.hackusatepvp.fall.staff.listeners;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class StaffItemsListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (profile.isStaff()) {
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Fall.getInstance().getStaffManager().getVanish(player).getItemMeta().getDisplayName())) {
                    if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        if (Fall.getInstance().getStaffManager().isStaffMode(player)) {
                            if (Fall.getInstance().getStaffManager().isVanish(player)) {
                                Fall.getInstance().getStaffManager().removeVanish(player);
                                Bukkit.getOnlinePlayers().forEach(instance -> instance.showPlayer(player));
                                player.getInventory().clear();
                                player.getInventory().setContents(Fall.getInstance().getStaffManager().giveItems(player).getContents());
                                player.sendMessage(StringUtil.format("&cYou have removed your vanish."));
                            } else {
                                Fall.getInstance().getStaffManager().setVanish(player);
                                Bukkit.getOnlinePlayers().forEach(instance -> instance.hidePlayer(player));
                                player.getInventory().clear();
                                player.getInventory().setContents(Fall.getInstance().getStaffManager().giveItems(player).getContents());
                                player.sendMessage(StringUtil.format("&7Poof! You have disappeared."));
                            }
                        } else {
                            player.getInventory().remove(event.getItem());
                        }
                    }
                }
                if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Fall.getInstance().getStaffManager().getInfo().getItemMeta().getDisplayName())) {
                    if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        if (Fall.getInstance().getStaffManager().isStaffMode(player)) {
                            player.openInventory(Fall.getInstance().getInfoGUI().getInfoGUI());
                        } else {
                            player.getInventory().remove(event.getItem());
                        }
                    }
                }
            }
        }
    }
}
