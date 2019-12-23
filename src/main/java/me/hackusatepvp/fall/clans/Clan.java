package me.hackusatepvp.fall.clans;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class Clan {
    private String name, prefix;
    private List<ClanPlayer> members;
    private String leader;
    private int online, size;
    private int kills, deaths;
    private Integer uuid;

    public Clan(String leader, String name, String prefix) {
        this.leader = leader;
        this.name = name;
        this.prefix = prefix;
        members = new ArrayList<>();
    }
}