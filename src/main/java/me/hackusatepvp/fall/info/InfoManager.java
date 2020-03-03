package me.hackusatepvp.fall.info;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class InfoManager {
    HashMap<UUID, String> players = new HashMap<>();

    public String getPlayer(UUID uuid) {
       return players.get(uuid);
    }

    public void addPlayer(Player player) {
        players.put(player.getUniqueId(), player.getName());
    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
    }
}
