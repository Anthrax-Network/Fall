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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class SumoListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (Fall.getInstance().getGameManager().getGame().contains(player)) {
                if (Fall.getInstance().getGameManager().getGameState() != GameState.GAME) {
                    if (Fall.getInstance().getGameManager().getGame().contains(player)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (Fall.getInstance().getGameManager().getGame().contains(player)) {
            if (Fall.getInstance().getGameManager().getGameState() == GameState.GAME) {
                if (Fall.getInstance().getGameTimer().getPlayer1().equals(player.getName())) {
                    if (player.getLocation().getY() <= Fall.getInstance().getConfig().getInt("Sumo.Arena.Y")) {
                        player.sendMessage(ChatColor.RED + "You have been eliminated.");
                        Player player2 = Bukkit.getPlayer(Fall.getInstance().getGameTimer().getPlayer2());
                        player2.teleport(new Location(Bukkit.getWorld("world"), Fall.getInstance().getConfig().getDouble("Sumo.Lobby.x"), Fall.getInstance().getConfig().getInt("Sumo.Lobby.y"), Fall.getInstance().getConfig().getDouble("Sumo.Lobby.z"),
                                Fall.getInstance().getConfig().getFloat("Sumo.Lobby.pitch"), Fall.getInstance().getConfig().getInt("Sumo.Lobby.yaw")));
                        Fall.getInstance().getGameManager().getGame().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + player.getName() + " &7has been eliminated &c" + Fall.getInstance().getGameTimer().getPlayer2() + " &7wins.")));
                        Fall.getInstance().getGameManager().setGameState(GameState.WAITING);
                        Fall.getInstance().getGameTimer().setLeft(5);
                        if (Fall.getInstance().getGameManager().getGame().size() == 2) {
                            Fall.getInstance().getGameManager().getGame().forEach(instance -> instance.sendMessage(StringUtil.format("&cThe game has been concluded.")));
                            if (Fall.getInstance().getGameManager().getGame().stream().findFirst().isPresent()) {
                                Bukkit.getOnlinePlayers().forEach(instance -> player.sendMessage(StringUtil.format("&7* &9" + Fall.getInstance().getGameManager().getGame().stream().findFirst().get().getName() + " &7has won the game!")));
                            }
                            Fall.getInstance().getGameManager().getGame().forEach(instance -> Fall.getInstance().getGameManager().getGame().remove(player));
                            Fall.getInstance().getGameManager().setGameState(GameState.STOP);
                            Fall.getInstance().getGameTimer().setStarted(false);
                        } else {
                            Fall.getInstance().getGameManager().getGame().remove(player);
                            player.teleport(new Location(Bukkit.getWorld("world"), Fall.getInstance().getConfig().getDouble("Sumo.Lobby.x"), Fall.getInstance().getConfig().getInt("Sumo.Lobby.y"), Fall.getInstance().getConfig().getDouble("Sumo.Lobby.z"),
                                    Fall.getInstance().getConfig().getFloat("Sumo.Lobby.pitch"), Fall.getInstance().getConfig().getInt("Sumo.Lobby.yaw")));
                            Fall.getInstance().getPlayerManager().addSpectator(player);
                            Bukkit.getOnlinePlayers().forEach(instance -> instance.hidePlayer(player));
                            player.setFlying(true);
                        }
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Fall.getInstance(), new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.getInventory().setContents(Fall.getInstance().getGameManager().getPlayerinvs().get(player));
                                player.getInventory().setArmorContents(Fall.getInstance().getGameManager().getPlayerArmour().get(player));
                            }
                        }, 20);
                    }
                } else {
                    if (Fall.getInstance().getGameTimer().getPlayer2().equals(player.getName())) {
                        if (player.getLocation().getY() <= Fall.getInstance().getConfig().getInt("Sumo.Arena.Y")) {
                            Fall.getInstance().getGameManager().getGame().remove(player);
                            player.sendMessage(ChatColor.RED + "You have been eliminated.");
                            Player player1 = Bukkit.getPlayer(Fall.getInstance().getGameTimer().getPlayer1());
                            player1.teleport(new Location(Bukkit.getWorld("world"), Fall.getInstance().getConfig().getDouble("Sumo.Lobby.x"), Fall.getInstance().getConfig().getInt("Sumo.Lobby.y"), Fall.getInstance().getConfig().getDouble("Sumo.Lobby.z"),
                                    Fall.getInstance().getConfig().getFloat("Sumo.Lobby.pitch"), Fall.getInstance().getConfig().getInt("Sumo.Lobby.yaw")));
                            Fall.getInstance().getGameManager().getGame().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + player.getName() + " &7has been eliminated &c" + Fall.getInstance().getGameTimer().getPlayer1() + " &7wins.")));
                            Fall.getInstance().getGameManager().setGameState(GameState.WAITING);
                            Fall.getInstance().getGameTimer().setLeft(5);
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Fall.getInstance(), new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player.getInventory().setContents(Fall.getInstance().getGameManager().getPlayerinvs().get(player));
                                    player.getInventory().setArmorContents(Fall.getInstance().getGameManager().getPlayerArmour().get(player));
                                }
                            }, 20);

                        }
                    }
                }
            } else if (Fall.getInstance().getGameManager().getGameState() == GameState.RUNNING) {
                event.setTo(event.getFrom());
            }

        }
    }




    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (Fall.getInstance().getGameManager().getGame().contains(player)) {
            if (Fall.getInstance().getPlayerManager().getSpectator().contains(player)) {
                Fall.getInstance().getPlayerManager().spectator.remove(player);
                Bukkit.getOnlinePlayers().forEach(player::showPlayer);
                player.setFlying(false);
            }
            if (Fall.getInstance().getGameManager().getGameState() == GameState.GAME) {
                if (Fall.getInstance().getGameTimer().getPlayer1().equals(player.getName())) {
                    Fall.getInstance().getGameManager().getGame().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + player.getName() + " &7has disconnected &c" + Fall.getInstance().getGameTimer().getPlayer2() + " &7wins.")));
                    Fall.getInstance().getGameManager().setGameState(GameState.WAITING);
                    Fall.getInstance().getGameTimer().setLeft(60);
                    Fall.getInstance().getGameManager().getGame().remove(player);
                } else if (Fall.getInstance().getGameTimer().getPlayer2().equals(player.getName())) {
                    if (Fall.getInstance().getGameManager().getGame().size() == 2) {

                    }
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
