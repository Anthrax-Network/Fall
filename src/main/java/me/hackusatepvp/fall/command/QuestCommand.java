package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.quests.Quest;
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

public class QuestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        /*if (args.length == 1) {
            Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());

            // first way of doing it

            if (args[0].equalsIgnoreCase("diamond")) {
                profile.setActiveQuest(Quest.MINING_DIAMONDS);
            }

            // second way of doing it <--- highly suggest this one to avoid many if statements

            Quest quest = Quest.getByName(args[0]);

            profile.setActiveQuest(quest);
        } */
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (args.length == 0) {
            if (profile.getActiveQuest() != null) {
                List<String> lines = new ArrayList<>();
                lines.add("&7&m--------------------------------");
                lines.add("&4* &7Quest: &c" + Quest.getByName(profile.getActiveQuest()).getName());
                lines.add("&4* &7Goal: &c" + Quest.getByName(profile.getActiveQuest()).getGoal());
                lines.add("&4* &7Progress: &c" + Quest.getByName(profile.getActiveQuest()).getProgress(profile));
                lines.add("&4* &7Next Rank: &c" + Quest.getByName(profile.getActiveQuest()).getNext(profile).getName());
                lines.add("&7&m--------------------------------");
                lines.forEach(quest -> player.sendMessage(StringUtil.format(quest)));
            } else {
                player.sendMessage(ChatColor.RED + "It seems you have no active quest, we set one for you.");
                profile.setActiveQuest(Quest.STARTER_QUEST.getName());
            }
        } else {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target != null) {
                Profile tar = Fall.getInstance().getProfileManager().getProfile(target.getUniqueId());
                if (tar.getActiveQuest() != null) {
                    List<String> lines = new ArrayList<>();
                    lines.add("&7&m--------------------------------------------");
                    lines.add("&4* &7Quest: &c" + Quest.getByName(tar.getActiveQuest()).getName());
                    lines.add("&4* &7Goal: &c" + Quest.getByName(tar.getActiveQuest()).getGoal());
                    lines.add("&4* &7Progress: &c" + Quest.getByName(tar.getActiveQuest()).getProgress(tar));
                    lines.add("&4* &7Next Rank: &c" + Quest.getByName(tar.getActiveQuest()).getNext(tar).getName());
                    lines.add("&7&m--------------------------------------------");
                    lines.forEach(quest -> player.sendMessage(StringUtil.format(quest)));
                }
            }
        }
        return false;
    }
}
