package me.hackusatepvp.fall.bounty;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BountyManager {

    @Getter private Map<UUID, Bounty> bounties = new HashMap<>();

    public void addBounty(UUID uuid, Bounty bounty) {
        bounties.put(uuid, bounty);
    }

    public Bounty getBounty(UUID uuid) {
        return bounties.get(uuid);
    }

    public boolean hasBounty(UUID uuid) {
        return bounties.containsKey(uuid);
    }

    public void removeBounty(UUID uuid) {
        bounties.remove(uuid);
    }
}
