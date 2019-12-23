package me.hackusatepvp.fall.clans;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class ClanListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Fall.getInstance().getProfileManager().getProfile(player.getUniqueId()) == null) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(Fall.getInstance(), new BukkitRunnable() {
                @Override
                public void run() {
                    load(player);
                }
            }, 60L);
            return;
        }
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (Fall.getInstance().getProfileManager().getProfile(player.getUniqueId()).getClan().equalsIgnoreCase("null")) {
            player.sendMessage("No clan found. Trying again...");
            load(player);
            return;
        }
            Clan clan = Fall.getInstance().getClanManager().getClan(Fall.getInstance().getProfileManager().getProfile(player.getUniqueId()).getClan());
            ClanManager.userExists(new ClanManager.Callback<HashMap<String, Boolean>>() {
                @Override
                public void onSuccess(HashMap<String, Boolean> done) {
                    if (done.get(clan.getUuid().toString()) != Boolean.TRUE) {
                        Fall.getInstance().getClanManager().createClan(clan);
                        Fall.getInstance().getClanManager().load(clan);
                        ClanPlayer clanPlayer = new ClanPlayer(player.getUniqueId(), clan, profile.getClanrank(), 1, player);
                        Fall.getInstance().getClanManager().addToClan(clan, clanPlayer);
                        profile.setLeader(clan.getLeader());
                        profile.setPrefix(clan.getPrefix());
                        profile.setClan(clan.getName());
                    } else {
                        Fall.getInstance().getClanManager().load(clan);
                        ClanPlayer clanPlayer = new ClanPlayer(player.getUniqueId(), clan, profile.getClanrank(), 1, player);
                        Fall.getInstance().getClanManager().addToClan(clan, clanPlayer);
                        profile.setLeader(clan.getLeader());
                        profile.setPrefix(clan.getPrefix());
                        profile.setClan(clan.getName());
                    }
                }

                @Override
                public void onFailure(Throwable cause) {
                    Bukkit.getLogger().severe(cause.getMessage());
                }
            }, clan.getUuid().toString());

    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (Fall.getInstance().getClanManager().inClan(player.getUniqueId())) {
            ClanPlayer clanPlayer = Fall.getInstance().getClanManager().getClanPlayer(player);
            Fall.getInstance().getClanManager().unload(clanPlayer.getClan());
        }
    }

    public void load(Player player) {
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (!Fall.getInstance().getProfileManager().getProfile(player.getUniqueId()).getClan().equalsIgnoreCase("null")) {
            ClanPlayer clanPlayer = Fall.getInstance().getClanManager().getClanPlayer(player);
            if (!profile.getClan().equalsIgnoreCase("null")) {
                if (clanPlayer != null) {
                    if (Fall.getInstance().getClanManager().getClan(clanPlayer) != null) {
                        Clan clan = Fall.getInstance().getClanManager().getClan(clanPlayer.getClan().getName());
                        clan.getMembers().add(clanPlayer);
                        Fall.getInstance().getClanManager().addToClan(clan, clanPlayer);
                        profile.setClan(clan.getName());
                    } else {
                        Clan clan = new Clan(profile.getLeader(), profile.getClan(), profile.getPrefix());
                        clan.getMembers().add(clanPlayer);
                        Fall.getInstance().getClanManager().load(clan);
                        Fall.getInstance().getClanManager().addToClan(clan, clanPlayer);
                        Fall.getInstance().getClanManager().createClan(clan);
                        profile.setLeader(clan.getLeader());
                        profile.setPrefix(clan.getPrefix());
                        profile.setClan(clan.getName());
                    }
                } else {
                    if (Fall.getInstance().getClanManager().getClan(profile.getClan()) != null) {
                        Clan clan = Fall.getInstance().getClanManager().getClan(profile.getClan());
                        ClanPlayer work = new ClanPlayer(player.getUniqueId(), clan, profile.getClanrank(), profile.getLadder(), player);
                        clan.getMembers().add(work);
                        Fall.getInstance().getClanManager().addToClan(clan, work);
                        profile.setClan(clan.getName());
                    } else {
                        Clan clan = new Clan(profile.getLeader(), profile.getClan(), profile.getPrefix());
                        ClanPlayer work = new ClanPlayer(player.getUniqueId(), clan, profile.getClanrank(), profile.getLadder(), player);
                        clan.getMembers().add(work);
                        Fall.getInstance().getClanManager().load(clan);
                        Fall.getInstance().getClanManager().addToClan(clan, work);
                        Fall.getInstance().getClanManager().createClan(clan);
                        profile.setLeader(clan.getLeader());
                        profile.setPrefix(clan.getPrefix());
                        profile.setClan(clan.getName());
                    }
                }

                /*if (clan != null) {
                    if (clanPlayer != null) {
                        Fall.getInstance().getClanManager().addToClan(clan, clanPlayer);
                        player.sendMessage(ChatColor.GREEN + "You should now be in a clan.");
                    } else {
                        new ClanPlayer(player.getUniqueId(), clan, profile.getClanrank(), profile.getLadder());
                        player.sendMessage("Trying again...");
                        load(player);
                    }
                } else {
                    Clan work = new Clan(profile.getLeader(), profile.getClan(), profile.getPrefix());
                    if (clanPlayer != null) {
                        Fall.getInstance().getClanManager().addToClan(work, clanPlayer);
                        player.sendMessage(ChatColor.GREEN + "You should now be in a clan.");
                    } else {
                        ClanPlayer attempt = new ClanPlayer(player.getUniqueId(), work, profile.getClanrank(), profile.getLadder());
                        Fall.getInstance().getClanManager().addToClan(work, attempt);
                        player.sendMessage("Trying again...");
                        load(player);
                    }
                } */
            } else {
                player.sendMessage("Profile did not load correctly or you were never in a clan.");
            }
        } else {
            player.sendMessage("No Clan.");
        }
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getDamager() instanceof Player) {
                Player attacker = (Player) event.getDamager();
                Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
                Profile profile1 = Fall.getInstance().getProfileManager().getProfile(attacker.getUniqueId());
                if (!Fall.getInstance().getProfileManager().getProfile(player.getUniqueId()).getClan().equalsIgnoreCase("null")) {
                    if (!Fall.getInstance().getProfileManager().getProfile(attacker.getUniqueId()).getClan().equalsIgnoreCase("null")) {
                        Clan clanp = Fall.getInstance().getClanManager().getClan(profile.getClan());
                        Clan clana = Fall.getInstance().getClanManager().getClan(profile1.getClan());
                        if (clanp.getName().equalsIgnoreCase(clana.getName())) {
                            event.setCancelled(true);
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        return;
    }

}
