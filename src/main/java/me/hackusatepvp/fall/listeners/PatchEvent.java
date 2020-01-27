package me.hackusatepvp.fall.listeners;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.EnchantingInventory;

import java.util.ArrayList;
import java.util.List;

public class PatchEvent implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getDamager().getType() != EntityType.PLAYER) {
                event.getDamager().remove();
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory() instanceof AnvilInventory) {
            if (e.getPlayer() != null) {
                Player player = (Player) e.getPlayer();
                Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
                player.setExp(profile.getLevel());
                player.setLevel(profile.getLevel());
            }
        }
    }

    @EventHandler
    public void onInvenotryOpen(InventoryOpenEvent event) {
        if (event.getInventory() instanceof AnvilInventory) {
            if (event.getPlayer() != null) {
                Player player = (Player) event.getPlayer();
                player.setLevel(10000);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        Bukkit.getOnlinePlayers().forEach(instance -> instance.sendMessage(StringUtil.format("&a+ &7" + player.getName())));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        Bukkit.getOnlinePlayers().forEach(instance -> instance.sendMessage(StringUtil.format("&c- &7" + player.getName())));
        if (Fall.getInstance().getBountyManager().hasBounty(player.getUniqueId())) {
            Bukkit.getOnlinePlayers().forEach(instance -> instance.sendMessage(StringUtil.format("&c" + player.getName() + " &7has quit the game, a new bounty will be created.")));
            Fall.getInstance().getBountyTimer().setLeft(60);
            Fall.getInstance().getBountyTimer().setRunning(false);
        }
    }
}
