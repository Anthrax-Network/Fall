package me.hackusatepvp.fall.classes;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

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
}
