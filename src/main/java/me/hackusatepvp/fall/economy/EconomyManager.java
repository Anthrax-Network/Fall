package me.hackusatepvp.fall.economy;

import me.hackusatepvp.fall.profile.Profile;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class EconomyManager {

    public Double getBalance(Profile profile) {
        return profile.getBalance();
    }

    public void setBalance(Profile profile, Double value) {
        profile.setBalance(value);
    }

    public void addBalance(Profile profile, Double value) {
        profile.setBalance(profile.getBalance() + value);
    }

    public void subtractBalance(Profile profile, Double value) {
        profile.setBalance(profile.getBalance() - value);
    }
}
