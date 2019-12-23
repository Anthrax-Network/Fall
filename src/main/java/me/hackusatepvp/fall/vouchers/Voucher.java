package me.hackusatepvp.fall.vouchers;

import me.hackusatepvp.fall.quests.Quest;
import me.hackusatepvp.fall.vouchers.impl.kits.SaberKit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Optional;
import java.util.OptionalInt;

public abstract class Voucher {
    private static HashMap<String, Voucher> byName = new HashMap<>();

    private static final Voucher SABER_KIT = new SaberKit();

    private String name;

    public Voucher(String name) {
        this.name = name;
        byName.put(name, this);
    }

    public static Voucher getByName(String name) {
        return byName.get(name);
    }

    public abstract String getName();

    public abstract String getType();

    public abstract Optional<Integer> getCoast();

    public abstract Optional<String> getRank();

    public abstract Optional<String> getKit();


}
