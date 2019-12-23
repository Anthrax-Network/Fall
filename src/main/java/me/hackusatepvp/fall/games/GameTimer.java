package me.hackusatepvp.fall.games;

import lombok.Getter;
import lombok.Setter;
import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;

public class GameTimer extends BukkitRunnable {
    @Getter @Setter private int left;
    @Getter private int total = 120;
    @Getter @Setter String player1;
    @Getter @Setter String player2;

    public void start() {
        this.runTaskTimer(Fall.getInstance(), 20, 20);
    }

    public void stop() {
        this.cancel();
    }

    private void broadCastTotal() {
        if (total == 120) {
            Bukkit.getOnlinePlayers().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + Fall.getInstance().getGameManager().getName() + " &7 has started a sumo event. &b(/sumo join)")));
        }
        if (total == 90) {
            Bukkit.getOnlinePlayers().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + Fall.getInstance().getGameManager().getName() + " &7 has started a sumo event. &b(/sumo join)")));
        }
        if (total == 60) {
            Bukkit.getOnlinePlayers().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + Fall.getInstance().getGameManager().getName() + " &7 has started a sumo event. &b(/sumo join)")));
        }
        if (total == 30) {
            Bukkit.getOnlinePlayers().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + Fall.getInstance().getGameManager().getName() + " &7 has started a sumo event. &b(/sumo join)")));
        }
        if (total == 15) {
            Bukkit.getOnlinePlayers().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + Fall.getInstance().getGameManager().getName() + " &7 has started a sumo event. &b(/sumo join)")));
        }
        if (total == 10) {
            Bukkit.getOnlinePlayers().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + Fall.getInstance().getGameManager().getName() + " &7 has started a sumo event. &b(/sumo join)")));
        }
        if (total == 5) {
            Bukkit.getOnlinePlayers().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + Fall.getInstance().getGameManager().getName() + " &7 has started a sumo event. &b(/sumo join)")));
        }
        if (total == 0) {
            Bukkit.getOnlinePlayers().forEach(instance -> instance.sendMessage(StringUtil.format("&cNot enough players joined the event. Event has been cancelled.")));
            Fall.getInstance().getGameManager().getGame().forEach(player -> Fall.getInstance().getGameManager().getGame().remove(player));
            this.cancel();
            Fall.getInstance().getGameManager().setGameState(GameState.STOP);
        }
    }

    @Override
    public void run() {
        --left;
        --total;
        if (Fall.getInstance().getGameManager().getGameState() == GameState.STOP) {
            this.cancel();
        } else if (Fall.getInstance().getGameManager().getGameState() == GameState.WAITING) {
            broadCastTotal();
            if (Bukkit.getOnlinePlayers().size() < 2) {
                this.left = 60;
            }
            if (left == 0) {
                Iterator iterator = Fall.getInstance().getGameManager().getGame().iterator();
                int count = 0;
                while (iterator.hasNext()) {
                    Player player = (Player) iterator.next();
                    count++;
                    if (count == 1) {
                        player.teleport(new Location(Bukkit.getWorld("world"), Fall.getInstance().getConfig().getDouble("Sumo.Arena.First.x"), Fall.getInstance().getConfig().getInt("Sumo.Arena.First.y"), Fall.getInstance().getConfig().getDouble("Sumo.Arena.First.z"), Fall.getInstance().getConfig().getFloat("Sumo.Arena.First.pitch"), Fall.getInstance().getConfig().getFloat("Sumo.Arena.First.yaw")));
                        setPlayer1(player.getName());
                    } else if (count == 2) {
                        player.teleport(new Location(Bukkit.getWorld("world"), Fall.getInstance().getConfig().getDouble("Sumo.Arena.Second.x"), Fall.getInstance().getConfig().getInt("Sumo.Arena.Second.y"), Fall.getInstance().getConfig().getDouble("Sumo.Arena.Second.z"), Fall.getInstance().getConfig().getFloat("Sumo.Arena.Second.pitch"), Fall.getInstance().getConfig().getFloat("Sumo.Arena.Second.yaw")));
                        setPlayer2(player.getName());
                    } else {
                        player.teleport(new Location(Bukkit.getWorld("world"), Fall.getInstance().getConfig().getDouble("Sumo.Lobby.x"), Fall.getInstance().getConfig().getInt("Sumo.Lobby.y"), Fall.getInstance().getConfig().getDouble("Sumo.Lobby.z"),
                                Fall.getInstance().getConfig().getFloat("Sumo.Lobby.pitch"), Fall.getInstance().getConfig().getInt("Sumo.Lobby.yaw")));
                    }
                }
                Fall.getInstance().getGameManager().setGameState(GameState.STARTING);
                left = 12;
            }
        } else if (Fall.getInstance().getGameManager().getGameState() == GameState.STARTING) {
            if (left == 10) {
                Bukkit.getPlayer(player1).sendMessage(StringUtil.format("&7Starting in &910s"));
                Bukkit.getPlayer(player2).sendMessage(StringUtil.format("&7Starting in &910s"));
            }
            if (left == 5) {
                Bukkit.getPlayer(player1).sendMessage(StringUtil.format("&7Starting in &95s"));
                Bukkit.getPlayer(player2).sendMessage(StringUtil.format("&7Starting in &95s"));
            }
            if (left == 4) {
                Bukkit.getPlayer(player1).sendMessage(StringUtil.format("&7Starting in &94s"));
                Bukkit.getPlayer(player2).sendMessage(StringUtil.format("&7Starting in &94s"));
            }
            if (left == 3) {
                Bukkit.getPlayer(player1).sendMessage(StringUtil.format("&7Starting in &93s"));
                Bukkit.getPlayer(player2).sendMessage(StringUtil.format("&7Starting in &93s"));
            }
            if (left == 2) {
                Bukkit.getPlayer(player1).sendMessage(StringUtil.format("&7Starting in &92s"));
                Bukkit.getPlayer(player2).sendMessage(StringUtil.format("&7Starting in &92s"));
            }
            if (left == 1) {
                Bukkit.getPlayer(player1).sendMessage(StringUtil.format("&7Starting in &91s"));
                Bukkit.getPlayer(player2).sendMessage(StringUtil.format("&7Starting in &91s"));
            }
            if (left == 0) {
                Fall.getInstance().getGameManager().setGameState(GameState.GAME);
                Bukkit.getPlayer(player1).sendMessage(StringUtil.format("\n&9GO!\n"));
                Bukkit.getPlayer(player2).sendMessage(StringUtil.format("\n&9GO!\n"));
                left = 120;
            }
        } else if (Fall.getInstance().getGameManager().getGameState() == GameState.GAME) {
            if (left == 0) {
                Fall.getInstance().getGameManager().getGame().remove(Bukkit.getPlayer(player1));
                Fall.getInstance().getGameManager().getGame().remove(Bukkit.getPlayer(player2));
                Bukkit.getPlayer(player1).damage(100f);
                Bukkit.getPlayer(player2).damage(100f);
                Fall.getInstance().getGameManager().getGame().forEach(instance -> instance.sendMessage(StringUtil.format("\n&7Time has ran out, both players will be eliminated.\n")));
                Iterator iterator = Fall.getInstance().getGameManager().getGame().iterator();
                int count = 0;
                if (Fall.getInstance().getGameManager().getGame().size() % 2 == 0) {
                    while (iterator.hasNext()) {
                        count++;
                        Player player = (Player) iterator.next();
                        if (count == 1) {
                            player.teleport(new Location(Bukkit.getWorld("world"), Fall.getInstance().getConfig().getDouble("Sumo.Arena.First.x"), Fall.getInstance().getConfig().getInt("Sumo.Arena.First.y"), Fall.getInstance().getConfig().getDouble("Sumo.Arena.First.z"), Fall.getInstance().getConfig().getFloat("Sumo.Arena.First.pitch"), Fall.getInstance().getConfig().getFloat("Sumo.Arena.First.yaw")));
                            setPlayer1(player.getName());
                        } else if (count == 2) {
                            player.teleport(new Location(Bukkit.getWorld("world"), Fall.getInstance().getConfig().getDouble("Sumo.Arena.Second.x"), Fall.getInstance().getConfig().getInt("Sumo.Arena.Second.y"), Fall.getInstance().getConfig().getDouble("Sumo.Arena.Second.z"), Fall.getInstance().getConfig().getFloat("Sumo.Arena.Second.pitch"), Fall.getInstance().getConfig().getFloat("Sumo.Arena.Second.yaw")));
                            setPlayer2(player.getName());
                        }
                    }
                } else {
                    while (iterator.hasNext()) {
                        //we remove 1 player to make it even.
                        Player player = (Player) iterator.next();
                        player.sendMessage(ChatColor.RED + "You were removed from the event because it requires a even number.");
                        Fall.getInstance().getGameManager().getGame().remove(player);
                    }
                    if (Fall.getInstance().getGameManager().getGame().size() % 2 == 0) {
                        while (iterator.hasNext()) {
                            count++;
                            Player player = (Player) iterator.next();
                            if (count == 1) {
                                player.teleport(new Location(Bukkit.getWorld("world"), Fall.getInstance().getConfig().getDouble("Sumo.Arena.First.x"), Fall.getInstance().getConfig().getInt("Sumo.Arena.First.y"), Fall.getInstance().getConfig().getDouble("Sumo.Arena.First.z"), Fall.getInstance().getConfig().getFloat("Sumo.Arena.First.pitch"), Fall.getInstance().getConfig().getFloat("Sumo.Arena.First.yaw")));
                                setPlayer1(player.getName());
                            } else if (count == 2) {
                                player.teleport(new Location(Bukkit.getWorld("world"), Fall.getInstance().getConfig().getDouble("Sumo.Arena.Second.x"), Fall.getInstance().getConfig().getInt("Sumo.Arena.Second.y"), Fall.getInstance().getConfig().getDouble("Sumo.Arena.Second.z"), Fall.getInstance().getConfig().getFloat("Sumo.Arena.Second.pitch"), Fall.getInstance().getConfig().getFloat("Sumo.Arena.Second.yaw")));
                                setPlayer2(player.getName());
                            }
                        }
                    }
                }
                Fall.getInstance().getGameManager().setGameState(GameState.STARTING);
            }
        }
    }
}
