package me.hackusatepvp.fall.tags;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.tags.common.CommonCategory;
import me.hackusatepvp.fall.tags.special.SpecialCategory;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TagsListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() != null) {
            Player player = (Player) event.getWhoClicked();
            Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
            if (event.getInventory() != null) {
                if (event.getClickedInventory() != null) {
                    if (event.getCurrentItem() != null) {
                        if (event.getClickedInventory().getName().equals(Fall.getInstance().getTagsGUI().getTagsGUI(player).getName())) {
                            event.setCancelled(true);
                            if (Category.getByName(event.getCurrentItem().getType().name()) != null) {
                                Category category = Category.getByName(event.getCurrentItem().getType().name());
                                if (category.isEnabled()) {
                                    player.openInventory(category.getInventory());
                                } else {
                                    player.sendMessage(StringUtil.format("&cThere are no special tags available at this moment."));
                                }
                                return;
                            }
                        }
                        if (event.getClickedInventory().getName().contains("Common")) {
                            event.setCancelled(true);
                            Tags tags = Tags.getByName(StringUtil.format(event.getCurrentItem().getItemMeta().getDisplayName()));
                            if (tags != null) {
                                tags.onClick(player, profile, event.getCurrentItem().getItemMeta().getDisplayName());
                            }
                        }
                        if (event.getClickedInventory().getName().contains("Rare")) {
                            event.setCancelled(true);
                            Tags tags = Tags.getByName(StringUtil.format(event.getCurrentItem().getItemMeta().getDisplayName()));
                            if (tags != null) {
                                tags.onClick(player, profile, event.getCurrentItem().getItemMeta().getDisplayName());
                            }
                        }
                        if (event.getClickedInventory().getName().contains("Legendary")) {
                            event.setCancelled(true);
                            Tags tags = Tags.getByName(StringUtil.format(event.getCurrentItem().getItemMeta().getDisplayName()));
                            if (tags != null) {
                                tags.onClick(player, profile, event.getCurrentItem().getItemMeta().getDisplayName());
                            }
                        }
                        if (event.getClickedInventory().getName().contains("Special")) {
                            event.setCancelled(true);
                            Tags tags = Tags.getByName(StringUtil.format(event.getCurrentItem().getItemMeta().getDisplayName()));
                            if (tags != null) {
                                tags.onClick(player, profile, event.getCurrentItem().getItemMeta().getDisplayName());
                            }
                        }
                    }
                }
            }
        }
    }
}
