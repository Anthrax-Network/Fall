package me.hackusatepvp.fall.info;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

@Getter
@Setter
public class Info {
    private final UUID uuid;
    private String name;

    public Info(Player player) {
        this.name = player.getName();
        this.uuid = player.getUniqueId();
    }
}
