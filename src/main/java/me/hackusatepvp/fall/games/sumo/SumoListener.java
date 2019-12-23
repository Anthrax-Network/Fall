package me.hackusatepvp.fall.games.sumo;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.games.GameState;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveBlockEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class SumoListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (Fall.getInstance().getGameManager().getGame().contains(player)) {
                if (Fall.getInstance().getGameManager().getGameState() != GameState.GAME) {
                    if (Fall.getInstance().getGameTimer().getPlayer1().equals(player.getName()) || Fall.getInstance().getGameTimer().getPlayer2().equals(player.getName())) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

  /*  @EventHandler
    public void onMove(PlayerMoveBlockEvent event) {
        Player player = event.getPlayer();
        if (Fall.getInstance().getGameManager().getGame().contains(player)) {
            if (Fall.getInstance().getGameTimer().getPlayer1().equals(player.getName()) || Fall.getInstance().getGameTimer().getPlayer2().equals(player.getName())) {
                if (Fall.getInstance().getGameManager().getGameState() == GameState.STARTING) {
                    event.setCancelled(true);
                }
            }
            if (event.getTo().getBlock().isLiquid()) {
                if (Fall.getInstance().getGameTimer().getPlayer1().equals(player.getName())) {
                    Fall.getInstance().getGameManager().getGame().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + player.getName() + " &7has been eliminated &c" + Fall.getInstance().getGameTimer().getPlayer2() + " &7wins.")));
                    Fall.getInstance().getGameManager().setGameState(GameState.WAITING);
                    Fall.getInstance().getGameTimer().setLeft(60);
                    player.damage(100);
                }
                if (Fall .getInstance().getGameTimer().getPlayer2().equals(player.getName())) {
                    Fall.getInstance().getGameManager().getGame().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + player.getName() + " &7has been eliminated &c" + Fall.getInstance().getGameTimer().getPlayer1() + " &7wins.")));
                    Fall.getInstance().getGameManager().setGameState(GameState.WAITING);
                    Fall.getInstance().getGameTimer().setLeft(60);
                    player.damage(100);
                }
            }
        }
    } */

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = event.getPlayer().getUniqueId();
        Location spawn = new Location(Bukkit.getWorld("world"), Fall.getInstance().getConfig().getDouble("Sumo.Lobby.x"), Fall.getInstance().getConfig().getInt("Sumo.Lobby.y"), Fall.getInstance().getConfig().getDouble("Sumo.Lobby.z"),
                Fall.getInstance().getConfig().getFloat("Sumo.Lobby.pitch"), Fall.getInstance().getConfig().getInt("Sumo.Lobby.yaw"));

        if (!isInWater(event.getPlayer())) {
            if (event.getTo().getBlockX() == event.getFrom().getBlockX() &&
                    event.getTo().getBlockY() == event.getFrom().getBlockY() &&
                    event.getTo().getBlockZ() == event.getFrom().getBlockZ()) {
                return;
            }
        } else {
            if (Fall.getInstance().getGameManager().getGameState() == GameState.STARTING) {
                if (Fall.getInstance().getGameManager().getName().contains(player.getName())) {
                    if (isInWater(event.getPlayer())) {
                        event.getPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation());
                        event.getPlayer().sendMessage(StringUtil.format("&4&lEvent &8» &fYou were removed from the event."));
                        event.getPlayer().getInventory().clear();
                        Fall.getInstance().getGameManager().getGame().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + player.getName() + " &7has been eliminated &c" + Fall.getInstance().getGameTimer().getPlayer1() + " &7wins.")));
                        Fall.getInstance().getGameManager().setGameState(GameState.WAITING);
                        Fall.getInstance().getGameTimer().setLeft(60);
                        player.teleport(spawn);
                        Fall.getInstance().getGameManager().getGame().forEach(instance -> instance.sendMessage(StringUtil.format("&4&lEvent &8» &a" + Fall.getInstance().getGameTimer().getPlayer1() + "&f has won against opponent &c" + Fall.getInstance().getGameTimer().getPlayer2() + "&7 (" + Fall.getInstance().getGameManager().getGame().stream() + " remaining)")));
                    }
                }
            }
        }
    }




    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (Fall.getInstance().getGameManager().getGame().contains(player)) {
            if (Fall.getInstance().getGameManager().getGameState() == GameState.GAME) {
                if (Fall.getInstance().getGameTimer().getPlayer1().equals(player.getName())) {
                    Fall.getInstance().getGameManager().getGame().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + player.getName() + " &7has disconnected &c" + Fall.getInstance().getGameTimer().getPlayer2() + " &7wins.")));
                    Fall.getInstance().getGameManager().setGameState(GameState.WAITING);
                    Fall.getInstance().getGameTimer().setLeft(60);
                    Fall.getInstance().getGameManager().getGame().remove(player);
                } else if (Fall.getInstance().getGameTimer().getPlayer2().equals(player.getName())) {
                    Fall.getInstance().getGameManager().getGame().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + player.getName() + " &7has disconnected &c" + Fall.getInstance().getGameTimer().getPlayer1() + " &7wins.")));
                    Fall.getInstance().getGameManager().setGameState(GameState.WAITING);
                    Fall.getInstance().getGameTimer().setLeft(60);
                    Fall.getInstance().getGameManager().getGame().remove(player);
                }
            }
        }
    }

    private boolean isInWater(Player player) {
        final Material m = player.getLocation().getBlock().getType();
        return m == Material.STATIONARY_WATER || m == Material.WATER;
    }

}
