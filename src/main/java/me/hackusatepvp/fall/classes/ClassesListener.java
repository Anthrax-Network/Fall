package me.hackusatepvp.fall.classes;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

public class ClassesListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        for (ItemStack item : Classes.MASTER_CLASS.getItems()) {
            if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
                Classes classes = Classes.MASTER_CLASS;
                classes.onItemUse(event.getPlayer(), classes, event.getPlayer().getItemInHand());
            }
        }
        for (ItemStack item : Classes.RIDER_CLASS.getItems()) {
            if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
                Classes classes = Classes.RIDER_CLASS;
                classes.onItemUse(event.getPlayer(), classes, event.getPlayer().getItemInHand());
            }
        }
        for (ItemStack item : Classes.CASTER_CLASS.getItems()) {
            if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
                Classes classes = Classes.CASTER_CLASS;
                classes.onItemUse(event.getPlayer(), classes, event.getPlayer().getItemInHand());
            }
        }
        for (ItemStack item : Classes.ARCHER_CLASS.getItems()) {
            if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
                Classes classes = Classes.ARCHER_CLASS;
                classes.onItemUse(event.getPlayer(), classes, event.getPlayer().getItemInHand());
            }
        }
        for (ItemStack item : Classes.ASSASSIN_CLASS.getItems()) {
            if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
                Classes classes = Classes.ASSASSIN_CLASS;
                classes.onItemUse(event.getPlayer(), classes, event.getPlayer().getItemInHand());
            }
        }
        for (ItemStack item : Classes.LANCER_CLASS.getItems()) {
            if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
                Classes classes = Classes.LANCER_CLASS;
                classes.onItemUse(event.getPlayer(), classes, event.getPlayer().getItemInHand());
            }
        }
        for (ItemStack item : Classes.BERSERKER_CLASS.getItems()) {
            if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
                Classes classes = Classes.BERSERKER_CLASS;
                classes.onItemUse(event.getPlayer(), classes, event.getPlayer().getItemInHand());
            }
        }
        for (ItemStack item : Classes.SABER_CLASS.getItems()) {
            if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
                Classes classes = Classes.SABER_CLASS;
                classes.onItemUse(event.getPlayer(), classes, event.getPlayer().getItemInHand());
            }
        }
        for (ItemStack item : Classes.FATE_CLASS.getItems()) {
            if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())) {
                Classes classes = Classes.FATE_CLASS;
                classes.onItemUse(event.getPlayer(), classes, event.getPlayer().getItemInHand());
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();
        if (projectile instanceof Arrow) {
            Arrow arrow = (Arrow) projectile;
            ProjectileSource source = arrow.getShooter();
            if (source instanceof Player) {
                Player shooter = (Player) source;
                if (Fall.getInstance().getCombatManager().isPearlCooldown(shooter)) {
                    ItemStack item = shooter.getItemInHand();
                    if (item.getItemMeta().getDisplayName().equalsIgnoreCase(Classes.ARCHER_CLASS.getItems().get(0).getItemMeta().getDisplayName())) {
                        shooter.getInventory().remove(item);
                    }
                }
            }
        }
    }
}
