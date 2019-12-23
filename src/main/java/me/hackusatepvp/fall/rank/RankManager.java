package me.hackusatepvp.fall.rank;

import java.util.HashSet;
import java.util.Set;

public class RankManager {

    private Set<Rank> ranks = new HashSet<>();

    public void addRank(Rank rank) {
        ranks.add(rank);
    }

    public boolean removeRank(Rank rank) {
        return ranks.remove(rank);
    }
}
