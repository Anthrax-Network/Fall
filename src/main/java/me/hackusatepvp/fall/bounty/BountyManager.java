package me.hackusatepvp.fall.bounty;

import me.hackusatepvp.fall.Fall;
import me.hackusatepvp.fall.profile.Profile;
import org.bukkit.entity.Player;

public class BountyManager {

    public void setBounty(Player player, boolean status, double amount) {
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (profile.isBounty()) {
            return;
        }
        profile.setBounty(status);
        if (status) {
            profile.setBountyamount(amount);
            Fall.getInstance().saveConfig();
        }
    }

    public Double getBounty(Player player) {
        Profile profile = Fall.getInstance().getProfileManager().getProfile(player.getUniqueId());
        if (!profile.isBounty()) {
            return 0.0;
        }
        return profile.getBountyamount();
    }
}
