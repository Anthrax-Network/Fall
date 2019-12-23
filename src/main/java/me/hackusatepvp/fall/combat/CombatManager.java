package me.hackusatepvp.fall.combat;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CombatManager {

    @Getter private HashMap<Player, Integer> combat = new HashMap<>();

    @Getter private HashMap<Player, Integer> pearl = new HashMap<>();

    public void addToCombat(Player player) {
        combat.put(player, 30);
    }

    public void addToCombat(Player player, Integer integer) {
        combat.put(player, integer);
    }

    public boolean inCombat(Player player) {
        return combat.containsKey(player);
    }

    public Integer getCombatTime(Player player) {
        return combat.get(player);
    }

    public void setCombatTime(Player player, Integer integer) {
        combat.put(player, integer);
    }

    public void removeFromCombat(Player player) {
        combat.remove(player);
    }

    public boolean isPearlCooldown(Player player) {
        return pearl.containsKey(player);
    }

    public Integer getPearCooldown(Player player) {
        return pearl.get(player);
    }

    public void setPearlCooldown(Player player, Integer integer) {
        pearl.put(player, integer);
    }

    public void removePearlCooldown(Player player) {
        pearl.remove(player);
    }
}
