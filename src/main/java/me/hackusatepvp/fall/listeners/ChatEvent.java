package me.hackusatepvp.fall.listeners;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.clans.ClanPlayer;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.quests.Quest;
import me.hackusatepvp.fall.tags.Tags;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if (Fall.getInstance().getClanManager().isNaughtyWord(message.toUpperCase())) {
            player.sendMessage(ChatColor.RED + "Try to be somewhat nice in chat thanks ;)");
            event.setMessage(null);
            event.setCancelled(true);
            return;
        }
        event.setCancelled(true);

        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());

        //For any player that has in a clan all methods will be here.
        if (Fall.getInstance().getClanManager().inClan(player.getUniqueId())) {
            ClanPlayer clanPlayer = Fall.getInstance().getClanManager().getClanPlayer(player);
            if (profile.getChatype().equals("public")) {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    Profile onlinepro = Fall.getInstance().getProfileManager().getProfile(online.getUniqueId());
                    if (onlinepro.isChat()) {
                        if (profile.getTag().equals("null")) {
                            if (profile.getDonor().equals("starter")) {
                                online.sendMessage(StringUtil.format("&7[&b" + clanPlayer.getClan().getPrefix() + "&7] " + Quest.getByName(profile.getActiveQuest()).getPrefix() + " " + profile.getColor() + player.getName() + " &8» " + event.getMessage()));
                            } else {
                                online.sendMessage(StringUtil.format("&7[&b" + clanPlayer.getClan().getPrefix() + "&7] " + Quest.getByName(profile.getDonor()).getPrefix() + " " + profile.getColor() + player.getName() + " &8» " + event.getMessage()));
                            }
                        } else {
                            if (profile.getDonor().equals("starter ")) {
                                online.sendMessage(StringUtil.format("&7[&b" + clanPlayer.getClan().getPrefix() + "&7] " + Quest.getByName(profile.getActiveQuest()).getPrefix() + " " + Tags.getByName(profile.getTag()).getPrefix() + " " + profile.getColor() + player.getName() + " &8» " + event.getMessage()));
                            } else {
                                online.sendMessage(StringUtil.format("&7[&b" + clanPlayer.getClan().getPrefix() + "&7] " + Quest.getByName(profile.getDonor()).getPrefix() + " " + Tags.getByName(profile.getTag()).getPrefix() + " " + profile.getColor() + player.getName() + " &8» " + event.getMessage()));
                            }
                        }
                    }
                }
                return;
            }
            if (profile.getChatype().equals("clan")) {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    ClanPlayer onlinePlayer = Fall.getInstance().getClanManager().getClanPlayer(online);
                    if (onlinePlayer.getClan().getName().equals(clanPlayer.getClan().getName())) {
                        online.sendMessage(StringUtil.format("&8[&3" + clanPlayer.getRank() + "&8] &c" + profile.getNick() + "&f: &7" + event.getMessage()));
                    }
                }
            }
        }

        if (profile.getChatype().equals("public")) {
            for (Player online : Bukkit.getOnlinePlayers()) {
                Profile onlinepro = Fall.getInstance().getProfileManager().getProfile(online.getUniqueId());
                if (onlinepro.isChat()) {
                    if (!profile.getNick().equals("null")) {
                        if (!profile.getTag().equals("null")) {
                            if (!profile.getDonor().equals("starter")) {
                                online.sendMessage(StringUtil.format(Quest.getByName(profile.getDonor()) + " " + Tags.getByName(profile.getTag()).getPrefix() + " " + profile.getColor() + profile.getNick() + " &8» " + event.getMessage()));
                            } else {
                                online.sendMessage(StringUtil.format(Quest.getActiveQuest(profile).getPrefix() + " " + Tags.getByName(profile.getTag()).getPrefix() + profile.getColor() + " " + profile.getNick() + " &8» " + event.getMessage()));
                            }
                        } else {
                            online.sendMessage(StringUtil.format(Quest.getActiveQuest(profile).getPrefix() + " " + profile.getColor() + profile.getNick() + " &8» " + event.getMessage()));
                        }
                    } else {
                        if (!profile.getTag().equals("null")) {
                            if (!profile.getDonor().equals("starter")) {
                                online.sendMessage(StringUtil.format(Quest.getByName(profile.getDonor()).getPrefix() + " " + Tags.getByName(profile.getTag()).getPrefix() + " " + profile.getColor() + player.getName() + " &8» " + event.getMessage()));
                            } else {
                                online.sendMessage(StringUtil.format(Quest.getActiveQuest(profile).getPrefix() + " " + Tags.getByName(profile.getTag()).getPrefix() + " " + profile.getColor() + player.getName() + " &8» " + event.getMessage()));
                            }
                        } else {
                            if (!profile.getDonor().equals("starter")) {
                                online.sendMessage(StringUtil.format(Quest.getByName(profile.getDonor()).getPrefix() + " " + profile.getColor() + player.getName() + " &8» " + event.getMessage()));
                            } else {
                                online.sendMessage(StringUtil.format(Quest.getActiveQuest(profile).getPrefix() + " " + profile.getColor() + player.getName() + " &8» " + event.getMessage()));
                            }
                        }
                    }
                }
            }
            return;
        }

        if (profile.getChatype().equals("staff")) {
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (online.hasPermission("fall.staff")) {
                    online.sendMessage(StringUtil.format("&7[&cStaff&7] &4" + player.getName() + " &8» " + event.getMessage()));
                }
            }
        }

    }
}
