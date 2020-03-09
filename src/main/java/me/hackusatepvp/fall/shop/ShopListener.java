package me.hackusatepvp.fall.shop;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.shop.armor.Armor;
import me.hackusatepvp.fall.shop.armor.ArmorShop;
import me.hackusatepvp.fall.shop.enchants.Enchants;
import me.hackusatepvp.fall.shop.weapons.Weapons;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ShopListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() != null) {
            Player player = (Player) event.getWhoClicked();
            if (event.getInventory() != null) {
                if (event.getClickedInventory() != null) {
                    if (event.getCurrentItem() != null) {
                        if (event.getClickedInventory().getName().equals(Fall.getInstance().getShopGUI().getMainGUI(player).getName())) {
                            event.setCancelled(true);
                            if (Shop.getByName(event.getCurrentItem().getType().name()) != null) {
                                Shop shop = Shop.getByName(event.getCurrentItem().getType().name());
                                player.openInventory(shop.getInventory());
                            }
                        }
                        if (event.getClickedInventory().getName().contains("Armor")) {
                            event.setCancelled(true);
                            if (ArmorShop.getByName(event.getCurrentItem().getType().name()) != null) {
                                Armor armor = Armor.getByName(event.getCurrentItem().getType().name());
                                armor.onPurchase(player, armor.getItem());
                                player.sendMessage("Item found.");
                            }
                        }
                        if (event.getClickedInventory().getName().contains("Weapon")) {
                            event.setCancelled(true);
                            if (Weapons.getByName(event.getCurrentItem().getItemMeta().getDisplayName()) != null) {
                                Weapons weapons = Weapons.getByName(event.getCurrentItem().getItemMeta().getDisplayName());
                                weapons.onPurchase(player, weapons.getCategory(), weapons.getItem(), weapons.getCoast());
                            }
                        }
                        if (event.getClickedInventory().getName().contains("Enchants")) {
                            event.setCancelled(true);
                            if (Weapons.getByName(event.getCurrentItem().getType().name()) != null) {
                                Enchants enchants = Enchants.getByName(event.getCurrentItem().getItemMeta().getDisplayName());
                                enchants.onPurchase(player, enchants.getItem());
                            }
                        }
                    }
                }
            }
        }
    }
}
