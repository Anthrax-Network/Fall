package me.hackusatepvp.fall.info;

import java.util.HashMap;
import java.util.UUID;

public class InfoManager {
    private HashMap<UUID, Info> infos = new HashMap<>();

    public void addInfo(UUID uuid, Info info) {
        infos.put(uuid, info);
    }

    public Info getInfo(UUID uuid) {
        return infos.get(uuid);
    }
}
