package me.hackusatepvp.fall.games;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PlayerManager {
    @Getter public HashSet<Player> spectator = new HashSet<>();

    public void addSpectator(Player player) {
        spectator.add(player);
    }

    public void removeSpectator(Player player) {
        spectator.remove(player);
    }

    public List<String> getSpectators() {
        List<String> players = new ArrayList<>();
        for (Player player : spectator) {
            players.add(player.getName());
        }
        return players;
    }
}
