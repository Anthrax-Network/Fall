package me.hackusatepvp.fall.command;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import me.hackusatepvp.fall.profile.ProfileManager;
import me.hackusatepvp.fall.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class RankCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage("Unknown command. Type \"help\" for help.");
            return true;
        }
        sender.sendMessage("Nope idiot.");
        if (args.length == 2) {
            Player target = Bukkit.getPlayerExact(args[0]);
            Profile profile = Fall.getInstance().getProfileManager().getProfile(target.getUniqueId());
            profile.setDonor(args[1]);
            Fall.getInstance().getProfileManager().unload(target);
            Fall.getInstance().getProfileManager().load(target);
        }
        return false;
    }
}

    /*@Command(aliases = "rank", desc = "rank command")

    // lemme show u the docs and wiki that's better
    public void rankCommand(CommandSender sender, Player player, Rank rank) {

        UUID uuid = player.getUniqueId();

        // I get the profile of the player
        Profile profile = Fall.getInstance().getProfileManager().getProfile(uuid);

        // and I access the setter that will set the rank object in the Profile class to whatever I provide in the command, menu, whatever
        // benefit of this is that I just made a whole rank system without using a single if statement and assigned ranks to players through a command with 3 lines of code...
        //profile.setRank(rank);
    } */


