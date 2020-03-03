package me.hackusatepvp.fall.staff.listeners;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.staff.Staff;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.hackusatepvp.fall.util.StringUtil.format;

public class StaffPatch implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (Fall.getInstance().getStaffManager().isStaffMode(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (Fall.getInstance().getStaffManager().isStaffMode(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("fall.staff")) {
            Fall.getInstance().getStaffManager().addStaff(player.getUniqueId(), new Staff(player.getUniqueId()));
        } else {
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (Fall.getInstance().getStaffManager().isVanish(online)) {
                    Fall.getInstance().getStaffManager().setHiden(player, true);
                    if (!Fall.getInstance().getStaffManager().isStaff(online.getUniqueId())) {
                        player.hidePlayer(online);
                    }
                }
            }
        }
        if (Fall.getInstance().getStaffManager().isStaff(player.getUniqueId())) {
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (Fall.getInstance().getStaffManager().isStaff(online.getUniqueId())) {
                    // online.sendMessage(format("&"));
                }
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (Fall.getInstance().getStaffManager().isStaffMode(player)) {
            event.setCancelled(true);
        }
        if (Fall.getInstance().getStaffManager().isVanish(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getEntity();
        if (Fall.getInstance().getStaffManager().isStaffMode(player)) {
            event.setCancelled(true);
        }
        if (Fall.getInstance().getStaffManager().isVanish(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Player player = (Player) event.getEntity();
        if (Fall.getInstance().getStaffManager().isStaffMode(player)) {
            event.setCancelled(true);
        }
        if (Fall.getInstance().getStaffManager().isVanish(player)) {
            event.setCancelled(true);
        }
    }
}
