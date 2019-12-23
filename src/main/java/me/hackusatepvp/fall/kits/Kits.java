package me.hackusatepvp.fall.kits;

import me.hackusatepvp.fall.kits.impl.*;
import me.hackusatepvp.fall.profile.Profile;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public abstract class Kits {

    private static Map<String, Kits> byName = new HashMap<>();

    private static final Kits START_KIT = new StarterKit();
    private static final Kits MASTER_KIT = new MasterKit();
    private static final Kits RIDER_KIT = new RiderKit();
    private static final Kits CASTER_KIT = new CasterKit();
    private static final Kits ARCHER_KIT = new ArcherKit();
    private static final Kits ASSASSIN_KIT = new AssassinKit();
    private static final Kits LANCER_KIT = new LancerKit();
    private static final Kits BERSERKER_KIT = new BerserkerKit();
    private static final Kits SABER_KIT = new SaberKit();
    private static final Kits FATE_KIT = new FateKit();

    private String name;

    public Kits(String name) {
        this.name = name;

        byName.put(name, this);
    }

    public static Kits getByName(String name) {
        return byName.get(name);
    }

    public abstract String getName();

    public abstract String getColor();

    public abstract String getPermission();

    public abstract String getDelay();

    public abstract String getCommand();

    public abstract ItemStack getIcon();

    public void onClick(Player player, ItemStack itemStack) {

    }
}
