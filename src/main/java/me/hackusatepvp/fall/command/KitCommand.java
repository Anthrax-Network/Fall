package me.hackusatepvp.fall.command;

import app.ashcon.intake.Command;
import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.util.StringUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KitCommand {

  /*  @Command(aliases = "kit", perms = "fall.commands.kit", desc = "kit command")
    public void kitCommand(Player player, Kit kit) {
        Fall.getInstance().getKitManager().applyKit(kit, player);
        player.sendMessage(StringUtil.format("&7You have been given a " + kit.getColor() + kit.getName() + " kit."));

        // say we want a help message, i'll do this more than one way --> FIRST WAY

        Arrays.asList(
                "&7/kit &6color &7<kit> <color>",
                "&7/kit &6apply &7<kit> <player>",
                "&7/kit &aenable &7<kit>"
        ).forEach(message -> player.sendMessage(StringUtil.format(message)));

        // ---------------------------------> SECOND WAY

        List<String> helpMessage = new ArrayList<>();
        helpMessage.add("&7/kit &6color &7<kit> <color>");
        helpMessage.add("&7/kit &6apply &7<kit> <player>");
        helpMessage.add("&7/kit &aenable &7<kit>");

        // the usual for loop
        for (String message : helpMessage) {
            player.sendMessage(StringUtil.format(message));
        }

        /* using foreach */ /* helpMessage.forEach(message -> player.sendMessage(StringUtil.format(message)));

        // ---------------------------------> THIRD WAY

       /* String[] helpMessages = new String[] {
                "&7/kit &6color &7<kit> <color>",
                "&7/kit &6apply &7<kit> <player>",
                "&7/kit &aenable &7<kit>"
        };

        // usual for loop
        for (String message : helpMessages) {
            player.sendMessage(StringUtil.format(message));
        }

        // for each
        Arrays.asList(helpMessages).forEach(message -> player.sendMessage(StringUtil.format(message)));

        // ---------------------------------> FORTH WAY (kinda)
        // if you want a for each to help you with a string list you have in a config..
        Fall.getInstance().getConfig().getStringList("help.messages").forEach(message -> player.sendMessage(StringUtil.format(message)));

        List<String> configHelpMessages = new ArrayList<>(/*this is a way of setting the contents of the list/set/map within the constructor of it */ /*Fall.getInstance().getConfig().getStringList("help.message"));

        // usual for loop for the same config string list
     /*   for (String message : configHelpMessages) {
            player.sendMessage(StringUtil.format(message));
        }

        // any other way of getting messages you want me to show you?
        // ill come up with some questions tomorrow and then I figured I start with a basic OOP just a simple Info command that will get the target's name uuid
        // i'm down
    } */
}