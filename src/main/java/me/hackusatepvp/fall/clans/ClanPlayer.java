package me.hackusatepvp.fall.clans;

import lombok.Getter;
import lombok.Setter;
import me.hackusatepvp.fall.profile.Profile;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter @Setter
public class ClanPlayer {
    private Clan clan;
    private UUID uuid;
    private String name;
    private String rank;
    private int ladder;
    private Player player;
    private Profile profile;

    public ClanPlayer(UUID uuid, Clan clan, String rank, int ladder, Player player) {
        this.uuid = uuid;
        this.clan = clan;
        this.name = clan.getName();
        this.rank = rank;
        this.ladder = ladder;
        this.player = player;
    }
}
