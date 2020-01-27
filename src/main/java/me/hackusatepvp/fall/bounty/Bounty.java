package me.hackusatepvp.fall.bounty;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Bounty {
    private UUID uuid;
    private String name;

    public Bounty(UUID uuid) {
        this.uuid = uuid;
    }
}
