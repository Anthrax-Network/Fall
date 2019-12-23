package me.hackusatepvp.fall.classes;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ClassesManager {

   @Getter private HashMap<Player, Integer> classcooldown = new HashMap<>();

    public boolean isCooldown(Player player) {
        return classcooldown.containsKey(player);
    }

    public void setCooldown(Player player, Integer integer) {
        classcooldown.put(player, integer);
    }

    public Integer getCooldown(Player player) {
        return classcooldown.get(player);
    }

    public void removeCooldown(Player player) {
        classcooldown.remove(player);
    }
}
