package me.hackusatepvp.fall.quests.impl;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.quests.Quest;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class StarterQuest extends Quest {

    public StarterQuest() {
        super("Starter");
    }

    @Override
    public Quest getNext(Profile profile) {
        return MASTER_QUEST;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.DIAMOND_SWORD);
    }

    @Override
    public String getCompletion(Player player, Object data) {
        return null;
    }

    @Override
    public int getGoal() {
        return 15;
    }

    @Override
    public Double getProgress(Profile profile) {
        double ratio = 0.69D;
        if (profile.getKills() != 0) {
            double divided = ((double) profile.getKills()) / ((double) getGoal());
            ratio = Math.round(divided);
        }
        if (ratio == 0.69D) {
            return 0.0D;
        }
        return ratio;
    }

    @Override
    public String rankUpMessage() {
        return StringUtil.format("&7You have ranked up to &7&lMASTER&7.");
    }

    @Override
    public String getPrefix() {
        return StringUtil.format("&7[&fStarter&7]");
    }


    @Override
    public void onKill(Player killer, LivingEntity victom, EntityDeathEvent event) {
        Bukkit.getLogger().info("[Quest] event is registered.");
        Profile profile = Fall.getInstance().getProfileManager().getProfile(killer.getUniqueId());

        if (profile.getKills() == getGoal()) {
            if (profile.getDonor().equalsIgnoreCase("starter")) {
                killer.sendMessage(rankUpMessage());
                profile.setActiveQuest(getNext(profile).getName());
                return;
            }
            if (profile.getDonor().equalsIgnoreCase("master")) {
                killer.sendMessage(StringUtil.format("&aCongrats! &7You have reached your donor rank &7&nMASTER&r&7."));
                return;
            }
            killer.sendMessage(ChatColor.RED + "You have ranked up but since you are a donor this will not affect your rank.");
        }
    }

}
