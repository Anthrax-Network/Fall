package me.hackusatepvp.fall.games.sumo;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.games.GameState;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class SumoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            String[] message = new String[] {
              "&7&m-----------------------------------------------",
              "&9Sumo Event Help",
              "&c* &b/sumo create &7(Create a sumo event)",
              "&c* &b/sumo join &7(Join a current sumo event)",
              "&7&m-----------------------------------------------",
            };
            Arrays.asList(message).forEach(msg -> player.sendMessage(StringUtil.format(msg)));
        } else {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("join")) {
                    if (Fall.getInstance().getGameManager().getGameState() == GameState.STOP || Fall.getInstance().getGameManager().getGameStateInt() >= 2) {
                        player.sendMessage(ChatColor.RED + "I'm sorry, you cannot join the event at this time.");
                        return true;
                    }
                    if (Fall.getInstance().getGameManager().getGame().contains(player)) {
                        player.sendMessage(ChatColor.RED + "You have already joined the event.");
                        return true;
                    }
                    if (Fall.getInstance().getGameTimer().isStarted()) {
                        player.sendMessage(ChatColor.RED + "I'm sorry, you cannot join the event at this time.");
                        return true;
                    }
                    if (Fall.getInstance().getCombatManager().inCombat(player)) {
                        player.sendMessage(ChatColor.RED + "Cannot join the even whilst in combat.");
                        return true;
                    }
                    player.sendMessage(ChatColor.GREEN + "You have joined the game.");
                    Fall.getInstance().getGameManager().getPlayerinvs().put(player, player.getInventory().getContents());
                    Fall.getInstance().getGameManager().getPlayerArmour().put(player, player.getInventory().getArmorContents());
                    player.getActivePotionEffects().clear();
                    player.getInventory().clear();
                    player.getInventory().setArmorContents(null);
                    Fall.getInstance().getGameManager().getGame().add(player);
                    Fall.getInstance().getGameManager().getGame().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + player.getName() + " &7has joined the event.")));
                    player.teleport(new Location(Bukkit.getWorld("world"), Fall.getInstance().getConfig().getDouble("Sumo.Lobby.x"), Fall.getInstance().getConfig().getInt("Sumo.Lobby.y"), Fall.getInstance().getConfig().getDouble("Sumo.Lobby.z"),
                            Fall.getInstance().getConfig().getFloat("Sumo.Lobby.pitch"), Fall.getInstance().getConfig().getInt("Sumo.Lobby.yaw")));
                }
                if (args[0].equalsIgnoreCase("create")) {
                    if (player.hasPermission("fall.donor")) {
                        if (Fall.getInstance().getGameManager().getGameState() != GameState.STOP) {
                            player.sendMessage(ChatColor.RED + "A game appears to be already running.");
                            player.sendMessage("State: " + Fall.getInstance().getGameManager().getGameState().toString());
                            return true;
                        }
                        player.sendMessage(ChatColor.GREEN + "You have created a sumo event.");
                        Fall.getInstance().getGameManager().getPlayerinvs().put(player, player.getInventory().getContents());
                        Fall.getInstance().getGameManager().getPlayerArmour().put(player, player.getInventory().getArmorContents());
                        player.getInventory().clear();
                        player.getInventory().setArmorContents(null);
                        player.getActivePotionEffects().clear();
                        Fall.getInstance().getGameTimer().runTaskTimer(Fall.getInstance(), 20, 20);
                        Fall.getInstance().getGameManager().setGameState(GameState.WAITING);
                        Fall.getInstance().getGameManager().getGame().add(player);
                        Fall.getInstance().getGameManager().setName(player.getName());
                        Fall.getInstance().getGameTimer().setStarted(false);
                        Fall.getInstance().getGameTimer().setLeft(60);
                        Bukkit.getOnlinePlayers().forEach(instance -> instance.sendMessage(StringUtil.format("&9" + player.getName() + " &7 has started a sumo event. &b(/sumo join)")));
                        player.teleport(new Location(Bukkit.getWorld("world"), Fall.getInstance().getConfig().getDouble("Sumo.Lobby.x"), Fall.getInstance().getConfig().getInt("Sumo.Lobby.y"), Fall.getInstance().getConfig().getDouble("Sumo.Lobby.z"),
                                Fall.getInstance().getConfig().getFloat("Sumo.Lobby.pitch"), Fall.getInstance().getConfig().getInt("Sumo.Lobby.yaw")));
                    } else {
                        player.sendMessage(ChatColor.RED + "This is a donor command.");
                    }
                }

                if (player.hasPermission("fall.admin")) {
                    if (args[0].equalsIgnoreCase("setlobby")) {
                        Fall.getInstance().getConfig().set("Sumo.Lobby.x", player.getLocation().getX());
                        Fall.getInstance().getConfig().set("Sumo.Lobby.y", player.getLocation().getY());
                        Fall.getInstance().getConfig().set("Sumo.Lobby.z", player.getLocation().getZ());
                        Fall.getInstance().getConfig().set("Sumo.Lobby.pitch", player.getLocation().getPitch());
                        Fall.getInstance().getConfig().set("Sumo.Lobby.yaw", player.getLocation().getYaw());
                        player.teleport(new Location(Bukkit.getWorld("world"), Fall.getInstance().getConfig().getDouble("Sumo.Lobby.x"), Fall.getInstance().getConfig().getInt("Sumo.Lobby.y"), Fall.getInstance().getConfig().getDouble("Sumo.Lobby.z"),
                                Fall.getInstance().getConfig().getFloat("Sumo.Lobby.pitch"), Fall.getInstance().getConfig().getInt("Sumo.Lobby.yaw")));
                        player.sendMessage(ChatColor.GREEN + "Successfully set lobby spawn.");
                        Fall.getInstance().saveConfig();
                    }
                    if (args[0].equalsIgnoreCase("setpos1")) {
                        Fall.getInstance().getConfig().set("Sumo.Arena.First.x", player.getLocation().getX());
                        Fall.getInstance().getConfig().set("Sumo.Arena.First.y", player.getLocation().getY());
                        Fall.getInstance().getConfig().set("Sumo.Arena.First.z", player.getLocation().getZ());
                        Fall.getInstance().getConfig().set("Sumo.Arena.First.pitch", player.getLocation().getPitch());
                        Fall.getInstance().getConfig().set("Sumo.Arena.First.yaw", player.getLocation().getYaw());
                        Fall.getInstance().saveConfig();
                        player.sendMessage(ChatColor.GREEN + "You have updated pos1.");
                    }
                    if (args[0].equalsIgnoreCase("setpos2")) {
                        Fall.getInstance().getConfig().set("Sumo.Arena.Second.x", player.getLocation().getX());
                        Fall.getInstance().getConfig().set("Sumo.Arena.Second.y", player.getLocation().getY());
                        Fall.getInstance().getConfig().set("Sumo.Arena.Second.z", player.getLocation().getZ());
                        Fall.getInstance().getConfig().set("Sumo.Arena.Second.pitch", player.getLocation().getPitch());
                        Fall.getInstance().getConfig().set("Sumo.Arena.Second.yaw", player.getLocation().getYaw());
                        Fall.getInstance().saveConfig();
                        player.sendMessage(ChatColor.GREEN + "You have updated pos2.");
                    }
                    if (args[0].equalsIgnoreCase("sety")) {
                        Fall.getInstance().getConfig().set("Sumo.Arena.Y", player.getLocation().getY());
                        Fall.getInstance().saveConfig();
                        player.sendMessage(ChatColor.GREEN + "You have updated y.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                }

            }
        }
        return false;
    }
}
