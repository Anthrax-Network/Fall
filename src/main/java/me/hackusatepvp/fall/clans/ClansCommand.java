package me.hackusatepvp.fall.clans;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClansCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Nope");
            return true;
        }
        Player player = (Player) sender;
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (Fall.getInstance().getClanManager().inClan(player.getUniqueId())) {
            ClanPlayer clanPlayer = Fall.getInstance().getClanManager().getClanPlayer(player);
            if (args.length == 0) {
                List<String> helpMessages = new ArrayList<>();
                helpMessages.add("&7&m-----------------------------------");
                if (clanPlayer.getLadder() >= 2) {
                    helpMessages.add("&7* &b/clan &9invite &7(Invite a player to the clan.)");
                    helpMessages.add("&7* &b/clan &9kick &7(kick a player from the clan.)");
                }
                helpMessages.add("&7* &b/clan &9stats &7(Get stats from a clan)");
                helpMessages.add("&7* &b/clan &9roaster &7(Get the roaster of the clan)");
                helpMessages.add("&7&m-----------------------------------");
                helpMessages.forEach(message -> player.sendMessage(StringUtil.format(message)));
            } else {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("invite")) {
                        player.sendMessage(ChatColor.RED + "/clan invite <player>");
                    }
                    if (args[0].equalsIgnoreCase("kick")) {
                        player.sendMessage(ChatColor.RED + "/clan kick <player>");
                    }
                    if (args[0].equalsIgnoreCase("disband")) {
                        if (clanPlayer.getClan().getLeader().equalsIgnoreCase(player.getDisplayName())) {
                            player.sendMessage(ChatColor.RED + "Please type /clan disband <clan name> to confirm this action. (This cannot be undone)");
                        } else {
                            player.sendMessage(ChatColor.RED + "Sorry, only the owner can disband the clan.");
                            return true;
                        }
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("stats")) {
                        List<String> lines = new ArrayList<>();
                        Clan clan = clanPlayer.getClan();
                        lines.add("&7&m-----------------------------------");
                        lines.add("&9&l" + clan.getName() + "'s Stats");
                        lines.add("");
                        lines.add("&7* &bName: &9" + clan.getName());
                        lines.add("&7* &bPrefix: &9" + clan.getPrefix());
                        lines.add("&7* &bSize: &9" + clan.getSize());
                        lines.add("&7* &bLeader: &9" + clan.getLeader());
                        lines.add("");
                        lines.add("&9PvP Stats");
                        lines.add("&7* &bKills: &9" + clan.getKills());
                        lines.add("&7* &bDeaths: &9" + clan.getDeaths());
                        if (clan.getKills() != 0 || clan.getDeaths() != 0) {
                            lines.add("&7* &bKDR: &9" + Double.parseDouble(String.valueOf(clan.getKills() / clan.getDeaths())));
                        } else {
                            lines.add("&7* &bKDR: &9" + 0.0d);
                        }
                        lines.add("&7&m-----------------------------------");
                        lines.forEach(msg -> player.sendMessage(StringUtil.format(msg)));
                    }
                    if (args[0].equalsIgnoreCase("roaster")) {
                            List<String> lines = new ArrayList<>();
                            lines.add("&7&m-----------------------------------");
                            for (ClanPlayer members : clanPlayer.getClan().getMembers()) {
                                lines.add("&7* &9" + members.getPlayer().getName());
                            }
                            lines.add("&7&m-----------------------------------");
                            lines.forEach(msg -> player.sendMessage(StringUtil.format(msg)));
                    }
                    return true;
                }
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("stats")) {
                        String attempt = args[1];
                        Clan clan = Fall.getInstance().getClanManager().getClan(attempt);
                        if (clan != null) {
                            List<String> lines = new ArrayList<>();
                            lines.add("&7&m-----------------------------------");
                            lines.add("&9&l" + clan.getName() + "'s Stats");
                            lines.add("");
                            lines.add("&7* &bName: &9" + clan.getName());
                            lines.add("&7* &bPrefix: &9" + clan.getPrefix());
                            lines.add("&7* &bSize: &9" + clan.getSize());
                            lines.add("&7* &bLeader: &9" + clan.getLeader());
                            lines.add("");
                            lines.add("&9PvP Stats");
                            lines.add("&7* &bKills: &9" + clan.getKills());
                            lines.add("&7* &bDeaths: &9" + clan.getDeaths());
                            if (clan.getKills() != 0 || clan.getDeaths() != 0) {
                                lines.add("&7* &bKDR: &9" + Double.parseDouble(String.valueOf(clan.getKills() / clan.getDeaths())));
                            } else {
                                lines.add("&7* &bKDR: &9" + 0.0d);
                            }
                            lines.add("&7&m-----------------------------------");
                            lines.forEach(msg -> player.sendMessage(StringUtil.format(msg)));
                        } else {
                            player.sendMessage(ChatColor.RED + "Clan not found, make sure you type it exact.");
                        }
                    }
                    if (args[0].equalsIgnoreCase("roaster")) {
                        String attempt = args[1];
                        Clan clan = Fall.getInstance().getClanManager().getClan(attempt);
                        if (clan != null) {
                            List<String> lines = new ArrayList<>();
                            lines.add("&7&m-----------------------------------");
                            for (ClanPlayer members : clan.getMembers()) {
                                lines.add("&7* &9" + members.getPlayer().getName());
                            }
                            lines.add("&7&m-----------------------------------");
                            lines.forEach(msg -> player.sendMessage(StringUtil.format(msg)));
                        }
                    }
                    if (args[0].equalsIgnoreCase("invite")) {
                        if (clanPlayer.getLadder() >= 2) {
                            Player target = Bukkit.getPlayer(args[1]);
                            if (target == null) {
                                player.sendMessage(ChatColor.RED + "Target not found.");
                                return true;
                            }
                            if (!Fall.getInstance().getClanManager().inClan(target.getUniqueId())) {
                                Profile targetpro = Fall.getInstance().getProfileManager().getProfile(target.getUniqueId());
                                if (targetpro.getInvites() == null) {
                                    targetpro.setInvites(clanPlayer.getClan().getName());
                                    target.sendMessage(StringUtil.format("&9&l" + clanPlayer.getClan().getName() + " &bhas invited you to join there clan &7(&9/clan join&7) to accept the invitation &7(&c/clan deny&7) &b to deny the invitation."));
                                    for (ClanPlayer members : clanPlayer.getClan().getMembers()) {
                                        Player online = Bukkit.getPlayer(members.getUuid());
                                        online.sendMessage(StringUtil.format("&9&l" + player.getName() + " &bhas invited &c&l" + target.getName() + " &bto the clan."));
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + "This player has already been invited to a clan.");
                                }
                            } else {
                                player.sendMessage(ChatColor.RED + "Target is in already in a clan.");
                                return true;
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have permissions.");
                        }
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("kick")) {
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if (clanPlayer.getLadder() < 2) {
                            player.sendMessage(ChatColor.RED + "You do not have permission.");
                            return true;
                        }
                        if (target == null) {
                            player.sendMessage(ChatColor.RED + "Target not found.");
                            return true;
                        }
                        if (!Fall.getInstance().getClanManager().inClan(target.getUniqueId())) {
                            player.sendMessage(ChatColor.RED + "You do not have permission or the player is not in the clan.");
                            return true;
                        }
                        ClanPlayer targetPlayer = Fall.getInstance().getClanManager().getClanPlayer(player);
                        if (clanPlayer.getLadder() <= targetPlayer.getLadder()) {
                            player.sendMessage(ChatColor.RED + "You do not have permission to kick this player");
                            return true;
                        }
                        if (player.getName().equals(target.getName())) {
                            player.sendMessage(ChatColor.RED + "You cannot kick yourself.");
                        }
                        Clan targetClan = clanPlayer.getClan();
                        if (targetClan.getName().equals(clanPlayer.getClan().getName())) {
                            targetClan.getMembers().remove(targetPlayer);
                            Fall.getInstance().getClanManager().removeFromClan(targetClan, targetPlayer, target);
                        } else {
                            player.sendMessage(ChatColor.RED + "Target is not in your clan.");
                        }
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("disband")) {
                        if (clanPlayer.getClan().getLeader().equalsIgnoreCase(player.getDisplayName())) {
                            Clan clan = Fall.getInstance().getClanManager().getClan(clanPlayer);
                            for (ClanPlayer members : clan.getMembers()) {
                                members.getPlayer().sendMessage(ChatColor.RED + "Clan has been disbanded.");
                                Profile promem = Fall.getInstance().getProfileManager().getProfile(members.getUuid());
                                profile.setClan("null");
                                profile.setClanrank("null");
                                profile.setLadder(0);
                                profile.setPrefix("null");
                                profile.setLeader("null");
                                promem.setClan("null");
                                promem.setClanrank("null");
                                promem.setLadder(0);
                                promem.setPrefix("null");
                                promem.setLeader("null");
                            }
                            player.sendMessage(ChatColor.RED + "Clan data may take up to 24hr to be wiped.");
                            //do last
                            Fall.getInstance().getClanManager().deleteClan(clan);
                        } else {
                            player.sendMessage(ChatColor.RED + "Sorry, only the owner can disband the clan.");
                            return true;
                        }
                        return true;
                    }
                    return true;
                }
            }
        } else {
            String[] message = new String[] {
                    "&7&m-----------------------------------",
                    "&c/clan create <name> <prefix>",
                    "&c/clan join <name>",
                    "&7&m-----------------------------------"

            };
            if (args.length == 0) {
                Arrays.asList(message).forEach(msg -> player.sendMessage(StringUtil.format(msg)));
            } else {
                if (args.length == 1) {
                    String first_argument = args[0];

                    if (first_argument.equalsIgnoreCase("create")) {
                        player.sendMessage(ChatColor.RED + "Usage: /clan create <name> <prefix>");
                        return true;
                    }
                    if (first_argument.equals("join")) {
                        if (!(profile.getInvites() == null)) {
                            Clan clan = Fall.getInstance().getClanManager().getClan(profile.getInvites());
                            if (clan.getSize() < 8) {
                                ClanPlayer clanPlayer = new ClanPlayer(player.getUniqueId(), clan, "default", 0, player);
                                clanPlayer.getClan().getMembers().add(clanPlayer);
                                for (ClanPlayer members : clanPlayer.getClan().getMembers()) {
                                    Player online = Bukkit.getPlayer(members.getUuid());
                                    if (online != null) {
                                        online.sendMessage(StringUtil.format("&9&l" + player.getName() + " &bhas joined the clan."));
                                    }
                                }
                                profile.setClan(clan.getName());
                                profile.setClanrank("default");
                                profile.setLeader(clan.getLeader());
                                clan.setSize(clan.getSize() + 1);
                            } else {
                                player.sendMessage(ChatColor.RED + "Sorry, but the clan is full.");
                                profile.setInvites("null");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You have not been invited to a clan.");
                        }

                        return true;
                    }
                    Arrays.asList(message).forEach(msg -> player.sendMessage(StringUtil.format(msg)));
                    return true;
                }
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("create")) {
                        player.sendMessage(ChatColor.RED + "Usage: /clan create <" + args[1] + "> <prefix>");
                        return true;
                    }
                    Arrays.asList(message).forEach(msg -> player.sendMessage(StringUtil.format(msg)));
                    return true;
                }
                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("create")) {
                        String clan = args[1];
                        String prefix = args[2];
                        if (Fall.getInstance().getClanManager().getClans().contains(Fall.getInstance().getClanManager().getClan(clan.toLowerCase()))) {
                            player.sendMessage(ChatColor.RED + "Clan name already exists.");
                            return true;
                        }
                        if (!clan.matches("[0-9a-zA-Z]*")) {
                            player.sendMessage(ChatColor.RED + "Your clan name can only contain letters");
                            return true;
                        }
                        if (Fall.getInstance().getClanManager().isNaughtyWord(clan.toLowerCase())) {
                            player.sendMessage(ChatColor.RED + "Your clan name contains blocked words try again.");
                            return true;
                        }
                        if (clan.contains("&")) {
                            player.sendMessage(ChatColor.RED + "Your clan name cannot contain colors.");
                            return true;
                        }
                        if (args[1].length() > 16) {
                            player.sendMessage(ChatColor.RED + "Clan name cannot be more than 16 characters.");
                            return true;
                        }
                        Clan clan1 = new Clan(player.getName(), clan, prefix);
                        ClanPlayer clanPlayer = new ClanPlayer(player.getUniqueId(), clan1, "leader", 5, player);
                        clan1.getMembers().add(clanPlayer);
                        Fall.getInstance().getClanManager().createClan(clan1);
                        player.sendMessage(ChatColor.GREEN + "You have successfully created " + clan + ".");
                        profile.setClan(clan);
                        profile.setLadder(5);
                        profile.setClanrank("leader");
                        profile.setPrefix(prefix);
                        profile.setLeader(player.getName());
                        int uuid = Fall.getInstance().getIdHandler().generateID();
                        clan1.setName(clan);
                        clan1.setLeader(player.getName());
                        clan1.setPrefix(prefix);
                        clan1.setUuid(uuid);
                        clan1.setSize(1);
                        return true;
                    }
                    Arrays.asList(message).forEach(msg -> player.sendMessage(StringUtil.format(msg)));
                    return true;
                }
            }
        }
        return false;
    }
}
